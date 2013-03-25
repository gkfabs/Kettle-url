 /* Copyright (c) 2007 Pentaho Corporation.  All rights reserved. 
 * This software was developed by Pentaho Corporation and is provided under the terms 
 * of the GNU Lesser General Public License, Version 2.1. You may not use 
 * this file except in compliance with the license. If you need a copy of the license, 
 * please go to http://www.gnu.org/licenses/lgpl-2.1.txt. The Original Code is Pentaho 
 * Data Integration.  The Initial Developer is Samatar HASSAN.
 *
 * Software distributed under the GNU Lesser Public License is distributed on an "AS IS" 
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or  implied. Please refer to 
 * the license for the specific language governing your rights and limitations.*/


package be.ibridge.kettle.url;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.pentaho.di.core.Const;
import org.pentaho.di.core.Props;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.core.row.ValueMeta;
import org.pentaho.di.i18n.BaseMessages;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStepMeta;
import org.pentaho.di.trans.step.StepDialogInterface;
import org.pentaho.di.ui.core.dialog.ErrorDialog;
import org.pentaho.di.ui.core.widget.ColumnInfo;
import org.pentaho.di.ui.core.widget.TableView;
import org.pentaho.di.ui.core.widget.TextVar;
import org.pentaho.di.ui.trans.step.BaseStepDialog;

public class UrlInputDialog extends BaseStepDialog implements StepDialogInterface
{
	private static Class<?> PKG = UrlInputMeta.class; // for i18n purposes, needed by Translator2!!   $NON-NLS-1$

	private CTabFolder   wTabFolder;
	private FormData     fdTabFolder;
	
	private CTabItem     wFileTab, wFieldsTab;

	private Composite    wFileComp, wFieldsComp;
	private FormData     fdFileComp, fdFieldsComp;

	private FormData fdlFieldValue;
	private FormData    fdFieldValue;
	private Label wlSourceField;
	private CCombo wFieldValue;
 
	private TableView    wFields;
	private FormData     fdFields;

    private Label	    wlAuthorityFieldName;
    private FormData	fdlAuthorityFieldName;
    private TextVar		wAuthorityFieldName;
    private FormData    fdAuthorityFieldName;
    private Label	    wlDefaultPortFieldName;
    private FormData	fdlDefaultPortFieldName;
    private TextVar		wDefaultPortFieldName;
    private FormData    fdDefaultPortFieldName;
    private Label	    wlFileFieldName;
    private FormData	fdlFileFieldName;
    private TextVar		wFileFieldName;
    private FormData    fdFileFieldName;
    private Label	    wlHostFieldName;
    private FormData	fdlHostFieldName;
    private TextVar		wHostFieldName;
    private FormData    fdHostFieldName;
    private Label	    wlPathFieldName;
    private FormData	fdlPathFieldName;
    private TextVar		wPathFieldName;
    private FormData    fdPathFieldName;
    private Label	    wlPortFieldName;
    private FormData	fdlPortFieldName;
    private TextVar		wPortFieldName;
    private FormData    fdPortFieldName;
    private Label	    wlProtocolFieldName;
    private FormData	fdlProtocolFieldName;
    private TextVar		wProtocolFieldName;
    private FormData    fdProtocolFieldName;
    private Label	    wlQueryFieldName;
    private FormData	fdlQueryFieldName;
    private TextVar		wQueryFieldName;
    private FormData    fdQueryFieldName;
    private Label	    wlRefFieldName;
    private FormData	fdlRefFieldName;
    private TextVar		wRefFieldName;
    private FormData    fdRefFieldName;
    private Label	    wlUserInfoFieldName;
    private FormData	fdlUserInfoFieldName;
    private TextVar		wUserInfoFieldName;
    private FormData    fdUserInfoFieldName;
    private Label	    wlUriNameFieldName;
    private FormData	fdlUriNameFieldName;
    private TextVar		wUriNameFieldName;
    private FormData    fdUriNameFieldName;

	private UrlInputMeta input;
	
	private int middle;
	private int margin;
	private ModifyListener lsMod;
	
	public UrlInputDialog(Shell parent, Object in, TransMeta transMeta, String sname)
	{
		super(parent, (BaseStepMeta)in, transMeta, sname);
		input=(UrlInputMeta)in;
	}

