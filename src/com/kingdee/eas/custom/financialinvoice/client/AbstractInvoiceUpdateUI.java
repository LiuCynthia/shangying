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
public abstract class AbstractInvoiceUpdateUI extends CoreUIObject
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractInvoiceUpdateUI.class);
    protected ResourceBundleHelper resHelper = null;
    protected com.kingdee.bos.ctrl.swing.KDToolBar InvoiceUpdateUI_toolbar;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer2;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer3;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer4;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer7;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer8;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer9;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer10;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer11;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer12;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer13;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer14;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer15;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer5;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer6;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer16;
    protected com.kingdee.bos.ctrl.swing.KDButton btnChcek;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer17;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer18;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer19;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer1;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer20;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer21;
    protected com.kingdee.bos.ctrl.swing.KDTextField kDTextField1;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox kDBizPromptBox2;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox kDBizPromptBox3;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField kDFormattedTextField1;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField kDFormattedTextField2;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField kDFormattedTextField3;
    protected com.kingdee.bos.ctrl.swing.KDTextField kDTextField2;
    protected com.kingdee.bos.ctrl.swing.KDTextField kDTextField3;
    protected com.kingdee.bos.ctrl.swing.KDTextField kDTextField4;
    protected com.kingdee.bos.ctrl.swing.KDTextField kDTextField5;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox kDBizPromptBox4;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtInvoiceNumber;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtInvoiceCode;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtIssuingOffice;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkBillingDate;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtRegisterNumber;
    protected com.kingdee.bos.ctrl.swing.KDComboBox prmtInvoiceType;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtApplyer;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtApplyDept;
    protected com.kingdee.bos.ctrl.swing.KDTextArea txtRemark;
    /**
     * output class constructor
     */
    public AbstractInvoiceUpdateUI() throws Exception
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
        this.resHelper = new ResourceBundleHelper(AbstractInvoiceUpdateUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        this.toolBar = new com.kingdee.bos.ctrl.swing.KDToolBar();
        this.menuBar = new com.kingdee.bos.ctrl.swing.KDMenuBar();
        this.kDLabelContainer2 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer3 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer4 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer7 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer8 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer9 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer10 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer11 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer12 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer13 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer14 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer15 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer5 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer6 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer16 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.btnChcek = new com.kingdee.bos.ctrl.swing.KDButton();
        this.kDLabelContainer17 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer18 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer19 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer1 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer20 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer21 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDTextField1 = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.kDBizPromptBox2 = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDBizPromptBox3 = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDFormattedTextField1 = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.kDFormattedTextField2 = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.kDFormattedTextField3 = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.kDTextField2 = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.kDTextField3 = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.kDTextField4 = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.kDTextField5 = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.kDBizPromptBox4 = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtInvoiceNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtInvoiceCode = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtIssuingOffice = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.pkBillingDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtRegisterNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtInvoiceType = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.prmtApplyer = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtApplyDept = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtRemark = new com.kingdee.bos.ctrl.swing.KDTextArea();
        this.setName("InvoiceUpdateUI");
        this.toolBar.setName("InvoiceUpdateUI_toolbar");
        this.menuBar.setName("InvoiceUpdateUI_menubar");
        this.kDLabelContainer2.setName("kDLabelContainer2");
        this.kDLabelContainer3.setName("kDLabelContainer3");
        this.kDLabelContainer4.setName("kDLabelContainer4");
        this.kDLabelContainer7.setName("kDLabelContainer7");
        this.kDLabelContainer8.setName("kDLabelContainer8");
        this.kDLabelContainer9.setName("kDLabelContainer9");
        this.kDLabelContainer10.setName("kDLabelContainer10");
        this.kDLabelContainer11.setName("kDLabelContainer11");
        this.kDLabelContainer12.setName("kDLabelContainer12");
        this.kDLabelContainer13.setName("kDLabelContainer13");
        this.kDLabelContainer14.setName("kDLabelContainer14");
        this.kDLabelContainer15.setName("kDLabelContainer15");
        this.kDLabelContainer5.setName("kDLabelContainer5");
        this.kDLabelContainer6.setName("kDLabelContainer6");
        this.kDLabelContainer16.setName("kDLabelContainer16");
        this.btnChcek.setName("btnChcek");
        this.kDLabelContainer17.setName("kDLabelContainer17");
        this.kDLabelContainer18.setName("kDLabelContainer18");
        this.kDLabelContainer19.setName("kDLabelContainer19");
        this.kDLabelContainer1.setName("kDLabelContainer1");
        this.kDLabelContainer20.setName("kDLabelContainer20");
        this.kDLabelContainer21.setName("kDLabelContainer21");
        this.kDTextField1.setName("kDTextField1");
        this.kDBizPromptBox2.setName("kDBizPromptBox2");
        this.kDBizPromptBox3.setName("kDBizPromptBox3");
        this.kDFormattedTextField1.setName("kDFormattedTextField1");
        this.kDFormattedTextField2.setName("kDFormattedTextField2");
        this.kDFormattedTextField3.setName("kDFormattedTextField3");
        this.kDTextField2.setName("kDTextField2");
        this.kDTextField3.setName("kDTextField3");
        this.kDTextField4.setName("kDTextField4");
        this.kDTextField5.setName("kDTextField5");
        this.kDBizPromptBox4.setName("kDBizPromptBox4");
        this.txtInvoiceNumber.setName("txtInvoiceNumber");
        this.txtInvoiceCode.setName("txtInvoiceCode");
        this.txtIssuingOffice.setName("txtIssuingOffice");
        this.pkBillingDate.setName("pkBillingDate");
        this.txtRegisterNumber.setName("txtRegisterNumber");
        this.prmtInvoiceType.setName("prmtInvoiceType");
        this.prmtApplyer.setName("prmtApplyer");
        this.prmtApplyDept.setName("prmtApplyDept");
        this.txtRemark.setName("txtRemark");
        // InvoiceUpdateUI
        // InvoiceUpdateUI_toolbar
        // InvoiceUpdateUI_menubar
        // kDLabelContainer2		
        this.kDLabelContainer2.setBoundLabelText(resHelper.getString("kDLabelContainer2.boundLabelText"));		
        this.kDLabelContainer2.setBoundLabelLength(100);		
        this.kDLabelContainer2.setBoundLabelUnderline(true);		
        this.kDLabelContainer2.setVisible(true);
        // kDLabelContainer3		
        this.kDLabelContainer3.setBoundLabelText(resHelper.getString("kDLabelContainer3.boundLabelText"));		
        this.kDLabelContainer3.setBoundLabelLength(100);		
        this.kDLabelContainer3.setBoundLabelUnderline(true);		
        this.kDLabelContainer3.setVisible(true);
        // kDLabelContainer4		
        this.kDLabelContainer4.setBoundLabelText(resHelper.getString("kDLabelContainer4.boundLabelText"));		
        this.kDLabelContainer4.setBoundLabelLength(100);		
        this.kDLabelContainer4.setBoundLabelUnderline(true);		
        this.kDLabelContainer4.setVisible(true);
        // kDLabelContainer7		
        this.kDLabelContainer7.setBoundLabelText(resHelper.getString("kDLabelContainer7.boundLabelText"));		
        this.kDLabelContainer7.setBoundLabelLength(100);		
        this.kDLabelContainer7.setBoundLabelUnderline(true);		
        this.kDLabelContainer7.setVisible(true);
        // kDLabelContainer8		
        this.kDLabelContainer8.setBoundLabelText(resHelper.getString("kDLabelContainer8.boundLabelText"));		
        this.kDLabelContainer8.setBoundLabelLength(100);		
        this.kDLabelContainer8.setBoundLabelUnderline(true);		
        this.kDLabelContainer8.setVisible(true);
        // kDLabelContainer9		
        this.kDLabelContainer9.setBoundLabelText(resHelper.getString("kDLabelContainer9.boundLabelText"));		
        this.kDLabelContainer9.setBoundLabelLength(100);		
        this.kDLabelContainer9.setBoundLabelUnderline(true);		
        this.kDLabelContainer9.setVisible(true);
        // kDLabelContainer10		
        this.kDLabelContainer10.setBoundLabelText(resHelper.getString("kDLabelContainer10.boundLabelText"));		
        this.kDLabelContainer10.setBoundLabelLength(100);		
        this.kDLabelContainer10.setBoundLabelUnderline(true);		
        this.kDLabelContainer10.setVisible(true);
        // kDLabelContainer11		
        this.kDLabelContainer11.setBoundLabelText(resHelper.getString("kDLabelContainer11.boundLabelText"));		
        this.kDLabelContainer11.setBoundLabelLength(100);		
        this.kDLabelContainer11.setBoundLabelUnderline(true);		
        this.kDLabelContainer11.setVisible(true);
        // kDLabelContainer12		
        this.kDLabelContainer12.setBoundLabelText(resHelper.getString("kDLabelContainer12.boundLabelText"));		
        this.kDLabelContainer12.setBoundLabelLength(100);		
        this.kDLabelContainer12.setBoundLabelUnderline(true);		
        this.kDLabelContainer12.setVisible(true);
        // kDLabelContainer13		
        this.kDLabelContainer13.setBoundLabelText(resHelper.getString("kDLabelContainer13.boundLabelText"));		
        this.kDLabelContainer13.setBoundLabelLength(100);		
        this.kDLabelContainer13.setBoundLabelUnderline(true);		
        this.kDLabelContainer13.setVisible(true);
        // kDLabelContainer14		
        this.kDLabelContainer14.setBoundLabelText(resHelper.getString("kDLabelContainer14.boundLabelText"));		
        this.kDLabelContainer14.setBoundLabelLength(100);		
        this.kDLabelContainer14.setBoundLabelUnderline(true);		
        this.kDLabelContainer14.setVisible(true);
        // kDLabelContainer15		
        this.kDLabelContainer15.setBoundLabelText(resHelper.getString("kDLabelContainer15.boundLabelText"));		
        this.kDLabelContainer15.setBoundLabelLength(100);		
        this.kDLabelContainer15.setBoundLabelUnderline(true);		
        this.kDLabelContainer15.setVisible(true);
        // kDLabelContainer5		
        this.kDLabelContainer5.setBoundLabelText(resHelper.getString("kDLabelContainer5.boundLabelText"));
        // kDLabelContainer6		
        this.kDLabelContainer6.setBoundLabelText(resHelper.getString("kDLabelContainer6.boundLabelText"));
        // kDLabelContainer16		
        this.kDLabelContainer16.setBoundLabelText(resHelper.getString("kDLabelContainer16.boundLabelText"));
        // btnChcek		
        this.btnChcek.setText(resHelper.getString("btnChcek.text"));
        this.btnChcek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    btnChcek_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // kDLabelContainer17		
        this.kDLabelContainer17.setBoundLabelText(resHelper.getString("kDLabelContainer17.boundLabelText"));
        // kDLabelContainer18		
        this.kDLabelContainer18.setBoundLabelText(resHelper.getString("kDLabelContainer18.boundLabelText"));
        // kDLabelContainer19		
        this.kDLabelContainer19.setBoundLabelText(resHelper.getString("kDLabelContainer19.boundLabelText"));
        // kDLabelContainer1		
        this.kDLabelContainer1.setBoundLabelText(resHelper.getString("kDLabelContainer1.boundLabelText"));
        // kDLabelContainer20		
        this.kDLabelContainer20.setBoundLabelText(resHelper.getString("kDLabelContainer20.boundLabelText"));
        // kDLabelContainer21		
        this.kDLabelContainer21.setBoundLabelText(resHelper.getString("kDLabelContainer21.boundLabelText"));
        // kDTextField1		
        this.kDTextField1.setVisible(true);		
        this.kDTextField1.setHorizontalAlignment(2);		
        this.kDTextField1.setMaxLength(100);		
        this.kDTextField1.setRequired(false);		
        this.kDTextField1.setText(resHelper.getString("kDTextField1.text"));
        // kDBizPromptBox2		
        this.kDBizPromptBox2.setQueryInfo("com.kingdee.eas.basedata.person.app.PersonQuery");		
        this.kDBizPromptBox2.setVisible(true);		
        this.kDBizPromptBox2.setEditable(true);		
        this.kDBizPromptBox2.setDisplayFormat("$name$");		
        this.kDBizPromptBox2.setEditFormat("$number$");		
        this.kDBizPromptBox2.setCommitFormat("$number$");		
        this.kDBizPromptBox2.setRequired(true);
        // kDBizPromptBox3		
        this.kDBizPromptBox3.setQueryInfo("com.kingdee.eas.custom.financialinvoice.app.InvoicetypeQuery");		
        this.kDBizPromptBox3.setVisible(true);		
        this.kDBizPromptBox3.setEditable(true);		
        this.kDBizPromptBox3.setDisplayFormat("$name$");		
        this.kDBizPromptBox3.setEditFormat("$number$");		
        this.kDBizPromptBox3.setCommitFormat("$number$");		
        this.kDBizPromptBox3.setRequired(true);
        // kDFormattedTextField1		
        this.kDFormattedTextField1.setVisible(true);		
        this.kDFormattedTextField1.setHorizontalAlignment(2);		
        this.kDFormattedTextField1.setDataType(1);		
        this.kDFormattedTextField1.setSupportedEmpty(true);		
        this.kDFormattedTextField1.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.kDFormattedTextField1.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.kDFormattedTextField1.setPrecision(2);		
        this.kDFormattedTextField1.setRequired(false);
        // kDFormattedTextField2		
        this.kDFormattedTextField2.setVisible(true);		
        this.kDFormattedTextField2.setHorizontalAlignment(2);		
        this.kDFormattedTextField2.setDataType(1);		
        this.kDFormattedTextField2.setSupportedEmpty(true);		
        this.kDFormattedTextField2.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.kDFormattedTextField2.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.kDFormattedTextField2.setPrecision(2);		
        this.kDFormattedTextField2.setRequired(true);
        this.kDFormattedTextField2.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    txtUnTax_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // kDFormattedTextField3		
        this.kDFormattedTextField3.setVisible(true);		
        this.kDFormattedTextField3.setHorizontalAlignment(2);		
        this.kDFormattedTextField3.setDataType(1);		
        this.kDFormattedTextField3.setSupportedEmpty(true);		
        this.kDFormattedTextField3.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.kDFormattedTextField3.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.kDFormattedTextField3.setPrecision(2);		
        this.kDFormattedTextField3.setRequired(true);
        this.kDFormattedTextField3.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    txtTax_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // kDTextField2		
        this.kDTextField2.setVisible(true);		
        this.kDTextField2.setHorizontalAlignment(2);		
        this.kDTextField2.setMaxLength(100);		
        this.kDTextField2.setRequired(false);		
        this.kDTextField2.setText(resHelper.getString("kDTextField2.text"));
        // kDTextField3		
        this.kDTextField3.setVisible(true);		
        this.kDTextField3.setHorizontalAlignment(2);		
        this.kDTextField3.setMaxLength(100);		
        this.kDTextField3.setRequired(true);		
        this.kDTextField3.setText(resHelper.getString("kDTextField3.text"));
        // kDTextField4		
        this.kDTextField4.setVisible(true);		
        this.kDTextField4.setHorizontalAlignment(2);		
        this.kDTextField4.setMaxLength(100);		
        this.kDTextField4.setRequired(true);		
        this.kDTextField4.setText(resHelper.getString("kDTextField4.text"));
        // kDTextField5		
        this.kDTextField5.setVisible(true);		
        this.kDTextField5.setHorizontalAlignment(2);		
        this.kDTextField5.setMaxLength(100);		
        this.kDTextField5.setRequired(true);		
        this.kDTextField5.setText(resHelper.getString("kDTextField5.text"));
        // kDBizPromptBox4		
        this.kDBizPromptBox4.setQueryInfo("com.kingdee.eas.basedata.org.app.CompanyOrgUnitQuery4AsstAcct");		
        this.kDBizPromptBox4.setVisible(true);		
        this.kDBizPromptBox4.setEditable(true);		
        this.kDBizPromptBox4.setDisplayFormat("$name$");		
        this.kDBizPromptBox4.setEditFormat("$number$");		
        this.kDBizPromptBox4.setCommitFormat("$number$");		
        this.kDBizPromptBox4.setRequired(true);
        // txtInvoiceNumber
        // txtInvoiceCode
        // txtIssuingOffice
        // pkBillingDate
        // txtRegisterNumber
        // prmtInvoiceType
        // prmtApplyer
        // prmtApplyDept
        // txtRemark
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
        kDLabelContainer2.setBounds(new Rectangle(-1, -69, 270, 19));
        this.add(kDLabelContainer2, null);
        kDLabelContainer3.setBounds(new Rectangle(292, -38, 270, 19));
        this.add(kDLabelContainer3, null);
        kDLabelContainer4.setBounds(new Rectangle(290, -214, 270, 19));
        this.add(kDLabelContainer4, null);
        kDLabelContainer7.setBounds(new Rectangle(-1, -37, 270, 19));
        this.add(kDLabelContainer7, null);
        kDLabelContainer8.setBounds(new Rectangle(291, -103, 270, 19));
        this.add(kDLabelContainer8, null);
        kDLabelContainer9.setBounds(new Rectangle(292, -71, 270, 19));
        this.add(kDLabelContainer9, null);
        kDLabelContainer10.setBounds(new Rectangle(-1, -102, 270, 19));
        this.add(kDLabelContainer10, null);
        kDLabelContainer11.setBounds(new Rectangle(-1, -139, 270, 19));
        this.add(kDLabelContainer11, null);
        kDLabelContainer12.setBounds(new Rectangle(290, -140, 270, 19));
        this.add(kDLabelContainer12, null);
        kDLabelContainer13.setBounds(new Rectangle(290, -176, 270, 19));
        this.add(kDLabelContainer13, null);
        kDLabelContainer14.setBounds(new Rectangle(-1, -176, 270, 19));
        this.add(kDLabelContainer14, null);
        kDLabelContainer15.setBounds(new Rectangle(-1, -213, 270, 19));
        this.add(kDLabelContainer15, null);
        kDLabelContainer5.setBounds(new Rectangle(32, 12, 275, 19));
        this.add(kDLabelContainer5, null);
        kDLabelContainer6.setBounds(new Rectangle(320, 10, 275, 19));
        this.add(kDLabelContainer6, null);
        kDLabelContainer16.setBounds(new Rectangle(20, 45, 287, 19));
        this.add(kDLabelContainer16, null);
        btnChcek.setBounds(new Rectangle(612, 9, 101, 21));
        this.add(btnChcek, null);
        kDLabelContainer17.setBounds(new Rectangle(320, 43, 275, 19));
        this.add(kDLabelContainer17, null);
        kDLabelContainer18.setBounds(new Rectangle(11, 78, 298, 19));
        this.add(kDLabelContainer18, null);
        kDLabelContainer19.setBounds(new Rectangle(320, 76, 275, 19));
        this.add(kDLabelContainer19, null);
        kDLabelContainer1.setBounds(new Rectangle(34, 111, 275, 19));
        this.add(kDLabelContainer1, null);
        kDLabelContainer20.setBounds(new Rectangle(320, 109, 275, 19));
        this.add(kDLabelContainer20, null);
        kDLabelContainer21.setBounds(new Rectangle(9, 144, 613, 112));
        this.add(kDLabelContainer21, null);
        //kDLabelContainer2
        kDLabelContainer2.setBoundEditor(kDTextField1);
        //kDLabelContainer3
        kDLabelContainer3.setBoundEditor(kDBizPromptBox2);
        //kDLabelContainer4
        kDLabelContainer4.setBoundEditor(kDBizPromptBox3);
        //kDLabelContainer7
        kDLabelContainer7.setBoundEditor(kDFormattedTextField1);
        //kDLabelContainer8
        kDLabelContainer8.setBoundEditor(kDFormattedTextField2);
        //kDLabelContainer9
        kDLabelContainer9.setBoundEditor(kDFormattedTextField3);
        //kDLabelContainer10
        kDLabelContainer10.setBoundEditor(kDTextField2);
        //kDLabelContainer11
        kDLabelContainer11.setBoundEditor(kDTextField3);
        //kDLabelContainer13
        kDLabelContainer13.setBoundEditor(kDTextField4);
        //kDLabelContainer14
        kDLabelContainer14.setBoundEditor(kDTextField5);
        //kDLabelContainer15
        kDLabelContainer15.setBoundEditor(kDBizPromptBox4);
        //kDLabelContainer5
        kDLabelContainer5.setBoundEditor(txtInvoiceNumber);
        //kDLabelContainer6
        kDLabelContainer6.setBoundEditor(txtInvoiceCode);
        //kDLabelContainer16
        kDLabelContainer16.setBoundEditor(txtIssuingOffice);
        //kDLabelContainer17
        kDLabelContainer17.setBoundEditor(pkBillingDate);
        //kDLabelContainer18
        kDLabelContainer18.setBoundEditor(txtRegisterNumber);
        //kDLabelContainer19
        kDLabelContainer19.setBoundEditor(prmtInvoiceType);
        //kDLabelContainer1
        kDLabelContainer1.setBoundEditor(prmtApplyer);
        //kDLabelContainer20
        kDLabelContainer20.setBoundEditor(prmtApplyDept);
        //kDLabelContainer21
        kDLabelContainer21.setBoundEditor(txtRemark);

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
	    return "com.kingdee.eas.custom.financialinvoice.app.InvoiceUpdateUIHandler";
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
     * output btnChcek_actionPerformed method
     */
    protected void btnChcek_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }

    /**
     * output txtUnTax_dataChanged method
     */
    protected void txtUnTax_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output txtTax_dataChanged method
     */
    protected void txtTax_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
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
        return new MetaDataPK("com.kingdee.eas.custom.financialinvoice.client", "InvoiceUpdateUI");
    }




}