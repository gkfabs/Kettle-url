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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.vfs.FileObject;
import org.pentaho.di.core.CheckResult;
import org.pentaho.di.core.CheckResultInterface;
import org.pentaho.di.core.Const;
import org.pentaho.di.core.Counter;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleStepException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.fileinput.FileInputList;
import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.core.row.ValueMeta;
import org.pentaho.di.core.row.ValueMetaInterface;
import org.pentaho.di.core.variables.VariableSpace;
import org.pentaho.di.core.xml.XMLHandler;
import org.pentaho.di.i18n.BaseMessages;
import org.pentaho.di.repository.ObjectId;
import org.pentaho.di.repository.Repository;
import org.pentaho.di.resource.ResourceDefinition;
import org.pentaho.di.resource.ResourceNamingInterface;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStepMeta;
import org.pentaho.di.trans.step.StepDataInterface;
import org.pentaho.di.trans.step.StepInterface;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.step.StepMetaInterface;
import org.w3c.dom.Node;


/**
 * Store run-time data on the UrlInput step.
 */
public class UrlInputMeta extends BaseStepMeta implements StepMetaInterface
{	
	private static Class<?> PKG = UrlInputMeta.class; // for i18n purposes, needed by Translator2!!   $NON-NLS-1$

	private static final String YES = "Y";
	
	public static final String[] RequiredFilesDesc = new String[] { BaseMessages.getString(PKG, "System.Combo.No"), BaseMessages.getString(PKG, "System.Combo.Yes") };
	public static final String[] RequiredFilesCode = new String[] {"N", "Y"};
	
	/** The name of the field in the output containing the url */
	private  String  urlField;
	
	/** The fields to import... */
	private UrlInputField inputFields[];
    
    /**  Is In fields     */
    private  String valueField;
    
    /** Additional fields  **/
    private String authorityFieldName;
    private String defaultPortFieldName;
    private String fileFieldName;
    private String hostFieldName;
    private String pathFieldName;
    private String portFieldName;
    private String protocolFieldName;
    private String queryFieldName;
    private String refFieldName;
    private String userInfoFieldName;
    private String uriNameFieldName;
    	
	public UrlInputMeta()
	{
		super(); // allocate BaseStepMeta
	}

	/**
	 * @return Returns the authorityFieldName.
	 */
    public String getAuthorityField()
    {
    	return authorityFieldName;
    }
    /**
	 * @param field The authorityFieldName to set.
	 */
    public void setAuthorityField(String field)
    {
    	this.authorityFieldName=field;
    }

	/**
	 * @return Returns the defaultPortFieldName.
	 */
    public String getDefaultPortField()
    {
    	return defaultPortFieldName;
    }
    /**
	 * @param field The defaultPortFieldName to set.
	 */
    public void setDefaultPortField(String field)
    {
    	this.defaultPortFieldName=field;
    }

	/**
	 * @return Returns the fileFieldName.
	 */
    public String getFileField()
    {
    	return fileFieldName;
    }
    /**
	 * @param field The fileFieldName to set.
	 */
    public void setFileField(String field)
    {
    	this.fileFieldName=field;
    }

	/**
	 * @return Returns the hostFieldName.
	 */
    public String getHostField()
    {
    	return hostFieldName;
    }
    /**
	 * @param field The hostFieldName to set.
	 */
    public void setHostField(String field)
    {
    	this.hostFieldName=field;
    }

	/**
	 * @return Returns the pathFieldName.
	 */
    public String getPathField()
    {
    	return pathFieldName;
    }
    /**
	 * @param field The pathFieldName to set.
	 */
    public void setPathField(String field)
    {
    	this.pathFieldName=field;
    }

	/**
	 * @return Returns the portFieldName.
	 */
    public String getPortField()
    {
    	return portFieldName;
    }
    /**
	 * @param field The portFieldName to set.
	 */
    public void setPortField(String field)
    {
    	this.portFieldName=field;
    }

