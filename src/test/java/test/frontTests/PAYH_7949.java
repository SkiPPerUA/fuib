package test.frontTests;

import org.example.qaTransactionTeam.BaseTest;
import org.example.qaTransactionTeam.frontEnd.pages.LoginPage;
import org.example.qaTransactionTeam.frontEnd.pages.PersonalAreaPage;
import org.example.qaTransactionTeam.frontEnd.pages.TransactionPage;
import org.example.qaTransactionTeam.frontEnd.utils.Session;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class PAYH_7949 extends BaseTest {

    LoginPage loginPage = new LoginPage();
    TransactionPage transactionPage = new TransactionPage();
    PersonalAreaPage personalAreaPage = new PersonalAreaPage();
    String [] card = {"4314140002543707","4314140002543707","4314140002543707"};
    String [] inn = {"8576238495","8576238495","8576238495"};
    String [] ndfl = {"22.00","27.46","12.4"};
    String [] vs = {"12.00","66.03","22.8"};
    String [] esv = {"43.00","22.00","22.99"};
    String [] amount = {"10000.00","10000.01","10000.6"};

    @BeforeTest
    public void session(){
        Session.Session();
    }

    @Test
    public void manualAddOneData(){
        logStartTest("manualAddOneData");

        loginPage.loginInCab();

        transactionPage.confirmPage();
        transactionPage.menuBlock.confirmBlock();
        transactionPage.menuBlock.personalAreaButton.click();

        personalAreaPage.confirmPage();
        personalAreaPage.manualAddButton.click();

        personalAreaPage.addData(card[0],inn[0],ndfl[0],vs[0],esv[0],amount[0],"test");
        Assert.assertEquals(personalAreaPage.countCardInfo.text(),"1");
        Assert.assertEquals(personalAreaPage.ndflInfo.text(),ndfl[0]);
        Assert.assertEquals(personalAreaPage.vsInfo.text(),vs[0]);
        Assert.assertEquals(personalAreaPage.esvInfo.text(),esv[0]);
        Assert.assertEquals(personalAreaPage.amountInfo.text(),amount[0]);
        personalAreaPage.processingButton.click();

        personalAreaPage.otpBlock.confirmBlock();
        personalAreaPage.otpBlock.otpField.setValue("111111");
        personalAreaPage.otpBlock.confirmButton.click();

        personalAreaPage.menuBlock.confirmBlock();
        personalAreaPage.menuBlock.logOutButton.click();

        logFinishTest("manualAddOneData");
    }

    @Test
    public void manualAddThreeData(){
        logStartTest("manualAddOneData");

        loginPage.loginInCab();

        transactionPage.confirmPage();
        transactionPage.menuBlock.confirmBlock();
        transactionPage.menuBlock.personalAreaButton.click();

        personalAreaPage.confirmPage();
        personalAreaPage.manualAddButton.click();

        personalAreaPage.addData(card[0],inn[0],ndfl[0],vs[0],esv[0],amount[0],"test");
        personalAreaPage.addData(card[1],inn[1],ndfl[1],vs[1],esv[1],amount[1],"test");
        personalAreaPage.addData(card[2],inn[2],ndfl[2],vs[2],esv[2],amount[2],"test");
        Assert.assertEquals(personalAreaPage.countCardInfo.text(),"3");
        Assert.assertEquals(personalAreaPage.ndflInfo.text(),sumFloat(3,ndfl));
        Assert.assertEquals(personalAreaPage.vsInfo.text(),sumFloat(3,vs));
        Assert.assertEquals(personalAreaPage.esvInfo.text(),sumFloat(3,esv));
        Assert.assertEquals(personalAreaPage.amountInfo.text(),sumFloat(3,amount));
        personalAreaPage.processingButton.click();

        personalAreaPage.otpBlock.confirmBlock();
        personalAreaPage.otpBlock.otpField.setValue("111111");
        personalAreaPage.otpBlock.confirmButton.click();

        personalAreaPage.menuBlock.confirmBlock();
        personalAreaPage.menuBlock.logOutButton.click();

        logFinishTest("manualAddOneData");
    }

    @AfterTest
    public void closeSession(){
        Session.closeSession();
    }

    private String sumFloat(int count, String [] mass){
        float res = 0.00f;
        for(int i = 0; i<count;i++){
            res = res + Float.valueOf(mass[i]);
        }
        return String.valueOf(res);
    }
}
