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

package org.polarsys.capella.common.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.polarsys.capella.common.MdeCommonActivator;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 */
public class FileHelper2 {
  /**
   * Does specified file contain a file extension ?
   * @param filePath
   * @return <code>true</code>
   */
  public static boolean containsFileExtension(String filePath) {
    return null != getFileExtension(filePath);
  }

  /**
   * Get the file extension for specified file path.
   * @param filePath
   * @return the file extension starting with '.' character.
   */
  public static String getFileExtension(String filePath) {
    String result = null;
    File file = new File(filePath);
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
   * @param fileName a short name without file extension.
   * @param fileExtension
   * @param content
   * @return a not <code>null</code> file if success.
   * @throws IOException
   */
  public static File writeContentInTemporaryFile(String fileName, String fileExtension, byte[] content) throws IOException {
    String prefix = fileName;
    if (prefix.length() < 3) {
      prefix =
          new StringBuilder(prefix).append(ICommonConstants.UNDERSCORE_CHARACTER).append(ICommonConstants.UNDERSCORE_CHARACTER).append(
              ICommonConstants.UNDERSCORE_CHARACTER).toString();
    }
    File tempFile = File.createTempFile(prefix, fileExtension);
    tempFile.deleteOnExit();
    writeFile(tempFile, content);
    return tempFile;
  }

  /**
   * Write file content for given file with specified content.
   * @param file
   * @param content
   */
  public static void writeFile(File file, byte[] content) {
    // Preconditions.
    if ((null == file) || (null == content)) {
      Platform.getLog(MdeCommonActivator.class).log(Status.error("Error while writing file", new IllegalArgumentException())); //$NON-NLS-1$
      return;
    }
    FileChannel channel = null;
    // Try and open the resulting file.
    try (FileOutputStream fileOutputStream = new FileOutputStream(file)){
	channel = fileOutputStream.getChannel();
      // Write contents.
      channel.write(ByteBuffer.wrap(content));
    } catch (Exception exception) {
      Platform.getLog(MdeCommonActivator.class).log(Status.error("Error while writing file", exception)); //$NON-NLS-1$
    } finally {
      if ((null != channel) && channel.isOpen()) {
        try {
          // Close the channel.
          channel.close();
        } catch (IOException exception) {
          Platform.getLog(MdeCommonActivator.class).log(Status.error("Error while closing file channel", exception)); //$NON-NLS-1$
        }
      }
    }
  }
}