	/**
	 * @return Returns the protocolFieldName.
	 */
    public String getProtocolField()
    {
    	return protocolFieldName;
    }
    /**
	 * @param field The protocolFieldName to set.
	 */
    public void setProtocolField(String field)
    {
    	this.protocolFieldName=field;
    }

	/**
	 * @return Returns the queryFieldName.
	 */
    public String getQueryField()
    {
    	return queryFieldName;
    }
    /**
	 * @param field The queryFieldName to set.
	 */
    public void setQueryField(String field)
    {
    	this.queryFieldName=field;
    }

	/**
	 * @return Returns the refFieldName.
	 */
    public String getRefField()
    {
    	return refFieldName;
    }
    /**
	 * @param field The refFieldName to set.
	 */
    public void setRefField(String field)
    {
    	this.refFieldName=field;
    }

	/**
	 * @return Returns the userInfoFieldName.
	 */
    public String getUserInfoField()
    {
    	return userInfoFieldName;
    }
    /**
	 * @param field The userInfoFieldName to set.
	 */
    public void setUserInfoField(String field)
    {
    	this.userInfoFieldName=field;
    }

	/**
	 * @return Returns the uriNameFieldName.
	 */
    public String getUriNameField()
    {
    	return uriNameFieldName;
    }
    /**
	 * @param field The uriNameFieldName to set.
	 */
    public void setUriNameField(String field)
    {
    	this.uriNameFieldName=field;
    }


	/**
     * @return Returns the input fields.
     */
    public UrlInputField[] getInputFields()
    {
        return inputFields;
    }
    
    /**
     * @param inputFields The input fields to set.
     */
    public void setInputFields(UrlInputField[] inputFields)
    {
        this.inputFields = inputFields;
    }
    
    /**
     * Get field value.
     */
    public String getFieldValue()
    {
        return valueField;
    }
    
    /**
     * Set field field.
     */ 
    public void setFieldValue(String value)
    {
        this.valueField = value;
    }
    
    /**
     * @return Returns the urlField.
     */
    public String getUrlField()
    {
        return urlField;
    }
    
    /**
     * @param urlField The urlField to set.
     */
    public void setUrlField(String urlField)
    {
        this.urlField = urlField;
    }
    
	
    public void loadXML(Node stepnode, List<DatabaseMeta> databases, Map<String, Counter> counters)
  	    throws KettleXMLException
	{
    	readData(stepnode);
	}

	public Object clone()
	{
		UrlInputMeta retval = (UrlInputMeta)super.clone();
		
		int nrFields = inputFields.length;

		retval.allocate(nrFields);
		
		for (int i=0;i<nrFields;i++)
		{
            if (inputFields[i]!=null)
            {
                retval.inputFields[i] = (UrlInputField)inputFields[i].clone();
            }
		}		
		return retval;
	}

