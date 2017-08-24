package com.kingdee.eas.custom.financialinvoice;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.bos.util.*;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.framework.IDataBase;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;

public interface IInvoicetype extends IDataBase
{
    public InvoicetypeInfo getInvoicetypeInfo(IObjectPK pk) throws BOSException, EASBizException;
    public InvoicetypeInfo getInvoicetypeInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public InvoicetypeInfo getInvoicetypeInfo(String oql) throws BOSException, EASBizException;
    public InvoicetypeCollection getInvoicetypeCollection() throws BOSException;
    public InvoicetypeCollection getInvoicetypeCollection(EntityViewInfo view) throws BOSException;
    public InvoicetypeCollection getInvoicetypeCollection(String oql) throws BOSException;
}