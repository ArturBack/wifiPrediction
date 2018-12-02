package classification;

import data.metadata.MetaData2015Week1;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

import java.io.File;
import java.io.IOException;

import static data.DataIO.SEPARATOR;
import static data.DataProcessor.DATA_DIR_PROCESSED;
import static data.DataProcessor.TRAIN_DATA_FILENAME;

public class DataProvider {

    public static Instances loadTrainData(String directoryName) {
        return loadData(directoryName.concat(DATA_DIR_PROCESSED).concat(TRAIN_DATA_FILENAME));
    }

    public static Instances loadTestData(String path) {
        return loadData(path);
    }

    private static Instances loadData(String path) {
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