    public String getXML()
    {
        StringBuffer retval=new StringBuffer(400);
        
        retval.append("    ").append(XMLHandler.addTagValue("url_field",   urlField));

        retval.append("    <fields>").append(Const.CR);
        for (int i=0;i<inputFields.length;i++)
        {
            UrlInputField field = inputFields[i];
            retval.append(field.getXML());
        }
        retval.append("    </fields>").append(Const.CR);
        
        retval.append("    ").append(XMLHandler.addTagValue("valueField", valueField));

        retval.append("    ").append(XMLHandler.addTagValue("valueField", valueField));
 
		retval.append("    ").append(XMLHandler.addTagValue("authorityFieldName", authorityFieldName));
		retval.append("    ").append(XMLHandler.addTagValue("defaultPortFieldName", defaultPortFieldName));
		retval.append("    ").append(XMLHandler.addTagValue("fileFieldName", fileFieldName));
		retval.append("    ").append(XMLHandler.addTagValue("hostFieldName", hostFieldName));
		retval.append("    ").append(XMLHandler.addTagValue("pathFieldName", pathFieldName));
		retval.append("    ").append(XMLHandler.addTagValue("portFieldName", portFieldName));
		retval.append("    ").append(XMLHandler.addTagValue("protocolFieldName", protocolFieldName));
		retval.append("    ").append(XMLHandler.addTagValue("queryFieldName", queryFieldName));
		retval.append("    ").append(XMLHandler.addTagValue("refFieldName", refFieldName));
		retval.append("    ").append(XMLHandler.addTagValue("userInfoFieldName", userInfoFieldName));
		retval.append("    ").append(XMLHandler.addTagValue("uriNameFieldName", uriNameFieldName));
        return retval.toString();
    }
     public String getRequiredFilesDesc(String tt)
     {
    	 if(Const.isEmpty(tt)) return RequiredFilesDesc[0]; 
 		if(tt.equalsIgnoreCase(RequiredFilesCode[1]))
			return RequiredFilesDesc[1];
		else
			return RequiredFilesDesc[0]; 
     }
     public String getRequiredFilesCode(String tt)
     {
    	if(tt==null) return RequiredFilesCode[0]; 
 		if(tt.equals(RequiredFilesDesc[1]))
			return RequiredFilesCode[1];
		else
			return RequiredFilesCode[0]; 
     }
	private void readData(Node stepnode) throws KettleXMLException
	{
		try
		{
			urlField     = XMLHandler.getTagValue(stepnode, "url_field");

			Node fields       = XMLHandler.getSubNode(stepnode,   "fields");
			int nrFields      = XMLHandler.countNodes(fields,    "field");
	
			allocate(nrFields);
			
			for (int i=0;i<nrFields;i++)
			{
				Node fnode = XMLHandler.getSubNodeByNr(fields, "field", i);
				UrlInputField field = new UrlInputField(fnode);
				inputFields[i] = field;
			} 

			valueField = XMLHandler.getTagValue(stepnode, "valueField");  

			authorityFieldName = XMLHandler.getTagValue(stepnode, "authorityFieldName");
			defaultPortFieldName = XMLHandler.getTagValue(stepnode, "defaultPortFieldName");
			fileFieldName = XMLHandler.getTagValue(stepnode, "fileFieldName");
			hostFieldName = XMLHandler.getTagValue(stepnode, "hostFieldName");
			pathFieldName = XMLHandler.getTagValue(stepnode, "pathFieldName");
			portFieldName = XMLHandler.getTagValue(stepnode, "portFieldName");
			protocolFieldName = XMLHandler.getTagValue(stepnode, "protocolFieldName");
			queryFieldName = XMLHandler.getTagValue(stepnode, "queryFieldName");
			refFieldName = XMLHandler.getTagValue(stepnode, "refFieldName");
			userInfoFieldName = XMLHandler.getTagValue(stepnode, "userInfoFieldName");
			uriNameFieldName = XMLHandler.getTagValue(stepnode, "uriNameFieldName");
		}
		catch(Exception e)
		{
			throw new KettleXMLException(BaseMessages.getString(PKG, "UrlInputMeta.Exception.ErrorLoadingXML", e.toString()));
		}
	}
	
	public void allocate(int nrfields)
	{
		inputFields  = new UrlInputField[nrfields];
	}
	
	public void setDefault()
	{
        urlField = "";

	    authorityFieldName=null;
	    defaultPortFieldName=null;
	    fileFieldName=null;
	    hostFieldName=null;
	    pathFieldName=null;
	    portFieldName=null;
	    protocolFieldName=null;
	    queryFieldName=null;
	    refFieldName=null;
	    userInfoFieldName=null;
	    uriNameFieldName=null;
	    
		int nrFields=0;


		allocate(nrFields);	
		
		for (int i=0;i<nrFields;i++)
		{
		    inputFields[i] = new UrlInputField("field"+(i+1));
		}

		valueField = "";

	}
	
