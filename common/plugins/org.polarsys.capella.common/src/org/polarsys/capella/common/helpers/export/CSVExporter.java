/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import java.nio.charset.Charset;
import java.util.List;

import org.polarsys.capella.common.helpers.export.utils.CSVWriter;

/**
 *
 */
public class CSVExporter extends AbstractExporter {
  
  protected char delimiter;
  protected String charset = "UTF-8";
  
  public CSVExporter(char delimiter) {
    this.delimiter = delimiter;
  }

  /**
   * @see org.polarsys.capella.common.helpers.export.AbstractExporter#export(java.io.OutputStream, java.lang.Object)
   */
  @SuppressWarnings("unchecked")
  @Override
  public void export(OutputStream stream, Object data) throws IOException {
    export(stream, (List<String[]>) data);
  }
  
  /**
   * @see #export(OutputStream, Object)
   */
  public void export(OutputStream stream, List<String[]> data) throws IOException  {
        
    CSVWriter writer = new CSVWriter(stream, this.delimiter, Charset.forName("UTF-8"));
    
    for (String[] line: data) {
      if (null == line || line.length == 0) {
        writer.endRecord();
      }
      writer.writeRecord(line);
    }

    writer.flush();
  }

  /**
   * @see org.polarsys.capella.core.ui.metric.export.AbstractExporter#getExtension()
   */
  @Override
  public String getExtension() {
    return IFileExtensionConstants.CSV_EXTENSION;
  }

  /**
   * @see org.polarsys.capella.core.ui.metric.export.AbstractExporter#getDescription()
   */
  @Override
  public String getDescription() {
    return ExportMessages.csvDesc;
  }
}
