package test.backTests.payHub.framesTest;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.payHub.frames.AcquiringFrame;
import org.example.qaTransactionTeam.backEnd.payHub.frames.C4СFrame;
import org.testng.annotations.Test;

@Test
public class PGAFramesTest extends BaseTest {

    AcquiringFrame acquiringFrame;

    public void acquiringFrame(){
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
                "    \"merchant_config_id\": \"c949382b-ddd0-4b13-94d3-713dfa3e8cca\",\n" +
                "    \"config_id\": \"e137fdbc-30ac-470d-8b77-862bad72152f\",\n" +
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

        acquiringFrame = new AcquiringFrame(body);
    }

}
