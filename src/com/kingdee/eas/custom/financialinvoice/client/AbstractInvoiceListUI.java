/**
 * output package name
 */
package com.kingdee.eas.custom.financialinvoice.client;

import org.apache.log4j.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.event.*;
import javax.swing.KeyStroke;

import com.kingdee.bos.ctrl.swing.*;
import com.kingdee.bos.ctrl.kdf.table.*;
import com.kingdee.bos.ctrl.kdf.data.event.*;
import com.kingdee.bos.dao.*;
import com.kingdee.bos.dao.query.*;
import com.kingdee.bos.metadata.*;
import com.kingdee.bos.metadata.entity.*;
import com.kingdee.bos.ui.face.*;
import com.kingdee.bos.ui.util.ResourceBundleHelper;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.bos.service.ServiceContext;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.enums.EnumUtils;
import com.kingdee.bos.ui.face.UIRuleUtil;
import com.kingdee.bos.ctrl.swing.event.*;
import com.kingdee.bos.ctrl.kdf.table.event.*;
import com.kingdee.bos.ctrl.extendcontrols.*;
import com.kingdee.bos.ctrl.kdf.util.render.*;
import com.kingdee.bos.ui.face.IItemAction;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.bos.ui.util.IUIActionPostman;
import com.kingdee.bos.appframework.client.servicebinding.ActionProxyFactory;
import com.kingdee.bos.appframework.uistatemanage.ActionStateConst;
import com.kingdee.bos.appframework.validator.ValidateHelper;
import com.kingdee.bos.appframework.uip.UINavigator;


/**
 * output class name
 */
