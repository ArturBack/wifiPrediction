package data;

import data.metadata.MetaDataInfo;
import data.model.ConvertedDataItem;
import data.model.DataItem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class DataProcessor {

    private Logger logger = Logger.getLogger(getClass().getSimpleName());

    public static final String TRAIN_DATA_FILEPATH = "testdata/trainData.csv";
    private String DATE_PATTERN = "yyyy-MM-dd";

    private MetaDataInfo dataInfo;
    private String apName;

    public DataProcessor(MetaDataInfo dataInfo, String apName) {
        this.dataInfo = dataInfo;
        this.apName = apName;
    }

    public void processData() {
        try (Stream<Path> paths = Files.walk(Paths.get(dataInfo.getDataDirectorName()))) {
            paths
                    .filter(path -> Files.isRegularFile(path))
                    .forEach(this::convertData);

        } catch (IOException e) {
            e.printStackTrace();
        }
        joinAllTrainData();
    }

    private void convertData(Path path) {
        List<DataItem> items = DataIO.loadData(path, DataItem.class);
        logger.info("Loaded: " + path.getFileName().toString());

        List<ConvertedDataItem> convertedDataItems = convertData(items);

        Path destinationPath = getDestinationPath(path);
        DataIO.saveData(destinationPath, convertedDataItems);

        logger.info("Saved: " + path.getFileName().toString() + " with size: " + convertedDataItems.size());
    }

    private Path getDestinationPath(Path path) {
        return Paths.get(getProcessedDataDirectoryName().concat(path.getFileName().toString()));
    }

    private String getProcessedDataDirectoryName() {
        return ProcessedDataPathProvider.getProcessedDataDirectoryPath(dataInfo);
    }

    private List<ConvertedDataItem> convertData(List<DataItem> items) {
        Stream<ConvertedDataItem> stream = items
                .stream()
                .filter(dataItem -> apName.equals(dataItem.getApName()))
                .map(this::toConvertedDataItem);
        return stream.collect(Collectors.toList());
    }

    private ConvertedDataItem toConvertedDataItem(DataItem dataItem) {
        Pattern pattern = Pattern.compile("--");
        String[] splittedDate = pattern.split(dataItem.getDate());

        String date = splittedDate[0];
        String time = splittedDate[1];
        int weekDay = getDayFromDate(date);
        int hour = getHourFromTime(time);
        int minute = getMinuteFromTime(time);
        int isSchool = isSchool(date);

        return new ConvertedDataItem(weekDay, hour, minute, isSchool, dataItem.getChannelUtilization(), dataItem.getClientsNumber());
    }

    private int getHourFromTime(String time) {
        Pattern pattern = Pattern.compile("-");
        String[] splittedTime = pattern.split(time);

        String hour = splittedTime[0];
        return hour.startsWith("0") ? Integer.valueOf(hour.substring(1, 2)) : Integer.valueOf(hour);
    }

    private int getMinuteFromTime(String time) {
        Pattern pattern = Pattern.compile("-");
        String[] splittedTime = pattern.split(time);

        String minute = splittedTime[1];
        return minute.startsWith("0") ? Integer.valueOf(minute.substring(1, 2)) : Integer.valueOf(minute);
    }

    private int isSchool(String date) {
        return dataInfo.getNoSchoolDates()
                .stream()
                .anyMatch(noSchoolDate -> noSchoolDate.equals(date)) ? 0 : 1;
    }

    private int getDayFromDate(String stringDate) {
        try {
            SimpleDateFormat formatedDate = new SimpleDateFormat(DATE_PATTERN);
            Date date = formatedDate.parse(stringDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(Calendar.DAY_OF_WEEK);
        } catch (ParseException e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        return -1;
    }

    private void joinAllTrainData() {
        DataIO.saveData(Paths.get(getProcessedDataDirectoryName().concat(TRAIN_DATA_FILEPATH)), loadAllTrainData());
    }

    private List<ConvertedDataItem> loadAllTrainData(){
        List<ConvertedDataItem> convertedDataItems = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(getProcessedDataDirectoryName()))) {
            paths
                    .filter(path -> Files.isRegularFile(path))
                    .forEach(path -> convertedDataItems.addAll(DataIO.loadData(path, ConvertedDataItem.class)));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertedDataItems;
    }
}
