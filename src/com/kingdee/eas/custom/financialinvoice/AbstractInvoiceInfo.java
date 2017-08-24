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
     * Object:发票's 是否生成凭证property 
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
     * Object: 发票 's 组织 property 
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
     * Object:发票's 发票号property 
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
     * Object:发票's 发票代码property 
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
     * Object:发票's 开票日期property 
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
     * Object:发票's 开票单位property 
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
     * Object:发票's 纳税登记号property 
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
     * Object:发票's 税额property 
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
     * Object:发票's 不含税金额property 
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
     * Object:发票's 合计金额property 
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
     * Object:发票's 审核状态property 
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
     * Object:发票's 备注property 
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
     * Object: 发票 's 发票类别 property 
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
     * Object:发票's 是否生成付款单property 
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
     * Object: 发票 's 报销人 property 
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
     * Object:发票's 是否生成凭证property 
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
     * Object:发票's 税率property 
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
     * Object: 发票 's 报销部门 property 
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