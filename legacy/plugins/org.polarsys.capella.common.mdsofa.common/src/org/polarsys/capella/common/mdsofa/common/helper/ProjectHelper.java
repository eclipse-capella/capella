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

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.codegen.ecore.Generator;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.generator.IMDSoFaGeneratorConstants;

/**
 * Workspace projects helper.
 */
public class ProjectHelper {
  /**
   * Log4j reference logger.
   */
  private static final Logger __logger = Logger.getLogger(ProjectHelper.class.getPackage().getName());
  /**
   * Required plug-ins class path entry path identifier.
   */
  protected static final String CLASS_PATH_ENTRY_REQUIRED_PLUGINS_PATH_ID = "requiredPlugins"; //$NON-NLS-1$

  /**
   * Project existence status after check.<br>
   * The project already exists (including default structure), or it has just been created, or creation process failed.<br>
   * Internal purpose only.
   */
  public enum ProjectExistenceStatus {
    ALREADY_EXISTS, CREATED, CREATION_FAILED
  }

  /**
   * Get project from its name.<br>
   * It is assumed that this project name refers to a plug-in.<br>
   * If not, the method <code>ResourcesPlugin.getWorkspace().getRoot().getProject(projectName_p)</code> is invoked as result.
   * @param projectName_p A project name that points to a plug-in in the workspace.
   * @return
   */
  public static IProject getProject(String projectName_p) {
    IProject result = null;
    // Precondition.
    if (null == projectName_p) {
      return result;
    }
    // Get model base from project name.
    IPluginModelBase modelBase = PluginRegistry.findModel(projectName_p);
    // Precondition.
    // Warning : fix for the Eclipse platform bug that consists in having PDE in a weird state
    // regarding in-development plug-ins from launching platform as deployed ones in current one.
    if (null == modelBase) {
      return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName_p);
    }
    result = getProject(modelBase);
    return result;
  }

  /**
   * Get the IProject for specified plug-in model.
   * @param pluginModelBase_p
   * @return null if the plug-in is not in the workspace.
   */
  public static IProject getProject(IPluginModelBase pluginModelBase_p) {
    IProject result = null;
    // Precondition.
    if (null != pluginModelBase_p) {
      IResource underlyingResource = pluginModelBase_p.getUnderlyingResource();
      if (null != underlyingResource) {
        // Retrieve project from the model.
        result = underlyingResource.getProject();
      }
    }
    return result;
  }

  /**
   * Can identified project element be converted to a factory component ?
   * @param elementId_p The chosen project element id. Can either be the project name or the plug-in id.
   * @return <code>false</code> if no project can be found, or it is not a plug-in, or its id does not match its name. <code>true</code> if it can be
   *         converted to a FC.
   */
  public static boolean canBeConvertedToFC(String elementId_p) {
    boolean result = false;
    IProject project = getProject(elementId_p);
    if (null == project) {
      StringBuilder loggerMessage = new StringBuilder("ProjectHelper.canBeConvertedToFC(..) _ "); //$NON-NLS-1$
      loggerMessage.append("Unable to locate a project with element id ").append(elementId_p); //$NON-NLS-1$
      __logger.error(loggerMessage.toString());
    } else {
      IPluginModelBase model = PluginRegistry.findModel(project);
      if (null != model) {
        String modelId = model.getPluginBase().getId();
        result = project.getName().equals(modelId);
        if (!result) {
          StringBuilder loggerMessage = new StringBuilder("ProjectHelper.canBeConvertedToFC(..) _ "); //$NON-NLS-1$
          loggerMessage.append("To convert an exisiting plug-in into a FC, its related project name must be equal to the plug-in id."); //$NON-NLS-1$
          __logger.error(loggerMessage.toString());
        }
      } else {
        StringBuilder loggerMessage = new StringBuilder("ProjectHelper.canBeConvertedToFC(..) _ "); //$NON-NLS-1$
        loggerMessage.append("Element ").append(elementId_p).append(" is not a valid plug-in project."); //$NON-NLS-1$ //$NON-NLS-2$
        loggerMessage.append("Only plug-in projects can be converted to FCs."); //$NON-NLS-1$
        __logger.error(loggerMessage.toString());
      }
    }
    return result;
  }

  /**
   * Get java project in the workspace from its project name.
   * @param projectName_p It is assumed given project name points to a plug-in project. If not, use {@link #getJavaProject(IProject)} instead.
   * @return null if it could not be found. Either the given name is null, or there is no java project in the workspace with this name (maybe then the project
   *         is deployed instead).
   */
  public static IJavaProject getJavaProject(String projectName_p) {
    return getJavaProject(getProject(projectName_p));
  }

  /**
   * Get java project from workspace project.
   * @param project_p
   * @return null if it could not be found. The given project is not a Java one, or JDT has not been initialized correctly.
   */
  public static IJavaProject getJavaProject(IProject project_p) {
    IJavaProject result = null;
    // Precondition.
    if (null == project_p) {
      return result;
    }
    // Get java project from project.
    IJavaElement javaElement = (IJavaElement) ((IAdaptable) project_p).getAdapter(IJavaElement.class);
    // Only JDT UI initializes adapter mechanism.
    // If this method is called from a non UI application (e.g antRunner) uses the JavaCore API instead.
    if (null == javaElement) {
      javaElement = JavaCore.create(project_p);
    }
    result = javaElement.getJavaProject();
    return result;
  }

  /**
   * Create a new class loader for a java project, son of specified parent class loader.
   * @param javaProject_p
   * @param parentClassLoader_p
   * @return null if one of the parameter is null or no extra dependency was found in the java project
   * @throws Exception
   */
  public static ClassLoader getClassLoaderFor(IJavaProject javaProject_p, ClassLoader parentClassLoader_p) throws Exception {
    URLClassLoader result = null;
    // Preconditions.
    if ((null == javaProject_p) || (null == parentClassLoader_p)) {
      return result;
    }
    // Create the class loader.
    URL[] classPathEntries = getProjectDependencies(javaProject_p);
    // No dependency available, it is likely nothing is to be done.
    // Should an error have happened, it would have been thrown as an exception.
    if (classPathEntries.length == 0) {
      return result;
    }
    // Use pattern plug-in classloader as parent one.
    result = new URLClassLoader(classPathEntries, parentClassLoader_p);
    return result;
  }

  /**
   * Get given java project dependencies in terms of compilation.<br>
   * The resulting array can be used to feed a specific class loader.
   * @param javaProject_p
   * @return An array of URL.<br>
   *         Not null, but possibly empty.
   * @throws Exception
   */
  protected static URL[] getProjectDependencies(IJavaProject javaProject_p) throws Exception {
    // Retrieve its dependencies.
    IClasspathEntry[] classpathEntries = javaProject_p.getResolvedClasspath(true);
    List<URL> classpathUrls = new ArrayList<URL>(classpathEntries.length);
    // Create urls out of them.
    for (IClasspathEntry classpathEntry : classpathEntries) {
      IPath urlPath = classpathEntry.getPath();
      switch (classpathEntry.getEntryKind()) {
        // For source project or projects, look for the output folder...
        case IClasspathEntry.CPE_SOURCE:
        case IClasspathEntry.CPE_PROJECT:
          IJavaProject localProject = ProjectHelper.getJavaProject(urlPath.segment(0));
          urlPath = localProject.getProject().getLocation().append(localProject.getOutputLocation().removeFirstSegments(1));
        break;
        // For libraries and installed plug-ins, simply retain the classpath entry path.
        default:
        break;
      }
      classpathUrls.add(urlPath.toFile().toURI().toURL());
    }
    return classpathUrls.toArray(new URL[classpathUrls.size()]);
  }

  /**
   * Refresh given project in the workspace.
   * @param project_p
   * @param monitor_p
   */
  public static void refreshProject(IProject project_p, IProgressMonitor monitor_p) {
    refreshProject(project_p, IResource.DEPTH_INFINITE, monitor_p);
  }

  /**
   * Refresh a project in the workspace.
   * @param projectToRefresh_p
   * @param depth_p
   * @param monitor_p
   * @see {@link IResource#refreshLocal(int, IProgressMonitor)}
   */
  public static void refreshProject(IProject projectToRefresh_p, int depth_p, IProgressMonitor monitor_p) {
    try {
      projectToRefresh_p.refreshLocal(depth_p, monitor_p);
    } catch (CoreException exception_p) {
      StringBuilder loggerMessage = new StringBuilder("ProjectHelper.refreshProject(..) _ "); //$NON-NLS-1$
      __logger.warn(loggerMessage.toString(), exception_p);
    }
  }

  /**
   * Create a source folder in given project.
   * @param sourceFolderName_p the name of the source folder.
   * @param project_p the hosting project.
   * @param monitor_p progress monitor.
   * @return <code>true</code> means the creation is successful.
   */
  public static boolean createSourceFolder(String sourceFolderName_p, IProject project_p, IProgressMonitor monitor_p) {
    boolean successful = false;
    // Preconditions
    if ((null == sourceFolderName_p) || (null == project_p) || (null == monitor_p)) {
      return successful;
    }
    try {
      // Get the related java project.
      IJavaProject javaProject = JavaCore.create(project_p);
      // Get all source entries.
      IClasspathEntry[] sourceEntries = javaProject.getRawClasspath();
      // Add a generated source folder.
      IPath generatedSourceFolderPath = project_p.getFullPath().append(sourceFolderName_p);
      createFolder(sourceFolderName_p, project_p, new SubProgressMonitor(monitor_p, 1));
      // Create a new source entry for the 'generated' source folder.
      IClasspathEntry generatedSourceEntry = JavaCore.newSourceEntry(generatedSourceFolderPath);
      IClasspathEntry[] newEntries = new IClasspathEntry[sourceEntries.length + 1];
      // Copy the 'src' source entry.
      newEntries[0] = sourceEntries[0];
      // Add the 'generated' entry.
      newEntries[1] = generatedSourceEntry;
      // Copy the remaining entries.
      System.arraycopy(sourceEntries, 1, newEntries, 2, sourceEntries.length - 1);
      // Set the new entries on the java project.
      javaProject.setRawClasspath(newEntries, new SubProgressMonitor(monitor_p, 1));
      successful = true;
    } catch (Exception exception_p) {
      successful = false;
      StringBuilder loggerMessage = new StringBuilder("ProjectHelper.createSourceFolder(..) _ "); //$NON-NLS-1$
      __logger.warn(loggerMessage.toString(), exception_p);
    }
    return successful;
  }

  /**
   * Create a folder with given name in given project.
   * @param folderName_p
   * @param project_p
   * @param monitor_p
   * @return <code>null</code> if creation failed.
   */
  public static IFolder createFolder(String folderName_p, IProject project_p, IProgressMonitor monitor_p) {
    IFolder folder = project_p.getFolder(folderName_p);
    // Create the physical resource.
    if (!folder.exists()) {
      // Get parent path.
      IPath parentPath = new Path(folderName_p);
      if (parentPath.segmentCount() > 1) {
        parentPath = parentPath.removeLastSegments(1);
        // Make sure parent exists first.
        createFolder(parentPath.toString(), project_p, monitor_p);
      }
      // Then try and create given folder.
      try {
        folder.create(true, true, monitor_p);
      } catch (CoreException exception_p) {
        folder = null;
        StringBuilder loggerMessage = new StringBuilder("ProjectHelper.createFolder(..) _ "); //$NON-NLS-1$
        __logger.warn(loggerMessage.toString(), exception_p);
      }
    }
    return folder;
  }

  /**
   * Make sure named project exists.<br>
   * If not try and create a new one with given name.
   * @param projectName_p The expected project name.
   * @param cleanProject_p true to clean project structure after creation, false otherwise.
   * @param projectType_p {@link Generator#EMF_EMPTY_PROJECT_STYLE} see other values.
   * @param sourceFolder_p Source folder of created project.
   * @return {@link ProjectExistenceStatus} value.
   */
  private static ProjectExistenceStatus ensureProjectExists(String projectName_p, boolean cleanProject_p, int projectType_p, String sourceFolder_p) {
    ProjectExistenceStatus result = ProjectExistenceStatus.CREATION_FAILED;
    // Precondition.
    if (null == projectName_p) {
      return result;
    }
    IFile file = FileHelper.getPlatformFile(projectName_p + IMDSoFaGeneratorConstants.PROJECT_ROOT_FILE);
    // Project already exists, stop here.
    if (file.exists()) {
      result = ProjectExistenceStatus.ALREADY_EXISTS;
      return result;
    }
    // Else, try and create an EMF project.
    IPath projectLocationPath = new Path(ICommonConstants.SLASH_CHARACTER + projectName_p);
    IProject resultingProject =
                                Generator.createEMFProject(projectLocationPath.append(ICommonConstants.SLASH_CHARACTER + sourceFolder_p), null,
                                                           new ArrayList<IProject>(0), new NullProgressMonitor(), projectType_p, Collections.EMPTY_LIST);
    if ((null != resultingProject) && resultingProject.exists()) {
      result = ProjectExistenceStatus.CREATED;
      // If project should be cleaned, do it.
      if (cleanProject_p) {
        cleanProjectStructure(resultingProject);
      }
    } else {
      result = ProjectExistenceStatus.CREATION_FAILED;
    }
    return result;
  }

  /**
   * Make sure named project exists.<br>
   * If not try and create a new one with given name, source folder is set to 'src'.
   * @param projectName_p The expected project name.
   * @param cleanProject_p true to clean project structure after creation, false otherwise.
   * @param projectType_p {@link Generator#EMF_EMPTY_PROJECT_STYLE} see other values.
   * @return {@link ProjectExistenceStatus} value.
   */
  private static ProjectExistenceStatus ensureProjectExists(String projectName_p, boolean cleanProject_p, int projectType_p) {
    return ensureProjectExists(projectName_p, cleanProject_p, projectType_p, IMDSoFaGeneratorConstants.SRC_FOLDER);
  }

  /**
   * Clean newly created project structure.<br/> Remove plug-in dependencies class path container from given project (if applicable).<br/> Also set nature
   * back to Java one.
   * @param project_p
   */
  private static void cleanProjectStructure(IProject project_p) {
    IJavaProject javaProject = getJavaProject(project_p);
    // Precondition.
    if (null == javaProject) {
      return;
    }
    // Restore Java nature only.
    String natureIds[] = new String[] { JavaCore.NATURE_ID };
    try {
      IProjectDescription description = project_p.getDescription();
      description.setNatureIds(natureIds);
      project_p.setDescription(description, new NullProgressMonitor());
    } catch (CoreException exception_p1) {
      StringBuilder loggerMessage = new StringBuilder("ProjectHelper.cleanProjectStructure(..) _ "); //$NON-NLS-1$
      __logger.warn(loggerMessage.toString(), exception_p1);
    }
    // Get raw class path.
    IClasspathEntry[] rawClasspath = null;
    try {
      rawClasspath = javaProject.getRawClasspath();
    } catch (JavaModelException exception_p) {
      StringBuilder loggerMessage = new StringBuilder("ProjectHelper.cleanProjectStructure(..) _ "); //$NON-NLS-1$
      __logger.warn(loggerMessage.toString(), exception_p);
    }
    // Iterate over class path elements.
    if ((null != rawClasspath) && (0 != rawClasspath.length)) {
      List<IClasspathEntry> newRawClasspath = new ArrayList<IClasspathEntry>(rawClasspath.length);
      for (IClasspathEntry classpathEntry : rawClasspath) {
        if (classpathEntry.getEntryKind() == IClasspathEntry.CPE_CONTAINER) {
          // Do not retain required plug-ins container.
          IPath path = classpathEntry.getPath();
          if (!CLASS_PATH_ENTRY_REQUIRED_PLUGINS_PATH_ID.equals(path.getFileExtension())) {
            newRawClasspath.add(classpathEntry);
          }
        } else {
          newRawClasspath.add(classpathEntry);
        }
      }
      // Set new raw class path.
      try {
        javaProject.setRawClasspath(newRawClasspath.toArray(new IClasspathEntry[newRawClasspath.size()]), new NullProgressMonitor());
      } catch (JavaModelException exception_p) {
        StringBuilder loggerMessage = new StringBuilder("ProjectHelper.cleanProjectStructure(..) _ "); //$NON-NLS-1$
        __logger.warn(loggerMessage.toString(), exception_p);
      }
    }
  }

  /**
   * Make sure named plug-in project exists.<br>
   * If not try and create a new one with given name.
   * @param projectName_p The expected project name.
   * @return
   */
  public static ProjectExistenceStatus ensurePluginProjectExists(String projectName_p) {
    return ensureProjectExists(projectName_p, false, Generator.EMF_PLUGIN_PROJECT_STYLE);
  }

  /**
   * Make sure named plug-in project exists.<br>
   * If not try and create a new one with given name.
   * @param projectName_p The expected project name.
   * @param sourceFolder_p Source folder of created project.
   * @return
   */
  public static ProjectExistenceStatus ensurePluginProjectExists(String projectName_p, String sourceFolder_p) {
    return ensureProjectExists(projectName_p, false, Generator.EMF_PLUGIN_PROJECT_STYLE, sourceFolder_p);
  }

  /**
   * Make sure named project exists.<br>
   * If not try and create a new one with given name.
   * @param projectName_p The expected project name.
   * @return
   */
  public static ProjectExistenceStatus ensureProjectExists(String projectName_p) {
    return ensureProjectExists(projectName_p, true, Generator.EMF_EMPTY_PROJECT_STYLE);
  }
}
