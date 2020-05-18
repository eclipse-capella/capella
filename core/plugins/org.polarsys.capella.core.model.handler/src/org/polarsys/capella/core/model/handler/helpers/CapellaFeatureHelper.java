/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.handler.helpers;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.xmi.impl.XMLHandler;
import org.osgi.framework.Version;
import org.polarsys.capella.common.data.core.gen.xmi.impl.CapellaXMLSaveImpl;
import org.polarsys.capella.common.mdsofa.common.helper.StringHelper;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;

/**
 */
public class CapellaFeatureHelper {
  /**
   * Log4j reference logger.
   */
  private static final Logger __logger = ReportManagerRegistry.getInstance()
      .subscribe(IReportManagerDefaultComponents.MODEL);

  /**
   * BUFFER size read to detect the version.
   */
  private static final int BUFFER_SIZE = 76;

  /**
   * Retrieve the short version of the given model
   * 
   * It returns a Version.emptyVersion if no version encountered in the file
   */
  public static Version getFileVersion(IFile modelFile) {
    String detectedVersion = CapellaFeatureHelper.getDetectedVersion((IFile) modelFile);
    return Version.parseVersion(detectedVersion);
  }

  /**
   * Get the version serialized in given model file.
   * 
   * @param modelFile_p
   * Deprecated use getFileVersion to prevent NPE
   */
  @Deprecated
  public static String getDetectedVersion(IFile modelFile_p) {
    String detectedVersion = null;
    try {
      // Get an open input stream on given file.
      InputStream contents = modelFile_p.getContents(false);
      if (!contents.markSupported()) {
        contents = new BufferedInputStream(contents);
      }
      // Read bytes to be able to detect the Capella version.
      byte[] buffer = readBuffer(contents);
      // Close the input stream.
      contents.close();
      String encoding = XMLHandler.getXMLEncoding(buffer);
      // Instantiate the bytes as a string according to encoding.
      String contentAsString = new String(buffer, encoding);
      // Get the version.
      detectedVersion = StringHelper.substring(CapellaXMLSaveImpl.CAPELLA_VERSION_PREFIX, "-->", contentAsString, //$NON-NLS-1$
          false);
      if (detectedVersion == null) {
        // version not detected, it might be an old legacy model
        detectedVersion = StringHelper.substring(CapellaXMLSaveImpl.MELODY_VERSION_PREFIX, "-->", contentAsString, //$NON-NLS-1$
            false);
      }
    } catch (Exception exception_p) {
      StringBuilder loggerMessage = new StringBuilder("CapellaFeatureHelper.getDetectedVersion(..) _ "); //$NON-NLS-1$
      __logger.warn(loggerMessage.toString(), exception_p);
    }
    return detectedVersion;
  }

  /**
   * Read buffer where Capella version is stored.
   * 
   * @param inputStream_p
   * @return
   * @throws IOException
   */
  private static byte[] readBuffer(InputStream inputStream_p) throws IOException {
    if (inputStream_p.available() == 0) {
      return new byte[0];
    }

    byte[] buffer = new byte[BUFFER_SIZE];
    inputStream_p.mark(BUFFER_SIZE);
    int bytesRead = inputStream_p.read(buffer, 0, BUFFER_SIZE);
    int totalBytesRead = bytesRead;

    while (bytesRead != -1 && (totalBytesRead < BUFFER_SIZE)) {
      bytesRead = inputStream_p.read(buffer, totalBytesRead, BUFFER_SIZE - totalBytesRead);

      if (bytesRead != -1)
        totalBytesRead += bytesRead;
    }

    if (totalBytesRead < 0) {
      buffer = new byte[0];
    } else if (totalBytesRead < BUFFER_SIZE) {
      byte[] smallerBuffer = new byte[totalBytesRead];
      System.arraycopy(buffer, 0, smallerBuffer, 0, totalBytesRead);
      buffer = smallerBuffer;
    }

    inputStream_p.reset();
    return buffer;
  }
}
