package com.kingdee.eas.custom.financialinvoice;

import java.io.Serializable;

public class InvoicetypeInfo extends AbstractInvoicetypeInfo implements Serializable 
{
    public InvoicetypeInfo()
    {
        super();
    }
    protected InvoicetypeInfo(String pkField)
    {
        super(pkField);
    }
}