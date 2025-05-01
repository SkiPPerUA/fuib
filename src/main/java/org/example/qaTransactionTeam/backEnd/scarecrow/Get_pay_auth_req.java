package org.example.qaTransactionTeam.backEnd.scarecrow;

import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data;
import org.example.qaTransactionTeam.backEnd.utils.Configs;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

public class Get_pay_auth_req extends ScarecrowRabbit{

    private Logger logger = Logger.getLogger(Get_pay_auth_req.class);
    private String vers;

    public void sendToRabbit(int version3ds, String acqid) throws URISyntaxException, KeyManagementException, TimeoutException, NoSuchAlgorithmException, IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        dataForMerchant(acqid);

        if (version3ds == 2) {
            vers = "2.1.0";
        } else if (version3ds == 1) {
            vers = "1";
        } else {
            logger.error("Версия 3ds - НЕ корректна");
        }

        String mess = "{\n" +
                "                  \"session_id\":\"999999999999\",\n" +
                "                  \"card_number\":\""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan) +"\",\n" +
                "                  \"ip\":\"0.0.0.0\",\n" +
                "                  \"exp_date\":\""+Cards_data.getData(Card.FUIB_MC, Card_param.expire)+"\",\n" +
                "                  \"cipher_key\":\"fVdTDKSzqyLdilKK9*****\",\n" +
                "                  \"amount\":\"800\",\n" +
                "                  \"cvv2\":\""+Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "                  \"version\":\""+vers+"\",\n" +
                "                  \"channel\":\"BRW\",\n" +
                "                  \"browser_accept_header\":\"*/*\",\n" +
                "                  \"browser_java_enabled\":true,\n" +
                "                  \"browser_language\":\"RU\",\n" +
                "                  \"browser_color_depth\":24,\n" +
                "                  \"browser_screen_height\":1800,\n" +
                "                  \"browser_screen_width\":900,\n" +
                "                  \"browser_tz\":-120,\n" +
                "                  \"browser_user_agent\":\"Chrome Agent\",\n" +
                "                  \"challenge_window_size\":\"05\",\n" +
                "                  \"iframe_return_url\":\"https://service.fuib.com\",\n" +
                "                  \"finger_print\":\"1q2w3e4r5t6y\",\n" +
                "                  \"acquirer_settings\":{\n" +
                "                      \"merchant_id_tds\":\""+merchant_id_tds+"\",\n" +
                "                      \"merchant_id_av\":\""+merchant_id_av+"\",\n" +
                "                      \"account_id_tds\":\""+account_id_tds+"\",\n" +
                "                      \"account_id_av\":\""+account_id_av+"\",\n" +
                "                      \"cata\":\"PUMB Card-to-Card      KYIV           UA\",\n" +
                "                      \"otp_length\":8,\n" +
                "                      \"portal_id\":\""+portal_id+"\",\n" +
                "                      \"available_tds\":true\n" +
                "                  }\n" +
                "                 }";


        rabbit.getChannel().queueDeclare("get_pay_auth_req", true, false, false, null);
        rabbit.getChannel().exchangeDeclare("tsys_3ds", "direct", true);

        rabbit.getChannel().basicPublish("tsys_3ds", "get_pay_auth_req",
        rabbit.getProperties().correlationId("vlad2").replyTo("qasdf1").contentType("application/json").build(),
                mess.getBytes());
        logger.info("Отправлено сообщение в Rabbit -> " + mess);
        System.out.println("Результат -> https://rmq01.dev-fuib.com/#/queues/tsys/qasdf1");

    }

}

