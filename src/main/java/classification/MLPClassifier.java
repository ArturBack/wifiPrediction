package classification;

import data.DataIO;
import data.metadata.MetaData2015Week1;
import data.metadata.MetaDataInfo;
import data.model.PredictedDataItem;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static data.DataProcessor.DATA_DIR_PROCESSED;
import static data.DataProcessor.TRAIN_DATA_FILENAME;

public class MLPClassifier {

    private static final String MODEL_NAME = "model.weka";
    private static final String PREDICTIONS_DIR = "predictions/";
    private MetaDataInfo metaDataInfo = new MetaData2015Week1();

    public void trainModel() {
        try {
            Instances trainData = DataProvider.loadTrainData(metaDataInfo.getDataDirectorName());
            trainData.setClassIndex(trainData.numAttributes() - 1);

            MultilayerPerceptron model = buildNeuralNetwork();
            buildClassifier(model, trainData);

            Evaluation evaluation = new Evaluation(trainData);
            //evaluation.crossValidateModel(model, trainData, 10, new Random(1));

            evaluation.evaluateModel(model, trainData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MultilayerPerceptron buildNeuralNetwork() {
        MultilayerPerceptron model = new MultilayerPerceptron();
        model.setLearningRate(0.1);
        model.setMomentum(0.2);
        model.setTrainingTime(20000);
        model.setHiddenLayers("20,10,101");
        model.setSeed(123);
        return model;
    }

    private void buildClassifier(MultilayerPerceptron neuralNetwork, Instances trainData) throws Exception {
        neuralNetwork.buildClassifier(trainData);
        saveModel(neuralNetwork);
    }

    private void saveModel(MultilayerPerceptron neuralNetwork) throws Exception {
        SerializationHelper.write(new FileOutputStream(MODEL_NAME), neuralNetwork);
    }

    private MultilayerPerceptron readModel() throws Exception {
        File file = new File(MODEL_NAME);
        if (!file.exists()) {
            return null;
        }
        return (MultilayerPerceptron) SerializationHelper.read(new FileInputStream(file));
    }

    public void evaluateTestData() throws Exception {
        MultilayerPerceptron model = readModel();
        if (model == null) {
            return;
        }
        try (Stream<Path> paths = Files.walk(Paths.get(getProcessedDataDirectoryName()))) {
            paths
                    .filter(path -> Files.isRegularFile(path) && !path.endsWith(TRAIN_DATA_FILENAME))
                    .forEach(path -> evaluateData(model, path));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void evaluateData(MultilayerPerceptron model, Path path) {
        Instances testData = loadTestData(path);
        List<PredictedDataItem> predictions = new ArrayList<>();

        double max = 0;
        for (int i = 0; i < testData.numInstances(); i++) {
            try {
                //evaluate prediction
                Instance instance = testData.instance(i);
                double prediction = model.distributionForInstance(instance)[0];

                predictions.add(createPredictionItem(instance, prediction));
                max = Math.max(max, prediction);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        DataIO.saveData(Paths.get(PREDICTIONS_DIR.concat(path.getFileName().toString())), predictions);
        System.out.println("Data number: " + max);
    }

    private PredictedDataItem createPredictionItem(Instance instance, double prediction) {
        int day = (int) instance.value(0);
        int hour = (int) instance.value(1);
        int minute = (int) instance.value(2);
        int isSchool = (int) instance.value(3);
        int channelUtilization = (int) instance.value(4);
        int clientsNumber = (int) instance.value(5);
        return new PredictedDataItem(day, hour, minute, isSchool, channelUtilization, clientsNumber, (int) Math.max(prediction - 1, 0));
    }

    private String getProcessedDataDirectoryName() {
        return metaDataInfo.getDataDirectorName().concat(DATA_DIR_PROCESSED);
    }

    private Instances loadTestData(Path path) {
        Instances testData = DataProvider.loadTestData(path.toFile().getAbsolutePath());
        testData.setClassIndex(testData.numAttributes() - 1);
        return testData;
    }
}
