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
import com.kingdee.eas.framework.DataBase;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.custom.financialinvoice.app.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.framework.IDataBase;

public class Invoicetype extends DataBase implements IInvoicetype
{
    public Invoicetype()
    {
        super();
        registerInterface(IInvoicetype.class, this);
    }
    public Invoicetype(Context ctx)
    {
        super(ctx);
        registerInterface(IInvoicetype.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("C37D8C08");
    }
    private InvoicetypeController getController() throws BOSException
    {
        return (InvoicetypeController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public InvoicetypeInfo getInvoicetypeInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getInvoicetypeInfo(getContext(), pk);
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
    public InvoicetypeInfo getInvoicetypeInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getInvoicetypeInfo(getContext(), pk, selector);
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
    public InvoicetypeInfo getInvoicetypeInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getInvoicetypeInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public InvoicetypeCollection getInvoicetypeCollection() throws BOSException
    {
        try {
            return getController().getInvoicetypeCollection(getContext());
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
    public InvoicetypeCollection getInvoicetypeCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getInvoicetypeCollection(getContext(), view);
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
    public InvoicetypeCollection getInvoicetypeCollection(String oql) throws BOSException
    {
        try {
            return getController().getInvoicetypeCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}