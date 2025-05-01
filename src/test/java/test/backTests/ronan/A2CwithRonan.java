package test.backTests.ronan;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.ronan.A2CRonan;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.json.JSONException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class A2CwithRonan extends BaseTest {

    @Test
    public void A2Ctransaction() throws InterruptedException, NoSuchAlgorithmException, KeyManagementException, JSONException, TimeoutException, URISyntaxException, IOException {
        A2CRonan a2c = new A2CRonan();
        a2c.A2C(Cards_data.getData(Card.FUIB_MC, Card_param.pan));
    }
}
