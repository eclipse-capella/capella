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
package org.polarsys.capella.common.mdsofa.common.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;

import org.polarsys.capella.common.mdsofa.common.activator.SolFaCommonActivator;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 */
public class FileHelper {
  /**
   * Log4j reference logger.
   */
  private static final Logger __logger = Logger.getLogger(FileHelper.class.getPackage().getName());

  /**
   * Get file full url from relative one.
   * @param fileRelativePath_p File path relative to workspace.<br>
   *          It <b>must</b> start with <i>pluginId</i> or <i>project name</i>. It is also recommended that both plug-in id and plug-in project names are the
   *          same.<br>
   *          As a convenience, the full path will refer to the plug-in id.<br>
   *          <b>Example</b> : <i>org.polarsys.capella.common.mdsofa/model/example.ecore</i> is a path relative to the workspace that refers to the
   *          <i>org.polarsys.capella.common.mdsofa plug-in</i>, having a <i>model/example.ecore</i> file in its project.<br>
   *          In Eclipse resource system, such a path is considered as an absolute one against the workspace root.<br>
   *          It's still referred to as a relative path, since the returned URL is absolute in the file system.
   * @return
   */
  public static URL getFileFullUrl(String fileRelativePath_p) {
    // Get the URI for given relative path.
    return getFileFullUrl(getFileFullUri(fileRelativePath_p));
  }

  /**
   * Get file full url from its full uri.<br>
   * See {@link #getFileFullUri(String)} method.
   * @param fileFullUri_p
   * @return
   */
  public static URL getFileFullUrl(URI fileFullUri_p) {
    URL result = null;
    // Resolve url from returned uri.
    try {
      result = FileLocator.resolve(new URL(fileFullUri_p.toString()));
    } catch (Exception exception_p) {
      StringBuilder loggerMessage = new StringBuilder("FileHelper.getFileFullPath(..) _ "); //$NON-NLS-1$
      loggerMessage.append("Unable to resolve the url for ").append(fileFullUri_p.toString()); //$NON-NLS-1$
      __logger.warn(loggerMessage.toString(), exception_p);
    }
    return result;
  }

  /**
   * Get a file uri from relative one which is not resolved against the eclipse platform.<br>
   * The returned uri starts with either 'platform:/plug-in/' or 'platform:/resource/'.
   * @param fileRelativePath_p File path relative to workspace.<br>
   *          It <b>must</b> start with <code>project name</code> or <i>pluginId</i>. It is also recommended that both plug-in id and plug-in project names are
   *          the same.<br>
   *          As a convenience, the full path will refer to the plug-in id.<br>
   *          <b>Example</b> : <i>org.polarsys.capella.common.mdsofa/model/example.ecore</i> is a path relative to the workspace that refers to the
   *          <i>org.polarsys.capella.common.mdsofa plug-in</i>, having a <i>model/example.ecore</i> file in its project.
   * @return an {@link URI} not resolved against the eclipse platform.<br>
   */
  public static URI getFileFullUri(String fileRelativePath_p) {
    URI fileUri = null;
    // Precondition.
    if (null == fileRelativePath_p) {
      return fileUri;
    }
    // Find project from relative first segment.
    IPath path = new Path(fileRelativePath_p);
    IProject project = ProjectHelper.getProject(path.segment(0));
    if ((null != project) && project.exists()) { // Project found, the file is in the workspace.
      fileUri = URI.createPlatformResourceURI(fileRelativePath_p, true);
    } else { // Resource not found, the file is deployed elsewhere.
      fileUri = URI.createPlatformPluginURI(fileRelativePath_p, true);
    }
    return fileUri;
  }

  /**
   * Convert package name to a correct java folder path.
   * @param packageName_p
   * @return
   */
  public static String convertPackageNameToFolderPath(String packageName_p) {
    String result = null;
    if (null != packageName_p) {
      result = packageName_p.replace(ICommonConstants.POINT_CHARACTER, ICommonConstants.SLASH_CHARACTER);
    }
    return result;
  }

