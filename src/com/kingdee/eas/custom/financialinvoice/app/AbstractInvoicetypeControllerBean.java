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
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.ObjectBaseCollection;
import com.kingdee.eas.custom.financialinvoice.InvoicetypeInfo;
import com.kingdee.eas.framework.app.DataBaseControllerBean;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.custom.financialinvoice.InvoicetypeCollection;
import com.kingdee.eas.framework.DataBaseCollection;



public abstract class AbstractInvoicetypeControllerBean extends DataBaseControllerBean implements InvoicetypeController
{
    protected AbstractInvoicetypeControllerBean()
    {
    }

    protected BOSObjectType getBOSType()
    {
        return new BOSObjectType("C37D8C08");
    }

    public InvoicetypeInfo getInvoicetypeInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("099bed25-78b6-468c-9fe5-7bba6283b61c"), new Object[]{ctx, pk});
            invokeServiceBefore(svcCtx);
            InvoicetypeInfo retValue = (InvoicetypeInfo)_getValue(ctx, pk);
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

    public InvoicetypeInfo getInvoicetypeInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("1a3f661f-5f4b-4241-9d09-91e6cac1dfde"), new Object[]{ctx, pk, selector});
            invokeServiceBefore(svcCtx);
            InvoicetypeInfo retValue = (InvoicetypeInfo)_getValue(ctx, pk, selector);
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

    public InvoicetypeInfo getInvoicetypeInfo(Context ctx, String oql) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("2795a082-b23c-4d3d-aadf-27d633f11397"), new Object[]{ctx, oql});
            invokeServiceBefore(svcCtx);
            InvoicetypeInfo retValue = (InvoicetypeInfo)_getValue(ctx, oql);
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

    public InvoicetypeCollection getInvoicetypeCollection(Context ctx) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("f0c74b91-6bd0-48f4-8460-2fe51685a933"), new Object[]{ctx});
            invokeServiceBefore(svcCtx);
            InvoicetypeCollection retValue = (InvoicetypeCollection)_getCollection(ctx, svcCtx);
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

    public InvoicetypeCollection getInvoicetypeCollection(Context ctx, EntityViewInfo view) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("fed89f70-26f9-40f8-a36f-5eae2de2cbae"), new Object[]{ctx, view});
            invokeServiceBefore(svcCtx);
            InvoicetypeCollection retValue = (InvoicetypeCollection)_getCollection(ctx, svcCtx, view);
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

    public InvoicetypeCollection getInvoicetypeCollection(Context ctx, String oql) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("d7d80d1f-9664-42ab-9e5e-e0c1edb5d858"), new Object[]{ctx, oql});
            invokeServiceBefore(svcCtx);
            InvoicetypeCollection retValue = (InvoicetypeCollection)_getCollection(ctx, svcCtx, oql);
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

    public DataBaseCollection getDataBaseCollection (Context ctx) throws BOSException
    {
    	return (DataBaseCollection)(getInvoicetypeCollection(ctx).cast(DataBaseCollection.class));
    }
    public DataBaseCollection getDataBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (DataBaseCollection)(getInvoicetypeCollection(ctx, view).cast(DataBaseCollection.class));
    }
    public DataBaseCollection getDataBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (DataBaseCollection)(getInvoicetypeCollection(ctx, oql).cast(DataBaseCollection.class));
    }
    public ObjectBaseCollection getObjectBaseCollection (Context ctx) throws BOSException
    {
    	return (ObjectBaseCollection)(getInvoicetypeCollection(ctx).cast(ObjectBaseCollection.class));
    }
    public ObjectBaseCollection getObjectBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (ObjectBaseCollection)(getInvoicetypeCollection(ctx, view).cast(ObjectBaseCollection.class));
    }
    public ObjectBaseCollection getObjectBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (ObjectBaseCollection)(getInvoicetypeCollection(ctx, oql).cast(ObjectBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx) throws BOSException
    {
    	return (CoreBaseCollection)(getInvoicetypeCollection(ctx).cast(CoreBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (CoreBaseCollection)(getInvoicetypeCollection(ctx, view).cast(CoreBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (CoreBaseCollection)(getInvoicetypeCollection(ctx, oql).cast(CoreBaseCollection.class));
    }
}