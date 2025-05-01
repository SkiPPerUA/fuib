package test.backTests.itm.transaction;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.P4P;
import org.example.qaTransactionTeam.backEnd.utils.*;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class P4P_regress extends BaseTest {

    public String amount = "100";
    public String payment_amount = "100";

    @Test
    void payment_test(Card cardSender, Card cardReceiver){
        make_p4p(cardSender, cardReceiver);
        transaction.P4Ppayment(payment_amount);
    }

    @Test
    void p4p(){
        make_p4p(Card.FUIB_VISA, Card.FUIB_MC);
    }

    @Test
    void payment_to_another_card_negative(){
        Card another_card = Card.MONO_MC;
        Card cardSender = Card.FUIB_VISA;
        Card cardReceiver = Card.FUIB_MC;

        Assert.assertNotEquals(cardReceiver, another_card, "Карта зачисления на register = карте зачисление на payment:");
        make_p4p(cardSender,cardReceiver);

        transaction.P4Ppayment("60", another_card);
        Assert.assertEquals(new JSONObject(transaction.getResponse()).getString("errorMessage"),"Can't change receiver cardSender");

        transaction.P4Ppayment("60", cardReceiver);
        Assert.assertEquals(transaction.getErrorCode(),"00");

        transaction.P4Ppayment("20", another_card);
        Assert.assertEquals(new JSONObject(transaction.getResponse()).getString("errorMessage"),"Can't change receiver cardSender");

        transaction.P4Ppayment("20", cardReceiver);
        Assert.assertEquals(transaction.getErrorCode(),"00");
    }

    @Test
    void payment_to_another_card_positive() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Card payment_card = Card.MONO_MC;
        Card cardSender = Card.FUIB_VISA;
        Card cardReceiver = Card.FUIB_VISA;

        make_p4p(cardSender,cardReceiver);

        BDas400.BDas400("ITMTST", Configs.ITMTST_ALL_NAME, Configs.ITMTST_ALL_PASSWORD);
        BDas400.callProcedure("CALL ITM22R.VMT2ITRSSP('DELETE', '"+transaction.getTransactionId()+"')");
        BDas400.closeConn();

        transaction.P4Ppayment("60", payment_card);
        Assert.assertEquals(transaction.getErrorCode(),"00");

        transaction.P4Ppayment("20", payment_card);
        Assert.assertEquals(transaction.getErrorCode(),"00");
    }

    public P4P make_p4p(Card cardSender, Card cardReceiver){
        body.put("senderCardNumber", Cards_data.getData(cardSender, Card_param.pan));
        body.put("receiverCardNumber", Cards_data.getData(cardReceiver,Card_param.pan));
        body.put("expDate", Cards_data.getData(cardSender,Card_param.expire));
        body.put("amount", amount);
        body.put("operationId", "119");
        body.put("details.source","01");
        body.put("details.merchantUrl","merchantUrl");
        body.put("details.paymentUrl","paymentUrl");
        body.put("details.additionalMessage","additionalMessage");
        body.put("details.independentSalesOrganizationId","independentSalesOrganizationId");
        transaction = new P4P(body, Cards_data.getData(cardSender, Card_param.cvv));
        return transaction;
    }

    Map<String,String> body = new HashMap<>();
    P4P transaction;

}
