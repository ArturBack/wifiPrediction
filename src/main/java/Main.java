import classification.MLPClassifier;
import data.DataProcessor;

public class Main {

    public static void main(String[] args) {

    /* DataProcessor processor = new DataProcessor();
      processor.processTrainData();*/

        MLPClassifier mlpClassifier = new MLPClassifier();
        try {
            mlpClassifier.trainModel();
            mlpClassifier.evaluateTestData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
