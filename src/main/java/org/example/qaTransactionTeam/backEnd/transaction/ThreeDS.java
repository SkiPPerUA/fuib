package org.example.qaTransactionTeam.backEnd.transaction;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public interface ThreeDS {

    String threeDS_2_1_0 = "\"threed\": {\n" +
            "        \"version\": \"2.1.0\",\n" +
            "        \"fingerprint\": \"test\",\n" +
            "        \"java_enabled\": false,\n" +
            "        \"javascript_enabled\": true,\n" +
            "        \"accept_header\": \"*\",\n" +
            "        \"lang\": \"RU\",\n" +
            "        \"color_depth\": 24,\n" +
            "        \"screen_width\": 1920,\n" +
            "        \"screen_height\": 1080,\n" +
            "        \"tz\": 120,\n" +
            "        \"challenge_window_size\": \"02\",\n" +
            "        \"iframe_return_url\": \"https://service.fuib.com\",\n" +
            "        \"user_agent\": \"Gecko\"\n" +
            "    }";

    String threeDS_2_2_0 = "\"threed\": {\n" +
            "        \"version\": \"2.2.0\",\n" +
            "        \"fingerprint\": \"test\",\n" +
            "        \"java_enabled\": false,\n" +
            "        \"javascript_enabled\": true,\n" +
            "        \"accept_header\": \"*\",\n" +
            "        \"lang\": \"RU\",\n" +
            "        \"color_depth\": 24,\n" +
            "        \"screen_width\": 1920,\n" +
            "        \"screen_height\": 1080,\n" +
            "        \"tz\": 120,\n" +
            "        \"challenge_window_size\": \"02\",\n" +
            "        \"iframe_return_url\": \"https://service.fuib.com\",\n" +
            "        \"user_agent\": \"Gecko\"\n" +
            "    }";

    String with_threeDS_2_1_0_itm = "\"info_3ds\":{\n" +
            "\t\t\"ip\":\"0.0.0.127\",\n" +
            "\t\t\"fingerprint\":\"1q2w3e4r5t\",\n" +
            "\t\t\"without_3ds\": false,\n" +
            "\t\t\"version\": \"2.1.0\",\n" +
            "\t\t\"channel\":\"BRW\",\n" +
            "\t\t\"accept_header\":\"text/html\",\n" +
            "\t\t\"java_enabled\":false,\n" +
            "\t\t\"language\":\"RU-ru\",\n" +
            "\t\t\"color_depth\":32,\n" +
            "\t\t\"screen_height\":800,\n" +
            "\t\t\"screen_width\":900, \n" +
            "\t\t\"time_zone\":-180,\n" +
            "\t\t\"user_agent\":\"Gecko\",\n" +
            "\t\t\"challenge_window_size\":\"04\",\n" +
            "\t\t\"return_url\":\"https://service.fuib.com\"\n" +
            "\t}\n";

    String without_threeDS_2_1_0_itm = "\"info_3ds\":{\n" +
            "\t\t\"ip\":\"0.0.0.127\",\n" +
            "\t\t\"fingerprint\":\"1q2w3e4r5t\",\n" +
            "\t\t\"without_3ds\": true,\n" +
            "\t\t\"version\": \"2.1.0\",\n" +
            "\t\t\"channel\":\"BRW\",\n" +
            "\t\t\"accept_header\":\"text/html\",\n" +
            "\t\t\"java_enabled\":false,\n" +
            "\t\t\"language\":\"RU-ru\",\n" +
            "\t\t\"color_depth\":32,\n" +
            "\t\t\"screen_height\":800,\n" +
            "\t\t\"screen_width\":900, \n" +
            "\t\t\"time_zone\":-180,\n" +
            "\t\t\"user_agent\":\"Gecko\",\n" +
            "\t\t\"challenge_window_size\":\"04\",\n" +
            "\t\t\"return_url\":\"https://service.fuib.com\"\n" +
            "\t}\n";

    String threeDS_2_2_0_acquiring = "{\n" +
            "    \"version\": \"2.2.0\",\n" +
            "    \"device\": {\n" +
            "      \"channel\": \"BRW\",\n" +
            "      \"challenge_window_size\": \"01\",\n" +
            "      \"iframe_return_url\": \"http://127.0.0.1:3000\",\n" +
            "      \"browser_accept_header\": \"*/*\",\n" +
            "      \"browser_ip\": \"2001:0db8:85a3:0000:0000:8a2e:0370:7334\",\n" +
            "      \"browser_java_enabled\": false,\n" +
            "      \"browser_language\": \"ru\",\n" +
            "      \"browser_javascript_enabled\": true,\n" +
            "      \"browser_color_depth\": \"32\",\n" +
            "      \"browser_screen_height\": \"1080\",\n" +
            "      \"browser_screen_width\": \"1920\",\n" +
            "      \"browser_tz\": \"180\",\n" +
            "      \"browser_user_agent\": \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36\"\n" +
            "    }\n" +
            "  }";

    String threeDS_2_1_0_acquiring = "{\n" +
            "    \"version\": \"2.1.0\",\n" +
            "    \"device\": {\n" +
            "      \"channel\": \"BRW\",\n" +
            "      \"challenge_window_size\": \"01\",\n" +
            "      \"iframe_return_url\": \"http://127.0.0.1:3000\",\n" +
            "      \"browser_accept_header\": \"*/*\",\n" +
            "      \"browser_ip\": \"2001:0db8:85a3:0000:0000:8a2e:0370:7334\",\n" +
            "      \"browser_java_enabled\": false,\n" +
            "      \"browser_language\": \"ru\",\n" +
            "      \"browser_javascript_enabled\": true,\n" +
            "      \"browser_color_depth\": \"32\",\n" +
            "      \"browser_screen_height\": \"1080\",\n" +
            "      \"browser_screen_width\": \"1920\",\n" +
            "      \"browser_tz\": \"180\",\n" +
            "      \"browser_user_agent\": \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36\"\n" +
            "    }\n" +
            "  }";

    static void createIFrame(String url, String pareq) {
        String htmlCreq = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<html>\n" +
                "  <body onload=\"document.getElementById('acs').submit()\">\n" +
                "    <form id=\"acs\" method=\"post\" action=\"" + url + "\">\n" +
                "      <input style=\"display:none\" type=\"submit\">\n" +
                "      <input type=\"hidden\" id=\"creq\" value=\"" + pareq + "\" name=\"creq\">\n" +
                "      </form>\n" +
                "  </body>\n" +
                "</html>";

        try {
            Files.write(Paths.get("/Users/user/Documents/Тесты/creq.html"), htmlCreq.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(new File("/Users/user/Documents/Тесты/creq.html"));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        String htmlpareq = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
//                "<html>\n" +
//                "  <body onload=\"document.getElementById('acs').submit()\">\n" +
//                "    <form id=\"acs\" method=\"post\" action=\"" + url + "\">\n" +
//                "      <input style=\"display:none\" type=\"submit\">\n" +
//                "      <input type=\"hidden\" id=\"MD\" value=\"md\" name=\"MD\">\n" +
//                "      <input type=\"hidden\" id=\"TermUrl\" value=\"https://service.fuib.com/\" name=\"TermUrl\">\n" +
//                "      <input type=\"hidden\" id=\"PaReq\" value=\"" + pareq + "\" name=\"PaReq\">\n" +
//                "      </form>\n" +
//                "  </body>\n" +
//                "</html>";
//
//        Files.write(Paths.get("/Users/user/Documents/Тесты/acs.html"), htmlpareq.getBytes(StandardCharsets.UTF_8));
//        Desktop desktop = Desktop.getDesktop();
//        desktop.open(new File("/Users/user/Documents/Тесты/acs.html"));

        //Пауза для прохождение 3дс через iFrame
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
