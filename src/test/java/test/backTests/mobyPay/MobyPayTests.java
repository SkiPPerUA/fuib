package test.backTests.mobyPay;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.mobyPay.MobyTrans;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.example.qaTransactionTeam.backEnd.utils.Configs;
import org.testng.annotations.Test;

import java.io.IOException;

public class MobyPayTests extends BaseTest {

    @Test
    public void transHoldWith3ds() throws IOException {
        String payer = "\"source\": \"GOOGLE_PAN\",\n" +
                "\t\t\t\t\t\t  \"pan\": \""+ Cards_data.getData(Card.MONO_VISA, Card_param.pan) +"\",\n" +
                "              \"expire\": \""+ Cards_data.getData(Card.MONO_VISA, Card_param.expire) +"\"";
        MobyTrans trans = new MobyTrans("100",payer,false,2);
        trans.status();
//        trans.complete_hold("100");
//        trans.complete_hold("101");
//        trans.status();
    }

    @Test
    public void transHoldWithout3ds() throws IOException, InterruptedException {
        logStartTest("transHoldWithout3ds");
        String payer = "\"source\": \"GOOGLE_PAN\",\n" +
                "\t\t\t\t\t\t  \"pan\": \""+ Cards_data.getData(Card.MONO_VISA, Card_param.pan) +"\",\n" +
                "              \"expire\": \"2409\"";
        MobyTrans trans = new MobyTrans("1000",payer,false);
//        trans.complete_hold("1000");
//        trans.refund("150");
//        trans.refund("250");
        trans.status();

        logFinishTest("transHoldWithout3ds");
    }

    @Test
    public void transHoldWithout3dsCraptogram() {
        logStartTest("transHoldWithout3dsCraptogram");
        String payer = "\"source\": \"APPLE_CRYPTOGRAM\",\n" +
                "\t\t\t\t\t\t  \"pan\": \""+ Cards_data.getData(Card.MONO_VISA, Card_param.pan) +"\",\n" +
                "\t\t\t\t\t\t  \"cryptogram\": \"AHAzQmvcgxILAAKhssPUAAADFA==\",\n" +
                "              \"expire\": \"2602\"";
        MobyTrans trans = new MobyTrans("1000",payer,false);
//        trans.complete_hold("1000");
//        trans.refund("150");
//        trans.refund("250");
        trans.status();
    }

    @Test
    public void transWith3ds() throws IOException, InterruptedException {
        logStartTest("transWith3ds");
        String payer = "\"source\": \"GOOGLE_PAN\",\n" +
                "\t\t\t\t\t\t  \"pan\": \""+ Cards_data.getData(Card.MONO_VISA, Card_param.pan) +"\",\n" +
                "              \"expire\": \""+ Cards_data.getData(Card.MONO_VISA, Card_param.expire) +"\"";
        MobyTrans trans = new MobyTrans("102",payer,false,2);
        Thread.sleep(5000);
        trans.status();

        logFinishTest("transWith3ds");
    }

    @Test
    public void transWithout3ds() throws IOException, InterruptedException {
        logStartTest("transWithout3ds");
        String payer = "\"source\": \"GOOGLE_PAN\",\n" +
                "\t\t\t\t\t\t  \"pan\": \""+ Cards_data.getData(Card.MONO_MC, Card_param.pan) +"\",\n" +
                "              \"expire\": \"2409\"";
        MobyTrans trans = new MobyTrans("1104",payer,true);
        Thread.sleep(5000);
        trans.status();
        logFinishTest("transWithout3ds");
    }

    @Test
    public void transHoldWithout3dsAGP(){
        logStartTest("transHoldWithout3dsAGP");
        //VTS, C2P, M4M
        String payer = "\"source\": \"M4M\",\n" +
                "       \"token\": \"5167974893582613\",\n" +
                "       \"cryptogram\": \"AHAzQmvcgxILAAKhssPUAAADFA==\",\n" +
                "       \"expire\": \"2512\"";
        MobyTrans trans = null;
        try {
            trans = new MobyTrans("1000",payer,true, 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        trans.complete_hold("1000");
        logFinishTest("transHoldWithout3dsAGP");
    }
}
