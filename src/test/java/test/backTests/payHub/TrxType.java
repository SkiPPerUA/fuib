package test.backTests.payHub;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transaction.Payer_constructor;
import org.example.qaTransactionTeam.backEnd.transaction.ThreeDS;
import org.example.qaTransactionTeam.backEnd.transaction.Transaction;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.A2C;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.A2C_direct;
import org.example.qaTransactionTeam.backEnd.transaction.typeTrans_payhub.C2A;
import org.example.qaTransactionTeam.backEnd.utils.BDpostgre;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Test
public class TrxType extends BaseTest {

    Transaction transaction;

    public void testPositiveA2C(){
        List<Integer> z270s = List.of(1,4,5,7);
        z270s.forEach(x -> {   String body = "\"amount\": 110, \n" +
                "        \"commission\": 0, \n" +
                "        \"currency\": \"UAH\", \n" +
                "        \"z270\": "+x+", \n" +
                "        \"date\": \"2023-05-09\", \n" +
                "        \"description\": \"description_тест\", \n" +
                "        \"destination\": \"Переказ коштів на картку ***6420\", \n" +
                "        \"payer\": { \n" +
                "            \"source\": \"IBAN\", \n" +
                "            \"value\": \"UA953348510000026201112609803\", \n" +
                "            \"client\": { \n" +
                "                \"source\": \"EKB\", \n" +
                "                \"id\": \"1234567\" \n" +
                "            } \n" +
                "        }, \n" +
                "    \"identification\": {\n" +
                "         \"requirements\":{\n" +
                "             \"recipient\":{\n" +
                "                 \"first_name\":\"IvanRecipient\",\n" +
                "                 \"last_name\":\"IvanovRecipient\"\n" +
                "              },\n" +
                "             \"sender\":{\n" +
                "                 \"first_name\":\"IvanSender\",\n" +
                "                 \"last_name\":\"IvanovSender\",\n" +
                "                 \"account_number\":\"4206520000078197\"\n" +
                "              }\n" +
                "        },"+
                "       \"address\": {" +
                "               \"locality\": \"11111\",\n" +
                "               \"street\": \"22222\"\n" +
                "               }"+
                "    },"+
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan) +"\",\n" +
                "        \"recipientFirstName\":\"Fir's-tNam e ыв\",\n" +
                "        \"recipientLastName\":\"L'as-tNa.me\"\n" +
                "    }";
            transaction = new A2C(body);
            System.out.println(x);
            check(x);
        });
    }

    public void testPositiveC2A(){
        List<Integer> z270s = List.of(1,4,5,7);
        z270s.forEach(x -> {   String body = " \"amount\": 100,\n" +
                "    \"currency\": 980,\n" +
                "    \"commission\": 10,\n" +
                "    \"z270\": "+x+", \n" +
                "    \"description\": \"c2a - пумб мастер\",\n" +
                ThreeDS.threeDS_2_1_0 +","+
                "    \"identification\": {\n" +
                "         \"requirements\":{\n" +
                "             \"recipient\":{\n" +
                "                 \"account_number\":\"UA213223130000026007233566001\"\n" +
                "              }}},\n" +
                Payer_constructor.PAN_payer(Cards_data.getData(Card.FUIB_MC));

            transaction = new C2A(body,0);
            System.out.println(x);
            check(x);
        });
    }

    private ResultSet get_receiver_transactions() throws SQLException {
       return BDpostgre.selectSQL("SELECT * FROM info_registry.receiver_transactions where transaction_id = '"+transaction.getTransactionId()+"'");
    }

    private ResultSet get_requirements_details() throws SQLException {
        return BDpostgre.selectSQL("SELECT * FROM info_registry.requirements_details where receiver_transaction_id = (select id from info_registry.receiver_transactions where transaction_id = '"+transaction.getTransactionId()+"')");
    }

    private void check(int type){
        ResultSet res = null;
        try {
            res = get_receiver_transactions();
            res.next();
            ResultSet res2 = get_requirements_details();
            res2.next();
            Assert.assertEquals(res.getInt("z270"), type);
            Assert.assertEquals(res2.getString("trx_type"), String.valueOf(type));
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
        }

    }

    @BeforeTest
    void open() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        BDpostgre.BDpostgre("temporary_registries", "dev","password");
    }

    @AfterTest
    void close() throws SQLException {
        BDpostgre.closeConn();
    }
}
