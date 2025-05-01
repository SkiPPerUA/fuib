package test.backTests.itm.transaction;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.P2P;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data1;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class P2P_regress extends BaseTest {

    public String amount = "100";
    Map<String,String> body = new HashMap<>();

    @Test
    void p2p_with3ds2(){
        make_p2p_threeDS(Card.MONO_MC, Card.MONO_MC);
    }

    @Test
    void p2p(){
        make_p2p(Card.MONO_MC, Card.MONO_MC);
    }

    public P2P make_p2p_threeDS(Card cardSender, Card cardReceiver){
        set_body(cardSender, cardReceiver);
        return new P2P(body,2);
    }

    public P2P make_p2p(Card cardSender, Card cardReceiver){
        set_body(cardSender, cardReceiver);
        return new P2P(body);
    }

    private void set_body(Card cardSender, Card cardReceiver){
        body.put("senderCardNumber", Cards_data1.getData(cardSender, Card_param.pan));
        body.put("receiverCardNumber", Cards_data1.getData(cardReceiver,Card_param.pan));
        body.put("expDate", Cards_data1.getData(cardSender,Card_param.expire));
        body.put("amount", amount);
        body.put("details.source","01");
        body.put("sli","242");
        body.put("details.merchantUrl","merchantUrl");
        body.put("details.paymentUrl","paymentUrl");
        body.put("details.additionalMessage","additionalMessage");
        body.put("details.independentSalesOrganizationId","independentSalesOrganizationId");
    }
}
