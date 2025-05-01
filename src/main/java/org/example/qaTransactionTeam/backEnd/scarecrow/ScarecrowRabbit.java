package org.example.qaTransactionTeam.backEnd.scarecrow;

import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.backEnd.utils.BDas400;
import org.example.qaTransactionTeam.backEnd.utils.Configs1;
import org.example.qaTransactionTeam.backEnd.utils.RabbitMQ;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

class ScarecrowRabbit {

    private Logger logger = Logger.getLogger(ScarecrowRabbit.class);
    protected String merchant_id_tds;
    protected String merchant_id_av;
    protected String account_id_tds;
    protected String account_id_av;
    protected String portal_id;
    protected RabbitMQ rabbit;

    public void dataForMerchant(String acqid) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, URISyntaxException, KeyManagementException, TimeoutException, NoSuchAlgorithmException, IOException {
        BDas400.BDas400("ITMTST", Configs1.ITMTST_ALL_NAME, Configs1.ITMTST_ALL_PASSWORD);
        ResultSet res = BDas400.selectSQL("SELECT * FROM ITM22D.VMTACQSETT WHERE ACQID="+acqid+"");
        res.next();
        merchant_id_tds = res.getString("3DSMRCID").trim();
        merchant_id_av = res.getString("AVTMRCID").trim();
        account_id_tds = res.getString("ACCOUNTID").trim();
        account_id_av = res.getString("ACCOUNTIDAV").trim();
        portal_id = res.getString("PORTALID").trim();
        logger.info("Получены данные для acqid = "+acqid+": "+merchant_id_tds+" "+merchant_id_av+" "+account_id_tds+" "+account_id_av+" "+portal_id);
        BDas400.closeConn();

        if(rabbit == null){
            rabbit = new RabbitMQ("tsys");
        }
    }

    public void cleanQueue() throws IOException, URISyntaxException, TimeoutException, NoSuchAlgorithmException, KeyManagementException {
        if(rabbit == null){
            rabbit = new RabbitMQ("tsys");
        }
        rabbit.getChannel().queueDeclare("qasdf1", true,false,false,null);
        rabbit.getChannel().queuePurge("qasdf1");
        logger.info("Exchange qasdf1 - очищена");
    }

    public RabbitMQ getRabbit() {
        return rabbit;
    }
}
