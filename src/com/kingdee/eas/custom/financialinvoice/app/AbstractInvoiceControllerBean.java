package com.kingdee.eas.custom.financialinvoice.app;

import javax.ejb.*;
import java.rmi.RemoteException;
import com.kingdee.bos.*;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.metadata.IMetaDataPK;
import com.kingdee.bos.metadata.rule.RuleExecutor;
import com.kingdee.bos.metadata.MetaDataPK;
//import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.framework.ejb.AbstractEntityControllerBean;
import com.kingdee.bos.framework.ejb.AbstractBizControllerBean;
//import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.IObjectCollection;
import com.kingdee.bos.service.ServiceContext;
import com.kingdee.bos.service.IServiceContext;
import com.kingdee.eas.framework.Result;
import com.kingdee.eas.framework.LineResult;
import com.kingdee.eas.framework.exception.EASMultiException;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import java.math.BigDecimal;
import com.kingdee.eas.custom.financialinvoice.InvoiceInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.CoreBillBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.app.CoreBillBaseControllerBean;
import com.kingdee.eas.custom.financialinvoice.InvoiceCollection;
import com.kingdee.eas.framework.ObjectBaseCollection;
import java.util.List;



public abstract class AbstractInvoiceControllerBean extends CoreBillBaseControllerBean implements InvoiceController
{
    protected AbstractInvoiceControllerBean()
    {
    }

    protected BOSObjectType getBOSType()
    {
        return new BOSObjectType("80F193CE");
    }

