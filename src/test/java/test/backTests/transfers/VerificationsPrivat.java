package test.backTests.transfers;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transfers.Verifications;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class VerificationsPrivat extends BaseTest {

    Verifications verifications = new Verifications();

    public void as(){
        verifications.setExpectedResponseCode(200);
        verifications.our_verif("{\n" +
                " \"amount\" : 100,\n" +
                " \"currency\" : \"980\",\n" +
                " \"receiver\" : {\n" +
                " \"source\" : \"IBAN\",\n" +
                " \"value\" : \"UA823348510000026207116857172\"\n" +
                " }\n" +
                "}");
    }

    public void positive_test(){
        verifications.setExpectedResponseCode(200);
        verifications.our_verif("{\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA413348510000026205118023821\"\n" +
                "    },\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"amount\": 10000\n" +
                "}");
        Assert.assertEquals(verifications.getResponse(),"{\"status\":1,\"data\":{\"full_name\":\"Matsko Dmytro\",\"full_name_ua\":\"Мацько Дмитро\",\"client_type\":\"FIZ\",\"is_resident\":true,\"residency_country\":\"804\",\"ipn\":\"3156506750\",\"commission\":0}}");

        verifications.our_verif("{\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA413348510000026205118023821\"\n" +
                "    },\n" +
                "    \"currency\": \"USD\",\n" +
                "    \"amount\": 10000\n" +
                "}");
        Assert.assertEquals(verifications.getResponse(),"{\"status\":1,\"data\":{\"full_name\":\"Matsko Dmytro\",\"full_name_ua\":\"Мацько Дмитро\",\"client_type\":\"FIZ\",\"is_resident\":true,\"residency_country\":\"804\",\"ipn\":\"3156506750\",\"commission\":0}}");

        verifications.our_verif("{\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA413348510000026205118023821\"\n" +
                "    },\n" +
                "    \"currency\": \"RUB\",\n" +
                "    \"amount\": 1\n" +
                "}");
        Assert.assertEquals(verifications.getResponse(),"{\"status\":1,\"data\":{\"full_name\":\"Matsko Dmytro\",\"full_name_ua\":\"Мацько Дмитро\",\"client_type\":\"FIZ\",\"is_resident\":true,\"residency_country\":\"804\",\"ipn\":\"3156506750\",\"commission\":0}}");
    }

    public void mandatory_fields(){
        verifications.setExpectedResponseCode(400);

        //without iban
        verifications.our_verif("{\n" +
                "    \"receiver\": {\n" +
                        "        \"source\": \"IBAN\"\n" +
                        "    },\n" +
                        "    \"currency\": \"UAH\",\n" +
                        "    \"amount\": 10000\n" +
                        "}");

        //without currency
        verifications.our_verif("{\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA413348510000026205118023821\"\n" +
                "    },\n" +
                "    \"amount\": 10000\n" +
                "}");

        //without amount
        verifications.our_verif("{\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA413348510000026205118023821\"\n" +
                "    },\n" +
                "    \"currency\": \"UAH\"\n" +
                "}");

        //without source
        verifications.our_verif("{\n" +
                "    \"receiver\": {\n" +
                "        \"value\": \"UA413348510000026205118023821\"\n" +
                "    },\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"amount\": 10000\n" +
                "}");

        //without receiver
        verifications.our_verif("{\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"amount\": 10000\n" +
                "}");
        verifications.setExpectedResponseCode(200);
    }

    public void negative_test_IBAN(){
        verifications.setExpectedResponseCode(404);

        //not found
        verifications.our_verif("{\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA413348510000026205\"\n" +
                "    },\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"amount\": 10000\n" +
                "}");

        //empty
        verifications.our_verif("{\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"\"\n" +
                "    },\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"amount\": 10000\n" +
                "}");

        //null - str
        verifications.our_verif("{\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"null\"\n" +
                "    },\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"amount\": 10000\n" +
                "}");

        //null
        verifications.our_verif("{\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": null\n" +
                "    },\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"amount\": 10000\n" +
                "}");
        verifications.setExpectedResponseCode(200);
    }

    public void negative_test_source(){
        verifications.setExpectedResponseCode(500);

        //pan
        verifications.our_verif("{\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"PAN\",\n" +
                "        \"value\": \"UA413348510000026205118023821\"\n" +
                "    },\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"amount\": 10000\n" +
                "}");
        verifications.setExpectedResponseCode(200);
    }

    public void negative_test_currency(){
        verifications.setExpectedResponseCode(200);
        verifications.our_verif("{\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA413348510000026205118023821\"\n" +
                "    },\n" +
                "    \"currency\": \"FFF\",\n" +
                "    \"amount\": 10000\n" +
                "}");
    }

    public void notFiz_test() {
        verifications.setExpectedResponseCode(404);
        verifications.our_verif("{\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA493348510000000026009230916\"\n" +
                "    },\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"amount\": 10000\n" +
                "}");
        Assert.assertTrue(verifications.getResponse().contains("cannot found card owner"));
    }

    public void negative_test_amount(){
        verifications.setExpectedResponseCode(400);

        //minus
        verifications.our_verif("{\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA413348510000026205118023821\"\n" +
                "    },\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"amount\": -10000\n" +
                "}");

        //0
        verifications.our_verif("{\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA413348510000026205118023821\"\n" +
                "    },\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"amount\": 0\n" +
                "}");

        //float
        verifications.our_verif("{\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA413348510000026205118023821\"\n" +
                "    },\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"amount\": 10.04\n" +
                "}");

        //null
        verifications.our_verif("{\n" +
                "    \"receiver\": {\n" +
                "        \"source\": \"IBAN\",\n" +
                "        \"value\": \"UA413348510000026205118023821\"\n" +
                "    },\n" +
                "    \"currency\": \"UAH\",\n" +
                "    \"amount\": null\n" +
                "}");
        verifications.setExpectedResponseCode(200);
    }

}
