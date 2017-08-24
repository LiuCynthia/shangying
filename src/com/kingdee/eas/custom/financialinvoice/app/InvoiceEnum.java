/**
 * output package name
 */
package com.kingdee.eas.custom.financialinvoice.app;

import java.util.Map;
import java.util.List;
import java.util.Iterator;
import com.kingdee.util.enums.StringEnum;

/**
 * output class name
 */
public class InvoiceEnum extends StringEnum
{
    public static final String SUBMIT_VALUE = "1";
    public static final String AUDITED_VALUE = "2";
    public static final String REVERSEAUDIT_VALUE = "3";
    public static final String DELETE_VALUE = "-1";

    public static final InvoiceEnum submit = new InvoiceEnum("submit", SUBMIT_VALUE);
    public static final InvoiceEnum Audited = new InvoiceEnum("Audited", AUDITED_VALUE);
    public static final InvoiceEnum ReverseAudit = new InvoiceEnum("ReverseAudit", REVERSEAUDIT_VALUE);
    public static final InvoiceEnum delete = new InvoiceEnum("delete", DELETE_VALUE);

    /**
     * construct function
     * @param String invoiceEnum
     */
    private InvoiceEnum(String name, String invoiceEnum)
    {
        super(name, invoiceEnum);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static InvoiceEnum getEnum(String invoiceEnum)
    {
        return (InvoiceEnum)getEnum(InvoiceEnum.class, invoiceEnum);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(InvoiceEnum.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(InvoiceEnum.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(InvoiceEnum.class);
    }
}