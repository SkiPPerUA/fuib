package org.example.qaTransactionTeam.backEnd.mobyPay;

import org.json.JSONException;

import java.io.IOException;

public class MobyTrans extends MobyPayTrans{

    public MobyTrans(String amount, String payer, boolean hold) throws JSONException {
        makeTrans(hold,amount,payer);
    }

    public MobyTrans(String amount, String payer, boolean hold, int threeDS) throws JSONException, IOException {
        makeTrans(hold,amount,payer,threeDS);
    }

    public MobyTrans(String amount, String payer, boolean hold, String identification, int threeDS) throws JSONException, IOException {
        makeTrans(hold,amount,payer,identification,threeDS);
    }

    public MobyTrans(String body, int threeDS) throws JSONException, IOException {
        makeTrans(body,threeDS);
    }

}
