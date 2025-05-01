package test.backTests.nbu.nbu_21;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.C2A;
import org.example.qaTransactionTeam.backEnd.utils.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Test
public class NBU_21_c2a_vmt extends BaseTest {

    C2A c2a;
    Map<String, String> body;
    String card = Cards_data.getData(Card.MONO_VISA,Card_param.pan); // 5575191548185686 4596610012333736

    public void positive_all_fields(){
        body = new HashMap<>();
        body.put("senderCardNumber", card);
        body.put("amount","100");
        body.put("operationId","2");
        body.put("customFee","0");
        //body.put("receiverAccount","26202111828383");
        body.put("expDate","2602");
        body.put("ip","127.0.0.1");
        body.put("fingerprint","tests25");
        body.put("receiverFirstName","recipientFirstName");
        body.put("receiverLastName","recipientLastName");
        body.put("receiverAccountNumber","UA193348510000026200111677871");
        body.put("receiverAccountNumberType","recipientAccountNumberType");
        body.put("senderFirstName","senderFirstName");
        body.put("senderLastName","senderLastName");
        body.put("senderAccountNumber","UA193348510000026200111677872");
        body.put("senderAccountNumberType","senderAccountNumberType");
        body.put("senderReferenceNumber","senderReferenceNumber");
        body.put("senderAddress","senderAddress");
        body.put("senderCity","senderCity");
        body.put("senderCountry","senderCountry");
        body.put("senderPostalCode","senderPostalCode");
        body.put("senderDocument","senderDocument");
        body.put("senderDocumentType","senderDocumentType");
        body.put("senderBirthday","01-01-2000");
        body.put("senderIpn","senderIpn");
        body.put("details.merchantUrl","merchantUrl");
        body.put("details.paymentUrl","paymentUrl");
        body.put("details.additionalMessage","additionalMessage");
        body.put("details.source","01");
        body.put("details.independentSalesOrganizationId","independentSalesOrganizationId");

        for (int i = 0; i < 1; i++){
            makeTran(body);
        }
    }

    public void positive_only_req_fields_master_less_30K(){
        logStartTest("positive_only_req_fields_master");
        body = new HashMap<>();
        body.put("senderCardNumber", Cards_data.getData(Card.FUIB_MC, Card_param.pan));
        body.put("amount","100");
        body.put("operationId","2");
        body.put("customFee","0");
        body.put("receiverAccount","26202111828383");
        body.put("expDate","2602");
        body.put("receiverFirstName","recipientFirstName");
        body.put("receiverLastName","recipientLastName");
        body.put("receiverAccountNumber","recipientAccountNumber");

        makeTran(body);
    }

    public void positive_only_req_fields_visa_less_30K(){
        logStartTest("positive_only_req_fields_visa");
        body = new HashMap<>();
        body.put("senderCardNumber", Cards_data.getData(Card.FUIB_VISA, Card_param.pan));
        body.put("amount","10012");
        body.put("operationId","2");
        body.put("customFee","0");
        body.put("receiverAccount","26202111828383");
        body.put("expDate","2602");
        body.put("receiverAccountNumber","recipientAccountNumber");

        makeTran(body);
    }

    public void positive_only_req_fields_master_more_30K(){
        logStartTest("positive_only_req_fields_master");
        body = new HashMap<>();
        body.put("senderCardNumber", Cards_data.getData(Card.FUIB_MC, Card_param.pan));
        body.put("amount","100");
        body.put("operationId","2");
        body.put("customFee","0");
        body.put("senderFirstName","senderFirstName");
        body.put("senderLastName","senderLastName");
        body.put("receiverAccount","26202111828383");
        body.put("expDate",Cards_data.getData(Card.FUIB_MC, Card_param.expire));
        body.put("senderBirthday","01-01-2000");
        body.put("details.source","01");
        body.put("receiverFirstName","recipientFirstName");
        body.put("receiverLastName","recipientLastName");
        body.put("receiverAccountNumber","recipientAccountNumber");

        makeTran(body);
    }

    public void positive_only_req_fields_visa_more_30K(){
        logStartTest("positive_only_req_fields_visa");
        body = new HashMap<>();
        body.put("senderCardNumber", Cards_data.getData(Card.FUIB_VISA, Card_param.pan));
        body.put("amount","100");
        body.put("operationId","2");
        body.put("customFee","0");
        body.put("senderFirstName","senderFirstName");
        body.put("senderLastName","senderLastName");
        body.put("receiverAccount","26202111828383");
        body.put("expDate",Cards_data.getData(Card.FUIB_VISA, Card_param.expire));
        body.put("senderBirthday","01-01-2000");
        //body.put("details.source","01");
        body.put("receiverFirstName","recipientFirstName");
        body.put("receiverLastName","recipientLastName");
        body.put("receiverAccountNumber","recipientAccountNumber");

        makeTran(body);
    }

    private void makeTran(Map body){
        c2a = new C2A(body);
//        try {
//            checkData(c2a.getSessionId());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    private void checkData(String rrf) throws SQLException {
        ResultSet res = BDas400.selectSQL("SELECT\n" +
                "    VALUE \n" +
                "FROM\n" +
                "    ITM22D . VMT2ITRSTB\n" +
                "WHERE\n" +
                "    REFERENCE = '"+rrf+"'");

        res.next();
        String data = res.getString(1);
        System.out.println("Recipient First Name -                {"+data.substring(0,35)+"}");
        System.out.println("Recipient Last Name -                 {"+data.substring(35,70)+"}");
        System.out.println("Recipient Account Number -            {"+data.substring(70,104)+"}");
        System.out.println("Recipient Account Number Type -       {"+data.substring(104,106)+"}");
        System.out.println("Sender First Name -                   {"+data.substring(106,141)+"}");
        System.out.println("Sender Last Name -                    {"+data.substring(141,176)+"}");
        System.out.println("Sender Account Number -               {"+data.substring(176,210)+"}");
        System.out.println("Sender Account Number Type -          {"+data.substring(210,212)+"}");
        System.out.println("Source of Funds -                     {"+data.substring(212,214)+"}");
        System.out.println("Funding Source -                      {"+data.substring(214,216)+"}");
        System.out.println("Sender Address -                      {"+data.substring(216,266)+"}");
        System.out.println("Sender City -                         {"+data.substring(266,291)+"}");
        System.out.println("Sender Country -                      {"+data.substring(291,294)+"}");
        System.out.println("Sender Postal Code -                  {"+data.substring(294,310)+"}");
        System.out.println("Sender Document -                     {"+data.substring(310,319)+"}");
        System.out.println("Sender Document Type -                {"+data.substring(319,321)+"}");
        System.out.println("Sender Birthday -                     {"+data.substring(321,329)+"}");
        System.out.println("Sender IPN -                          {"+data.substring(329,339)+"}");
        System.out.println("Independent Sales Organization Id -   {"+data.substring(339,347)+"}");
        System.out.println("Sender Reference Number -             {"+data.substring(347,363)+"}");
        System.out.println("All end -                             {"+data.substring(363, data.toCharArray().length)+"}");
    }

//    @BeforeTest
//    public void open_conn() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
//        BDas400.BDas400("ITMTST", Configs.ITMTST_ALL_NAME,Configs.ITMTST_ALL_PASSWORD);
//    }
//
//    @AfterTest
//    public void close_conn() throws SQLException{
//        BDas400.closeConn();
//    }
}
