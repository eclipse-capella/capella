/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.framework.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.common.mdsofa.common.helper.ProjectHelper;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.ProjectApproach;
import org.polarsys.capella.test.framework.actions.gui.GuiActions;
import org.polarsys.capella.test.framework.api.CommonTestMessages;

import com.google.common.io.Files;

/**
 * Helper for writing JUnit tests for RCP application.
 */
public class TestHelper {
  /**
   * UTF-8 encoding constant.
   */
  public static final String UTF_8 = "UTF-8"; //$NON-NLS-1$

  /**
   * Capella project nature. Copied from CapellaNature.ID
   */
  public static final String CAPELLA_PROJECT_NATURE = "org.polarsys.capella.project.nature"; //$NON-NLS-1$

  /**
   * Capella library nature.
   */
  public static final String CAPELLA_LIBRARY_NATURE = "org.polarsys.capella.library.nature"; //$NON-NLS-1$

  /**
   * Clean workspace.
   */
  public static void cleanWorkspace() {
    try {
      ResourcesPlugin.getWorkspace().getRoot().delete(true, true, null);
      System.out.println("Workspace cleaned..."); //$NON-NLS-1$
    } catch (CoreException exception_p) {
      exception_p.printStackTrace();
      Assert.fail(exception_p.getMessage());
    }
  }

  /**
   * Get the model folder in given plug-in by its id.
   * 
   * @param pluginId_p
   * @return a not <code>null</code> folder leading with a <code>/</code>
   *         character.
   */
  public static String getModelFolder(String pluginId_p) {
    return pluginId_p + ICommonConstants.SLASH_CHARACTER + "model/"; //$NON-NLS-1$
  }

  /**
   * Create an empty capella project with given name.
   * 
   * @param name_p
   *          project name.
   * @return a not <code>null</code> project.
   */
  public static IProject createCapellaProject(String name_p) {
    // Get the workspace.
    IWorkspace workspace = ResourcesPlugin.getWorkspace();

    // Get a project for given name.
    IProject project = workspace.getRoot().getProject(name_p);
    // Should not exist, workspace is supposed to be cleaned before the
    // tests execution.
    if (!project.exists()) {
      try {
        IProjectDescription description = workspace.newProjectDescription(name_p);
        // Add the project nature.
        description.setNatureIds(new String[] { CAPELLA_PROJECT_NATURE });
        project.create(description, new NullProgressMonitor());
      } catch (CoreException exception_p) {
        exception_p.printStackTrace();
        Assert.fail(exception_p.getMessage());
      }
    }
    // Open the project.
    try {
      project.open(new NullProgressMonitor());
    } catch (CoreException exception_p) {
      exception_p.printStackTrace();
      Assert.fail(exception_p.getMessage());
    }
    return project;
  }

  /**
   * Create an empty capella library with given name.
   * 
   * @param name_p
   *          project name.
   * @return a not <code>null</code> project.
   */
  public static IProject createCapellaLibrary(String name_p) {
    // Get the workspace.
    IWorkspace workspace = ResourcesPlugin.getWorkspace();

    // Get a project for given name.
    IProject project = workspace.getRoot().getProject(name_p);
    // Should not exist, workspace is supposed to be cleaned before the
    // tests execution.
    if (!project.exists()) {
      try {
        IProjectDescription description = workspace.newProjectDescription(name_p);
        // Add the project nature.
        description.setNatureIds(new String[] { CAPELLA_LIBRARY_NATURE });
        project.create(description, new NullProgressMonitor());
      } catch (CoreException exception_p) {
        exception_p.printStackTrace();
        Assert.fail(exception_p.getMessage());
      }
    }
    // Open the project.
    try {
      project.open(new NullProgressMonitor());
    } catch (CoreException exception_p) {
      exception_p.printStackTrace();
      Assert.fail(exception_p.getMessage());
    }
    return project;
  }

