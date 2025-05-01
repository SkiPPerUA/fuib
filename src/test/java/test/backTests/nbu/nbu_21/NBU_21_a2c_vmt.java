package test.backTests.nbu.nbu_21;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.A2C;
import org.example.qaTransactionTeam.backEnd.utils.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Test
public class NBU_21_a2c_vmt extends BaseTest {

    A2C a2c;
    Map<String, String> body;
    String card = Cards_data.getData(Card.FUIB_MC, Card_param.pan);
    String source = "01";
    String amount = "100";

    public void positive_all_fields(){
        body = new HashMap<>();
        //body.put("senderAccount","26202111828383");
        body.put("amount",amount);
        body.put("operationId","1");
        body.put("customFee","10");
        body.put("receiverCardNumber", card);
        body.put("receiverFirstName","recipientFirstName");
        body.put("receiverLastName","recipientLastName");
        //body.put("receiverAccountNumber","UA193348510000026200111677871");
        body.put("receiverAccountNumberType","recipientAccountNumberType");
        body.put("senderFirstName","senderFirstName");
        body.put("senderLastName","senderLastName");
        body.put("senderAccountNumber","UA193348510000026200111677871");
        body.put("senderAccountNumberType","senderAccountNumberType");
        body.put("senderReferenceNumber","UA213223130000026007233566001");
        body.put("senderAddress","senderAddress");
        body.put("senderCity","senderCity");
        body.put("senderCountry","senderCountry");
        body.put("senderPostalCode","senderPostalCode");
        body.put("senderDocument","senderDocument");
        body.put("senderBirthday","01-01-2000");
        body.put("senderIpn","senderIpn");
        body.put("details.additionalMessage","additionalMessage");
        body.put("details.merchantUrl","merchantUrl");
        body.put("details.paymentUrl","paymentUrl");
        body.put("details.source",source);
        body.put("details.independentSalesOrganizationId","independentSalesOrganizationId");

        makeTran(body);
    }

    public void positive_only_req_fields_master(){
        logStartTest("positive_only_req_fields_master");
        body = new HashMap<>();
        //body.put("senderAccount","UA493220010000026209303616072");
        body.put("amount",amount);
        body.put("operationId","1");
        body.put("customFee","10");
        body.put("receiverCardNumber", Cards_data.getData(Card.FUIB_MC, Card_param.pan));
        body.put("senderFirstName","senderFirstName");
        body.put("senderLastName","senderLastName");
        body.put("receiverFirstName","recipientFirstName");
        body.put("receiverLastName","recipientLastName");
        body.put("senderAddress","senderAddress");
        body.put("senderCity","senderCity");
        body.put("details.source","01");

        makeTran(body);
    }

    public void positive_only_req_fields_visa(){
        logStartTest("positive_only_req_fields_visa");
        body = new HashMap<>();
        body.put("senderAccount","26202111828383");
        body.put("amount",amount);
        body.put("operationId","1");
        body.put("customFee","10");
        body.put("receiverCardNumber", Cards_data.getData(Card.FUIB_VISA, Card_param.pan));
        body.put("senderAccountNumber","senderAccountNumber");
        body.put("details.source","01");

        makeTran(body);
    }

    public void positive_only_req_fields_master_more_30k(){
        logStartTest("positive_only_req_fields_master");
        body = new HashMap<>();
        body.put("senderAccount","26202111828383");
        body.put("senderAccountNumber","UA193348510000026200111677871");
        body.put("amount","3000100");
        body.put("operationId","1");
        body.put("customFee","10");
        body.put("receiverCardNumber", Cards_data.getData(Card.FUIB_MC, Card_param.pan));
        body.put("senderFirstName","senderFirstName");
        body.put("senderLastName","senderLastName");
        body.put("receiverFirstName","recipientFirstName");
        body.put("receiverLastName","recipientLastName");
        body.put("senderBirthday","01-01-2000");
        body.put("senderAddress","senderAddress");
        body.put("senderCity","senderCity");
        body.put("details.source","01");

        makeTran(body);
    }

