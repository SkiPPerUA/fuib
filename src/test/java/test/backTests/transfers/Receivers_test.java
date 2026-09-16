package test.backTests.transfers;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transfers.Receivers;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class Receivers_test extends BaseTest {

    Receivers receivers = new Receivers();

    public void positive_PAN_fiz(){
        receivers.receivers("{\n" +
                "    \"recipient\":\n" +
                "        {\n" +
                "            \"source\": \"PAN\",\n" +
                "            \"value\": \""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan) +"\"\n" +
                "        }\n" +
                "}");

        Assert.assertTrue(receivers.getResponse().contains("\"full_name\":\"SAVCHUK VLADYSLAV IHOROVYCH\""));
        Assert.assertTrue(receivers.getResponse().contains("\"full_name_ua\":\"Савчук Владислав Ігорович\""));
        Assert.assertTrue(receivers.getResponse().contains("\"iban\":\"UA953348510000026201112609803\""));
        Assert.assertTrue(receivers.getResponse().contains("\"currency\":\"UAH\""));
        Assert.assertTrue(receivers.getResponse().contains("\"receiver_residence\":\"UA\""));
        Assert.assertTrue(receivers.getResponse().contains("\"type_client\":1"));
        Assert.assertTrue(receivers.getResponse().contains("\"ipn\":\"3462406450\"}"));
    }

    public void positive_IBAN_fiz(){
        receivers.receivers("{\n" +
                "    \"recipient\":\n" +
                "        {\n" +
                "            \"source\": \"IBAN\",\n" +
                "            \"value\": \"UA953348510000026201112609803\"\n" +
                "        }\n" +
                "}");

        Assert.assertTrue(receivers.getResponse().contains("\"full_name\":\"SAVCHUK VLADYSLAV IHOROVYCH\""));
        Assert.assertTrue(receivers.getResponse().contains("\"full_name_ua\":\"Савчук Владислав Ігорович\""));
        Assert.assertTrue(receivers.getResponse().contains("\"iban\":\"UA953348510000026201112609803\""));
        Assert.assertTrue(receivers.getResponse().contains("\"currency\":\"UAH\""));
        Assert.assertTrue(receivers.getResponse().contains("\"receiver_residence\":\"UA\""));
        Assert.assertTrue(receivers.getResponse().contains("\"type_client\":1"));
        Assert.assertTrue(receivers.getResponse().contains("\"ipn\":\"3462406450\"}"));
    }

    public void positive_PAN_jur(){
        receivers.receivers("{\n" +
                "    \"recipient\":\n" +
                "        {\n" +
                "            \"source\": \"PAN\",\n" +
                "            \"value\": \"4874240100036877\"\n" +
                "        }\n" +
                "}");

        Assert.assertTrue(receivers.getResponse().contains("\"full_name\":\"KOTOVYCH YEVHENII VIACHESLAVOVYCH\""));
        Assert.assertTrue(receivers.getResponse().contains("\"full_name_ua\":\"Котович Євгеній В'ячеславович\""));
        Assert.assertTrue(receivers.getResponse().contains("\"iban\":\"UA803348510000000026004333410\""));
        Assert.assertTrue(receivers.getResponse().contains("\"currency\":\"UAH\""));
        Assert.assertTrue(receivers.getResponse().contains("\"receiver_residence\":\"UA\""));
        Assert.assertTrue(receivers.getResponse().contains("\"type_client\":2"));
        Assert.assertTrue(receivers.getResponse().contains("\"ipn\":\"2867523539\"}"));
    }

    public void positive_IBAN_jur(){
        receivers.receivers("{\n" +
                "    \"recipient\":\n" +
                "        {\n" +
                "            \"source\": \"IBAN\",\n" +
                "            \"value\": \"UA783348510000000002063956185\"\n" +
                "        }\n" +
                "}");
        Assert.assertTrue(receivers.getResponse().contains("\"full_name\":\"PRYKHODKO DMYTRO IHOROVYCH\""));
        Assert.assertTrue(receivers.getResponse().contains("\"full_name_ua\":\"Приходько Дмитро Ігорович\""));
        Assert.assertTrue(receivers.getResponse().contains("\"iban\":\"UA783348510000000002063956185\""));
        Assert.assertTrue(receivers.getResponse().contains("\"currency\":\"UAH\""));
        Assert.assertTrue(receivers.getResponse().contains("\"receiver_residence\":\"UA\""));
        Assert.assertTrue(receivers.getResponse().contains("\"type_client\":2"));
        Assert.assertTrue(receivers.getResponse().contains("\"ipn\":\"3391508037\"}"));
    }
}