	public String open()
	{
		Shell parent = getParent();
		Display display = parent.getDisplay();

		shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.RESIZE | SWT.MAX | SWT.MIN);
 		props.setLook(shell);
        setShellImage(shell, input);

		lsMod = new ModifyListener() 
		{
			public void modifyText(ModifyEvent e) 
			{
				input.setChanged();
			}
		};
		changed         = input.hasChanged();
		
		FormLayout formLayout = new FormLayout ();
		formLayout.marginWidth  = Const.FORM_MARGIN;
		formLayout.marginHeight = Const.FORM_MARGIN;

		shell.setLayout(formLayout);
		shell.setText(BaseMessages.getString(PKG, "UrlInputDialog.DialogTitle"));
		
		
		middle = props.getMiddlePct();
		margin = Const.MARGIN;

		// Stepname line
		wlStepname=new Label(shell, SWT.RIGHT);
		wlStepname.setText(BaseMessages.getString(PKG, "System.Label.StepName"));
 		props.setLook(wlStepname);
		fdlStepname=new FormData();
		fdlStepname.left = new FormAttachment(0, 0);
		fdlStepname.top  = new FormAttachment(0, margin);
		fdlStepname.right= new FormAttachment(middle, -margin);
		wlStepname.setLayoutData(fdlStepname);
		wStepname=new Text(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		wStepname.setText(stepname);
 		props.setLook(wStepname);
		wStepname.addModifyListener(lsMod);
		fdStepname=new FormData();
		fdStepname.left = new FormAttachment(middle, 0);
		fdStepname.top  = new FormAttachment(0, margin);
		fdStepname.right= new FormAttachment(100, 0);
		wStepname.setLayoutData(fdStepname);

		wTabFolder = new CTabFolder(shell, SWT.BORDER);
 		props.setLook(wTabFolder, Props.WIDGET_STYLE_TAB);

		//////////////////////////
		// START OF FILE TAB   ///
		//////////////////////////
		wFileTab=new CTabItem(wTabFolder, SWT.NONE);
		wFileTab.setText(BaseMessages.getString(PKG, "UrlInputDialog.File.Tab"));
		
		wFileComp = new Composite(wTabFolder, SWT.NONE);
 		props.setLook(wFileComp);

		FormLayout fileLayout = new FormLayout();
		fileLayout.marginWidth  = 3;
		fileLayout.marginHeight = 3;
		wFileComp.setLayout(fileLayout);
		
		

		// ///////////////////////////////
		// START OF Output Field GROUP  //
		///////////////////////////////// 

		wlSourceField=new Label(wFileComp, SWT.RIGHT);
        wlSourceField.setText(BaseMessages.getString(PKG, "UrlInputDialog.wlSourceField.Label"));
        props.setLook(wlSourceField);
        fdlFieldValue=new FormData();
        fdlFieldValue.left = new FormAttachment(0, 0);
        fdlFieldValue.top  = new FormAttachment(0, margin);
        fdlFieldValue.right= new FormAttachment(middle, -margin);
        wlSourceField.setLayoutData(fdlFieldValue);
        
        
        wFieldValue=new CCombo(wFileComp, SWT.BORDER | SWT.READ_ONLY);
        wFieldValue.setEditable(true);
        props.setLook(wFieldValue);
        wFieldValue.addModifyListener(lsMod);
        fdFieldValue=new FormData();
        fdFieldValue.left = new FormAttachment(middle, 0);
        fdFieldValue.right= new FormAttachment(100, -margin);
        fdFieldValue.top  = new FormAttachment(0, margin);
        wFieldValue.setLayoutData(fdFieldValue);
        wFieldValue.addFocusListener(new FocusListener()
            {
                public void focusLost(org.eclipse.swt.events.FocusEvent e)
                {
                }
            
                public void focusGained(org.eclipse.swt.events.FocusEvent e)
                {
                    Cursor busy = new Cursor(shell.getDisplay(), SWT.CURSOR_WAIT);
                    shell.setCursor(busy);
                    setSourceStreamField();
                    shell.setCursor(null);
                    busy.dispose();
                }
            }
        );           	
        
			// AuthorityFieldName line
			wlAuthorityFieldName = new Label(wFileComp, SWT.RIGHT);
			wlAuthorityFieldName.setText(BaseMessages.getString(PKG, "UrlInputDialog.AuthorityFieldName.Label"));
			props.setLook(wlAuthorityFieldName);
			fdlAuthorityFieldName = new FormData();
			fdlAuthorityFieldName.left = new FormAttachment(0, 0);
			fdlAuthorityFieldName.top = new FormAttachment(wFieldValue, margin);
			fdlAuthorityFieldName.right = new FormAttachment(middle, -margin);
			wlAuthorityFieldName.setLayoutData(fdlAuthorityFieldName);

			wAuthorityFieldName = new TextVar(transMeta, wFileComp, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
			props.setLook(wAuthorityFieldName);
			wAuthorityFieldName.addModifyListener(lsMod);
			fdAuthorityFieldName = new FormData();
			fdAuthorityFieldName.left = new FormAttachment(middle, 0);
			fdAuthorityFieldName.right = new FormAttachment(100, -margin);
			fdAuthorityFieldName.top = new FormAttachment(wFieldValue, margin);
			wAuthorityFieldName.setLayoutData(fdAuthorityFieldName);
			
			
			// DefaultPortFieldName line
			wlDefaultPortFieldName = new Label(wFileComp, SWT.RIGHT);
			wlDefaultPortFieldName.setText(BaseMessages.getString(PKG, "UrlInputDialog.DefaultPortFieldName.Label"));
			props.setLook(wlDefaultPortFieldName);
			fdlDefaultPortFieldName = new FormData();
			fdlDefaultPortFieldName.left = new FormAttachment(0, 0);
			fdlDefaultPortFieldName.top = new FormAttachment(wAuthorityFieldName, margin);
			fdlDefaultPortFieldName.right = new FormAttachment(middle, -margin);
			wlDefaultPortFieldName.setLayoutData(fdlDefaultPortFieldName);

			wDefaultPortFieldName = new TextVar(transMeta, wFileComp, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
			props.setLook(wDefaultPortFieldName);
			wDefaultPortFieldName.addModifyListener(lsMod);
			fdDefaultPortFieldName = new FormData();
			fdDefaultPortFieldName.left = new FormAttachment(middle, 0);
			fdDefaultPortFieldName.right = new FormAttachment(100, -margin);
			fdDefaultPortFieldName.top = new FormAttachment(wAuthorityFieldName, margin);
			wDefaultPortFieldName.setLayoutData(fdDefaultPortFieldName);
			
			
			// FileFieldName line
			wlFileFieldName = new Label(wFileComp, SWT.RIGHT);
			wlFileFieldName.setText(BaseMessages.getString(PKG, "UrlInputDialog.FileFieldName.Label"));
			props.setLook(wlFileFieldName);
			fdlFileFieldName = new FormData();
			fdlFileFieldName.left = new FormAttachment(0, 0);
			fdlFileFieldName.top = new FormAttachment(wDefaultPortFieldName, margin);
			fdlFileFieldName.right = new FormAttachment(middle, -margin);
			wlFileFieldName.setLayoutData(fdlFileFieldName);

			wFileFieldName = new TextVar(transMeta, wFileComp, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
			props.setLook(wFileFieldName);
			wFileFieldName.addModifyListener(lsMod);
			fdFileFieldName = new FormData();
			fdFileFieldName.left = new FormAttachment(middle, 0);
			fdFileFieldName.right = new FormAttachment(100, -margin);
			fdFileFieldName.top = new FormAttachment(wDefaultPortFieldName, margin);
			wFileFieldName.setLayoutData(fdFileFieldName);
			


	  		// HostFieldName line
			wlHostFieldName = new Label(wFileComp, SWT.RIGHT);
			wlHostFieldName.setText(BaseMessages.getString(PKG, "UrlInputDialog.HostFieldName.Label"));
			props.setLook(wlHostFieldName);
			fdlHostFieldName = new FormData();
			fdlHostFieldName.left = new FormAttachment(0, 0);
			fdlHostFieldName.top = new FormAttachment(wFileFieldName, margin);
			fdlHostFieldName.right = new FormAttachment(middle, -margin);
			wlHostFieldName.setLayoutData(fdlHostFieldName);

			wHostFieldName = new TextVar(transMeta, wFileComp, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
			props.setLook(wHostFieldName);
			wHostFieldName.addModifyListener(lsMod);
			fdHostFieldName = new FormData();
			fdHostFieldName.left = new FormAttachment(middle, 0);
			fdHostFieldName.right = new FormAttachment(100, -margin);
			fdHostFieldName.top = new FormAttachment(wFileFieldName, margin);
			wHostFieldName.setLayoutData(fdHostFieldName);
			
			
			// PathFieldName line
			wlPathFieldName = new Label(wFileComp, SWT.RIGHT);
			wlPathFieldName.setText(BaseMessages.getString(PKG, "UrlInputDialog.PathFieldName.Label"));
			props.setLook(wlPathFieldName);
			fdlPathFieldName = new FormData();
			fdlPathFieldName.left = new FormAttachment(0, 0);
			fdlPathFieldName.top = new FormAttachment(wHostFieldName, margin);
			fdlPathFieldName.right = new FormAttachment(middle, -margin);
			wlPathFieldName.setLayoutData(fdlPathFieldName);

			wPathFieldName = new TextVar(transMeta, wFileComp, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
			props.setLook(wPathFieldName);
			wPathFieldName.addModifyListener(lsMod);
			fdPathFieldName = new FormData();
			fdPathFieldName.left = new FormAttachment(middle, 0);
			fdPathFieldName.right = new FormAttachment(100, -margin);
			fdPathFieldName.top = new FormAttachment(wHostFieldName, margin);
			wPathFieldName.setLayoutData(fdPathFieldName);
			
			// PortFieldName line
			wlPortFieldName = new Label(wFileComp, SWT.RIGHT);
			wlPortFieldName.setText(BaseMessages.getString(PKG, "UrlInputDialog.PortFieldName.Label"));
			props.setLook(wlPortFieldName);
			fdlPortFieldName = new FormData();
			fdlPortFieldName.left = new FormAttachment(0, 0);
			fdlPortFieldName.top = new FormAttachment(wPathFieldName, margin);
			fdlPortFieldName.right = new FormAttachment(middle, -margin);
			wlPortFieldName.setLayoutData(fdlPortFieldName);

			wPortFieldName = new TextVar(transMeta, wFileComp, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
			props.setLook(wPortFieldName);
			wPortFieldName.addModifyListener(lsMod);
			fdPortFieldName = new FormData();
			fdPortFieldName.left = new FormAttachment(middle, 0);
			fdPortFieldName.right = new FormAttachment(100, -margin);
			fdPortFieldName.top = new FormAttachment(wPathFieldName, margin);
			wPortFieldName.setLayoutData(fdPortFieldName);
			
			// ProtocolFieldName line
			wlProtocolFieldName = new Label(wFileComp, SWT.RIGHT);
			wlProtocolFieldName.setText(BaseMessages.getString(PKG, "UrlInputDialog.ProtocolFieldName.Label"));
			props.setLook(wlProtocolFieldName);
			fdlProtocolFieldName = new FormData();
			fdlProtocolFieldName.left = new FormAttachment(0, 0);
			fdlProtocolFieldName.top = new FormAttachment(wPortFieldName, margin);
			fdlProtocolFieldName.right = new FormAttachment(middle, -margin);
			wlProtocolFieldName.setLayoutData(fdlProtocolFieldName);

			wProtocolFieldName = new TextVar(transMeta, wFileComp, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
			props.setLook(wProtocolFieldName);
			wProtocolFieldName.addModifyListener(lsMod);
			fdProtocolFieldName = new FormData();
			fdProtocolFieldName.left = new FormAttachment(middle, 0);
			fdProtocolFieldName.right = new FormAttachment(100, -margin);
			fdProtocolFieldName.top = new FormAttachment(wPortFieldName, margin);
			wProtocolFieldName.setLayoutData(fdProtocolFieldName);
			
			// QueryFieldName line
			wlQueryFieldName = new Label(wFileComp, SWT.RIGHT);
			wlQueryFieldName.setText(BaseMessages.getString(PKG, "UrlInputDialog.QueryFieldName.Label"));
			props.setLook(wlQueryFieldName);
			fdlQueryFieldName = new FormData();
			fdlQueryFieldName.left = new FormAttachment(0, 0);
			fdlQueryFieldName.top = new FormAttachment(wProtocolFieldName, margin);
			fdlQueryFieldName.right = new FormAttachment(middle, -margin);
			wlQueryFieldName.setLayoutData(fdlQueryFieldName);

			wQueryFieldName = new TextVar(transMeta, wFileComp, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
			props.setLook(wQueryFieldName);
			wQueryFieldName.addModifyListener(lsMod);
			fdQueryFieldName = new FormData();
			fdQueryFieldName.left = new FormAttachment(middle, 0);
			fdQueryFieldName.right = new FormAttachment(100, -margin);
			fdQueryFieldName.top = new FormAttachment(wProtocolFieldName, margin);
			wQueryFieldName.setLayoutData(fdQueryFieldName);
			
			// RefFieldName line
			wlRefFieldName = new Label(wFileComp, SWT.RIGHT);
			wlRefFieldName.setText(BaseMessages.getString(PKG, "UrlInputDialog.RefFieldName.Label"));
			props.setLook(wlRefFieldName);
			fdlRefFieldName = new FormData();
			fdlRefFieldName.left = new FormAttachment(0, 0);
			fdlRefFieldName.top = new FormAttachment(wQueryFieldName, margin);
			fdlRefFieldName.right = new FormAttachment(middle, -margin);
			wlRefFieldName.setLayoutData(fdlRefFieldName);

			wRefFieldName = new TextVar(transMeta, wFileComp, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
			props.setLook(wRefFieldName);
			wRefFieldName.addModifyListener(lsMod);
			fdRefFieldName = new FormData();
			fdRefFieldName.left = new FormAttachment(middle, 0);
			fdRefFieldName.right = new FormAttachment(100, -margin);
			fdRefFieldName.top = new FormAttachment(wQueryFieldName, margin);
			wRefFieldName.setLayoutData(fdRefFieldName);
			
			// UserInfoFieldName line
			wlUserInfoFieldName = new Label(wFileComp, SWT.RIGHT);
			wlUserInfoFieldName.setText(BaseMessages.getString(PKG, "UrlInputDialog.UserInfoFieldName.Label"));
			props.setLook(wlUserInfoFieldName);
			fdlUserInfoFieldName = new FormData();
			fdlUserInfoFieldName.left = new FormAttachment(0, 0);
			fdlUserInfoFieldName.top = new FormAttachment(wRefFieldName, margin);
			fdlUserInfoFieldName.right = new FormAttachment(middle, -margin);
			wlUserInfoFieldName.setLayoutData(fdlUserInfoFieldName);

			wUserInfoFieldName = new TextVar(transMeta, wFileComp, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
			props.setLook(wUserInfoFieldName);
			wUserInfoFieldName.addModifyListener(lsMod);
			fdUserInfoFieldName = new FormData();
			fdUserInfoFieldName.left = new FormAttachment(middle, 0);
			fdUserInfoFieldName.right = new FormAttachment(100, -margin);
			fdUserInfoFieldName.top = new FormAttachment(wRefFieldName, margin);
			wUserInfoFieldName.setLayoutData(fdUserInfoFieldName);
			
			// UriNameFieldName line
			wlUriNameFieldName = new Label(wFileComp, SWT.RIGHT);
			wlUriNameFieldName.setText(BaseMessages.getString(PKG, "UrlInputDialog.UriNameFieldName.Label"));
			props.setLook(wlUriNameFieldName);
			fdlUriNameFieldName = new FormData();
			fdlUriNameFieldName.left = new FormAttachment(0, 0);
			fdlUriNameFieldName.top = new FormAttachment(wUserInfoFieldName, margin);
			fdlUriNameFieldName.right = new FormAttachment(middle, -margin);
			wlUriNameFieldName.setLayoutData(fdlUriNameFieldName);

			wUriNameFieldName = new TextVar(transMeta, wFileComp, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
			props.setLook(wUriNameFieldName);
			wUriNameFieldName.addModifyListener(lsMod);
			fdUriNameFieldName = new FormData();
			fdUriNameFieldName.left = new FormAttachment(middle, 0);
			fdUriNameFieldName.right = new FormAttachment(100, -margin);
			fdUriNameFieldName.top = new FormAttachment(wUserInfoFieldName, margin);
			wUriNameFieldName.setLayoutData(fdUriNameFieldName);

		
		fdFileComp=new FormData();
		fdFileComp.left  = new FormAttachment(0, 0);
		fdFileComp.top   = new FormAttachment(0, 0);
		fdFileComp.right = new FormAttachment(100, 0);
		fdFileComp.bottom= new FormAttachment(100, 0);
		wFileComp.setLayoutData(fdFileComp);
	
		wFileComp.layout();
		wFileTab.setControl(wFileComp);

		// Fields tab...
		//
		wFieldsTab = new CTabItem(wTabFolder, SWT.NONE);
		wFieldsTab.setText(BaseMessages.getString(PKG, "UrlInputDialog.Fields.Tab"));
		
		FormLayout fieldsLayout = new FormLayout ();
		fieldsLayout.marginWidth  = Const.FORM_MARGIN;
		fieldsLayout.marginHeight = Const.FORM_MARGIN;
		
		wFieldsComp = new Composite(wTabFolder, SWT.NONE);
		wFieldsComp.setLayout(fieldsLayout);
 		props.setLook(wFieldsComp);

		final int FieldsRows=input.getInputFields().length;
		
		ColumnInfo[] colinf=new ColumnInfo[]
            {
			 new ColumnInfo(
         BaseMessages.getString(PKG, "UrlInputDialog.FieldsTable.Name.Column"),
         ColumnInfo.COLUMN_TYPE_TEXT,
         false),
         new ColumnInfo(
                 BaseMessages.getString(PKG, "UrlInputDialog.FieldsTable.Value.Column"),
                 ColumnInfo.COLUMN_TYPE_TEXT,
                 false),
			 new ColumnInfo(
         BaseMessages.getString(PKG, "UrlInputDialog.FieldsTable.Type.Column"),
         ColumnInfo.COLUMN_TYPE_CCOMBO,
         ValueMeta.getTypes(),
         true ),
			 new ColumnInfo(
         BaseMessages.getString(PKG, "UrlInputDialog.FieldsTable.Format.Column"),
         ColumnInfo.COLUMN_TYPE_CCOMBO,
         Const.getConversionFormats()),
			 new ColumnInfo(
         BaseMessages.getString(PKG, "UrlInputDialog.FieldsTable.Length.Column"),
         ColumnInfo.COLUMN_TYPE_TEXT,
         false),
			 new ColumnInfo(
         BaseMessages.getString(PKG, "UrlInputDialog.FieldsTable.Precision.Column"),
         ColumnInfo.COLUMN_TYPE_TEXT,
         false),
			 new ColumnInfo(
         BaseMessages.getString(PKG, "UrlInputDialog.FieldsTable.Currency.Column"),
         ColumnInfo.COLUMN_TYPE_TEXT,
         false),
			 new ColumnInfo(
         BaseMessages.getString(PKG, "UrlInputDialog.FieldsTable.Decimal.Column"),
         ColumnInfo.COLUMN_TYPE_TEXT,
         false),
			 new ColumnInfo(
         BaseMessages.getString(PKG, "UrlInputDialog.FieldsTable.Group.Column"),
         ColumnInfo.COLUMN_TYPE_TEXT,
         false),
			 new ColumnInfo(
         BaseMessages.getString(PKG, "UrlInputDialog.FieldsTable.TrimType.Column"),
         ColumnInfo.COLUMN_TYPE_CCOMBO,
         UrlInputField.trimTypeDesc,
         true ),
			 new ColumnInfo(
         BaseMessages.getString(PKG, "UrlInputDialog.FieldsTable.Repeat.Column"),
         ColumnInfo.COLUMN_TYPE_CCOMBO,
         new String[] { BaseMessages.getString(PKG, "System.Combo.Yes"), BaseMessages.getString(PKG, "System.Combo.No") },
         true ),
     
    };
		
		colinf[0].setUsingVariables(true);
		colinf[0].setToolTip(BaseMessages.getString(PKG, "UrlInputDialog.FieldsTable.Name.Column.Tooltip"));
		colinf[1].setUsingVariables(true);
		colinf[1].setToolTip(BaseMessages.getString(PKG, "UrlInputDialog.FieldsTable.Path.Column.Tooltip"));
		
		wFields=new TableView(transMeta,wFieldsComp, 
						      SWT.FULL_SELECTION | SWT.MULTI, 
						      colinf, 
						      FieldsRows,  
						      lsMod,
							  props
						      );

		fdFields=new FormData();
		fdFields.left  = new FormAttachment(0, 0);
		fdFields.top   = new FormAttachment(0, 0);
		fdFields.right = new FormAttachment(100, 0);
		fdFields.bottom= new FormAttachment(100, -margin);
		wFields.setLayoutData(fdFields);

		fdFieldsComp=new FormData();
		fdFieldsComp.left  = new FormAttachment(0, 0);
		fdFieldsComp.top   = new FormAttachment(0, 0);
		fdFieldsComp.right = new FormAttachment(100, 0);
		fdFieldsComp.bottom= new FormAttachment(100, 0);
		wFieldsComp.setLayoutData(fdFieldsComp);
		
		wFieldsComp.layout();
		wFieldsTab.setControl(wFieldsComp);
		
		fdTabFolder = new FormData();
		fdTabFolder.left  = new FormAttachment(0, 0);
		fdTabFolder.top   = new FormAttachment(wStepname, margin);
		fdTabFolder.right = new FormAttachment(100, 0);
		fdTabFolder.bottom= new FormAttachment(100, -50);
		wTabFolder.setLayoutData(fdTabFolder);
		
		
		wOK=new Button(shell, SWT.PUSH);
		wOK.setText(BaseMessages.getString(PKG, "System.Button.OK"));

		wCancel=new Button(shell, SWT.PUSH);
		wCancel.setText(BaseMessages.getString(PKG, "System.Button.Cancel"));
		
		setButtonPositions(new Button[] { wOK, wCancel }, margin, wTabFolder);

		// Add listeners
		lsOK       = new Listener() { public void handleEvent(Event e) { ok();     } };
		lsCancel   = new Listener() { public void handleEvent(Event e) { cancel();     } };
		
		wOK.addListener     (SWT.Selection, lsOK     );
		wCancel.addListener (SWT.Selection, lsCancel );
		
		lsDef=new SelectionAdapter() { public void widgetDefaultSelected(SelectionEvent e) { ok(); } };
		
		wStepname.addSelectionListener( lsDef );

		// Detect X or ALT-F4 or something that kills this window...
		shell.addShellListener(	new ShellAdapter() { public void shellClosed(ShellEvent e) { cancel(); } } );

		wTabFolder.setSelection(0);

		// Set the shell size, based upon previous time...
		setSize();
		getData(input);
		input.setChanged(changed);
		wFields.optWidth(true);
		
		shell.open();
		while (!shell.isDisposed())
		{
				if (!display.readAndDispatch()) display.sleep();
		}
		return stepname;
	}

	 private void setSourceStreamField()
	 {
		 try{
	         String value = wFieldValue.getText();  
			 wFieldValue.removeAll();
				
			 RowMetaInterface r = transMeta.getPrevStepFields(stepname);
             if(r!=null) {
            	 wFieldValue.setItems(r.getFieldNames());
             }
             if(value!=null) wFieldValue.setText(value);
		 }catch(KettleException ke){
				new ErrorDialog(shell, BaseMessages.getString(PKG, "UrlInputDialog.FailedToGetFields.DialogTitle"), BaseMessages.getString(PKG, "UrlInputDialog.FailedToGetFields.DialogMessage"), ke); //$NON-NLS-1$ //$NON-NLS-2$
			}
	 }
	 
	/**
	 * Read the data from the TextFileInputMeta object and show it in this dialog.
	 * 
	 * @param in The TextFileInputMeta object to obtain the data from.
	 */
	public void getData(UrlInputMeta in)
	{
		if (in.getFieldValue()!=null) wFieldValue.setText(in.getFieldValue());
		
		if(isDebug()) logDebug( BaseMessages.getString(PKG, "UrlInputDialog.Log.GettingFieldsInfo"));
		for (int i=0;i<in.getInputFields().length;i++)
		{
		    UrlInputField field = in.getInputFields()[i];
		    
            if (field!=null)
            {
    			TableItem item = wFields.table.getItem(i);
    			String name     = field.getName();
    			String value	= field.getValue();
    			String type     = field.getTypeDesc();
    			String format   = field.getFormat();
    			String length   = ""+field.getLength();
    			String prec     = ""+field.getPrecision();
    			String curr     = field.getCurrencySymbol();
    			String group    = field.getGroupSymbol();
    			String decim    = field.getDecimalSymbol();
    			String trim     = field.getTrimTypeDesc();
    			String rep      = field.isRepeated()?BaseMessages.getString(PKG, "System.Combo.Yes"):BaseMessages.getString(PKG, "System.Combo.No");
    			
                if (name    !=null) item.setText( 1, name);
                if (value   !=null) item.setText( 2, value);
    			if (type    !=null) item.setText( 3, type    );
    			if (format  !=null) item.setText( 4, format  );
    			if (length  !=null && !"-1".equals(length  )) item.setText( 5, length  );
    			if (prec    !=null && !"-1".equals(prec    )) item.setText( 6, prec    );
    			if (curr    !=null) item.setText( 7, curr    );
    			if (decim   !=null) item.setText( 8, decim   );
    			if (group   !=null) item.setText( 9, group   );
    			if (trim    !=null) item.setText( 10, trim    );
    			if (rep     !=null) item.setText(11, rep     );
                
            }
		}     
        
        wFields.removeEmptyRows();
        wFields.setRowNums();
        wFields.optWidth(true);

        if(in.getAuthorityField()!=null) wAuthorityFieldName.setText(in.getAuthorityField());
        if(in.getDefaultPortField()!=null) wDefaultPortFieldName.setText(in.getDefaultPortField());
        if(in.getFileField()!=null) wFileFieldName.setText(in.getFileField());
        if(in.getHostField()!=null) wHostFieldName.setText(in.getHostField());
        if(in.getPathField()!=null) wPathFieldName.setText(in.getPathField());
        if(in.getPortField()!=null) wPortFieldName.setText(in.getPortField());
        if(in.getProtocolField()!=null) wProtocolFieldName.setText(in.getProtocolField());
        if(in.getQueryField()!=null) wQueryFieldName.setText(in.getQueryField());
        if(in.getRefField()!=null) wRefFieldName.setText(in.getRefField());
        if(in.getUserInfoField()!=null) wUserInfoFieldName.setText(in.getUserInfoField());
        if(in.getUriNameField()!=null) wUriNameFieldName.setText(in.getUriNameField());
        
		wStepname.selectAll();
	}
	
	private void cancel()
	{
		stepname=null;
		input.setChanged(changed);
		dispose();
	}
	
	private void ok()
	{
        try
        {
            getInfo(input);
        }
        catch(KettleException e)
        {
            new ErrorDialog(shell, BaseMessages.getString(PKG, "UrlInputDialog.ErrorParsingData.DialogTitle"), BaseMessages.getString(PKG, "UrlInputDialog.ErrorParsingData.DialogMessage"), e);
        }
		dispose();
	}
	
	private void getInfo(UrlInputMeta in) throws KettleException
	{
		stepname = wStepname.getText(); // return value

		in.setFieldValue(wFieldValue.getText());
		
		int nrFields    = wFields.nrNonEmpty();
        
		in.allocate(nrFields);

		for (int i=0;i<nrFields;i++)
		{
		    UrlInputField field = new UrlInputField();
		    
			TableItem item  = wFields.getNonEmpty(i);
            
			field.setName( item.getText(1) );
			field.setValue( item.getText(2) );
			field.setType( ValueMeta.getType(item.getText(3)) );
			field.setFormat( item.getText(4) );
			field.setLength( Const.toInt(item.getText(5), -1) );
			field.setPrecision( Const.toInt(item.getText(6), -1) );
			field.setCurrencySymbol( item.getText(7) );
			field.setDecimalSymbol( item.getText(8) );
			field.setGroupSymbol( item.getText(9) );
			field.setTrimType( UrlInputField.getTrimTypeByDesc(item.getText(10)) );
			field.setRepeated( BaseMessages.getString(PKG, "System.Combo.Yes").equalsIgnoreCase(item.getText(11)) );		
            
			in.getInputFields()[i] = field;
		}
        in.setAuthorityField(wAuthorityFieldName.getText());
        in.setDefaultPortField(wDefaultPortFieldName.getText());
        in.setFileField(wFileFieldName.getText());
        in.setHostField(wHostFieldName.getText());
        in.setPathField(wPathFieldName.getText());
        in.setPortField(wPortFieldName.getText());
        in.setProtocolField(wProtocolFieldName.getText());
        in.setQueryField(wQueryFieldName.getText());
        in.setRefField(wRefFieldName.getText());
        in.setUserInfoField(wUserInfoFieldName.getText());
        in.setUriNameField(wUriNameFieldName.getText());
	}
	
}