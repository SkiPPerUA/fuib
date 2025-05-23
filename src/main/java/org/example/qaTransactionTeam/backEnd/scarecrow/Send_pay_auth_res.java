package org.example.qaTransactionTeam.backEnd.scarecrow;

import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

public class Send_pay_auth_res extends ScarecrowRabbit{

    private Logger logger = Logger.getLogger(Send_pay_auth_res.class);
    private String mess;
    private String step;
    private String data;

    public Send_pay_auth_res(int version3ds, String acqid) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, KeyManagementException, TimeoutException, NoSuchAlgorithmException, IOException, URISyntaxException {

        dataForMerchant(acqid);

        if (version3ds == 2) {
            step = "CREQ";
            data = "c_res";
        } else if (version3ds == 1) {
            step = "TRUE_PAREQ";
            data = "pa_res";
        } else {
            logger.error("Версия 3ds - НЕ корректна");
        }

    }

    public void sendToRabbit(String token, String value) throws URISyntaxException, KeyManagementException, TimeoutException, NoSuchAlgorithmException, IOException {


        mess = "{\n" +
                    "               \t\"token\":\""+token+"\",\n" +
                    "                \"session_id\":\"999999999999\",\n" +
                    "               \t\"card_number\":\""+ Cards_data.getData(Card.FUIB_MC, Card_param.pan)+"\",\n" +
                    "               \t\"ip\":\"0.0.0.0\",\n" +
                    "               \t\"exp_date\":\""+ Cards_data.getData(Card.FUIB_MC, Card_param.expire)+"\",\n" +
                    "               \t\"cipher_key\":\"fVdTDKSzqyLdilKK9U****\",\n" +
                    "               \t\"amount\":\"800\",\n" +
                    "               \t\"cvv2\":\""+ Cards_data.getData(Card.FUIB_MC, Card_param.cvv)+"\",\n" +
                    "               \t\"acquirer_settings\":{\n" +
                    "               \t\t\"merchant_id_tds\":\"" + merchant_id_tds+ "\",\n" +
                    "               \t\t\"merchant_id_av\":\"" + merchant_id_av + "\",\n" +
                    "               \t\t\"account_id_tds\":\"" + account_id_tds + "\",\n" +
                    "               \t\t\"account_id_av\":\"" + account_id_av + "\",\n" +
                    "               \t\t\"cata\":\"PUMB Card-to-Card      KYIV           UA\",\n" +
                    "               \t\t\"otp_length\":8,\n" +
                    "               \t\t\"portal_id\":\""+portal_id+"\",\n" +
                    "               \t\t\"available_tds\":true\n" +
                    "               \t},\n" +
                    "               \"step\":\""+step+"\",\n" +
                    "               \"md\":\"MD\",\n" +
                    "               \""+data+"\":\""+ value +"\"\n" +
                    "               }\n";
        if(rabbit == null){
            rabbit = new RabbitMQ("tsys");
        }

        rabbit.getChannel().queueDeclare("send_pay_auth_res", true, false, false, null);
        rabbit.getChannel().exchangeDeclare("tsys_3ds", "direct", true);

        rabbit.getChannel().basicPublish("tsys_3ds", "send_pay_auth_res",
                rabbit.getProperties().correlationId("vlad2").replyTo("qasdf1").contentType("application/json").build(),
                mess.getBytes());
        logger.info("Отправлено сообщение в Rabbit -> " + mess);
        System.out.println("Результат -> https://rmq01.dev-fuib.com/#/queues/tsys/qasdf1");

    }

}
