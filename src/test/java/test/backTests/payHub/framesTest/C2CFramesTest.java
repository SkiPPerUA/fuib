package test.backTests.payHub.framesTest;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.payHub.frames.C2AFrame;
import org.example.qaTransactionTeam.backEnd.payHub.frames.C2СFrame;
import org.testng.annotations.Test;

@Test
public class C2CFramesTest extends BaseTest {

    C2СFrame c2cFrame;

    public void с2cFrame(){
        String body = "\"options\": {\n" +
                "        \"ttl\": 0,\n" +
                "        \"create_short_url\": true,\n" +
                "        \"backurl\": {\n" +
                "            \"success\": \"https://yourapp.test/payment/success\",\n" +
                "            \"error\": \"https://yourapp.test/payment/error\",\n" +
                "            \"cancel\": \"https://yourapp.test/payment/cancel\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"lang\": \"UK\",\n" +
                "    \"amount\": 100,\n" +
                "    \"title\": \"Merchant name\",\n" +
                "    \"description\": \"Payment description\",\n" +
                "    \"short_description\": \"Short payment description\",\n" +
                "    \"merchant_config_id\": \"fb7b84d4-2477-4a53-862f-814fc3b3010b\",\n" +
                "    \"config_id\": \"a7a240d3-00e7-4f1b-bc6f-1f3ad183f246\",\n" +
                "    \"params\": {\n" +
                "        \"shop_url\": \"https://yourapp.test\"\n" +
                "    },"+
                "    \"identification\": {\n" +
                "   \"requirements\":[{\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfak1\",\n" +
                "         \"last_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfak2\",\n" +
                "         \"account_number\":\"UA21322313000006007233566001000011\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfak1\",\n" +
                "         \"last_name\":\"jkasdfjkdsfnakjdsfnakdjsfnksajdfnsadkjfnaksjdnfak2\",\n" +
                "         \"account_number\":\"UA21323130000026007233566011000001\",\n" +
                "         \"reference_number\":\"1111111111111111\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"11111111221222222233333333334444444444555555555666666666677777111\",\n" +
                "         \"source\":\"07\",\n" +
                "         \"independent_sales_organization_id\":\"3016715233\"\n" +
                "      }\n" +
                "      },\n" +
                "     {\n" +
                "      \"recipient\":{\n" +
                "         \"first_name\":\"666\",\n" +
                "         \"last_name\":\"7777\",\n" +
                "         \"account_number\":\"UA21322313000006007233566001000011\"\n" +
                "      },\n" +
                "      \"sender\":{\n" +
                "         \"first_name\":\"77711\",\n" +
                "         \"last_name\":\"1123\",\n" +
                "         \"account_number\":\"UA21323130000026007233566011000001\",\n" +
                "         \"reference_number\":\"123\"\n" +
                "      },\n" +
                "      \"details\":{\n" +
                "         \"additional_message\":\"11111111221222222233333333334444444444555555555666666666677777111\",\n" +
                "         \"source\":\"07\",\n" +
                "         \"independent_sales_organization_id\":\"3016715233\"\n" +
                "      }\n" +
                "      }]\n"+
                "      }\n";

        c2cFrame = new C2СFrame(body);
        c2cFrame.status();
    }

    public void st(){
        c2cFrame = new C2СFrame();
        c2cFrame.status("a4153427-9b14-4c2c-8c67-ca07a37a4dfd");
    }
}
