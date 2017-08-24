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
public abstract class AbstractPaymentQueryUIHandler extends com.kingdee.eas.framework.app.CoreUIHandler

{
	public void handleactionSearch(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleactionSearch(request,response,context);
	}
	protected void _handleactionSearch(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleactionPayment(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleactionPayment(request,response,context);
	}
	protected void _handleactionPayment(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
}