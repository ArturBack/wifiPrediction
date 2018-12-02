package data;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import data.model.ConvertedDataItem;
import data.model.DataItem;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DataIO {

    public static Character SEPARATOR = ';';

    public static List<DataItem> loadData(Path path) {
        List<DataItem> result = null;
        try (Reader reader = Files.newBufferedReader(path)) {
            CsvToBean<DataItem> csvToBean =
                    new CsvToBeanBuilder(reader)
                            .withType(DataItem.class)
                            .withSeparator(SEPARATOR)
                            .build();

            result = csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<ConvertedDataItem> loadProcessedData(Path path) {
        List<ConvertedDataItem> result = null;
        try (Reader reader = Files.newBufferedReader(path)) {
            CsvToBean<ConvertedDataItem> csvToBean =
                    new CsvToBeanBuilder(reader)
                            .withType(ConvertedDataItem.class)
                            .withSeparator(SEPARATOR)
                            .build();

            result = csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <T> void saveData(Path path, List<T> items) {
        path.getParent().toFile().mkdirs();
        if (path.toFile().exists()) {
            path.toFile().delete();
        }
        try (Writer writer = Files.newBufferedWriter(path)) {
            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withSeparator(SEPARATOR)
                    .build();

            beanToCsv.write(items);
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }

    }

}
