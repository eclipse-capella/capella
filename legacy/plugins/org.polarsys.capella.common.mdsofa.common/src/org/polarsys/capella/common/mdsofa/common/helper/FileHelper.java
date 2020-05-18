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
package org.polarsys.capella.common.mdsofa.common.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

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
  private static final Logger logger = Logger.getLogger(FileHelper.class.getPackage().getName());
  
  private FileHelper() {
	  // To hide the implicit public one
  }

  /**
   * Get file full url from relative one.
   * 
   * @param fileRelativePath
   *          File path relative to workspace.<br>
   *          It <b>must</b> start with <i>pluginId</i> or <i>project name</i>. It is also recommended that both plug-in
   *          id and plug-in project names are the same.<br>
   *          As a convenience, the full path will refer to the plug-in id.<br>
   *          <b>Example</b> : <i>org.polarsys.capella.common.mdsofa/model/example.ecore</i> is a path relative to the
   *          workspace that refers to the <i>org.polarsys.capella.common.mdsofa plug-in</i>, having a
   *          <i>model/example.ecore</i> file in its project.<br>
   *          In Eclipse resource system, such a path is considered as an absolute one against the workspace root.<br>
   *          It's still referred to as a relative path, since the returned URL is absolute in the file system.
   * @return
   */
  public static URL getFileFullUrl(String fileRelativePath) {
    // Get the URI for given relative path.
    URI fileFullUri = getFileFullUri(fileRelativePath);
    if(fileFullUri != null) {
    	return getFileFullUrl(fileFullUri);    	
    }
    return null;
  }

  /**
   * Get file full url from its full uri.<br>
   * See {@link #getFileFullUri(String)} method.
   * 
   * @param fileFullUri
   * @return
   */
  public static URL getFileFullUrl(URI fileFullUri) {
    URL result = null;
    // Resolve url from returned uri.
    try {
      result = FileLocator.resolve(new URL(fileFullUri.toString()));
    } catch (Exception exception) {
      StringBuilder loggerMessage = new StringBuilder("FileHelper.getFileFullPath(..) _ "); //$NON-NLS-1$
      loggerMessage.append("Unable to resolve the url for ").append(fileFullUri.toString()); //$NON-NLS-1$
      logger.warn(loggerMessage.toString(), exception);
    }
    return result;
  }

  /**
   * Get a file uri from relative one which is not resolved against the eclipse platform.<br>
   * The returned uri starts with either 'platform:/plug-in/' or 'platform:/resource/'.
   * 
   * @param fileRelativePath
   *          File path relative to workspace.<br>
   *          It <b>must</b> start with <code>project name</code> or <i>pluginId</i>. It is also recommended that both
   *          plug-in id and plug-in project names are the same.<br>
   *          As a convenience, the full path will refer to the plug-in id.<br>
   *          <b>Example</b> : <i>org.polarsys.capella.common.mdsofa/model/example.ecore</i> is a path relative to the
   *          workspace that refers to the <i>org.polarsys.capella.common.mdsofa plug-in</i>, having a
   *          <i>model/example.ecore</i> file in its project.
   * @return an {@link URI} not resolved against the eclipse platform.<br>
   */
  public static URI getFileFullUri(String fileRelativePath) {
    URI fileUri = null;
    // Precondition.
    if (null == fileRelativePath) {
      return fileUri;
    }
    // Find project from relative first segment.
    IPath path = new Path(fileRelativePath);
    IProject project = ProjectHelper.getProject(path.segment(0));
    if ((null != project) && project.exists()) { // Project found, the file is in the workspace.
      fileUri = URI.createPlatformResourceURI(fileRelativePath, true);
    } else { // Resource not found, the file is deployed elsewhere.
      fileUri = URI.createPlatformPluginURI(fileRelativePath, true);
    }
    return fileUri;
  }

  /**
   * Convert package name to a correct java folder path.
   * 
   * @param packageName
   * @return
   */
  public static String convertPackageNameToFolderPath(String packageName) {
    String result = null;
    if (null != packageName) {
      result = packageName.replace(ICommonConstants.POINT_CHARACTER, ICommonConstants.SLASH_CHARACTER);
    }
    return result;
  }

  /**
   * Read given input stream as an array of bytes.
   * 
   * @param inputStream
   * @return a not null array.
   */
  public static byte[] readFile(InputStream inputStream) {
    byte[] buffer = null;
    try {
      // Available bytes to read.
      int sizeToRead = inputStream.available();
      // Allocate a new buffer with the right size.
      buffer = new byte[sizeToRead];
      // Read bytes from the input stream.
      int readBytes = inputStream.read(buffer);
      // Total read bytes count.
      int totalReadBytes = readBytes;
      // Read again, if available bytes remain.
      while (readBytes != -1 && (totalReadBytes < sizeToRead)) {
        readBytes = inputStream.read(buffer, totalReadBytes, sizeToRead - totalReadBytes);
        // Add readBytes to the total read bytes count.
        if (readBytes != -1) {
          totalReadBytes += readBytes;
        }
      }
    } catch (Exception exception) {
      StringBuilder loggerMessage = new StringBuilder("FileHelper.readFile(..) _ "); //$NON-NLS-1$
      loggerMessage.append("Failed to read the input stream ! "); //$NON-NLS-1$
      logger.warn(loggerMessage.toString());
    } finally {
        try {
          inputStream.close();
        } catch (IOException exception) {
          StringBuilder loggerMessage = new StringBuilder("FileHelper.readFile(..) _ "); //$NON-NLS-1$
          loggerMessage.append("Failed to close input stream ! "); //$NON-NLS-1$
          logger.warn(loggerMessage.toString(), exception);
        }
    }
    // Ensure to return a not null array.
    return (null == buffer) ? new byte[0] : buffer;
  }

  /**
   * Read file as a string.
   * 
   * @param filePath_p
   *          File path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @return If an error occurred {@link ICommonConstants#EMPTY_STRING} is returned.
   * @throws UnsupportedEncodingException
   */
  public static String readFile(String filePath) throws UnsupportedEncodingException {
    byte[] rawContent = readRawFile(filePath);
    String result;
    result = (0 == rawContent.length) ? ICommonConstants.EMPTY_STRING : new String(rawContent, "UTF-8");
    return result;
  }

  /**
   * Get file as a stream.
   * 
   * @param filePath
   *          File path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @return If an error occurred null is returned.
   */
  public static InputStream getFileAsStream(String filePath) {
    InputStream result = null;
    // Get input stream.
    URL fileURL = getFileFullUrl(filePath);
    try {
    	if(fileURL != null) {
    		result = fileURL.openStream();
    	}
    } catch (Exception exception) {
      StringBuilder loggerMessage = new StringBuilder("FileHelper.getFileAsStream(..) _ "); //$NON-NLS-1$
      loggerMessage.append("Failed to load ").append(filePath); //$NON-NLS-1$
      logger.warn(loggerMessage.toString());
    }
    return result;
  }

  /**
   * Read file as an array of bytes.
   * 
   * @param filePath
   *          File path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @return a not null array.
   */
  public static byte[] readRawFile(String filePath) {
    byte[] result = null;
    // Get stream from file.
    InputStream inputStream = getFileAsStream(filePath);
    // Ensure the input stream got from the file path is not null.
    if (null != inputStream) {
      result = readFile(inputStream);
    }
    return (null == result) ? new byte[0] : result;
  }

  /**
   * Copy given source file content in given target file.
   * 
   * @param sourceFileRelativePath
   *          File path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @param targetFileRelativePath
   *          File path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   */
  public static void copyFile(String sourceFileRelativePath, String targetFileRelativePath) {
    byte[] content = readRawFile(sourceFileRelativePath);
    writeFile(targetFileRelativePath, true, content);
  }

  /**
   * Write given string contents at specified path.
   * 
   * @param filePath
   *          File path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @param ensureFolders
   *          Make sure all parent folders exist by creating all necessary ones.
   * @param contents
   *          Contents that should be written to pointed file.
   * @return
   */
  public static boolean writeFile(String filePath, boolean ensureFolders, String contents) {
    return writeFile(filePath, ensureFolders, contents.getBytes(Charset.forName("UTF-8")));
  }

  /**
   * Write given contents of bytes at specified path.
   * 
   * @param filePath
   *          File path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @param ensureFolders
   *          Make sure all parent folders exist by creating all necessary ones.
   * @param contents
   *          Contents that should be written to pointed file.
   * @return
   */
	public static boolean writeFile(String filePath, boolean ensureFolders, byte[] contents) {
		boolean result = false;
		FileChannel channel = null;
		// Get file full path from its relative one.
		URI fileFullUri = FileHelper.getFileFullUri(filePath);
		if (fileFullUri != null) {
			URL fileFullUrl = FileHelper.getFileFullUrl(fileFullUri);
			if(fileFullUrl != null) {
				String fileFullPath = fileFullUrl.getFile();
				try (FileOutputStream fileOutputStream = new FileOutputStream(fileFullPath)) {
					// Should path be enforced ?
					if (ensureFolders) {
						ensurePathAvailability(fileFullPath);
					}
					// Make sure file is writable.
					boolean fileWritable = makeFileWritable(filePath);
					// Write content.
					if (fileWritable) {
						// Try and open the resulting file.
						channel = fileOutputStream.getChannel();
						// Write contents.
						channel.write(ByteBuffer.wrap(contents));
						result = true;
						channel.close();
					}
				} catch (Exception exception) {
					result = false;
					StringBuilder loggerMessage = new StringBuilder("FileHelper.writeFile(..) _ "); //$NON-NLS-1$
					loggerMessage.append("Failed to open channel in write mode for "); //$NON-NLS-1$
					loggerMessage.append(filePath).append(" !"); //$NON-NLS-1$
					logger.warn(loggerMessage.toString(), exception);
				} finally {
					if ((null != channel) && channel.isOpen()) {
						try {
							// Close the channel.
							channel.close();
						} catch (IOException exception) {
							result = false;
							StringBuilder loggerMessage = new StringBuilder("FileHelper.writeFile(..) _ "); //$NON-NLS-1$
							loggerMessage.append("Failed to close opened channel in write mode ! "); //$NON-NLS-1$
							loggerMessage.append(filePath).append(" may no longer be usable."); //$NON-NLS-1$
							logger.warn(loggerMessage.toString(), exception);
						}
					}
				}
			}
		}
		return result;
	}

  /**
   * Rename file from source file relative path to destination relative path.
   * 
   * @param sourceFileRelativePath
   *          File path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @param destinationFileRelativePath
   *          File path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @return
   */
  public static boolean renameFile(String sourceFileRelativePath, String destinationFileRelativePath) {
    // Preconditions.
    if ((null == sourceFileRelativePath) || (null == destinationFileRelativePath)) {
      return false;
    }
    IFile sourceFile = getPlatformFile(sourceFileRelativePath);
    IPath destinationPath = getPlatformFile(destinationFileRelativePath).getFullPath();
    return moveResource(sourceFile, destinationPath);
  }

  /**
   * Rename folder from source folder relative path to destination relative path.
   * 
   * @param sourceFolderRelativePath
   *          Folder path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @param destinationFolderRelativePath
   *          Folder path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @return
   */
  public static boolean renameFolder(String sourceFolderRelativePath, String destinationFolderRelativePath) {
    // Preconditions.
    if ((null == sourceFolderRelativePath) || (null == destinationFolderRelativePath)) {
      return false;
    }
    IFolder sourceFolder = getPlatformFolder(sourceFolderRelativePath);
    IPath destinationPath = getPlatformFolder(destinationFolderRelativePath).getFullPath();
    return moveResource(sourceFolder, destinationPath);
  }

  /**
   * Move resource to given destination path.
   * 
   * @param resource
   * @param destinationPath
   * @return true if move occurred with no exception, false otherwise.
   */
  public static boolean moveResource(IResource resource, IPath destinationPath) {
    boolean result = false;
    try {
      resource.move(destinationPath, true, new NullProgressMonitor());
      result = true;
    } catch (Exception ex) {
      StringBuilder loggerMessage = new StringBuilder("FileHelper.moveResource(..) _ "); //$NON-NLS-1$
      loggerMessage.append("Could not move ").append(resource.getFullPath()); //$NON-NLS-1$
      loggerMessage.append(" to ").append(destinationPath); //$NON-NLS-1$
      logger.warn(loggerMessage.toString(), ex);
    }
    return result;
  }

  /**
   * Is given file relative path pointing to an existing file ?
   * 
   * @param fileRelativePath
   *          File path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @return
   */
  public static boolean exists(String fileRelativePath) {
    IFile file = getPlatformFile(fileRelativePath);
    return (null != file) ? file.exists() : Boolean.FALSE;
  }

  /**
   * Make sure that given path is safe to use, ie ensure that all parent folders exist.
   * 
   * @param fileFullPath
   */
  public static void ensurePathAvailability(String fileFullPath) {
    // Get rid of file extension and file name, for this has no meaning in the parent folders chain.
    IPath parentFolderPath = new Path(fileFullPath).removeFileExtension().removeLastSegments(1);
    // If it still makes sense to create a folder, go for it.
    if (!parentFolderPath.isEmpty()) {
      File parentFolder = parentFolderPath.toFile();
      // Create the chain of parent folders.
      parentFolder.mkdirs();
    }
  }

  /**
   * Delete given relative file in the workspace.
   * 
   * @param workspaceRelativeFile
   */
  public static boolean deleteFile(String workspaceRelativeFile) {
    boolean result = false;
    // Get the file
    IFile fileToDelete = getPlatformFile(workspaceRelativeFile);
    if ((null != fileToDelete) && fileToDelete.exists()) {
      try {
        fileToDelete.delete(true, new NullProgressMonitor());
        result = true;
      } catch (CoreException exception) {
        StringBuilder loggerMessage = new StringBuilder("FileHelper.deleteFile(..) _ "); //$NON-NLS-1$
        loggerMessage.append("Failed to delete file:").append(workspaceRelativeFile); //$NON-NLS-1$
        logger.warn(loggerMessage.toString());
      }
    }
    return result;
  }

  /**
   * Delete given relative folder in the workspace.
   * 
   * @param workspaceRelativePath
   * @return true if successfully deleted, false otherwise.
   */
  public static boolean deleteFolder(String workspaceRelativePath) {
    boolean result = false;
    IFolder folderToDelete = getPlatformFolder(workspaceRelativePath);
    if ((null != folderToDelete) && folderToDelete.exists()) {
      try {
        folderToDelete.delete(true, new NullProgressMonitor());
        result = true;
      } catch (CoreException exception) {
        StringBuilder loggerMessage = new StringBuilder("FileHelper.deleteFolder(..) _ "); //$NON-NLS-1$
        loggerMessage.append("Failed to delete folder:").append(workspaceRelativePath); //$NON-NLS-1$
        logger.warn(loggerMessage.toString(), exception);
      }
    }
    return result;
  }

  /**
   * Get platform file as an {@link IResource} from its relative path.
   * 
   * @param fileRelativePath
   *          File path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @return
   */
  public static IFile getPlatformFile(String fileRelativePath) {
    IFile result = null;
    // Precondition.
    if (null == fileRelativePath) {
      return result;
    }
    IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
    result = root.getFile(new Path(fileRelativePath));
    return result;
  }

  /**
   * Get platform folder as an {@link IResource} from its relative path.
   * 
   * @param folderRelativePath
   *          Folder path relative to the plug-in, plug-in id included.<br>
   *          See {@link #getFileFullUrl(String)} documentation.
   * @return
   */
  public static IFolder getPlatformFolder(String folderRelativePath) {
    IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
    return root.getFolder(new Path(folderRelativePath));
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
   * 
   * @param filePath
   * @return the file extension or <code>null</code>
   */
  public static String getFileExtension(String filePath) {
    return new Path(filePath).getFileExtension();
  }

  /**
   * Make file writable.<br>
   * That is, make sure file is modifiable after this call.<br>
   * The user may be asked to take a decision if the file is held by the configuration management system.<br>
   * Nevertheless, if no UI is reachable, then the system is urged into making the file writable.
   * 
   * @param filePath
   *          File path relative to the plug-in, plug-in id included. See {@link #getFileFullUrl(String)} documentation.
   * @return false if file could not be made writable or user denied rights to (in case of a configuration management).
   *         true if it does not exist (then it is writable) or permission was granted (either by the system or by the
   *         user).
   */
  public static boolean makeFileWritable(String filePath) {
    // Get user helper.
    IUserEnforcedHelper helper = SolFaCommonActivator.getDefault().getUserEnforcedHelper();
    // Delegate to it.
    IStatus status = helper.makeFileWritable(getPlatformFile(filePath));
    return status.isOK();
  }
}
