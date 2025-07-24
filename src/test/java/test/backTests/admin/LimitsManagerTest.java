package test.backTests.admin;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.backEnd.admin.Limits;
import org.example.qaTransactionTeam.backEnd.utils.BDpostgre;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

@Test
public class LimitsManagerTest extends BaseTest {

    Limits limits = new Limits();
    JSONArray array;
    JSONObject json;
    String body = "";
    int count_old;
    int count_new;

    public void positive_selfLimits() {
            limits.createSelfLimits("{\n" +
                    "\"max_amount\": 100,\n" +
                    "\"min_amount\": 70,\n" +
                    "\"card_in_use\": 999,\n" +
                    "\"account_in_use\": 999,\n" +
                    "\"active\": true\n" +
                    "}");
    }

    public void testCreate(){
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"FUIB_TO_FUIB\",\n" +
                "  \"transaction_amount\": 1100,\n" +
                "  \"daily_amount\": 2200,\n" +
                "  \"daily_quantity\": 5,\n" +
                "  \"monthly_amount\": 4400,\n" +
                "  \"monthly_quantity\": 10\n" +
                "}";
        limits.createLimits(body);
    }

    public void createLimits_positive(){
        logStartTest("createLimits_positive_PROFIT_DIRECT_MONEY_TRANS");
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_old = array.length();
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.createLimits(body);
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_new = array.length();
        Assert.assertEquals(count_new, count_old+1, "Количесво лимитов НЕ увеличилось на 1");
        checkLimitsData("PROFIT", "DIRECT_MONEY_TRANS", 11, 22, 33, 44, 55);
        logFinishTest("createLimits_positive_PROFIT_DIRECT_MONEY_TRANS");
        logStartTest("createLimits_positive_PROFIT_FUIB_TO_FUIB");
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_old = array.length();
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"FUIB_TO_FUIB\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.createLimits(body);
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_new = array.length();
        Assert.assertEquals(count_new, count_old+1, "Количесво лимитов НЕ увеличилось на 1");
        checkLimitsData("PROFIT", "FUIB_TO_FUIB", 11, 22, 33, 44, 55);
        logFinishTest("createLimits_positive_PROFIT_FUIB_TO_FUIB");
        logStartTest("createLimits_positive_PROFIT_EXTERNAL_BANK");
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_old = array.length();
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"EXTERNAL_BANK\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.createLimits(body);
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_new = array.length();
        Assert.assertEquals(count_new, count_old+1, "Количесво лимитов НЕ увеличилось на 1");
        checkLimitsData("PROFIT", "EXTERNAL_BANK", 11, 22, 33, 44, 55);
        logFinishTest("createLimits_positive_PROFIT_EXTERNAL_BANK");
        logStartTest("createLimits_positive_PROFIT_FULL_IDENT_DIRECT");
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_old = array.length();
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"FULL_IDENT_DIRECT\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.createLimits(body);
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_new = array.length();
        Assert.assertEquals(count_new, count_old+1, "Количесво лимитов НЕ увеличилось на 1");
        checkLimitsData("PROFIT", "FULL_IDENT_DIRECT", 11, 22, 33, 44, 55);
        logFinishTest("createLimits_positive_PROFIT_FULL_IDENT_DIRECT");
        logStartTest("createLimits_positive_PROFIT_FULL_IDENT_FUIB_TO_FUIB");
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_old = array.length();
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"FULL_IDENT_FUIB_TO_FUIB\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.createLimits(body);
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_new = array.length();
        Assert.assertEquals(count_new, count_old+1, "Количесво лимитов НЕ увеличилось на 1");
        checkLimitsData("PROFIT", "FULL_IDENT_FUIB_TO_FUIB", 11, 22, 33, 44, 55);
        logFinishTest("createLimits_positive_PROFIT_FULL_IDENT_FUIB_TO_FUIB");
        logStartTest("createLimits_positive_EXPENSE_DIRECT_MONEY_TRANS");
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_old = array.length();
        body = "{\n" +
                "  \"direction\": \"EXPENSE\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.createLimits(body);
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_new = array.length();
        Assert.assertEquals(count_new, count_old+1, "Количесво лимитов НЕ увеличилось на 1");
        checkLimitsData("EXPENSE", "DIRECT_MONEY_TRANS", 11, 22, 33, 44, 55);
        logFinishTest("createLimits_positive_EXPENSE_DIRECT_MONEY_TRANS");
        logStartTest("createLimits_positive_EXPENSE_FUIB_TO_FUIB");
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_old = array.length();
        body = "{\n" +
                "  \"direction\": \"EXPENSE\",\n" +
                "  \"kind\": \"FUIB_TO_FUIB\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.createLimits(body);
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_new = array.length();
        Assert.assertEquals(count_new, count_old+1, "Количесво лимитов НЕ увеличилось на 1");
        checkLimitsData("EXPENSE", "FUIB_TO_FUIB", 11, 22, 33, 44, 55);
        logFinishTest("createLimits_positive_EXPENSE_FUIB_TO_FUIB");
        logStartTest("createLimits_positive_EXPENSE_EXTERNAL_BANK");
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_old = array.length();
        body = "{\n" +
                "  \"direction\": \"EXPENSE\",\n" +
                "  \"kind\": \"EXTERNAL_BANK\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.createLimits(body);
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_new = array.length();
        Assert.assertEquals(count_new, count_old+1, "Количесво лимитов НЕ увеличилось на 1");
        checkLimitsData("EXPENSE", "EXTERNAL_BANK", 11, 22, 33, 44, 55);
        logFinishTest("createLimits_positive_EXPENSE_EXTERNAL_BANK");
        logStartTest("createLimits_positive_EXPENSE_FULL_IDENT_DIRECT");
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_old = array.length();
        body = "{\n" +
                "  \"direction\": \"EXPENSE\",\n" +
                "  \"kind\": \"FULL_IDENT_DIRECT\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.createLimits(body);
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_new = array.length();
        Assert.assertEquals(count_new, count_old+1, "Количесво лимитов НЕ увеличилось на 1");
        checkLimitsData("EXPENSE", "FULL_IDENT_DIRECT", 11, 22, 33, 44, 55);
        logFinishTest("createLimits_positive_EXPENSE_FULL_IDENT_DIRECT");
        logStartTest("createLimits_positive_EXPENSE_FULL_IDENT_FUIB_TO_FUIB");
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_old = array.length();
        body = "{\n" +
                "  \"direction\": \"EXPENSE\",\n" +
                "  \"kind\": \"FULL_IDENT_FUIB_TO_FUIB\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.createLimits(body);
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_new = array.length();
        Assert.assertEquals(count_new, count_old+1, "Количесво лимитов НЕ увеличилось на 1");
        checkLimitsData("EXPENSE", "FULL_IDENT_FUIB_TO_FUIB", 11, 22, 33, 44, 55);
        logFinishTest("createLimits_positive_EXPENSE_FULL_IDENT_FUIB_TO_FUIB");
        logStartTest("createLimits_positive_transaction_string");
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_old = array.length();
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": \"11\",\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.createLimits(body);
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_new = array.length();
        Assert.assertEquals(count_new, count_old+1, "Количесво лимитов НЕ увеличилось на 1");
        checkLimitsData("PROFIT", "DIRECT_MONEY_TRANS", 11, 22, 33, 44, 55);
        logFinishTest("createLimits_positive_transaction_string");
        logStartTest("createLimits_positive_daily_amount_string");
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_old = array.length();
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": \"22\",\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.createLimits(body);
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_new = array.length();
        Assert.assertEquals(count_new, count_old+1, "Количесво лимитов НЕ увеличилось на 1");
        checkLimitsData("PROFIT", "DIRECT_MONEY_TRANS", 11, 22, 33, 44, 55);
        logFinishTest("createLimits_positive_daily_amount_string");
        logStartTest("createLimits_positive_daily_quantity_string");
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_old = array.length();
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": \"33\",\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.createLimits(body);
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_new = array.length();
        Assert.assertEquals(count_new, count_old+1, "Количесво лимитов НЕ увеличилось на 1");
        checkLimitsData("PROFIT", "DIRECT_MONEY_TRANS", 11, 22, 33, 44, 55);
        logFinishTest("createLimits_positive_daily_quantity_string");
        logStartTest("createLimits_positive_monthly_amount_string");
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_old = array.length();
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": \"44\",\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.createLimits(body);
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_new = array.length();
        Assert.assertEquals(count_new, count_old+1, "Количесво лимитов НЕ увеличилось на 1");
        checkLimitsData("PROFIT", "DIRECT_MONEY_TRANS", 11, 22, 33, 44, 55);
        logFinishTest("createLimits_positive_monthly_amount_string");
        logStartTest("createLimits_positive_monthly_quantity_string");
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_old = array.length();
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": \"55\"\n" +
                "}";
        limits.createLimits(body);
        limits.getLimits();
        array = new JSONArray(limits.getResponse());
        count_new = array.length();
        Assert.assertEquals(count_new, count_old+1, "Количесво лимитов НЕ увеличилось на 1");
        checkLimitsData("PROFIT", "DIRECT_MONEY_TRANS", 11, 22, 33, 44, 55);
        logFinishTest("createLimits_positive_monthly_quantity_string");
    }

