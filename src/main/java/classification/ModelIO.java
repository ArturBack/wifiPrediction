package classification;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.SerializationHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ModelIO {

    private static final String MODEL_NAME = "model.weka";

    public static void saveModel(MultilayerPerceptron neuralNetwork) throws Exception {
        SerializationHelper.write(new FileOutputStream(MODEL_NAME), neuralNetwork);
    }

    public static MultilayerPerceptron readModel() throws Exception {
        File file = new File(MODEL_NAME);
        if (!file.exists()) {
            return null;
        }
        return (MultilayerPerceptron) SerializationHelper.read(new FileInputStream(file));
    }
}
