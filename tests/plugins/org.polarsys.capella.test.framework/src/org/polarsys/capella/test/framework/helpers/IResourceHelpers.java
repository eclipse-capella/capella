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
package org.polarsys.capella.test.framework.helpers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jdt.internal.core.JavaElement;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * @author Erwan Brottier
 */
public class IResourceHelpers {

  public static File getFileInPlugin(Class<?> oneClassInThePlugin, String relativeFilePath)
      throws URISyntaxException, IOException {
    Bundle bundle = FrameworkUtil.getBundle(oneClassInThePlugin);
    URL fileURL = bundle.getEntry(relativeFilePath);

    // Use FileLocator.toFileURL(fileURL) rather than resolve(fileURL)
    // because when the plugin is packed into a jar this will cause Eclipse to create an unpacked version in a temporary
    // location so that the object can be accessed using File.
    URL resolvedFileURL = FileLocator.toFileURL(fileURL);
    // We need to use the 3-arg constructor of URI in order to properly escape file system chars
    URI resolvedURI = new URI(resolvedFileURL.getProtocol(), resolvedFileURL.getPath(), null);

    return new File(resolvedURI);
  }

  /**
   * Returns the root folder of the plugin containing the given class
   */
  public static File getPluginFolder(Class<?> clazz) {
    File pluginFolder = null;
    try {
      pluginFolder = getFileInPlugin(clazz, "/"); //$NON-NLS-1$
    } catch (URISyntaxException e1) {
      e1.printStackTrace();
    } catch (IOException e1) {
      e1.printStackTrace();
    }

    return pluginFolder;
  }

  /**
   * Returns a path in the plugin containing the given class
   */
  public static File getFileOrFolderInTestPlugin(Class<?> clazz, String relativePath) {
    return new File(IResourceHelpers.getPluginFolder(clazz).toString() + "/" + relativePath); //$NON-NLS-1$
  }

  public static List<IResource> getIResourceFromSelection(ExecutionEvent event) {
    ISelection selection = HandlerUtil.getCurrentSelection(event);
    List<IResource> resources = new ArrayList<IResource>();
    if (selection instanceof IStructuredSelection) {
      for (Object select : ((IStructuredSelection) selection).toList()) {
        IResource resource = null;
        if (select instanceof IResource)
          resource = (IResource) select;
        else if (select instanceof JavaElement)
          resource = ((JavaElement) select).getResource();
        resources.add(resource);
      }
    }
    return resources;
  }

  public static List<IFile> getIFilesFromSelection(ExecutionEvent event) {
    List<IFile> result = new ArrayList<IFile>();
    for (IResource resource : getIResourceFromSelection(event))
      if (resource instanceof IFile)
        result.add((IFile) resource);
    return result;
  }

  public static List<IProject> getIProjectFromSelection(ExecutionEvent event) {
    List<IProject> result = new ArrayList<IProject>();
    for (IResource resource : getIResourceFromSelection(event))
      if (resource instanceof IProject)
        result.add((IProject) resource);
    return result;
  }

