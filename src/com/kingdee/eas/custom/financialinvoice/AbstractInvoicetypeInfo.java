package com.kingdee.eas.custom.financialinvoice;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractInvoicetypeInfo extends com.kingdee.eas.framework.DataBaseInfo implements Serializable 
{
    public AbstractInvoicetypeInfo()
    {
        this("id");
    }
    protected AbstractInvoicetypeInfo(String pkField)
    {
        super(pkField);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("C37D8C08");
    }
}