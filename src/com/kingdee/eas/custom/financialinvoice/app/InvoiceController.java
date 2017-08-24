package com.kingdee.eas.custom.financialinvoice.app;

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
import com.kingdee.eas.framework.app.CoreBillBaseController;
import com.kingdee.eas.custom.financialinvoice.InvoiceInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.custom.financialinvoice.InvoiceCollection;
import java.util.List;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface InvoiceController extends CoreBillBaseController
{
    public InvoiceCollection getInvoiceCollection(Context ctx) throws BOSException, RemoteException;
    public InvoiceCollection getInvoiceCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public InvoiceCollection getInvoiceCollection(Context ctx, String oql) throws BOSException, RemoteException;
    public InvoiceInfo getInvoiceInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public InvoiceInfo getInvoiceInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public InvoiceInfo getInvoiceInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public boolean isExistInvoiceNumber(Context ctx, String invoiceNumber, String billId, String invoiceCode) throws BOSException, RemoteException;
    public boolean isExistInvoiceTotalMoney(Context ctx, String ApplyDept, String Applyer, String BillingDate, String BillId, BigDecimal TotalMoney) throws BOSException, RemoteException;
    public List getCredentialLists(Context ctx, String InvoiceId, String startTime, String endTime, String paymentCode) throws BOSException, RemoteException;
    public void updPayStatus(Context ctx, List billIds, String type) throws BOSException, EASBizException, RemoteException;
    public void updDeleteStatus(Context ctx, List billIds) throws BOSException, RemoteException;
    public List getVouchersLists(Context ctx, String InvoiceId, String voucherCode, String startTime, String endTime) throws BOSException, RemoteException;
}