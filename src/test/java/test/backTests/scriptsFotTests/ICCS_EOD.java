package test.backTests.scriptsFotTests;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.utils.BDas400;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

@Test
public class ICCS_EOD extends BaseTest {

    String env = "ITMTST";

    public void eod() throws SQLException {

        //управление EOD
        ResultSet res = BDas400.callProcedure("CALL FUIBGPL.ICCSETEODF (@EODFLAG  => 'N',@MINUTES  => 5,@WAITFLAG => '')");
        res.next();
        System.out.println(res.getString("MESSAGE"));
    }

    @BeforeTest
    public void connBD() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        BDas400.BDas400(env, "savchukv", "skipper11223");
    }

    @AfterTest
    public void closeBD() throws SQLException {
        BDas400.closeConn();
    }
}
