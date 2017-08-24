/**
 * output package name
 */
package com.kingdee.eas.custom.financialinvoice.app;

import com.kingdee.bos.Context;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.eas.framework.batchHandler.ResponseContext;


/**
 * output class name
 */
public abstract class AbstractInvoiceListUIHandler extends com.kingdee.eas.framework.app.CoreBillListUIHandler

{
	public void handleActionTDPrint(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionTDPrint(request,response,context);
	}
	protected void _handleActionTDPrint(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionTDPrintPreview(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionTDPrintPreview(request,response,context);
	}
	protected void _handleActionTDPrintPreview(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleactionAudited(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleactionAudited(request,response,context);
	}
	protected void _handleactionAudited(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleactionUnAudited(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleactionUnAudited(request,response,context);
	}
	protected void _handleactionUnAudited(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleactionAddPayment(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleactionAddPayment(request,response,context);
	}
	protected void _handleactionAddPayment(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleactionCredentials(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleactionCredentials(request,response,context);
	}
	protected void _handleactionCredentials(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleactionVouchers(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleactionVouchers(request,response,context);
	}
	protected void _handleactionVouchers(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleactionUpdate(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleactionUpdate(request,response,context);
	}
	protected void _handleactionUpdate(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
}