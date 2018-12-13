package classification;

import classification.model.ModelIO;
import data.ProcessedDataPathProvider;
import data.metadata.MetaDataInfo;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;

import static data.DataProcessor.TRAIN_DATA_FILEPATH;

public class MLPClassifier {

    private static final double LEARNING_RATE = 0.1;
    private static final double MOMENTUM = 0.2;
    private static final int TRAINING_TIME = 10000;
    private static final int SEED = 123;
    private static final String HIDDEN_LAYERS_STRUCTURE = "20,10,101";

    public static void trainModel(MetaDataInfo trainDataInfo) {
        try {
            Instances trainData = DataProvider.loadData(getTrainDataDirectoryPath(trainDataInfo));
            trainData.setClassIndex(trainData.numAttributes() - 1);

            MultilayerPerceptron model = buildNeuralNetwork();
            model.buildClassifier(trainData);
            ModelIO.saveModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static MultilayerPerceptron buildNeuralNetwork() {
        MultilayerPerceptron model = new MultilayerPerceptron();
        model.setLearningRate(LEARNING_RATE);
        model.setMomentum(MOMENTUM);
        model.setTrainingTime(TRAINING_TIME);
        model.setSeed(SEED);
        model.setHiddenLayers(HIDDEN_LAYERS_STRUCTURE);
        return model;
    }

    private static String getTrainDataDirectoryPath(MetaDataInfo trainMetaDataInfo) {
        return ProcessedDataPathProvider.getProcessedDataDirectoryPath(trainMetaDataInfo).concat(TRAIN_DATA_FILEPATH);
    }
}
