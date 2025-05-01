package org.example.qaTransactionTeam.backEnd.notificationBP;

import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.BDpostgre;
import org.example.qaTransactionTeam.backEnd.utils.RabbitMQ;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

public class NotificationBP {

    public static final Logger logger = Logger.getLogger(NotificationBP.class);

    public void createEvent(String trty, String stan, String actn, String ntistp) throws URISyntaxException, KeyManagementException, TimeoutException, NoSuchAlgorithmException, IOException, InterruptedException {

        String event = "{\n" +
                "\t\"NTMSGC\": \"02\",\n" +
                "\t\"NTMSGF\": \"1\",\n" +
                "\t\"NTMSGT\": \"0\",\n" +
                "\t\"NTMICD\": \"0\",\n" +
                "\t\"NTTRTY\": \""+trty+"\",\n" +
                "\t\"NTTRFA\": \"01\",\n" +
                "\t\"NTTRTA\": \"\",\n" +
                "\t\"NTACTN\": \""+actn+"\",\n" +
                "\t\"NTRSPC\": \"00\",\n" +
                "\t\"NTAPRNUM\": \"\",\n" +
                "\t\"NTCRDNUM\": \"\",\n" +
                "\t\"NTXMDT\": \"1210409\",\n" +
                "\t\"NTXMTM\": \"151134\",\n" +
                "\t\"NTSTAN\": \""+stan+"\",\n" +
                "\t\"NTRRFNUM\": \"\",\n" +
                "\t\"NTAQPT\": \""+ntistp+"\",\n" +
                "\t\"NTISPT\": \""+ntistp+"\",\n" +
                "\t\"NTI2PT\": \"\",\n" +
                "\t\"NTAQND\": \"\",\n" +
                "\t\"NTISND\": \"\",\n" +
                "\t\"NTI2ND\": \"\",\n" +
                "\t\"NTNTID\": \"ITM\",\n" +
                "\t\"NTGWAY\": \"\",\n" +
                "\t\"NTMCAT\": \"0000\",\n" +
                "\t\"NTCAID\": \"911111111111\",\n" +
                "\t\"NTCATI\": \"00010001\",\n" +
                "\t\"NTCATA\": \"b_mtb2021-04-09-15.11.34.003849\",\n" +
                "\t\"NTAQID\": \""+ntistp+"\",\n" +
                "\t\"NTI1ID\": \"\",\n" +
                "\t\"NTI2ID\": \"\",\n" +
                "\t\"NTTDAT\": \"1210409\",\n" +
                "\t\"NTTTIM\": \"151134\",\n" +
                "\t\"NTCASD\": \"1210327\",\n" +
                "\t\"NTASDT\": \"1210409\",\n" +
                "\t\"NTI1DT\": \"0\",\n" +
                "\t\"NTI2DT\": \"0\",\n" +
                "\t\"NTPOSL\": \"0\",\n" +
                "\t\"NTTRCC\": \"980\",\n" +
                "\t\"NTTRNSUM\": \"\",\n" +
                "\t\"NTATRSUM\": \"\",\n" +
                "\t\"NTTRFE\": \"0\",\n" +
                "\t\"NTCOCC\": \"\",\n" +
                "\t\"NTCOTSUM\": \"\",\n" +
                "\t\"NTACOSUM\": \"\",\n" +
                "\t\"NTCICC\": \"\",\n" +
                "\t\"NTCITSUM\": \"\",\n" +
                "\t\"NTASCC\": \"980\",\n" +
                "\t\"NTASASUM\": \"\",\n" +
                "\t\"NTASFSUM\": \"\",\n" +
                "\t\"NTASPSUM\": \"\",\n" +
                "\t\"NTASCR\": \"1.000000000\",\n" +
                "\t\"NTI1BC\": \"\",\n" +
                "\t\"NTI1CC\": \"\",\n" +
                "\t\"NTI1ASUM\": \"\",\n" +
                "\t\"NTI1FSUM\": \"\",\n" +
                "\t\"NTI1PSUM\": \"\",\n" +
                "\t\"NTI1CR\": \"0.000000000\",\n" +
                "\t\"NTI2BC\": \"\",\n" +
                "\t\"NTI2CC\": \"\",\n" +
                "\t\"NTI2ASUM\": \"\",\n" +
                "\t\"NTI2FSUM\": \"\",\n" +
                "\t\"NTI2PSUM\": \"\",\n" +
                "\t\"NTI2CR\": \"0.000000000\",\n" +
                "\t\"NTC1CC\": \"980\",\n" +
                "\t\"NTC1ASUM\": \"\",\n" +
                "\t\"NTC1AF\": \"0\",\n" +
                "\t\"NTC1PF\": \"0\",\n" +
                "\t\"NTC1SF\": \"0\",\n" +
                "\t\"NTC1MSUM\": \"\",\n" +
                "\t\"NTC1MP\": \"0.000000000\",\n" +
                "\t\"NTSC1R\": \"1.000000000\",\n" +
                "\t\"NTC2CC\": \"\",\n" +
                "\t\"NTC2ASUM\": \"\",\n" +
                "\t\"NTC2AF\": \"0\",\n" +
                "\t\"NTC2PF\": \"0\",\n" +
                "\t\"NTC2SF\": \"0\",\n" +
                "\t\"NTC2MSUM\": \"\",\n" +
                "\t\"NTC2MP\": \"0.000000000\",\n" +
                "\t\"NTSC2R\": \"0.000000000\",\n" +
                "\t\"NTAC1Q\": \"01\",\n" +
                "\t\"NTAC1B\": \"DHO\",\n" +
                "\t\"NTAC1T\": \"01\",\n" +
                "\t\"NTAC1NUM\": \"\",\n" +
                "\t\"NTAC2Q\": \"00\",\n" +
                "\t\"NTAC2B\": \"\",\n" +
                "\t\"NTAC2T\": \"\",\n" +
                "\t\"NTAC2NUM\": \"\",\n" +
                "\t\"NTPNEM\": \"00\",\n" +
                "\t\"NTPNEC\": \"0\",\n" +
                "\t\"NTPNCC\": \"00\",\n" +
                "\t\"NTPCCD\": \"00\",\n" +
                "\t\"NTTRKT\": \"\",\n" +
                "\t\"NTCRDL\": \"0\",\n" +
                "\t\"NTISOL\": \"0\",\n" +
                "\t\"NTMBRNUM\": \"\",\n" +
                "\t\"NTVEXD\": \"\",\n" +
                "\t\"NTDATI\": \"\",\n" +
                "\t\"NTEXDT\": \"0\",\n" +
                "\t\"NTCVVV\": \"\",\n" +
                "\t\"NTCVVI\": \"\",\n" +
                "\t\"NTCVVL\": \"0\",\n" +
                "\t\"NTCVCD\": \"\",\n" +
                "\t\"NTSVCI\": \"\",\n" +
                "\t\"NTSVCD\": \"\",\n" +
                "\t\"NTVPIN\": \"\",\n" +
                "\t\"NTPBTY\": \"\",\n" +
                "\t\"NTPINB\": \"\",\n" +
                "\t\"NTOFFU\": \"\",\n" +
                "\t\"NTOFFL\": \"0\",\n" +
                "\t\"NTPINO\": \"\",\n" +
                "\t\"NTPVKI\": \"\",\n" +
                "\t\"NTPVKC\": \"\",\n" +
                "\t\"NTPVVI\": \"\",\n" +
                "\t\"NTPVVC\": \"\",\n" +
                "\t\"NTAQCO\": \"804\",\n" +
                "\t\"NTAFMT\": \"\",\n" +
                "\t\"NTPOZP\": \"\",\n" +
                "\t\"NTPOAD\": \"\",\n" +
                "\t\"NTUBDF\": \"\",\n" +
                "\t\"NTCCNV\": \"\",\n" +
                "\t\"NTSVCF\": \"\",\n" +
                "\t\"NTWSVC\": \"\",\n" +
                "\t\"NTDNFG\": \"\",\n" +
                "\t\"NTRETF\": \"N\",\n" +
                "\t\"NTCZID\": \"000070587112345678\",\n" +
                "\t\"NTVNDNUM\": \"\",\n" +
                "\t\"NTVNDQ\": \"00\",\n" +
                "\t\"NTDOC1\": \"7092192\",\n" +
                "\t\"NTDOC2\": \"92\",\n" +
                "\t\"NTDOCNUM\": \"\",\n" +
                "\t\"NTSPDY\": \"0\",\n" +
                "\t\"NTSPDT\": \"0\",\n" +
                "\t\"NTUSER\": \"ASGATE\",\n" +
                "\t\"NTTOVR\": \"\",\n" +
                "\t\"NTSOVR\": \"\",\n" +
                "\t\"NTOARF\": \"\",\n" +
                "\t\"NTPST1\": \"2\",\n" +
                "\t\"NTPST2\": \"0\",\n" +
                "\t\"NTRPSF\": \"\",\n" +
                "\t\"NTTRAK\": \"\",\n" +
                "\t\"NTLNGC\": \"\",\n" +
                "\t\"NTNAME\": \"\",\n" +
                "\t\"NTI1ST\": \"\",\n" +
                "\t\"NTI2ST\": \"\",\n" +
                "\t\"NTDPFL\": \"\",\n" +
                "\t\"NTCPFL\": \"\",\n" +
                "\t\"NTREVR\": \"\",\n" +
                "\t\"NTCMPR\": \"\",\n" +
                "\t\"NTAPTNUM\": \"\",\n" +
                "\t\"NTPBT2\": \"\",\n" +
                "\t\"NTPIN2\": \"\",\n" +
                "\t\"NTA1TY\": \"\",\n" +
                "\t\"NTA1BT\": \"\",\n" +
                "\t\"NTA1CC\": \"\",\n" +
                "\t\"NTA1BSUM\": \"\",\n" +
                "\t\"NTA2TY\": \"\",\n" +
                "\t\"NTA2BT\": \"\",\n" +
                "\t\"NTA2CC\": \"\",\n" +
                "\t\"NTA2BSUM\": \"\",\n" +
                "\t\"NTA3TY\": \"\",\n" +
                "\t\"NTA3BT\": \"\",\n" +
                "\t\"NTA3CC\": \"\",\n" +
                "\t\"NTA3BSUM\": \"\",\n" +
                "\t\"NTA4TY\": \"\",\n" +
                "\t\"NTA4BT\": \"\",\n" +
                "\t\"NTA4CC\": \"\",\n" +
                "\t\"NTA4BSUM\": \"\",\n" +
                "\t\"NTA5TY\": \"\",\n" +
                "\t\"NTA5BT\": \"\",\n" +
                "\t\"NTA5CC\": \"\",\n" +
                "\t\"NTA5BSUM\": \"\",\n" +
                "\t\"NTA6TY\": \"\",\n" +
                "\t\"NTA6BT\": \"\",\n" +
                "\t\"NTA6CC\": \"\",\n" +
                "\t\"NTA6BSUM\": \"\"\n" +
                "}";

        RabbitMQ addMess = new RabbitMQ("tsys");
        addMess.getChannel().exchangeDeclare("tsys.beyonder_stream", "direct", true);
        addMess.getChannel().basicPublish("tsys.beyonder_stream", "", addMess.getProperties().build(), event.getBytes());
        logger.info("Отправлено событие в RabbitMQ -> exchange = tsys.beyonder_stream ("+event+")");
        addMess.closeConn();

        //Ожидание обработки события на стороне beyonder
        Thread.sleep(2000);

    }

    public void deleteEvent(String stan) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        BDpostgre.updateSQL("delete from notification_processed np where ntstan = '"+stan+"'");
        logger.info("Удален стан: "+stan+" из notification_processed");

        BDpostgre.updateSQL("delete from notification_sized np where ntstan = '"+stan+"'");
        logger.info("Удален стан: "+stan+" из notification_sized");
    }

}
