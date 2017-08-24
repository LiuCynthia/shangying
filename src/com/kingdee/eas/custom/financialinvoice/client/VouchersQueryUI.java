/**
 * output package name
 */
package com.kingdee.eas.custom.financialinvoice.client;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.kingdee.bos.metadata.bot.BOTRelationFactory;
import com.kingdee.bos.metadata.bot.BOTRelationInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ui.face.IUIWindow;
import com.kingdee.bos.ui.face.UIException;
import com.kingdee.bos.ui.face.UIFactory;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectBlock;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectManager;
import com.kingdee.bos.ctrl.kdf.table.event.KDTMouseEvent;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.common.client.UIContext;
import com.kingdee.eas.common.client.UIFactoryName;
import com.kingdee.eas.custom.financialinvoice.InvoiceFactory;
import com.kingdee.eas.custom.financialinvoice.InvoiceInfo;
import com.kingdee.eas.fi.cas.PaymentBillInfo;
import com.kingdee.eas.fi.gl.VoucherInfo;
import com.kingdee.eas.fi.gl.client.VoucherEditUI;
import com.kingdee.eas.framework.*;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.MsgBox;

/**
 * output class name
 */
public class VouchersQueryUI extends AbstractVouchersQueryUI
{
    private static final Logger logger = CoreUIObject.getLogger(VouchersQueryUI.class);
    
    /**
     * output class constructor
     */
    public VouchersQueryUI() throws Exception
    {
        super();
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
		kDTable1.checkParsed();
	}
    
    
    
	protected void kDTable1_tableClicked(KDTMouseEvent e) throws Exception {
		if(e.getClickCount()==2){
			KDTSelectBlock sb;
			int size = kDTable1.getSelectManager().size(); // 获取选择块的总个数
			List idList =  new ArrayList();
			for (int i = 0; i < size; i++){
				// 获取第i个选择块
				sb = kDTable1.getSelectManager().get(i);
				for(int j=sb.getBeginRow();j<=sb.getEndRow();j++){
					String id = (String)kDTable1.getRow(j).getCell("id").getValue();
					UIContext uiContext = new UIContext(this);
					uiContext.put("ID", id);
					if(id==null){
						MsgBox.showInfo("未生成凭证信息，无法查看凭证");
						SysUtil.abort();
					}else{
						try {
							IUIWindow create = UIFactory.createUIFactory(UIFactoryName.MODEL).create(
									VoucherEditUI.class.getName(), uiContext, null, OprtState.VIEW);
							create.show();
						} catch (UIException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		}
		super.kDTable1_tableClicked(e);
	}
	
	protected void btnSeach_actionPerformed(ActionEvent e) throws Exception {
		kDTable1.removeRows();
		String id =this.getUIContext().get("id").toString();
		List dataList = InvoiceFactory.getRemoteInstance().getVouchersLists(id,txtVoucherCode.getText(),txtstartTime.getText(),txtendTime.getText());
		for (int i = 0; i < dataList.size(); i++) {
			String[] data  = (String[]) dataList.get(i);
			IRow row = kDTable1.addRow();
			row.getCell("voucherNumber").setValue(data[0]);
			row.getCell("id").setValue(data[1]);
			row.getCell("bizdate").setValue(data[2]);
			row.getCell("InvoiceId").setValue(id);
			row.getCell("relationId").setValue(data[3]);
			if(data[3] != null){
				row.getCell("isRelation").setValue(true);
			}else{
				row.getCell("isRelation").setValue(false);
			}
		}
		kDTable1.getSelectManager().setSelectMode(KDTSelectManager.ROW_SELECT);
		kDTable1.getStyleAttributes().setLocked(true);
	}

	protected void btnVouchers_actionPerformed(ActionEvent e) throws Exception {
		KDTSelectBlock sb;
		int size = kDTable1.getSelectManager().size(); // 获取选择块的总个数
		if(size==0){
			MsgBox.showInfo("请选择一条付款单");
			return;
		}else{
			BOTRelationInfo botpInfo = null;
			String payType = new VoucherInfo().getBOSType().toString();
			for (int i = 0; i < size; i++){
			    // 获取第i个选择块
				SelectorItemCollection items=new SelectorItemCollection();
				items.add(new SelectorItemInfo("isCreateVoucher"));
			    sb = kDTable1.getSelectManager().get(i);
			    for(int j=sb.getBeginRow();j<=sb.getEndRow();j++){
			    	if(kDTable1.getRow(j).getCell("isRelation").getValue()==Boolean.TRUE){
			    		MsgBox.showInfo("已关联凭证不能再次关联，请重新选择！！");
						return;
			    	}
			    	String VouvherId = (String)kDTable1.getRow(j).getCell("id").getValue();
			    	String InvoiceId = (String)kDTable1.getRow(j).getCell("InvoiceId").getValue();
			    	botpInfo = new BOTRelationInfo();
			    	botpInfo.setSrcEntityID("80F193CE");
			    	botpInfo.setSrcObjectID(InvoiceId);
			    	botpInfo.setDestEntityID(payType);
			    	botpInfo.setDestObjectID(VouvherId);
			    	botpInfo.setDate(new Date());
			    	botpInfo.setIsEffected(true);
			    	botpInfo.setType(0);
			    	botpInfo.setOperatorID(com.kingdee.eas.common.client.SysContext.getSysContext().getUserName());
			    	IObjectPK pk=	BOTRelationFactory.getRemoteInstance().addnew(botpInfo);
			    	//更新状态
			    	InvoiceInfo invoice = InvoiceFactory.getRemoteInstance().getInvoiceInfo(new ObjectUuidPK(InvoiceId));
			    	invoice.setIsCreateVoucher(true);
			    	InvoiceFactory.getRemoteInstance().updatePartial(invoice, items);
			    	
			    	kDTable1.getCell(j, "isRelation").setValue(true);
			    	kDTable1.getCell(j, "relationId").setValue(pk.toString());
			    }
			}
			MsgBox.showInfo("手动关联凭证成功");
			return;
		}
	}

	protected void btnRelieve_actionPerformed(ActionEvent e) throws Exception {
		KDTSelectBlock sb;
		int size = kDTable1.getSelectManager().size(); // 获取选择块的总个数
		if(size==0){
			MsgBox.showInfo("请选择一条付款单");
			SysUtil.abort();
		}else{
			List billids=new ArrayList();
			for (int i = 0; i < size; i++){
			    // 获取第i个选择块
			    sb = kDTable1.getSelectManager().get(i);
			    for(int j=sb.getBeginRow();j<=sb.getEndRow();j++){
			    	if(kDTable1.getRow(j).getCell("isRelation").getValue()==Boolean.FALSE){
			    		MsgBox.showInfo("未关联凭证不能做解除，请重新选择！！");
						return;
			    	}
			    	String relationId = (String)kDTable1.getRow(j).getCell("relationId").getValue();
			    	String InvoiceId = (String)kDTable1.getRow(j).getCell("InvoiceId").getValue();
			    	billids.add(InvoiceId);
			    	BOTRelationFactory.getRemoteInstance().delete(new ObjectUuidPK(relationId));
			    	//更新状态
			    	kDTable1.getCell(j, "isRelation").setValue(false);
			    	kDTable1.getCell(j, "relationId").setValue(null);
			    }
			}
			//更新发票是否生成凭证的状态
			InvoiceFactory.getRemoteInstance().updPayStatus(billids,"2");
			MsgBox.showInfo("解除关联凭证成功");
		}
	}

}