package org.example.qaTransactionTeam.backEnd.scarecrow;


import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.Card;
import org.example.qaTransactionTeam.backEnd.utils.Card_param;
import org.example.qaTransactionTeam.backEnd.utils.Cards_data1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

public class Single_lookup extends ScarecrowRabbit{

    private Logger logger = Logger.getLogger(Single_lookup.class);
    private String mess;


    public void sendToRabbit(String acqid) throws URISyntaxException, KeyManagementException, TimeoutException, NoSuchAlgorithmException, IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        dataForMerchant(acqid);

        mess = "{\n" +
                "                \"session_id\":\"999999999999\",\n" +
                "               \t\"card_number\":\""+ Cards_data1.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                "               \t\"ip\":\"0.0.0.0\",\n" +
                "               \t\"exp_date\":\""+ Cards_data1.getData(Card.FUIB_MC, Card_param.expire)+"\",\n" +
                "               \t\"cipher_key\":\"fVdTDKSzqyLdilKK9****\",\n" +
                "               \t\"amount\":\"800\",\n" +
                "               \t\"cvv2\":\""+ Cards_data1.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                "               \t\"acquirer_settings\":{\n" +
                "               \t\t\"merchant_id_tds\":\""+merchant_id_tds+"\",\n" +
                "               \t\t\"merchant_id_av\":\""+merchant_id_av+"\",\n" +
                "               \t\t\"account_id_tds\":\""+account_id_tds+"\",\n" +
                "               \t\t\"account_id_av\":\""+account_id_av+"\",\n" +
                "               \t\t\"cata\":\"PUMB Card-to-Card      KYIV           UA\",\n" +
                "               \t\t\"otp_length\":8,\n" +
                "               \t\t\"portal_id\":\""+portal_id+"\",\n" +
                "               \t\t\"available_tds\":true\n" +
                "               \t}\n" +
                "               }\n";

        rabbit.getChannel().queueDeclare("single_lookup", true, false, false, null);
        rabbit.getChannel().exchangeDeclare("tsys_3ds", "direct", true);

        rabbit.getChannel().basicPublish("tsys_3ds", "single_lookup",
                rabbit.getProperties().correlationId("vlad2").replyTo("qasdf1").contentType("application/json").build(),
                mess.getBytes());
        logger.info("Отправлено сообщение в Rabbit -> " + mess);
        System.out.println("Результат -> https://rmq01.dev-fuib.com/#/queues/tsys/qasdf1");

    }


}
