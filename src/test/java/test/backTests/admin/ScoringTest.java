package test.backTests.admin;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.admin.Scoring;
import org.example.qaTransactionTeam.backEnd.utils.BDpostgre;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data1;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

@Test
public class ScoringTest extends BaseTest {

    Scoring scoring = new Scoring();
    String body;

    public void internal_positive_internal_scoring(){
        scoring.setStatusCode(200);
        body = "{\n" +
                "    \"pan\" : \""+ Cards_data1.getData(Card.FUIB_MC, Card_param.pan) +"\",\n" +
                "    \"inn\" : \"3510812694\",\n" +
                "    \"first_name\" : \"Test\",\n" +
                "    \"last_name\" : \"Test\"\n" +
                "}";
        scoring.internalScoring(body);
    }

    public void internal_positive_only_mandatory(){
        scoring.setStatusCode(200);
        body = "{\n" +
                "    \"pan\" : \""+ Cards_data1.getData(Card.FUIB_MC, Card_param.pan) +"\"\n" +
                "}";
        scoring.internalScoring(body);
    }

    public void internal_negative_pan(){
        scoring.setStatusCode(400);
        logStartTest("Without pan");
        body = "{\n" +
                "    \"inn\" : \"3510812694\",\n" +
                "    \"first_name\" : \"Test\",\n" +
                "    \"last_name\" : \"Test\"\n" +
                "}";
        scoring.internalScoring(body);

        logStartTest("Pan - null");
        body = "{\n" +
                "    \"pan\" : \"null\",\n" +
                "    \"inn\" : \"3510812694\",\n" +
                "    \"first_name\" : \"Test\",\n" +
                "    \"last_name\" : \"Test\"\n" +
                "}";
        scoring.internalScoring(body);

        logStartTest("Pan - empty");
        body = "{\n" +
                "    \"pan\" : \"\",\n" +
                "    \"inn\" : \"3510812694\",\n" +
                "    \"first_name\" : \"Test\",\n" +
                "    \"last_name\" : \"Test\"\n" +
                "}";
        scoring.internalScoring(body);

        logStartTest("Pan - default");
        body = "{\n" +
                "    \"pan\" : \"default\",\n" +
                "    \"inn\" : \"3510812694\",\n" +
                "    \"first_name\" : \"Test\",\n" +
                "    \"last_name\" : \"Test\"\n" +
                "}";
        scoring.internalScoring(body);
    }

    public void pan_notLuhn(){
        scoring.setStatusCode(503);
        body = "{\n" +
                "    \"pan\" : \"5218320000173351\",\n" +
                "    \"inn\" : \"3510812694\",\n" +
                "    \"first_name\" : \"Test\",\n" +
                "    \"last_name\" : \"Test\"\n" +
                "}";
        scoring.internalScoring(body);
    }

    public void partner_positive_internal_scoring(){
        scoring.setStatusCode(200);
        body = "{\n" +
                "    \"pan\" : \""+ Cards_data1.getData(Card.FUIB_VISA, Card_param.pan) +"\",\n" +
                "    \"inn\" : \"3510812694\",\n" +
                "    \"first_name\" : \"Testыыы\",\n" +
                "    \"last_name\" : \"Testффф\"\n" +
                "}";
        scoring.partnerScoring(body);
    }

    public void partner_positive_only_mandatory(){
        scoring.setStatusCode(200);
        body = "{\n" +
                "    \"pan\" : \""+ Cards_data1.getData(Card.FUIB_MC, Card_param.pan) +"\"\n" +
                "}";
        scoring.partnerScoring(body);
    }

    public void partner_negative_pan(){
        scoring.setStatusCode(400);
        logStartTest("Without pan");
        body = "{\n" +
                "    \"inn\" : \"3510812694\",\n" +
                "    \"first_name\" : \"Test\",\n" +
                "    \"last_name\" : \"Test\"\n" +
                "}";
        scoring.partnerScoring(body);

        logStartTest("Pan - null");
        body = "{\n" +
                "    \"pan\" : \"null\",\n" +
                "    \"inn\" : \"3510812694\",\n" +
                "    \"first_name\" : \"Test\",\n" +
                "    \"last_name\" : \"Test\"\n" +
                "}";
        scoring.partnerScoring(body);

        logStartTest("Pan - empty");
        body = "{\n" +
                "    \"pan\" : \"\",\n" +
                "    \"inn\" : \"3510812694\",\n" +
                "    \"first_name\" : \"Test\",\n" +
                "    \"last_name\" : \"Test\"\n" +
                "}";
        scoring.partnerScoring(body);

        logStartTest("Pan - default");
        body = "{\n" +
                "    \"pan\" : \"default\",\n" +
                "    \"inn\" : \"3510812694\",\n" +
                "    \"first_name\" : \"Test\",\n" +
                "    \"last_name\" : \"Test\"\n" +
                "}";
        scoring.partnerScoring(body);

        logStartTest("Pan - not luhn");
        body = "{\n" +
                "    \"pan\" : \"5218320000173351\",\n" +
                "    \"inn\" : \"3510812694\",\n" +
                "    \"first_name\" : \"Test\",\n" +
                "    \"last_name\" : \"Test\"\n" +
                "}";
        scoring.partnerScoring(body);
    }

    public void test_count() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        try {
            BDpostgre.BDpostgre("black_noir", "dev","password");
            int count = getCount();
            internal_positive_only_mandatory();
            Assert.assertEquals(getCount(),count, "Количесво записей выросло");
            partner_positive_only_mandatory();
            Assert.assertEquals(getCount(), count+1);
        }finally {
            BDpostgre.closeConn();
        }
    }

    public void test_value(){
        scoring.setStatusCode(200);
        body = "{\n" +
                "    \"pan\" : \"5555555555554444\",\n" +
                "    \"inn\" : \"3047621697\",\n" +
                "    \"first_name\" : \"олексій\",\n" +
                "    \"last_name\" : \"КАЛАШНИКррррВ\"\n" +
                "}";
        scoring.partnerScoring(body);
        String res_partnerScoring = scoring.getResponse();
        scoring.internalScoring(body);
        String res_internalScoring = scoring.getResponse();
        Assert.assertEquals(res_partnerScoring,res_internalScoring);
    }

    int getCount() throws SQLException {
        ResultSet res = BDpostgre.selectSQL("select count(*) from public.scoring_requests");
        res.next();
        return res.getInt(1);
    }
}
