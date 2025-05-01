package org.example.qaTransactionTeam.frontEnd.utils.widget;


import org.apache.log4j.Logger;
import org.example.qaTransactionTeam.frontEnd.utils.Locator;
import org.example.qaTransactionTeam.frontEnd.utils.Widget;

public class GUILink extends Widget
{
    private static final Logger logger = Logger.getLogger(GUILink.class);

    public GUILink(Locator locator) {

        Widget(locator);
    }

    public void click ()
    {
        setElement();
        this.element.click();
        this.waitAction();
        logger.info("Нажата ссылка - "+getLocator().getWDLocator());
    }

    public String getHref(){
        setElement();
        String x = this.element.getAttribute("href");
        return x;
    }
}
