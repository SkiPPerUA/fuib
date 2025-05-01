package org.example.qaTransactionTeam.frontEnd.utils.widget;

import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.frontEnd.utils.Locator;
import org.example.qaTransactionTeam.frontEnd.utils.Widget;

public class GUIInfoPlace extends Widget {

    private static final Logger logger = Logger.getLogger(GUIInfoPlace.class);

    public GUIInfoPlace(Locator locator) {
        Widget(locator);
    }

    public String text()
    {
        setElement();
        logger.info("Получены данные из - "+getLocator().getWDLocator());
        return element.getText();
    }
}
