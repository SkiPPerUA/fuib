package test.backTests.prostir;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.prostir.EmissionCard;
import org.example.qaTransactionTeam.backEnd.utils.BDas400;
import org.example.qaTransactionTeam.backEnd.utils.Configs;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MT2890 extends BaseTest {
    // Эмиссия карт ПРОСТІР для Банков-партнеров. Обеспечение клиринга

    private String TRID;
    private String STATUS;
    private String ISMATCHED;
    private String ISINBKLOG;
    private String CODE;
    private EmissionCard emCard = new EmissionCard();

    @BeforeTest
    public void connDB() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        BDas400.BDas400("ITMTST", Configs.ITMTST_ALL_NAME, Configs.ITMTST_ALL_PASSWORD);
    }

    @AfterTest
    public void closeConn() throws SQLException {
        BDas400.closeConn();
    }

    @Test
    public void positiveIsNotMatched() throws SQLException {
        logStartTest("positiveIsNotMatched");

        //Проверка файлов - которые отдают статус 00200
        BDas400.callProcedureWithOutResult("CALL ITM22R.NPSBLNISS()");
        logger.info("Запущена процедура NPSBLNISS");

        String successId = findSuccessFile();
        emCard.deleteMatchedFiles(successId);

        emCard.changeMTI(1240,successId);
        createTestData(successId);
        BDas400.callProcedureWithOutResult("CALL ITM22R.NPSBLNISS()");

        String fileStatus = emCard.checkFileStatus(successId);
        Assert.assertEquals(fileStatus,"00200");

        findResult(successId);
        ResultSet res = BDas400.selectSQL("select TRID FROM itm22d.PRTRNTR0P WHERE TRFHID = "+successId+"");
        res.next();
        String expectedTRID = res.getString(1);
        Assert.assertEquals(TRID,expectedTRID);
        Assert.assertEquals(STATUS,"H");
        Assert.assertEquals(ISMATCHED,"F");
        Assert.assertEquals(ISINBKLOG,"T");
        Assert.assertEquals(CODE,"00");

        logFinishTest("positiveIsNotMatched");
    }

    @Test
    public void positiveIsMatched() throws SQLException {
        logStartTest("positiveIsMatched");

        //Проверка файлов - которые отдают статус 00200
        BDas400.callProcedureWithOutResult("CALL ITM22R.NPSBLNISS()");
        logger.info("Запущена процедура NPSBLNISS");

        String successId = findSuccessFile();

        //Делаем привязку
        emCard.addMatchedFiles(successId);

        emCard.changeMTI(1240,successId);
        createTestData(successId);
        BDas400.callProcedureWithOutResult("CALL ITM22R.NPSBLNISS()");

        String fileStatus = emCard.checkFileStatus(successId);
        Assert.assertEquals(fileStatus,"00200");

        findResult(successId);
        ResultSet res = BDas400.selectSQL("select TRID FROM itm22d.PRTRNTR0P WHERE TRFHID = "+successId+"");
        res.next();
        String expectedTRID = res.getString(1);
        Assert.assertEquals(TRID,expectedTRID);
        Assert.assertEquals(STATUS,"H");
        Assert.assertEquals(ISMATCHED,"T");
        Assert.assertEquals(ISINBKLOG,"T");
        Assert.assertEquals(CODE,"05");

        ResultSet res1 = BDas400.selectSQL("SELECT * FROM ASID144402.ZTRANS0P WHERE \"TRRRF#\" = (select \"trrrn#\" FROM itm22d.PRTRNTR0P WHERE TRFHID = "+successId+")");
        res1.next();
        String TRRSPC = res1.getString("TRRSPC");
        Assert.assertEquals(TRRSPC,"52");
        logFinishTest("positiveIsMatched");
    }

    @Test
    public void negativeIsMatched01() throws SQLException {
        logStartTest("negativeIsMatched01 - MTI != 1240");

        //Проверка файлов - которые отдают статус 00200
        BDas400.callProcedureWithOutResult("CALL ITM22R.NPSBLNISS()");
        logger.info("Запущена процедура NPSBLNISS");

        String successId = findSuccessFile();

        //Делаем привязку
        emCard.addMatchedFiles(successId);

        emCard.changeMTI(1241,successId);
        createTestData(successId);
        BDas400.callProcedureWithOutResult("CALL ITM22R.NPSBLNISS()");

        String fileStatus = emCard.checkFileStatus(successId);
        Assert.assertEquals(fileStatus,"00206");

        //Возвращаем те стовые данные в исходное положение (что бы не испортить данные)
        BDas400.updateSQL("UPDATE itm22d.PRFHDFH0P SET FHFLER = '00200' WHERE FHKYID = "+successId+"");
        emCard.changeMTI(1240,successId);

        findResult(successId);
        ResultSet res = BDas400.selectSQL("select TRID FROM itm22d.PRTRNTR0P WHERE TRFHID = "+successId+"");
        res.next();
        String expectedTRID = res.getString(1);
        Assert.assertEquals(TRID,expectedTRID);
        Assert.assertEquals(STATUS,"E");
        Assert.assertEquals(ISMATCHED,"T");
        Assert.assertEquals(ISINBKLOG,"F");
        Assert.assertEquals(CODE,"01");

        ResultSet res1 = BDas400.selectSQL("SELECT * FROM ASID144402.ZTRANS0P WHERE \"TRRRF#\" = (select \"trrrn#\" FROM itm22d.PRTRNTR0P WHERE TRFHID = "+successId+")");
        res1.next();
        String TRRSPC = res1.getString("TRRSPC");
        Assert.assertEquals(TRRSPC,"52");
        logFinishTest("negativeIsMatched01 - MTI != 1240");
    }

    @Test
    public void negativeIsNotMatched01() throws SQLException {
        logStartTest("negativeIsNotMatched01 - MTI != 1240");

        //Проверка файлов - которые отдают статус 00200
        BDas400.callProcedureWithOutResult("CALL ITM22R.NPSBLNISS()");
        logger.info("Запущена процедура NPSBLNISS");

        String successId = findSuccessFile();

        //Удаляем привязку (если она была)
        emCard.deleteMatchedFiles(successId);

        emCard.changeMTI(1241,successId);
        createTestData(successId);
        BDas400.callProcedureWithOutResult("CALL ITM22R.NPSBLNISS()");

        String fileStatus = emCard.checkFileStatus(successId);
        Assert.assertEquals(fileStatus,"00206");

        //Возвращаем те стовые данные в исходное положение (что бы не испортить данные)
        BDas400.updateSQL("UPDATE itm22d.PRFHDFH0P SET FHFLER = '00200' WHERE FHKYID = "+successId+"");
        emCard.changeMTI(1240,successId);

        findResult(successId);
        ResultSet res = BDas400.selectSQL("select TRID FROM itm22d.PRTRNTR0P WHERE TRFHID = "+successId+"");
        res.next();
        String expectedTRID = res.getString(1);
        Assert.assertEquals(TRID,expectedTRID);
        Assert.assertEquals(STATUS,"E");
        Assert.assertEquals(ISMATCHED,"F");
        Assert.assertEquals(ISINBKLOG,"F");
        Assert.assertEquals(CODE,"01");

        logFinishTest("negativeIsNotMatched01 - MTI != 1240");
    }

    @Test
    public void negativeIsMatched08() throws SQLException {
        logStartTest("negativeIsMatched08 - тип транзакции не корректен");

        //Проверка файлов - которые отдают статус 00200
        BDas400.callProcedureWithOutResult("CALL ITM22R.NPSBLNISS()");
        logger.info("Запущена процедура NPSBLNISS");

        String successId = findSuccessFile();

        //Делаем привязку
        emCard.addMatchedFiles(successId);

        emCard.changeMTI(1240,successId);
        ResultSet res = BDas400.selectSQL("select TRID FROM itm22d.PRTRNTR0P WHERE TRFHID = "+successId+"");
        res.next();
        String expectedTRID = res.getString(1);
        ResultSet res1 = BDas400.selectSQL("SELECT TRTRTY FROM itm22d.PRTRNTR0P WHERE TRID = "+expectedTRID+"");
        res1.next();
        String oldTRTRTY = res1.getString("TRTRTY");
        BDas400.updateSQL("UPDATE itm22d.PRTRNTR0P SET TRTRTY = '99' WHERE TRID = "+expectedTRID+"");
        createTestData(successId);
        BDas400.callProcedureWithOutResult("CALL ITM22R.NPSBLNISS()");


        String fileStatus = emCard.checkFileStatus(successId);
        Assert.assertEquals(fileStatus,"00206");

        //Возвращаем те стовые данные в исходное положение (что бы не испортить данные)
        BDas400.updateSQL("UPDATE itm22d.PRTRNTR0P SET TRTRTY = '"+oldTRTRTY+"' WHERE TRID = "+expectedTRID+"");
        BDas400.updateSQL("UPDATE itm22d.PRFHDFH0P SET FHFLER = '00200' WHERE FHKYID = "+successId+"");

        findResult(successId);
        Assert.assertEquals(TRID,expectedTRID);
        Assert.assertEquals(STATUS,"E");
        Assert.assertEquals(ISMATCHED,"T");
        Assert.assertEquals(ISINBKLOG,"F");
        Assert.assertEquals(CODE,"08");

        ResultSet res2 = BDas400.selectSQL("SELECT * FROM ASID144402.ZTRANS0P WHERE \"TRRRF#\" = (select \"trrrn#\" FROM itm22d.PRTRNTR0P WHERE TRFHID = "+successId+")");
        res2.next();
        String TRRSPC = res2.getString("TRRSPC");
        Assert.assertEquals(TRRSPC,"52");
        logFinishTest("negativeIsMatched08 - тип транзакции не корректен");
    }

    @Test
    public void negativeIsNotMatched08() throws SQLException {
        logStartTest("negativeIsNotMatched08 - тип транзакции не корректен");

        //Проверка файлов - которые отдают статус 00200
        BDas400.callProcedureWithOutResult("CALL ITM22R.NPSBLNISS()");
        logger.info("Запущена процедура NPSBLNISS");

        String successId = findSuccessFile();

        //Удаляем привязку (если она была)
        emCard.deleteMatchedFiles(successId);

        emCard.changeMTI(1240,successId);
        ResultSet res = BDas400.selectSQL("select TRID FROM itm22d.PRTRNTR0P WHERE TRFHID = "+successId+"");
        res.next();
        String expectedTRID = res.getString(1);
        ResultSet res1 = BDas400.selectSQL("SELECT TRTRTY FROM itm22d.PRTRNTR0P WHERE TRID = "+expectedTRID+"");
        res1.next();
        String oldTRTRTY = res1.getString("TRTRTY");
        BDas400.updateSQL("UPDATE itm22d.PRTRNTR0P SET TRTRTY = '99' WHERE TRID = "+expectedTRID+"");
        createTestData(successId);
        BDas400.callProcedureWithOutResult("CALL ITM22R.NPSBLNISS()");

        String fileStatus = emCard.checkFileStatus(successId);
        Assert.assertEquals(fileStatus,"00206");

        //Возвращаем те стовые данные в исходное положение (что бы не испортить данные)
        BDas400.updateSQL("UPDATE itm22d.PRTRNTR0P SET TRTRTY = '"+oldTRTRTY+"' WHERE TRID = "+expectedTRID+"");
        BDas400.updateSQL("UPDATE itm22d.PRFHDFH0P SET FHFLER = '00200' WHERE FHKYID = "+successId+"");

        findResult(successId);
        Assert.assertEquals(TRID,expectedTRID);
        Assert.assertEquals(STATUS,"E");
        Assert.assertEquals(ISMATCHED,"F");
        Assert.assertEquals(ISINBKLOG,"F");
        Assert.assertEquals(CODE,"08");

        logFinishTest("negativeIsNotMatched08 - тип транзакции не корректен");
    }

    public String findSuccessFile() throws SQLException {

        //Поиск ID файла
        ResultSet res = BDas400.selectSQL("select FHKYID \n" +
                "FROM itm22d.PRFHDFH0P p \n" +
                "WHERE  p.FHINTY = 'DIRECT'\n" +
                "AND EXISTS (SELECT 1 FROM itm22d.PRTRNTR0P pp WHERE pp.TRFHID = p.FHKYID)\n" +
                "AND p.FHFLDR = 'IN'\n" +
                "AND FHFLER = 00200 LIMIT 1");

        res.next();
        String id = res.getString(1);
        logger.info("Получено ID файла = "+id);
        return id;
    }

    public void createTestData(String idFile) throws SQLException {
        BDas400.updateSQL("DELETE FROM ITM22D.NPSBLNTBL WHERE FILEID = "+idFile+"");
        BDas400.updateSQL("UPDATE itm22d.PRFHDFH0P SET FHFLER = '00000' WHERE FHKYID = "+idFile+"");
        logger.info("Тестовые данные готовые для - "+idFile);
    }

    private void findResult(String idFile) throws SQLException {
        ResultSet res = BDas400.selectSQL("SELECT * FROM ITM22D.NPSBLNTBL WHERE FILEID = "+idFile+"");
        res.next();

        TRID = res.getString("TRID");
        logger.info("TRID = "+TRID);
        STATUS = res.getString("STATUS");
        logger.info("STATUS = "+STATUS);
        ISMATCHED = res.getString("ISMATCHED");
        logger.info("ISMATCHED = "+ISMATCHED);
        ISINBKLOG = res.getString("ISINBKLOG");
        logger.info("ISINBKLOG = "+ISINBKLOG);
        CODE = res.getString("CODE");
        logger.info("CODE = "+CODE);

    }

}