	public void getFields(RowMetaInterface r, String name, RowMetaInterface info[], StepMeta nextStep, VariableSpace space) throws KettleStepException
	{		
		int i;
		for (i=0;i<inputFields.length;i++)
		{
			UrlInputField field = inputFields[i];       
	        
			int type=field.getType();
			if (type==ValueMeta.TYPE_NONE) type=ValueMeta.TYPE_STRING;
			ValueMetaInterface v=new ValueMeta(space.environmentSubstitute(field.getName()), type);
			v.setLength(field.getLength());
			v.setPrecision(field.getPrecision());
			v.setOrigin(name);
			v.setConversionMask(field.getFormat());
	        v.setDecimalSymbol(field.getDecimalSymbol());
	        v.setGroupingSymbol(field.getGroupSymbol());
	        v.setCurrencySymbol(field.getCurrencySymbol());
			r.addValueMeta(v);    
		}
		
		// Add additional fields

		if(getAuthorityField()!=null && getAuthorityField().length()>0)
		{
			ValueMetaInterface v = new ValueMeta(space.environmentSubstitute(getAuthorityField()), ValueMeta.TYPE_STRING);
			v.setLength(100, -1);
			v.setOrigin(name);
			r.addValueMeta(v);
		}
		if(getDefaultPortField()!=null && getDefaultPortField().length()>0)
		{
			ValueMetaInterface v = new ValueMeta(space.environmentSubstitute(getDefaultPortField()), ValueMeta.TYPE_STRING);
			v.setLength(100, -1);
			v.setOrigin(name);
			r.addValueMeta(v);
		}
		if(getFileField()!=null && getFileField().length()>0)
		{
			ValueMetaInterface v = new ValueMeta(space.environmentSubstitute(getFileField()), ValueMeta.TYPE_STRING);
			v.setLength(100, -1);
			v.setOrigin(name);
			r.addValueMeta(v);
		}
		if(getHostField()!=null && getHostField().length()>0)
		{
			ValueMetaInterface v = new ValueMeta(space.environmentSubstitute(getHostField()), ValueMeta.TYPE_STRING);
			v.setLength(100, -1);
			v.setOrigin(name);
			r.addValueMeta(v);
		}
		if(getPathField()!=null && getPathField().length()>0)
		{
			ValueMetaInterface v = new ValueMeta(space.environmentSubstitute(getPathField()), ValueMeta.TYPE_STRING);
			v.setLength(100, -1);
			v.setOrigin(name);
			r.addValueMeta(v);
		}
		if(getPortField()!=null && getPortField().length()>0)
		{
			ValueMetaInterface v = new ValueMeta(space.environmentSubstitute(getPortField()), ValueMeta.TYPE_STRING);
			v.setLength(100, -1);
			v.setOrigin(name);
			r.addValueMeta(v);
		}
		if(getProtocolField()!=null && getProtocolField().length()>0)
		{
			ValueMetaInterface v = new ValueMeta(space.environmentSubstitute(getProtocolField()), ValueMeta.TYPE_STRING);
			v.setLength(100, -1);
			v.setOrigin(name);
			r.addValueMeta(v);
		}
		if(getQueryField()!=null && getQueryField().length()>0)
		{
			ValueMetaInterface v = new ValueMeta(space.environmentSubstitute(getQueryField()), ValueMeta.TYPE_STRING);
			v.setLength(100, -1);
			v.setOrigin(name);
			r.addValueMeta(v);
		}
		if(getRefField()!=null && getRefField().length()>0)
		{
			ValueMetaInterface v = new ValueMeta(space.environmentSubstitute(getRefField()), ValueMeta.TYPE_STRING);
			v.setLength(100, -1);
			v.setOrigin(name);
			r.addValueMeta(v);
		}
		if(getUserInfoField()!=null && getUserInfoField().length()>0)
		{
			ValueMetaInterface v = new ValueMeta(space.environmentSubstitute(getUserInfoField()), ValueMeta.TYPE_STRING);
			v.setLength(100, -1);
			v.setOrigin(name);
			r.addValueMeta(v);
		}
		if(getUriNameField()!=null && getUriNameField().length()>0)
		{
			ValueMetaInterface v = new ValueMeta(space.environmentSubstitute(getUriNameField()), ValueMeta.TYPE_STRING);
			v.setLength(100, -1);
			v.setOrigin(name);
			r.addValueMeta(v);
		}
	}

