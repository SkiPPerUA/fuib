package test.backTests.temporaryRegistries;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transaction.ThreeDS;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.MobilePay;
import org.example.qaTransactionTeam.backEnd.utils.BDpostgre;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data1;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Test
public class TemporaryRegistriesTest extends BaseTest {

    Card card = Card.FUIB_MC;

    public void mobilePay_itm() throws SQLException {
        body = "{\n" +
                "\t\"amount\":\"101\",\n" +
                "\t\"card_number\":\""+ Cards_data1.getData(card, Card_param.pan) +"\",\n" +
                "\t\"experation_date\":\""+ Cards_data1.getData(card, Card_param.expire)+"\",\n" +
                "\t\"cvv\": \""+ Cards_data1.getData(card, Card_param.cvv)+"\", \n" +
                "\t\"ucaf\":\"AAABA5RZlAAAA5cZGFmUAAAAAAA=\",\n" +
                "\t\"ext_trans_id\": \"12wq3e50\",\n" +
                ThreeDS.with_threeDS_2_1_0_itm + ",\n" +
                "\"additional_data\":{\n" +
                "  \"recipients\": [{\n" +
                "    \"first_name\": \""+test_data.get("recipients.first_name")+"\",\n" +
                "    \"last_name\": \""+test_data.get("recipients.last_name")+"\",\n" +
                "    \"amount\": "+test_data.get("recipients.amount")+",\n" +
                "    \"account_number\": \""+test_data.get("recipients.account_number")+"\",\n" +
                "    \"independent_sales_organization_id\": \""+test_data.get("recipients.independent_sales_organization_id")+"\",\n" +
                "    \"merchant_url\": \""+test_data.get("recipients.merchant_url")+"\",\n" +
                "    \"payment_url\": \""+test_data.get("recipients.payment_url")+"\"\n" +
                "  }],\n" +
                "  \"sender\": {\n" +
                "    \"first_name\": \""+test_data.get("sender.first_name")+"\",  \n" +
                "    \"last_name\": \""+test_data.get("sender.last_name")+"\",\n" +
                "    \"account_number\": \""+test_data.get("sender.account_number")+"\"  \n" +
                "  },\n" +
                "  \"details\": {\n" +
                "    \"additional_message\": \""+test_data.get("details.additional_message")+"\",\n" +
                "    \"source\": \""+test_data.get("details.source")+"\",\n" +
                "    \"sub_merchant_url\": \""+test_data.get("details.submerchant_url")+"\"\n" +
                "  }\n" +
                "}}";
        transaction = new MobilePay(body,2);
        resultSet = selectSQL("SELECT x.* FROM info_registry.receiver_transactions x where x.session_id = '"+transaction.getTransactionId()+"'");
        Assert.assertEquals(resultSet.getString("type"),"PAY");
        Assert.assertEquals(resultSet.getString("source"), "AGPAY");
        resultSet = selectSQL("SELECT x.* FROM info_registry.requirements_details x where receiver_transaction_id = (SELECT x.id FROM info_registry.receiver_transactions x where x.session_id = '"+transaction.getTransactionId()+"')");
        Assert.assertEquals(resultSet.getString("additional_message"),test_data.get("details.additional_message"));
        Assert.assertEquals(resultSet.getString("source"), test_data.get("details.source"));
        resultSet = selectSQL("SELECT x.* FROM info_registry.requirements_recipient_extra x where receiver_transaction_id = (SELECT x.id FROM info_registry.receiver_transactions x where x.session_id = '"+transaction.getTransactionId()+"')");
        Assert.assertEquals(resultSet.getString("recipient_first_name"),test_data.get("recipients.first_name"));
        Assert.assertEquals(resultSet.getString("recipient_last_name"), test_data.get("recipients.last_name"));
        Assert.assertEquals(resultSet.getString("recipient_account_number"),test_data.get("recipients.account_number"));
        Assert.assertEquals(resultSet.getString("recipient_amount"), test_data.get("recipients.amount"));
        Assert.assertEquals(resultSet.getString("independent_sales_organization_id"),test_data.get("recipients.independent_sales_organization_id"));
        Assert.assertEquals(resultSet.getString("merchant_url"), test_data.get("recipients.merchant_url"));
        Assert.assertEquals(resultSet.getString("payment_url"), test_data.get("recipients.payment_url"));
        resultSet = selectSQL("SELECT x.* FROM info_registry.requirements_sender x where receiver_transaction_id = (SELECT x.id FROM info_registry.receiver_transactions x where x.session_id = '"+transaction.getTransactionId()+"')");
        Assert.assertEquals(resultSet.getString("sender_first_name"),test_data.get("sender.first_name"));
        Assert.assertEquals(resultSet.getString("sender_last_name"), test_data.get("sender.last_name"));
        Assert.assertEquals(resultSet.getString("sender_account_number"),test_data.get("sender.account_number"));
    }

    Transaction transaction;
    String body;
    ResultSet resultSet;
    Map<String, String> test_data;

    @BeforeTest
    void openCon(){
        try {
            BDpostgre.BDpostgre("temporary_registries", "dev","password");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        test_data = new HashMap();
        test_data.put("recipients.first_name","Ivan");
        test_data.put("recipients.last_name","Petrov");
        test_data.put("recipients.amount","200");
        test_data.put("recipients.account_number","UA213223130000026007233566001");
        test_data.put("recipients.independent_sales_organization_id","3056715233");
        test_data.put("recipients.merchant_url","https://merchant111.com?com=12345");
        test_data.put("recipients.payment_url","https://payment111.com?com=12345");
        test_data.put("sender.first_name","Petro");
        test_data.put("sender.last_name","Ivanov");
        test_data.put("sender.account_number","UA213223130000026007233566002");
        test_data.put("details.additional_message","Lorem ipsum dolor sit amet");
        test_data.put("details.source","01");
        test_data.put("details.submerchant_url","https://some.url.com");
    }

    @AfterTest
    void closeCon(){
        try {
            BDpostgre.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ResultSet selectSQL(String sql){
        ResultSet res = null;
        try {
            res = BDpostgre.selectSQL(sql);
            res.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
