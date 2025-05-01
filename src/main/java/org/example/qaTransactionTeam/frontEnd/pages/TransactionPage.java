package org.example.qaTransactionTeam.frontEnd.pages;

import org.example.qaTransactionTeam.frontEnd.pageBlocks.MainBlock;
import org.example.qaTransactionTeam.frontEnd.utils.Locator;
import org.example.qaTransactionTeam.frontEnd.utils.PageObject;

public class TransactionPage extends PageObject {

    public final MainBlock menuBlock = new MainBlock();

    @Override
    protected Locator readyLocator() {
        return Locator.create("//*[@id=\"filters\"]/app-filters/nb-accordion/nb-accordion-item");
            }
}
