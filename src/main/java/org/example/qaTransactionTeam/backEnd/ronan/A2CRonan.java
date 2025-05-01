package org.example.qaTransactionTeam.backEnd.ronan;

import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class A2CRonan extends Ronan{

    public void A2C(String pan) throws KeyManagementException, TimeoutException, NoSuchAlgorithmException, IOException, URISyntaxException, InterruptedException, JSONException {
        token();
        register(pan);
        process();

    }
}
