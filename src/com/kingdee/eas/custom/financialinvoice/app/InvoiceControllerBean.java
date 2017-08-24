package com.kingdee.eas.custom.financialinvoice.app;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.bot.BOTRelationCollection;
import com.kingdee.bos.metadata.bot.BOTRelationFactory;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.custom.financialinvoice.IInvoice;
import com.kingdee.eas.custom.financialinvoice.InvoiceFactory;
import com.kingdee.eas.custom.financialinvoice.InvoiceInfo;
import com.kingdee.eas.util.app.ContextUtil;
import com.kingdee.eas.util.app.DbUtil;
import com.kingdee.jdbc.rowset.IRowSet;

public class InvoiceControllerBean extends AbstractInvoiceControllerBean
{
    private static Logger logger =
        Logger.getLogger("com.kingdee.eas.custom.financialinvoice.app.InvoiceControllerBean");


	protected boolean _isExistInvoiceNumber(Context ctx, String InvoiceCode,
			String invoiceNumber, String billId) throws BOSException {
		boolean isExist=false;
		StringBuffer sql=new StringBuffer();
		sql.append("select FID from T_FIN_Invoice ");
		sql.append(" where FStatus !='4' and FInvoiceCode='").append(InvoiceCode).append("'");
		sql.append(" and FInvoiceNumber='").append(invoiceNumber).append("'");
		if(billId != null){
			sql.append(" and FID != '").append(billId).append("'");
		}
		IRowSet rs=DbUtil.executeQuery(ctx, sql.toString());
		try {
			while(rs.next()){
				isExist=true;
				 break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BOSException("查询发票号失败"+e.getMessage());
		}
		return isExist;
		
	}


	protected boolean _isExistInvoiceTotalMoney(Context ctx, String ApplyDept,
			String Applyer, String BillingDate,String BillId,BigDecimal totalMoney) throws BOSException {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
 	    GregorianCalendar now = new GregorianCalendar();
 	    now.add(GregorianCalendar.MONTH,-6); 
 	    String date  = sf.format(new Date());
 	    String date2  = sf.format(now.getTime());
	    boolean isExist=false;
		StringBuffer sql=new StringBuffer();
		sql.append("select FTotalAmount from T_FIN_Invoice ");
		sql.append(" where FApplyerID='").append(Applyer).append("'");
		sql.append(" and FApplyDeptID='").append(ApplyDept).append("'");
		sql.append(" and to_char(FBillingDate,'yyyy-MM-dd')>= '").append(date2).append("' and to_char(FBillingDate,'yyyy-MM-dd') <='").append(date).append("'");
		if(BillId != null){
			sql.append(" and FID != '").append(BillId).append("'");
		}
		
		IRowSet rs=DbUtil.executeQuery(ctx, sql.toString());
		BigDecimal amount=BigDecimal.ZERO;
		try {
			while(rs.next()){
				BigDecimal FTotalAmount=new BigDecimal(rs.getString("FTotalAmount"));
				amount =amount.add(FTotalAmount);
			}
			if(amount.compareTo(totalMoney)==0){
				isExist = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BOSException("查询半年内的申请总金额失败"+e.getMessage());
		}
		return isExist;
	}
	
	protected List _getCredentialLists(Context ctx, String InvoiceId,String startTime, String endTime, String paymentCode)throws BOSException {
		InvoiceInfo info = null;
		try {
			info = InvoiceFactory.getLocalInstance(ctx).getInvoiceInfo(new ObjectUuidPK(InvoiceId));
		} catch (EASBizException e2) {
			e2.printStackTrace();
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date FstartTime = null;
		Date FendTime = null;
		try {
			FstartTime = sf.parse(startTime);
			FendTime = sf.parse(endTime);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		List dateList = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.fbizdate, t.fid as paymentId, t.fnumber,t.FVoucherNumber, t1.fid as id, t2.fid  as relationID \n");
		sql.append(" from T_CAS_PaymentBill t  \n");
		sql.append(" left join t_gl_voucher t1 on t.fvoucherid=t1.fid \n");
		sql.append(" left join  (select fid,fdestObjectID from t_bot_relation where fsrcEntityID='80F193CE' ");
		sql.append(" and fdestentityid='40284E81'  and fsrcObjectID='").append(InvoiceId).append("') t2 ");;
		sql.append(" on t.fid=t2.fdestObjectID  \n");
		sql.append(" where 1=1 and t.fcompanyid ='").append(info.getFICompany().getId()).append("'");
		
		if(startTime!=null && !"".equals(startTime)){
			sql.append(" and to_char(t.FBizDate,'yyyy-MM-dd') >= '").append(sf.format(FstartTime)).append("'");
		}
		
		if(endTime!=null && !"".equals(endTime)){
			sql.append(" and to_char(t.FBizDate,'yyyy-MM-dd') <= '").append(sf.format(FendTime)).append("'");
		}
		
		if(paymentCode!=null && !"".equals(paymentCode)){
			sql.append(" and t.FNumber like '%").append(paymentCode).append("%'");
		}
		IRowSet rs=DbUtil.executeQuery(ctx, sql.toString());
		String[] datas=null;
		try {
			while(rs.next()){
				datas=new String[6];
				datas[0]=rs.getString("FNumber");
				datas[1] = rs.getString("FVoucherNumber");
				datas[2]=rs.getString("id");
				datas[3]=rs.getString("fbizdate");
				datas[4]=rs.getString("paymentId");
				datas[5]=rs.getString("relationID");
				dateList.add(datas);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dateList;
	}


	 
	protected void _updPayStatus(Context ctx, List billIds,String type) throws BOSException, EASBizException {
		Set ids = new HashSet();
		for (int i = 0; i < billIds.size(); i++) {
			ids.add(billIds.get(i).toString());
		}
		
		EntityViewInfo view=new EntityViewInfo();
		FilterInfo filter=new FilterInfo();
		filter.getFilterItems().add(new FilterItemInfo("srcObjectID",ids,CompareType.INCLUDE));
		view.setFilter(filter);
		BOTRelationCollection cols=	BOTRelationFactory.getLocalInstance(ctx).getCollection(view);
		IInvoice Iinvoice=InvoiceFactory.getLocalInstance(ctx);
		SelectorItemCollection items=new SelectorItemCollection();
		items.add(new SelectorItemInfo("isCreateVoucher"));
		items.add(new SelectorItemInfo("isCreatePayment"));
		for(int i=0;i<cols.size();i++){
			InvoiceInfo info=Iinvoice.getInvoiceInfo(new ObjectUuidPK(cols.get(i).getSrcObjectID()));
			if(type.equals("1")){  //生成付款单
				info.setIsCreatePayment(true);
			}else if(type.equals("2")){ //生成凭证
				info.setIsCreateVoucher(true);
			}else{
				info.setIsCreateVoucher(false);
			}
			Iinvoice.updatePartial(info, items);
		}
		
		if(cols.size()==0){
			//解除状态
			for(int i=0;i<billIds.size();i++){
				InvoiceInfo info=Iinvoice.getInvoiceInfo(new ObjectUuidPK(billIds.get(i).toString()));
				info.setIsCreatePayment(false);
				Iinvoice.updatePartial(info, items);
			}
		}
	}
	
	protected void _updDeleteStatus(Context ctx, List billIds)throws BOSException{
		IInvoice Iinvoice=InvoiceFactory.getLocalInstance(ctx);
		SelectorItemCollection items=new SelectorItemCollection();
		items.add(new SelectorItemInfo("status"));
		items.add(new SelectorItemInfo("isCreateVoucher"));
		items.add(new SelectorItemInfo("isCreatePayment"));
		for(int i=0;i<billIds.size();i++){
			InvoiceInfo info;
			try {
				info = Iinvoice.getInvoiceInfo(new ObjectUuidPK(billIds.get(i).toString()));
				info.setStatus(InvoiceEnum.delete);
				info.setIsCreatePayment(false);
				info.setIsCreateVoucher(false);
				Iinvoice.updatePartial(info, items);
			} catch (EASBizException e) {
				e.printStackTrace();
			}
		}
	}

	protected List _getVouchersLists(Context ctx, String InvoiceId,String voucherCode, String startTime, String endTime)
			throws BOSException {
		InvoiceInfo info = null;
		try {
			info = InvoiceFactory.getLocalInstance(ctx).getInvoiceInfo(new ObjectUuidPK(InvoiceId));
		} catch (EASBizException e2) {
			e2.printStackTrace();
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date FstartTime = null;
		Date FendTime = null;
		try {
			FstartTime = sf.parse(startTime);
			FendTime = sf.parse(endTime);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		List dateList = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.FNumber,t.FID as id,t.FBizDate,t2.fid  as relationID from t_gl_voucher t \n");
		sql.append(" left join  (select fid,fdestObjectID from t_bot_relation \n");
		sql.append(" where fsrcEntityID='80F193CE' and fdestentityid='2652E01E' \n");
		sql.append(" and fsrcObjectID='").append(InvoiceId).append("') t2 ");;
		sql.append(" on t.fid=t2.fdestObjectID   \n");
		sql.append(" where 1=1 and t.fcompanyid ='").append(info.getFICompany().getId()).append("'");
		
		if(startTime!=null && !"".equals(startTime)){
			sql.append(" and to_char(t.FBizDate,'yyyy-MM-dd') >= '").append(sf.format(FstartTime)).append("'");
		}
		if(endTime!=null && !"".equals(endTime)){
			sql.append(" and to_char(t.FBizDate,'yyyy-MM-dd') <= '").append(sf.format(FendTime)).append("'");
		}
		if(voucherCode!=null && !"".equals(voucherCode)){
			sql.append(" and t.FNumber like '%").append(voucherCode).append("%'");
		}
		IRowSet rs=DbUtil.executeQuery(ctx, sql.toString());
		String[] datas=null;
		try {
			while(rs.next()){
				datas=new String[4];
				datas[0]=rs.getString("FNumber");
				datas[1]=rs.getString("id");
				datas[2]=rs.getString("FBizDate");
				datas[3]=rs.getString("relationID");
				dateList.add(datas);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dateList;
	}
	
 
	
}