  /**
   * Read given input stream as an array of bytes.
   * @param inputStream_p
   * @return a not null array.
   */
  public static byte[] readFile(InputStream inputStream_p) {
    byte[] buffer = null;
    try {
      // Available bytes to read.
      int sizeToRead = inputStream_p.available();
      // Allocate a new buffer with the right size.
      buffer = new byte[sizeToRead];
      // Read bytes from the input stream.
      int readBytes = inputStream_p.read(buffer);
      // Total read bytes count.
      int totalReadBytes = readBytes;
      // Read again, if available bytes remain.
      while (readBytes != -1 && (totalReadBytes < sizeToRead)) {
        readBytes = inputStream_p.read(buffer, totalReadBytes, sizeToRead - totalReadBytes);
        // Add readBytes to the total read bytes count.
        if (readBytes != -1) {
          totalReadBytes += readBytes;
        }
      }
    } catch (Exception exception_p) {
      StringBuilder loggerMessage = new StringBuilder("FileHelper.readFile(..) _ "); //$NON-NLS-1$
      loggerMessage.append("Failed to read the input stream ! "); //$NON-NLS-1$
      __logger.warn(loggerMessage.toString());
    } finally {
      if (null != inputStream_p) {
        try {
          inputStream_p.close();
        } catch (IOException exception_p) {
          StringBuilder loggerMessage = new StringBuilder("FileHelper.readFile(..) _ "); //$NON-NLS-1$
          loggerMessage.append("Failed to close input stream ! "); //$NON-NLS-1$
          __logger.warn(loggerMessage.toString(), exception_p);
        }
      }
    }
    // Ensure to return a not null array.
    return (null == buffer) ? new byte[0] : buffer;
  }

  /**
   * Read file as a string.
   * @param filePath_p File path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @return If an error occurred {@link ICommonConstants#EMPTY_STRING} is returned.
   */
  public static String readFile(String filePath_p) {
    byte[] rawContent = readRawFile(filePath_p);
    String result = (0 == rawContent.length) ? ICommonConstants.EMPTY_STRING : new String(rawContent);
    return result;
  }

  /**
   * Get file as a stream.
   * @param filePath_p File path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @return If an error occurred null is returned.
   */
  public static InputStream getFileAsStream(String filePath_p) {
    InputStream result = null;
    // Get input stream.
    URL fileURL = getFileFullUrl(filePath_p);
    try {
      result = fileURL.openStream();
    } catch (Exception exception_p) {
      StringBuilder loggerMessage = new StringBuilder("FileHelper.getFileAsStream(..) _ "); //$NON-NLS-1$
      loggerMessage.append("Failed to load ").append(filePath_p); //$NON-NLS-1$
      __logger.warn(loggerMessage.toString());
    }
    return result;
  }

  /**
   * Read file as an array of bytes.
   * @param filePath_p File path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @return a not null array.
   */
  public static byte[] readRawFile(String filePath_p) {
    byte[] result = null;
    // Get stream from file.
    InputStream inputStream = getFileAsStream(filePath_p);
    // Ensure the input stream got from the file path is not null.
    if (null != inputStream) {
      result = readFile(inputStream);
    }
    return (null == result) ? new byte[0] : result;
  }

  /**
   * Copy given source file content in given target file.
   * @param sourceFileRelativePath_p File path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @param targetFileRelativePath_p File path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   */
  public static void copyFile(String sourceFileRelativePath_p, String targetFileRelativePath_p) {
    byte[] content = readRawFile(sourceFileRelativePath_p);
    writeFile(targetFileRelativePath_p, true, content);
  }

  /**
   * Write given string contents at specified path.
   * @param filePath_p File path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @param ensureFolders_p Make sure all parent folders exist by creating all necessary ones.
   * @param contents_p Contents that should be written to pointed file.
   * @return
   */
  public static boolean writeFile(String filePath_p, boolean ensureFolders_p, String contents_p) {
    return writeFile(filePath_p, ensureFolders_p, contents_p.getBytes());
  }