public abstract class AbstractInvoiceListUI extends com.kingdee.eas.framework.client.CoreBillListUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractInvoiceListUI.class);
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnUpdate;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnAudited;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnUnAudited;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnCredentials;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnVouchers;
    protected ActionTDPrint actionTDPrint = null;
    protected ActionTDPrintPreview actionTDPrintPreview = null;
    protected actionAudited actionAudited = null;
    protected actionUnAudited actionUnAudited = null;
    protected actionAddPayment actionAddPayment = null;
    protected actionCredentials actionCredentials = null;
    protected actionVouchers actionVouchers = null;
    protected actionUpdate actionUpdate = null;
    public final static String STATUS_VIEW = "VIEW";
    /**
     * output class constructor
     */
    public AbstractInvoiceListUI() throws Exception
    {
        super();
        this.defaultObjectName = "mainQuery";
        jbInit();
        
        initUIP();
    }

    /**
     * output jbInit method
     */
    private void jbInit() throws Exception
    {
        this.resHelper = new ResourceBundleHelper(AbstractInvoiceListUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        mainQueryPK = new MetaDataPK("com.kingdee.eas.custom.financialinvoice.app", "InvoiceQuery");
        //actionRemove
        String _tempStr = null;
        actionRemove.setEnabled(true);
        actionRemove.setDaemonRun(false);

        actionRemove.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl D"));
        _tempStr = resHelper.getString("ActionRemove.SHORT_DESCRIPTION");
        actionRemove.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionRemove.LONG_DESCRIPTION");
        actionRemove.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionRemove.NAME");
        actionRemove.putValue(ItemAction.NAME, _tempStr);
        this.actionRemove.setBindWorkFlow(true);
         this.actionRemove.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionRemove.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionRemove.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionTDPrint
        this.actionTDPrint = new ActionTDPrint(this);
        getActionManager().registerAction("actionTDPrint", actionTDPrint);
         this.actionTDPrint.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionTDPrintPreview
        this.actionTDPrintPreview = new ActionTDPrintPreview(this);
        getActionManager().registerAction("actionTDPrintPreview", actionTDPrintPreview);
         this.actionTDPrintPreview.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionAudited
        this.actionAudited = new actionAudited(this);
        getActionManager().registerAction("actionAudited", actionAudited);
         this.actionAudited.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionUnAudited
        this.actionUnAudited = new actionUnAudited(this);
        getActionManager().registerAction("actionUnAudited", actionUnAudited);
         this.actionUnAudited.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionAddPayment
        this.actionAddPayment = new actionAddPayment(this);
        getActionManager().registerAction("actionAddPayment", actionAddPayment);
         this.actionAddPayment.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionCredentials
        this.actionCredentials = new actionCredentials(this);
        getActionManager().registerAction("actionCredentials", actionCredentials);
         this.actionCredentials.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionVouchers
        this.actionVouchers = new actionVouchers(this);
        getActionManager().registerAction("actionVouchers", actionVouchers);
         this.actionVouchers.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionUpdate
        this.actionUpdate = new actionUpdate(this);
        getActionManager().registerAction("actionUpdate", actionUpdate);
         this.actionUpdate.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        this.btnUpdate = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnAudited = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnUnAudited = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnCredentials = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnVouchers = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnUpdate.setName("btnUpdate");
        this.btnAudited.setName("btnAudited");
        this.btnUnAudited.setName("btnUnAudited");
        this.btnCredentials.setName("btnCredentials");
        this.btnVouchers.setName("btnVouchers");
        // CoreUI
		String tblMainStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol6\"><c:NumberFormat>&amp;date</c:NumberFormat></c:Style><c:Style id=\"sCol9\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol11\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol12\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"0\" t:styleID=\"sCol0\" /><t:Column t:key=\"number\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"1\" /><t:Column t:key=\"FICompany.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"2\" /><t:Column t:key=\"InvoiceType.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"3\" /><t:Column t:key=\"InvoiceNumber\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"4\" /><t:Column t:key=\"InvoiceCode\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"5\" /><t:Column t:key=\"BillingDate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"6\" t:styleID=\"sCol6\" /><t:Column t:key=\"IssuingOffice\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"7\" /><t:Column t:key=\"RegisterNumber\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"8\" /><t:Column t:key=\"Tax\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"9\" t:styleID=\"sCol9\" /><t:Column t:key=\"TaxRate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"10\" /><t:Column t:key=\"UnTax\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"11\" t:styleID=\"sCol11\" /><t:Column t:key=\"TotalAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"12\" t:styleID=\"sCol12\" /><t:Column t:key=\"status\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"13\" /><t:Column t:key=\"isCreatePayment\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"14\" /><t:Column t:key=\"isCreateVoucher\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"15\" /><t:Column t:key=\"Applyer.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"16\" /><t:Column t:key=\"Remark\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"18\" /><t:Column t:key=\"ApplyDept.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{id}</t:Cell><t:Cell>$Resource{number}</t:Cell><t:Cell>$Resource{FICompany.name}</t:Cell><t:Cell>$Resource{InvoiceType.name}</t:Cell><t:Cell>$Resource{InvoiceNumber}</t:Cell><t:Cell>$Resource{InvoiceCode}</t:Cell><t:Cell>$Resource{BillingDate}</t:Cell><t:Cell>$Resource{IssuingOffice}</t:Cell><t:Cell>$Resource{RegisterNumber}</t:Cell><t:Cell>$Resource{Tax}</t:Cell><t:Cell>$Resource{TaxRate}</t:Cell><t:Cell>$Resource{UnTax}</t:Cell><t:Cell>$Resource{TotalAmount}</t:Cell><t:Cell>$Resource{status}</t:Cell><t:Cell>$Resource{isCreatePayment}</t:Cell><t:Cell>$Resource{isCreateVoucher}</t:Cell><t:Cell>$Resource{Applyer.name}</t:Cell><t:Cell>$Resource{Remark}</t:Cell><t:Cell>$Resource{ApplyDept.name}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.tblMain.setFormatXml(resHelper.translateString("tblMain",tblMainStrXML));
                this.tblMain.putBindContents("mainQuery",new String[] {"id","number","FICompany.name","InvoiceType.name","InvoiceNumber","InvoiceCode","BillingDate","IssuingOffice","RegisterNumber","Tax","TaxRate","UnTax","TotalAmount","status","isCreatePayment","isCreateVoucher","Applyer.name","Remark","ApplyDept.name"});


        this.tblMain.checkParsed();
        this.tblMain.getGroupManager().setGroup(true);		
        this.separatorFW2.setVisible(true);		
        this.btnDelVoucher.setVisible(false);		
        this.btnDelVoucher.setEnabled(false);		
        this.btnAuditResult.setVisible(false);		
        this.btnAuditResult.setEnabled(false);		
        this.kDSeparator4.setVisible(false);		
        this.menuItemCopyTo.setVisible(false);		
        this.kDSeparator6.setVisible(false);		
        this.menuItemViewDoProccess.setVisible(false);		
        this.menuItemAuditResult.setVisible(false);		
        this.btnWorkFlowG.setEnabled(false);		
        this.btnWorkFlowG.setVisible(false);
        // btnUpdate
        this.btnUpdate.setAction((IItemAction)ActionProxyFactory.getProxy(actionUpdate, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnUpdate.setText(resHelper.getString("btnUpdate.text"));		
        this.btnUpdate.setToolTipText(resHelper.getString("btnUpdate.toolTipText"));		
        this.btnUpdate.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgIcon_update"));
        // btnAudited
        this.btnAudited.setAction((IItemAction)ActionProxyFactory.getProxy(actionAudited, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnAudited.setText(resHelper.getString("btnAudited.text"));		
        this.btnAudited.setToolTipText(resHelper.getString("btnAudited.toolTipText"));		
        this.btnAudited.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_auditing"));
        // btnUnAudited
        this.btnUnAudited.setAction((IItemAction)ActionProxyFactory.getProxy(actionUnAudited, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnUnAudited.setText(resHelper.getString("btnUnAudited.text"));		
        this.btnUnAudited.setToolTipText(resHelper.getString("btnUnAudited.toolTipText"));		
        this.btnUnAudited.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_fauditing"));
        // btnCredentials
        this.btnCredentials.setAction((IItemAction)ActionProxyFactory.getProxy(actionCredentials, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnCredentials.setText(resHelper.getString("btnCredentials.text"));		
        this.btnCredentials.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_credence"));		
        this.btnCredentials.setToolTipText(resHelper.getString("btnCredentials.toolTipText"));
        // btnVouchers
        this.btnVouchers.setAction((IItemAction)ActionProxyFactory.getProxy(actionVouchers, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnVouchers.setText(resHelper.getString("btnVouchers.text"));		
        this.btnVouchers.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_scvoucher"));		
        this.btnVouchers.setToolTipText(resHelper.getString("btnVouchers.toolTipText"));
		//Register control's property binding
		registerBindings();
		registerUIState();


    }

	public com.kingdee.bos.ctrl.swing.KDToolBar[] getUIMultiToolBar(){
		java.util.List list = new java.util.ArrayList();
		com.kingdee.bos.ctrl.swing.KDToolBar[] bars = super.getUIMultiToolBar();
		if (bars != null) {
			list.addAll(java.util.Arrays.asList(bars));
		}
		return (com.kingdee.bos.ctrl.swing.KDToolBar[])list.toArray(new com.kingdee.bos.ctrl.swing.KDToolBar[list.size()]);
	}




    /**
     * output initUIContentLayout method
     */
    public void initUIContentLayout()
    {
        this.setBounds(new Rectangle(10, 10, 1013, 629));
        this.setLayout(new KDLayout());
        this.putClientProperty("OriginalBounds", new Rectangle(10, 10, 1013, 629));
        tblMain.setBounds(new Rectangle(6, 8, 996, 514));
        this.add(tblMain, new KDLayout.Constraints(6, 8, 996, 514, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));

    }


    /**
     * output initUIMenuBarLayout method
     */
    public void initUIMenuBarLayout()
    {
        this.menuBar.add(menuFile);
        this.menuBar.add(menuEdit);
        this.menuBar.add(MenuService);
        this.menuBar.add(menuView);
        this.menuBar.add(menuBiz);
        this.menuBar.add(menuTool);
        this.menuBar.add(menuWorkFlow);
        this.menuBar.add(menuTools);
        this.menuBar.add(menuHelp);
        //menuFile
        menuFile.add(menuItemAddNew);
        menuFile.add(menuItemImportData);
        menuFile.add(menuItemExportData);
        menuFile.add(separatorFile1);
        menuFile.add(MenuItemAttachment);
        menuFile.add(kDSeparator1);
        menuFile.add(menuItemPageSetup);
        menuFile.add(menuItemPrint);
        menuFile.add(menuItemPrintPreview);
        menuFile.add(kDSeparator2);
        menuFile.add(menuItemExitCurrent);
        //menuEdit
        menuEdit.add(menuItemEdit);
        menuEdit.add(menuItemRemove);
        menuEdit.add(kDSeparator3);
        menuEdit.add(menuItemCreateTo);
        menuEdit.add(menuItemCopyTo);
        menuEdit.add(kDSeparator4);
        //MenuService
        MenuService.add(MenuItemKnowStore);
        MenuService.add(MenuItemAnwser);
        MenuService.add(SepratorService);
        MenuService.add(MenuItemRemoteAssist);
        //menuView
        menuView.add(menuItemView);
        menuView.add(menuItemLocate);
        menuView.add(kDSeparator5);
        menuView.add(menuItemQuery);
        menuView.add(menuItemRefresh);
        menuView.add(menuItemSwitchView);
        menuView.add(separatorView1);
        menuView.add(menuItemTraceUp);
        menuView.add(menuItemQueryScheme);
        menuView.add(menuItemTraceDown);
        menuView.add(kDSeparator6);
        //menuBiz
        menuBiz.add(menuItemCancelCancel);
        menuBiz.add(menuItemCancel);
        menuBiz.add(menuItemVoucher);
        menuBiz.add(menuItemDelVoucher);
        //menuTool
        menuTool.add(menuItemSendMessage);
        menuTool.add(menuItemCalculator);
        //menuWorkFlow
        menuWorkFlow.add(menuItemViewDoProccess);
        menuWorkFlow.add(menuItemMultiapprove);
        menuWorkFlow.add(menuItemWorkFlowG);
        menuWorkFlow.add(menuItemWorkFlowList);
        menuWorkFlow.add(separatorWF1);
        menuWorkFlow.add(menuItemNextPerson);
        menuWorkFlow.add(menuItemAuditResult);
        menuWorkFlow.add(kDSeparator7);
        menuWorkFlow.add(menuItemSendSmsMessage);
        //menuTools
        menuTools.add(menuMail);
        menuTools.add(menuItemStartWorkFlow);
        menuTools.add(menuItemPublishReport);
        //menuMail
        menuMail.add(menuItemToHTML);
        menuMail.add(menuItemCopyScreen);
        menuMail.add(menuItemToExcel);
        //menuHelp
        menuHelp.add(menuItemHelp);
        menuHelp.add(kDSeparator12);
        menuHelp.add(menuItemRegPro);
        menuHelp.add(menuItemPersonalSite);
        menuHelp.add(helpseparatorDiv);
        menuHelp.add(menuitemProductval);
        menuHelp.add(kDSeparatorProduct);
        menuHelp.add(menuItemAbout);

    }

    /**
     * output initUIToolBarLayout method
     */
    public void initUIToolBarLayout()
    {
        this.toolBar.add(btnAddNew);
        this.toolBar.add(btnView);
        this.toolBar.add(btnEdit);
        this.toolBar.add(btnRemove);
        this.toolBar.add(btnRefresh);
        this.toolBar.add(btnQuery);
        this.toolBar.add(btnLocate);
        this.toolBar.add(btnAttachment);
        this.toolBar.add(separatorFW1);
        this.toolBar.add(btnPageSetup);
        this.toolBar.add(btnPrint);
        this.toolBar.add(btnPrintPreview);
        this.toolBar.add(separatorFW2);
        this.toolBar.add(btnCreateTo);
        this.toolBar.add(btnCopyTo);
        this.toolBar.add(btnQueryScheme);
        this.toolBar.add(separatorFW3);
        this.toolBar.add(btnTraceUp);
        this.toolBar.add(btnTraceDown);
        this.toolBar.add(btnWorkFlowG);
        this.toolBar.add(btnWorkFlowList);
        this.toolBar.add(btnSignature);
        this.toolBar.add(btnViewSignature);
        this.toolBar.add(separatorFW4);
        this.toolBar.add(btnVoucher);
        this.toolBar.add(btnDelVoucher);
        this.toolBar.add(btnMultiapprove);
        this.toolBar.add(btnNextPerson);
        this.toolBar.add(btnAuditResult);
        this.toolBar.add(btnCancel);
        this.toolBar.add(btnCancelCancel);
        this.toolBar.add(btnWFViewdoProccess);
        this.toolBar.add(btnUpdate);
        this.toolBar.add(btnAudited);
        this.toolBar.add(btnUnAudited);
        this.toolBar.add(btnCredentials);
        this.toolBar.add(btnVouchers);


    }

	//Regiester control's property binding.
	private void registerBindings(){		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.custom.financialinvoice.app.InvoiceListUIHandler";
	}
	public IUIActionPostman prepareInit() {
		IUIActionPostman clientHanlder = super.prepareInit();
		if (clientHanlder != null) {
			RequestContext request = new RequestContext();
    		request.setClassName(getUIHandlerClassName());
			clientHanlder.setRequestContext(request);
		}
		return clientHanlder;
    }
	
	public boolean isPrepareInit() {
    	return false;
    }
    protected void initUIP() {
        super.initUIP();
    }



	
	

    /**
     * output setDataObject method
     */
    public void setDataObject(IObjectValue dataObject)
    {
        IObjectValue ov = dataObject;        	    	
        super.setDataObject(ov);
    }
	protected void Remove() throws Exception {
    	IObjectValue editData = getBizInterface().getValue(new com.kingdee.bos.dao.ormapping.ObjectUuidPK(BOSUuid.read(getSelectedKeyValue())));
    	super.Remove();
    	recycleNumberByOrg(editData,"",editData.getString("number"));
    }
    protected void recycleNumberByOrg(IObjectValue editData,String orgType,String number) {
        if (!StringUtils.isEmpty(number))
        {
            try {
            	String companyID = null;            
				com.kingdee.eas.base.codingrule.ICodingRuleManager iCodingRuleManager = com.kingdee.eas.base.codingrule.CodingRuleManagerFactory.getRemoteInstance();
				if(!com.kingdee.util.StringUtils.isEmpty(orgType) && !"NONE".equalsIgnoreCase(orgType) && com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType))!=null) {
					companyID =com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType)).getString("id");
				}
				else if (com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit() != null) {
					companyID = ((com.kingdee.eas.basedata.org.OrgUnitInfo)com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit()).getString("id");
            	}				
				if (!StringUtils.isEmpty(companyID) && iCodingRuleManager.isExist(editData, companyID) && iCodingRuleManager.isUseIntermitNumber(editData, companyID)) {
					iCodingRuleManager.recycleNumber(editData,companyID,number);					
				}
            }
            catch (Exception e)
            {
                handUIException(e);
            }
        }
    }
			protected com.kingdee.eas.basedata.org.OrgType getMainBizOrgType() {
			return com.kingdee.eas.basedata.org.OrgType.getEnum("Company");
		}


    /**
     * output loadFields method
     */
    public void loadFields()
    {
        dataBinder.loadFields();
    }
    /**
     * output storeFields method
     */
    public void storeFields()
    {
		dataBinder.storeFields();
    }

	/**
	 * ????????§µ??
	 */
	protected void registerValidator() {
    	getValidateHelper().setCustomValidator( getValidator() );		
	}



    /**
     * output setOprtState method
     */
    public void setOprtState(String oprtType)
    {
        super.setOprtState(oprtType);
    }

			public SelectorItemCollection getBOTPSelectors() {
			SelectorItemCollection sic = new SelectorItemCollection();
			sic.add(new SelectorItemInfo("*"));
			sic.add(new SelectorItemInfo("creator.*"));
			sic.add(new SelectorItemInfo("lastUpdateUser.*"));
			sic.add(new SelectorItemInfo("CU.*"));
			sic.add(new SelectorItemInfo("handler.*"));
			sic.add(new SelectorItemInfo("auditor.*"));
			sic.add(new SelectorItemInfo("FICompany.*"));
			sic.add(new SelectorItemInfo("InvoiceType.*"));
			sic.add(new SelectorItemInfo("Applyer.*"));
			sic.add(new SelectorItemInfo("ApplyDept.*"));
			return sic;
		}

	protected FilterInfo getDefaultFilterForQuery() {
			FilterInfo filter = super.getDefaultFilterForQuery();
			if (filter == null)
				filter = new FilterInfo();
			FilterInfo otherFilter = com.kingdee.eas.framework.FrameWorkUtils.getF7FilterInfoByAuthorizedOrg(com.kingdee.eas.basedata.org.OrgType.getEnum("Company"),"FICompany.id",true);
			if (otherFilter != null) {
				for(int i=0,n=otherFilter.getFilterItems().size();i<n;i++){
					FilterItemInfo curFilterItem =otherFilter.getFilterItems().get(i);
					if(curFilterItem.getCompareValue()!=null && (curFilterItem.getCompareValue() instanceof String)){
						if("*".equalsIgnoreCase((String)(curFilterItem.getCompareValue()))){
							BOSUuid nullUuid=BOSUuid.create("nullnull");
							curFilterItem.setCompareValue(nullUuid.toString());
						}
					}
				}
				try {	
					if (com.kingdee.eas.framework.util.FilterUtility.hasFilterItem(filter))
						filter.mergeFilter(otherFilter, "AND");
					else
						filter = otherFilter;
				} catch (Exception e) {
					this.handUIException(e);
				}
			}
			return filter;
	}

    /**
     * output getSelectors method
     */
    public SelectorItemCollection getSelectors()
    {
        SelectorItemCollection sic = new SelectorItemCollection();
        sic.add(new SelectorItemInfo("id"));
        sic.add(new SelectorItemInfo("number"));
        sic.add(new SelectorItemInfo("bizDate"));
        sic.add(new SelectorItemInfo("description"));
        sic.add(new SelectorItemInfo("createTime"));
        sic.add(new SelectorItemInfo("lastUpdateTime"));
        sic.add(new SelectorItemInfo("auditor.number"));
        sic.add(new SelectorItemInfo("auditor.name"));
        sic.add(new SelectorItemInfo("creator.number"));
        sic.add(new SelectorItemInfo("creator.name"));
        sic.add(new SelectorItemInfo("lastUpdateUser.number"));
        sic.add(new SelectorItemInfo("lastUpdateUser.name"));
        sic.add(new SelectorItemInfo("entrys.id"));
        sic.add(new SelectorItemInfo("handler.number"));
        sic.add(new SelectorItemInfo("handler.name"));
        sic.add(new SelectorItemInfo("entrys.seq"));
        sic.add(new SelectorItemInfo("FICompany.name"));
        sic.add(new SelectorItemInfo("InvoiceNumber"));
        sic.add(new SelectorItemInfo("InvoiceCode"));
        sic.add(new SelectorItemInfo("BillingDate"));
        sic.add(new SelectorItemInfo("IssuingOffice"));
        sic.add(new SelectorItemInfo("RegisterNumber"));
        sic.add(new SelectorItemInfo("Tax"));
        sic.add(new SelectorItemInfo("UnTax"));
        sic.add(new SelectorItemInfo("TotalAmount"));
        sic.add(new SelectorItemInfo("status"));
        sic.add(new SelectorItemInfo("Remark"));
        sic.add(new SelectorItemInfo("InvoiceType.name"));
        sic.add(new SelectorItemInfo("isCreatePayment"));
        sic.add(new SelectorItemInfo("Applyer.name"));
        sic.add(new SelectorItemInfo("PaymenId"));
        sic.add(new SelectorItemInfo("VoucherId"));
        sic.add(new SelectorItemInfo("isDelete"));
        sic.add(new SelectorItemInfo("isCreateVoucher"));
        sic.add(new SelectorItemInfo("TaxRate"));
        sic.add(new SelectorItemInfo("ApplyDept.name"));
        return sic;
    }        
    	

    /**
     * output actionRemove_actionPerformed method
     */
    public void actionRemove_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionRemove_actionPerformed(e);
    }
    	

    /**
     * output actionTDPrint_actionPerformed method
     */
    public void actionTDPrint_actionPerformed(ActionEvent e) throws Exception
    {
        checkSelected();        
    	ArrayList idList =super.getSelectedIdValues();
        if (idList == null || idList.size() == 0 || getTDQueryPK() == null || getTDFileName() == null)
            return;
        com.kingdee.bos.ctrl.reportone.r1.print.data.AbstractPrintDataProvider data = new com.kingdee.bos.ctrl.report.forapp.kdnote.client.DefaultNoteDataProvider(idList,getTDQueryPK());
        com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper appHlp = new com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper();
        appHlp.print(getTDFileName(), data, javax.swing.SwingUtilities.getWindowAncestor(this));
    }
    	

    /**
     * output actionTDPrintPreview_actionPerformed method
     */
    public void actionTDPrintPreview_actionPerformed(ActionEvent e) throws Exception
    {
        checkSelected();
    	ArrayList idList =super.getSelectedIdValues();
        if (idList == null || idList.size() == 0 || getTDQueryPK() == null || getTDFileName() == null)
            return;
        com.kingdee.bos.ctrl.reportone.r1.print.data.AbstractPrintDataProvider data = new com.kingdee.bos.ctrl.report.forapp.kdnote.client.DefaultNoteDataProvider(idList,getTDQueryPK());
        com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper appHlp = new com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper();
        appHlp.printPreview(getTDFileName(), data, javax.swing.SwingUtilities.getWindowAncestor(this));
    }
    	

    /**
     * output actionAudited_actionPerformed method
     */
    public void actionAudited_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionUnAudited_actionPerformed method
     */
    public void actionUnAudited_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionAddPayment_actionPerformed method
     */
    public void actionAddPayment_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionCredentials_actionPerformed method
     */
    public void actionCredentials_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionVouchers_actionPerformed method
     */
    public void actionVouchers_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionUpdate_actionPerformed method
     */
    public void actionUpdate_actionPerformed(ActionEvent e) throws Exception
    {
    }
	public RequestContext prepareActionRemove(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionRemove(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionRemove() {
    	return false;
    }
	public RequestContext prepareActionTDPrint(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionTDPrint() {
    	return false;
    }
	public RequestContext prepareActionTDPrintPreview(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionTDPrintPreview() {
    	return false;
    }
	public RequestContext prepareactionAudited(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionAudited() {
    	return false;
    }
	public RequestContext prepareactionUnAudited(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionUnAudited() {
    	return false;
    }
	public RequestContext prepareactionAddPayment(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionAddPayment() {
    	return false;
    }
	public RequestContext prepareactionCredentials(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionCredentials() {
    	return false;
    }
	public RequestContext prepareactionVouchers(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionVouchers() {
    	return false;
    }
	public RequestContext prepareactionUpdate(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionUpdate() {
    	return false;
    }

    /**
     * output ActionTDPrint class
     */     
    protected class ActionTDPrint extends ItemAction {     
    
        public ActionTDPrint()
        {
            this(null);
        }

        public ActionTDPrint(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionTDPrint.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionTDPrint.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionTDPrint.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractInvoiceListUI.this, "ActionTDPrint", "actionTDPrint_actionPerformed", e);
        }
    }

    /**
     * output ActionTDPrintPreview class
     */     
    protected class ActionTDPrintPreview extends ItemAction {     
    
        public ActionTDPrintPreview()
        {
            this(null);
        }

        public ActionTDPrintPreview(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionTDPrintPreview.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionTDPrintPreview.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionTDPrintPreview.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractInvoiceListUI.this, "ActionTDPrintPreview", "actionTDPrintPreview_actionPerformed", e);
        }
    }

    /**
     * output actionAudited class
     */     
    protected class actionAudited extends ItemAction {     
    
        public actionAudited()
        {
            this(null);
        }

        public actionAudited(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("actionAudited.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionAudited.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionAudited.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractInvoiceListUI.this, "actionAudited", "actionAudited_actionPerformed", e);
        }
    }

    /**
     * output actionUnAudited class
     */     
    protected class actionUnAudited extends ItemAction {     
    
        public actionUnAudited()
        {
            this(null);
        }

        public actionUnAudited(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("actionUnAudited.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionUnAudited.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionUnAudited.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractInvoiceListUI.this, "actionUnAudited", "actionUnAudited_actionPerformed", e);
        }
    }

    /**
     * output actionAddPayment class
     */     
    protected class actionAddPayment extends ItemAction {     
    
        public actionAddPayment()
        {
            this(null);
        }

        public actionAddPayment(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("actionAddPayment.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionAddPayment.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionAddPayment.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractInvoiceListUI.this, "actionAddPayment", "actionAddPayment_actionPerformed", e);
        }
    }

    /**
     * output actionCredentials class
     */     
    protected class actionCredentials extends ItemAction {     
    
        public actionCredentials()
        {
            this(null);
        }

        public actionCredentials(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("actionCredentials.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionCredentials.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionCredentials.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractInvoiceListUI.this, "actionCredentials", "actionCredentials_actionPerformed", e);
        }
    }

    /**
     * output actionVouchers class
     */     
    protected class actionVouchers extends ItemAction {     
    
        public actionVouchers()
        {
            this(null);
        }

        public actionVouchers(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("actionVouchers.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionVouchers.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionVouchers.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractInvoiceListUI.this, "actionVouchers", "actionVouchers_actionPerformed", e);
        }
    }

    /**
     * output actionUpdate class
     */     
    protected class actionUpdate extends ItemAction {     
    
        public actionUpdate()
        {
            this(null);
        }

        public actionUpdate(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("actionUpdate.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionUpdate.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionUpdate.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractInvoiceListUI.this, "actionUpdate", "actionUpdate_actionPerformed", e);
        }
    }

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.custom.financialinvoice.client", "InvoiceListUI");
    }
    /**
     * output isBindWorkFlow method
     */
    public boolean isBindWorkFlow()
    {
        return true;
    }

    /**
     * output getEditUIName method
     */
    protected String getEditUIName()
    {
        return com.kingdee.eas.custom.financialinvoice.client.InvoiceEditUI.class.getName();
    }

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.custom.financialinvoice.InvoiceFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected IObjectValue createNewData()
    {
        com.kingdee.eas.custom.financialinvoice.InvoiceInfo objectValue = new com.kingdee.eas.custom.financialinvoice.InvoiceInfo();		
        return objectValue;
    }

    /**
     * output getMergeColumnKeys method
     */
    public String[] getMergeColumnKeys()
    {
        return new String[] {"id","number","FICompany.name","InvoiceType.name","InvoiceNumber","InvoiceCode","BillingDate","IssuingOffice","RegisterNumber","Tax","TaxRate","UnTax","TotalAmount","status","isCreatePayment","isCreateVoucher","Applyer.name","Remark","ApplyDept.name"};
    }



	protected String getTDFileName() {
    	return "/bim/custom/financialinvoice/Invoice";
	}
    protected IMetaDataPK getTDQueryPK() {
    	return new MetaDataPK("com.kingdee.eas.custom.financialinvoice.app.InvoiceQuery");
	}

}