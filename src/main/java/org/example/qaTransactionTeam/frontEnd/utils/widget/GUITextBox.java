package org.example.qaTransactionTeam.frontEnd.utils.widget;


import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.frontEnd.utils.Locator;
import org.example.qaTransactionTeam.frontEnd.utils.Session;
import org.example.qaTransactionTeam.frontEnd.utils.Widget;
import org.openqa.selenium.WebElement;

public class GUITextBox extends Widget {

    private static final Logger logger = Logger.getLogger(GUITextBox.class);

    public GUITextBox(Locator locator) {
        Widget(locator);
    }

    final public void setValue (String value)
    {
        setElement();
        this.element.clear();
        this.element.sendKeys(value);
        this.waitAction();
        logger.info("Установлено значение = "+value+" для - "+getLocator().getWDLocator());
    }
}
