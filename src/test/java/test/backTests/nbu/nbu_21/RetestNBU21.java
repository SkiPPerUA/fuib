package test.backTests.nbu.nbu_21;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.P4P;
import org.example.qaTransactionTeam.backEnd.utils.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Test
public class RetestNBU21 extends BaseTest {

    Map<String, String> body;
    Card senderCard = Card.MONO_MC;
    Card receiverCard = Card.MONO_MC;
    String source = "01"; //03 счет       02 кэш       01 карта
    String [] sourceCases = {"01"};
    String amount = "100";
    int acquirer_id = 2101;
    String bd_table = "itm22d.vmtconnvl"; //itm22d.vmtconnvl   tb_itm_vmtconnvl

    public void test() throws SQLException {
//        for (String sour: sourceCases){
//            source = sour;
//            System.out.println("Источник - "+source);
            ur_Notpumb();
            //ur_pumb();
            fiz_Notpumb();
            //fiz_pumb();
        //}
    }

    private void positive_all_fields(){
        body = new HashMap<>();
        body.put("senderCardNumber", Cards_data1.getData(senderCard,Card_param.pan));
        body.put("receiverCardNumber", Cards_data1.getData(receiverCard,Card_param.pan));
        body.put("expDate", Cards_data1.getData(receiverCard,Card_param.expire));
        body.put("amount", amount);
        body.put("receiverFirstName","recipientFirstName");
        body.put("receiverLastName","recipientLastName");
        //body.put("receiverAccountNumber","UA193348510000026200111677871");
        body.put("receiverAccountNumberType","recipientAccountNumberType");
        body.put("senderFirstName","senderFirstName");
        body.put("senderLastName","senderLastName");
        body.put("senderAccountNumber","UA193348510000026200111677871");
        //body.put("senderAccountNumberType","senderAccountNumberType");
        body.put("senderReferenceNumber","senderReferenceNumber");
        body.put("senderAddress","senderAddress");
        body.put("senderCity","senderCity");
        body.put("senderCountry","senderCountry");
        body.put("senderPostalCode","senderPostalCode");
        body.put("senderDocument","senderDocument");
        body.put("senderBirthday","01-01-2000");
        body.put("senderIpn","senderIpn");
        body.put("operationId", "119");
        body.put("details.merchantUrl","merchantUrl");
        body.put("details.paymentUrl","paymentUrl");
        body.put("details.additionalMessage","additionalMessage");
        body.put("details.source",source);
        body.put("details.independentSalesOrganizationId","independentSalesOrganizationId");
    }

    private void ur_pumb() throws SQLException {
        System.out.print("Юр - пумб ");
        BDas400.updateSQL("update "+bd_table+" set vcvalue = 'true' where vcownerid = "+acquirer_id+" and vckey = 'IS_PUMB'");
        BDas400.updateSQL("update "+bd_table+" set vcvalue = 'true' where vcownerid = "+acquirer_id+" and vckey = 'IS_FOR_UR'");
        positive_all_fields();
        tran();
    }

    private void fiz_pumb() throws SQLException {
        System.out.print("Физ - пумб ");
        BDas400.updateSQL("update "+bd_table+" set vcvalue = 'true' where vcownerid = "+acquirer_id+" and vckey = 'IS_PUMB'");
        BDas400.updateSQL("update "+bd_table+" set vcvalue = 'false' where vcownerid = "+acquirer_id+" and vckey = 'IS_FOR_UR'");
        positive_all_fields();
        tran();
    }

    private void ur_Notpumb() throws SQLException {
        System.out.print("Юр - партнер ");
        BDas400.updateSQL("update "+bd_table+" set vcvalue = 'false' where vcownerid = "+acquirer_id+" and vckey = 'IS_PUMB'");
        BDas400.updateSQL("update "+bd_table+" set vcvalue = 'true' where vcownerid = "+acquirer_id+" and vckey = 'IS_FOR_UR'");
        positive_all_fields();
        tran();
    }

    private void fiz_Notpumb() throws SQLException {
        System.out.print("Физ - партнер ");
        BDas400.updateSQL("update "+bd_table+" set vcvalue = 'false' where vcownerid = "+acquirer_id+" and vckey = 'IS_PUMB'");
        BDas400.updateSQL("update "+bd_table+" set vcvalue = 'false' where vcownerid = "+acquirer_id+" and vckey = 'IS_FOR_UR'");
        positive_all_fields();
        tran();
    }

    private void tran(){
        Transaction trans = new P4P(body);
        System.out.println(trans.getTransactionId());
    }

    @BeforeTest
    void open(){
        try {
            //BDpostgre.BDpostgre("dc3-bgpg-001-vs.test-fuib.com:5432/","kreedb","svc_kree","passw0rd");
            BDas400.BDas400("ITMTST", Configs1.ITMTST_ALL_NAME, Configs1.ITMTST_ALL_PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    void close(){
        try {
            BDas400.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

// а2с > 30
//body.put("senderAccount","26202111828383");
//        body.put("senderAccountNumber","UA193348510000026200111677871");
//        body.put("amount","3000100");
//        body.put("operationId","1");
//        body.put("customFee","10");
//        body.put("receiverCardNumber", Cards_data.getData(receiverCard, Card_param.pan));
//        body.put("senderFirstName","senderFirstName");
//        body.put("senderLastName","senderLastName");
//        body.put("receiverFirstName","recipientFirstName");
//        body.put("receiverLastName","recipientLastName");
//        body.put("senderBirthday","01-01-2000");
//        body.put("senderAddress","senderAddress");
//        body.put("senderCity","senderCity");
//        body.put("details.source","03");


//body.put("senderCardNumber", Cards_data.getData(senderCard,Card_param.pan));
//        body.put("amount","3000100");
//        body.put("operationId","2");
//        body.put("customFee","0");
//        body.put("receiverAccount","26202111828383");
//        body.put("expDate",Cards_data.getData(senderCard,Card_param.expire));
//        body.put("ip","127.0.0.1");
//        body.put("fingerprint","tests25");
//        body.put("receiverFirstName","recipientFirstName");
//        body.put("receiverLastName","recipientLastName");
//        body.put("receiverAccountNumber","UA193348510000026200111677871");
//        body.put("receiverAccountNumberType","recipientAccountNumberType");
//        body.put("senderFirstName","senderFirstName");
//        body.put("senderLastName","senderLastName");
//        body.put("senderAccountNumber","UA193348510000026200111677871");
//        body.put("senderAccountNumberType","senderAccountNumberType");
//        body.put("senderReferenceNumber","senderReferenceNumber");
//        body.put("senderAddress","senderAddress");
//        body.put("senderCity","senderCity");
//        body.put("senderCountry","senderCountry");
//        body.put("senderPostalCode","senderPostalCode");
//        body.put("senderDocument","senderDocument");
//        body.put("senderDocumentType","senderDocumentType");
//        body.put("senderBirthday","01-01-2000");
//        body.put("senderIpn","senderIpn");
//        body.put("details.merchantUrl","merchantUrl");
//        body.put("details.paymentUrl","paymentUrl");
//        body.put("details.additionalMessage","additionalMessage");
//        body.put("details.source","01");
//        body.put("details.independentSalesOrganizationId","independentSalesOrganizationId");