package org.example.qaTransactionTeam.frontEnd.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Session {

    private static WebDriver dr;
    private static WebDriverWait waitDr;
    private final static Logger logger = Logger.getLogger(Session.class);

    public static void Session(){

        System.setProperty("webdriver.chrome.driver","/Users/user/Documents/Тесты/chromedriver");
        //WebDriverManager.operadriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("start-maximized");

        dr = new ChromeDriver(options);
        waitDr = new WebDriverWait(dr, 10);
        logger.info("Драйвер запущен");
    }

    public static void closeSession(){
        if (dr != null){
            dr.close();
            dr.quit();
    }
        logger.info("Драйвер закрыт");
    }

    public static WebDriver getWD(){
        return dr;
    }

    public static WebDriverWait getWaitDr(){ return waitDr; }

}
