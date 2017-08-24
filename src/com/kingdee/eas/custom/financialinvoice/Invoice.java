package com.kingdee.eas.custom.financialinvoice;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import java.math.BigDecimal;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.CoreBillBase;
import com.kingdee.eas.custom.financialinvoice.app.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import java.util.List;
import com.kingdee.eas.framework.ICoreBillBase;

public class Invoice extends CoreBillBase implements IInvoice
{
    public Invoice()
    {
        super();
        registerInterface(IInvoice.class, this);
    }
    public Invoice(Context ctx)
    {
        super(ctx);
        registerInterface(IInvoice.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("80F193CE");
    }
    private InvoiceController getController() throws BOSException
    {
        return (InvoiceController)getBizController();
    }
    /**
     *取集合-System defined method
     *@return
     */
    public InvoiceCollection getInvoiceCollection() throws BOSException
    {
        try {
            return getController().getInvoiceCollection(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@param view 取集合
     *@return
     */
    public InvoiceCollection getInvoiceCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getInvoiceCollection(getContext(), view);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@param oql 取集合
     *@return
     */
    public InvoiceCollection getInvoiceCollection(String oql) throws BOSException
    {
        try {
            return getController().getInvoiceCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public InvoiceInfo getInvoiceInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getInvoiceInfo(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@param selector 取值
     *@return
     */
    public InvoiceInfo getInvoiceInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getInvoiceInfo(getContext(), pk, selector);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取值-System defined method
     *@param oql 取值
     *@return
     */
    public InvoiceInfo getInvoiceInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getInvoiceInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *判断发票号是否存在-User defined method
     *@param invoiceNumber 发票号码
     *@param billId 单据ID
     *@param invoiceCode 发票代码
     *@return
     */
    public boolean isExistInvoiceNumber(String invoiceNumber, String billId, String invoiceCode) throws BOSException
    {
        try {
            return getController().isExistInvoiceNumber(getContext(), invoiceNumber, billId, invoiceCode);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *判断半年内的金额是否相同-User defined method
     *@param ApplyDept 申请部门
     *@param Applyer 申请人
     *@param BillingDate 申请时间
     *@param BillId 单据ID
     *@param TotalMoney 申请总金额
     *@return
     */
    public boolean isExistInvoiceTotalMoney(String ApplyDept, String Applyer, String BillingDate, String BillId, BigDecimal TotalMoney) throws BOSException
    {
        try {
            return getController().isExistInvoiceTotalMoney(getContext(), ApplyDept, Applyer, BillingDate, BillId, TotalMoney);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *获取当前发票的所有凭证及付款单-User defined method
     *@param InvoiceId InvoiceId
     *@param startTime startTime
     *@param endTime endTime
     *@param paymentCode paymentCode
     *@return
     */
    public List getCredentialLists(String InvoiceId, String startTime, String endTime, String paymentCode) throws BOSException
    {
        try {
            return getController().getCredentialLists(getContext(), InvoiceId, startTime, endTime, paymentCode);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *更新状态-User defined method
     *@param billIds 单据ID
     *@param type type
     */
    public void updPayStatus(List billIds, String type) throws BOSException, EASBizException
    {
        try {
            getController().updPayStatus(getContext(), billIds, type);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *更新数据删除状态-User defined method
     *@param billIds billIds
     */
    public void updDeleteStatus(List billIds) throws BOSException
    {
        try {
            getController().updDeleteStatus(getContext(), billIds);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *凭证联查-User defined method
     *@param InvoiceId InvoiceId
     *@param voucherCode voucherCode
     *@param startTime startTime
     *@param endTime endTime
     *@return
     */
    public List getVouchersLists(String InvoiceId, String voucherCode, String startTime, String endTime) throws BOSException
    {
        try {
            return getController().getVouchersLists(getContext(), InvoiceId, voucherCode, startTime, endTime);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}