package test.backTests.scarecrow;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.scarecrow.Get_pay_auth_req;
import org.example.qaTransactionTeam.backEnd.scarecrow.Send_pay_auth_res;
import org.example.qaTransactionTeam.backEnd.scarecrow.Send_tds;
import org.example.qaTransactionTeam.backEnd.scarecrow.Single_lookup;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

public class ScarecrowTests extends BaseTest {

    private String acqId = "2101";
    private Get_pay_auth_req request;

    @Test
    public void addNewEvent3ds1() throws KeyManagementException, TimeoutException, NoSuchAlgorithmException, IOException, URISyntaxException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
       logStartTest("addNewEvent3ds1");

        request = new Get_pay_auth_req();
        request.cleanQueue();
        request.sendToRabbit(1, acqId);
        request.getRabbit().closeConn();

        logFinishTest("addNewEvent3ds1");
    }

    @Test
    public void endVerification3ds1() throws KeyManagementException, TimeoutException, NoSuchAlgorithmException, IOException, URISyntaxException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        logStartTest("endVerification3ds1");

        String token = "Z46DAYG244BIKQJW";

        String pares = "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";

        logger.info(pares);
        Send_pay_auth_res request1 = new Send_pay_auth_res(1,"2101");
        request1.sendToRabbit(token,pares);
        request1.getRabbit().closeConn();

        logFinishTest("endVerification3ds1");
    }

    @Test
    public void addNewEvent3ds2() throws KeyManagementException, TimeoutException, NoSuchAlgorithmException, IOException, URISyntaxException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        logStartTest("addNewEvent3ds2");

        request = new Get_pay_auth_req();
        request.cleanQueue();
        request.sendToRabbit(2, acqId);
        request.getRabbit().closeConn();

        logFinishTest("addNewEvent3ds2");
    }

    @Test
    public void endVerification3ds2() throws KeyManagementException, TimeoutException, NoSuchAlgorithmException, IOException, URISyntaxException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        logStartTest("endVerification3ds2");

        String token = "RT45E9JIRXLPBG8Q";
        String pares = "eyJ0cmFuc1N0YXR1cyI6IlkiLCJtZXNzYWdlVHlwZSI6IkNSZXMiLCJtZXNzYWdlVmVyc2lvbiI6IjIuMS4wIiwiYWNzVHJhbnNJRCI6IjkxOGZiZDcwLWJlZmEtNDFlYi1iMTliLTZlMjk1NmFlMDAyOCIsImRzVHJhbnNJRCI6IjkxOGZiZDcwLWJlZmEtNDFlYi05MjlhLTk1N2VlMmZiNTVkNCIsInRocmVlRFNTZXJ2ZXJUcmFuc0lEIjoiOGI0ZGI4NDAtYmVmYS00MWViLThhOGYtN2U5OThjNGUwNGExIn0=";

        Send_pay_auth_res request1 = new Send_pay_auth_res(2,"2101");
        request1.sendToRabbit(token,pares);
        request1.getRabbit().closeConn();

        logFinishTest("endVerification3ds2");
    }

    @Test
    public void makeLookUp() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, KeyManagementException, TimeoutException, NoSuchAlgorithmException, IOException, URISyntaxException {
        logStartTest("makeLookUp");

        Single_lookup lookup = new Single_lookup();
        lookup.cleanQueue();
        lookup.sendToRabbit("2101");
        lookup.getRabbit().closeConn();

        logFinishTest("makeLookUp");
    }

    @Test
    public void sendtds() throws KeyManagementException, TimeoutException, NoSuchAlgorithmException, IOException, URISyntaxException {

        String token = "RT45E9JIRXLPBG8Q";
        String value = "eyJ0aHJlZURTU2VydmVyVHJhbnNJRCI6ImUzODk5MWIwLWJlZjktNDFlYi04YThmLTdlOTk4YzRlMDQ5ZSJ9";

        Send_tds tds = new Send_tds();
        tds.sendToRabbit(token,value);
        tds.getRabbit().closeConn();
    }

}
