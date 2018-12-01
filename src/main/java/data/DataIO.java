package data;

import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
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

    private static Character SEPARATOR = ';';

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

    public static void saveData(Path path, List<ConvertedDataItem> items) {
        path.getParent().toFile().mkdirs();
        try (Writer writer = Files.newBufferedWriter(path)) {
            StatefulBeanToCsv<ConvertedDataItem> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withSeparator(SEPARATOR)
                    .build();

            beanToCsv.write(items);
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }


    }

}