	public void readRep(Repository rep, ObjectId id_step, List<DatabaseMeta> databases, Map<String, Counter> counters)
	throws KettleException
	{
	
		try
		{
			urlField     =   rep.getStepAttributeString (id_step, "url_field");

			int nrFields      =   rep.countNrStepAttributes(id_step, "field_name");
            
			allocate(nrFields);

			for (int i=0;i<nrFields;i++)
			{
			    UrlInputField field = new UrlInputField();
			    
				field.setName( rep.getStepAttributeString (id_step, i, "field_name") );
				field.setValue( rep.getStepAttributeString (id_step, i, "field_value") );
				field.setType( ValueMeta.getType( rep.getStepAttributeString (id_step, i, "field_type") ) );
				field.setFormat( rep.getStepAttributeString (id_step, i, "field_format") );
				field.setCurrencySymbol( rep.getStepAttributeString (id_step, i, "field_currency") );
				field.setDecimalSymbol( rep.getStepAttributeString (id_step, i, "field_decimal") );
				field.setGroupSymbol( rep.getStepAttributeString (id_step, i, "field_group") );
				field.setLength( (int)rep.getStepAttributeInteger(id_step, i, "field_length") );
				field.setPrecision( (int)rep.getStepAttributeInteger(id_step, i, "field_precision") );
				field.setTrimType( UrlInputField.getTrimTypeByCode( rep.getStepAttributeString (id_step, i, "field_trim_type") ));
				field.setRepeated( rep.getStepAttributeBoolean(id_step, i, "field_repeat") );
                
				inputFields[i] = field;
			}

			valueField   = rep.getStepAttributeString (id_step, "valueField");
			
			authorityFieldName = rep.getStepAttributeString(id_step, "authorityFieldName");
			defaultPortFieldName = rep.getStepAttributeString(id_step, "defaultPortFieldName");
			fileFieldName = rep.getStepAttributeString(id_step, "fileFieldName");
			hostFieldName = rep.getStepAttributeString(id_step, "hostFieldName");
			pathFieldName = rep.getStepAttributeString(id_step, "pathFieldName");
			portFieldName = rep.getStepAttributeString(id_step, "portFieldName");
			protocolFieldName = rep.getStepAttributeString(id_step, "protocolFieldName");
			queryFieldName = rep.getStepAttributeString(id_step, "queryFieldName");
			refFieldName = rep.getStepAttributeString(id_step, "refFieldName");
			userInfoFieldName = rep.getStepAttributeString(id_step, "userInfoFieldName");
			uriNameFieldName = rep.getStepAttributeString(id_step, "uriNameFieldName");
		}
		catch(Exception e)
		{
			throw new KettleException(BaseMessages.getString(PKG, "UrlInputMeta.Exception.ErrorReadingRepository"), e);
		}
	}
	