  public static String readFileAsString(File file) {
    StringBuffer fileData = new StringBuffer();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(file));
      char[] buf = new char[1024];
      int numRead = 0;
      while ((numRead = reader.read(buf)) != -1) {
        String readData = String.valueOf(buf, 0, numRead);
        fileData.append(readData);
      }
      reader.close();
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return fileData.toString();
  }

  public static String readFileAsString(URL fileUrl) {
    try {
      File file = new File(fileUrl.toURI());
      return readFileAsString(file);
    } catch (URISyntaxException exception) {
      exception.printStackTrace();
    }
    return null;
  }

  public static void writeStringInFile(File file, String data) {
    File folder = file.getParentFile();
    if (!folder.exists())
      folder.mkdirs();
    try {
      FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
      fileWriter.write(data);
      fileWriter.close();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  public static String readFileAsString(IFile file) {
    return readFileAsString(convertToFile(file));
  }

  public static void writeStringInFile(IFile file, String data) {
    writeStringInFile(convertToFile(file), data);
  }

  /**
   * Creates a file in the given parent with the given relative path. Adds the given content in the new file. Be
   * careful, the file is overwritten if it already exist.
   */
  public static IFile createFile(IContainer parent, String relativeFilePath, String content) {
    IFile file = null;
    if (parent instanceof IProject) {
      file = ((IProject) parent).getFile("/" + relativeFilePath); //$NON-NLS-1$
    } else {
      file = ((IFolder) parent).getFile("/" + relativeFilePath); //$NON-NLS-1$
    }
    InputStream source = new ByteArrayInputStream(content.getBytes());
    try {
      if (file.exists()) {
        file.delete(true, null);
      }
      file.create(source, true, null);
    } catch (CoreException exception) {
      exception.printStackTrace();
    }
    return file;
  }

  public static IFile createFile(IContainer parent, String relativeFilePath) {
    return createFile(parent, relativeFilePath, ""); //$NON-NLS-1$
  }

  public static List<IFile> getIFilesIn(IContainer container) {
    return new IFileRequestor().search(container);
  }

  public static List<IFile> getIFilesIn(IContainer container, String fileExtension) {
    return new IFileRequestor().search(container, fileExtension);
  }

  public static IProject getProjectFromIContainer(IContainer container) {
    IContainer newContainer = container;
    while (!(newContainer instanceof IProject)) {
      newContainer = newContainer.getParent();
    }
    return (IProject) newContainer;
  }

  public static IProject getProjectFromIFile(IFile file) {
    return getProjectFromIContainer(file.getParent());
  }

  public static IProject getProjectFromIFile(IFolder folder) {
    return getProjectFromIContainer(folder);
  }

  public static IFolder createFolder(IFolder folder) {
    if (!folder.exists()) {
      try {
        folder.create(true, false, null);
      } catch (CoreException exception) {
        exception.printStackTrace();
      }
    }
    return folder;
  }

  public static File convertToFile(IResource file) {
    return file.getRawLocation().makeAbsolute().toFile();
  }

  public static void refresh(IResource resource) {
    try {
      resource.refreshLocal(IResource.DEPTH_INFINITE, null);
    } catch (CoreException exception) {
      exception.printStackTrace();
    }
  }

  public static void setFileAsWritable(IFile file) {
    ResourceAttributes attributes = file.getResourceAttributes();
    attributes.setReadOnly(false);
    try {
      file.setResourceAttributes(attributes);
    } catch (CoreException exception) {
      exception.printStackTrace();
    }
  }

  public static IProject getEclipseProjectInWorkspace(String projectName) {
    return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
  }

  public static void createZip(File sourceFolder, File... files) {
    createZip(sourceFolder, Arrays.asList(files));
  }

  /**
   * Returns recursive file list
   */
  public static List<File> getAllFiles(File dir) {
    List<File> result = new ArrayList<File>();
    LinkedList<File> files = new LinkedList<File>();
    files.add(dir);

    while (!files.isEmpty()) {
      File file = files.removeFirst();
      if (file.isDirectory()) {
        files.addAll(Arrays.asList(file.listFiles()));
      } else {
        result.add(file);
      }
    }
    return result;
  }

  /**
   * Create a zip file located into sourceFolder with the following files.
   */
  public static void createZip(File sourceFolder, List<File> fileList) {

    try {
      if (sourceFolder.exists()) {
        sourceFolder.delete();
      }

      FileOutputStream fos = new FileOutputStream(sourceFolder);
      ZipOutputStream zos = new ZipOutputStream(fos);

      for (File file : fileList) {
        for (File item : getAllFiles(file)) {
          if (!item.isDirectory()) {
            addToZip(sourceFolder.getParentFile(), item, zos);
          }
        }
      }

      zos.close();
      fos.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void addToZip(File directoryToZip, File file, ZipOutputStream zos)
      throws FileNotFoundException, IOException {

    FileInputStream fis = new FileInputStream(file);

    // create a relative path for the zipEntry
    String zipFilePath = file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1,
        file.getCanonicalPath().length());
    ZipEntry zipEntry = new ZipEntry(zipFilePath);
    zos.putNextEntry(zipEntry);

    byte[] bytes = new byte[1024];
    int length;
    while ((length = fis.read(bytes)) >= 0) {
      zos.write(bytes, 0, length);
    }

    zos.closeEntry();
    fis.close();
  }

}
