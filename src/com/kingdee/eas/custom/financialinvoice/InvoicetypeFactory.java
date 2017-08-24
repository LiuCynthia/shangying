package com.kingdee.eas.custom.financialinvoice;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class InvoicetypeFactory
{
    private InvoicetypeFactory()
    {
    }
    public static com.kingdee.eas.custom.financialinvoice.IInvoicetype getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.custom.financialinvoice.IInvoicetype)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("C37D8C08") ,com.kingdee.eas.custom.financialinvoice.IInvoicetype.class);
    }
    
    public static com.kingdee.eas.custom.financialinvoice.IInvoicetype getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.custom.financialinvoice.IInvoicetype)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("C37D8C08") ,com.kingdee.eas.custom.financialinvoice.IInvoicetype.class, objectCtx);
    }
    public static com.kingdee.eas.custom.financialinvoice.IInvoicetype getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.custom.financialinvoice.IInvoicetype)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("C37D8C08"));
    }
    public static com.kingdee.eas.custom.financialinvoice.IInvoicetype getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.custom.financialinvoice.IInvoicetype)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("C37D8C08"));
    }
}