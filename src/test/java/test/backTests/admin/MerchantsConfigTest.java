package test.backTests.admin;

import com.github.jknack.handlebars.Helper;
import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.admin.MerchantsConfigs;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class MerchantsConfigTest extends BaseTest {

    MerchantsConfigs configs = new MerchantsConfigs();

    public void positive_getConfigs(){
        configs.getConfigs("80bbbe8b-c207-428b-98d4-654bb3b8e606");
        Assert.assertTrue(configs.getResponse().contains("\"name\":\"https://www.pbl_test.com/\""));
    }

    public void positive_getConfigsTrans(){
        configs.getConfigsTransactions("80bbbe8b-c207-428b-98d4-654bb3b8e606", "A2C");
        Assert.assertTrue(configs.getResponse().contains("\"vmt_token_status\":\"ACTIVE\""));
    }

    public void positive_updateConfigsTrans(){
        configs.setExpectedResponseCode(204);
        configs.updateConfigsTransactions("80bbbe8b-c207-428b-98d4-654bb3b8e606","{\n" +
                "    \"vmt_token_status\": \"DISABLED\",\n" +
                "    \"transaction_type\": \"A2C\"\n" +
                "}");
        configs.updateConfigsTransactions("80bbbe8b-c207-428b-98d4-654bb3b8e606","{\n" +
                "    \"vmt_token_status\": \"DELETED\",\n" +
                "    \"transaction_type\": \"A2C\"\n" +
                "}");
        configs.updateConfigsTransactions("80bbbe8b-c207-428b-98d4-654bb3b8e606","{\n" +
                "    \"vmt_token_status\": \"ACTIVE\",\n" +
                "    \"transaction_type\": \"A2C\"\n" +
                "}");
        configs.setExpectedResponseCode(200);
    }

    public void negative_updateConfigsTrans(){
        configs.setExpectedResponseCode(400);
        configs.updateConfigsTransactions("80bbbe8b-c207-428b-98d4-654bb3b8e606","{\n" +
                "    \"vmt_token_status\": \"DISABLED1\",\n" +
                "    \"transaction_type\": \"A2C\"\n" +
                "}");
        configs.updateConfigsTransactions("80bbbe8b-c207-428b-98d4-654bb3b8e607","{\n" +
                "    \"vmt_token_status\": \"ACTIVE\",\n" +
                "    \"transaction_type\": \"A2C\"\n" +
                "}");
        configs.updateConfigsTransactions("80bbbe8b-c207-428b-98d4-654bb3","{\n" +
                "    \"vmt_token_status\": \"ACTIVE\",\n" +
                "    \"transaction_type\": \"A2C\"\n" +
                "}");
        configs.setExpectedResponseCode(200);
    }

    public void negative_getConfigsTrans(){
        configs.setExpectedResponseCode(404);
        configs.getConfigsTransactions("80bbbe8b-c207-428b-98d4-654bb3b8e606", "C2C");
        configs.getConfigsTransactions("80bbbe8b-c207-428b-98d4-654bb3b8e607", "A2C");

        configs.setExpectedResponseCode(400);
        configs.getConfigsTransactions("80bbbe8b-c207-428b-98d4-654bb3b8e606", "C2F");
        configs.getConfigsTransactions("80bbbe8b-c207-428b-98d4-654bb3b8e", "A2C");

        configs.setExpectedResponseCode(200);
    }

    public void negative_getConfigs(){
        configs.getConfigs("80bbbe8b-c207-428b-98d4-654bb3b8e607");
        Assert.assertFalse(configs.getResponse().contains("\"name\""));

        configs.setExpectedResponseCode(404);
        configs.getConfigs("");
        Assert.assertTrue(configs.getResponse().contains("\"error\""));

        configs.setExpectedResponseCode(500);
        configs.getConfigs("12");
        Assert.assertTrue(configs.getResponse().contains("\"merchant_id\":\"NOT_VALID_UUIDV4\""));

        configs.setExpectedResponseCode(200);
    }

    public void positive_updateConfigs(){
        String merchant_id = "80bbbe8b-c207-428b-98d4-654bb3b8e606";
        String uuid = Uuid_helper.generate_uuid();
        configs.setExpectedResponseCode(204);
        configs.updateConfigs(merchant_id,"{\n" +
                "    \"submerchant_url\": \"https://test.fuib.com/"+uuid+"\"\n" +
                "}");

        configs.setExpectedResponseCode(200);
        configs.getConfigs(merchant_id);
        Assert.assertTrue(configs.getResponse().contains("\"submerchant_url\":\"https://test.fuib.com/"+uuid+"\""));
    }

    public void positive_addConfigs(){
        String merchant_id = "c2bd11e7-bc16-4819-a43a-3956beeb5a87";
        String uuid = Uuid_helper.generate_uuid();
        try {
            configs.addConfigs(merchant_id,"{\"merchant_id\":\""+merchant_id+"\",\"point_id\":\"3330\",\"channel_id\":15,\"submerchant_url\":\"https://test.fuib.com/"+uuid+"\",\"mode\":\"OTHER\",\"require_description\":false,\"require_identification\":false,\"check_limits\":false,\"callback_ib\":false}\n");
        }catch (Throwable e){
            Assert.fail("Выполнить - {delete FROM merchants.configs x where x.merchant_id = '"+merchant_id+"'}");
        }
        configs.setExpectedResponseCode(200);
        configs.getConfigs(merchant_id);
        Assert.assertTrue(configs.getResponse().contains("\"submerchant_url\":\"https://test.fuib.com/"+uuid+"\""));
    }

    public void positive_instructions(){
        //all instructions
        List.of("acquiring","smart_pay","oplata_chactinami").forEach(x->{
            configs.createAdmins("{\n" +
                "  \"msisdn\": \"380987654321\",\n" +
                "  \"client_id\": \"2186b853-23a8-440b-9f0f-2f8ea1cf8885\",\n" +
                "  \"merchant_id\": \"c14b8a88-56e4-4582-9e2b-d4886083c051\",\n" +
                "  \"email\": \"Vladyslav.Savchuk@valuetek.com.ua\",\n" +
                "  \"login\": \""+Uuid_helper.generate_uuid()+"\",\n" +
                "  \"roles\": {\"acquiring\": [], \"smart_pay\": []},\n" +
                "  \"instructions\": [\""+x+"\"]\n" +
                "}");
        });

        //null instructions
        configs.createAdmins("{\n" +
                "  \"msisdn\": \"380987654321\",\n" +
                "  \"client_id\": \"2186b853-23a8-440b-9f0f-2f8ea1cf8885\",\n" +
                "  \"merchant_id\": \"c14b8a88-56e4-4582-9e2b-d4886083c051\",\n" +
                "  \"email\": \"Vladyslav.Savchuk@valuetek.com.ua\",\n" +
                "  \"login\": \""+Uuid_helper.generate_uuid()+"\",\n" +
                "  \"roles\": {\"acquiring\": [], \"smart_pay\": []},\n" +
                "  \"instructions\": []\n" +
                "}");

        //without instructions
        configs.createAdmins("{\n" +
                "  \"msisdn\": \"380987654321\",\n" +
                "  \"client_id\": \"2186b853-23a8-440b-9f0f-2f8ea1cf8885\",\n" +
                "  \"merchant_id\": \"c14b8a88-56e4-4582-9e2b-d4886083c051\",\n" +
                "  \"email\": \"Vladyslav.Savchuk@valuetek.com.ua\",\n" +
                "  \"login\": \""+Uuid_helper.generate_uuid()+"\",\n" +
                "  \"roles\": {\"acquiring\": [], \"smart_pay\": []}\n" +
                "}");

        //more one
        configs.createAdmins("{\n" +
                "  \"msisdn\": \"380987654321\",\n" +
                "  \"client_id\": \"2186b853-23a8-440b-9f0f-2f8ea1cf8885\",\n" +
                "  \"merchant_id\": \"c14b8a88-56e4-4582-9e2b-d4886083c051\",\n" +
                "  \"email\": \"Vladyslav.Savchuk@valuetek.com.ua\",\n" +
                "  \"login\": \""+Uuid_helper.generate_uuid()+"\",\n" +
                "  \"roles\": {\"acquiring\": [], \"smart_pay\": []},\n" +
                "  \"instructions\": [\"acquiring\",\"smart_pay\",\"oplata_chactinami\"]\n" +
                "}");
    }

    public void negative_instructions(){
        configs.setExpectedResponseCode(400);
        configs.createAdmins("{\n" +
                "  \"msisdn\": \"380987654321\",\n" +
                "  \"client_id\": \"2186b853-23a8-440b-9f0f-2f8ea1cf8885\",\n" +
                "  \"merchant_id\": \"c14b8a88-56e4-4582-9e2b-d4886083c051\",\n" +
                "  \"email\": \"Vladyslav.Savchuk@valuetek.com.ua\",\n" +
                "  \"login\": \""+Uuid_helper.generate_uuid()+"\",\n" +
                "  \"roles\": {\"acquiring\": [], \"smart_pay\": []},\n" +
                "  \"instructions\": [\"acquiring1\"]\n" +
                "}");
        configs.setExpectedResponseCode(200);
    }

    public void getAdmins_positive(){
        configs.getAdmins("881c356e-3931-4b5b-ac81-a974d88c9b3a");
    }

    public void getAdmins_negative(){
        configs.setExpectedResponseCode(400);
        configs.getAdmins("881c356e-3931-4b5b-ac81-a974d88c9b31");
        configs.setExpectedResponseCode(200);
    }

    public void updateAdmins_positiveInstructions(){
        List.of("acquiring","smart_pay","oplata_chactinami").forEach(x->{
            configs.setExpectedResponseCode(204);
            configs.updateAdmins("881c356e-3931-4b5b-ac81-a974d88c9b3a", "{\n" +
                    "  \"client_id\": \"2186b853-23a8-440b-9f0f-2f8ea1cf8885\",\n" +
                    "  \"merchant_id\": \"c14b8a88-56e4-4582-9e2b-d4886083c051\",\n" +
                    "  \"email\": \"Vladyslav.Savchuk@valuetek.com.ua\",\n" +
                    "  \"reset_password\": true,\n" +
                    "  \"instructions\": [\""+x+"\"]\n" +
                    "}");
            configs.setExpectedResponseCode(200);
            configs.getAdmins("881c356e-3931-4b5b-ac81-a974d88c9b3a");
            Assert.assertTrue(configs.getResponse().contains("\"instructions\":[\""+x+"\"]"));
        });

        configs.setExpectedResponseCode(204);
        configs.updateAdmins("881c356e-3931-4b5b-ac81-a974d88c9b3a", "{\n" +
                "  \"client_id\": \"2186b853-23a8-440b-9f0f-2f8ea1cf8885\",\n" +
                "  \"merchant_id\": \"c14b8a88-56e4-4582-9e2b-d4886083c051\",\n" +
                "  \"email\": \"Vladyslav.Savchuk@valuetek.com.ua\",\n" +
                "  \"reset_password\": true,\n" +
                "  \"instructions\": [\"acquiring\",\"smart_pay\",\"oplata_chactinami\"]\n" +
                "}");
        configs.setExpectedResponseCode(200);
        configs.getAdmins("881c356e-3931-4b5b-ac81-a974d88c9b3a");
        Assert.assertTrue(configs.getResponse().contains("\"instructions\":[\"acquiring\",\"smart_pay\",\"oplata_chactinami\"]"));
    }

    public void updateAdmins_negativeInstructions(){
        configs.setExpectedResponseCode(400);
        configs.updateAdmins("881c356e-3931-4b5b-ac81-a974d88c9b3a", "{\n" +
                "  \"client_id\": \"2186b853-23a8-440b-9f0f-2f8ea1cf8885\",\n" +
                "  \"merchant_id\": \"c14b8a88-56e4-4582-9e2b-d4886083c051\",\n" +
                "  \"email\": \"Vladyslav.Savchuk@valuetek.com.ua\",\n" +
                "  \"reset_password\": true,\n" +
                "  \"instructions\": [\"acquiring1\",\"smart_pay\",\"oplata_chactinami\"]\n" +
                "}");
    }

    public void updateAdmins_reset_password_TRUE(){
        configs.setExpectedResponseCode(204);
        configs.updateAdmins("881c356e-3931-4b5b-ac81-a974d88c9b3a", "{\n" +
                "  \"client_id\": \"2186b853-23a8-440b-9f0f-2f8ea1cf8885\",\n" +
                "  \"merchant_id\": \"c14b8a88-56e4-4582-9e2b-d4886083c051\",\n" +
                "  \"email\": \"Vladyslav.Savchuk@valuetek.com.ua\",\n" +
                "  \"reset_password\": true,\n" +
                "  \"instructions\": [\"acquiring\",\"smart_pay\",\"oplata_chactinami\"]\n" +
                "}");
    }

    public void updateAdmins_reset_password_FALSE(){
        configs.setExpectedResponseCode(204);
        configs.updateAdmins("881c356e-3931-4b5b-ac81-a974d88c9b3a", "{\n" +
                "  \"client_id\": \"2186b853-23a8-440b-9f0f-2f8ea1cf8885\",\n" +
                "  \"merchant_id\": \"c14b8a88-56e4-4582-9e2b-d4886083c051\",\n" +
                "  \"email\": \"Vladyslav.Savchuk@valuetek.com.ua\",\n" +
                "  \"reset_password\": false,\n" +
                "  \"instructions\": [\"acquiring\",\"smart_pay\",\"oplata_chactinami\"]\n" +
                "}");
    }

    public void pos(){
        configs.updateConfigsInstallments("3bfda6a7-36ed-442b-a840-80d75d0fb7c6",
                "1c0b3059-24ab-4cb0-8343-0ed789603ae1",
                "{\n" +
                        "  \"title\": \"string1\",\n" +
                        "  \"point_id\": \"string1\",\n" +
                        "  \"partner_name\": \"string1\",\n" +
                        "  \"point_type\": \"POS\",\n" +
                        "  \"status\": \"BLOCKED\"\n" +
                        "}");
    }
}