  /**
   * Write given contents of bytes at specified path.
   * @param filePath_p File path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @param ensureFolders_p Make sure all parent folders exist by creating all necessary ones.
   * @param contents_p Contents that should be written to pointed file.
   * @return
   */
  public static boolean writeFile(String filePath_p, boolean ensureFolders_p, byte[] contents_p) {
    boolean result = false;
    FileChannel channel = null;
    try {
      // Get file full path from its relative one.
      String fileFullPath = FileHelper.getFileFullUrl(FileHelper.getFileFullUri(filePath_p)).getFile();
      // Should path be enforced ?
      if (ensureFolders_p) {
        ensurePathAvailability(fileFullPath);
      }
      // Make sure file is writable.
      boolean fileWritable = makeFileWritable(filePath_p);
      // Write content.
      if (fileWritable) {
        // Try and open the resulting file.
        channel = new FileOutputStream(fileFullPath).getChannel();
        // Write contents.
        channel.write(ByteBuffer.wrap(contents_p));
        result = true;
      }
    } catch (Exception exception_p) {
      result = false;
      StringBuilder loggerMessage = new StringBuilder("FileHelper.writeFile(..) _ "); //$NON-NLS-1$
      loggerMessage.append("Failed to open channel in write mode for "); //$NON-NLS-1$
      loggerMessage.append(filePath_p).append(" !"); //$NON-NLS-1$
      __logger.warn(loggerMessage.toString(), exception_p);
    } finally {
      if ((null != channel) && channel.isOpen()) {
        try {
          // Close the channel.
          channel.close();
        } catch (IOException exception_p) {
          result = false;
          StringBuilder loggerMessage = new StringBuilder("FileHelper.writeFile(..) _ "); //$NON-NLS-1$
          loggerMessage.append("Failed to close opened channel in write mode ! "); //$NON-NLS-1$
          loggerMessage.append(filePath_p).append(" may no longer be usable."); //$NON-NLS-1$
          __logger.warn(loggerMessage.toString(), exception_p);
        }
      }
    }
    return result;
  }

  /**
   * Rename file from source file relative path to destination relative path.
   * @param sourceFileRelativePath_p File path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @param destinationFileRelativePath_p File path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @return
   */
  public static boolean renameFile(String sourceFileRelativePath_p, String destinationFileRelativePath_p) {
    // Preconditions.
    if ((null == sourceFileRelativePath_p) || (null == destinationFileRelativePath_p)) {
      return false;
    }
    IFile sourceFile = getPlatformFile(sourceFileRelativePath_p);
    IPath destinationPath = getPlatformFile(destinationFileRelativePath_p).getFullPath();
    return moveResource(sourceFile, destinationPath);
  }

  /**
   * Rename folder from source folder relative path to destination relative path.
   * @param sourceFolderRelativePath_p Folder path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @param destinationFolderRelativePath_p Folder path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @return
   */
  public static boolean renameFolder(String sourceFolderRelativePath_p, String destinationFolderRelativePath_p) {
    // Preconditions.
    if ((null == sourceFolderRelativePath_p) || (null == destinationFolderRelativePath_p)) {
      return false;
    }
    IFolder sourceFolder = getPlatformFolder(sourceFolderRelativePath_p);
    IPath destinationPath = getPlatformFolder(destinationFolderRelativePath_p).getFullPath();
    return moveResource(sourceFolder, destinationPath);
  }

  /**
   * Move resource to given destination path.
   * @param resource_p
   * @param destinationPath_p
   * @return true if move occurred with no exception, false otherwise.
   */
  public static boolean moveResource(IResource resource_p, IPath destinationPath_p) {
    boolean result = false;
    try {
      resource_p.move(destinationPath_p, true, new NullProgressMonitor());
      result = true;
    } catch (Exception e_p) {
      StringBuilder loggerMessage = new StringBuilder("FileHelper.moveResource(..) _ "); //$NON-NLS-1$
      loggerMessage.append("Could not move ").append(resource_p.getFullPath()); //$NON-NLS-1$
      loggerMessage.append(" to ").append(destinationPath_p); //$NON-NLS-1$
      __logger.warn(loggerMessage.toString(), e_p);
    }
    return result;
  }

  /**
   * Is given file relative path pointing to an existing file ?
   * @param fileRelativePath_p File path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @return
   */
  public static boolean exists(String fileRelativePath_p) {
    IFile file = getPlatformFile(fileRelativePath_p);
    return (null != file) ? file.exists() : false;
  }

