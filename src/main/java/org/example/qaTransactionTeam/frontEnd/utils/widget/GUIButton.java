package org.example.qaTransactionTeam.frontEnd.utils.widget;

import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.frontEnd.utils.Locator;
import org.example.qaTransactionTeam.frontEnd.utils.Widget;


public class GUIButton extends Widget {

    private static final Logger logger = Logger.getLogger(GUIButton.class);

    public GUIButton(Locator locator) {
        Widget(locator);
    }

    public void click ()
    {
        setElement();
        this.element.click();
        this.waitAction();
        logger.info("Нажата кнопка - "+getLocator().getWDLocator());
    }

}
