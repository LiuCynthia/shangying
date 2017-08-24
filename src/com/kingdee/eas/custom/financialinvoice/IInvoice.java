package com.kingdee.eas.custom.financialinvoice;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import java.math.BigDecimal;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import java.util.List;
import com.kingdee.eas.framework.ICoreBillBase;

public interface IInvoice extends ICoreBillBase
{
    public InvoiceCollection getInvoiceCollection() throws BOSException;
    public InvoiceCollection getInvoiceCollection(EntityViewInfo view) throws BOSException;
    public InvoiceCollection getInvoiceCollection(String oql) throws BOSException;
    public InvoiceInfo getInvoiceInfo(IObjectPK pk) throws BOSException, EASBizException;
    public InvoiceInfo getInvoiceInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public InvoiceInfo getInvoiceInfo(String oql) throws BOSException, EASBizException;
    public boolean isExistInvoiceNumber(String invoiceNumber, String billId, String invoiceCode) throws BOSException;
    public boolean isExistInvoiceTotalMoney(String ApplyDept, String Applyer, String BillingDate, String BillId, BigDecimal TotalMoney) throws BOSException;
    public List getCredentialLists(String InvoiceId, String startTime, String endTime, String paymentCode) throws BOSException;
    public void updPayStatus(List billIds, String type) throws BOSException, EASBizException;
    public void updDeleteStatus(List billIds) throws BOSException;
    public List getVouchersLists(String InvoiceId, String voucherCode, String startTime, String endTime) throws BOSException;
}