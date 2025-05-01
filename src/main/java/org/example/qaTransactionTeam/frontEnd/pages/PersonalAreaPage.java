package org.example.qaTransactionTeam.frontEnd.pages;

import org.example.qaTransactionTeam.frontEnd.pageBlocks.MainBlock;
import org.example.qaTransactionTeam.frontEnd.pageBlocks.OtpBlock;
import org.example.qaTransactionTeam.frontEnd.utils.Locator;
import org.example.qaTransactionTeam.frontEnd.utils.PageObject;
import org.example.qaTransactionTeam.frontEnd.utils.widget.GUIButton;
import org.example.qaTransactionTeam.frontEnd.utils.widget.GUIInfoPlace;
import org.example.qaTransactionTeam.frontEnd.utils.widget.GUITextBox;

public class PersonalAreaPage extends PageObject {

    public final MainBlock menuBlock = new MainBlock();
    public final OtpBlock otpBlock = new OtpBlock();
    private int correctional = 2;

    public final GUIButton manualAddButton = new GUIButton(Locator.create("//button[text() = 'Додати вручну']"));
    public final GUIButton addButton = new GUIButton(Locator.create("//*[@data-name=\"plus\"]"));
    public final GUIButton processingButton = new GUIButton(Locator.create("//button[text() = 'Передати в обробку']"));
    public final GUIButton deleteAllButton = new GUIButton(Locator.create("//button[text() = 'Видалити все']"));
    public final GUIButton showAllDataButton = new GUIButton(Locator.create("//button[text() = 'Показати всю таблицю']"));


    public GUITextBox cardNumberField = new GUITextBox(Locator.create("//*[@formcontrolname=\"number_card\"]"));
    public GUITextBox innField = new GUITextBox(Locator.create("//*[@formcontrolname=\"inn\"]"));
    public GUITextBox ndflField = new GUITextBox(Locator.create("//*[@formcontrolname=\"ndfl\"]"));
    public GUITextBox vsField = new GUITextBox(Locator.create("//*[@formcontrolname=\"vs\"]"));
    public GUITextBox esvField = new GUITextBox(Locator.create("//*[@formcontrolname=\"esv\"]"));
    public GUITextBox amountField = new GUITextBox(Locator.create("//*[@formcontrolname=\"amount_credit_card\"]"));
    public GUITextBox commentField = new GUITextBox(Locator.create("//*[@formcontrolname=\"optional\"]"));

    public final GUIInfoPlace countCardInfo = new GUIInfoPlace(Locator.create("//span[text()='Кількість карт:']/../span[@class=\"result__value\"]"));
    public final GUIInfoPlace ndflInfo = new GUIInfoPlace(Locator.create("//span[text()='НДФЛ:']/../span[@class=\"result__value\"]"));
    public final GUIInfoPlace vsInfo = new GUIInfoPlace(Locator.create("//span[text()='ВС:']/../span[@class=\"result__value\"]"));
    public final GUIInfoPlace esvInfo = new GUIInfoPlace(Locator.create("//span[text()='ЕВС:']/../span[@class=\"result__value\"]"));
    public final GUIInfoPlace amountInfo = new GUIInfoPlace(Locator.create("//span[text()='Сума зарахування на карту:']/../span[@class=\"result__value\"]"));


    @Override
    protected Locator readyLocator() {
        return manualAddButton.getLocator();
    }

    public void addData(String cardNumber, String inn, String ndfl, String vs, String esv, String amount, String comment){
        addButton.click();
        cardNumberField = new GUITextBox(Locator.create("//tr["+correctional+"]//*[@formcontrolname=\"number_card\"]"));
        cardNumberField.setValue(cardNumber);
        innField = new GUITextBox(Locator.create("//tr["+correctional+"]//*[@formcontrolname=\"inn\"]"));
        innField.setValue(inn);
        ndflField = new GUITextBox(Locator.create("//tr["+correctional+"]//*[@formcontrolname=\"ndfl\"]"));
        ndflField.setValue(ndfl);
        vsField = new GUITextBox(Locator.create("//tr["+correctional+"]//*[@formcontrolname=\"vs\"]"));
        vsField.setValue(vs);
        esvField = new GUITextBox(Locator.create("//tr["+correctional+"]//*[@formcontrolname=\"esv\"]"));
        esvField.setValue(esv);
        amountField = new GUITextBox(Locator.create("//tr["+correctional+"]//*[@formcontrolname=\"amount_credit_card\"]"));
        amountField.setValue(amount);
        commentField = new GUITextBox(Locator.create("//tr["+correctional+"]//*[@formcontrolname=\"optional\"]"));
        commentField.setValue(comment);
        correctional++;
    }

}
