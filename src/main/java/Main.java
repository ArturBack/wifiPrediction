import classification.MLPClassifier;
import classification.TestDataEvaluator;
import data.DataProcessor;
import data.metadata.MetaData2015Week1;
import data.metadata.MetaDataInfo;

public class Main {


    public static void main(String[] args) {

        MetaDataInfo trainDataInfo = new MetaData2015Week1();
        MetaDataInfo testDataInfo = new MetaData2015Week1();
        String apName = "AP-D2-acf2.c571.70c0";

        DataProcessor processor = new DataProcessor(trainDataInfo, apName);
        //processor.processData();

        try {
            MLPClassifier.trainModel(trainDataInfo);
            TestDataEvaluator.evaluateTestData(testDataInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