  /**
   * Create a library in current workspace with given name with given capella
   * models.<br>
   * Models are created within a 'model' folder.
   * 
   * @param name_p
   * @param melodymodellerFile_p
   * @param diagramFileName_p
   *          can be <code>null</code> if optional.
   */
  public static IProject createCapellaLibrary(final String name_p, final String melodymodellerFile_p,
      final String diagramFileName_p) {

    final IProject[] capellaProject = { null };

    WorkspaceJob job = new WorkspaceJob("Project Create") { //$NON-NLS-1$
      @Override
      public IStatus runInWorkspace(IProgressMonitor monitor_p) throws CoreException {
        // Create a project that hosts capella data models : aird &
        // melodymodeller files.
        capellaProject[0] = TestHelper.createCapellaLibrary(name_p);
        String melodymodellerFile = new Path(melodymodellerFile_p).lastSegment();
        TestHelper
            .copyFile(melodymodellerFile_p, name_p + ICommonConstants.SLASH_CHARACTER + melodymodellerFile, UTF_8);
        if (null != diagramFileName_p) {
          String diagramFileName = new Path(diagramFileName_p).lastSegment();
          TestHelper.copyFile(diagramFileName_p, name_p + ICommonConstants.SLASH_CHARACTER + diagramFileName, UTF_8);
        }
        return Status.OK_STATUS;
      }
    };
    job.schedule();
    try {
      // Waiting the end
      job.join();
    } catch (InterruptedException exception_p) {
      exception_p.printStackTrace();
    }
    // Make sure the project is refreshed.
    ProjectHelper.refreshProject(capellaProject[0], new NullProgressMonitor());

    return capellaProject[0];
  }

  /**
   * Close and Delete the current project in current workspace with given name .<br>
   * 
   * @param name_p
   */
  public static void closeAndDeleteCapellaProject(String name_p) {
    // Get the workspace.
    IWorkspace workspace = ResourcesPlugin.getWorkspace();

    // Get a project for given name.
    IProject project = workspace.getRoot().getProject(name_p);

    if (project.exists()) {
      try {
        closeProject(project, null);
        deleteProject(project, new NullProgressMonitor());
        // workspace.getRoot().refreshLocal(IResource.DEPTH_INFINITE, new
        // NullProgressMonitor());
        // cleanWorkspace();
        // GuiActions.flushASyncGuiThread();
      } catch (Exception exception_p) {
        exception_p.printStackTrace();
        Assert.fail(exception_p.getMessage());
      }
    }
  }

  /**
   * deleteProject
   * 
   * @param project_p
   * @param monitor_p
   */
  public static void deleteProject(IProject project_p, IProgressMonitor monitor_p) {

    boolean isDeleted = true;
    try {
      project_p.delete(true, true, monitor_p);
    } catch (CoreException exception_p) {
      isDeleted = false;
    }
    Assert.assertTrue(project_p.getName() + " project can not be deleted.", isDeleted); //$NON-NLS-1$
  }

  /**
   * Close project
   * 
   * @param project_p
   * @param monitor_p
   */
  public static void closeProject(IProject project_p, IProgressMonitor monitor_p) {
    boolean isClosed = true;
    try {
      project_p.close(monitor_p);
    } catch (CoreException exception_p) {
      isClosed = false;
    }
    Assert.assertTrue(project_p.getName() + " project can not be closed.", isClosed); //$NON-NLS-1$
  }

  protected static String FRAGMENTS_FOLDER = "fragments"; //$NON-NLS-1$

  /**
   * @param project_p
   * @param name_p
   * @param folder_p
   * @param melodymodellerFragmentFileName_p
   * @param diagramFragmentFileName_p
   *          can be <code>null</code> if optional.
   */
  public static void addCapellaFragment(IProject project_p, String name_p, String folder_p,
      String melodymodellerFragmentFileName_p, String diagramFragmentFileName_p) {
    try {
      IFolder folder = project_p.getFolder(FRAGMENTS_FOLDER);
      if (!folder.exists()) {
        folder.create(IResource.FORCE, true, null);
      }
      String folderName = folder_p + FRAGMENTS_FOLDER + ICommonConstants.SLASH_CHARACTER;
      TestHelper.copyFile(folderName + melodymodellerFragmentFileName_p, name_p + ICommonConstants.SLASH_CHARACTER
          + FRAGMENTS_FOLDER + ICommonConstants.SLASH_CHARACTER + melodymodellerFragmentFileName_p, UTF_8);
      if (null != diagramFragmentFileName_p) {
        TestHelper.copyFile(folderName + diagramFragmentFileName_p, name_p + ICommonConstants.SLASH_CHARACTER
            + FRAGMENTS_FOLDER + ICommonConstants.SLASH_CHARACTER + diagramFragmentFileName_p, UTF_8);
      }
      // Make sure the project is refreshed.
      ProjectHelper.refreshProject(project_p, new NullProgressMonitor());
    } catch (CoreException exception_p) {
      exception_p.printStackTrace();
      Assert.fail(exception_p.getMessage());
    }
  }

