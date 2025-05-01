package org.example.qaTransactionTeam.frontEnd.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract public class BlockObject {

    final protected WebDriver driver;
    final protected Logger logger = Logger.getLogger(this.getClass());

    public BlockObject ()
    {
        this.driver = Session.getWD();
    }

    public void confirmBlock()
    {
        logger.info("Блок загружен");
        try {
            Session.getWaitDr().until(d -> d.findElements(this.readyLocator().getWDLocator()).size() != 0);
        } catch (Throwable e) {
            logger.error("Не смог подтвердить загрузку блока = "+this.getClass().getSimpleName());
        }
    }

    abstract protected Locator readyLocator();
}
