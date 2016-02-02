
import com.uni.sofia.fmi.dm.categorization.classifier.Classifier;
import com.uni.sofia.fmi.dm.categorization.classifier.Validator;
import com.uni.sofia.fmi.dm.categorization.utils.TokensHolder;
import com.uni.sofia.fmi.dm.categorization.utils.console.ChoiceHandler;
import com.uni.sofia.fmi.dm.categorization.utils.console.ConsoleApp;
import com.uni.sofia.fmi.dm.categorization.utils.parser.InformationParser;
import com.uni.sofia.fmi.dm.categorization.utils.parser.JsonFileEntity;
import com.uni.sofia.fmi.dm.categorization.utils.parser.JsonInformationParser;
import com.uni.sofia.fmi.dm.categorization.utils.parser.ObjectMapperWrapper;
import com.uni.sofia.fmi.dm.categorization.utils.parser.StanfordReviewsConverter;
import com.uni.sofia.fmi.dm.categorization.utils.serialization.ObjectSerializer;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Dimitar
 */
public class Main {

    // Place your folders here
    private static final String ORIGINAL_TRAIN_DATA_FOLDER = "D:\\aclImdb_v1\\aclImdb\\train";
    private static final String ORIGINAL_TEST_DATA_FOLDER = "D:\\aclImdb_v1\\aclImdb\\test";

    private static final String TRAIN_DATA_JSON = "stanfordDataTrain.json";
    private static final String TEST_DATA_JSON = "stanfordDataTest.json";

    public static void main(String[] args) throws IOException {

        // If the data has not been converted to JSON, uncomment theese 3 lines and 
        // load yourself with patience
//        StanfordReviewsConverter.convertTxtToJson(TRAIN_DATA_FOLDER, TRAIN_DATA_JSON);
//        StanfordReviewsConverter.convertTxtToJson(TEST_DATA_FOLDER, TEST_DATA_JSON);
//        System.out.println("Data converted!");

        InformationParser ip = new JsonInformationParser();
        TokensHolder tokenHolder = ip.parse(TRAIN_DATA_JSON);

        JsonFileEntity jsonFileEntity = (JsonFileEntity) ObjectMapperWrapper.readFile(new File(TEST_DATA_JSON), JsonFileEntity.class);
        Classifier classifier = new Classifier(tokenHolder);

        //Will show validation percentage
        //new Validator(classifier, jsonFileEntity).validate();

        // Start the CLI
        ChoiceHandler ch = new ChoiceHandler(tokenHolder, classifier);
        ConsoleApp ca = new ConsoleApp(ch);
        ca.startApp();
    }
}
