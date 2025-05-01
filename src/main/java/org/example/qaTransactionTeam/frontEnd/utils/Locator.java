package org.example.qaTransactionTeam.frontEnd.utils;

import org.openqa.selenium.By;

public class Locator {
    private By wdLocator;

    public Locator (String strLocator)
    {
        this.wdLocator = By.xpath(strLocator);
    }

    public static Locator create(String strLocator)
    {
        return new Locator(strLocator);

    }

    public By getWDLocator()
    {
        return this.wdLocator;
    }

}
