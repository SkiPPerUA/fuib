package test.backTests.beyonder;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.notificationBP.NotificationBP;
import org.example.qaTransactionTeam.backEnd.utils.BDpostgre;
import org.example.qaTransactionTeam.backEnd.utils.Configs1;
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

public class DublNotifications extends BaseTest{

    private String stanNotif = "999999999999";
    private String ntistp = "043";
    private NotificationBP notification = new NotificationBP();

    @BeforeTest
    public void connBD() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        BDpostgre.BDpostgre("beyonderdb", Configs1.POSTGRE_SQL_BEYONDER_NAME, Configs1.POSTGRE_SQL_BEYONDER_PASSWORD);
    }

    @Test
    public void positiveDublTodayNotifications() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, KeyManagementException, TimeoutException, NoSuchAlgorithmException, IOException, URISyntaxException, InterruptedException {
        logger.info("Старт теста - positiveDublTodayNotifications");
        //Удаление нотификации (если она есть в БД)
        notification.deleteEvent(stanNotif);
        //Проверка количества записей
        Assert.assertEquals(countStanInNotificationProcessed(stanNotif),0);
        Assert.assertEquals(countStanInNotificationSized(stanNotif),0);
        //Создание нотификации
        notification.createEvent("FX",stanNotif,"D", ntistp);
        //Проверка количества записей
        Assert.assertEquals(countStanInNotificationProcessed(stanNotif),1);
        Assert.assertEquals(countStanInNotificationSized(stanNotif),1);
        //Создание повторной нотификации
        notification.createEvent("FX",stanNotif,"D", ntistp);
        //Проверка количества записей
        Assert.assertEquals(countStanInNotificationProcessed(stanNotif),1);
        Assert.assertEquals(countStanInNotificationSized(stanNotif),1);
        logger.info("Конец теста - positiveDublTodayNotifications");
    }

    @Test
    public void negativeDublAfter1Day() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, KeyManagementException, TimeoutException, NoSuchAlgorithmException, IOException, URISyntaxException, InterruptedException {
        logger.info("Старт теста - negativeDublAfter1Day");
        //Удаление нотификации (если она есть в БД)
        notification.deleteEvent(stanNotif);
        //Проверка количества записей
        Assert.assertEquals(countStanInNotificationProcessed(stanNotif),0);
        Assert.assertEquals(countStanInNotificationSized(stanNotif),0);
        //Создание нотификации
        notification.createEvent("FX",stanNotif,"D", ntistp);
        //Проверка количества записей
        Assert.assertEquals(countStanInNotificationProcessed(stanNotif),1);
        Assert.assertEquals(countStanInNotificationSized(stanNotif),1);
        //Update event что бы "прошло" более одного дня
        BDpostgre.updateSQL("update notification_sized set processed_date = '2021-04-26 12:48:08' where ntstan = '"+stanNotif+"'");
        //Создание повторной нотификации
        notification.createEvent("FX",stanNotif,"D", ntistp);
        //Проверка количества записей
        Assert.assertEquals(countStanInNotificationProcessed(stanNotif),2);
        Assert.assertEquals(countStanInNotificationSized(stanNotif),2);

        logger.info("Конец теста - negativeDublAfter1Day");
    }

    @Test
    public void negativeDublActnA() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, InterruptedException, NoSuchAlgorithmException, KeyManagementException, TimeoutException, URISyntaxException, IOException {

        logger.info("Старт теста - negativeDublActnA");
        //Удаление нотификации (если она есть в БД)
        notification.deleteEvent(stanNotif);
        //Проверка количества записей
        Assert.assertEquals(countStanInNotificationProcessed(stanNotif),0);
        Assert.assertEquals(countStanInNotificationSized(stanNotif),0);
        //Создание нотификации
        notification.createEvent("FX",stanNotif,"A", ntistp);
        //Проверка количества записей
        Assert.assertEquals(countStanInNotificationProcessed(stanNotif),1);
        Assert.assertEquals(countStanInNotificationSized(stanNotif),0);
        //Создание повторной нотификации
        notification.createEvent("FX",stanNotif,"A", ntistp);
        //Проверка количества записей
        Assert.assertEquals(countStanInNotificationProcessed(stanNotif),2);
        Assert.assertEquals(countStanInNotificationSized(stanNotif),0);

        logger.info("Конец теста - negativeDublActnA");
    }

    @Test
    public void negativeDublDifferentTrty() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, InterruptedException, NoSuchAlgorithmException, KeyManagementException, TimeoutException, URISyntaxException, IOException {

        logger.info("Старт теста - negativeDublDifferentTrty");
        //Удаление нотификации (если она есть в БД)
        notification.deleteEvent(stanNotif);
        //Проверка количества записей
        Assert.assertEquals(countStanInNotificationProcessed(stanNotif),0);
        Assert.assertEquals(countStanInNotificationSized(stanNotif),0);
        //Создание нотификации
        notification.createEvent("FX",stanNotif,"D", ntistp);
        //Проверка количества записей
        Assert.assertEquals(countStanInNotificationProcessed(stanNotif),1);
        Assert.assertEquals(countStanInNotificationSized(stanNotif),1);
        //Создание повторной нотификации
        notification.createEvent("OC",stanNotif,"D", ntistp);
        //Проверка количества записей
        Assert.assertEquals(countStanInNotificationProcessed(stanNotif),2);
        Assert.assertEquals(countStanInNotificationSized(stanNotif),2);

        logger.info("Конец теста - negativeDublDifferentTrty");
    }

    @Test
    public void negativeDublDifferentPartner() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, InterruptedException, NoSuchAlgorithmException, KeyManagementException, TimeoutException, URISyntaxException, IOException {

        logger.info("Старт теста - negativeDublDifferentPartner");
        //Удаление нотификации (если она есть в БД)
        notification.deleteEvent(stanNotif);
        //Проверка количества записей
        Assert.assertEquals(countStanInNotificationProcessed(stanNotif),0);
        Assert.assertEquals(countStanInNotificationSized(stanNotif),0);
        //Создание нотификации
        notification.createEvent("FX",stanNotif,"D", "005");
        //Проверка количества записей
        Assert.assertEquals(countStanInNotificationProcessed(stanNotif),1);
        Assert.assertEquals(countStanInNotificationSized(stanNotif),1);
        //Создание повторной нотификации
        notification.createEvent("FX",stanNotif,"D", "034");
        //Проверка количества записей
        Assert.assertEquals(countStanInNotificationProcessed(stanNotif),2);
        Assert.assertEquals(countStanInNotificationSized(stanNotif),2);

        logger.info("Конец теста - negativeDublDifferentPartner");
    }

    @AfterTest
    public void closeBD() throws SQLException {
        BDpostgre.closeConn();
    }

    private int countStanInNotificationProcessed(String stan) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        ResultSet res = BDpostgre.selectSQL("select count(*) from notification_processed where ntstan = '"+stan+"'");
        res.next();
        return Integer.parseInt(res.getString(1));
    }

    private int countStanInNotificationSized(String stan) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        ResultSet res = BDpostgre.selectSQL("select count(*) from notification_sized where ntstan = '"+stan+"'");
        res.next();
        return Integer.parseInt(res.getString(1));
    }
}