	public void saveRep(Repository rep, ObjectId id_transformation, ObjectId id_step)
	throws KettleException
	{
		try
		{
			for (int i=0;i<inputFields.length;i++)
			{
                rep.saveStepAttribute(id_transformation, id_step, "url_field",   urlField);

			    UrlInputField field = inputFields[i];
			    
				rep.saveStepAttribute(id_transformation, id_step, i, "field_name",          field.getName());
				rep.saveStepAttribute(id_transformation, id_step, i, "field_value",         field.getValue());
				rep.saveStepAttribute(id_transformation, id_step, i, "field_type",          field.getTypeDesc());
				rep.saveStepAttribute(id_transformation, id_step, i, "field_format",        field.getFormat());
				rep.saveStepAttribute(id_transformation, id_step, i, "field_currency",      field.getCurrencySymbol());
				rep.saveStepAttribute(id_transformation, id_step, i, "field_decimal",       field.getDecimalSymbol());
				rep.saveStepAttribute(id_transformation, id_step, i, "field_group",         field.getGroupSymbol());
				rep.saveStepAttribute(id_transformation, id_step, i, "field_length",        field.getLength());
				rep.saveStepAttribute(id_transformation, id_step, i, "field_precision",     field.getPrecision());
				rep.saveStepAttribute(id_transformation, id_step, i, "field_trim_type",     field.getTrimTypeCode());
				rep.saveStepAttribute(id_transformation, id_step, i, "field_repeat",        field.isRepeated());
			}

            rep.saveStepAttribute(id_transformation, id_step, "valueField",        valueField);

			rep.saveStepAttribute(id_transformation, id_step, "authorityFieldName", authorityFieldName);
			rep.saveStepAttribute(id_transformation, id_step, "defaultPortFieldName", defaultPortFieldName);
			rep.saveStepAttribute(id_transformation, id_step, "fileFieldName", fileFieldName);
			rep.saveStepAttribute(id_transformation, id_step, "hostFieldName", hostFieldName);
			rep.saveStepAttribute(id_transformation, id_step, "pathFieldName", pathFieldName);
			rep.saveStepAttribute(id_transformation, id_step, "portFieldName", portFieldName);
			rep.saveStepAttribute(id_transformation, id_step, "protocolFieldName", protocolFieldName);
			rep.saveStepAttribute(id_transformation, id_step, "queryFieldName", queryFieldName);
			rep.saveStepAttribute(id_transformation, id_step, "refFieldName", refFieldName);
			rep.saveStepAttribute(id_transformation, id_step, "userInfoFieldName", userInfoFieldName);
			rep.saveStepAttribute(id_transformation, id_step, "uriNameFieldName", uriNameFieldName);
		}
		catch(Exception e)
		{
			throw new KettleException(BaseMessages.getString(PKG, "UrlInputMeta.Exception.ErrorSavingToRepository", ""+id_step), e);
		}
	}

	public void check(List<CheckResultInterface> remarks, TransMeta transMeta, StepMeta stepMeta, RowMetaInterface prev, String input[], String output[], RowMetaInterface info)
	{
		CheckResult cr;

		if(getInputFields().length<=0)
		{
			cr = new CheckResult(CheckResult.TYPE_RESULT_ERROR, BaseMessages.getString(PKG, "UrlInputMeta.CheckResult.NoInputField"), stepMeta);
			remarks.add(cr);
		}		
		
			 if (Const.isEmpty(getFieldValue()))
			 {
				 cr = new CheckResult(CheckResult.TYPE_RESULT_ERROR, BaseMessages.getString(PKG, "UrlInputMeta.CheckResult.NoField"), stepMeta);
				 remarks.add(cr); 
			 }
			 else
			 {
				 cr = new CheckResult(CheckResult.TYPE_RESULT_OK, BaseMessages.getString(PKG, "UrlInputMeta.CheckResult.FieldOk"), stepMeta);
				 remarks.add(cr); 
			 }		 
	}
	
	public StepInterface getStep(StepMeta stepMeta, StepDataInterface stepDataInterface, int cnr, TransMeta tr, Trans trans)
	{
		return new UrlInput(stepMeta, stepDataInterface, cnr, tr, trans);
	}

	public StepDataInterface getStepData()
	{
		return new UrlInputData();
	}

    public boolean supportsErrorHandling()
    {
        return true;
    }

}