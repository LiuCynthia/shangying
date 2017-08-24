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
public abstract class AbstractInvoiceEditUI extends com.kingdee.eas.framework.client.CoreBillEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractInvoiceEditUI.class);
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCreator;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCreateTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contLastUpdateUser;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contLastUpdateTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contBizDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contDescription;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contAuditor;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contFICompany;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contInvoiceNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contInvoiceCode;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contBillingDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contIssuingOffice;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contRegisterNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contTax;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contUnTax;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contTotalAmount;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contstatus;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contRemark;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contInvoiceType;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkisCreatePayment;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contApplyer;
    protected com.kingdee.bos.ctrl.swing.KDButton btnChcek;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkisCreateVoucher;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contTaxRate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contApplyDept;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtCreator;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker kDDateCreateTime;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtLastUpdateUser;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker kDDateLastUpdateTime;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtNumber;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkBizDate;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtDescription;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtAuditor;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtFICompany;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtInvoiceNumber;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtInvoiceCode;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkBillingDate;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtIssuingOffice;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtRegisterNumber;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtTax;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtUnTax;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtTotalAmount;
    protected com.kingdee.bos.ctrl.swing.KDComboBox status;
    protected com.kingdee.bos.ctrl.swing.KDScrollPane scrollPaneRemark;
    protected com.kingdee.bos.ctrl.swing.KDTextArea txtRemark;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtInvoiceType;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtApplyer;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtTaxRate;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtApplyDept;
    protected com.kingdee.eas.custom.financialinvoice.InvoiceInfo editData = null;
    public final static String STATUS_UPDATE = "UPDATE";
    /**
     * output class constructor
     */
    public AbstractInvoiceEditUI() throws Exception
    {
        super();
        this.defaultObjectName = "editData";
        jbInit();
        
        initUIP();
    }

    /**
     * output jbInit method
     */
    private void jbInit() throws Exception
    {
        this.resHelper = new ResourceBundleHelper(AbstractInvoiceEditUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        //actionSubmit
        String _tempStr = null;
        actionSubmit.setEnabled(true);
        actionSubmit.setDaemonRun(false);

        actionSubmit.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl S"));
        _tempStr = resHelper.getString("ActionSubmit.SHORT_DESCRIPTION");
        actionSubmit.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSubmit.LONG_DESCRIPTION");
        actionSubmit.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSubmit.NAME");
        actionSubmit.putValue(ItemAction.NAME, _tempStr);
        this.actionSubmit.setBindWorkFlow(true);
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionPrint
        actionPrint.setEnabled(true);
        actionPrint.setDaemonRun(false);

        actionPrint.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl P"));
        _tempStr = resHelper.getString("ActionPrint.SHORT_DESCRIPTION");
        actionPrint.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrint.LONG_DESCRIPTION");
        actionPrint.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrint.NAME");
        actionPrint.putValue(ItemAction.NAME, _tempStr);
         this.actionPrint.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionPrint.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionPrint.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionPrintPreview
        actionPrintPreview.setEnabled(true);
        actionPrintPreview.setDaemonRun(false);

        actionPrintPreview.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("shift ctrl P"));
        _tempStr = resHelper.getString("ActionPrintPreview.SHORT_DESCRIPTION");
        actionPrintPreview.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrintPreview.LONG_DESCRIPTION");
        actionPrintPreview.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrintPreview.NAME");
        actionPrintPreview.putValue(ItemAction.NAME, _tempStr);
         this.actionPrintPreview.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionPrintPreview.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionPrintPreview.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        this.contCreator = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contCreateTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contLastUpdateUser = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contLastUpdateTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contBizDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contDescription = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contAuditor = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contFICompany = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contInvoiceNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contInvoiceCode = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contBillingDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contIssuingOffice = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contRegisterNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contTax = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contUnTax = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contTotalAmount = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contstatus = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contRemark = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contInvoiceType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.chkisCreatePayment = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.contApplyer = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.btnChcek = new com.kingdee.bos.ctrl.swing.KDButton();
        this.chkisCreateVoucher = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.contTaxRate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contApplyDept = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.prmtCreator = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDDateCreateTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.prmtLastUpdateUser = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDDateLastUpdateTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.pkBizDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtDescription = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtAuditor = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtFICompany = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtInvoiceNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtInvoiceCode = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.pkBillingDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtIssuingOffice = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtRegisterNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtTax = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtUnTax = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtTotalAmount = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.status = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.scrollPaneRemark = new com.kingdee.bos.ctrl.swing.KDScrollPane();
        this.txtRemark = new com.kingdee.bos.ctrl.swing.KDTextArea();
        this.prmtInvoiceType = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtApplyer = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtTaxRate = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtApplyDept = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.contCreator.setName("contCreator");
        this.contCreateTime.setName("contCreateTime");
        this.contLastUpdateUser.setName("contLastUpdateUser");
        this.contLastUpdateTime.setName("contLastUpdateTime");
        this.contNumber.setName("contNumber");
        this.contBizDate.setName("contBizDate");
        this.contDescription.setName("contDescription");
        this.contAuditor.setName("contAuditor");
        this.contFICompany.setName("contFICompany");
        this.contInvoiceNumber.setName("contInvoiceNumber");
        this.contInvoiceCode.setName("contInvoiceCode");
        this.contBillingDate.setName("contBillingDate");
        this.contIssuingOffice.setName("contIssuingOffice");
        this.contRegisterNumber.setName("contRegisterNumber");
        this.contTax.setName("contTax");
        this.contUnTax.setName("contUnTax");
        this.contTotalAmount.setName("contTotalAmount");
        this.contstatus.setName("contstatus");
        this.contRemark.setName("contRemark");
        this.contInvoiceType.setName("contInvoiceType");
        this.chkisCreatePayment.setName("chkisCreatePayment");
        this.contApplyer.setName("contApplyer");
        this.btnChcek.setName("btnChcek");
        this.chkisCreateVoucher.setName("chkisCreateVoucher");
        this.contTaxRate.setName("contTaxRate");
        this.contApplyDept.setName("contApplyDept");
        this.prmtCreator.setName("prmtCreator");
        this.kDDateCreateTime.setName("kDDateCreateTime");
        this.prmtLastUpdateUser.setName("prmtLastUpdateUser");
        this.kDDateLastUpdateTime.setName("kDDateLastUpdateTime");
        this.txtNumber.setName("txtNumber");
        this.pkBizDate.setName("pkBizDate");
        this.txtDescription.setName("txtDescription");
        this.prmtAuditor.setName("prmtAuditor");
        this.prmtFICompany.setName("prmtFICompany");
        this.txtInvoiceNumber.setName("txtInvoiceNumber");
        this.txtInvoiceCode.setName("txtInvoiceCode");
        this.pkBillingDate.setName("pkBillingDate");
        this.txtIssuingOffice.setName("txtIssuingOffice");
        this.txtRegisterNumber.setName("txtRegisterNumber");
        this.txtTax.setName("txtTax");
        this.txtUnTax.setName("txtUnTax");
        this.txtTotalAmount.setName("txtTotalAmount");
        this.status.setName("status");
        this.scrollPaneRemark.setName("scrollPaneRemark");
        this.txtRemark.setName("txtRemark");
        this.prmtInvoiceType.setName("prmtInvoiceType");
        this.prmtApplyer.setName("prmtApplyer");
        this.txtTaxRate.setName("txtTaxRate");
        this.prmtApplyDept.setName("prmtApplyDept");
        // CoreUI		
        this.btnEdit.setEnabled(false);		
        this.btnEdit.setVisible(false);		
        this.btnSave.setEnabled(false);		
        this.btnSave.setVisible(false);		
        this.btnRemove.setEnabled(false);		
        this.btnRemove.setVisible(false);		
        this.btnFirst.setVisible(false);		
        this.btnPre.setVisible(false);		
        this.btnNext.setVisible(false);		
        this.btnLast.setVisible(false);		
        this.btnAddLine.setVisible(false);		
        this.btnCopyLine.setVisible(false);		
        this.btnInsertLine.setVisible(false);		
        this.btnRemoveLine.setVisible(false);		
        this.btnCreateFrom.setVisible(false);		
        this.btnTraceUp.setVisible(false);		
        this.btnTraceDown.setVisible(false);		
        this.btnAuditResult.setVisible(false);		
        this.separator1.setVisible(false);		
        this.separator3.setVisible(false);		
        this.menuItemTraceUp.setVisible(false);		
        this.menuItemTraceDown.setVisible(false);		
        this.menuItemViewSubmitProccess.setVisible(false);		
        this.menuItemViewDoProccess.setVisible(false);		
        this.btnNextPerson.setEnabled(false);		
        this.btnNextPerson.setVisible(false);		
        this.menuItemAuditResult.setVisible(false);		
        this.menuTable1.setVisible(false);		
        this.menuItemAddLine.setVisible(false);		
        this.menuItemInsertLine.setVisible(false);		
        this.menuItemRemoveLine.setVisible(false);		
        this.menuItemCreateTo.setVisible(true);		
        this.menuItemCopyLine.setVisible(false);
        // contCreator		
        this.contCreator.setBoundLabelText(resHelper.getString("contCreator.boundLabelText"));		
        this.contCreator.setBoundLabelLength(100);		
        this.contCreator.setBoundLabelUnderline(true);		
        this.contCreator.setEnabled(false);		
        this.contCreator.setVisible(false);
        // contCreateTime		
        this.contCreateTime.setBoundLabelText(resHelper.getString("contCreateTime.boundLabelText"));		
        this.contCreateTime.setBoundLabelLength(100);		
        this.contCreateTime.setBoundLabelUnderline(true);		
        this.contCreateTime.setEnabled(false);		
        this.contCreateTime.setVisible(false);
        // contLastUpdateUser		
        this.contLastUpdateUser.setBoundLabelText(resHelper.getString("contLastUpdateUser.boundLabelText"));		
        this.contLastUpdateUser.setBoundLabelLength(100);		
        this.contLastUpdateUser.setBoundLabelUnderline(true);		
        this.contLastUpdateUser.setEnabled(false);		
        this.contLastUpdateUser.setVisible(false);
        // contLastUpdateTime		
        this.contLastUpdateTime.setBoundLabelText(resHelper.getString("contLastUpdateTime.boundLabelText"));		
        this.contLastUpdateTime.setBoundLabelLength(100);		
        this.contLastUpdateTime.setBoundLabelUnderline(true);		
        this.contLastUpdateTime.setEnabled(false);		
        this.contLastUpdateTime.setVisible(false);
        // contNumber		
        this.contNumber.setBoundLabelText(resHelper.getString("contNumber.boundLabelText"));		
        this.contNumber.setBoundLabelLength(100);		
        this.contNumber.setBoundLabelUnderline(true);
        // contBizDate		
        this.contBizDate.setBoundLabelText(resHelper.getString("contBizDate.boundLabelText"));		
        this.contBizDate.setBoundLabelLength(100);		
        this.contBizDate.setBoundLabelUnderline(true);		
        this.contBizDate.setBoundLabelAlignment(7);		
        this.contBizDate.setVisible(false);		
        this.contBizDate.setEnabled(false);
        // contDescription		
        this.contDescription.setBoundLabelText(resHelper.getString("contDescription.boundLabelText"));		
        this.contDescription.setBoundLabelLength(100);		
        this.contDescription.setBoundLabelUnderline(true);		
        this.contDescription.setEnabled(false);		
        this.contDescription.setVisible(false);
        // contAuditor		
        this.contAuditor.setBoundLabelText(resHelper.getString("contAuditor.boundLabelText"));		
        this.contAuditor.setBoundLabelLength(100);		
        this.contAuditor.setBoundLabelUnderline(true);		
        this.contAuditor.setEnabled(false);		
        this.contAuditor.setVisible(false);
        // contFICompany		
        this.contFICompany.setBoundLabelText(resHelper.getString("contFICompany.boundLabelText"));		
        this.contFICompany.setBoundLabelLength(100);		
        this.contFICompany.setBoundLabelUnderline(true);		
        this.contFICompany.setVisible(true);
        // contInvoiceNumber		
        this.contInvoiceNumber.setBoundLabelText(resHelper.getString("contInvoiceNumber.boundLabelText"));		
        this.contInvoiceNumber.setBoundLabelLength(100);		
        this.contInvoiceNumber.setBoundLabelUnderline(true);		
        this.contInvoiceNumber.setVisible(true);
        // contInvoiceCode		
        this.contInvoiceCode.setBoundLabelText(resHelper.getString("contInvoiceCode.boundLabelText"));		
        this.contInvoiceCode.setBoundLabelLength(100);		
        this.contInvoiceCode.setBoundLabelUnderline(true);		
        this.contInvoiceCode.setVisible(true);
        // contBillingDate		
        this.contBillingDate.setBoundLabelText(resHelper.getString("contBillingDate.boundLabelText"));		
        this.contBillingDate.setBoundLabelLength(100);		
        this.contBillingDate.setBoundLabelUnderline(true);		
        this.contBillingDate.setVisible(true);
        // contIssuingOffice		
        this.contIssuingOffice.setBoundLabelText(resHelper.getString("contIssuingOffice.boundLabelText"));		
        this.contIssuingOffice.setBoundLabelLength(100);		
        this.contIssuingOffice.setBoundLabelUnderline(true);		
        this.contIssuingOffice.setVisible(true);
        // contRegisterNumber		
        this.contRegisterNumber.setBoundLabelText(resHelper.getString("contRegisterNumber.boundLabelText"));		
        this.contRegisterNumber.setBoundLabelLength(100);		
        this.contRegisterNumber.setBoundLabelUnderline(true);		
        this.contRegisterNumber.setVisible(true);
        // contTax		
        this.contTax.setBoundLabelText(resHelper.getString("contTax.boundLabelText"));		
        this.contTax.setBoundLabelLength(100);		
        this.contTax.setBoundLabelUnderline(true);		
        this.contTax.setVisible(true);
        // contUnTax		
        this.contUnTax.setBoundLabelText(resHelper.getString("contUnTax.boundLabelText"));		
        this.contUnTax.setBoundLabelLength(100);		
        this.contUnTax.setBoundLabelUnderline(true);		
        this.contUnTax.setVisible(true);
        // contTotalAmount		
        this.contTotalAmount.setBoundLabelText(resHelper.getString("contTotalAmount.boundLabelText"));		
        this.contTotalAmount.setBoundLabelLength(100);		
        this.contTotalAmount.setBoundLabelUnderline(true);		
        this.contTotalAmount.setVisible(true);
        // contstatus		
        this.contstatus.setBoundLabelText(resHelper.getString("contstatus.boundLabelText"));		
        this.contstatus.setBoundLabelLength(100);		
        this.contstatus.setBoundLabelUnderline(true);		
        this.contstatus.setVisible(false);		
        this.contstatus.setEnabled(false);
        // contRemark		
        this.contRemark.setBoundLabelText(resHelper.getString("contRemark.boundLabelText"));		
        this.contRemark.setBoundLabelLength(50);		
        this.contRemark.setBoundLabelUnderline(true);		
        this.contRemark.setVisible(true);
        // contInvoiceType		
        this.contInvoiceType.setBoundLabelText(resHelper.getString("contInvoiceType.boundLabelText"));		
        this.contInvoiceType.setBoundLabelLength(100);		
        this.contInvoiceType.setBoundLabelUnderline(true);		
        this.contInvoiceType.setVisible(true);
        // chkisCreatePayment		
        this.chkisCreatePayment.setText(resHelper.getString("chkisCreatePayment.text"));		
        this.chkisCreatePayment.setHorizontalAlignment(2);		
        this.chkisCreatePayment.setEnabled(false);		
        this.chkisCreatePayment.setVisible(false);
        // contApplyer		
        this.contApplyer.setBoundLabelText(resHelper.getString("contApplyer.boundLabelText"));		
        this.contApplyer.setBoundLabelLength(100);		
        this.contApplyer.setBoundLabelUnderline(true);		
        this.contApplyer.setVisible(true);
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
        // chkisCreateVoucher		
        this.chkisCreateVoucher.setText(resHelper.getString("chkisCreateVoucher.text"));		
        this.chkisCreateVoucher.setHorizontalAlignment(2);		
        this.chkisCreateVoucher.setVisible(false);		
        this.chkisCreateVoucher.setEnabled(false);
        // contTaxRate		
        this.contTaxRate.setBoundLabelText(resHelper.getString("contTaxRate.boundLabelText"));		
        this.contTaxRate.setBoundLabelLength(100);		
        this.contTaxRate.setBoundLabelUnderline(true);		
        this.contTaxRate.setVisible(true);
        // contApplyDept		
        this.contApplyDept.setBoundLabelText(resHelper.getString("contApplyDept.boundLabelText"));		
        this.contApplyDept.setBoundLabelLength(100);		
        this.contApplyDept.setBoundLabelUnderline(true);		
        this.contApplyDept.setVisible(true);
        // prmtCreator		
        this.prmtCreator.setEnabled(false);
        // kDDateCreateTime		
        this.kDDateCreateTime.setTimeEnabled(true);		
        this.kDDateCreateTime.setEnabled(false);
        // prmtLastUpdateUser		
        this.prmtLastUpdateUser.setEnabled(false);
        // kDDateLastUpdateTime		
        this.kDDateLastUpdateTime.setTimeEnabled(true);		
        this.kDDateLastUpdateTime.setEnabled(false);
        // txtNumber		
        this.txtNumber.setMaxLength(80);
        // pkBizDate		
        this.pkBizDate.setVisible(true);		
        this.pkBizDate.setEnabled(true);
        // txtDescription		
        this.txtDescription.setMaxLength(80);
        // prmtAuditor		
        this.prmtAuditor.setEnabled(false);
        // prmtFICompany		
        this.prmtFICompany.setQueryInfo("com.kingdee.eas.basedata.org.app.CompanyOrgUnitQuery4AsstAcct");		
        this.prmtFICompany.setVisible(true);		
        this.prmtFICompany.setEditable(true);		
        this.prmtFICompany.setDisplayFormat("$name$");		
        this.prmtFICompany.setEditFormat("$number$");		
        this.prmtFICompany.setCommitFormat("$number$");		
        this.prmtFICompany.setRequired(true);
        		setOrgF7(prmtFICompany,com.kingdee.eas.basedata.org.OrgType.getEnum("Company"));
					
        // txtInvoiceNumber		
        this.txtInvoiceNumber.setVisible(true);		
        this.txtInvoiceNumber.setHorizontalAlignment(2);		
        this.txtInvoiceNumber.setMaxLength(100);		
        this.txtInvoiceNumber.setRequired(true);
        // txtInvoiceCode		
        this.txtInvoiceCode.setVisible(true);		
        this.txtInvoiceCode.setHorizontalAlignment(2);		
        this.txtInvoiceCode.setMaxLength(100);		
        this.txtInvoiceCode.setRequired(true);
        // pkBillingDate		
        this.pkBillingDate.setVisible(true);		
        this.pkBillingDate.setRequired(true);
        // txtIssuingOffice		
        this.txtIssuingOffice.setVisible(true);		
        this.txtIssuingOffice.setHorizontalAlignment(2);		
        this.txtIssuingOffice.setMaxLength(100);		
        this.txtIssuingOffice.setRequired(true);
        // txtRegisterNumber		
        this.txtRegisterNumber.setVisible(true);		
        this.txtRegisterNumber.setHorizontalAlignment(2);		
        this.txtRegisterNumber.setMaxLength(100);		
        this.txtRegisterNumber.setRequired(false);
        // txtTax		
        this.txtTax.setVisible(true);		
        this.txtTax.setHorizontalAlignment(2);		
        this.txtTax.setDataType(1);		
        this.txtTax.setSupportedEmpty(true);		
        this.txtTax.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.txtTax.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.txtTax.setPrecision(2);		
        this.txtTax.setRequired(true);
        this.txtTax.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    txtTax_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // txtUnTax		
        this.txtUnTax.setVisible(true);		
        this.txtUnTax.setHorizontalAlignment(2);		
        this.txtUnTax.setDataType(1);		
        this.txtUnTax.setSupportedEmpty(true);		
        this.txtUnTax.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.txtUnTax.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.txtUnTax.setPrecision(2);		
        this.txtUnTax.setRequired(true);
        this.txtUnTax.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    txtUnTax_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // txtTotalAmount		
        this.txtTotalAmount.setVisible(true);		
        this.txtTotalAmount.setHorizontalAlignment(2);		
        this.txtTotalAmount.setDataType(1);		
        this.txtTotalAmount.setSupportedEmpty(true);		
        this.txtTotalAmount.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.txtTotalAmount.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.txtTotalAmount.setPrecision(2);		
        this.txtTotalAmount.setRequired(false);
        // status		
        this.status.setVisible(true);		
        this.status.addItems(EnumUtils.getEnumList("com.kingdee.eas.custom.financialinvoice.app.InvoiceEnum").toArray());		
        this.status.setRequired(false);
        // scrollPaneRemark
        // txtRemark		
        this.txtRemark.setVisible(true);		
        this.txtRemark.setRequired(false);		
        this.txtRemark.setMaxLength(255);
        // prmtInvoiceType		
        this.prmtInvoiceType.setQueryInfo("com.kingdee.eas.custom.financialinvoice.app.InvoicetypeQuery");		
        this.prmtInvoiceType.setVisible(true);		
        this.prmtInvoiceType.setEditable(true);		
        this.prmtInvoiceType.setDisplayFormat("$name$");		
        this.prmtInvoiceType.setEditFormat("$number$");		
        this.prmtInvoiceType.setCommitFormat("$number$");		
        this.prmtInvoiceType.setRequired(true);
        // prmtApplyer		
        this.prmtApplyer.setQueryInfo("com.kingdee.eas.basedata.person.app.PersonQuery");		
        this.prmtApplyer.setVisible(true);		
        this.prmtApplyer.setEditable(true);		
        this.prmtApplyer.setDisplayFormat("$name$");		
        this.prmtApplyer.setEditFormat("$number$");		
        this.prmtApplyer.setCommitFormat("$number$");		
        this.prmtApplyer.setRequired(true);
        // txtTaxRate		
        this.txtTaxRate.setVisible(true);		
        this.txtTaxRate.setHorizontalAlignment(2);		
        this.txtTaxRate.setMaxLength(100);		
        this.txtTaxRate.setRequired(false);
        // prmtApplyDept		
        this.prmtApplyDept.setQueryInfo("com.kingdee.eas.basedata.org.app.AdminItemQuery");		
        this.prmtApplyDept.setVisible(true);		
        this.prmtApplyDept.setEditable(true);		
        this.prmtApplyDept.setDisplayFormat("$name$");		
        this.prmtApplyDept.setEditFormat("$number$");		
        this.prmtApplyDept.setCommitFormat("$number$");		
        this.prmtApplyDept.setRequired(false);
        this.prmtApplyDept.addSelectorListener(new com.kingdee.bos.ctrl.swing.event.SelectorListener() {
            public void willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) {
                try {
                    prmtApplyDept_willShow(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        this.setFocusTraversalPolicy(new com.kingdee.bos.ui.UIFocusTraversalPolicy(new java.awt.Component[] {txtInvoiceNumber,txtInvoiceCode,pkBillingDate,txtIssuingOffice,txtTax,txtUnTax,txtTotalAmount,status,txtRemark,prmtInvoiceType,chkisCreatePayment,prmtApplyer,txtTaxRate,prmtApplyDept}));
        this.setFocusCycleRoot(true);
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
        this.setBounds(new Rectangle(0, 0, 972, 546));
        this.setLayout(null);
        contCreator.setBounds(new Rectangle(334, 603, 270, 19));
        this.add(contCreator, null);
        contCreateTime.setBounds(new Rectangle(730, 524, 270, 19));
        this.add(contCreateTime, null);
        contLastUpdateUser.setBounds(new Rectangle(363, 577, 270, 19));
        this.add(contLastUpdateUser, null);
        contLastUpdateTime.setBounds(new Rectangle(730, 555, 270, 19));
        this.add(contLastUpdateTime, null);
        contNumber.setBounds(new Rectangle(8, 436, 270, 19));
        this.add(contNumber, null);
        contBizDate.setBounds(new Rectangle(73, 514, 270, 19));
        this.add(contBizDate, null);
        contDescription.setBounds(new Rectangle(428, 532, 270, 19));
        this.add(contDescription, null);
        contAuditor.setBounds(new Rectangle(9, 593, 270, 19));
        this.add(contAuditor, null);
        contFICompany.setBounds(new Rectangle(17, 17, 270, 19));
        this.add(contFICompany, null);
        contInvoiceNumber.setBounds(new Rectangle(17, 54, 270, 19));
        this.add(contInvoiceNumber, null);
        contInvoiceCode.setBounds(new Rectangle(308, 54, 270, 19));
        this.add(contInvoiceCode, null);
        contBillingDate.setBounds(new Rectangle(308, 90, 270, 19));
        this.add(contBillingDate, null);
        contIssuingOffice.setBounds(new Rectangle(17, 91, 270, 19));
        this.add(contIssuingOffice, null);
        contRegisterNumber.setBounds(new Rectangle(17, 128, 270, 19));
        this.add(contRegisterNumber, null);
        contTax.setBounds(new Rectangle(310, 159, 270, 19));
        this.add(contTax, null);
        contUnTax.setBounds(new Rectangle(309, 127, 270, 19));
        this.add(contUnTax, null);
        contTotalAmount.setBounds(new Rectangle(17, 193, 270, 19));
        this.add(contTotalAmount, null);
        contstatus.setBounds(new Rectangle(320, 396, 270, 19));
        this.add(contstatus, null);
        contRemark.setBounds(new Rectangle(16, 263, 565, 105));
        this.add(contRemark, null);
        contInvoiceType.setBounds(new Rectangle(308, 16, 270, 19));
        this.add(contInvoiceType, null);
        chkisCreatePayment.setBounds(new Rectangle(11, 390, 270, 19));
        this.add(chkisCreatePayment, null);
        contApplyer.setBounds(new Rectangle(310, 192, 270, 19));
        this.add(contApplyer, null);
        btnChcek.setBounds(new Rectangle(581, 53, 108, 21));
        this.add(btnChcek, null);
        chkisCreateVoucher.setBounds(new Rectangle(9, 413, 270, 19));
        this.add(chkisCreateVoucher, null);
        contTaxRate.setBounds(new Rectangle(17, 161, 270, 19));
        this.add(contTaxRate, null);
        contApplyDept.setBounds(new Rectangle(18, 230, 270, 19));
        this.add(contApplyDept, null);
        //contCreator
        contCreator.setBoundEditor(prmtCreator);
        //contCreateTime
        contCreateTime.setBoundEditor(kDDateCreateTime);
        //contLastUpdateUser
        contLastUpdateUser.setBoundEditor(prmtLastUpdateUser);
        //contLastUpdateTime
        contLastUpdateTime.setBoundEditor(kDDateLastUpdateTime);
        //contNumber
        contNumber.setBoundEditor(txtNumber);
        //contBizDate
        contBizDate.setBoundEditor(pkBizDate);
        //contDescription
        contDescription.setBoundEditor(txtDescription);
        //contAuditor
        contAuditor.setBoundEditor(prmtAuditor);
        //contFICompany
        contFICompany.setBoundEditor(prmtFICompany);
        //contInvoiceNumber
        contInvoiceNumber.setBoundEditor(txtInvoiceNumber);
        //contInvoiceCode
        contInvoiceCode.setBoundEditor(txtInvoiceCode);
        //contBillingDate
        contBillingDate.setBoundEditor(pkBillingDate);
        //contIssuingOffice
        contIssuingOffice.setBoundEditor(txtIssuingOffice);
        //contRegisterNumber
        contRegisterNumber.setBoundEditor(txtRegisterNumber);
        //contTax
        contTax.setBoundEditor(txtTax);
        //contUnTax
        contUnTax.setBoundEditor(txtUnTax);
        //contTotalAmount
        contTotalAmount.setBoundEditor(txtTotalAmount);
        //contstatus
        contstatus.setBoundEditor(status);
        //contRemark
        contRemark.setBoundEditor(scrollPaneRemark);
        //scrollPaneRemark
        scrollPaneRemark.getViewport().add(txtRemark, null);
        //contInvoiceType
        contInvoiceType.setBoundEditor(prmtInvoiceType);
        //contApplyer
        contApplyer.setBoundEditor(prmtApplyer);
        //contTaxRate
        contTaxRate.setBoundEditor(txtTaxRate);
        //contApplyDept
        contApplyDept.setBoundEditor(prmtApplyDept);

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
        this.menuBar.add(menuTable1);
        this.menuBar.add(menuTool);
        this.menuBar.add(menuWorkflow);
        this.menuBar.add(menuHelp);
        //menuFile
        menuFile.add(menuItemAddNew);
        menuFile.add(kDSeparator1);
        menuFile.add(menuItemSave);
        menuFile.add(menuItemSubmit);
        menuFile.add(menuSubmitOption);
        menuFile.add(rMenuItemSubmit);
        menuFile.add(rMenuItemSubmitAndAddNew);
        menuFile.add(rMenuItemSubmitAndPrint);
        menuFile.add(separatorFile1);
        menuFile.add(MenuItemAttachment);
        menuFile.add(kDSeparator2);
        menuFile.add(menuItemPageSetup);
        menuFile.add(menuItemPrint);
        menuFile.add(menuItemPrintPreview);
        menuFile.add(kDSeparator6);
        menuFile.add(menuItemSendMail);
        menuFile.add(kDSeparator3);
        menuFile.add(menuItemExitCurrent);
        //menuSubmitOption
        menuSubmitOption.add(chkMenuItemSubmitAndAddNew);
        menuSubmitOption.add(chkMenuItemSubmitAndPrint);
        //menuEdit
        menuEdit.add(menuItemCopy);
        menuEdit.add(menuItemEdit);
        menuEdit.add(menuItemRemove);
        menuEdit.add(kDSeparator4);
        menuEdit.add(menuItemReset);
        menuEdit.add(separator1);
        menuEdit.add(menuItemCreateFrom);
        menuEdit.add(menuItemCreateTo);
        menuEdit.add(menuItemCopyFrom);
        menuEdit.add(separatorEdit1);
        menuEdit.add(menuItemEnterToNextRow);
        menuEdit.add(separator2);
        //MenuService
        MenuService.add(MenuItemKnowStore);
        MenuService.add(MenuItemAnwser);
        MenuService.add(SepratorService);
        MenuService.add(MenuItemRemoteAssist);
        //menuView
        menuView.add(menuItemFirst);
        menuView.add(menuItemPre);
        menuView.add(menuItemNext);
        menuView.add(menuItemLast);
        menuView.add(separator3);
        menuView.add(menuItemTraceUp);
        menuView.add(menuItemTraceDown);
        menuView.add(kDSeparator7);
        menuView.add(menuItemLocate);
        //menuBiz
        menuBiz.add(menuItemCancelCancel);
        menuBiz.add(menuItemCancel);
        menuBiz.add(MenuItemVoucher);
        menuBiz.add(menuItemDelVoucher);
        //menuTable1
        menuTable1.add(menuItemAddLine);
        menuTable1.add(menuItemCopyLine);
        menuTable1.add(menuItemInsertLine);
        menuTable1.add(menuItemRemoveLine);
        //menuTool
        menuTool.add(menuItemSendMessage);
        menuTool.add(menuItemMsgFormat);
        menuTool.add(menuItemCalculator);
        //menuWorkflow
        menuWorkflow.add(menuItemStartWorkFlow);
        menuWorkflow.add(separatorWF1);
        menuWorkflow.add(menuItemViewSubmitProccess);
        menuWorkflow.add(menuItemViewDoProccess);
        menuWorkflow.add(MenuItemWFG);
        menuWorkflow.add(menuItemWorkFlowList);
        menuWorkflow.add(separatorWF2);
        menuWorkflow.add(menuItemMultiapprove);
        menuWorkflow.add(menuItemNextPerson);
        menuWorkflow.add(menuItemAuditResult);
        menuWorkflow.add(kDSeparator5);
        menuWorkflow.add(kDMenuItemSendMessage);
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
        this.toolBar.add(btnEdit);
        this.toolBar.add(btnSave);
        this.toolBar.add(btnReset);
        this.toolBar.add(btnSubmit);
        this.toolBar.add(btnCopy);
        this.toolBar.add(btnRemove);
        this.toolBar.add(btnCancelCancel);
        this.toolBar.add(btnCancel);
        this.toolBar.add(btnAttachment);
        this.toolBar.add(separatorFW1);
        this.toolBar.add(btnPageSetup);
        this.toolBar.add(btnPrint);
        this.toolBar.add(btnPrintPreview);
        this.toolBar.add(separatorFW2);
        this.toolBar.add(btnFirst);
        this.toolBar.add(btnPre);
        this.toolBar.add(btnNext);
        this.toolBar.add(btnLast);
        this.toolBar.add(separatorFW3);
        this.toolBar.add(btnTraceUp);
        this.toolBar.add(btnTraceDown);
        this.toolBar.add(btnWorkFlowG);
        this.toolBar.add(btnSignature);
        this.toolBar.add(btnViewSignature);
        this.toolBar.add(separatorFW4);
        this.toolBar.add(separatorFW7);
        this.toolBar.add(btnCreateFrom);
        this.toolBar.add(btnCopyFrom);
        this.toolBar.add(btnCreateTo);
        this.toolBar.add(separatorFW5);
        this.toolBar.add(separatorFW8);
        this.toolBar.add(btnAddLine);
        this.toolBar.add(btnCopyLine);
        this.toolBar.add(btnInsertLine);
        this.toolBar.add(btnRemoveLine);
        this.toolBar.add(separatorFW6);
        this.toolBar.add(separatorFW9);
        this.toolBar.add(btnVoucher);
        this.toolBar.add(btnDelVoucher);
        this.toolBar.add(btnAuditResult);
        this.toolBar.add(btnMultiapprove);
        this.toolBar.add(btnWFViewdoProccess);
        this.toolBar.add(btnWFViewSubmitProccess);
        this.toolBar.add(btnNextPerson);


    }

	//Regiester control's property binding.
	private void registerBindings(){
		dataBinder.registerBinding("isCreatePayment", boolean.class, this.chkisCreatePayment, "selected");
		dataBinder.registerBinding("isCreateVoucher", boolean.class, this.chkisCreateVoucher, "selected");
		dataBinder.registerBinding("creator", com.kingdee.eas.base.permission.UserInfo.class, this.prmtCreator, "data");
		dataBinder.registerBinding("createTime", java.sql.Timestamp.class, this.kDDateCreateTime, "value");
		dataBinder.registerBinding("lastUpdateUser", com.kingdee.eas.base.permission.UserInfo.class, this.prmtLastUpdateUser, "data");
		dataBinder.registerBinding("lastUpdateTime", java.sql.Timestamp.class, this.kDDateLastUpdateTime, "value");
		dataBinder.registerBinding("number", String.class, this.txtNumber, "text");
		dataBinder.registerBinding("bizDate", java.util.Date.class, this.pkBizDate, "value");
		dataBinder.registerBinding("description", String.class, this.txtDescription, "text");
		dataBinder.registerBinding("auditor", com.kingdee.eas.base.permission.UserInfo.class, this.prmtAuditor, "data");
		dataBinder.registerBinding("FICompany", com.kingdee.eas.basedata.org.CompanyOrgUnitInfo.class, this.prmtFICompany, "data");
		dataBinder.registerBinding("InvoiceNumber", String.class, this.txtInvoiceNumber, "text");
		dataBinder.registerBinding("InvoiceCode", String.class, this.txtInvoiceCode, "text");
		dataBinder.registerBinding("BillingDate", java.util.Date.class, this.pkBillingDate, "value");
		dataBinder.registerBinding("IssuingOffice", String.class, this.txtIssuingOffice, "text");
		dataBinder.registerBinding("RegisterNumber", String.class, this.txtRegisterNumber, "text");
		dataBinder.registerBinding("Tax", java.math.BigDecimal.class, this.txtTax, "value");
		dataBinder.registerBinding("UnTax", java.math.BigDecimal.class, this.txtUnTax, "value");
		dataBinder.registerBinding("TotalAmount", java.math.BigDecimal.class, this.txtTotalAmount, "value");
		dataBinder.registerBinding("status", com.kingdee.eas.custom.financialinvoice.app.InvoiceEnum.class, this.status, "selectedItem");
		dataBinder.registerBinding("Remark", String.class, this.txtRemark, "text");
		dataBinder.registerBinding("InvoiceType", com.kingdee.eas.custom.financialinvoice.InvoicetypeInfo.class, this.prmtInvoiceType, "data");
		dataBinder.registerBinding("Applyer", com.kingdee.eas.basedata.person.PersonInfo.class, this.prmtApplyer, "data");
		dataBinder.registerBinding("TaxRate", String.class, this.txtTaxRate, "text");
		dataBinder.registerBinding("ApplyDept", com.kingdee.eas.basedata.org.AdminOrgUnitInfo.class, this.prmtApplyDept, "data");		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.custom.financialinvoice.app.InvoiceEditUIHandler";
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
     * output onShow method
     */
    public void onShow() throws Exception
    {
        super.onShow();
        this.txtInvoiceNumber.requestFocusInWindow();
    }

	
	

    /**
     * output setDataObject method
     */
    public void setDataObject(IObjectValue dataObject)
    {
        IObjectValue ov = dataObject;        	    	
        super.setDataObject(ov);
        this.editData = (com.kingdee.eas.custom.financialinvoice.InvoiceInfo)ov;
    }
    protected void removeByPK(IObjectPK pk) throws Exception {
    	IObjectValue editData = this.editData;
    	super.removeByPK(pk);
    	recycleNumberByOrg(editData,"Company",editData.getString("number"));
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
    protected void setAutoNumberByOrg(String orgType) {
    	if (editData == null) return;
		if (editData.getNumber() == null) {
            try {
            	String companyID = null;
				if(!com.kingdee.util.StringUtils.isEmpty(orgType) && !"NONE".equalsIgnoreCase(orgType) && com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType))!=null) {
					companyID = com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType)).getString("id");
				}
				else if (com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit() != null) {
					companyID = ((com.kingdee.eas.basedata.org.OrgUnitInfo)com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit()).getString("id");
            	}
				com.kingdee.eas.base.codingrule.ICodingRuleManager iCodingRuleManager = com.kingdee.eas.base.codingrule.CodingRuleManagerFactory.getRemoteInstance();
		        if (iCodingRuleManager.isExist(editData, companyID)) {
		            if (iCodingRuleManager.isAddView(editData, companyID)) {
		            	editData.setNumber(iCodingRuleManager.getNumber(editData,companyID));
		            }
	                txtNumber.setEnabled(false);
		        }
            }
            catch (Exception e) {
                handUIException(e);
                this.oldData = editData;
                com.kingdee.eas.util.SysUtil.abort();
            } 
        } 
        else {
            if (editData.getNumber().trim().length() > 0) {
                txtNumber.setText(editData.getNumber());
            }
        }
    }
			protected com.kingdee.eas.basedata.org.OrgType getMainBizOrgType() {
			return com.kingdee.eas.basedata.org.OrgType.getEnum("Company");
		}

	protected KDBizPromptBox getMainBizOrg() {
		return prmtFICompany;
}


    /**
     * output loadFields method
     */
    public void loadFields()
    {
        		setAutoNumberByOrg("Company");
        dataBinder.loadFields();
    }
		protected void setOrgF7(KDBizPromptBox f7,com.kingdee.eas.basedata.org.OrgType orgType) throws Exception
		{
			com.kingdee.bos.ctrl.extendcontrols.ext.OrgUnitFilterInfoProducer oufip=(com.kingdee.bos.ctrl.extendcontrols.ext.OrgUnitFilterInfoProducer)com.kingdee.bos.ctrl.extendcontrols.ext.FilterInfoProducerFactory.getOrgUnitFilterInfoProducer(orgType);
			oufip.getModel().setIsCUFilter(true);
			f7.setFilterInfoProducer(oufip);
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
		getValidateHelper().registerBindProperty("isCreatePayment", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("isCreateVoucher", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("creator", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("createTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("lastUpdateUser", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("lastUpdateTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("number", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bizDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("description", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("auditor", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("FICompany", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("InvoiceNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("InvoiceCode", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("BillingDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("IssuingOffice", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RegisterNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Tax", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("UnTax", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TotalAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("status", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Remark", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("InvoiceType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Applyer", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TaxRate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("ApplyDept", ValidateHelper.ON_SAVE);    		
	}



    /**
     * output setOprtState method
     */
    public void setOprtState(String oprtType)
    {
        super.setOprtState(oprtType);
        if (STATUS_ADDNEW.equals(this.oprtState)) {
        } else if (STATUS_EDIT.equals(this.oprtState)) {
        } else if (STATUS_VIEW.equals(this.oprtState)) {
        } else if (STATUS_FINDVIEW.equals(this.oprtState)) {
        }
    }

    /**
     * output btnChcek_actionPerformed method
     */
    protected void btnChcek_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }

    /**
     * output txtTax_dataChanged method
     */
    protected void txtTax_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output txtUnTax_dataChanged method
     */
    protected void txtUnTax_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output prmtApplyDept_willShow method
     */
    protected void prmtApplyDept_willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) throws Exception
    {
    }

    /**
     * output getSelectors method
     */
    public SelectorItemCollection getSelectors()
    {
        SelectorItemCollection sic = new SelectorItemCollection();
        sic.add(new SelectorItemInfo("isCreatePayment"));
        sic.add(new SelectorItemInfo("isCreateVoucher"));
        sic.add(new SelectorItemInfo("creator.*"));
        sic.add(new SelectorItemInfo("createTime"));
        sic.add(new SelectorItemInfo("lastUpdateUser.*"));
        sic.add(new SelectorItemInfo("lastUpdateTime"));
        sic.add(new SelectorItemInfo("number"));
        sic.add(new SelectorItemInfo("bizDate"));
        sic.add(new SelectorItemInfo("description"));
        sic.add(new SelectorItemInfo("auditor.*"));
        sic.add(new SelectorItemInfo("FICompany.*"));
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
        sic.add(new SelectorItemInfo("InvoiceType.*"));
        sic.add(new SelectorItemInfo("Applyer.*"));
        sic.add(new SelectorItemInfo("TaxRate"));
        sic.add(new SelectorItemInfo("ApplyDept.*"));
        return sic;
    }        
    	

    /**
     * output actionSubmit_actionPerformed method
     */
    public void actionSubmit_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionSubmit_actionPerformed(e);
    }
    	

    /**
     * output actionPrint_actionPerformed method
     */
    public void actionPrint_actionPerformed(ActionEvent e) throws Exception
    {
        ArrayList idList = new ArrayList();
    	if (editData != null && !StringUtils.isEmpty(editData.getString("id"))) {
    		idList.add(editData.getString("id"));
    	}
        if (idList == null || idList.size() == 0 || getTDQueryPK() == null || getTDFileName() == null)
            return;
        com.kingdee.bos.ctrl.kdf.data.impl.BOSQueryDelegate data = new com.kingdee.eas.framework.util.CommonDataProvider(idList,getTDQueryPK());
        com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper appHlp = new com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper();
        appHlp.print(getTDFileName(), data, javax.swing.SwingUtilities.getWindowAncestor(this));
    }
    	

    /**
     * output actionPrintPreview_actionPerformed method
     */
    public void actionPrintPreview_actionPerformed(ActionEvent e) throws Exception
    {
        ArrayList idList = new ArrayList();
        if (editData != null && !StringUtils.isEmpty(editData.getString("id"))) {
    		idList.add(editData.getString("id"));
    	}
        if (idList == null || idList.size() == 0 || getTDQueryPK() == null || getTDFileName() == null)
            return;
        com.kingdee.bos.ctrl.kdf.data.impl.BOSQueryDelegate data = new com.kingdee.eas.framework.util.CommonDataProvider(idList,getTDQueryPK());
        com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper appHlp = new com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper();
        appHlp.printPreview(getTDFileName(), data, javax.swing.SwingUtilities.getWindowAncestor(this));
    }
	public RequestContext prepareActionSubmit(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionSubmit(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionSubmit() {
    	return false;
    }
	public RequestContext prepareActionPrint(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionPrint(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionPrint() {
    	return false;
    }
	public RequestContext prepareActionPrintPreview(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionPrintPreview(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionPrintPreview() {
    	return false;
    }

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.custom.financialinvoice.client", "InvoiceEditUI");
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
				if (com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum("Company")) != null && com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum("Company")).getBoolean("isBizUnit"))
			objectValue.put("FICompany",com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum("Company")));
 
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));		
        return objectValue;
    }


    	protected String getTDFileName() {
    	return "/bim/custom/financialinvoice/Invoice";
	}
    protected IMetaDataPK getTDQueryPK() {
    	return new MetaDataPK("com.kingdee.eas.custom.financialinvoice.app.InvoiceQuery");
	}
    

    /**
     * output getDetailTable method
     */
    protected KDTable getDetailTable() {        
        return null;
	}
    /**
     * output applyDefaultValue method
     */
    protected void applyDefaultValue(IObjectValue vo) {        
		vo.put("status","1");
        
    }        
	protected void setFieldsNull(com.kingdee.bos.dao.AbstractObjectValue arg0) {
		super.setFieldsNull(arg0);
		arg0.put("number",null);
	}

}