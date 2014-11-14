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
package org.polarsys.capella.common.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.eclipse.core.runtime.IStatus;

import org.polarsys.capella.common.MdeCommonActivator;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 */
public class FileHelper2 {
  /**
   * Does specified file contain a file extension ?
   * @param filePath_p
   * @return <code>true</code>
   */
  public static boolean containsFileExtension(String filePath_p) {
    return null != getFileExtension(filePath_p);
  }

  /**
   * Get the file extension for specified file path.
   * @param filePath_p
   * @return the file extension starting with '.' character.
   */
  public static String getFileExtension(String filePath_p) {
    String result = null;
    File file = new File(filePath_p);
    // Get file short name to see if extension is provided.
    String fileShortName = file.getName();
    int index = fileShortName.lastIndexOf(ICommonConstants.POINT_CHARACTER);
    if (index != -1) {
      result = fileShortName.substring(index);
    }
    return result;
  }

  /**
   * Write content in specified temporary file.<br>
   * This temporary file will be deleted on VM exit.
   * @param fileName_p a short name without file extension.
   * @param fileExtension_p
   * @param content_p
   * @return a not <code>null</code> file if success.
   * @throws IOException
   */
  public static File writeContentInTemporaryFile(String fileName_p, String fileExtension_p, byte[] content_p) throws IOException {
    String prefix = fileName_p;
    if (prefix.length() < 3) {
      prefix =
          new StringBuilder(prefix).append(ICommonConstants.UNDERSCORE_CHARACTER).append(ICommonConstants.UNDERSCORE_CHARACTER).append(
              ICommonConstants.UNDERSCORE_CHARACTER).toString();
    }
    File tempFile = File.createTempFile(prefix, fileExtension_p);
    tempFile.deleteOnExit();
    writeFile(tempFile, content_p);
    return tempFile;
  }

  /**
   * Write file content for given file with specified content.
   * @param file_p
   * @param content_p
   */
  public static void writeFile(File file_p, byte[] content_p) {
    // Preconditions.
    if ((null == file_p) || (null == content_p)) {
      MdeCommonActivator.getDefault().log(IStatus.ERROR, "Error while writing file", new IllegalArgumentException()); //$NON-NLS-1$
      return;
    }
    FileChannel channel = null;
    // Try and open the resulting file.
    try {
      channel = new FileOutputStream(file_p).getChannel();
      // Write contents.
      channel.write(ByteBuffer.wrap(content_p));
    } catch (Exception exception_p) {
      MdeCommonActivator.getDefault().log(IStatus.ERROR, "Error while writing file", exception_p); //$NON-NLS-1$
    } finally {
      if ((null != channel) && channel.isOpen()) {
        try {
          // Close the channel.
          channel.close();
        } catch (IOException exception_p) {
          MdeCommonActivator.getDefault().log(IStatus.ERROR, "Error while closing file channel", exception_p); //$NON-NLS-1$
        }
      }
    }
  }
}
