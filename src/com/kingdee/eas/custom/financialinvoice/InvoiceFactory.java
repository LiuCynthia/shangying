package com.kingdee.eas.custom.financialinvoice;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class InvoiceFactory
{
    private InvoiceFactory()
    {
    }
    public static com.kingdee.eas.custom.financialinvoice.IInvoice getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.custom.financialinvoice.IInvoice)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("80F193CE") ,com.kingdee.eas.custom.financialinvoice.IInvoice.class);
    }
    
    public static com.kingdee.eas.custom.financialinvoice.IInvoice getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.custom.financialinvoice.IInvoice)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("80F193CE") ,com.kingdee.eas.custom.financialinvoice.IInvoice.class, objectCtx);
    }
    public static com.kingdee.eas.custom.financialinvoice.IInvoice getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.custom.financialinvoice.IInvoice)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("80F193CE"));
    }
    public static com.kingdee.eas.custom.financialinvoice.IInvoice getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.custom.financialinvoice.IInvoice)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("80F193CE"));
    }
}