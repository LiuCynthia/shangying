/**
 * output package name
 */
package com.kingdee.eas.custom.financialinvoice.client;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectBlock;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectManager;
import com.kingdee.bos.ctrl.kdf.table.event.KDTMouseEvent;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.bot.BOTRelationFactory;
import com.kingdee.bos.metadata.bot.BOTRelationInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ui.face.IUIWindow;
import com.kingdee.bos.ui.face.UIException;
import com.kingdee.bos.ui.face.UIFactory;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.common.client.UIContext;
import com.kingdee.eas.common.client.UIFactoryName;
import com.kingdee.eas.custom.financialinvoice.InvoiceFactory;
import com.kingdee.eas.custom.financialinvoice.InvoiceInfo;
import com.kingdee.eas.fi.cas.PaymentBillInfo;
import com.kingdee.eas.fi.gl.client.VoucherEditUI;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.MsgBox;

/**
 * output class name
 */
public class PaymentQueryUI extends AbstractPaymentQueryUI
{
    private static final Logger logger = CoreUIObject.getLogger(PaymentQueryUI.class);
    
    /**
     * output class constructor
     */
    public PaymentQueryUI() throws Exception
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
			int size = kDTable1.getSelectManager().size(); // ��ȡѡ�����ܸ���
			List idList =  new ArrayList();
			for (int i = 0; i < size; i++){
				// ��ȡ��i��ѡ���
				sb = kDTable1.getSelectManager().get(i);
				for(int j=sb.getBeginRow();j<=sb.getEndRow();j++){
					String id = (String)kDTable1.getRow(j).getCell("id").getValue();
					UIContext uiContext = new UIContext(this);
					uiContext.put("ID", id);
					if(id==null){
						MsgBox.showInfo("�����δ����ƾ֤��Ϣ");
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

	public void kDTable1_tableClicked(ActionEvent e) throws Exception {
		KDTSelectBlock sb;
		int size = kDTable1.getSelectManager().size(); // ��ȡѡ�����ܸ���
		List idList =  new ArrayList();
		for (int i = 0; i < size; i++){
		    // ��ȡ��i��ѡ���
		    sb = kDTable1.getSelectManager().get(i);
		    for(int j=sb.getBeginRow();j<=sb.getEndRow();j++){
		    	String id = (String)kDTable1.getRow(j).getCell("id").getValue();
		    	UIContext uiContext = new UIContext(this);
				uiContext.put("ID", id);
				if(id==null){
					MsgBox.showInfo("δ����ƾ֤��Ϣ���޷��鿴ƾ֤");
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
	
	public void actionSearch_actionPerformed(ActionEvent e) throws Exception {
		kDTable1.removeRows();
		String id =this.getUIContext().get("id").toString();
		List dataList = InvoiceFactory.getRemoteInstance().getCredentialLists(id,txtstartTime.getText(),txtendTime.getText(),txtpaymentCode.getText());
		for (int i = 0; i < dataList.size(); i++) {
			String[] data  = (String[]) dataList.get(i);
			IRow row = kDTable1.addRow();
			row.getCell("PaymentNumber").setValue(data[0]);
			row.getCell("VocherNumber").setValue(data[1]);
		//	row.getCell("periodNumber").setValue(data[2]);
			row.getCell("id").setValue(data[2]);
			//row.getCell("VocherTime").setValue(data[4]);
			row.getCell("PayTime").setValue(data[3]);
			row.getCell("paymentId").setValue(data[4]);
			row.getCell("InvoiceId").setValue(id);
			row.getCell("relationID").setValue(data[5]);
			if(data[5] != null){
				row.getCell("isRelation").setValue(true);
			}else{
				row.getCell("isRelation").setValue(false);
			}
			
		}
		kDTable1.getSelectManager().setSelectMode(KDTSelectManager.ROW_SELECT);
		kDTable1.getStyleAttributes().setLocked(true);
	}

	public void actionPayment_actionPerformed(ActionEvent e) throws Exception {
		KDTSelectBlock sb;
		int size = kDTable1.getSelectManager().size(); // ��ȡѡ�����ܸ���
		if(size==0){
			MsgBox.showInfo("��ѡ��һ�����");
			return;
		}else{
			BOTRelationInfo botpInfo = null;
			String payType=new PaymentBillInfo().getBOSType().toString();
			for (int i = 0; i < size; i++){
			    // ��ȡ��i��ѡ���
				SelectorItemCollection items=new SelectorItemCollection();
				items.add(new SelectorItemInfo("isCreatePayment"));
			    sb = kDTable1.getSelectManager().get(i);
			    for(int j=sb.getBeginRow();j<=sb.getEndRow();j++){
			    	if(kDTable1.getRow(j).getCell("isRelation").getValue()==Boolean.TRUE){
			    		MsgBox.showInfo("�ѹ�����������ٴι�����������ѡ�񣡣�");
						return;
			    	}
			    	String paymentId = (String)kDTable1.getRow(j).getCell("paymentId").getValue();
			    	String InvoiceId = (String)kDTable1.getRow(j).getCell("InvoiceId").getValue();
			    	botpInfo = new BOTRelationInfo();
			    	botpInfo.setSrcEntityID("80F193CE");
			    	botpInfo.setSrcObjectID(InvoiceId);
			    	botpInfo.setDestEntityID(payType);
			    	botpInfo.setDestObjectID(paymentId);
			    	botpInfo.setDate(new Date());
			    	botpInfo.setIsEffected(true);
			    	botpInfo.setType(0);
			    	botpInfo.setOperatorID(com.kingdee.eas.common.client.SysContext.getSysContext().getUserName());
			    	IObjectPK pk=	BOTRelationFactory.getRemoteInstance().addnew(botpInfo);
			    	//����״̬
			    	InvoiceInfo invoice = InvoiceFactory.getRemoteInstance().getInvoiceInfo(new ObjectUuidPK(InvoiceId));
			    	invoice.setIsCreatePayment(true);
			    	InvoiceFactory.getRemoteInstance().updatePartial(invoice, items);
			    	
			    	kDTable1.getCell(j, "isRelation").setValue(true);
			    	kDTable1.getCell(j, "relationID").setValue(pk.toString());
			    	
			    }
			}
			MsgBox.showInfo("�ֶ���������ɹ�");
			return;
		}
	}

	/**�����*/
	protected void btnRelieve_actionPerformed(ActionEvent e) throws Exception {
		KDTSelectBlock sb;
		int size = kDTable1.getSelectManager().size(); // ��ȡѡ�����ܸ���
		if(size==0){
			MsgBox.showInfo("��ѡ��һ�����");
			SysUtil.abort();
		}else{
			List billids=new ArrayList();
			for (int i = 0; i < size; i++){
			    // ��ȡ��i��ѡ���
			    sb = kDTable1.getSelectManager().get(i);
			    for(int j=sb.getBeginRow();j<=sb.getEndRow();j++){
			    	if(kDTable1.getRow(j).getCell("isRelation").getValue()==Boolean.FALSE){
			    		MsgBox.showInfo("δ������������������������ѡ�񣡣�");
						return;
			    	}
			    	String relationId = (String)kDTable1.getRow(j).getCell("relationID").getValue();
			    	String InvoiceId = (String)kDTable1.getRow(j).getCell("InvoiceId").getValue();
			    	billids.add(InvoiceId);
			    	BOTRelationFactory.getRemoteInstance().delete(new ObjectUuidPK(relationId));
			    	//����״̬
			    	kDTable1.getCell(j, "isRelation").setValue(false);
			    	kDTable1.getCell(j, "relationID").setValue(null);
			    }
			}
			//���·�Ʊ�Ƿ����ɸ����״̬
			InvoiceFactory.getRemoteInstance().updPayStatus(billids,"1");
		}
	}
}