    public InvoiceCollection getInvoiceCollection(Context ctx) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("4d65dbc1-8caf-4f79-9de7-8354d1829af5"), new Object[]{ctx});
            invokeServiceBefore(svcCtx);
            InvoiceCollection retValue = (InvoiceCollection)_getCollection(ctx, svcCtx);
            svcCtx.setMethodReturnValue(retValue);
            invokeServiceAfter(svcCtx);
            return retValue;
        } catch (BOSException ex) {
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected IObjectCollection _getCollection(Context ctx, IServiceContext svcCtx) throws BOSException
    {
        return super._getCollection(ctx, svcCtx);
    }

    public InvoiceCollection getInvoiceCollection(Context ctx, EntityViewInfo view) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("119b012b-9ca3-4761-b021-25feb02c285d"), new Object[]{ctx, view});
            invokeServiceBefore(svcCtx);
            InvoiceCollection retValue = (InvoiceCollection)_getCollection(ctx, svcCtx, view);
            svcCtx.setMethodReturnValue(retValue);
            invokeServiceAfter(svcCtx);
            return retValue;
        } catch (BOSException ex) {
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected IObjectCollection _getCollection(Context ctx, IServiceContext svcCtx, EntityViewInfo view) throws BOSException
    {
        return super._getCollection(ctx, svcCtx, view);
    }

    public InvoiceCollection getInvoiceCollection(Context ctx, String oql) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("818146fe-ab28-46a2-818a-d34276ef59b4"), new Object[]{ctx, oql});
            invokeServiceBefore(svcCtx);
            InvoiceCollection retValue = (InvoiceCollection)_getCollection(ctx, svcCtx, oql);
            svcCtx.setMethodReturnValue(retValue);
            invokeServiceAfter(svcCtx);
            return retValue;
        } catch (BOSException ex) {
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected IObjectCollection _getCollection(Context ctx, IServiceContext svcCtx, String oql) throws BOSException
    {
        return super._getCollection(ctx, svcCtx, oql);
    }

    public InvoiceInfo getInvoiceInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("e80c9098-5441-4224-9cc5-2fca150b483f"), new Object[]{ctx, pk});
            invokeServiceBefore(svcCtx);
            InvoiceInfo retValue = (InvoiceInfo)_getValue(ctx, pk);
            svcCtx.setMethodReturnValue(retValue);
            invokeServiceAfter(svcCtx);
            return retValue;
        } catch (BOSException ex) {
            throw ex;
        } catch (EASBizException ex0) {
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected IObjectValue _getValue(Context ctx, IObjectPK pk) throws BOSException, EASBizException
    {
        return super._getValue(ctx, pk);
    }

    public InvoiceInfo getInvoiceInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("05bd4f72-8663-45a6-b7d1-6d4a3de5c9c6"), new Object[]{ctx, pk, selector});
            invokeServiceBefore(svcCtx);
            InvoiceInfo retValue = (InvoiceInfo)_getValue(ctx, pk, selector);
            svcCtx.setMethodReturnValue(retValue);
            invokeServiceAfter(svcCtx);
            return retValue;
        } catch (BOSException ex) {
            throw ex;
        } catch (EASBizException ex0) {
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected IObjectValue _getValue(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        return super._getValue(ctx, pk, selector);
    }

    public InvoiceInfo getInvoiceInfo(Context ctx, String oql) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("e5262765-77c1-45e2-b4c0-5a524c7187d4"), new Object[]{ctx, oql});
            invokeServiceBefore(svcCtx);
            InvoiceInfo retValue = (InvoiceInfo)_getValue(ctx, oql);
            svcCtx.setMethodReturnValue(retValue);
            invokeServiceAfter(svcCtx);
            return retValue;
        } catch (BOSException ex) {
            throw ex;
        } catch (EASBizException ex0) {
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected IObjectValue _getValue(Context ctx, String oql) throws BOSException, EASBizException
    {
        return super._getValue(ctx, oql);
    }

    public boolean isExistInvoiceNumber(Context ctx, String invoiceNumber, String billId, String invoiceCode) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("dd57be19-fd88-4d02-8249-297a3978b56e"), new Object[]{ctx, invoiceNumber, billId, invoiceCode});
            invokeServiceBefore(svcCtx);
            boolean retValue = (boolean)_isExistInvoiceNumber(ctx, invoiceNumber, billId, invoiceCode);
            svcCtx.setMethodReturnValue(new Boolean(retValue));
            invokeServiceAfter(svcCtx);
            return retValue;
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract boolean _isExistInvoiceNumber(Context ctx, String invoiceNumber, String billId, String invoiceCode) throws BOSException;

    public boolean isExistInvoiceTotalMoney(Context ctx, String ApplyDept, String Applyer, String BillingDate, String BillId, BigDecimal TotalMoney) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("9e81cdf1-f9cb-4a27-82f7-2fe1810056ca"), new Object[]{ctx, ApplyDept, Applyer, BillingDate, BillId, TotalMoney});
            invokeServiceBefore(svcCtx);
            boolean retValue = (boolean)_isExistInvoiceTotalMoney(ctx, ApplyDept, Applyer, BillingDate, BillId, TotalMoney);
            svcCtx.setMethodReturnValue(new Boolean(retValue));
            invokeServiceAfter(svcCtx);
            return retValue;
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract boolean _isExistInvoiceTotalMoney(Context ctx, String ApplyDept, String Applyer, String BillingDate, String BillId, BigDecimal TotalMoney) throws BOSException;

    public List getCredentialLists(Context ctx, String InvoiceId, String startTime, String endTime, String paymentCode) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("fbf9edd1-b732-42b9-b5fa-cbb0d41abaf4"), new Object[]{ctx, InvoiceId, startTime, endTime, paymentCode});
            invokeServiceBefore(svcCtx);
            List retValue = (List)_getCredentialLists(ctx, InvoiceId, startTime, endTime, paymentCode);
            svcCtx.setMethodReturnValue(retValue);
            invokeServiceAfter(svcCtx);
            return retValue;
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract List _getCredentialLists(Context ctx, String InvoiceId, String startTime, String endTime, String paymentCode) throws BOSException;

    public void updPayStatus(Context ctx, List billIds, String type) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("52b6b479-a130-4eb7-b83b-0cf8fdb724db"), new Object[]{ctx, billIds, type});
            invokeServiceBefore(svcCtx);
            _updPayStatus(ctx, billIds, type);
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected void _updPayStatus(Context ctx, List billIds, String type) throws BOSException, EASBizException
    {    	
        return;
    }

    public void updDeleteStatus(Context ctx, List billIds) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("e9c92990-19e9-47ef-ba9a-55d4bbf620c9"), new Object[]{ctx, billIds});
            invokeServiceBefore(svcCtx);
            _updDeleteStatus(ctx, billIds);
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _updDeleteStatus(Context ctx, List billIds) throws BOSException;

    public List getVouchersLists(Context ctx, String InvoiceId, String voucherCode, String startTime, String endTime) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("b1a8e5f0-d7ea-4aa9-81c9-4213621c663d"), new Object[]{ctx, InvoiceId, voucherCode, startTime, endTime});
            invokeServiceBefore(svcCtx);
            List retValue = (List)_getVouchersLists(ctx, InvoiceId, voucherCode, startTime, endTime);
            svcCtx.setMethodReturnValue(retValue);
            invokeServiceAfter(svcCtx);
            return retValue;
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract List _getVouchersLists(Context ctx, String InvoiceId, String voucherCode, String startTime, String endTime) throws BOSException;

					protected com.kingdee.eas.basedata.org.OrgUnitInfo getMainBizOrgUnit(Context ctx,com.kingdee.eas.framework.CoreBillBaseInfo model) {
			Object obj = model.get("FICompany");
			if (obj != null && obj instanceof com.kingdee.eas.basedata.org.OrgUnitInfo)
				return (com.kingdee.eas.basedata.org.OrgUnitInfo)obj;
			else
				return null;
		}

    public CoreBillBaseCollection getCoreBillBaseCollection (Context ctx) throws BOSException
    {
    	return (CoreBillBaseCollection)(getInvoiceCollection(ctx).cast(CoreBillBaseCollection.class));
    }
    public CoreBillBaseCollection getCoreBillBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (CoreBillBaseCollection)(getInvoiceCollection(ctx, view).cast(CoreBillBaseCollection.class));
    }
    public CoreBillBaseCollection getCoreBillBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (CoreBillBaseCollection)(getInvoiceCollection(ctx, oql).cast(CoreBillBaseCollection.class));
    }
    public ObjectBaseCollection getObjectBaseCollection (Context ctx) throws BOSException
    {
    	return (ObjectBaseCollection)(getInvoiceCollection(ctx).cast(ObjectBaseCollection.class));
    }
    public ObjectBaseCollection getObjectBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (ObjectBaseCollection)(getInvoiceCollection(ctx, view).cast(ObjectBaseCollection.class));
    }
    public ObjectBaseCollection getObjectBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (ObjectBaseCollection)(getInvoiceCollection(ctx, oql).cast(ObjectBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx) throws BOSException
    {
    	return (CoreBaseCollection)(getInvoiceCollection(ctx).cast(CoreBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (CoreBaseCollection)(getInvoiceCollection(ctx, view).cast(CoreBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (CoreBaseCollection)(getInvoiceCollection(ctx, oql).cast(CoreBaseCollection.class));
    }
}