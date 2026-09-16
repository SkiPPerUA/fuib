package test.backTests.dazzler;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.dazzler.Receipts;
import org.testng.annotations.Test;

@Test
public class Reciept_test extends BaseTest {

    Receipts api = new Receipts();

    public void positive(){
        api.receiptRequests("{\n" +
                " \"id\": \"f9a09920-6c29-4ac6-9173-9de5111c1e4c\",\n" +
                " \"id_type\": \"TRANSFER_ID\",\n" +
                " \"client_sirius_id\": 100501,\n" +
                " \"client_ekb_id\": 100500,\n" +
                " \"transfer_type\": \"C2A_FOREIGN\"\n" +
                "}");
    }
}
