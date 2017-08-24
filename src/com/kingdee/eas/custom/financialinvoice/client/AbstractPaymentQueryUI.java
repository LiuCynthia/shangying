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
public abstract class AbstractPaymentQueryUI extends com.kingdee.eas.framework.client.CoreUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractPaymentQueryUI.class);
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer1;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer2;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kDTable1;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer3;
    protected com.kingdee.bos.ctrl.swing.KDButton kDButton1;
    protected com.kingdee.bos.ctrl.swing.KDButton kDButton2;
    protected com.kingdee.bos.ctrl.swing.KDButton btnRelieve;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker txtstartTime;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker txtendTime;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtpaymentCode;
    protected actionSearch actionSearch = null;
    protected actionPayment actionPayment = null;
    /**
     * output class constructor
     */
    public AbstractPaymentQueryUI() throws Exception
    {
        super();
        jbInit();
        
        initUIP();
    }

    /**
     * output jbInit method
     */
    private void jbInit() throws Exception
    {
        this.resHelper = new ResourceBundleHelper(AbstractPaymentQueryUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        //actionSearch
        this.actionSearch = new actionSearch(this);
        getActionManager().registerAction("actionSearch", actionSearch);
         this.actionSearch.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionPayment
        this.actionPayment = new actionPayment(this);
        getActionManager().registerAction("actionPayment", actionPayment);
         this.actionPayment.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        this.kDLabelContainer1 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer2 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDTable1 = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.kDLabelContainer3 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDButton1 = new com.kingdee.bos.ctrl.swing.KDButton();
        this.kDButton2 = new com.kingdee.bos.ctrl.swing.KDButton();
        this.btnRelieve = new com.kingdee.bos.ctrl.swing.KDButton();
        this.txtstartTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtendTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtpaymentCode = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.kDLabelContainer1.setName("kDLabelContainer1");
        this.kDLabelContainer2.setName("kDLabelContainer2");
        this.kDTable1.setName("kDTable1");
        this.kDLabelContainer3.setName("kDLabelContainer3");
        this.kDButton1.setName("kDButton1");
        this.kDButton2.setName("kDButton2");
        this.btnRelieve.setName("btnRelieve");
        this.txtstartTime.setName("txtstartTime");
        this.txtendTime.setName("txtendTime");
        this.txtpaymentCode.setName("txtpaymentCode");
        // CoreUI
        // kDLabelContainer1		
        this.kDLabelContainer1.setBoundLabelText(resHelper.getString("kDLabelContainer1.boundLabelText"));
        // kDLabelContainer2		
        this.kDLabelContainer2.setBoundLabelText(resHelper.getString("kDLabelContainer2.boundLabelText"));
        // kDTable1
		String kDTable1StrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol4\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol5\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol6\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol7\"><c:Protection hidden=\"true\" /></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"PayTime\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"PaymentNumber\" t:width=\"150\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"VocherNumber\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"isRelation\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"3\" /><t:Column t:key=\"id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"4\" t:styleID=\"sCol4\" /><t:Column t:key=\"paymentId\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"5\" t:styleID=\"sCol5\" /><t:Column t:key=\"InvoiceId\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"6\" t:styleID=\"sCol6\" /><t:Column t:key=\"relationID\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"7\" t:styleID=\"sCol7\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header1\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{PayTime}</t:Cell><t:Cell>$Resource{PaymentNumber}</t:Cell><t:Cell>$Resource{VocherNumber}</t:Cell><t:Cell>$Resource{isRelation}</t:Cell><t:Cell>$Resource{id}</t:Cell><t:Cell>$Resource{paymentId}</t:Cell><t:Cell>$Resource{InvoiceId}</t:Cell><t:Cell>$Resource{relationID}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kDTable1.setFormatXml(resHelper.translateString("kDTable1",kDTable1StrXML));
        this.kDTable1.addKDTMouseListener(new com.kingdee.bos.ctrl.kdf.table.event.KDTMouseListener() {
            public void tableClicked(com.kingdee.bos.ctrl.kdf.table.event.KDTMouseEvent e) {
                try {
                    kDTable1_tableClicked(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });

        

        // kDLabelContainer3		
        this.kDLabelContainer3.setBoundLabelText(resHelper.getString("kDLabelContainer3.boundLabelText"));
        // kDButton1
        this.kDButton1.setAction((IItemAction)ActionProxyFactory.getProxy(actionSearch, new Class[] { IItemAction.class }, getServiceContext()));		
        this.kDButton1.setText(resHelper.getString("kDButton1.text"));
        // kDButton2
        this.kDButton2.setAction((IItemAction)ActionProxyFactory.getProxy(actionPayment, new Class[] { IItemAction.class }, getServiceContext()));		
        this.kDButton2.setText(resHelper.getString("kDButton2.text"));
        // btnRelieve		
        this.btnRelieve.setText(resHelper.getString("btnRelieve.text"));
        this.btnRelieve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    btnRelieve_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // txtstartTime
        // txtendTime
        // txtpaymentCode
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
        this.setLayout(null);
        kDLabelContainer1.setBounds(new Rectangle(14, 19, 274, 19));
        this.add(kDLabelContainer1, null);
        kDLabelContainer2.setBounds(new Rectangle(284, 20, 274, 19));
        this.add(kDLabelContainer2, null);
        kDTable1.setBounds(new Rectangle(14, 86, 997, 542));
        this.add(kDTable1, null);
        kDLabelContainer3.setBounds(new Rectangle(14, 50, 275, 19));
        this.add(kDLabelContainer3, null);
        kDButton1.setBounds(new Rectangle(308, 51, 77, 21));
        this.add(kDButton1, null);
        kDButton2.setBounds(new Rectangle(407, 51, 103, 21));
        this.add(kDButton2, null);
        btnRelieve.setBounds(new Rectangle(538, 51, 111, 21));
        this.add(btnRelieve, null);
        //kDLabelContainer1
        kDLabelContainer1.setBoundEditor(txtstartTime);
        //kDLabelContainer2
        kDLabelContainer2.setBoundEditor(txtendTime);
        //kDLabelContainer3
        kDLabelContainer3.setBoundEditor(txtpaymentCode);

    }


    /**
     * output initUIMenuBarLayout method
     */
    public void initUIMenuBarLayout()
    {
        this.menuBar.add(menuFile);
        this.menuBar.add(menuTool);
        this.menuBar.add(MenuService);
        this.menuBar.add(menuHelp);
        //menuFile
        menuFile.add(menuItemPageSetup);
        menuFile.add(kDSeparator1);
        menuFile.add(menuItemExitCurrent);
        //menuTool
        menuTool.add(menuItemSendMessage);
        menuTool.add(menuItemCalculator);
        //MenuService
        MenuService.add(MenuItemKnowStore);
        MenuService.add(MenuItemAnwser);
        MenuService.add(SepratorService);
        MenuService.add(MenuItemRemoteAssist);
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
        this.toolBar.add(btnPageSetup);


    }

	//Regiester control's property binding.
	private void registerBindings(){		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.custom.financialinvoice.app.PaymentQueryUIHandler";
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

    /**
     * output kDTable1_tableClicked method
     */
    protected void kDTable1_tableClicked(com.kingdee.bos.ctrl.kdf.table.event.KDTMouseEvent e) throws Exception
    {
        //write your code here
    }

    /**
     * output btnRelieve_actionPerformed method
     */
    protected void btnRelieve_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }

    	

    /**
     * output actionSearch_actionPerformed method
     */
    public void actionSearch_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionPayment_actionPerformed method
     */
    public void actionPayment_actionPerformed(ActionEvent e) throws Exception
    {
    }
	public RequestContext prepareactionSearch(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionSearch() {
    	return false;
    }
	public RequestContext prepareactionPayment(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionPayment() {
    	return false;
    }

    /**
     * output actionSearch class
     */     
    protected class actionSearch extends ItemAction {     
    
        public actionSearch()
        {
            this(null);
        }

        public actionSearch(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("actionSearch.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionSearch.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionSearch.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractPaymentQueryUI.this, "actionSearch", "actionSearch_actionPerformed", e);
        }
    }

    /**
     * output actionPayment class
     */     
    protected class actionPayment extends ItemAction {     
    
        public actionPayment()
        {
            this(null);
        }

        public actionPayment(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("actionPayment.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionPayment.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionPayment.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractPaymentQueryUI.this, "actionPayment", "actionPayment_actionPerformed", e);
        }
    }

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.custom.financialinvoice.client", "PaymentQueryUI");
    }




}