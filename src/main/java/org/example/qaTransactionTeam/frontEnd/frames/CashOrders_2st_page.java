package org.example.qaTransactionTeam.frontEnd.frames;

import org.example.qaTransactionTeam.frontEnd.utils.Locator;
import org.example.qaTransactionTeam.frontEnd.utils.PageObject;
import org.example.qaTransactionTeam.frontEnd.utils.widget.GUIButton;
import org.example.qaTransactionTeam.frontEnd.utils.widget.GUITextBox;

public class CashOrders_2st_page extends PageObject {

    private final GUIButton nextButton = new GUIButton(Locator.create("//div[@class='controls']/button"));
    private final GUITextBox cardBox = new GUITextBox(Locator.create("//input[@id='cc-number']"));
    private final GUITextBox monthBox = new GUITextBox(Locator.create("//input[@id='cc-exp-month']"));
    private final GUITextBox yearBox = new GUITextBox(Locator.create("//input[@id='cc-exp-year']"));
    private final GUITextBox cvvBox = new GUITextBox(Locator.create("//input[@id='cc-cvc']"));


    @Override
    protected Locator readyLocator() {
        return nextButton.getLocator();
    }

    public CashOrders_2st_page setCardBox(String card, String mounth, String year, String cvv){
        cardBox.setValue(card);
        monthBox.setValue(mounth);
        yearBox.setValue(year);
        cvvBox.setValue(cvv);
        return this;
    }

    public CashOrders_2st_page choiseCurrency(String currency){
        new GUIButton(Locator.create("//mat-select[@formcontrolname=\"currency_id\"]")).click();
        new GUIButton(Locator.create("//mat-option/span[text()='"+currency+"']")).click();
        return this;
    }

    public CashOrders_2st_page changeCash(String summCash){
        new GUITextBox(Locator.create("//input[@formcontrolname=\"amount\"]")).setValue(summCash);
        return this;
    }

    public void clickNextButton(){
        nextButton.click();
    }
}
