package test.backTests.visa;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.visa.visaAllias.CardsOnFiles;
import org.testng.annotations.Test;

@Test
public class CardsOnFilesTest extends BaseTest {

    String body = "";
    CardsOnFiles cardsOnFiles = new CardsOnFiles();

    public void test_cardsOnFiles(){
        logStartTest("test_cardsOnFiles");
        body = "{\n" +
                "  \"request_data\": {\n" +
                "    \"group\": \"STANDARD\",\n" +
                "    \"pANs\": [\n" +
                "      \"4263447290384681\"\n" +
                "    ]\n" +
                "  },\n" +
                "  \"request_header\": {\n" +
                "    \"message_date_time\": \"2023-09-11 06:01:00.601\",\n" +
                "    \"request_message_id\": \"7da60e1b8b024532a2e0eacb1af5858132a2e0eacb1af58587\"\n" +
                "  }\n" +
                "}";
        cardsOnFiles.cardsOnFiles(body);
        System.out.println(body);
        logFinishTest("test_cardsOnFiles");
    }
}
