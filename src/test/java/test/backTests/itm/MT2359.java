package test.backTests.itm;


import org.example.qaTransactionTeam.backEnd.utils.BDpostgre;
import org.example.qaTransactionTeam.backEnd.utils.Configs;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.sql.*;

//функция для расчета доступного баланса по операциям А2С для торговцев
public class MT2359 {

    @BeforeTest
    public void connBD() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        BDpostgre.BDpostgre("vmtdb", Configs.POSTGRE_SQL_VMTDB_NAME, Configs.POSTGRE_SQL_VMTDB_PASSWORD);
    }

    @Test //select func_match_docs_trans()
    public void setStatusTest() throws SQLException {

        //Поиск Id для апдейта
        ResultSet findId = BDpostgre.selectSQL("select * from tb_sir_partner_bills where account_dt = 55356800 and stan_buff_doc = '0' limit 1");
        findId.next();
        String id = findId.getString("id");

        //Поиск CorelationId и RRN для апдейта
        ResultSet findCorrAndRRN = BDpostgre.selectSQL("select * from  tb_iso8583_0210 where de_000_btmp = '702000018E818000' and de_041_cati = 'KIE00001' limit 1");
        findCorrAndRRN.next();
        String correlation_id = findCorrAndRRN.getString("correlation_id");
        String rrn = findCorrAndRRN.getString("de_037_rrn");

        //Ставим всем статус false
        BDpostgre.updateSQL("update tb_iso8583_status set is_in_sirius = false");

        //Готовим место для записи rrn
        BDpostgre.updateSQL("update tb_sir_partner_bills set stan_buff_doc = '0'");

        //Устанавливаем rnn
        BDpostgre.updateSQL("update tb_sir_partner_bills set stan_buff_doc = '"+rrn+"' where id ="+id+"");

        //Вызываем функцию
        BDpostgre.callProcedure("select func_match_docs_trans()");

        //Проверяем успешный статус для corelationID
        ResultSet checkStatusTrue = BDpostgre.selectSQL("select * from tb_iso8583_status where tb_iso8583_0200_correlation_id = "+correlation_id+"");
        checkStatusTrue.next();
        Assert.assertEquals(checkStatusTrue.getString("is_in_sirius"),"t");

        //Проверяем остальные НЕ успешные статусы
        ResultSet checkStatusFalse = BDpostgre.selectSQL("select * from tb_iso8583_status where tb_iso8583_0200_correlation_id <> "+correlation_id+"");
        while (checkStatusFalse.next()){
            Assert.assertEquals(checkStatusFalse.getString("is_in_sirius"),"f");
        }

        //Возвращаем исходный результат
        BDpostgre.updateSQL("update tb_iso8583_status set is_in_sirius = false");
        BDpostgre.updateSQL("update tb_sir_partner_bills set stan_buff_doc = '0'");
    }

    @Test //select vmt.func_holds_hist()
    public void testHoldTransfer() throws SQLException {

        //Подготовка данных для переливки
        BDpostgre.updateSQL("delete from tb_iso8583_holds_hist where tb_itm_vmtsession_vtrrf > 190000000000");
        BDpostgre.updateSQL("delete from tb_iso8583_holds");
        BDpostgre.updateSQL("insert into tb_iso8583_holds(id ,tb_itm_vmtacqsett_acqid ,tb_itm_vmtopertyp_vtopertyid ,tb_itm_vmtc2cop_vmtc2copid ,tb_itm_vmtsession_vtamt ,tb_itm_vmtsession_vtrrf ,status ,created_at ) select * from tb_iso8583_holds_hist");
        BDpostgre.updateSQL("delete from tb_iso8583_holds where status = 'unhold'");

        //Подсчет количества записей Hold
        ResultSet findHold = BDpostgre.selectSQL("select count(*) from tb_iso8583_holds");
        findHold.next();
        String countHold = findHold.getString(1);

        //Подсчет количества записей Hold_hist
        ResultSet findHist = BDpostgre.selectSQL("select count(*) from tb_iso8583_holds_hist");
        findHist.next();
        String countHist = findHist.getString(1);

        //Поиск записей для Update
        ResultSet findId = BDpostgre.selectSQL("select * from tb_iso8583_holds order by id asc limit 4");
        String [] id = new String[4];
        String [] vtrrf = new String[4];
        for (int i = 0; i < 4;i++){
            findId.next();
            id[i] = findId.getString("id");
            vtrrf[i] = findId.getString("tb_itm_vmtsession_vtrrf");
        }

        //Update rrf
        for(int i = 0; i < 4; i++) {
            BDpostgre.updateSQL("update tb_iso8583_holds set tb_itm_vmtsession_vtrrf = \n" +
                    "(select "+vtrrf[i]+" + 90000000000 from tb_iso8583_holds where id = " + id[i] + ") \n" +
                    "where id = " + id[i] + "");
        }

        //Сохранение новых rrf
        ResultSet findNewId = BDpostgre.selectSQL("select * from tb_iso8583_holds order by id asc limit 4");
        String[] vtrrfNew = new String[4];
        for(int i = 0; i < 4; i++){
            findNewId.next();
            vtrrfNew[i] = findNewId.getString("tb_itm_vmtsession_vtrrf");
        }

        //Установка новых ID
        String[] idNew = {"99999996","99999997","99999998","99999999"};
        for(int i = 0; i < 4; i++){
            BDpostgre.updateSQL("update tb_iso8583_holds set id = "+idNew[i]+" where tb_itm_vmtsession_vtrrf = "+vtrrfNew[i]+"");
        }

        //Установка нужных статусов и rrf
        BDpostgre.updateSQL("update tb_iso8583_holds set tb_itm_vmtsession_vtrrf = "+vtrrfNew[0]+",status = 'unhold' where id = "+idNew[1]+"");
        BDpostgre.updateSQL("update tb_iso8583_holds set status = 'unhold' where id = "+idNew[2]+"");

        //Удаление дублей
        BDpostgre.updateSQL("delete from tb_iso8583_holds where tb_itm_vmtsession_vtrrf < 190000000000");

        //Запуск ХП select vmt.func_holds_hist()
        BDpostgre.callProcedure("select vmt.func_holds_hist()");

        //Проверка наличия измененных записей в Hold и Hold_hist
        ResultSet countHistNew = BDpostgre.selectSQL("select count(*) from tb_iso8583_holds_hist where tb_itm_vmtsession_vtrrf = "+vtrrfNew[0]+"");
        countHistNew.next();
        Assert.assertEquals(countHistNew.getString(1), "2");

        ResultSet allHistCount = BDpostgre.selectSQL("select count(*) from tb_iso8583_holds_hist");
        int countHistInt = Integer.parseInt(countHist) + 2;
        allHistCount.next();
        Assert.assertEquals(allHistCount.getInt(1),countHistInt);

        ResultSet allHoldCount = BDpostgre.selectSQL("select count(*) from tb_iso8583_holds");
        allHoldCount.next();
        Assert.assertEquals(allHoldCount.getInt(1), 2);


    }

    @Test //select func_count_agr_sum(2101,1)
    public void agregateSum1Flou() throws SQLException {

        //Удаление старых данных
        BDpostgre.updateSQL("delete from tb_iso8583_agr_sum where tb_itm_vmtacqsett_acqid = 2101");

        //Запуск ХП select func_count_agr_sum(2101,1)
        BDpostgre.callProcedure("select func_count_agr_sum(2101,1)");

        //Проверка результата
        ResultSet findSum = BDpostgre.selectSQL("select * from tb_iso8583_agr_sum where tb_itm_vmtacqsett_acqid = 2101 and tb_itm_vmtc2cop_vmtc2copid = 1");
        findSum.next();
        int argSumm = findSum.getInt("agr_sum");

        ResultSet findExpectedSum = BDpostgre.selectSQL("select sum(de_004_amnt_tran) from tb_iso8583_0210 where correlation_id between (select correlation_id from tb_iso8583_0200 where de_041_cati = 'KIE00001' and de_042_caic = '0200100FMT00022' order by correlation_id asc limit 1) and (select correlation_id from tb_iso8583_0200 where de_041_cati = 'KIE00001' and de_042_caic = '0200100FMT00022' order by correlation_id desc limit 1) and de_039_rc = '00'");
        findExpectedSum.next();
        Assert.assertEquals(argSumm,findExpectedSum.getInt(1));
    }

    @Test //select func_count_agr_sum(2101,1)
    public void agregateSum2Flou() throws SQLException {

        //Удаление старых данных
        BDpostgre.updateSQL("delete from tb_iso8583_agr_sum where tb_itm_vmtacqsett_acqid = 2101");

        //Запуск ХП select func_count_agr_sum()
        BDpostgre.callProcedure("select func_count_agr_sum(2101,1)");
        BDpostgre.callProcedure("select func_count_agr_sum(2101,22)");

        //Поиск актуальной суммы
        ResultSet findActualSumm = BDpostgre.selectSQL("select agr_sum from tb_iso8583_agr_sum where tb_itm_vmtacqsett_acqid = 2101 and tb_itm_vmtc2cop_vmtc2copid = 1");
        findActualSumm.next();
        int actualSumm = findActualSumm.getInt(1);

        //Замена lastId
        ResultSet findLastId = BDpostgre.selectSQL("select id from tb_iso8583_0200 where de_041_cati = 'KIE00001' and de_042_caic = '0200100FMT00022' order by id desc offset 3");
        findLastId.next();
        int lastIdNew = findLastId.getInt(1);

        BDpostgre.updateSQL("update tb_iso8583_agr_sum set tb_iso8583_0200_max_id = "+lastIdNew+" where tb_itm_vmtc2cop_vmtc2copid = 22");
        BDpostgre.updateSQL("update tb_iso8583_agr_sum set tb_iso8583_0200_max_id = 1 where tb_itm_vmtc2cop_vmtc2copid = 1");

        //Замена операции
        BDpostgre.updateSQL("update tb_iso8583_agr_sum set tb_itm_vmtc2cop_vmtc2copid = 1");

        //Запуск ХП select func_count_agr_sum()
        BDpostgre.callProcedure("select func_count_agr_sum(2101,1)");

        //Проверка результата
        ResultSet findDifferentSumm = BDpostgre.selectSQL("select sum(de_004_amnt_tran ) from tb_iso8583_0200 where de_041_cati = 'KIE00001' and de_042_caic = '0200100FMT00022' and id > "+lastIdNew+"");
        findDifferentSumm.next();
        int differentSumm = findDifferentSumm.getInt(1);

        ResultSet findNewSumm = BDpostgre.selectSQL("select agr_sum from tb_iso8583_agr_sum order by id desc limit 1");
        findNewSumm.next();
        int summNew = findNewSumm.getInt(1);

        Assert.assertEquals(summNew,actualSumm + differentSumm);
    }

    @AfterTest
    public void closeConn() throws SQLException {
        BDpostgre.getConn().close();
    }
}
