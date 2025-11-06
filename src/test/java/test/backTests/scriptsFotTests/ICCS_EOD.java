package test.backTests.scriptsFotTests;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.helper.Uuid_helper;
import org.example.qaTransactionTeam.backEnd.utils.BDas400;
import org.example.qaTransactionTeam.backEnd.utils.RabbitMQ_http;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

@Test
public class ICCS_EOD extends BaseTest {

    String env = "ITMTST";

    public void eod() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        connBD();
        //управление EOD
        ResultSet res = BDas400.callProcedure("CALL FUIBGPL.ICCSETEODF (@EODFLAG  => 'N',@MINUTES  => 5,@WAITFLAG => '')");
        res.next();
        System.out.println(res.getString("MESSAGE"));
        closeBD();
    }

    public void eod_payhub(){
        RabbitMQ_http rabbitMQHttp = new RabbitMQ_http("setEOD","transfers.input");
        rabbitMQHttp.sendHttp("{\"eod\": true }");
    }


    private void connBD() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        BDas400.BDas400(env, "savchukv", "skipper11223");
    }


    private void closeBD() throws SQLException {
        BDas400.closeConn();
    }
}
