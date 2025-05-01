package test.backTests.itm;

import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.C2A;
import org.example.qaTransactionTeam.backEnd.itm.GetTransDetails;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_itm;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data1;
import org.json.JSONException;
import org.testng.annotations.Test;


import java.util.HashMap;
import java.util.Map;

public class MT610 {
    //Интеграция API статуса через RabbitMQ через Arnim-Zola

    @Test (priority = 0)
    public void makeOldTransaction() throws JSONException {

        Map<String,String> body = new HashMap<>();
        body.put("senderCardNumber","5355280001301447");
        body.put("amount","100");
        body.put("operationId","2");
        body.put("customFee","0");
        body.put("receiverAccount","26006962500692");
        body.put("expDate","2212");
        body.put("ip","127.0.0.1");
        body.put("fingerprint","tests25");

        C2A c2a = new C2A(body, Cards_data1.getData(Card.FUIB_MC, Card_param.token));

    }

    @Test (priority = 1)
    public void getDetails() throws JSONException {
        Trans_token_itm tok = new Trans_token_itm();
        GetTransDetails det = new GetTransDetails("100000022785",tok);
    }
}
