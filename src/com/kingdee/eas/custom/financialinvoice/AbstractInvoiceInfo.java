package com.kingdee.eas.custom.financialinvoice;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractInvoiceInfo extends com.kingdee.eas.framework.CoreBillBaseInfo implements Serializable 
{
    public AbstractInvoiceInfo()
    {
        this("id");
    }
    protected AbstractInvoiceInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object:��Ʊ's �Ƿ�����ƾ֤property 
     */
    public boolean isFivouchered()
    {
        return getBoolean("Fivouchered");
    }
    public void setFivouchered(boolean item)
    {
        setBoolean("Fivouchered", item);
    }
    /**
     * Object: ��Ʊ 's ��֯ property 
     */
    public com.kingdee.eas.basedata.org.CompanyOrgUnitInfo getFICompany()
    {
        return (com.kingdee.eas.basedata.org.CompanyOrgUnitInfo)get("FICompany");
    }
    public void setFICompany(com.kingdee.eas.basedata.org.CompanyOrgUnitInfo item)
    {
        put("FICompany", item);
    }
    /**
     * Object:��Ʊ's ��Ʊ��property 
     */
    public String getInvoiceNumber()
    {
        return getString("InvoiceNumber");
    }
    public void setInvoiceNumber(String item)
    {
        setString("InvoiceNumber", item);
    }
    /**
     * Object:��Ʊ's ��Ʊ����property 
     */
    public String getInvoiceCode()
    {
        return getString("InvoiceCode");
    }
    public void setInvoiceCode(String item)
    {
        setString("InvoiceCode", item);
    }
    /**
     * Object:��Ʊ's ��Ʊ����property 
     */
    public java.util.Date getBillingDate()
    {
        return getDate("BillingDate");
    }
    public void setBillingDate(java.util.Date item)
    {
        setDate("BillingDate", item);
    }
    /**
     * Object:��Ʊ's ��Ʊ��λproperty 
     */
    public String getIssuingOffice()
    {
        return getString("IssuingOffice");
    }
    public void setIssuingOffice(String item)
    {
        setString("IssuingOffice", item);
    }
    /**
     * Object:��Ʊ's ��˰�ǼǺ�property 
     */
    public String getRegisterNumber()
    {
        return getString("RegisterNumber");
    }
    public void setRegisterNumber(String item)
    {
        setString("RegisterNumber", item);
    }
    /**
     * Object:��Ʊ's ˰��property 
     */
    public java.math.BigDecimal getTax()
    {
        return getBigDecimal("Tax");
    }
    public void setTax(java.math.BigDecimal item)
    {
        setBigDecimal("Tax", item);
    }
    /**
     * Object:��Ʊ's ����˰���property 
     */
    public java.math.BigDecimal getUnTax()
    {
        return getBigDecimal("UnTax");
    }
    public void setUnTax(java.math.BigDecimal item)
    {
        setBigDecimal("UnTax", item);
    }
    /**
     * Object:��Ʊ's �ϼƽ��property 
     */
    public java.math.BigDecimal getTotalAmount()
    {
        return getBigDecimal("TotalAmount");
    }
    public void setTotalAmount(java.math.BigDecimal item)
    {
        setBigDecimal("TotalAmount", item);
    }
    /**
     * Object:��Ʊ's ���״̬property 
     */
    public com.kingdee.eas.custom.financialinvoice.app.InvoiceEnum getStatus()
    {
        return com.kingdee.eas.custom.financialinvoice.app.InvoiceEnum.getEnum(getString("status"));
    }
    public void setStatus(com.kingdee.eas.custom.financialinvoice.app.InvoiceEnum item)
    {
		if (item != null) {
        setString("status", item.getValue());
		}
    }
    /**
     * Object:��Ʊ's ��עproperty 
     */
    public String getRemark()
    {
        return getString("Remark");
    }
    public void setRemark(String item)
    {
        setString("Remark", item);
    }
    /**
     * Object: ��Ʊ 's ��Ʊ��� property 
     */
    public com.kingdee.eas.custom.financialinvoice.InvoicetypeInfo getInvoiceType()
    {
        return (com.kingdee.eas.custom.financialinvoice.InvoicetypeInfo)get("InvoiceType");
    }
    public void setInvoiceType(com.kingdee.eas.custom.financialinvoice.InvoicetypeInfo item)
    {
        put("InvoiceType", item);
    }
    /**
     * Object:��Ʊ's �Ƿ����ɸ��property 
     */
    public boolean isIsCreatePayment()
    {
        return getBoolean("isCreatePayment");
    }
    public void setIsCreatePayment(boolean item)
    {
        setBoolean("isCreatePayment", item);
    }
    /**
     * Object: ��Ʊ 's ������ property 
     */
    public com.kingdee.eas.basedata.person.PersonInfo getApplyer()
    {
        return (com.kingdee.eas.basedata.person.PersonInfo)get("Applyer");
    }
    public void setApplyer(com.kingdee.eas.basedata.person.PersonInfo item)
    {
        put("Applyer", item);
    }
    /**
     * Object:��Ʊ's �Ƿ�����ƾ֤property 
     */
    public boolean isIsCreateVoucher()
    {
        return getBoolean("isCreateVoucher");
    }
    public void setIsCreateVoucher(boolean item)
    {
        setBoolean("isCreateVoucher", item);
    }
    /**
     * Object:��Ʊ's ˰��property 
     */
    public String getTaxRate()
    {
        return getString("TaxRate");
    }
    public void setTaxRate(String item)
    {
        setString("TaxRate", item);
    }
    /**
     * Object: ��Ʊ 's �������� property 
     */
    public com.kingdee.eas.basedata.org.AdminOrgUnitInfo getApplyDept()
    {
        return (com.kingdee.eas.basedata.org.AdminOrgUnitInfo)get("ApplyDept");
    }
    public void setApplyDept(com.kingdee.eas.basedata.org.AdminOrgUnitInfo item)
    {
        put("ApplyDept", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("80F193CE");
    }
}