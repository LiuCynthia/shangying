/**
 * output package name
 */
package com.kingdee.eas.custom.financialinvoice.client;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectBlock;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectManager;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.data.SortType;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SorterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ui.face.IUIWindow;
import com.kingdee.bos.ui.face.UIException;
import com.kingdee.bos.ui.face.UIFactory;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.basedata.org.CompanyOrgUnitInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.common.client.UIContext;
import com.kingdee.eas.common.client.UIFactoryName;
import com.kingdee.eas.custom.financialinvoice.InvoiceFactory;
import com.kingdee.eas.custom.financialinvoice.InvoiceInfo;
import com.kingdee.eas.custom.financialinvoice.app.InvoiceEnum;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.MsgBox;

/**
 * output class name
 */
public class InvoiceListUI extends AbstractInvoiceListUI
{
	private static final Logger logger = CoreUIObject.getLogger(InvoiceListUI.class);
	private IUIWindow precisionConfigWindow = null;

	/**
	 * output class constructor
	 */
	public InvoiceListUI() throws Exception
	{
		super();
	}

	public void onLoad() throws Exception {
		super.onLoad();
		tblMain.getSelectManager().setSelectMode(KDTSelectManager.MULTIPLE_ROW_SELECT);
		btnDelVoucher.setVisible(true);
		btnDelVoucher.setEnabled(true);
		btnAuditResult.setVisible(false);
		actionAuditResult.setVisible(false);
		actionPrintPreview.setVisible(true);
		actionPrintPreview.setEnabled(true);
		actionVoucher.setVisible(true);
		btnVoucher.setVisible(true);
		menuItemVoucher.setVisible(true);
		btnVoucher.setEnabled(true);
		menuItemVoucher.setEnabled(true);
		
	}

	protected void beforeExcutQuery(EntityViewInfo ev) {
		String code=getUIContext().get("invoiceCode") == null ? null:getUIContext().get("invoiceCode").toString();
		String number=getUIContext().get("invoiceNumber") == null ? null :getUIContext().get("invoiceNumber").toString();
		if(code != null && number != null){
			FilterInfo filter=ev.getFilter();
			String mark="";
			if(filter == null){
				filter=new FilterInfo();
				mark=" #0 and #1";
			}else{
				mark=filter.getMaskString()+" and (#"+filter.getFilterItems().size()+" and #"+(filter.getFilterItems().size()+1)+" )";
			}

			filter.getFilterItems().add(new FilterItemInfo("InvoiceCode",code,CompareType.EQUALS));
			filter.getFilterItems().add(new FilterItemInfo("InvoiceNumber",number,CompareType.EQUALS));
			filter.setMaskString(mark);
			ev.setFilter(filter);
		}		
		SorterItemInfo sorter = new SorterItemInfo("number");
		sorter.setSortType(SortType.DESCEND);
		ev.getSorter().add(sorter);
		super.beforeExcutQuery(ev);
	}

	public void actionEdit_actionPerformed(ActionEvent e) throws Exception {
		String id=getSelectedKeyValue();
		InvoiceInfo invoice = InvoiceFactory.getRemoteInstance().getInvoiceInfo(new ObjectUuidPK(id));
		if(invoice!=null && invoice.getStatus().getValue().equals("2")){
			MsgBox.showError("数据已审核无法修改");
			SysUtil.abort();
		}else if(invoice!=null && invoice.getStatus().getValue().equals("-1")){
			MsgBox.showError("数据已废弃无法修改");
			SysUtil.abort();
		}
		super.actionEdit_actionPerformed(e);
	}

	public void actionRemove_actionPerformed(ActionEvent e) throws Exception {
		KDTSelectBlock sb;
		int size = tblMain.getSelectManager().size(); // 获取选择块的总个数
		List idList =  new ArrayList();
		for (int i = 0; i < size; i++){
			// 获取第i个选择块
			sb = tblMain.getSelectManager().get(i);
			for(int j=sb.getBeginRow();j<=sb.getEndRow();j++){
				String id = (String)tblMain.getRow(j).getCell("id").getValue();
				InvoiceInfo invoice = InvoiceFactory.getRemoteInstance().getInvoiceInfo(new ObjectUuidPK(id));
				if(invoice.getStatus().getValue().equals("2")){
					MsgBox.showError("数据已审核无法删除");
					SysUtil.abort();
				}else if(invoice.isFivouchered()){
					MsgBox.showError("已经生成付款单无法删除");
					SysUtil.abort();
				}else if(invoice.getStatus().getValue().equals("-1")){
					MsgBox.showError("数据已删除无须再次删除");
					SysUtil.abort();
				}else{
					idList.add(id);
				}
			}
		} 
		if(idList.size()>0){
			InvoiceFactory.getRemoteInstance().updDeleteStatus(idList);
			MsgBox.showInfo("删除成功");
			super.refreshList();
		}
	}

