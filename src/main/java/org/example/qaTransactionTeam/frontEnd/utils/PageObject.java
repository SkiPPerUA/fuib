package org.example.qaTransactionTeam.frontEnd.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

abstract public class PageObject {
    final protected WebDriver driver;
    final protected Logger logger = Logger.getLogger(this.getClass());

    public PageObject ()
    {
        this.driver = Session.getWD();
    }

    public void confirmPage ()
    {
        logger.info("Страница загружена");
        try {
            Session.getWaitDr().until(d -> d.findElements(this.readyLocator().getWDLocator()).size() != 0);
        } catch (Throwable e) {
            Assert.fail("Не смог подтвердить загрузку страницы = "+this.getClass().getSimpleName());
        }
    }

    abstract protected Locator readyLocator();
}
