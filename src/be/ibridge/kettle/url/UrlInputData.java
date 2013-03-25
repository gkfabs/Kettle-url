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

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.net.URL;

import org.pentaho.di.core.fileinput.FileInputList;
import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.trans.step.BaseStepData;
import org.pentaho.di.trans.step.StepDataInterface;

/**
 * @author Fabien Carrion
 * @since 22-04-2012
 */
public class UrlInputData extends BaseStepData implements StepDataInterface 
{
	public Object[] previousRow;
    public RowMetaInterface inputRowMeta;
	public RowMetaInterface outputRowMeta;
	public RowMetaInterface convertRowMeta;
	public int nr_repeats;
	
    public int nrInputFields;
    public int recordnr;
    public int nrrecords;
    public Object[] readrow;
    public int totalpreviousfields;
    
    
	/**
	 * The XML files to read
	 */
	public FileInputList files;

	public FileInputStream fr;
	public BufferedInputStream is;
    public String itemElement;
    public int itemCount;
    public int itemPosition;
    public long rownr;
    public int indexSourceField;
    
    RowMetaInterface      outputMeta;
    
	public URL urlToParse;
	
	/**
	 * 
	 */
	public UrlInputData()
	{
		super();
		nr_repeats=0;
		previousRow=null;
		
		fr=null;
		is=null;
		indexSourceField=-1;
		
		nrInputFields=-1;
		recordnr=0;
		nrrecords=0;

		readrow=null;
		totalpreviousfields=0;
	}

}
