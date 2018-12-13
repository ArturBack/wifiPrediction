package classification;

import weka.core.Instances;
import weka.core.converters.CSVLoader;

import java.io.File;
import java.io.IOException;

import static data.DataIO.SEPARATOR;

public class DataProvider {

    public static Instances loadData(String path) {
        CSVLoader csvLoader = new CSVLoader();
        try {
            csvLoader.setSource(new File(path));
            csvLoader.setNoHeaderRowPresent(true);
            csvLoader.setFieldSeparator(SEPARATOR.toString());
            return csvLoader.getDataSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
