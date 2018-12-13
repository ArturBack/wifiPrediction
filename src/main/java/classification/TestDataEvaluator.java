package classification;

import classification.model.ModelIO;
import data.DataIO;
import data.ProcessedDataPathProvider;
import data.metadata.MetaDataInfo;
import data.model.PredictedDataItem;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instance;
import weka.core.Instances;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static data.DataProcessor.TRAIN_DATA_FILEPATH;
import static data.model.ConvertedDataItem.*;

public class TestDataEvaluator {

    private static final String PREDICTIONS_DIR = "predictions/";

    public static void evaluateTestData(MetaDataInfo testDataInfo) throws Exception {
        MultilayerPerceptron model = ModelIO.readModel();
        if (model == null) {
            return;
        }
        try (Stream<Path> paths = Files.walk(getTestDataDirectoryPath(testDataInfo))) {
            paths
                    .filter(path -> Files.isRegularFile(path) && !path.endsWith(TRAIN_DATA_FILEPATH))
                    .forEach(path -> evaluateData(model, path));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Path getTestDataDirectoryPath(MetaDataInfo testData) {
        return Paths.get(ProcessedDataPathProvider.getProcessedDataDirectoryPath(testData));
    }

    private static void evaluateData(MultilayerPerceptron model, Path path) {
        Instances testData = loadTestData(path);
        List<PredictedDataItem> predictions = new ArrayList<>();

        for (Instance instance : testData) {
            try {
                double prediction = model.distributionForInstance(instance)[0];
                predictions.add(createPredictionItem(instance, prediction));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        DataIO.saveData(createPredictionsFilePath(path), predictions);
    }

    private static Instances loadTestData(Path path) {
        Instances testData = DataProvider.loadData(path.toFile().getAbsolutePath());
        testData.setClassIndex(testData.numAttributes() - 1);
        return testData;
    }

    private static PredictedDataItem createPredictionItem(Instance instance, double prediction) {
        int day = (int) instance.value(DAY_POSITION);
        int hour = (int) instance.value(HOUR_POSITION);
        int minute = (int) instance.value(MINUTE_POSITION);
        int isSchool = (int) instance.value(IS_SCHOOL_POSITION);
        int channelUtilization = (int) instance.value(CHANNEL_UTILIZATION_POSITION);
        int clientsNumber = (int) instance.value(CLIENTS_NUMBER_POSITION);
        int finalPrediction = (int) Math.max(prediction - 1, 0);
        return new PredictedDataItem(day, hour, minute, isSchool, channelUtilization, clientsNumber, finalPrediction);
    }

    private static Path createPredictionsFilePath(Path filePath) {
        return Paths.get(PREDICTIONS_DIR.concat(filePath.getFileName().toString()));
    }


}
