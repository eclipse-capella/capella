/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.helpers.export;

import java.io.IOException;
import java.io.OutputStream;

import org.polarsys.capella.common.helpers.ICommonConstants2;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * Data exporter class. 
 *
 */
abstract public class AbstractExporter {

  /**
   * Return the filtering expression
   */
  final public String getExtensionFilter() {
    
    final String asterisk = String.valueOf(ICommonConstants2.ASTERISK_CHARACTER);
        
    String filter = asterisk + ICommonConstants.POINT_CHARACTER;
    
    filter += isFilteringOff() ? 
        asterisk : 
        getExtension()
    ;
    
    return filter;
  }
  
  /**
   * Export data to file
   * @param stream the target {@link OutputStream}
   * @param data the data to export (depending of the exporter implementation)
   * @throws IOException, ClassCastException 
   */
  abstract public void export(OutputStream stream, Object data)  throws IOException, ClassCastException;
  
  /** return the extension connected to this exporter */
  abstract public String getExtension();
  
  /**  return brief description about this exporter */
  abstract public String getDescription();
  
  /**
   * Check if a given file name match with this extension
   * @param fileName the file name to test
   * @return <code>true</code> whether matching, <code>false</code> otherwise
   */
  final public boolean isOfType(String fileName) {
    return
      isFilteringOff() ||
      (
          null != fileName &&
          fileName.endsWith(ICommonConstants.POINT_CHARACTER + getExtension())
       )
    ;
  }
  
  final private boolean isFilteringOff() {
    return getExtension().equals(ICommonConstants.EMPTY_STRING);
  }
  
}
