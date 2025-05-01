package test.backTests.pga;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.payHub.frames.AcquiringFrame;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class FrameTests extends BaseTest {

    public void test(){
        List<String> frames = List.of("06362227-11f3-4290-9678-59547c6f727d","bd7b8a2a-de7f-420a-9820-88568832813b","5848f1d4-715e-4cbe-8c3a-9d2e57de5c39","efbd69c2-b129-4d4b-8712-88fb1e163407");
        frames.forEach(x->{String body = "\"options\": {\n" +
                "        \"ttl\": 0,\n" +
                "        \"create_short_url\": true,\n" +
                "        \"backurl\": {\n" +
                "            \"success\": \"https://innsmouth.payhub.com.ua/payment/success\",\n" +
                "            \"error\": \"https://innsmouth.payhub.com.ua/payment/error\",\n" +
                "            \"cancel\": \"https://innsmouth.payhub.com.ua/payment/cancel\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"lang\": \"UK\",\n" +
                "    \"amount\": 100,\n" +
                "    \"title\": \"Merchant name\",\n" +
                "    \"description\": \"Payment description\",\n" +
                "    \"short_description\": \"Short payment description\",\n" +
                "    \"merchant_config_id\": \"c949382b-ddd0-4b13-94d3-713dfa3e8cca\",\n" +
                "    \"config_id\": \""+x+"\",\n" +
                "    \"params\": {\n" +
                "        \"shop_url\": \"https://yourapp.test\"\n" +
                "    }";
            AcquiringFrame frame = new AcquiringFrame(body);});
    }
}
