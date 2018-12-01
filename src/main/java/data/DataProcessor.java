package data;

import data.metadata.MetaData2015;
import data.metadata.MetaDataInfo;
import data.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataProcessor {

    private Logger logger = Logger.getLogger(getClass().getSimpleName());

    private String DATA_DIR_PROCESSED = "_PROCESSED/";
    private String AP_NAME = "AP-D2-acf2.c571.70c0";
    private String DATE_PATTERN = "yyyy-MM-dd";
    private MetaDataInfo metaDataInfo = new MetaData2015();

    public void processTrainData() {
        try (Stream<Path> paths = Files.walk(Paths.get(metaDataInfo.getDataDirectorName()))) {
            paths
                    .filter(path -> Files.isRegularFile(path))
                    .forEach(this::processData);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processData(Path path) {
        List<DataItem> items = DataIO.loadData(path);
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
        return metaDataInfo.getDataDirectorName().concat(DATA_DIR_PROCESSED);
    }

    private List<ConvertedDataItem> convertData(List<DataItem> items) {
        Stream<ConvertedDataItem> stream = items
                .stream()
                .filter(dataItem -> AP_NAME.equals(dataItem.getApName()))
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
        return metaDataInfo.getNoSchoolDates()
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
}
