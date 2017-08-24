/**
 * output package name
 */
package com.kingdee.eas.custom.financialinvoice.client;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;
import org.jasen.core.calculators.ChiSquaredCalculator;

import com.ibm.as400.util.commtrace.Data;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.swing.event.DataChangeEvent;
import com.kingdee.bos.ctrl.swing.event.SelectorEvent;
import com.kingdee.bos.dao.AbstractObjectValue;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ui.face.IUIWindow;
import com.kingdee.bos.ui.face.UIException;
import com.kingdee.bos.ui.face.UIFactory;
import com.kingdee.eas.basedata.org.AdminOrgUnitInfo;
import com.kingdee.eas.basedata.org.CompanyOrgUnitInfo;
import com.kingdee.eas.basedata.person.PersonInfo;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.common.client.UIContext;
import com.kingdee.eas.common.client.UIFactoryName;
import com.kingdee.eas.custom.financialinvoice.Invoice;
import com.kingdee.eas.custom.financialinvoice.InvoiceFactory;
import com.kingdee.eas.custom.financialinvoice.InvoiceInfo;
import com.kingdee.eas.custom.financialinvoice.app.InvoiceEnum;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.MsgBox;
import com.kingdee.util.StringUtils;

/**
 * output class name
 */
public class InvoiceEditUI extends AbstractInvoiceEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(InvoiceEditUI.class);
    
    /**
     * output class constructor
     */
    public InvoiceEditUI() throws Exception
    {
        super();
    }
   
    /**
     * output loadFields method
     */
    public void loadFields()
    {
        super.loadFields();
    }
    
    /**
     * output storeFields method
     */
    public void storeFields()
    {
        super.storeFields();
    }

	public void onLoad() throws Exception {
		super.onLoad();
		txtTotalAmount.setEnabled(false);
		contstatus.setVisible(false);
		contCreator.setVisible(false);
		contCreator.setVisible(false);
		contCreateTime.setVisible(false);
		contLastUpdateTime.setVisible(false);
		contLastUpdateUser.setVisible(false);
		contNumber.setVisible(false);
		contBizDate.setVisible(false);
		contDescription.setVisible(false);
		contAuditor.setVisible(false);
		btnMultiapprove.setVisible(false);
		btnAuditResult.setVisible(false);
		btnWorkFlowG.setVisible(false);
		actionNext.setVisible(true);
		actionFirst.setVisible(true);
		actionPre.setVisible(true);
		actionLast.setVisible(true);
		actionTraceDown.setVisible(true);
		//�ж��Ƿ��Ǹ��½���
		if(this.oprtState.equals("UPDATE")){
			contFICompany.setEnabled(false);
			contUnTax.setEnabled(false);
			contTaxRate.setEnabled(false);
			contTax.setEnabled(false);
			contTotalAmount.setEnabled(false);
		}
		if(prmtFICompany.getValue()!= null){
			CompanyOrgUnitInfo company=(CompanyOrgUnitInfo)prmtFICompany.getValue();
			EntityViewInfo view=new EntityViewInfo();
			FilterInfo filter=new FilterInfo();
			filter.getFilterItems().add(new FilterItemInfo("CU.ID",company.getCU().getId().toString(),CompareType.EQUALS));
			view.setFilter(filter);
			prmtApplyDept.setEntityViewInfo(view);
			prmtApplyer.setEntityViewInfo(view);
		}
	}
    
	//���ݵ�У��

	protected void verifyInput(ActionEvent e) throws Exception {
    	if(prmtFICompany.getText() == null || "".equals(txtInvoiceNumber.getText())){
    		MsgBox.showError("��֯��������Ϊ��");
    		prmtFICompany.requestFocus();
    		SysUtil.abort();
    	}
    	if(prmtInvoiceType.getText() == null || "".equals(prmtInvoiceType.getText())){
    		MsgBox.showError("��Ʊ�����Ϊ��");
    		prmtInvoiceType.requestFocus();
    		SysUtil.abort();
    	}
    	if(txtInvoiceNumber.getText() == null || "".equals(txtInvoiceNumber.getText())){
    		MsgBox.showError("��Ʊ�Ų���Ϊ��");
    		txtInvoiceNumber.requestFocus();
    		SysUtil.abort();
    	}
    	if(txtInvoiceCode.getText() == null || "".equals(txtInvoiceCode.getText())){
    		MsgBox.showError("��Ʊ���벻��Ϊ��");
    		txtInvoiceCode.requestFocus();
    		SysUtil.abort();
    	}
    	if(pkBillingDate.getText() == null || "".equals(pkBillingDate.getText())){
    		MsgBox.showError("��Ʊʱ�䲻��Ϊ��");
    		pkBillingDate.requestFocus();
    		SysUtil.abort();
    	}
    	if(txtIssuingOffice.getText() == null || "".equals(txtIssuingOffice.getText())){
    		MsgBox.showError("��Ʊ��λ����Ϊ��");
    		txtIssuingOffice.requestFocus();
    		SysUtil.abort();
    	}
    	if(txtTax.getText() == null || StringUtils.isEmpty(txtTax.getText())){
    		MsgBox.showError("˰���Ϊ��");
    		txtTax.requestFocus();
    		SysUtil.abort();
    	}
    	if(txtUnTax.getText() == null || StringUtils.isEmpty(txtUnTax.getText())){
    		MsgBox.showError("����˰���Ϊ��");
    		txtUnTax.requestFocus();
    		SysUtil.abort();
    	}
    	if(prmtApplyer.getValue() == null){
    		MsgBox.showError("�����˲���Ϊ��");
    		prmtApplyer.requestFocus();
    		SysUtil.abort();
    	}
    	if(prmtApplyDept.getValue() == null){
    		MsgBox.showError("���벿�Ų���Ϊ��");
    		prmtApplyDept.requestFocus();
    		SysUtil.abort();
    	}
    	boolean isExistInvoiceNumber= true;
    	if(this.getOprtState()==OprtState.EDIT){
    		isExistInvoiceNumber=InvoiceFactory.getRemoteInstance().isExistInvoiceNumber(txtInvoiceCode.getText(), txtInvoiceNumber.getText(), editData.getId().toString());
    	}else{
    		isExistInvoiceNumber=InvoiceFactory.getRemoteInstance().isExistInvoiceNumber(txtInvoiceCode.getText(), txtInvoiceNumber.getText(), null);
    	}
 	    if(isExistInvoiceNumber){
 	    	String error = "�Ѿ����ڷ�Ʊ���룺"+txtInvoiceCode.getText()+",��Ʊ�ţ�"+txtInvoiceNumber.getText()+"�ĵ��ݣ�����ȷ�ϣ�";
 	    	MsgBox.showError(error);
  		    SysUtil.abort();
 	    }
 	    
 	    boolean isExistInvoiceTotalMoney = false;
 	    AdminOrgUnitInfo unit=(AdminOrgUnitInfo)prmtApplyDept.getValue();
 	    PersonInfo person=(PersonInfo)prmtApplyer.getValue();
 	    if(this.getOprtState()==OprtState.EDIT){
 	    	isExistInvoiceTotalMoney = InvoiceFactory.getRemoteInstance().isExistInvoiceTotalMoney(unit.getId().toString(),person.getId().toString(),pkBillingDate.getText(),editData.getId().toString(),txtTotalAmount.getBigDecimalValue());
 	    }else{
 	    	isExistInvoiceTotalMoney = InvoiceFactory.getRemoteInstance().isExistInvoiceTotalMoney(unit.getId().toString(),person.getId().toString(),pkBillingDate.getText(),null,txtTotalAmount.getBigDecimalValue());
 	    }
 	    if(isExistInvoiceTotalMoney){
 	    	MsgBox.showConfirm2("�Ѿ����ڽ��Ϊ"+txtTotalAmount.getText()+"�ķ�Ʊ,�Ƿ�ȷ���ύ");
 	    }
	}
    

	protected void txtTax_dataChanged(DataChangeEvent e) throws Exception {
    	BigDecimal amount=BigDecimal.ZERO;
		if(txtTax.getBigDecimalValue() != null){
			amount=amount.add(txtTax.getBigDecimalValue());
		}
		if( txtUnTax.getBigDecimalValue() != null){
			amount=amount.add(txtUnTax.getBigDecimalValue());
		}
		txtTotalAmount.setValue(amount);
	}
    

	protected void txtUnTax_dataChanged(DataChangeEvent e) throws Exception {
		BigDecimal amount=BigDecimal.ZERO;
		if(txtTax.getBigDecimalValue() != null){
			amount=amount.add(txtTax.getBigDecimalValue());
		}
		if( txtUnTax.getBigDecimalValue() != null){
			amount=amount.add(txtUnTax.getBigDecimalValue());
		}
		txtTotalAmount.setValue(amount);
	}
    
	
	protected void btnChcek_actionPerformed(ActionEvent e) throws Exception {
    	if(txtInvoiceNumber.getText() == null || "".equals(txtInvoiceNumber.getText())){
    		MsgBox.showError("��Ʊ�Ų���Ϊ��");
    		txtInvoiceNumber.requestFocus();
    		SysUtil.abort();
    	}
    	if(txtInvoiceCode.getText() == null || "".equals(txtInvoiceCode.getText())){
    		MsgBox.showError("��Ʊ���벻��Ϊ��");
    		txtInvoiceCode.requestFocus();
    		SysUtil.abort();
    	}
    	boolean isExistInvoiceNumber= true;
    	if(this.getOprtState()==OprtState.EDIT){
    		isExistInvoiceNumber=InvoiceFactory.getRemoteInstance().isExistInvoiceNumber(txtInvoiceCode.getText(), txtInvoiceNumber.getText(), editData.getId().toString());
    	}else{
    		isExistInvoiceNumber=InvoiceFactory.getRemoteInstance().isExistInvoiceNumber(txtInvoiceCode.getText(), txtInvoiceNumber.getText(), null);
    	}
    	if(!isExistInvoiceNumber){
    		MsgBox.showConfirm2("��Ʊ�����뷢Ʊ�ż��ͨ��");
    		SysUtil.abort();
    	}else{
    		UIContext uiContext = new UIContext(this);
    		uiContext.put("invoiceCode", txtInvoiceCode.getText().trim());
    		uiContext.put("invoiceNumber", txtInvoiceNumber.getText().trim());
    		try {
    			IUIWindow create = UIFactory.createUIFactory(UIFactoryName.MODEL).create(
    			InvoiceListUI.class.getName(), uiContext, null, OprtState.VIEW);
    			create.show();
    		} catch (UIException e1) {
    			e1.printStackTrace();
    		}
    	}
    	
		super.btnChcek_actionPerformed(e);
	}
    
	protected void prmtApplyDept_willShow(SelectorEvent e) throws Exception {
		if(prmtFICompany.getValue()!= null){
			CompanyOrgUnitInfo company=(CompanyOrgUnitInfo)prmtFICompany.getValue();
			EntityViewInfo view=new EntityViewInfo();
			FilterInfo filter=new FilterInfo();
			filter.getFilterItems().add(new FilterItemInfo("CU.ID",company.getCU().getId().toString(),CompareType.EQUALS));
			view.setFilter(filter);
			prmtApplyDept.setEntityViewInfo(view);
			prmtApplyer.setEntityViewInfo(view);
		}
	}
	
	public void actionCopy_actionPerformed(ActionEvent e) throws Exception {
		super.actionCopy_actionPerformed(e);
		chkisCreatePayment.setSelected(false);
		chkisCreateVoucher.setSelected(false);
		
	}

	/**
     * output createNewDetailData method
     */
    protected IObjectValue createNewDetailData(KDTable table)
    {
        return null;
    }
    
	/**
     * output createNewData method
     */
    protected com.kingdee.bos.dao.IObjectValue createNewData()
    {
        com.kingdee.eas.custom.financialinvoice.InvoiceInfo objectValue = new com.kingdee.eas.custom.financialinvoice.InvoiceInfo();
				if (com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum("Company")) != null && com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum("Company")).getBoolean("isBizUnit"))
			objectValue.put("FICompany",com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum("Company")));
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));
        objectValue.setApplyer(SysContext.getSysContext().getCurrentUserInfo().getPerson());
        objectValue.setApplyDept(SysContext.getSysContext().getCurrentAdminUnit());
        objectValue.setStatus(InvoiceEnum.submit);
        objectValue.setCU(SysContext.getSysContext().getCurrentCtrlUnit());
        return objectValue;
    }

    
    
}