    public void positive_only_req_fields_visa_more_30k(){
        logStartTest("positive_only_req_fields_visa");
        body = new HashMap<>();
        body.put("senderAccount","26202111828383");
        body.put("amount","3000100");
        body.put("operationId","1");
        body.put("customFee","10");
        body.put("senderFirstName","senderFirstName");
        body.put("senderLastName","senderLastName");
        body.put("receiverFirstName","recipientFirstName");
        body.put("receiverLastName","recipientLastName");
        body.put("senderBirthday","01-01-2000");
        body.put("receiverCardNumber", Cards_data.getData(Card.FUIB_VISA, Card_param.pan));
        body.put("senderAccountNumber","senderAccountNumber");
        //body.put("details.source","01");

        makeTran(body);
    }

    public void positive_only_req_fields_master_cash_less_5000(){
        logStartTest("positive_only_req_fields_master_cash_less_5000");
        body = new HashMap<>();
        body.put("senderAccount","26202111828383");
        body.put("amount","500000");
        body.put("operationId","1");
        body.put("customFee","10");
        body.put("receiverCardNumber", Cards_data.getData(Card.FUIB_MC, Card_param.pan));
        body.put("details.source","02");

        makeTran(body);
    }

    public void positive_only_req_fields_master_cash_more_5000(){
        body = new HashMap<>();
        body.put("senderAccount","26202111828383");
        body.put("amount","500001");
        body.put("operationId","1");
        body.put("customFee","10");
        body.put("receiverCardNumber", Cards_data.getData(Card.FUIB_MC, Card_param.pan));
        body.put("receiverFirstName","recipientFirstName");
        body.put("receiverLastName","recipientLastName");
        body.put("senderFirstName","senderFirstName");
        body.put("senderLastName","senderLastName");
        body.put("senderAddress","senderAddress");
        body.put("senderCity","senderCity");
        body.put("senderCountry","UKR");
        body.put("senderDocument","senderDocument");
        body.put("senderBirthday","01-01-2000");
        body.put("senderIpn","senderIpn");
        body.put("details.source","02");

        makeTran(body);
    }

    public void positive_only_req_fields_visa_cash_less_5000(){
        logStartTest("positive_only_req_fields_visa_cash_less_5000");
        body = new HashMap<>();
        //body.put("senderAccount","26202111828383");
        body.put("amount","100");
        body.put("operationId","1");
        body.put("customFee","10");
        body.put("receiverCardNumber", Cards_data.getData(Card.FUIB_VISA, Card_param.pan));
        //body.put("senderAccountNumber","senderAccountNumber");
        body.put("details.source","02");

        makeTran(body);
    }

    public void positive_only_req_fields_visa_cash_more_5000(){
        logStartTest("positive_only_req_fields_visa_cash_more_5000");
        body = new HashMap<>();
        //body.put("senderAccount","26202111828383");
        body.put("amount","500100");
        body.put("operationId","1");
        body.put("customFee","10");
        body.put("receiverCardNumber", "4761730000000144");
        body.put("receiverFirstName","recipientFirstName");
        body.put("receiverLastName","recipientLastName");
        body.put("senderFirstName","senderFirstName");
        body.put("senderLastName","senderLastName");
        body.put("senderAddress","senderAddress");
        body.put("senderCity","senderCity");
        body.put("senderDocument","senderDocument");
        body.put("senderBirthday","01-01-2000");
        body.put("senderIpn","senderIpn");
        body.put("details.source","02");

        makeTran(body);
    }

    private void makeTran(Map body){
        a2c = new A2C(body);
        try {
            checkData(a2c.getSessionId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    @BeforeTest
    public void open_conn() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        BDas400.BDas400("ITMTST", Configs.ITMTST_ALL_NAME,Configs.ITMTST_ALL_PASSWORD);
    }

    @AfterTest
    public void close_conn() throws SQLException{
        BDas400.closeConn();
    }
}
