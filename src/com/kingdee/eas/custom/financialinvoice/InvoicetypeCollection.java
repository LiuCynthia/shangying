package com.kingdee.eas.custom.financialinvoice;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class InvoicetypeCollection extends AbstractObjectCollection 
{
    public InvoicetypeCollection()
    {
        super(InvoicetypeInfo.class);
    }
    public boolean add(InvoicetypeInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(InvoicetypeCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(InvoicetypeInfo item)
    {
        return removeObject(item);
    }
    public InvoicetypeInfo get(int index)
    {
        return(InvoicetypeInfo)getObject(index);
    }
    public InvoicetypeInfo get(Object key)
    {
        return(InvoicetypeInfo)getObject(key);
    }
    public void set(int index, InvoicetypeInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(InvoicetypeInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(InvoicetypeInfo item)
    {
        return super.indexOf(item);
    }
}