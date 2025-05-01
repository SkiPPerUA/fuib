package org.example.qaTransactionTeam.frontEnd.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

abstract public class Widget {

    private static final Logger logger = Logger.getLogger(Widget.class);
    protected WebElement element;
    private Locator locator;

    protected void Widget(Locator locator){
        this.locator = locator;
    }

    protected void waitAction(){
        try { Thread.sleep(1000); } catch (Throwable e) {
            logger.error("Действие НЕ произошло");
        }
    }

    public void setElement() {
        if(element == null) {
            try {
                this.element = Session.getWaitDr().until(ExpectedConditions.visibilityOfElementLocated(locator.getWDLocator()));

            }catch (Throwable e){
                logger.error("Елемент НЕ найден - "+locator.getWDLocator());
            }
        }
    }

    public Locator getLocator() {
        return locator;
    }
}
