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
public abstract class AbstractVouchersQueryUI extends CoreUIObject
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractVouchersQueryUI.class);
    protected ResourceBundleHelper resHelper = null;
    protected com.kingdee.bos.ctrl.swing.KDToolBar VouchersQueryUI_toolbar;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer1;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer2;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer3;
    protected com.kingdee.bos.ctrl.swing.KDButton kDButton1;
    protected com.kingdee.bos.ctrl.swing.KDButton kDButton2;
    protected com.kingdee.bos.ctrl.swing.KDButton kDButton3;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kDTable1;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker txtstartTime;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker txtendTime;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtVoucherCode;
    /**
     * output class constructor
     */
    public AbstractVouchersQueryUI() throws Exception
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
        this.resHelper = new ResourceBundleHelper(AbstractVouchersQueryUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        this.toolBar = new com.kingdee.bos.ctrl.swing.KDToolBar();
        this.menuBar = new com.kingdee.bos.ctrl.swing.KDMenuBar();
        this.kDLabelContainer1 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer2 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer3 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDButton1 = new com.kingdee.bos.ctrl.swing.KDButton();
        this.kDButton2 = new com.kingdee.bos.ctrl.swing.KDButton();
        this.kDButton3 = new com.kingdee.bos.ctrl.swing.KDButton();
        this.kDTable1 = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.txtstartTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtendTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtVoucherCode = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.setName("VouchersQueryUI");
        this.toolBar.setName("VouchersQueryUI_toolbar");
        this.menuBar.setName("VouchersQueryUI_menubar");
        this.kDLabelContainer1.setName("kDLabelContainer1");
        this.kDLabelContainer2.setName("kDLabelContainer2");
        this.kDLabelContainer3.setName("kDLabelContainer3");
        this.kDButton1.setName("kDButton1");
        this.kDButton2.setName("kDButton2");
        this.kDButton3.setName("kDButton3");
        this.kDTable1.setName("kDTable1");
        this.txtstartTime.setName("txtstartTime");
        this.txtendTime.setName("txtendTime");
        this.txtVoucherCode.setName("txtVoucherCode");
        // VouchersQueryUI
        // VouchersQueryUI_toolbar
        // VouchersQueryUI_menubar
        // kDLabelContainer1		
        this.kDLabelContainer1.setBoundLabelText(resHelper.getString("kDLabelContainer1.boundLabelText"));
        // kDLabelContainer2		
        this.kDLabelContainer2.setBoundLabelText(resHelper.getString("kDLabelContainer2.boundLabelText"));
        // kDLabelContainer3		
        this.kDLabelContainer3.setBoundLabelText(resHelper.getString("kDLabelContainer3.boundLabelText"));
        // kDButton1		
        this.kDButton1.setText(resHelper.getString("kDButton1.text"));
        this.kDButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    btnSeach_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // kDButton2		
        this.kDButton2.setText(resHelper.getString("kDButton2.text"));
        this.kDButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    btnVouchers_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // kDButton3		
        this.kDButton3.setText(resHelper.getString("kDButton3.text"));
        this.kDButton3.addActionListener(new java.awt.event.ActionListener() {
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
        // kDTable1
		String kDTable1StrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol4\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol5\"><c:Protection hidden=\"true\" /></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol0\" /><t:Column t:key=\"bizdate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"voucherNumber\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"isRelation\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"InvoiceId\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol4\" /><t:Column t:key=\"relationId\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol5\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header1\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{id}</t:Cell><t:Cell>$Resource{bizdate}</t:Cell><t:Cell>$Resource{voucherNumber}</t:Cell><t:Cell>$Resource{isRelation}</t:Cell><t:Cell>$Resource{InvoiceId}</t:Cell><t:Cell>$Resource{relationId}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
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

        

        // txtstartTime
        // txtendTime
        // txtVoucherCode
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
		list.add(this.toolBar);
		return (com.kingdee.bos.ctrl.swing.KDToolBar[])list.toArray(new com.kingdee.bos.ctrl.swing.KDToolBar[list.size()]);
	}




    /**
     * output initUIContentLayout method
     */
    public void initUIContentLayout()
    {
        this.setBounds(new Rectangle(10, 10, 1016, 600));
        this.setLayout(null);
        kDLabelContainer1.setBounds(new Rectangle(12, 11, 274, 19));
        this.add(kDLabelContainer1, null);
        kDLabelContainer2.setBounds(new Rectangle(286, 10, 274, 19));
        this.add(kDLabelContainer2, null);
        kDLabelContainer3.setBounds(new Rectangle(24, 37, 260, 19));
        this.add(kDLabelContainer3, null);
        kDButton1.setBounds(new Rectangle(299, 33, 73, 21));
        this.add(kDButton1, null);
        kDButton2.setBounds(new Rectangle(383, 34, 88, 21));
        this.add(kDButton2, null);
        kDButton3.setBounds(new Rectangle(480, 33, 100, 21));
        this.add(kDButton3, null);
        kDTable1.setBounds(new Rectangle(12, 70, 728, 525));
        this.add(kDTable1, null);
        //kDLabelContainer1
        kDLabelContainer1.setBoundEditor(txtstartTime);
        //kDLabelContainer2
        kDLabelContainer2.setBoundEditor(txtendTime);
        //kDLabelContainer3
        kDLabelContainer3.setBoundEditor(txtVoucherCode);

    }


    /**
     * output initUIMenuBarLayout method
     */
    public void initUIMenuBarLayout()
    {

    }

    /**
     * output initUIToolBarLayout method
     */
    public void initUIToolBarLayout()
    {


    }

	//Regiester control's property binding.
	private void registerBindings(){		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.custom.financialinvoice.app.VouchersQueryUIHandler";
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
     * output btnSeach_actionPerformed method
     */
    protected void btnSeach_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }

    /**
     * output btnVouchers_actionPerformed method
     */
    protected void btnVouchers_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }

    /**
     * output btnRelieve_actionPerformed method
     */
    protected void btnRelieve_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
        //write your code here
    }

    /**
     * output kDTable1_tableClicked method
     */
    protected void kDTable1_tableClicked(com.kingdee.bos.ctrl.kdf.table.event.KDTMouseEvent e) throws Exception
    {
    }

    /**
     * output getSelectors method
     */
    public SelectorItemCollection getSelectors()
    {
        SelectorItemCollection sic = new SelectorItemCollection();
        return sic;
    }        

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.custom.financialinvoice.client", "VouchersQueryUI");
    }




}