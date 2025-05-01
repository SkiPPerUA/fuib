package test.backTests.productCatalog;


import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.utils.BDas400;
import org.junit.Assert;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MT2375 extends BaseTest {
    //Загрузка/Обновление курсов в Arksys

    @BeforeTest
    public void connToBase() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        BDas400.BDas400("ITMTST", "asgate", "continent");
    }

    @Test
    public void testUpdateCurr() throws SQLException {

        logStartTest("testUpdateCurr");
        //Поиск данных для теста
        ResultSet findCurc = BDas400.selectSQL("SELECT CCCURC FROM LVFUIBCURR.TTMPCUR");
        findCurc.next();

        String ccurc = findCurc.getString("CCCURC");
        logger.info("CCCURC = "+ccurc);
        String oldCCRATE = "3.999900000";
        String oldSSRATE = "4                                       ";
        String newCCRATE = "3.777700000";
        String newSSRATE = "4.111100000                             ";

        //Update даты - для дальнейшей обработки
        changeData(ccurc);

        //Установка "старых" курсов
        BDas400.updateSQL("UPDATE LVFUIBCURR.TTMPCUR SET SSRATE = "+oldSSRATE+" ,CCRATE = "+oldCCRATE+" WHERE CCCURC = '"+ccurc+"'");

        //Загрузка старых курсов
        BDas400.callProcedureWithOutResult("CALL LVFUIBCURR.ZCCONV0PSP()");

        //Проверка старых курсов
        checkCurr(oldCCRATE,oldSSRATE, ccurc);
        logger.info("Старые курсы установлены");

        //Update "новых" курсов
        BDas400.updateSQL("UPDATE LVFUIBCURR.TTMPCUR SET SSRATE = "+newSSRATE+" ,CCRATE = "+newCCRATE+" WHERE CCCURC = '"+ccurc+"'");

        //Загрузка "новых" курсов
        BDas400.callProcedureWithOutResult("CALL LVFUIBCURR.ZCCONV0PSP()");

        //Проверка "новых" курсов
        checkCurr(newCCRATE,newSSRATE, ccurc);
        logger.info("Новые курсы установлены");
        logFinishTest("testUpdateCurr");

    }

    @Test
    public void testFlag_1() throws SQLException {
        logStartTest("testFlag_1");

        //Поиск данных для теста
        ResultSet findCurc = BDas400.selectSQL("SELECT * FROM LVFUIBCURR.TTMPCUR");
        findCurc.next();
        String ccurc = findCurc.getString("CCCURC");
        logger.info("CCCURC = "+ccurc);

        //Update даты - для дальнейшей обработки
        changeData(ccurc);

        //Загрузка старых курсов
        BDas400.callProcedureWithOutResult("CALL LVFUIBCURR.ZCCONV0PSP()");
        BDas400.callProcedureWithOutResult("CALL LVFUIBCURR.ZCCONV0PSP()");

        //Проверка флагов
        checkFlag("1",ccurc);
        logFinishTest("testFlag_1");
    }

    @Test
    public void testFlag_2() throws SQLException {
        logStartTest("testFlag_2");

        //Поиск данных для теста
        ResultSet findCurc = BDas400.selectSQL("SELECT * FROM LVFUIBCURR.TTMPCUR");
        findCurc.next();
        String ccurc = findCurc.getString("CCCURC");
        logger.info("CCCURC = "+ccurc);

        //Update даты - для дальнейшей обработки
        changeData(ccurc);

        //Загрузка старых курсов
        BDas400.callProcedureWithOutResult("CALL LVFUIBCURR.ZCCONV0PSP()");

        //Изменение даты курсов
        BDas400.updateSQL("UPDATE LVFUIBCURR.LRALNBUR SET CCDATE = 1201017 WHERE CCCURC = '"+ccurc+"'");

        //Загрузка новых курсов за текущий день
        BDas400.callProcedureWithOutResult("CALL LVFUIBCURR.ZCCONV0PSP()");

        //Проверка флагов
        checkFlag("2",ccurc);
        logFinishTest("testFlag_2");
    }

    @Test
    public void testFlag_3() throws SQLException {
        logStartTest("testFlag_3");

        //Поиск данных для теста
        ResultSet findCurc = BDas400.selectSQL("SELECT * FROM LVFUIBCURR.TTMPCUR");
        findCurc.next();
        String ccurc = findCurc.getString("CCCURC");
        logger.info("CCCURC = "+ccurc);

        //Update даты - для дальнейшей обработки
        changeData(ccurc);

        //Загрузка старых курсов
        BDas400.callProcedureWithOutResult("CALL LVFUIBCURR.ZCCONV0PSP()");

        //Удаление данных
        BDas400.updateSQL("DELETE FROM LVFUIBCURR.LRALNBUR WHERE CCCURC = '"+ccurc+"'");

        //Загрузка новых курсов
        BDas400.callProcedureWithOutResult("CALL LVFUIBCURR.ZCCONV0PSP()");

        //Проверка флагов
        checkFlag("3",ccurc);
        logFinishTest("testFlag_3");

    }

    @Test
    public void testAddCurr() throws SQLException {
        logStartTest("testAddCurr");

        //Поиск данных для теста
        ResultSet findCurc = BDas400.selectSQL("SELECT * FROM LVFUIBCURR.TTMPCUR");
        findCurc.next();
        String ccurc = findCurc.getString("CCCURC");
        String ccrate = findCurc.getString("CCRATE");
        String ssrate = findCurc.getString("SSRATE");
        logger.info("CCCURC = "+ccurc);

        //Удаление курсов
        BDas400.updateSQL("DELETE FROM asid144402.ZCCONV0P WHERE CCCURC = '"+ccurc+"'");
        BDas400.updateSQL("DELETE FROM ASID144402.addcph0p WHERE PHNAME = '"+ccurc+"' AND PHLNGC = '01'");
        BDas400.updateSQL("DELETE FROM LVFUIBCURR.LRALNBUR WHERE CCCURC = '"+ccurc+"'");

        //Загрузка старых курсов
        BDas400.callProcedureWithOutResult("CALL LVFUIBCURR.ZCCONV0PSP()");

        //Проверка наличие курсов
        ResultSet res1 = BDas400.selectSQL("SELECT * FROM asid144402.ZCCONV0P WHERE CCCURC = '"+ccurc+"'");
        res1.next();
        Assert.assertEquals(ccrate, res1.getString("CCRATE"));
        ResultSet res2 = BDas400.selectSQL("SELECT * FROM ASID144402.addcph0p WHERE PHNAME = '"+ccurc+"' AND PHLNGC = '01'");
        res2.next();
        Assert.assertEquals(ssrate.trim(), res2.getString("PHPHRS").trim());
        ResultSet res3 = BDas400.selectSQL("SELECT * FROM LVFUIBCURR.LRALNBUR WHERE CCCURC = '"+ccurc+"'");
        res3.next();
        Assert.assertEquals(ccrate, res3.getString("CCRATE"));
        logFinishTest("testAddCurr");

    }

    private void checkCurr(String cc,String ss, String ccurc) throws SQLException {
        ResultSet checkOldCCRATE_ZCCONV0P = BDas400.selectSQL("SELECT CCRATE FROM asid144402.ZCCONV0P WHERE CCCURC = '"+ccurc+"'ORDER BY CCDATE DESC");
        checkOldCCRATE_ZCCONV0P.next();
        Assert.assertEquals("Курсы не установлены (asid144402.ZCCONV0P)",cc, checkOldCCRATE_ZCCONV0P.getString("CCRATE"));

        ResultSet checkOldPHPHRS_addcph0p = BDas400.selectSQL("SELECT PHPHRS FROM ASID144402.addcph0p WHERE PHNAME = '"+ccurc+"' AND PHLNGC = '01'");
        checkOldPHPHRS_addcph0p.next();
        Assert.assertEquals("Курсы не установлены (ASID144402.addcph0p)",ss, checkOldPHPHRS_addcph0p.getString("PHPHRS"));

        ResultSet checkOldCCRATE_LRALNBUR = BDas400.selectSQL("SELECT CCRATE FROM LVFUIBCURR.LRALNBUR WHERE CCCURC = '"+ccurc+"'");
        checkOldCCRATE_LRALNBUR.next();
        Assert.assertEquals("Курсы не установлены (LVFUIBCURR.LRALNBUR)",cc, checkOldCCRATE_LRALNBUR.getString("CCRATE"));

    }

    private void checkFlag(String flag, String ccurc) throws SQLException {
        ResultSet res = BDas400.selectSQL("SELECT * FROM LVFUIBCURR.LRALNBUR WHERE CCCURC = '"+ccurc+"'");
        res.next();
        Assert.assertEquals("Флаг НЕ корректен",flag,res.getString("FLGNEW"));
    }

    private void changeData(String ccurc) throws SQLException {
        ResultSet res = BDas400.selectSQL("SELECT * FROM LVFUIBCURR.TTMPCUR WHERE CCCURC = '"+ccurc+"'");
        res.next();
        String actualCCDATE = res.getString("CCDATE");
        logger.info("CCDATE до апдейта = "+actualCCDATE);
        int i = Integer.parseInt(actualCCDATE);
        i++;
        BDas400.updateSQL("UPDATE LVFUIBCURR.TTMPCUR SET CCDATE = "+i+" WHERE CCCURC = '"+ccurc+"'");
        logger.info("CCDATE после апдейта = "+i);

    }

    @AfterTest
    public void disconnBase() throws SQLException {
        BDas400.closeConn();
    }
}
