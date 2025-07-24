package test.backTests.loomis;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.loomis.Loomis;
import org.testng.annotations.Test;

@Test
public class Loomis_test extends BaseTest {

    Loomis loomis = new Loomis();

    public void positive(){
        loomis.getDocument("{\n" +
                "\"document_id\": [\"11408738400\",\"11408738399\"]\n" +
                "}");
    }
}
