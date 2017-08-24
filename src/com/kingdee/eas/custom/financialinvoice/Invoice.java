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
     *ȡ����-System defined method
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
     *ȡ����-System defined method
     *@param view ȡ����
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
     *ȡ����-System defined method
     *@param oql ȡ����
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
     *ȡֵ-System defined method
     *@param pk ȡֵ
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
     *ȡֵ-System defined method
     *@param pk ȡֵ
     *@param selector ȡֵ
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
     *ȡֵ-System defined method
     *@param oql ȡֵ
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
     *�жϷ�Ʊ���Ƿ����-User defined method
     *@param invoiceNumber ��Ʊ����
     *@param billId ����ID
     *@param invoiceCode ��Ʊ����
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
     *�жϰ����ڵĽ���Ƿ���ͬ-User defined method
     *@param ApplyDept ���벿��
     *@param Applyer ������
     *@param BillingDate ����ʱ��
     *@param BillId ����ID
     *@param TotalMoney �����ܽ��
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
     *��ȡ��ǰ��Ʊ������ƾ֤�����-User defined method
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
     *����״̬-User defined method
     *@param billIds ����ID
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
     *��������ɾ��״̬-User defined method
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
     *ƾ֤����-User defined method
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