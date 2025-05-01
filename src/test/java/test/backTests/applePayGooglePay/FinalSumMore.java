package test.backTests.applePayGooglePay;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_itm;
import org.example.qaTransactionTeam.backEnd.transaction.AppleGoogleReversalRefund;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.MobilePay;
import org.example.qaTransactionTeam.backEnd.utils.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.sql.SQLException;
import java.util.List;

@Test
public class FinalSumMore extends BaseTest {

    private final Card card = Card.FUIB_MC;
    private final int amountPreauth = 1000;
    private final int acq = 2101;
    private final float percent = 20.00f;
    String body;
    MobilePay trans;
    AppleGoogleReversalRefund reversalRefund;

    public void tw(){
        List<String> amounts = List.of(String.valueOf(amountPreauth),String.valueOf(amountPreauth-1),String.valueOf(amountPreauth+1));
        amounts.forEach(x-> {
            trans = new MobilePay(body,0, x);
        });
    }

    public void test_finalize(){
        setBD(true,percent);
        //positive
        List<String> amounts = List.of(String.valueOf(amountPreauth),String.valueOf(amountPreauth-1),String.valueOf(amountPreauth+1));
        amounts.forEach(x-> {
            trans = new MobilePay(body,0, x);
            Assert.assertFalse(trans.getResponse().contains("\"code\":\"RF099\""));
        });
        trans = new MobilePay(body,0,String.valueOf((int) (amountPreauth+(amountPreauth*percent/100))));
        Assert.assertFalse(trans.getResponse().contains("\"code\":\"RF099\""));
        //positive regress
        setBD(false,percent);
        amounts = List.of(String.valueOf(amountPreauth),String.valueOf(amountPreauth-1));
        amounts.forEach(x-> {
            trans = new MobilePay(body,0, x);
            Assert.assertFalse(trans.getResponse().contains("\"code\":\"RF099\""));
        });

        //negative
        setBD(true,percent);
        trans = new MobilePay(body,0, String.valueOf((int) (amountPreauth+(amountPreauth*percent/100)+1)));
        Assert.assertTrue(trans.getResponse().contains("\"code\":\"RF024\""));
        setBD(false,percent);
        trans = new MobilePay(body,0, String.valueOf(amountPreauth+1));
        Assert.assertTrue(trans.getResponse().contains("\"code\":\"RF017\""));
    }

    public void test_reversal(){
        //positive
        setBD(true,percent);

        trans = new MobilePay(body,0, String.valueOf(amountPreauth+1));
        reversalRefund = new AppleGoogleReversalRefund();
        reversalRefund.reversal(trans.getSessionId(),String.valueOf(amountPreauth+1));
        Assert.assertTrue(reversalRefund.getReversalInfo().contains("\"amount\":\""+amountPreauth+"\""));

        trans = new MobilePay();
        trans.setToken(new Trans_token_itm());
        trans.setThreeDS(0);
        trans.register(body);
        reversalRefund = new AppleGoogleReversalRefund();
        reversalRefund.reversal(trans.getSessionId(),String.valueOf(amountPreauth+1));
        Assert.assertTrue(reversalRefund.getReversalInfo().contains("\"amount\":\""+amountPreauth+"\""));

        //positive regress
        setBD(false,percent);

        trans = new MobilePay(body,0, String.valueOf(amountPreauth));
        reversalRefund = new AppleGoogleReversalRefund();
        reversalRefund.reversal(trans.getSessionId(),String.valueOf(amountPreauth+1));
        Assert.assertTrue(reversalRefund.getReversalInfo().contains("\"amount\":\""+amountPreauth+"\""));

        trans = new MobilePay();
        trans.setToken(new Trans_token_itm());
        trans.setThreeDS(0);
        trans.register(body);
        reversalRefund = new AppleGoogleReversalRefund();
        reversalRefund.reversal(trans.getSessionId(),String.valueOf(amountPreauth+1));
        Assert.assertTrue(reversalRefund.getReversalInfo().contains("\"amount\":\""+amountPreauth+"\""));
    }

