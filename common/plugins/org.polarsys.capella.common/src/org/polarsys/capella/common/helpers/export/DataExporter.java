/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Status;
import org.polarsys.capella.common.MdeCommonActivator;
import org.polarsys.capella.common.helpers.ICommonConstants2;
import org.polarsys.capella.common.helpers.export.utils.CSVWriterMessages;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * The export tool itself
 */
public class DataExporter {

  protected IExporterProvider exporterProvider;

  /**
   * Constructor: at least one exporter must be provided.
   * 
   * @param exporterProvider
   *          the exporter Provider
   * @see IExporterProvider
   */
  public DataExporter(IExporterProvider exporterProvider) {
    if (exporterProvider == null || exporterProvider.getAvailableExporter() == null
        || exporterProvider.getAvailableExporter().isEmpty()) {
      throw new IllegalArgumentException(CSVWriterMessages.errNullFileName);
    }

    this.exporterProvider = exporterProvider;
  }

  /**
   * Export data to a given file.
   * 
   * @param fileName
   *          the full file path
   * @param data
   *          the data. format must be compliant with the provided exporters.
   * @return <code>true</code> whether file was created, <code>false</code> otherwise
   * @see AbstractExporter
   * @see IExporterProvider
   */
  public boolean exportToFile(String fileName, Object data) {

    if (null == fileName || 0 == fileName.length()) {
      return false;
    }

    boolean result = true;

    String ext = ICommonConstants.EMPTY_STRING;

    int dotLoc = fileName.lastIndexOf(ICommonConstants.POINT_CHARACTER);
    if (dotLoc != -1) {
      ext = fileName.substring(dotLoc + 1);
    } else { // No extension
      ext = ICommonConstants.EMPTY_STRING;
    }

    AbstractExporter exporter = getExporter(ext);
    if (null != exporter) {
      File file = new File(fileName);
      OutputStream stream = null;
      try {
        stream = new FileOutputStream(file);
        exporter.export(stream, data);
      } catch (FileNotFoundException exception) {
        MdeCommonActivator.getDefault().getLog().log(new Status(Status.ERROR, MdeCommonActivator.getDefault().getBundle().getSymbolicName(), "Error", exception));
        result = false;
      } catch (IOException exception) {
        MdeCommonActivator.getDefault().getLog().log(new Status(Status.ERROR, MdeCommonActivator.getDefault().getBundle().getSymbolicName(), "Error", exception));
        result = false;
      } finally {
        try {
          if (stream != null) {
            stream.close();
          }
        } catch (IOException exception) {
          MdeCommonActivator.getDefault().getLog().log(new Status(Status.ERROR, MdeCommonActivator.getDefault().getBundle().getSymbolicName(), "Error", exception));
        }
      }
    } else {
      result = false;
    }

    return result;
  }

  /**
   * Return the exporter linked to a given file extension.
   * 
   * @param ext
   *          the extension to found in available exporter.
   * @return the matching {@link AbstractExporter} whether found, <code>null</code> otherwise.
   */
  public AbstractExporter getExporter(String ext) {
    AbstractExporter result = null;
    for (AbstractExporter exporter : exporterProvider.getAvailableExporter()) {
      if (exporter.getExtension().equals(ext)) {
        result = exporter;
        break;
      }
    }
    return result;
  }

  /**
   * Return list of available extension in accordance with the format for file dialog
   * 
   * @return an array of String
   */
  public String[] getSupportedExtension() {

    List<String> extensions = new ArrayList<String>();

    final String suffix = getSuffixForFileDialog();

    for (AbstractExporter exporter : exporterProvider.getAvailableExporter()) {
      extensions.add(suffix + exporter.getExtension());
    }

    return extensions.toArray(new String[extensions.size()]);
  }

  /**
   * Return list of available extension in accordance with the format for file dialog
   * 
   * @return an array of String
   */
  public String[] getSupportedDescription() {

    List<String> descriptions = new ArrayList<String>();

    final String suffix = getSuffixForFileDialog();

    for (AbstractExporter exporter : exporterProvider.getAvailableExporter()) {
      descriptions.add(suffix + exporter.getDescription());
    }

    return descriptions.toArray(new String[descriptions.size()]);
  }

  protected String getSuffixForFileDialog() {
    return String.valueOf(ICommonConstants2.ASTERISK_CHARACTER) + ICommonConstants.POINT_CHARACTER;
  }

}
