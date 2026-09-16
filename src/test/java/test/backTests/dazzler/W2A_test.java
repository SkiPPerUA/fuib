package test.backTests.dazzler;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.dazzler.W2A_trans;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.testng.annotations.Test;

@Test
public class W2A_test extends BaseTest {

    W2A_trans w2ATrans = new W2A_trans();

    public void positive(){
        w2ATrans.dPans("{\n" +
                "   \"amount\":123,\n" +
                "   \"external_id\":\"1\",\n" +
                "   \"description\":\"\",\n" +
                "   \"receiver\":{\n" +
                "      \"source\":\"ACCOUNT_ID\",\n" +
                "      \"value\":201919872\n" +
                "   },\n" +
                "   \"payer\":{\n" +
                "      \"senderName\":\"Синьоока Ірина Олександрівна\",\n" +
                "      \"source\":\"APPLE_PAY\",\n" +
                "      \"payerInfo\":{\n" +
                "         \"infoSource\":\"shipping\",\n" +
                "         \"name\":\"Ірина Синьоока\"\n" +
                "      },\n" +
                "      \"payload\":{\n" +
                "         \"paymentData\":{\n" +
                "            \"version\":\"EC_v1\",\n" +
                "            \"signature\":\"***\",\n" +
                "            \"header\":{\n" +
                "               \"ephemeralPublicKey\":\"MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEaTqZNGGhBo0tCV5QUjLBun8FWXXv9BPpc2jx/KsXLfexPtj22j/MwtITEaOsyEiOyis9HJ9JTQ5KWc0nCs+RXg==\",\n" +
                "               \"publicKeyHash\":\"/8iBWb16McJiYwvOr65zorYVstIPfGdCMub0HhogKpQ=\",\n" +
                "               \"transactionId\":\"5c19ee1d48c07a646b6f3db689573e6367fddff3be6e06679f5538379b3aeeef\"\n" +
                "            },\n" +
                "            \"data\":\"Rxw0XaCB********ishd5w==\"\n" +
                "         },\n" +
                "         \"paymentMethod\":{\n" +
                "            \"displayName\":\"Visa 6811\",\n" +
                "            \"network\":\"Visa\",\n" +
                "            \"type\":\"debit\"\n" +
                "         },\n" +
                "         \"transactionIdentifier\":\"5c19ee1d48c07a646b6f3db689573e6367fddff3be6e06679f5538379b3aeeef\"\n" +
                "      }\n" +
                "   },\n" +
                "   \"threed\":{\n" +
                "      \"ip\":\"37.54.26.134\",\n" +
                "      \"fingerprint\":\"6a21ee20d1e32f1364c804c9\",\n" +
                "      \"channel\":\"BRW\",\n" +
                "      \"accept_header\":\"*/*\",\n" +
                "      \"java_enabled\":true,\n" +
                "      \"language\":\"uk\",\n" +
                "      \"color_depth\":32,\n" +
                "      \"screen_height\":874,\n" +
                "      \"screen_width\":402,\n" +
                "      \"time_zone\":180,\n" +
                "      \"user_agent\":\"PUMBOnline/2.326.2 iPhone18,1 iOS/26.4.1 CFNetwork/1.0 Darwin/25.4.0\",\n" +
                "      \"challenge_window_size\":\"04\"\n" +
                "   },\n" +
                "   \"authentication\":{\n" +
                "      \"device_id\":\"6a21ee20d1e32f1364c804c9\",\n" +
                "      \"session_id\":\"1781517896\"\n" +
                "   }\n" +
                "}");
    }

}