  /**
   * Make sure that given path is safe to use, ie ensure that all parent folders exist.
   * @param fileFullPath_p
   */
  public static void ensurePathAvailability(String fileFullPath_p) {
    // Get rid of file extension and file name, for this has no meaning in the parent folders chain.
    IPath parentFolderPath = new Path(fileFullPath_p).removeFileExtension().removeLastSegments(1);
    // If it still makes sense to create a folder, go for it.
    if (!parentFolderPath.isEmpty()) {
      File parentFolder = parentFolderPath.toFile();
      // Create the chain of parent folders.
      parentFolder.mkdirs();
    }
  }

  /**
   * Delete given relative file in the workspace.
   * @param workspaceRelativeFile_p
   */
  public static boolean deleteFile(String workspaceRelativeFile_p) {
    boolean result = false;
    // Get the file
    IFile fileToDelete = getPlatformFile(workspaceRelativeFile_p);
    if ((null != fileToDelete) && fileToDelete.exists()) {
      try {
        fileToDelete.delete(true, new NullProgressMonitor());
        result = true;
      } catch (CoreException exception_p) {
        StringBuilder loggerMessage = new StringBuilder("FileHelper.deleteFile(..) _ "); //$NON-NLS-1$
        loggerMessage.append("Failed to delete file:").append(workspaceRelativeFile_p); //$NON-NLS-1$
        __logger.warn(loggerMessage.toString());
      }
    }
    return result;
  }

  /**
   * Delete given relative folder in the workspace.
   * @param workspaceRelativePath_p
   * @return true if successfully deleted, false otherwise.
   */
  public static boolean deleteFolder(String workspaceRelativePath_p) {
    boolean result = false;
    IFolder folderToDelete = getPlatformFolder(workspaceRelativePath_p);
    if ((null != folderToDelete) && folderToDelete.exists()) {
      try {
        folderToDelete.delete(true, new NullProgressMonitor());
        result = true;
      } catch (CoreException exception_p) {
        StringBuilder loggerMessage = new StringBuilder("FileHelper.deleteFolder(..) _ "); //$NON-NLS-1$
        loggerMessage.append("Failed to delete folder:").append(workspaceRelativePath_p); //$NON-NLS-1$
        __logger.warn(loggerMessage.toString(), exception_p);
      }
    }
    return result;
  }

  /**
   * Get platform file as an {@link IResource} from its relative path.
   * @param fileRelativePath_p File path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @return
   */
  public static IFile getPlatformFile(String fileRelativePath_p) {
    IFile result = null;
    // Precondition.
    if (null == fileRelativePath_p) {
      return result;
    }
    IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
    result = root.getFile(new Path(fileRelativePath_p));
    return result;
  }

  /**
   * Get platform folder as an {@link IResource} from its relative path.
   * @param folderRelativePath_p Folder path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @return
   */
  public static IFolder getPlatformFolder(String folderRelativePath_p) {
    IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
    return root.getFolder(new Path(folderRelativePath_p));
  }

  /**
   * Returns the file extension portion for given file path, <br>
   * or <code>null</code> if there is none.<br>
   * <p>
   * The file extension portion is defined as the string<br>
   * following the last period (".") character in the last segment.<br>
   * If there is no period in the last segment, the path has no<br>
   * file extension portion. If the last segment ends in a period,<br>
   * the file extension portion is the empty string.<br>
   * </p>
   * @param filePath_p
   * @return the file extension or <code>null</code>
   */
  public static String getFileExtension(String filePath_p) {
    return new Path(filePath_p).getFileExtension();
  }

  /**
   * Make file writable.<br>
   * That is, make sure file is modifiable after this call.<br>
   * The user may be asked to take a decision if the file is held by the configuration management system.<br>
   * Nevertheless, if no UI is reachable, then the system is urged into making the file writable.
   * @param filePath_p File path relative to the plug-in, plug-in id included. See {@link #getFileFullUrl(String)} documentation.
   * @return false if file could not be made writable or user denied rights to (in case of a configuration management). true if it does not exist (then it is
   *         writable) or permission was granted (either by the system or by the user).
   */
  public static boolean makeFileWritable(String filePath_p) {
    // Get user helper.
    IUserEnforcedHelper helper = SolFaCommonActivator.getDefault().getUserEnforcedHelper();
    // Delegate to it.
    IStatus status = helper.makeFileWritable(getPlatformFile(filePath_p));
    return status.isOK();
  }
}
