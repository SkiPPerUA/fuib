package test.backTests.transferGO;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.transferGO.TransferGO;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class TransferGoTest extends BaseTest {

    TransferGO transferGO = new TransferGO();
    String body;

    public void create_test_positive(){
        transferGO.setStatusCode(200);
        body = "{\n" +
                "  \"transfer_id\": \"8065f36e-f1c6-456e-b1b7-cc66aa0fabd7\",\n" +
                "  \"transfer\": {\n" +
                "    \"amount\": 1000000,\n" +
                "    \"currency\": \"980\"\n" +
                "  },\n" +
                "  \"settlement\": {\n" +
                "    \"currency\": \"840\"\n" +
                "  },\n" +
                "  \"sender_country\": \"616\",\n" +
                "  \"purpose\": \"payment purpose\",\n" +
                "  \"recipient_account\": {\n" +
                "    \"source\": \"IBAN\",\n" +
                "    \"value\": \"UA613348510000026207117593877\"\n" +
                "  },\n" +
                "  \"recipient_data\": {\n" +
                "    \"recipient_first_name\": \"Viktor\",\n" +
                "    \"recipient_last_name\": \"Maliuha\",\n" +
                "    \"recipient_middle_name\": \"\"\n" +
                "  },\n" +
                "  \"sender_data\": {\n" +
                "    \"sender_first_name\": \"Robertson\",\n" +
                "    \"sender_last_name\": \"Daunies\",\n" +
                "    \"sender_middle_name\": \"Aleksandrovich\",\n" +
                "    \"residence_country\": \"804\"\n" +
                "  },\n" +
                "  \"address\": {\n" +
                "    \"country\": \"616\",\n" +
                "    \"locality\": \"Warsaw\",\n" +
                "    \"street\": \"Warsaw\"\n" +
                "  },\n" +
                "  \"documents\": {\n" +
                "    \"series\": \"СВ\",\n" +
                "    \"number\": \"12341244\"\n" +
                "  },\n" +
                "  \"general\": {\n" +
                "    \"birthday\": \"2023-12-01\",\n" +
                "    \"birth_locality\": \"Warsaw\",\n" +
                "    \"birth_country\": \"804\"\n" +
                "  }\n" +
                "}";
        transferGO.create(body);
        Assert.assertEquals(new JSONObject(transferGO.getResponse()).getString("status"),"NEW");
    }

    @Test(priority = 1)
    void create_test_only_required(){
        transferGO.setStatusCode(200);
        body = "{\n" +
                "  \"transfer_id\": \"8065f36e-f1c6-456e-b1b7-cc66aa0fabd7\",\n" +
                "  \"transfer\": {\n" +
                "    \"amount\": 1000000,\n" +
                "    \"currency\": \"980\"\n" +
                "  },\n" +
                "  \"settlement\": {\n" +
                "    \"currency\": \"840\"\n" +
                "  },\n" +
                "  \"sender_country\": \"616\",\n" +
                "  \"recipient_account\": {\n" +
                "    \"source\": \"IBAN\",\n" +
                "    \"value\": \"UA613348510000026207117593877\"\n" +
                "  },\n" +
                "  \"recipient_data\": {\n" +
                "    \"recipient_first_name\": \"Viktor\",\n" +
                "    \"recipient_last_name\": \"Maliuha\"\n" +
                "  },\n" +
                "  \"sender_data\": {\n" +
                "    \"sender_first_name\": \"Robertson\",\n" +
                "    \"sender_last_name\": \"Daunies\",\n" +
                "    \"sender_middle_name\": \"Aleksandrovich\",\n" +
                "    \"residence_country\": \"804\"\n" +
                "  },\n" +
                "  \"address\": {\n" +
                "    \"country\": \"616\",\n" +
                "    \"locality\": \"Warsaw\",\n" +
                "    \"street\": \"Warsaw\"\n" +
                "  },\n" +
                "  \"documents\": {\n" +
                "    \"series\": \"СВ\",\n" +
                "    \"number\": \"12341244\"\n" +
                "  },\n" +
                "  \"general\": {\n" +
                "    \"birthday\": \"2023-12-01\",\n" +
                "    \"birth_locality\": \"Warsaw\",\n" +
                "    \"birth_country\": \"804\"\n" +
                "  }\n" +
                "}";
        transferGO.create(body);
        Assert.assertEquals(new JSONObject(transferGO.getResponse()).getString("status"),"NEW");
    }

    public void create_test_negative_required_fields(){
        transferGO.setStatusCode(400);
        logStartTest("WithOut transfer_id");
        body = "{\n" +
                "  \"transfer\": {\n" +
                "    \"amount\": 1000000,\n" +
                "    \"currency\": \"980\"\n" +
                "  },\n" +
                "  \"settlement\": {\n" +
                "    \"currency\": \"840\"\n" +
                "  },\n" +
                "  \"sender_country\": \"616\",\n" +
                "  \"recipient_account\": {\n" +
                "    \"source\": \"IBAN\",\n" +
                "    \"value\": \"UA613348510000026207117593877\"\n" +
                "  },\n" +
                "  \"sender_data\": {\n" +
                "    \"sender_first_name\": \"Robert\",\n" +
                "    \"sender_last_name\": \"Daunie\",\n" +
                "    \"sender_middle_name\": \"Aleksandrovich\",\n" +
                "    \"residence_country\": \"804\"\n" +
                "  },\n" +
                "  \"address\": {\n" +
                "    \"country\": \"616\",\n" +
                "    \"locality\": \"Warsaw\",\n" +
                "    \"street\": \"Warsaw\"\n" +
                "  }}";
        transferGO.create(body);

        logStartTest("WithOut transfer.amount");
        body = "{\n" +
                "  \"transfer_id\": \"8065f36e-f1c6-456e-b1b7-cc66aa0fabd7\",\n" +
                "  \"transfer\": {\n" +
                "    \"currency\": \"980\"\n" +
                "  },\n" +
                "  \"settlement\": {\n" +
                "    \"currency\": \"840\"\n" +
                "  },\n" +
                "  \"sender_country\": \"616\",\n" +
                "  \"recipient_account\": {\n" +
                "    \"source\": \"IBAN\",\n" +
                "    \"value\": \"UA613348510000026207117593877\"\n" +
                "  },\n" +
                "  \"sender_data\": {\n" +
                "    \"sender_first_name\": \"Robert\",\n" +
                "    \"sender_last_name\": \"Daunie\",\n" +
                "    \"sender_middle_name\": \"Aleksandrovich\",\n" +
                "    \"residence_country\": \"804\"\n" +
                "  },\n" +
                "  \"address\": {\n" +
                "    \"country\": \"616\",\n" +
                "    \"locality\": \"Warsaw\",\n" +
                "    \"street\": \"Warsaw\"\n" +
                "  }}";
        transferGO.create(body);

        logStartTest("WithOut transfer.currency");
        body = "{\n" +
                "  \"transfer_id\": \"8065f36e-f1c6-456e-b1b7-cc66aa0fabd7\",\n" +
                "  \"transfer\": {\n" +
                "    \"amount\": 1000000\n" +
                "  },\n" +
                "  \"settlement\": {\n" +
                "    \"currency\": \"840\"\n" +
                "  },\n" +
                "  \"sender_country\": \"616\",\n" +
                "  \"recipient_account\": {\n" +
                "    \"source\": \"IBAN\",\n" +
                "    \"value\": \"UA613348510000026207117593877\"\n" +
                "  },\n" +
                "  \"sender_data\": {\n" +
                "    \"sender_first_name\": \"Robert\",\n" +
                "    \"sender_last_name\": \"Daunie\",\n" +
                "    \"sender_middle_name\": \"Aleksandrovich\",\n" +
                "    \"residence_country\": \"804\"\n" +
                "  },\n" +
                "  \"address\": {\n" +
                "    \"country\": \"616\",\n" +
                "    \"locality\": \"Warsaw\",\n" +
                "    \"street\": \"Warsaw\"\n" +
                "  }}";
        transferGO.create(body);

        logStartTest("WithOut transfer");
        body = "{\n" +
                "  \"transfer_id\": \"8065f36e-f1c6-456e-b1b7-cc66aa0fabd7\",\n" +
                "  \"settlement\": {\n" +
                "    \"currency\": \"840\"\n" +
                "  },\n" +
                "  \"sender_country\": \"616\",\n" +
                "  \"recipient_account\": {\n" +
                "    \"source\": \"IBAN\",\n" +
                "    \"value\": \"UA613348510000026207117593877\"\n" +
                "  },\n" +
                "  \"sender_data\": {\n" +
                "    \"sender_first_name\": \"Robert\",\n" +
                "    \"sender_last_name\": \"Daunie\",\n" +
                "    \"sender_middle_name\": \"Aleksandrovich\",\n" +
                "    \"residence_country\": \"804\"\n" +
                "  },\n" +
                "  \"address\": {\n" +
                "    \"country\": \"616\",\n" +
                "    \"locality\": \"Warsaw\",\n" +
                "    \"street\": \"Warsaw\"\n" +
                "  }}";
        transferGO.create(body);

        logStartTest("WithOut settlement");
        body = "{\n" +
                "  \"transfer_id\": \"8065f36e-f1c6-456e-b1b7-cc66aa0fabd7\",\n" +
                "  \"transfer\": {\n" +
                "    \"amount\": 1000000,\n" +
                "    \"currency\": \"980\"\n" +
                "  },\n" +
                "  \"sender_country\": \"616\",\n" +
                "  \"recipient_account\": {\n" +
                "    \"source\": \"IBAN\",\n" +
                "    \"value\": \"UA613348510000026207117593877\"\n" +
                "  },\n" +
                "  \"sender_data\": {\n" +
                "    \"sender_first_name\": \"Robert\",\n" +
                "    \"sender_last_name\": \"Daunie\",\n" +
                "    \"sender_middle_name\": \"Aleksandrovich\",\n" +
                "    \"residence_country\": \"804\"\n" +
                "  },\n" +
                "  \"address\": {\n" +
                "    \"country\": \"616\",\n" +
                "    \"locality\": \"Warsaw\",\n" +
                "    \"street\": \"Warsaw\"\n" +
                "  }}";
        transferGO.create(body);

        logStartTest("WithOut settlement.currency");
        body = "{\n" +
                "  \"transfer_id\": \"8065f36e-f1c6-456e-b1b7-cc66aa0fabd7\",\n" +
                "  \"transfer\": {\n" +
                "    \"amount\": 1000000,\n" +
                "    \"currency\": \"980\"\n" +
                "  },\n" +
                "  \"settlement\": {\n" +
                "  },\n" +
                "  \"sender_country\": \"616\",\n" +
                "  \"recipient_account\": {\n" +
                "    \"source\": \"IBAN\",\n" +
                "    \"value\": \"UA613348510000026207117593877\"\n" +
                "  },\n" +
                "  \"sender_data\": {\n" +
                "    \"sender_first_name\": \"Robert\",\n" +
                "    \"sender_last_name\": \"Daunie\",\n" +
                "    \"sender_middle_name\": \"Aleksandrovich\",\n" +
                "    \"residence_country\": \"804\"\n" +
                "  },\n" +
                "  \"address\": {\n" +
                "    \"country\": \"616\",\n" +
                "    \"locality\": \"Warsaw\",\n" +
                "    \"street\": \"Warsaw\"\n" +
                "  }}";
        transferGO.create(body);


        logStartTest("WithOut sender_country");
        body = "{\n" +
                "  \"transfer_id\": \"8065f36e-f1c6-456e-b1b7-cc66aa0fabd7\",\n" +
                "  \"transfer\": {\n" +
                "    \"amount\": 1000000,\n" +
                "    \"currency\": \"980\"\n" +
                "  },\n" +
                "  \"settlement\": {\n" +
                "    \"currency\": \"840\"\n" +
                "  },\n" +
                "  \"recipient_account\": {\n" +
                "    \"source\": \"IBAN\",\n" +
                "    \"value\": \"UA613348510000026207117593877\"\n" +
                "  },\n" +
                "  \"sender_data\": {\n" +
                "    \"sender_first_name\": \"Robert\",\n" +
                "    \"sender_last_name\": \"Daunie\",\n" +
                "    \"sender_middle_name\": \"Aleksandrovich\",\n" +
                "    \"residence_country\": \"804\"\n" +
                "  },\n" +
                "  \"address\": {\n" +
                "    \"country\": \"616\",\n" +
                "    \"locality\": \"Warsaw\",\n" +
                "    \"street\": \"Warsaw\"\n" +
                "  }}";
        transferGO.create(body);

        logStartTest("WithOut recipient_account.source");
        body = "{\n" +
                "  \"transfer_id\": \"8065f36e-f1c6-456e-b1b7-cc66aa0fabd7\",\n" +
                "  \"transfer\": {\n" +
                "    \"amount\": 1000000,\n" +
                "    \"currency\": \"980\"\n" +
                "  },\n" +
                "  \"settlement\": {\n" +
                "    \"currency\": \"840\"\n" +
                "  },\n" +
                "  \"sender_country\": \"616\",\n" +
                "  \"recipient_account\": {\n" +
                "    \"value\": \"UA613348510000026207117593877\"\n" +
                "  },\n" +
                "  \"sender_data\": {\n" +
                "    \"sender_first_name\": \"Robert\",\n" +
                "    \"sender_last_name\": \"Daunie\",\n" +
                "    \"sender_middle_name\": \"Aleksandrovich\",\n" +
                "    \"residence_country\": \"804\"\n" +
                "  },\n" +
                "  \"address\": {\n" +
                "    \"country\": \"616\",\n" +
                "    \"locality\": \"Warsaw\",\n" +
                "    \"street\": \"Warsaw\"\n" +
                "  }}";
        transferGO.create(body);

        logStartTest("WithOut recipient_account.value");
        body = "{\n" +
                "  \"transfer_id\": \"8065f36e-f1c6-456e-b1b7-cc66aa0fabd7\",\n" +
                "  \"transfer\": {\n" +
                "    \"amount\": 1000000,\n" +
                "    \"currency\": \"980\"\n" +
                "  },\n" +
                "  \"settlement\": {\n" +
                "    \"currency\": \"840\"\n" +
                "  },\n" +
                "  \"sender_country\": \"616\",\n" +
                "  \"recipient_account\": {\n" +
                "    \"source\": \"IBAN\"\n" +
                "  },\n" +
                "  \"sender_data\": {\n" +
                "    \"sender_first_name\": \"Robert\",\n" +
                "    \"sender_last_name\": \"Daunie\",\n" +
                "    \"sender_middle_name\": \"Aleksandrovich\",\n" +
                "    \"residence_country\": \"804\"\n" +
                "  },\n" +
                "  \"address\": {\n" +
                "    \"country\": \"616\",\n" +
                "    \"locality\": \"Warsaw\",\n" +
                "    \"street\": \"Warsaw\"\n" +
                "  }}";
        transferGO.create(body);

        logStartTest("WithOut recipient_account");
        body = "{\n" +
                "  \"transfer_id\": \"8065f36e-f1c6-456e-b1b7-cc66aa0fabd7\",\n" +
                "  \"transfer\": {\n" +
                "    \"amount\": 1000000,\n" +
                "    \"currency\": \"980\"\n" +
                "  },\n" +
                "  \"settlement\": {\n" +
                "    \"currency\": \"840\"\n" +
                "  },\n" +
                "  \"sender_country\": \"616\",\n" +
                "  \"sender_data\": {\n" +
                "    \"sender_first_name\": \"Robert\",\n" +
                "    \"sender_last_name\": \"Daunie\",\n" +
                "    \"sender_middle_name\": \"Aleksandrovich\",\n" +
                "    \"residence_country\": \"804\"\n" +
                "  },\n" +
                "  \"address\": {\n" +
                "    \"country\": \"616\",\n" +
                "    \"locality\": \"Warsaw\",\n" +
                "    \"street\": \"Warsaw\"\n" +
                "  }}";
        transferGO.create(body);

        logStartTest("WithOut sender_data.sender_first_name");
        body = "{\n" +
                "  \"transfer_id\": \"8065f36e-f1c6-456e-b1b7-cc66aa0fabd7\",\n" +
                "  \"transfer\": {\n" +
                "    \"amount\": 1000000,\n" +
                "    \"currency\": \"980\"\n" +
                "  },\n" +
                "  \"settlement\": {\n" +
                "    \"currency\": \"840\"\n" +
                "  },\n" +
                "  \"sender_country\": \"616\",\n" +
                "  \"recipient_account\": {\n" +
                "    \"source\": \"IBAN\",\n" +
                "    \"value\": \"UA613348510000026207117593877\"\n" +
                "  },\n" +
                "  \"sender_data\": {\n" +
                "    \"sender_last_name\": \"Daunie\",\n" +
                "    \"sender_middle_name\": \"Aleksandrovich\",\n" +
                "    \"residence_country\": \"804\"\n" +
                "  },\n" +
                "  \"address\": {\n" +
                "    \"country\": \"616\",\n" +
                "    \"locality\": \"Warsaw\",\n" +
                "    \"street\": \"Warsaw\"\n" +
                "  }}";
        transferGO.create(body);

        logStartTest("WithOut sender_data.sender_last_name");
        body = "{\n" +
                "  \"transfer_id\": \"8065f36e-f1c6-456e-b1b7-cc66aa0fabd7\",\n" +
                "  \"transfer\": {\n" +
                "    \"amount\": 1000000,\n" +
                "    \"currency\": \"980\"\n" +
                "  },\n" +
                "  \"settlement\": {\n" +
                "    \"currency\": \"840\"\n" +
                "  },\n" +
                "  \"sender_country\": \"616\",\n" +
                "  \"recipient_account\": {\n" +
                "    \"source\": \"IBAN\",\n" +
                "    \"value\": \"UA613348510000026207117593877\"\n" +
                "  },\n" +
                "  \"sender_data\": {\n" +
                "    \"sender_first_name\": \"Robert\",\n" +
                "    \"sender_middle_name\": \"Aleksandrovich\",\n" +
                "    \"residence_country\": \"804\"\n" +
                "  },\n" +
                "  \"address\": {\n" +
                "    \"country\": \"616\",\n" +
                "    \"locality\": \"Warsaw\",\n" +
                "    \"street\": \"Warsaw\"\n" +
                "  }}";
        transferGO.create(body);

        logStartTest("WithOut sender_data.residence_country");
        body = "{\n" +
                "  \"transfer_id\": \"8065f36e-f1c6-456e-b1b7-cc66aa0fabd7\",\n" +
                "  \"transfer\": {\n" +
                "    \"amount\": 1000000,\n" +
                "    \"currency\": \"980\"\n" +
                "  },\n" +
                "  \"settlement\": {\n" +
                "    \"currency\": \"840\"\n" +
                "  },\n" +
                "  \"sender_country\": \"616\",\n" +
                "  \"recipient_account\": {\n" +
                "    \"source\": \"IBAN\",\n" +
                "    \"value\": \"UA613348510000026207117593877\"\n" +
                "  },\n" +
                "  \"sender_data\": {\n" +
                "    \"sender_first_name\": \"Robert\",\n" +
                "    \"sender_last_name\": \"Daunie\",\n" +
                "    \"sender_middle_name\": \"Aleksandrovich\"\n" +
                "  },\n" +
                "  \"address\": {\n" +
                "    \"country\": \"616\",\n" +
                "    \"locality\": \"Warsaw\",\n" +
                "    \"street\": \"Warsaw\"\n" +
                "  }}";
        transferGO.create(body);

        logStartTest("WithOut sender_data");
        body = "{\n" +
                "  \"transfer_id\": \"8065f36e-f1c6-456e-b1b7-cc66aa0fabd7\",\n" +
                "  \"transfer\": {\n" +
                "    \"amount\": 1000000,\n" +
                "    \"currency\": \"980\"\n" +
                "  },\n" +
                "  \"settlement\": {\n" +
                "    \"currency\": \"840\"\n" +
                "  },\n" +
                "  \"sender_country\": \"616\",\n" +
                "  \"recipient_account\": {\n" +
                "    \"source\": \"IBAN\",\n" +
                "    \"value\": \"UA613348510000026207117593877\"\n" +
                "  },\n" +
                "  \"address\": {\n" +
                "    \"country\": \"616\",\n" +
                "    \"locality\": \"Warsaw\",\n" +
                "    \"street\": \"Warsaw\"\n" +
                "  }}";
        transferGO.create(body);

        logStartTest("WithOut address.country");
        body = "{\n" +
                "  \"transfer_id\": \"8065f36e-f1c6-456e-b1b7-cc66aa0fabd7\",\n" +
                "  \"transfer\": {\n" +
                "    \"amount\": 1000000,\n" +
                "    \"currency\": \"980\"\n" +
                "  },\n" +
                "  \"settlement\": {\n" +
                "    \"currency\": \"840\"\n" +
                "  },\n" +
                "  \"sender_country\": \"616\",\n" +
                "  \"recipient_account\": {\n" +
                "    \"source\": \"IBAN\",\n" +
                "    \"value\": \"UA613348510000026207117593877\"\n" +
                "  },\n" +
                "  \"sender_data\": {\n" +
                "    \"sender_first_name\": \"Robert\",\n" +
                "    \"sender_last_name\": \"Daunie\",\n" +
                "    \"sender_middle_name\": \"Aleksandrovich\",\n" +
                "    \"residence_country\": \"804\"\n" +
                "  },\n" +
                "  \"address\": {\n" +
                "    \"locality\": \"Warsaw\",\n" +
                "    \"street\": \"Warsaw\"\n" +
                "  }}";
        transferGO.create(body);

        logStartTest("WithOut address.locality");
        body = "{\n" +
                "  \"transfer_id\": \"8065f36e-f1c6-456e-b1b7-cc66aa0fabd7\",\n" +
                "  \"transfer\": {\n" +
                "    \"amount\": 1000000,\n" +
                "    \"currency\": \"980\"\n" +
                "  },\n" +
                "  \"settlement\": {\n" +
                "    \"currency\": \"840\"\n" +
                "  },\n" +
                "  \"sender_country\": \"616\",\n" +
                "  \"recipient_account\": {\n" +
                "    \"source\": \"IBAN\",\n" +
                "    \"value\": \"UA613348510000026207117593877\"\n" +
                "  },\n" +
                "  \"sender_data\": {\n" +
                "    \"sender_first_name\": \"Robert\",\n" +
                "    \"sender_last_name\": \"Daunie\",\n" +
                "    \"sender_middle_name\": \"Aleksandrovich\",\n" +
                "    \"residence_country\": \"804\"\n" +
                "  },\n" +
                "  \"address\": {\n" +
                "    \"country\": \"616\",\n" +
                "    \"street\": \"Warsaw\"\n" +
                "  }}";
        transferGO.create(body);

        logStartTest("WithOut address.street");
        body = "{\n" +
                "  \"transfer_id\": \"8065f36e-f1c6-456e-b1b7-cc66aa0fabd7\",\n" +
                "  \"transfer\": {\n" +
                "    \"amount\": 1000000,\n" +
                "    \"currency\": \"980\"\n" +
                "  },\n" +
                "  \"settlement\": {\n" +
                "    \"currency\": \"840\"\n" +
                "  },\n" +
                "  \"sender_country\": \"616\",\n" +
                "  \"recipient_account\": {\n" +
                "    \"source\": \"IBAN\",\n" +
                "    \"value\": \"UA613348510000026207117593877\"\n" +
                "  },\n" +
                "  \"sender_data\": {\n" +
                "    \"sender_first_name\": \"Robert\",\n" +
                "    \"sender_last_name\": \"Daunie\",\n" +
                "    \"sender_middle_name\": \"Aleksandrovich\",\n" +
                "    \"residence_country\": \"804\"\n" +
                "  },\n" +
                "  \"address\": {\n" +
                "    \"country\": \"616\",\n" +
                "    \"locality\": \"Warsaw\"\n" +
                "  }}";
        transferGO.create(body);

        logStartTest("WithOut address");
        body = "{\n" +
                "  \"transfer_id\": \"8065f36e-f1c6-456e-b1b7-cc66aa0fabd7\",\n" +
                "  \"transfer\": {\n" +
                "    \"amount\": 1000000,\n" +
                "    \"currency\": \"980\"\n" +
                "  },\n" +
                "  \"settlement\": {\n" +
                "    \"currency\": \"840\"\n" +
                "  },\n" +
                "  \"sender_country\": \"616\",\n" +
                "  \"recipient_account\": {\n" +
                "    \"source\": \"IBAN\",\n" +
                "    \"value\": \"UA613348510000026207117593877\"\n" +
                "  },\n" +
                "  \"sender_data\": {\n" +
                "    \"sender_first_name\": \"Robert\",\n" +
                "    \"sender_last_name\": \"Daunie\",\n" +
                "    \"sender_middle_name\": \"Aleksandrovich\",\n" +
                "    \"residence_country\": \"804\"\n" +
                "  }}";
        transferGO.create(body);

        logStartTest("WithOut documents.number");
        body = "{\n" +
                "  \"transfer_id\": \"8065f36e-f1c6-456e-b1b7-cc66aa0fabd7\",\n" +
                "  \"transfer\": {\n" +
                "    \"amount\": 1000000,\n" +
                "    \"currency\": \"980\"\n" +
                "  },\n" +
                "  \"settlement\": {\n" +
                "    \"currency\": \"840\"\n" +
                "  },\n" +
                "  \"sender_country\": \"616\",\n" +
                "  \"recipient_account\": {\n" +
                "    \"source\": \"IBAN\",\n" +
                "    \"value\": \"UA613348510000026207117593877\"\n" +
                "  },\n" +
                "  \"sender_data\": {\n" +
                "    \"sender_first_name\": \"Robert\",\n" +
                "    \"sender_last_name\": \"Daunie\",\n" +
                "    \"residence_country\": \"804\"\n" +
                "  },\n" +
                "  \"address\": {\n" +
                "    \"country\": \"616\",\n" +
                "    \"locality\": \"Warsaw\",\n" +
                "    \"street\": \"Warsaw\"\n" +
                "  },\n" +
                "  \"documents\": {\n" +
                "    \"series\": \"СВ\"\n" +
                "  },\n" +
                "  \"general\": {\n" +
                "    \"birthday\": \"2023-12-01\",\n" +
                "    \"birth_locality\": \"Warsaw\",\n" +
                "    \"birth_country\": \"804\"\n" +
                "  }\n" +
                "}\n";
        transferGO.create(body);

        logStartTest("WithOut general.birthday");
        body = "{\n" +
                "  \"transfer_id\": \"8065f36e-f1c6-456e-b1b7-cc66aa0fabd7\",\n" +
                "  \"transfer\": {\n" +
                "    \"amount\": 1000000,\n" +
                "    \"currency\": \"980\"\n" +
                "  },\n" +
                "  \"settlement\": {\n" +
                "    \"currency\": \"840\"\n" +
                "  },\n" +
                "  \"sender_country\": \"616\",\n" +
                "  \"recipient_account\": {\n" +
                "    \"source\": \"IBAN\",\n" +
                "    \"value\": \"UA613348510000026207117593877\"\n" +
                "  },\n" +
                "  \"sender_data\": {\n" +
                "    \"sender_first_name\": \"Robert\",\n" +
                "    \"sender_last_name\": \"Daunie\",\n" +
                "    \"residence_country\": \"804\"\n" +
                "  },\n" +
                "  \"address\": {\n" +
                "    \"country\": \"616\",\n" +
                "    \"locality\": \"Warsaw\",\n" +
                "    \"street\": \"Warsaw\"\n" +
                "  },\n" +
                "  \"documents\": {\n" +
                "    \"series\": \"СВ\",\n" +
                "    \"number\": \"12341244\"\n" +
                "  },\n" +
                "  \"general\": {\n" +
                "    \"birth_locality\": \"Warsaw\",\n" +
                "    \"birth_country\": \"804\"\n" +
                "  }\n" +
                "}\n";
        transferGO.create(body);

        logStartTest("WithOut general.birth_locality");
        body = "{\n" +
                "  \"transfer_id\": \"8065f36e-f1c6-456e-b1b7-cc66aa0fabd7\",\n" +
                "  \"transfer\": {\n" +
                "    \"amount\": 1000000,\n" +
                "    \"currency\": \"980\"\n" +
                "  },\n" +
                "  \"settlement\": {\n" +
                "    \"currency\": \"840\"\n" +
                "  },\n" +
                "  \"sender_country\": \"616\",\n" +
                "  \"recipient_account\": {\n" +
                "    \"source\": \"IBAN\",\n" +
                "    \"value\": \"UA613348510000026207117593877\"\n" +
                "  },\n" +
                "  \"sender_data\": {\n" +
                "    \"sender_first_name\": \"Robert\",\n" +
                "    \"sender_last_name\": \"Daunie\",\n" +
                "    \"residence_country\": \"804\"\n" +
                "  },\n" +
                "  \"address\": {\n" +
                "    \"country\": \"616\",\n" +
                "    \"locality\": \"Warsaw\",\n" +
                "    \"street\": \"Warsaw\"\n" +
                "  },\n" +
                "  \"documents\": {\n" +
                "    \"series\": \"СВ\",\n" +
                "    \"number\": \"12341244\"\n" +
                "  },\n" +
                "  \"general\": {\n" +
                "    \"birthday\": \"2023-12-01\",\n" +
                "    \"birth_country\": \"804\"\n" +
                "  }\n" +
                "}\n";
        transferGO.create(body);

        logStartTest("WithOut general.birth_country");
        body = "{\n" +
                "  \"transfer_id\": \"8065f36e-f1c6-456e-b1b7-cc66aa0fabd7\",\n" +
                "  \"transfer\": {\n" +
                "    \"amount\": 1000000,\n" +
                "    \"currency\": \"980\"\n" +
                "  },\n" +
                "  \"settlement\": {\n" +
                "    \"currency\": \"840\"\n" +
                "  },\n" +
                "  \"sender_country\": \"616\",\n" +
                "  \"recipient_account\": {\n" +
                "    \"source\": \"IBAN\",\n" +
                "    \"value\": \"UA613348510000026207117593877\"\n" +
                "  },\n" +
                "  \"sender_data\": {\n" +
                "    \"sender_first_name\": \"Robert\",\n" +
                "    \"sender_last_name\": \"Daunie\",\n" +
                "    \"residence_country\": \"804\"\n" +
                "  },\n" +
                "  \"address\": {\n" +
                "    \"country\": \"616\",\n" +
                "    \"locality\": \"Warsaw\",\n" +
                "    \"street\": \"Warsaw\"\n" +
                "  },\n" +
                "  \"documents\": {\n" +
                "    \"series\": \"СВ\",\n" +
                "    \"number\": \"12341244\"\n" +
                "  },\n" +
                "  \"general\": {\n" +
                "    \"birthday\": \"2023-12-01\",\n" +
                "    \"birth_locality\": \"Warsaw\"\n" +
                "  }\n" +
                "}\n";
        transferGO.create(body);
    }

    @Test(dependsOnMethods={"create_test_positive"})
    public void confirm_test_positive(){
        transferGO.setStatusCode(200);
        body = "{\n" +
                "  \"transfer_number\": \"1696320086-A1123\",\n" +
                "  \"sender_account\": {\n" +
                "    \"source\": \"IBAN\",\n" +
                "    \"value\": \"UA613348510000026207117593877\"\n" +
                "  }\n" +
                "}";
        transferGO.confirm(body);
    }

    @Test(dependsOnMethods={"create_test_positive"})
    public void confirm_test_negative_required_fields(){
        transferGO.setStatusCode(400);
        logStartTest("WithOut transfer_number");
        body = "{\n" +
                "  \"sender_account\": {\n" +
                "    \"source\": \"IBAN\",\n" +
                "    \"value\": \"UA613348510000026207117593877\"\n" +
                "  }\n" +
                "}";
        transferGO.confirm(body);

        logStartTest("WithOut sender_account");
        body = "{\n" +
                "  \"transfer_number\": \"1696320086-A\"\n" +
                "}";
        transferGO.confirm(body);

        logStartTest("WithOut sender_account.source");
        body = "{\n" +
                "  \"transfer_number\": \"1696320086-A\",\n" +
                "  \"sender_account\": {\n" +
                "    \"value\": \"UA613348510000026207117593877\"\n" +
                "  }\n" +
                "}";
        transferGO.confirm(body);

        logStartTest("WithOut sender_account.value");
        body = "{\n" +
                "  \"transfer_number\": \"1696320086-A\",\n" +
                "  \"sender_account\": {\n" +
                "    \"source\": \"IBAN\"\n" +
                "  }\n" +
                "}";
        transferGO.confirm(body);
    }

    @Test(dependsOnMethods={"create_test_positive"})
    public void getStatus_test(){
        transferGO.transStatus(transferGO.getId());
    }

    public void positive_testBalance(){
        transferGO.setStatusCode(200);
        List<Integer> currency = List.of(840, 980, 985);
        currency.forEach(x -> transferGO.getBalance(x));
    }

    public void negative_testBalance(){
        transferGO.setStatusCode(400);
        transferGO.getBalance(841);
    }
}