    public void createLimits_negative_direction(){
        logStartTest("createLimits_negative_without_direction");
        body = "{\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_without_direction");
    }

    public void createLimits_negative_kind(){
        logStartTest("createLimits_negative_without_kind");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_without_kind");
    }

    public void createLimits_negative_transaction_amount(){
        String test_name = "transaction_amount";
        logStartTest("createLimits_negative_without_"+test_name);
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_without_"+test_name);
        logStartTest("createLimits_negative_"+test_name+"_minus");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": -11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_minus");
        logStartTest("createLimits_negative_"+test_name+"_null");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": null,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_null");
        logStartTest("createLimits_negative_"+test_name+"_default");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": default,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_default");
        logStartTest("createLimits_negative_"+test_name+"_string");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": \"11.1\",\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_string");
        logStartTest("createLimits_negative_"+test_name+"_float");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11.1,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_float");
        logStartTest("createLimits_negative_"+test_name+"_zero");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 0,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_zero");
    }

    public void createLimits_negative_daily_amount(){
        String test_name = "daily_amount";
        logStartTest("createLimits_negative_without_"+ test_name);
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_without_"+ test_name);
        logStartTest("createLimits_negative_"+ test_name +"_minus");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": -22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+ test_name +"_minus");
        logStartTest("createLimits_negative_"+ test_name +"_null");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": null,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_null");
        logStartTest("createLimits_negative_"+test_name+"_default");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": default,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_default");
        logStartTest("createLimits_negative_"+test_name+"_string");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": \"22.2\",\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_string");
        logStartTest("createLimits_negative_"+test_name+"_float");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22.2,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_float");
        logStartTest("createLimits_negative_"+test_name+"_zero");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 0,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_zero");
    }

    public void createLimits_negative_daily_quantity(){
        String test_name = "daily_quantity";
        logStartTest("createLimits_negative_without_"+ test_name);
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_without_"+ test_name);
        logStartTest("createLimits_negative_"+ test_name +"_minus");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": -33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+ test_name +"_minus");
        logStartTest("createLimits_negative_"+ test_name +"_null");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": null,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_null");
        logStartTest("createLimits_negative_"+test_name+"_default");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": default,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_default");
        logStartTest("createLimits_negative_"+test_name+"_string");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22.,\n" +
                "  \"daily_quantity\": \"33.3\",\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_string");
        logStartTest("createLimits_negative_"+test_name+"_float");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33.3,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_float");
        logStartTest("createLimits_negative_"+test_name+"_zero");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 0,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_zero");
    }

    public void createLimits_negative_monthly_amount(){
        String test_name = "monthly_amount";
        logStartTest("createLimits_negative_without_"+ test_name);
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_without_"+ test_name);
        logStartTest("createLimits_negative_"+ test_name +"_minus");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": -44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+ test_name +"_minus");
        logStartTest("createLimits_negative_"+ test_name +"_null");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": null,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_null");
        logStartTest("createLimits_negative_"+test_name+"_default");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": default,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_default");
        logStartTest("createLimits_negative_"+test_name+"_string");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22.,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": \"44.4\",\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_string");
        logStartTest("createLimits_negative_"+test_name+"_float");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44.4,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_float");
        logStartTest("createLimits_negative_"+test_name+"_zero");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 0,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_zero");
    }

    public void createLimits_negative_monthly_quantity(){
        String test_name = "monthly_quantity";
        logStartTest("createLimits_negative_without_"+ test_name);
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_without_"+ test_name);
        logStartTest("createLimits_negative_"+ test_name +"_minus");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": -55\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+ test_name +"_minus");
        logStartTest("createLimits_negative_"+ test_name +"_null");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": null\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_null");
        logStartTest("createLimits_negative_"+test_name+"_default");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": default\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_default");
        logStartTest("createLimits_negative_"+test_name+"_string");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22.,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44.,\n" +
                "  \"monthly_quantity\": \"55.5\"\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_string");
        logStartTest("createLimits_negative_"+test_name+"_float");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55.5\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_float");
        logStartTest("createLimits_negative_"+test_name+"_zero");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 0\n" +
                "}";
        limits.setStatus_code(400);
        try {
            limits.createLimits(body);
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("createLimits_negative_"+test_name+"_zero");
    }

    public void getLimits_positive(){
        logStartTest("getLimits_positive_all_limits");
        limits.getLimits();
        try {
            array = new JSONArray(limits.getResponse());
        }catch (Throwable e){
            logger.error(e);
            System.out.println("В ответ получен НЕ массив");
        }
        logFinishTest("getLimits_positive_all_limits");
        logStartTest("getLimits_positive_limits_by_id");
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.createLimits(body);
        limits.getLimits(limits.getLimits_id());
        json = new JSONObject(limits.getResponse());
        Assert.assertEquals(json.getString("id"), limits.getLimits_id(), "Запрос get by id - вернул не тот id");
        logFinishTest("getLimits_positive_limits_by_id");
    }

    public void getLimits_negative(){
        logStartTest("getLimits_negative_limits_by_id_not_found");
        limits.setStatus_code(400);
        try {
            limits.getLimits("08898077-a990-4898-b9b0-4d7d76a0cb1c");
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("getLimits_negative_limits_by_id_not_found");
        logStartTest("getLimits_negative_limits_by_id_not_uuid");
        limits.setStatus_code(400);
        try {
            limits.getLimits("08898077-a990-4898-b9b04d7d76a0cb1c");
        }finally {
            limits.setStatus_code(200);
        }
        logFinishTest("getLimits_negative_limits_by_id_not_uuid");
    }

    public void updateLimits_positive(){
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.createLimits(body);

        logStartTest("updateLimits_positive");
        body = "{\n" +
                "  \"transaction_amount\": 111,\n" +
                "  \"daily_amount\": 222,\n" +
                "  \"daily_quantity\": 333,\n" +
                "  \"monthly_amount\": 444,\n" +
                "  \"monthly_quantity\": 555\n" +
                "}";
        limits.updateLimits(body, limits.getLimits_id());
        checkLimitsData("PROFIT", "DIRECT_MONEY_TRANS", 111, 222, 333, 444, 555);
        logFinishTest("updateLimits_positive");
        logStartTest("updateLimits_positive_transaction_amount_string");
        body = "{\n" +
                "  \"transaction_amount\": \"1111\",\n" +
                "  \"daily_amount\": 222,\n" +
                "  \"daily_quantity\": 333,\n" +
                "  \"monthly_amount\": 444,\n" +
                "  \"monthly_quantity\": 555\n" +
                "}";
        limits.updateLimits(body, limits.getLimits_id());
        checkLimitsData("PROFIT", "DIRECT_MONEY_TRANS", 1111, 222, 333, 444, 555);
        logFinishTest("updateLimits_positive_transaction_amount_string");
        logStartTest("updateLimits_positive_daily_amount_string");
        body = "{\n" +
                "  \"transaction_amount\": 1111,\n" +
                "  \"daily_amount\": \"2222\",\n" +
                "  \"daily_quantity\": 333,\n" +
                "  \"monthly_amount\": 444,\n" +
                "  \"monthly_quantity\": 555\n" +
                "}";
        limits.updateLimits(body, limits.getLimits_id());
        checkLimitsData("PROFIT", "DIRECT_MONEY_TRANS", 1111, 2222, 333, 444, 555);
        logFinishTest("updateLimits_positive_daily_amount_string");
        logStartTest("updateLimits_positive_daily_quantity_string");
        body = "{\n" +
                "  \"transaction_amount\": 1111,\n" +
                "  \"daily_amount\": 2222,\n" +
                "  \"daily_quantity\": \"3333\",\n" +
                "  \"monthly_amount\": 444,\n" +
                "  \"monthly_quantity\": 555\n" +
                "}";
        limits.updateLimits(body, limits.getLimits_id());
        checkLimitsData("PROFIT", "DIRECT_MONEY_TRANS", 1111, 2222, 3333, 444, 555);
        logFinishTest("updateLimits_positive_daily_quantity_string");
        logStartTest("updateLimits_positive_monthly_amount_string");
        body = "{\n" +
                "  \"transaction_amount\": 1111,\n" +
                "  \"daily_amount\": 2222,\n" +
                "  \"daily_quantity\": 3333,\n" +
                "  \"monthly_amount\": \"4444\",\n" +
                "  \"monthly_quantity\": 555\n" +
                "}";
        limits.updateLimits(body, limits.getLimits_id());
        checkLimitsData("PROFIT", "DIRECT_MONEY_TRANS", 1111, 2222, 3333, 4444, 555);
        logFinishTest("updateLimits_positive_monthly_amount_string");
        logStartTest("updateLimits_positive_monthly_quantity_string");
        body = "{\n" +
                "  \"transaction_amount\": 1111,\n" +
                "  \"daily_amount\": 2222,\n" +
                "  \"daily_quantity\": 3333,\n" +
                "  \"monthly_amount\": 4444,\n" +
                "  \"monthly_quantity\": \"5555\"\n" +
                "}";
        limits.updateLimits(body, limits.getLimits_id());
        checkLimitsData("PROFIT", "DIRECT_MONEY_TRANS", 1111, 2222, 3333, 4444, 5555);
        logFinishTest("updateLimits_positive_monthly_quantity_string");
        logStartTest("updateLimits_positive_without_transaction_amount");
        body = "{\n" +
                "  \"daily_amount\": 22222,\n" +
                "  \"daily_quantity\": 33333,\n" +
                "  \"monthly_amount\": 44444,\n" +
                "  \"monthly_quantity\": 55555\n" +
                "}";
        limits.updateLimits(body, limits.getLimits_id());
        checkLimitsData("PROFIT", "DIRECT_MONEY_TRANS", 1111, 22222, 33333, 44444, 55555);
        logFinishTest("updateLimits_negative_without_transaction_amount");
        logStartTest("updateLimits_positive_without_daily_amount");
        body = "{\n" +
                "  \"transaction_amount\": 11111,\n" +
                "  \"daily_quantity\": 3333,\n" +
                "  \"monthly_amount\": 4444,\n" +
                "  \"monthly_quantity\": 5555\n" +
                "}";
        limits.updateLimits(body, limits.getLimits_id());
        checkLimitsData("PROFIT", "DIRECT_MONEY_TRANS", 11111, 22222, 3333, 4444, 5555);
        logFinishTest("updateLimits_negative_without_daily_amount");
        logStartTest("updateLimits_positive_without_daily_quantity");
        body = "{\n" +
                "  \"transaction_amount\": 111,\n" +
                "  \"daily_amount\": 222,\n" +
                "  \"monthly_amount\": 444,\n" +
                "  \"monthly_quantity\": 555\n" +
                "}";
        limits.updateLimits(body, limits.getLimits_id());
        checkLimitsData("PROFIT", "DIRECT_MONEY_TRANS", 111, 222, 3333, 444, 555);
        logFinishTest("updateLimits_negative_without_daily_quantity");
        logStartTest("updateLimits_positive_without_monthly_amount");
        body = "{\n" +
                "  \"transaction_amount\": 1111,\n" +
                "  \"daily_amount\": 2222,\n" +
                "  \"daily_quantity\": 33333,\n" +
                "  \"monthly_quantity\": 5555\n" +
                "}";
        limits.updateLimits(body, limits.getLimits_id());
        checkLimitsData("PROFIT", "DIRECT_MONEY_TRANS", 1111, 2222, 33333, 444, 5555);
        logFinishTest("updateLimits_negative_without_monthly_amount");
        logStartTest("updateLimits_positive_without_monthly_quantity");
        body = "{\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44\n" +
                "}";
        limits.updateLimits(body, limits.getLimits_id());
        checkLimitsData("PROFIT", "DIRECT_MONEY_TRANS", 11, 22, 33, 44, 5555);
        logFinishTest("updateLimits_negative_without_monthly_quantity");
    }

    public void updateLimits_negative_transaction_amount(){
        String test_name = "transaction_amount";
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.createLimits(body);
        logStartTest("updateLimits_negative_"+test_name+"_minus");
        body = "{\n" +
                "  \"transaction_amount\": -11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_minus");
        logStartTest("updateLimits_negative_"+test_name+"_null");
        body = "{\n" +
                "  \"transaction_amount\": null,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_null");
        logStartTest("updateLimits_negative_"+test_name+"_default");
        body = "{\n" +
                "  \"transaction_amount\": default,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_default");
        logStartTest("updateLimits_negative_"+test_name+"_string");
        body = "{\n" +
                "  \"transaction_amount\": \"11.1\",\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_string");
        logStartTest("updateLimits_negative_"+test_name+"_float");
        body = "{\n" +
                "  \"transaction_amount\": 11.1,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_float");
        logStartTest("updateLimits_negative_"+test_name+"_zero");
        body = "{\n" +
                "  \"transaction_amount\": 0,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_zero");
    }

    public void updateLimits_negative_daily_amount(){
        String test_name = "daily_amount";
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.createLimits(body);
        logStartTest("updateLimits_negative_"+test_name+"_minus");
        body = "{\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": -22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_minus");
        logStartTest("updateLimits_negative_"+test_name+"_null");
        body = "{\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": null,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_null");
        logStartTest("updateLimits_negative_"+test_name+"_default");
        body = "{\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": default,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_default");
        logStartTest("updateLimits_negative_"+test_name+"_string");
        body = "{\n" +
                "  \"transaction_amount\": 11.,\n" +
                "  \"daily_amount\": \"22.2\",\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_string");
        logStartTest("updateLimits_negative_"+test_name+"_float");
        body = "{\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22.2,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_float");
        logStartTest("updateLimits_negative_"+test_name+"_zero");
        body = "{\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 0,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_zero");
    }

    public void updateLimits_negative_daily_quantity(){
        String test_name = "daily_quantity";
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.createLimits(body);
        logStartTest("updateLimits_negative_"+test_name+"_minus");
        body = "{\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": -33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_minus");
        logStartTest("updateLimits_negative_"+test_name+"_null");
        body = "{\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": null,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_null");
        logStartTest("updateLimits_negative_"+test_name+"_default");
        body = "{\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 33,\n" +
                "  \"daily_quantity\": default,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_default");
        logStartTest("updateLimits_negative_"+test_name+"_string");
        body = "{\n" +
                "  \"transaction_amount\": 11.,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": \"33.3\",\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_string");
        logStartTest("updateLimits_negative_"+test_name+"_float");
        body = "{\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33.3,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_float");
        logStartTest("updateLimits_negative_"+test_name+"_zero");
        body = "{\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 0,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_zero");
    }

    public void updateLimits_negative_monthly_amount(){
        String test_name = "monthly_amount";
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.createLimits(body);
        logStartTest("updateLimits_negative_"+test_name+"_minus");
        body = "{\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": -44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_minus");
        logStartTest("updateLimits_negative_"+test_name+"_null");
        body = "{\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 44,\n" +
                "  \"monthly_amount\": null,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_null");
        logStartTest("updateLimits_negative_"+test_name+"_default");
        body = "{\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 33,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": default,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_default");
        logStartTest("updateLimits_negative_"+test_name+"_string");
        body = "{\n" +
                "  \"transaction_amount\": 11.,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": \"44.4\",\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_string");
        logStartTest("updateLimits_negative_"+test_name+"_float");
        body = "{\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44.4,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_float");
        logStartTest("updateLimits_negative_"+test_name+"_zero");
        body = "{\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 0,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_zero");
    }

    public void updateLimits_negative_monthly_quantity(){
        String test_name = "monthly_quantity";
        body = "{\n" +
                "  \"direction\": \"PROFIT\",\n" +
                "  \"kind\": \"DIRECT_MONEY_TRANS\",\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55\n" +
                "}";
        limits.createLimits(body);
        logStartTest("updateLimits_negative_"+test_name+"_minus");
        body = "{\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": -55\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_minus");
        logStartTest("updateLimits_negative_"+test_name+"_null");
        body = "{\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": null\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_null");
        logStartTest("updateLimits_negative_"+test_name+"_default");
        body = "{\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": default\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_default");
        logStartTest("updateLimits_negative_"+test_name+"_string");
        body = "{\n" +
                "  \"transaction_amount\": 11.,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": \"55.5\"\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_string");
        logStartTest("updateLimits_negative_"+test_name+"_float");
        body = "{\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 55.5\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_float");
        logStartTest("updateLimits_negative_"+test_name+"_zero");
        body = "{\n" +
                "  \"transaction_amount\": 11,\n" +
                "  \"daily_amount\": 22,\n" +
                "  \"daily_quantity\": 33,\n" +
                "  \"monthly_amount\": 44,\n" +
                "  \"monthly_quantity\": 0\n" +
                "}";
        limits.setStatus_code_put(400);
        try {
            limits.updateLimits(body, limits.getLimits_id());
        }finally {
            limits.setStatus_code_put(204);
        }
        logFinishTest("updateLimits_negative_"+test_name+"_zero");
    }

    void checkLimitsData(String direction, String kind, int transaction_amount, int daily_amount, int daily_quantity, int monthly_amount, int monthly_quantity){
        limits.getLimits(limits.getLimits_id());
        json = new JSONObject(limits.getResponse());
        Assert.assertEquals(json.getString("direction"), direction);
        Assert.assertEquals(json.getString("kind"), kind);
        Assert.assertEquals(json.getInt("transaction_amount"), transaction_amount);
        Assert.assertEquals(json.getInt("daily_amount"), daily_amount);
        Assert.assertEquals(json.getInt("daily_quantity"), daily_quantity);
        Assert.assertEquals(json.getInt("monthly_amount"), monthly_amount);
        Assert.assertEquals(json.getInt("monthly_quantity"), monthly_quantity);
    }
}
