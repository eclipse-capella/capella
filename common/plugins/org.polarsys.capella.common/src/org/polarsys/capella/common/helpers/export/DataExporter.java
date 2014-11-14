/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.helpers.export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.helpers.ICommonConstants2;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * The export tool itself
 */
public class DataExporter {

  protected IExporterProvider _exporterProvider;
  
  /**
   * Constructor: at least one exporter must be provided.
   * @param exporterProvider the exporter Provider
   * @see IExporterProvider
   */
  public DataExporter(IExporterProvider exporterProvider) {
    
    if ( 
        exporterProvider == null ||
        exporterProvider.getAvailableExporter() == null ||
        exporterProvider.getAvailableExporter().isEmpty()
    ) {
      throw new IllegalArgumentException(ExportMessages.errNullFileName); 
    }
    
    _exporterProvider = exporterProvider;

  }
  
  /**
   * Export data to a given file.
   * @param fileName_p the full file path
   * @param data_p the data. format must be compliant with the provided exporters.
   * @return <code>true</code> whether file was created, <code>false</code> otherwise
   * @see AbstractExporter
   * @see IExporterProvider
   */
  public boolean exportToFile(String fileName_p, Object data_p) {
    
    if (null == fileName_p || 0 == fileName_p.length()) {
      return false;
    }
    
    boolean result = true;
    
    String ext = ICommonConstants.EMPTY_STRING;
    
    int dotLoc = fileName_p.lastIndexOf(ICommonConstants.POINT_CHARACTER);
    if (dotLoc != -1) {
      ext = fileName_p.substring(dotLoc + 1);
    } else { // No extension
      ext = ICommonConstants.EMPTY_STRING; 
    }
    
    AbstractExporter exporter = getExporter(ext);
    
    if (null != exporter) {
      
      File file = new File(fileName_p);
      OutputStream stream;
      try {
        stream = new FileOutputStream(file);
        exporter.export(stream, data_p);
        stream.close();
      } catch (FileNotFoundException exception_p) {
        exception_p.printStackTrace();
        result = false;
      } catch (IOException exception_p) {
        exception_p.printStackTrace();
        result = false;
      }
    } else {
      result = false;
    }
    
    return result; 
  }

  /**
   * Return the exporter linked to a given file extension. 
   * @param ext_p the extension to found in available exporter.
   * @return the matching {@link AbstractExporter} whether found, <code>null</code> otherwise.
   */
  public AbstractExporter getExporter(String ext_p) {
    
    AbstractExporter result = null;
    
    for (AbstractExporter exporter: _exporterProvider.getAvailableExporter()) {
      if (exporter.getExtension().equals(ext_p)) {
        result = exporter;
        break;
      }
    }
    
    return result;
  }
  
  /**
   * Return list of available extension in accordance with the format for file dialog
   * @return an array of String
   */
  public String[] getSupportedExtension() {
    
    List<String> extensions = new ArrayList<String>();
    
    final String suffix = getSuffixForFileDialog();
    
    for (AbstractExporter exporter : _exporterProvider.getAvailableExporter()) {
      extensions.add(suffix + exporter.getExtension());
    }
    
    return extensions.toArray(new String[extensions.size()]);
  }
  
  /**
   * Return list of available extension in accordance with the format for file dialog
   * @return an array of String
   */
  public String[] getSupportedDescription() {
    
    List<String> descriptions = new ArrayList<String>();
    
    final String suffix = getSuffixForFileDialog();
    
    for (AbstractExporter exporter : _exporterProvider.getAvailableExporter()) {
      descriptions.add(suffix + exporter.getDescription());
    }
    
    return descriptions.toArray(new String[descriptions.size()]);
  }
  
  protected String getSuffixForFileDialog() {
    return String.valueOf(ICommonConstants2.ASTERISK_CHARACTER) +  ICommonConstants.POINT_CHARACTER;
  }
  
}
