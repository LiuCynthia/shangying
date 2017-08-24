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
public class PaymentStatusEnum extends StringEnum
{
    public static final String YES_VALUE = "1";
    public static final String NO_VALUE = "0";

    public static final PaymentStatusEnum yes = new PaymentStatusEnum("yes", YES_VALUE);
    public static final PaymentStatusEnum no = new PaymentStatusEnum("no", NO_VALUE);

    /**
     * construct function
     * @param String paymentStatusEnum
     */
    private PaymentStatusEnum(String name, String paymentStatusEnum)
    {
        super(name, paymentStatusEnum);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static PaymentStatusEnum getEnum(String paymentStatusEnum)
    {
        return (PaymentStatusEnum)getEnum(PaymentStatusEnum.class, paymentStatusEnum);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(PaymentStatusEnum.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(PaymentStatusEnum.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(PaymentStatusEnum.class);
    }
}