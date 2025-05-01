package org.example.qaTransactionTeam.backEnd.payByLink;

public class InvoiceBusiness extends Invoice{

    private String type = "acquiring";

    public InvoiceBusiness(String body, boolean installment){
        super.installment = installment;
        createInvoice(type,body);
    }

}