  /**
   * TO DO: rework on this method To solve problem with fragments containing
   * space character in their name <br>
   * copy fragments to the fragment file of the project
   * 
   * @param project_p
   *          , the project
   * @param fileName
   *          , name of the file to copy
   * @param plugin_p
   *          , name of the the plugin, where the file to copy is located in
   * @param fragmentFolder_p
   *          , name of the fragment folder
   */
  public static void copyFragmentFileWithSpace(IProject project_p, String fileName_p, String plugin_p,
      String fragmentFolder_p) {

    ProjectHelper.refreshProject(project_p, new NullProgressMonitor());

    String fileRelativePath_p = plugin_p + fragmentFolder_p + ICommonConstants.SLASH_CHARACTER + fileName_p;
    IFolder folder = project_p.getFolder(fragmentFolder_p);

    ProjectHelper.refreshProject(project_p, new NullProgressMonitor());

    if (!folder.exists()) {
      try {
        folder.create(true, true, new NullProgressMonitor());

      } catch (CoreException exception_p) {
        exception_p.printStackTrace();
      }
    }
    ProjectHelper.refreshProject(project_p, new NullProgressMonitor());

    writeFile(project_p.getName() + ICommonConstants.SLASH_CHARACTER + fragmentFolder_p
        + ICommonConstants.SLASH_CHARACTER + fileName_p, true, fileRelativePath_p);

    ProjectHelper.refreshProject(project_p, new NullProgressMonitor());

    GuiActions.flushASyncGuiThread();

  }

  /**
   * Copy a file in a destination folder.<br>
   * 
   * @param sourcefile_p
   *          Source file path relative to the plug-in, plug-in id included.<br>
   *          See {@link FileHelper#getFileFullUrl(String)} documentation.
   * @param destinationFile_p
   *          Destination file path relative to the plug-in, plug-in id
   *          included.<br>
   *          See {@link FileHelper#getFileFullUrl(String)} documentation.
   * @param encoding_p
   *          can be <code>null</code>. Example : <code>UTF-8</code>
   */
  public static void copyFile(String sourcefile_p, String destinationFile_p, String encoding_p) {
    // Preconditions.
    if ((null == sourcefile_p) || (null == destinationFile_p)) {
      return;
    }

    // Write file content.
    writeFile(destinationFile_p, true, sourcefile_p);

    GuiActions.flushASyncGuiThread();
  }

