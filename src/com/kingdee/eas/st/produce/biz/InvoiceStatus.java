/**
 * output package name
 */
package com.kingdee.eas.st.produce.biz;

import java.util.Map;
import java.util.List;
import java.util.Iterator;
import com.kingdee.util.enums.StringEnum;

/**
 * output class name
 */
public class InvoiceStatus extends StringEnum
{
    public static final String SUBMIT_VALUE = "1";
    public static final String AUDITED_VALUE = "2";

    public static final InvoiceStatus submit = new InvoiceStatus("submit", SUBMIT_VALUE);
    public static final InvoiceStatus Audited = new InvoiceStatus("Audited", AUDITED_VALUE);

    /**
     * construct function
     * @param String invoiceStatus
     */
    private InvoiceStatus(String name, String invoiceStatus)
    {
        super(name, invoiceStatus);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static InvoiceStatus getEnum(String invoiceStatus)
    {
        return (InvoiceStatus)getEnum(InvoiceStatus.class, invoiceStatus);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(InvoiceStatus.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(InvoiceStatus.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(InvoiceStatus.class);
    }
}