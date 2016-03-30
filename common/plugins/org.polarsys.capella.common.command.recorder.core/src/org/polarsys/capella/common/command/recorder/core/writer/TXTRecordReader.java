/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.command.recorder.core.writer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * Default text reader for command record files.
 *
 */
public class TXTRecordReader {

  final static String WHITE_STRING = ICommonConstants.EMPTY_STRING + ICommonConstants.WHITE_SPACE_CHARACTER;
  
  protected File _file;
  
  protected BufferedReader _reader = null;
  
  protected DummyTreeData _data = null;
  
  /**
   * Constructor 
   */
  public TXTRecordReader(File file) {
    _file = file;

  }
  
  /** Accessor on data */
  public DummyTreeData getData() {
	  return _data;
  }
  
  /**
   * Parse file
   * @return
   */
  public boolean parse() {
    
    boolean result = true;
    
    if (null == _file || !_file.exists()) {
      return false;
    }

    _data = new DummyTreeData(null, -1);
    
    try {
      InputStream is = new FileInputStream(_file);
      _reader = new BufferedReader(new InputStreamReader(is, "UTF-8")); //$NON-NLS-1$
      
      String line = null; 
      
      DummyTreeData entry = null;
      DummyTreeData parent = null;
      DummyTreeData last = null;
      DummyTreeData current = null;
      
      for (;;) {
        line = _reader.readLine();
        if (null == line || 0 == line.length()) {
          break;
        }
        line = line.trim();
        
        if ( line.startsWith(TXTWriterHelper.ENTRY_TAG) ) {
        	entry = new DummyTreeData(_data, -1);
        	current = entry;
        	current.setData(splitEntry(line));
        	last = current;
        	_data.addChild(entry);
        } else if ( line.startsWith(TXTWriterHelper.SUBENTRY_TAG) ) {
        	String[] tag = splitSubEntry(line);
        	int depth = Integer.valueOf(tag[0]).intValue();
        	if (depth == 0) {
        		parent = entry;
        		current = new DummyTreeData(parent, depth);
        		current.setData(tag);
        		last = current;
        		parent.addChild(current);
        	} else {
        		int lastDepth = last.getDepth();
        	    if (depth > lastDepth) {
        	    	parent = last;
        	    	current = new DummyTreeData(parent, depth);
        	    	current.setData(tag);
        	    	last = current;
        	    	parent.addChild(current);
        	    } else {
        	    	parent = last.getParent();
        	    	int delta = depth - lastDepth;
        	    	for (int i = 0 ; i < delta; i++) {
        	    		parent = parent.getParent();
        	    	}
        	    	current = new DummyTreeData(parent, depth);
        	    	current.setData(tag);
        	    	last = current;
        	    	parent.addChild(current);
        	    }
        	}
        } else if ( line.startsWith(TXTWriterHelper.EXTRADATA_TAG) ) {
        	String[] tag = null;
        	if (last.getDepth() == -1) {
        		tag = splitExtraDataForDate(line);
        	} else {
        		tag = splitExtraDataForEObject(line);
        	}
        	current.setSubData(tag);
        } else {
          // Do nothing
        }
      }

    } catch (FileNotFoundException exception) {
      result = false;
    } catch (UnsupportedEncodingException exception) {
      result = false;
    } catch (IOException exception) {
      result = false;
    } finally {
      if ( _reader != null ) {
        try {
          _reader.close();
          _reader = null;
        } catch (IOException exception) {/* do nothing */}
      }
    }

    return result;
  }
  
  protected String[] splitEntry(String line) {
	  String tmp = removePrefix(line,  TXTWriterHelper.ENTRY_TAG);
	  String tmp1 = tmp.split(WHITE_STRING)[0]; // OP'
	  String tmp2;
    try {
      tmp2 = tmp.substring(tmp1.length() + 1 );
    } catch (StringIndexOutOfBoundsException exception) {
      tmp2 = ITXTConstants.NOT_FOUND_STRING;
    } 
	  return new String[] {tmp1, tmp2};
  }
  
  protected String[] splitSubEntry(String line) {
	  String tmp = removePrefix(line,  TXTWriterHelper.SUBENTRY_TAG);
	  String tmp1 = tmp.split(WHITE_STRING)[0]; // depth	  
	  String tmp2;
	  String tmp3 = ICommonConstants.EMPTY_STRING;
    try {
      tmp2 = tmp.substring(tmp1.length() + 1 );
    } catch (StringIndexOutOfBoundsException exception) {
      tmp2 = ITXTConstants.NOT_FOUND_STRING;
    }
    
    if (tmp2.indexOf(ITXTConstants.ID_PRE) >0 ) {
      String[] l = tmp2.split(ITXTConstants.ID_PRE);
      tmp2 = l[0];
      tmp3 = l[1];
    }
    
	  return new String[] {tmp1, tmp2, tmp3};
  }
  
  protected String[] splitExtraDataForDate(String line) {
	String tmp = removePrefix(line, TXTWriterHelper.EXTRADATA_TAG);
	return new String[]{tmp} ; // date as one string
  }
  
  protected String[] splitExtraDataForEObject(String line) {
	String tmp = removePrefix(line, TXTWriterHelper.EXTRADATA_TAG);
	return tmp.split(WHITE_STRING); // EClass + package URI
  }
  
  protected String removePrefix(String line, String prefix) {
	  return line.substring(prefix.length() + 1); // prefix + white space
  }
  
}
