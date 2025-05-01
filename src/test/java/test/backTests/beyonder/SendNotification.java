package test.backTests.beyonder;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.notificationBP.NotificationBP;
import org.example.qaTransactionTeam.backEnd.utils.BDpostgre;
import org.example.qaTransactionTeam.backEnd.utils.Configs;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

public class SendNotification extends BaseTest {

    private String trty = "FX";
    private String stan = "999999999999";
    private String ntispt = "043";
    NotificationBP not = new NotificationBP();

    @BeforeTest
    public void connDB() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        BDpostgre.BDpostgre("beyonderdb", Configs.POSTGRE_SQL_BEYONDER_NAME, Configs.POSTGRE_SQL_BEYONDER_PASSWORD);

    }

    @Test
    public void positiveSendNotification() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, InterruptedException, NoSuchAlgorithmException, KeyManagementException, TimeoutException, URISyntaxException, IOException {

        logStartTest("positiveSendNotification");
        not.deleteEvent(stan);
        not.createEvent(trty, stan, "A", ntispt);

        Assert.assertEquals(checkNotificationProcessed().getString("ntispt"),ntispt);

        ResultSet res = BDpostgre.selectSQL("select count(*) from notification_processed where ntstan = '"+stan+"'");
        res.next();
        Assert.assertEquals(res.getInt(1),1);

        ResultSet res1 = BDpostgre.selectSQL("select count(*) from notification_sized where ntstan = '"+stan+"'");
        res1.next();
        Assert.assertEquals(res1.getInt(1),0);
        logFinishTest("positiveSendNotification");
    }

    @Test
    public void negativeSendNotification() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, InterruptedException, NoSuchAlgorithmException, KeyManagementException, TimeoutException, URISyntaxException, IOException {

        logStartTest("negativeSendNotification");
        not.deleteEvent(stan);
        not.createEvent(trty, stan, "D", ntispt);

        Assert.assertEquals(checkNotificationProcessed().getString("ntispt"),ntispt);
        Assert.assertEquals(checkNotificationSized().getString("ntispt"),ntispt);
        Assert.assertEquals(checkNotificationSized().getString("nttrty"),trty);

        ResultSet res = BDpostgre.selectSQL("select count(*) from notification_processed where ntstan = '"+stan+"'");
        res.next();
        Assert.assertEquals(res.getInt(1),1);

        ResultSet res1 = BDpostgre.selectSQL("select count(*) from notification_sized where ntstan = '"+stan+"'");
        res1.next();
        Assert.assertEquals(res1.getInt(1),1);
        logFinishTest("negativeSendNotification");
    }

    @AfterTest
    public void closeConn() throws SQLException {
            BDpostgre.closeConn();
    }

    private ResultSet checkNotificationProcessed() throws SQLException {
            ResultSet res = BDpostgre.selectSQL("select * from notification_processed where ntstan = '"+stan+"'");
            res.next();
            return res;
        }

    private ResultSet checkNotificationSized() throws SQLException {
        ResultSet res = BDpostgre.selectSQL("select * from notification_sized where ntstan = '"+stan+"'");
        res.next();
        return res;
    }
}