	public void actionAudited_actionPerformed(ActionEvent e) throws Exception {
		KDTSelectBlock sb;
		int size = tblMain.getSelectManager().size(); // 获取选择块的总个数
		CoreBaseCollection cols=new CoreBaseCollection();
		for (int i = 0; i < size; i++){
			// 获取第i个选择块
			sb = tblMain.getSelectManager().get(i);
			for(int j=sb.getBeginRow();j<=sb.getEndRow();j++){
				String id = (String)tblMain.getRow(j).getCell("id").getValue();
				InvoiceInfo invoice = InvoiceFactory.getRemoteInstance().getInvoiceInfo(new ObjectUuidPK(id));
				if(invoice.getStatus().getValue().equals("1") || invoice.getStatus().getValue().equals("3")){
					invoice.setStatus(InvoiceEnum.Audited);
					cols.add(invoice);
				}
			}
		} 
		if(cols.size()>0){
			InvoiceFactory.getRemoteInstance().updateBatchData(cols);
			MsgBox.showInfo("审核成功");
			super.refreshList();
		}else{
			MsgBox.showError("数据已审核或者已废弃无须再次审核。");
			SysUtil.abort();
		}

	}


	public void actionUnAudited_actionPerformed(ActionEvent e) throws Exception {
		KDTSelectBlock sb;
		int size = tblMain.getSelectManager().size(); // 获取选择块的总个数
		CoreBaseCollection cols=new CoreBaseCollection();
		for (int i = 0; i < size; i++){
			// 获取第i个选择块
			sb = tblMain.getSelectManager().get(i);
			for(int j=sb.getBeginRow();j<=sb.getEndRow();j++){
				String id = (String)tblMain.getRow(j).getCell("id").getValue();
				InvoiceInfo invoice = InvoiceFactory.getRemoteInstance().getInvoiceInfo(new ObjectUuidPK(id));
				if(invoice.getStatus().getValue().equals("2")){
					invoice.setStatus(InvoiceEnum.submit);
					cols.add(invoice);
				}

			}
		} 
		if(cols.size()>0){
			InvoiceFactory.getRemoteInstance().updateBatchData(cols);
			MsgBox.showInfo("反审核成功");
			super.refreshList();
		}else{
			MsgBox.showError("数据未审核或者已废弃无须再次反审核。");
			SysUtil.abort();
		}
	}

	public void actionCreateTo_actionPerformed(ActionEvent e) throws Exception {
		KDTSelectBlock sb;
		int size = tblMain.getSelectManager().size(); // 获取选择块的总个数
		List billIds=new ArrayList();
		for (int i = 0; i < size; i++){
			// 获取第i个选择块
			sb = tblMain.getSelectManager().get(i);
			for(int j=sb.getBeginRow();j<=sb.getEndRow();j++){
				String id = (String)tblMain.getRow(j).getCell("id").getValue();
//				InvoiceInfo invoice = InvoiceFactory.getRemoteInstance().getInvoiceInfo(new ObjectUuidPK(id));
//				if(invoice.getStatus().getValue()==InvoiceEnum.submit.getValue()){
//					MsgBox.showInfo("发票未审核无法生成付款单！");
//				}else if(invoice.getStatus().getValue()==InvoiceEnum.delete.getValue()){
//					MsgBox.showInfo("发票已废弃");
//				}else{
					billIds.add(id);
//				}
			}
		}

		if(billIds.size()>0){
			super.actionCreateTo_actionPerformed(e);
			InvoiceFactory.getRemoteInstance().updPayStatus(billIds,"1");
			refreshList();
		}
	}


public void actionVoucher_actionPerformed(ActionEvent e)throws Exception {
		KDTSelectBlock sb;
		int size = tblMain.getSelectManager().size(); // 获取选择块的总个数
		List billIds=new ArrayList();
		for (int i = 0; i < size; i++){
			// 获取第i个选择块
			sb = tblMain.getSelectManager().get(i);
			for(int j=sb.getBeginRow();j<=sb.getEndRow();j++){
				String id = (String)tblMain.getRow(j).getCell("id").getValue();
//				InvoiceInfo invoice = InvoiceFactory.getRemoteInstance().getInvoiceInfo(new ObjectUuidPK(id));
//				if(invoice.getStatus().getValue()==InvoiceEnum.submit.getValue()){
//					MsgBox.showInfo("发票未审核无法生成凭证！");
//				}else if(invoice.getStatus().getValue()==InvoiceEnum.delete.getValue()){
//					MsgBox.showInfo("发票已废弃");
//				}else{
					billIds.add(id);
//				}
			}
		}
		if(billIds.size()>0){
			super.actionVoucher_actionPerformed(e);
			InvoiceFactory.getRemoteInstance().updPayStatus(billIds,"2");
			refreshList();
		}
	}

