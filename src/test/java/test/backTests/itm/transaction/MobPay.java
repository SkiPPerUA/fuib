package test.backTests.itm.transaction;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transaction.ThreeDS;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_itm.MobilePay;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.testng.annotations.Test;

@Test
public class MobPay extends BaseTest {

    public final Card card = Card.MONO_MC;
    private String body;
    public Transaction transaction;

    public void apple_pay_without_threeDS(){
        body = "{\n" +
                "\t\"amount\":\"101\",\n" +
                "\t\"card_number\":\""+ Cards_data.getData(card, Card_param.pan) +"\",\n" +
                "\t\"experation_date\":\""+ Cards_data.getData(card, Card_param.expire)+"\",\n" +
                "\t\"cvv\": \""+ Cards_data.getData(card, Card_param.cvv)+"\", \n" +
                "\t\"ucaf\":\"AAABA5RZlAAAA5cZGFmUAAAAAAA=\",\n" +
                "\t\"ext_trans_id\": \"12wq3e50\",\n" +
                ThreeDS.without_threeDS_2_1_0_itm+"}";

        transaction = new MobilePay(body,0);
    }

    public void apple_pay_with_threeDS(){
        body = "{\n" +
                "\t\"amount\":\"101\",\n" +
                "\t\"card_number\":\""+ Cards_data.getData(card, Card_param.pan) +"\",\n" +
                "\t\"experation_date\":\""+ Cards_data.getData(card, Card_param.expire)+"\",\n" +
                "\t\"cvv\": \""+ Cards_data.getData(card, Card_param.cvv)+"\", \n" +
                "\t\"ucaf\":\"AAABA5RZlAAAA5cZGFmUAAAAAAA=\",\n" +
                "\t\"ext_trans_id\": \"12wq3e50\",\n" +
                ThreeDS.with_threeDS_2_1_0_itm+"}";

        transaction = new MobilePay(body,2);
    }

    public void apple_pay_with_threeDS_and_additional_data(){
        body = "{\n" +
                "\t\"amount\":\"101\",\n" +
                "\t\"card_number\":\""+ Cards_data.getData(card, Card_param.pan) +"\",\n" +
                "\t\"experation_date\":\""+ Cards_data.getData(card, Card_param.expire)+"\",\n" +
                "\t\"cvv\": \""+ Cards_data.getData(card, Card_param.cvv)+"\", \n" +
                "\t\"ucaf\":\"AAABA5RZlAAAA5cZGFmUAAAAAAA=\",\n" +
                "\t\"ext_trans_id\": \"12wq3e50\",\n" +
                ThreeDS.with_threeDS_2_1_0_itm+","+
                "\"additional_data\":{\n" +
                "  \"recipients\": [{     \n" +
                "    \"first_name\": \"Ivan\",\n" +
                "    \"last_name\": \"Ivanov\",\n" +
                "    \"amount\": 100,\n" +
                "    \"account_number\": \"UA213223130000026007233566001\",\n" +
                "    \"independent_sales_organization_id\": \"3056715233\",\n" +
                "    \"merchant_url\": \"https://merchant111.com?com=12345\",\n" +
                "    \"payment_url\": \"https://payment111.com?com=12345\"\n" +
                "  }, {\n" +
                "    \"first_name\": \"Ivan\",\n" +
                "    \"last_name\": \"Petrov\",\n" +
                "    \"amount\": 200,\n" +
                "    \"account_number\": \"UA213223130000026007233566001\",\n" +
                "    \"independent_sales_organization_id\": \"3056715233\",\n" +
                "    \"merchant_url\": \"https://merchant111.com?com=12345\",\n" +
                "    \"payment_url\": \"https://payment111.com?com=12345\"\n" +
                "  }],\n" +
                "  \"sender\": {\n" +
                "    \"first_name\": \"Ivan\",  \n" +
                "    \"last_name\": \"Ivanov\",\n" +
                "    \"account_number\": \"UA213223130000026007233566001\"  \n" +
                "  },\n" +
                "  \"details\": {\n" +
                "    \"additional_message\": \"Lorem ipsum dolor sit amet\",\n" +
                "    \"source\": \"01\",\n" +
                "    \"submerchant_url\": \"https://some1.url.com\"\n" +
                "  }\n" +
                "}}";
        transaction = new MobilePay(body,2);
    }
}