    public void test_refund(){
        //positive
        setBD(true,percent);

        trans = new MobilePay(body,0, String.valueOf(amountPreauth+1));
        reversalRefund = new AppleGoogleReversalRefund();
        reversalRefund.refund(trans.getSessionId(),String.valueOf(amountPreauth+1));
        Assert.assertTrue(reversalRefund.getRefundInfo().contains("\"amount\":\""+(amountPreauth+1)+"\""));

        trans = new MobilePay(body,0, String.valueOf(amountPreauth+1));
        reversalRefund = new AppleGoogleReversalRefund();
        reversalRefund.refund(trans.getSessionId(),String.valueOf(amountPreauth));
        Assert.assertTrue(reversalRefund.getRefundInfo().contains("\"amount\":\""+(amountPreauth)+"\""));
        reversalRefund.refund(trans.getSessionId(),String.valueOf(1));
        Assert.assertTrue(reversalRefund.getRefundInfo().contains("\"amount\":\""+1+"\""));
        reversalRefund.refund(trans.getSessionId(),String.valueOf(1));
        Assert.assertTrue(reversalRefund.getRefundInfo().contains("\"code\":\"RF006\""));

        //positive regress
        setBD(false,percent);

        trans = new MobilePay(body,0, String.valueOf(amountPreauth));
        reversalRefund = new AppleGoogleReversalRefund();
        reversalRefund.refund(trans.getSessionId(),String.valueOf(amountPreauth));
        Assert.assertTrue(reversalRefund.getRefundInfo().contains("\"amount\":\""+(amountPreauth)+"\""));

        trans = new MobilePay(body,0, String.valueOf(amountPreauth));
        reversalRefund = new AppleGoogleReversalRefund();
        reversalRefund.refund(trans.getSessionId(),String.valueOf(amountPreauth-1));
        Assert.assertTrue(reversalRefund.getRefundInfo().contains("\"amount\":\""+(amountPreauth-1)+"\""));
        reversalRefund.refund(trans.getSessionId(),String.valueOf(1));
        Assert.assertTrue(reversalRefund.getRefundInfo().contains("\"amount\":\""+1+"\""));


        //negative
        setBD(false,percent);

        trans = new MobilePay(body,0, String.valueOf(amountPreauth));
        reversalRefund = new AppleGoogleReversalRefund();
        reversalRefund.refund(trans.getSessionId(),String.valueOf(amountPreauth+1));
        Assert.assertTrue(reversalRefund.getRefundInfo().contains("\"code\":\"RF006\""));

        trans = new MobilePay(body,0, String.valueOf(amountPreauth));
        reversalRefund = new AppleGoogleReversalRefund();
        reversalRefund.refund(trans.getSessionId(),String.valueOf(amountPreauth));
        Assert.assertTrue(reversalRefund.getRefundInfo().contains("\"amount\":\""+(amountPreauth)+"\""));
        reversalRefund.refund(trans.getSessionId(),String.valueOf(1));
        Assert.assertTrue(reversalRefund.getRefundInfo().contains("\"code\":\"RF006\""));
    }

    @BeforeTest
    void openCon() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        body = "{\n" +
                "\t\"amount\":\""+amountPreauth+"\",\n" +
                "\t\"card_number\":\""+ Cards_data.getData(card, Card_param.pan)+"\",\n" +
                "\t\"experation_date\":\""+ Cards_data.getData(card, Card_param.expire)+"\",\n" +
                "\t\"cvv\": \""+ Cards_data.getData(card, Card_param.cvv)+"\", \n" +
                "\t\"ucaf\":\"AAABA5RZlAAAA5cZGFmUAAAAAAA=\",\n" +
                "\t\"ext_trans_id\": \"12wq3e50\",\n" +
                "\t\"info_3ds\":{\n" +
                "\t\t\"ip\":\"0.0.0.127\",\n" +
                "\t\t\"fingerprint\":\"1q2w3e4r5t\",\n" +
                "\t\t\"without_3ds\": true,\n" +
                "\t\t\"version\": \"2.1.0\",\n" +
                "\t\t\"channel\":\"BRW\",\n" +
                "\t\t\"accept_header\":\"text/html\",\n" +
                "\t\t\"java_enabled\":false,\n" +
                "\t\t\"language\":\"RU-ru\",\n" +
                "\t\t\"color_depth\":32,\n" +
                "\t\t\"screen_height\":800,\n" +
                "\t\t\"screen_width\":900, \n" +
                "\t\t\"time_zone\":-180,\n" +
                "\t\t\"user_agent\":\"Gecko\",\n" +
                "\t\t\"challenge_window_size\":\"04\",\n" +
                "\t\t\"return_url\":\"https://service.fuib.com\"\n" +
                "\t}}";
        BDas400.BDas400("ITMTST", Configs.ITMTST_ALL_NAME, Configs.ITMTST_ALL_PASSWORD);
    }

    @AfterTest
    void closeConn() throws SQLException {
        BDas400.closeConn();
    }

    private void setBD(boolean can, float percent){
        try {
            BDas400.updateSQL("update itm22d.vmtconnvl set vcvalue = '"+can+"' where VCKEY = 'IS_FINALIZE_MORE_THAN_PREAUTH' AND VCOWNERID = "+acq);
            BDas400.updateSQL("update itm22d.vmtconnvl set vcvalue = '"+percent+"' where VCKEY = 'DELTA_PERCENT_FINALIZE' AND VCOWNERID = "+acq);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
