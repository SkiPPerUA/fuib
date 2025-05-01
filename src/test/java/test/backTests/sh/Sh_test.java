package test.backTests.sh;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.shCompliance.SHCompliance;
import org.testng.annotations.Test;

@Test
public class Sh_test extends BaseTest {

    public void getDatasetValues() {
        new SHCompliance().getDatasetValues("\n" +
                "{\n" +
                "    \"datasetId\": 318,\n" +
                "    \"value\": \"?C7YJ5HA28FTK4EU\"\n" +
                "}");
    }

    public void setDatasetValues(){
        new SHCompliance().setDatasetValues("{\n" +
                "    \"datasetId\": 318,\n" +
                "    \"values\": [\n" +
                "        {\n" +
                "            \"value\": \"?C977C2KLVC0QW7W\",\n" +
                "            \"expTime\": 1776340876\n" +
                "        }\n" +
                "    ]\n" +
                "}");
    }
}
