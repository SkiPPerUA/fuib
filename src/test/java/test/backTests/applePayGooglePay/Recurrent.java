package test.backTests.applePayGooglePay;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.token.Trans_token_itm;
import org.example.qaTransactionTeam.backEnd.transaction.AppleGoogleReversalRefund;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.MobilePay;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.RecurrentAGPay;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.example.qaTransactionTeam.backEnd.utils.Configs;
import org.testng.annotations.Test;

@Test
public class Recurrent extends BaseTest {


    Card cardAGP = Card.MONO_MC;
    String amountAGP = "101";
    boolean agpWithout3ds = false;

    Card cardRecur = Card.MONO_MC;
    String amountRecur = "150";
    boolean recurWithout3ds = true;

    String recurrent_id = "009MCC0001GL";

    public void testRecurrent(){
        body = "{\n" +
                "\t\"amount\":\""+amountRecur+"\",\n" +
                "\t\"card_number\":\""+ Cards_data.getData(cardRecur, Card_param.pan) +"\",\n" +
                "\t\"experation_date\":\""+ Cards_data.getData(cardRecur, Card_param.expire)+"\",\n" +
                "\t\"cvv\": \""+ Cards_data.getData(cardRecur, Card_param.cvv)+"\", \n" +
                "\t\"ext_trans_id\": \"12wq3e50\",\n" +
                "\t\"ucaf\": \"AgEAAAkC+X4C6oIDqQA+gPxQEOA=\",\n" +
                "\t\"SLI\": \"247\",\n" +
                "\"recurrent\": {\n" +
                "\t\t\"recurrent_payment_id\": \""+recurrent_id+"\"\n" +
                "\t},"+
                "\t\"info_3ds\":{\n" +
                "\t\t\"ip\":\"0.0.0.127\",\n" +
                "\t\t\"fingerprint\":\"1q2w3e4r5t\",\n" +
                "\t\t\"without_3ds\": %s,\n" +
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
                "\t}\n" +
                "}\n";
        recurrentAGPay = new RecurrentAGPay();
        recurrentAGPay.setBodyRequest(String.format(body,recurWithout3ds));
        if (!recurWithout3ds){
            recurrentAGPay.setThreeDS(2);
        }
        recurrentAGPay.find_summ();
        recurrentAGPay.makeTrans();
        AppleGoogleReversalRefund refund = new AppleGoogleReversalRefund();
        //refund.refund(recurrentAGPay.getSessionId(),"100");
        //refund.reversal(recurrentAGPay.getSessionId());

        //recurrentAGPay.status();
    }

    public void testRecurrent1(){
        body = "{\n" +
                "\t\"amount\":\""+amountRecur+"\",\n" +
                "\t\"card_number\":\""+ Cards_data.getData(cardRecur, Card_param.pan) +"\",\n" +
                "\t\"experation_date\":\""+ Cards_data.getData(cardRecur, Card_param.expire)+"\",\n" +
                "\t\"cvv\": \""+ Cards_data.getData(cardRecur, Card_param.cvv)+"\", \n" +
                "\t\"ext_trans_id\": \"12wq3e50\",\n" +
                "\t\"ucaf\": \"AgEAAAkC+X4C6oIDqQA+gPxQEOA=\",\n" +
                "\t\"SLI\": \"246\",\n" +
                "\"recurrent\": {\n" +
                "\t\t\"recurrent_payment_id\": \""+recurrent_id+"\"\n" +
                "\t},"+
                "\t\"info_3ds\":{\n" +
                "\t\t\"ip\":\"0.0.0.127\",\n" +
                "\t\t\"fingerprint\":\"1q2w3e4r5t\",\n" +
                "\t\t\"without_3ds\": %s,\n" +
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
                "\t}\n" +
                "}\n";
        recurrentAGPay = new RecurrentAGPay();
        recurrentAGPay.setBodyRequest(String.format(body,recurWithout3ds));
        if (!recurWithout3ds){
            recurrentAGPay.setThreeDS(2);
        }
        recurrentAGPay.find_summ();
        recurrentAGPay.makeTrans();
        AppleGoogleReversalRefund refund = new AppleGoogleReversalRefund();
        //refund.refund(recurrentAGPay.getSessionId(),"100");
        //refund.reversal(recurrentAGPay.getSessionId());

        //recurrentAGPay.status();
    }

    public void testRecurrent_withAPG(){
        body = "{\n" +
                "\t\"amount\":\""+amountAGP+"\",\n" +
                "\t\"card_number\":\""+ Cards_data.getData(cardAGP, Card_param.pan) +"\",\n" +
                "\t\"experation_date\":\""+ Cards_data.getData(cardAGP, Card_param.expire)+"\",\n" +
                "\t\"cvv\": \""+ Cards_data.getData(cardAGP, Card_param.cvv)+"\", \n" +
                "\t\"ext_trans_id\": \"12wq3e50\",\n" +
                "\t\"ucaf\": \"AgEAAAkC+X4C6oIDqQA+gPxQEOA=\",\n" +
                "\t\"SLI\": \"246\",\n" +
                "\t\"info_3ds\":{\n" +
                "\t\t\"ip\":\"0.0.0.127\",\n" +
                "\t\t\"fingerprint\":\"1q2w3e4r5t\",\n" +
                "\t\t\"without_3ds\": %s,\n" +
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
                "\t}\n" +
                "}\n";
        mobilePay = new MobilePay();
        mobilePay.setBodyRequest(String.format(body,agpWithout3ds));
        if (!agpWithout3ds){
            mobilePay.setThreeDS(2);
        }
        mobilePay.setToken(new Trans_token_itm("SBPRC_APGP", Configs.PASSWORD_MERCHANT_2101,"2202"));
        mobilePay.find_summ();
        mobilePay.makeTrans();
        recurrent_id = mobilePay.getRecurrent_id();
        testRecurrent();
        testRecurrent();
    }

    public void test(){
        agpWithout3ds = false;
        recurWithout3ds = false;
        testRecurrent_withAPG();
        System.out.println("3DS 1й = "+!agpWithout3ds+" 3DS recurrent = "+!recurWithout3ds);
        System.out.println(mobilePay.getTransactionId()+" -> "+recurrentAGPay.getTransactionId());

        agpWithout3ds = false;
        recurWithout3ds = true;
        testRecurrent_withAPG();
        System.out.println("3DS 1й = "+!agpWithout3ds+" 3DS recurrent = "+!recurWithout3ds);
        System.out.println(mobilePay.getTransactionId()+" -> "+recurrentAGPay.getTransactionId());
    }

    MobilePay mobilePay;
    RecurrentAGPay recurrentAGPay;
    String body;
}