  /**
   * copy a file from a plugin location into the specified workspace file
   */
  static void writeFile(String filePath_p, boolean ensureFolders_p, String sourceFile_p) {
    try {

      // TODO find a better way to ensure file is copied successfully

      GuiActions.flushASyncGuiThread();

      URI source = URI.createPlatformPluginURI(sourceFile_p, false);
      URL url = FileLocator.resolve(new URL(source.toString()));

      URI aTarget = URI.createPlatformResourceURI(filePath_p, false);
      URL url2 = FileLocator.resolve(new URL(aTarget.toString()));

      File asource = new File(url.getFile());
      File target = new File(url2.getFile());

      if (url.getProtocol().equals("jar")) {//$NON-NLS-1$
        JarURLConnection jarConnection = (JarURLConnection) url.openConnection();
        InputStream is = jarConnection.getInputStream();
        FileOutputStream fos = new FileOutputStream(target);
        while (is.available() > 0) {
          fos.write(is.read());
        }
        fos.close();
        is.close();
      } else {
        copyFile(asource, target);
      }
      GuiActions.flushASyncGuiThread();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  static void copyFile(final File srcFile, final File destFile) throws IOException {
    FileInputStream fis = null;
    FileOutputStream fos = null;
    FileChannel iChannel = null;
    FileChannel oChannel = null;

    try {
      fis = new FileInputStream(srcFile);
      fos = new FileOutputStream(destFile);
      iChannel = fis.getChannel();
      oChannel = fos.getChannel();

      long size = iChannel.size();
      long length = 0;
      long pos = 0;

      while (pos < size) {
        long rr = size - pos;
        length = Math.min(100000, rr);
        long res = oChannel.transferFrom(iChannel, pos, length);
        if (res == 0) {
          break;
        }
        pos += res;
      }

    } finally {
      iChannel.close();
      oChannel.close();
      fis.close();
      fos.close();
    }

  }

  /**
   * Open given diagram resource i.e AIRD one. Note: Even if the method
   * signature suggests that the returned session will be open, it will NOT. You
   * need to call session.open() manually.
   * 
   * @param resource_p
   */
  public static Session openOrGetSession(IFile file_p) {
    Session result = null;

    try {
      if ((null == file_p) || !file_p.exists()) {
        throw new Exception("'" + file_p.getName() + "' doesn't exist"); //$NON-NLS-1$ //$NON-NLS-2$
      }
      String fileFullPath = file_p.getFullPath().toOSString();
      URI uri = URI.createPlatformResourceURI(fileFullPath, true);
      Collection<Session> allSessions = SessionManager.INSTANCE.getSessions();
      for (Session session : allSessions) {
        for (Resource res : session.getAllSessionResources()) {
          if (res.getURI().equals(uri)) {
            return session;
          }
        }
      }
      if (fileFullPath.endsWith("airfragment")) //$NON-NLS-1$
        Assert.fail("open Session on '" + fileFullPath + "' is not allowed");//$NON-NLS-1$ //$NON-NLS-2$

      // if there is no opened session, create a new one from the uri.
      result = SessionManager.INSTANCE.getSession(uri, new NullProgressMonitor());

    } catch (Exception exception_p) {
      exception_p.printStackTrace();
      Assert.fail(exception_p.getMessage());
    }
    return result;
  }

  public static AirdResource getSessionResource(Session session_p, IFile file_p) {
    String fileFullPath = file_p.getFullPath().toOSString();
    URI uri = URI.createPlatformResourceURI(fileFullPath, true);

    for (Resource res : session_p.getAllSessionResources()) {
      if (res.getURI().equals(uri)) {
        return (AirdResource) res;
      }
    }
    Assert.fail("There is no resource for the session");//$NON-NLS-1
    return null;
  }

  /**
   * Open given diagram resource i.e AIRD one.
   * 
   * @param resource_p
   */
  @Deprecated
  public static Session openOrGetSession(Resource resource_p) {
    Session result = null;
    // Precondition: given resource is not null.
    if (null == resource_p) {
      return result;
    }
    try {
      result = SessionManager.INSTANCE.getSession(resource_p.getURI(), new NullProgressMonitor());
    } catch (Exception exception_p) {
      exception_p.printStackTrace();
      Assert.fail(exception_p.getMessage());
    }
    return result;
  }

  /**
   * Get the Capella Execution manager.
   * 
   * @return a not <code>null</code> execution manager.
   */
  public static ExecutionManager getExecutionManager(EObject eObject) {
    return TransactionHelper.getExecutionManager(eObject);
  }

  /**
   * Get the Capella Execution manager.
   * 
   * @return a not <code>null</code> execution manager.
   */
  public static ExecutionManager getExecutionManager(Session session) {
    return TransactionHelper.getExecutionManager(session);
  }

  /**
   * Get the Capella Editing Domain.
   * 
   * @return a not <code>null</code> editing domain.
   * @deprecated
   */
  @Deprecated
  public static TransactionalEditingDomain getEditingDomain() {
    return new ArrayList<TransactionalEditingDomain>(ExecutionManagerRegistry.getInstance().getAllEditingDomains())
        .get(0);
  }

  /**
   * Get the Capella Editing Domain.
   * 
   * @return a not <code>null</code> editing domain.
   */
  public static SemanticEditingDomain getEditingDomain(EObject eobject) {
    return (SemanticEditingDomain) TransactionHelper.getEditingDomain(eobject);
  }

  /**
   * Get the Capella Editing Domain.
   * 
   * @return a not <code>null</code> editing domain.
   */
  public static SemanticEditingDomain getEditingDomain(Resource resource) {
    return (SemanticEditingDomain) TransactionHelper.getEditingDomain(resource);
  }

  public static Resource getResource(IFile file_p, EditingDomain domain) {
    return getResource(URI.createPlatformResourceURI(file_p.getFullPath().toString(), true), domain);
  }

  public static Resource getResource(URI uri, EditingDomain domain) {
    Resource resource = null;
    final Map<String, String> options = new HashMap<String, String>(1);
    options.put(XMLResource.OPTION_ENCODING, System.getProperty("file.encoding"));//$NON-NLS-1$
    try {
      resource = domain.getResourceSet().getResource(uri, true);
    } catch (Exception exception_p) {
      exception_p.printStackTrace();
      Assert.fail(exception_p.getMessage());
    }
    return resource;
  }

  /**
   * Get the EMF resource for given file.
   * 
   * @param file_p
   *          aird or melodymodeller file located in the workspace.
   * @return <code>null</code> if an error occurred.
   */
  @Deprecated
  public static Resource getResource(IFile file_p) {
    return getResource(URI.createPlatformResourceURI(file_p.getFullPath().toString(), true));
  }

  @Deprecated
  public static Resource getResource(URI uri) {
    Resource resource = null;
    final Map<String, String> options = new HashMap<String, String>(1);
    options.put(XMLResource.OPTION_ENCODING, System.getProperty("file.encoding"));//$NON-NLS-1$
    try {
      resource = getEditingDomain().getResourceSet().getResource(uri, true);
    } catch (Exception exception_p) {
      exception_p.printStackTrace();
      Assert.fail(exception_p.getMessage());
    }
    return resource;
  }

  /**
   * Get the semantic resource linked to given diagram resource (AIRD one).
   * 
   * @param diagramResource_p
   * @return <code>null</code> if not found.
   */
  public static Resource getSemanticResource(Session session_p) {
    Resource semanticResource = null;
    DAnalysis root = null;
    try {
      Resource diagramResource = session_p.getSessionResource();
      root = (DAnalysis) diagramResource.getContents().get(0);
    } catch (Exception exception_p) {
      exception_p.printStackTrace();
      Assert.fail(exception_p.getMessage());
      return semanticResource;
    }

    List<EObject> models = root.getModels();
    if (!models.isEmpty()) {
      EObject semanticElementRoot = models.get(0);
      semanticResource = semanticElementRoot.eResource();
    }
    return semanticResource;
  }

  /**
   * Get the semantic resource linked to given diagram resource (AIRD one).
   * 
   * @param diagramResource_p
   * @return <code>null</code> if not found.
   */
  @Deprecated
  public static Resource getSemanticResource(Resource diagramResource_p) {
    Resource semanticResource = null;
    DAnalysis root = null;
    try {
      root = (DAnalysis) diagramResource_p.getContents().get(0);
    } catch (Exception exception_p) {
      exception_p.printStackTrace();
      Assert.fail(exception_p.getMessage());
      return semanticResource;
    }

    List<EObject> models = root.getModels();
    if (!models.isEmpty()) {
      EObject semanticElementRoot = models.get(0);
      semanticResource = semanticElementRoot.eResource();
    }
    return semanticResource;
  }

  /**
   * Get the first matching element contained in given resource for given type.
   * 
   * @param resource_p
   * @param elementType_p
   */
  public static EObject getFirstMatchingObject(Resource resource_p, EClass elementType_p) {
    TreeIterator<EObject> allContents = resource_p.getAllContents();
    EObject element = null;
    // Iterate over contained elements to search for an EObject that the
    // eClass matches given one.
    while (allContents.hasNext()) {
      EObject eObject = allContents.next();
      if (eObject.eClass() == elementType_p) {
        element = eObject;
        break;
      }
    }
    return element;
  }

  /**
   * Create a diagram with given parameters.
   * 
   * @param semanticElement_p
   *          semantic element that the diagram is based on.
   * @param session_p
   *          the session.
   * @return <code>null</code> if an error occurred.
   * @see {@link DiagramHelper}
   */
  @Deprecated
  public static DRepresentation createDiagram(final EObject semanticElement_p, final Session session_p) {
    final DRepresentation[] diagram = { null };
    // Get selected viewpoints.
    Collection<Viewpoint> selectedViewpoints = session_p.getSelectedViewpoints(false);
    // Get descriptions.
    Collection<RepresentationDescription> descriptions = DialectManager.INSTANCE
        .getAvailableRepresentationDescriptions(selectedViewpoints, semanticElement_p);
    // Loop over representation descriptions, stop looping as soon as an
    // appropriate description is found.
    for (RepresentationDescription representationDescription : descriptions) {
      if (DialectManager.INSTANCE.canCreate(semanticElement_p, representationDescription)) {
        final RepresentationDescription description = representationDescription;
        getExecutionManager(semanticElement_p).execute(new AbstractReadWriteCommand() {
          public void run() {
            String semanticElementType = semanticElement_p.eClass().getName();
            diagram[0] = DialectManager.INSTANCE.createRepresentation(
                "New " + semanticElementType + " Diagram", semanticElement_p, description, session_p, //$NON-NLS-1$//$NON-NLS-2$
                new NullProgressMonitor());
            Assert.assertNotNull("Fail to create a diagram for " + semanticElementType, semanticElement_p); //$NON-NLS-1$
            SessionManager.INSTANCE.notifyRepresentationCreated(session_p);
          }
        });
        break;
      }
    }
    return diagram[0];
  }

  /**
   * Utility method that checks whether some semantics objects should have a
   * representation (or not) onto a diagram.
   * 
   * @param diagram_p
   *          the target {@link DDiagram}
   * @param list_p
   *          the list of semantic Object
   * @param shouldBeAvailable_p
   *          defines whether the objects contained into the list should have a
   *          representation or not on the target diagram
   */
  public static void assertCheckObjectOnDiagram(String testName_p, DDiagram diagram_p, List<EObject> list_p,
      boolean shouldBeAvailable_p) {

    String errMsg;
    EObject eObject = null;

    errMsg = shouldBeAvailable_p ? CommonTestMessages.objectRepresentationNotAvailableOnDiagram
        : CommonTestMessages.objectRepresentationStillAvailableOnDiagram;

    for (EObject current : list_p) {
      eObject = DiagramHelper.getOnDiagram(diagram_p, current);
      Assert.assertTrue(
          NLS.bind(errMsg, new Object[] {
              testName_p,
              current instanceof AbstractNamedElement ? ((AbstractNamedElement) current).getName() : current.eClass()
                  .getName(), diagram_p.getName() }), shouldBeAvailable_p ? eObject != null : eObject == null);
    }

    return;
  }

  /**
   * Utility method that checks whether some DNode semantics objects should have
   * a representation (or not) onto a diagram.
   * 
   * @param diagram_p
   *          the target {@link DDiagram}
   * @param list_p
   *          the list of semantic Object
   * @param shouldBeAvailable_p
   *          defines whether the objects contained into the list should have a
   *          representation or not on the target diagram
   */
  public static void assertCheckDNodeOnDiagram(String testName_p, DDiagram diagram_p, List<EObject> list_p,
      boolean shouldBeAvailable_p) {

    String errMsg;
    EObject eObject = null;

    errMsg = shouldBeAvailable_p ? CommonTestMessages.objectRepresentationNotAvailableOnDiagram
        : CommonTestMessages.objectRepresentationStillAvailableOnDiagram;

    for (EObject current : list_p) {
      eObject = DiagramHelper.getDDiagramElementByEClass(diagram_p, current, DiagramPackage.Literals.DNODE);
      Assert.assertTrue(
          NLS.bind(errMsg, new Object[] {
              testName_p,
              current instanceof AbstractNamedElement ? ((AbstractNamedElement) current).getName() : current.eClass()
                  .getName(), diagram_p.getName() }), shouldBeAvailable_p ? eObject != null : eObject == null);
    }

    return;
  }

  /**
   * Get an integer value from a String
   * 
   * @param value_p
   * @return
   */
  static public int getIntegerValue(String value_p) {
    int result = -1;

    try {
      result = Integer.parseInt(value_p);
    } catch (NumberFormatException exception_p) {
      Assert.fail(NLS.bind(HelperMessages.invalidInteger, value_p));
    }

    return result;
  }

  /**
   * Check the {@link EClass} of a given {@link EObject}
   * 
   * @param eObject_p
   *          the {@link EObject} to test
   * @param expectedEClass_p
   *          the expected {@link EClass}
   */
  static public void checkEObjectClass(EObject eObject_p, EClass expectedEClass_p, boolean typeStrictlyIndentical) {

    boolean test = typeStrictlyIndentical ? expectedEClass_p == eObject_p.eClass() : expectedEClass_p
        .isSuperTypeOf(eObject_p.eClass());

    if (!test) {

      String objectName = eObject_p instanceof AbstractNamedElement ? ((AbstractNamedElement) eObject_p).getName()
          : eObject_p.eClass().getName();

      String msg = typeStrictlyIndentical ? HelperMessages.wrongEObjectType
          : HelperMessages.wrongEObjectTypeWithInheritance;

      Assert.fail(NLS.bind(msg, new Object[] { objectName, eObject_p.eClass().getName(),
          expectedEClass_p.eClass().getName() }));

    }

    return;
  }

  /**
   * Return the first IFile under resource tree starting at 'root' that has a
   * given extension. Returns null if no matching file is found.
   * 
   * @param fileExtension
   *          the file extension, may be null or the empty string
   * @param root
   *          where to start searching, null to start at the workspace root
   * @see IResource.getFileExtension
   * @throws CoreException
   */
  public static IFile findFirstMatchingFile(final String fileExtension, IResource root) throws CoreException {
    final AtomicReference<IFile> result = new AtomicReference<IFile>();
    IResource scope = root != null ? root : ResourcesPlugin.getWorkspace().getRoot();
    scope.accept(new IResourceVisitor() {
      public boolean visit(IResource resource) {
        if (result.get() != null) {
          return false;
        }
        if (resource.getType() == IResource.FILE) {
          if (fileExtension == null) {
            if (((IFile) resource).getFileExtension() == null) {
              result.set((IFile) resource);
            }
          } else {
            if (fileExtension.equals(((IFile) resource).getFileExtension())) {
              result.set((IFile) resource);
            }
          }
        }
        return true;
      }
    });
    return result.get();
  }

  /**
   * set a project as multipart or not
   * 
   * @param project_p
   * @param value_p
   */
  public static void setReusableComponents(Project project_p, boolean value_p) {
    if (value_p) {
      CapellaProjectHelper.setProjectWithApproach(project_p, ProjectApproach.ReusableComponents);
    } else {
      CapellaProjectHelper.setProjectWithApproach(project_p, ProjectApproach.SingletonComponents);
    }
  }

  public static void copy(File sourceLocation, File targetLocation) throws IOException {
    if (sourceLocation.isDirectory()) {
      copyDirectory(sourceLocation, targetLocation);
    } else if (!targetLocation.isDirectory()) {
      Files.copy(sourceLocation, targetLocation);
    } else {
      File targetFile = new File(targetLocation.toString() + "/" + sourceLocation.getName()); //$NON-NLS-1$
      Files.copy(sourceLocation, targetFile);
    }
  }

  private static void copyDirectory(File source, File target) throws IOException {
    if (!target.exists()) {
      target.mkdir();
    }

    for (String f : source.list()) {
      copy(new File(source, f), new File(target, f));
    }
  }

  public static File convertToFile(IResource file) {
    return file.getRawLocation().makeAbsolute().toFile();
  }

}