	public void actionCredentials_actionPerformed(ActionEvent e)throws Exception {
		String id=getSelectedKeyValue();
		if(id=="" || id==null){
			MsgBox.showInfo("请选择一条记录");
		}else{
			UIContext uiContext = new UIContext(this);
			uiContext.put("id", id);
			try {
				IUIWindow create = UIFactory.createUIFactory(UIFactoryName.MODEL).create(
						PaymentQueryUI.class.getName(), uiContext, null, OprtState.VIEW);
				create.show();
			} catch (UIException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void actionDelVoucher_actionPerformed(ActionEvent e)throws Exception {
		KDTSelectBlock sb;
		int size = tblMain.getSelectManager().size(); // 获取选择块的总个数
		List billIds=new ArrayList();
		for (int i = 0; i < size; i++){
			// 获取第i个选择块
			sb = tblMain.getSelectManager().get(i);
			for(int j=sb.getBeginRow();j<=sb.getEndRow();j++){
				String id = (String)tblMain.getRow(j).getCell("id").getValue();
				InvoiceInfo invoice = InvoiceFactory.getRemoteInstance().getInvoiceInfo(new ObjectUuidPK(id));
				if(invoice.isFivouchered()){
					billIds.add(id);
				}else{
					MsgBox.showInfo("未生成凭证无须删除！");
				}
			}
		}
		if(billIds.size()>0){
			super.actionDelVoucher_actionPerformed(e);
			InvoiceFactory.getRemoteInstance().updPayStatus(billIds,"3");
			refreshList();
		}
	}

	public void actionUpdate_actionPerformed(ActionEvent e) throws Exception {
		checkSelected();
		checkObjectExists();
        UIContext uiContext = new UIContext(this);
        uiContext.put("ID", getSelectedKeyValue());
	    prepareUIContext(uiContext, e);
		IUIWindow uiWindow = null;
		if ((SwingUtilities.getWindowAncestor(this) != null) && (SwingUtilities.getWindowAncestor(this) instanceof JDialog)){
			uiWindow = UIFactory.createUIFactory("com.kingdee.eas.base.uiframe.client.UIModelDialogFactory").create(getEditUIName(), uiContext, null, OprtState.EDIT);
		}else{
			uiWindow = UIFactory.createUIFactory(getEditUIModal()).create(getEditUIName(), uiContext, null, "UPDATE");
		}
		uiWindow.show();
	}
	
	private void checkObjectExists()throws BOSException, EASBizException, Exception{
		if (getSelectedKeyValue() == null){
			return;
		}
		if (getBizInterface().exists(new ObjectUuidPK(BOSUuid.read(getSelectedKeyValue()))))
			return;
	     	refreshList();
	     	throw new EASBizException(EASBizException.CHECKEXIST);
	   }

	public void actionVouchers_actionPerformed(ActionEvent e) throws Exception {
		String id=getSelectedKeyValue();
		if(id=="" || id==null){
			MsgBox.showInfo("请选择一条记录");
		}else{
			UIContext uiContext = new UIContext(this);
			uiContext.put("id", id);
			try {
				IUIWindow create = UIFactory.createUIFactory(UIFactoryName.MODEL).create(
						VouchersQueryUI.class.getName(), uiContext, null, OprtState.VIEW);
				create.show();
			} catch (UIException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	
	
}