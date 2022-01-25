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
package org.polarsys.capella.core.commands.preferences.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.internal.preferences.PreferencesService;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.commands.preferences.service.PropertyStore;
import org.polarsys.capella.core.commands.preferences.service.ScopedCapellaPreferencesStore;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.preferences.Activator;

public class PreferencesHelper {
  private PreferencesHelper() {

  }

  private static final Logger __logger = ReportManagerRegistry.getInstance()
      .subscribe(IReportManagerDefaultComponents.UI);

  public static final String CONFUGRATION_PROJECT_NATURE_ID = CapellaResourceHelper.CAPELLA_CONFIGURATION_PROJECT_NATURE;

  public static final String CAPELLA_PROJECT_NATURE_ID = CapellaResourceHelper.CAPELLA_PROJECT_NATURE;

  /**
   * @return
   */
  public static IProject getSelectedEclipseProject() {

    IProject sourceProject = getSelectedCapellaProject();

    try {
      IProject[] referencedProjects = sourceProject != null ? sourceProject.getReferencedProjects() : new IProject[] {};
      for (IProject referencedProject : referencedProjects) {
        return referencedProject;
      }

    } catch (Exception exception) {
      StringBuilder loggerMessage = new StringBuilder("could not found the selected resource"); //$NON-NLS-1$
      __logger.debug(loggerMessage.toString(), exception);
    }

    return sourceProject;
  }

  /**
   * @param project
   * @return
   */
  public static boolean hasConfigurationProject(IProject project) {
    boolean existe = false;
    try {
      if ((project.getReferencedProjects() != null) && (project.getReferencedProjects().length > 0)
          && project.getReferencedProjects()[0].isAccessible() && project.getReferencedProjects()[0].isOpen()) {
        existe = project.getReferencedProjects()[0].hasNature(CONFUGRATION_PROJECT_NATURE_ID);
      }
    } catch (CoreException exception) {
      return existe;
    }

    return existe;
  }

  /**
   * @param capellaProject
   * @return
   */
  public static IProject getReferencedProjectConfiguration(IProject capellaProject) {

    try {
      return capellaProject.getReferencedProjects()[0];
    } catch (CoreException exception) {
      StringBuilder loggerMessage = new StringBuilder("PreferencesHelper.getReferencedProjectConfiguration(..) _ "); //$NON-NLS-1$
      __logger.warn(loggerMessage.toString(), exception);
    }
    return null;

  }

  /**
   * @return
   */
  public static IProject getSelectedCapellaProject() {
    IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    IWorkbenchPage activePage = window != null ? window.getActivePage() : null;
    ISelection activeSelection = activePage != null ? activePage.getSelection() : null;

    IProject capellaProject = null;
    if ((activeSelection != null) && !activeSelection.isEmpty() && (activeSelection instanceof IStructuredSelection)) {

      Object a = ((IStructuredSelection) activeSelection).iterator().next();
      if (a instanceof IProject) {
        capellaProject = (IProject) a;
      } else if (a instanceof IFile) {
        capellaProject = ((IFile) a).getProject();
      } else {
        a = CapellaAdapterHelper.resolveSemanticObject(a);
        capellaProject = getProject(((EObject) a));
      }

    } else if ((activePage != null) && (activePage.getActiveEditor() != null)
        && (activePage.getActiveEditor().getEditorInput() instanceof URIEditorInput)) {
      URI uri = ((URIEditorInput) activePage.getActiveEditor().getEditorInput()).getURI();
      IFile resourceFile = getFileFromUri(uri);
      if ((null == resourceFile) && (activePage.getActiveEditor().getEditorInput() instanceof SessionEditorInput)
      // to avoid getting resource on a null session
          && ((SessionEditorInput) activePage.getActiveEditor().getEditorInput()).getStatus().isOK()) {
        // In Capella Team context, the URI is a cdo URI (can't be used to find a local resource file).
        // Get the session from the editor.
        Session session = ((SessionEditorInput) activePage.getActiveEditor().getEditorInput()).getSession();
        Resource sessionResource = session.getSessionResource();
        URI sessionResourceURI = sessionResource.getURI();
        resourceFile = getFileFromUri(sessionResourceURI);
      }
      // If the resource file is still null -> return a null project, preferences will be taken at workspace level.
      if (null != resourceFile) {
        capellaProject = resourceFile.getProject();
      }
    } else if ((activePage != null) && (activePage.getActiveEditor() != null)
        && (activePage.getActiveEditor().getEditorInput() != null)
        && (activePage.getActiveEditor().getEditorInput().getName() != null)) {
      capellaProject = getProjectByEditorName(activePage.getActiveEditor().getEditorInput().getName());
    }

    return capellaProject;
  }

  /**
   * @param name
   * @return
   */
  private static IProject getProjectByEditorName(String name) {
    IProject[] iProjects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
    for (IProject targetProject : iProjects) {
      try {
        if (targetProject.isOpen() && targetProject.isAccessible()
            && (CapellaResourceHelper.isCapellaProject(targetProject)) && targetProject.getName().equals(name)) {
          return targetProject;
        }
      } catch (Exception exception) {
        StringBuilder loggerMessage = new StringBuilder("PreferencesHelper.getProjectByEditorName(..) _ "); //$NON-NLS-1$
        __logger.error(loggerMessage.toString(), exception);
      }
    }
    return null;

  }

  /**
   * Return the project where is persisted given EMF object.
   * 
   * @param object
   * @return <code>null</code> if given object is not persisted.
   */
  public static IProject getProject(EObject object) {
    IProject result = null;

    Session session = new EObjectQuery(object).getSession();

    if (null != session) {
      Resource sessionResource = session.getSessionResource();
      URI sessionResourceURI = sessionResource.getURI();
      IFile resourceFile = getFileFromUri(sessionResourceURI);
      if (null != resourceFile) {
        result = resourceFile.getProject();
      }
    }

    if (result == null) {
      return EcoreUtil2.getProject(object);
    }

    return result;
  }

  /**
   * Convert the given EMF URI to an Eclipse file, if applicable
   * 
   * @param uri
   *          a non-null EMF URI
   * @return a potentially null Eclipse file
   */
  public static IFile getFileFromUri(URI uri) {
    IFile result = null;
    if ((null != uri) && uri.isPlatformResource()) {
      String platformString = uri.toPlatformString(true);
      result = (IFile) ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);
    }
    return result;
  }

  /**
   * @param targetProject
   * @return
   */
  public static ScopedPreferenceStore getProjectScopeOld(IProject targetProject) {
    IScopeContext scope = targetProject != null ? ScopedCapellaPreferencesStore.getProjectScope(targetProject) : null;
    try {
      if (scope != null) {
        IEclipsePreferences node = scope.getNode(Activator.getDefault().getBundle().getSymbolicName());
        node.flush();
        ScopedCapellaPreferencesStore.getProjectContexts().put(targetProject.getProject(), scope);
        return new ScopedPreferenceStore(scope, Activator.getDefault().getBundle().getSymbolicName());
      }
    } catch (Exception exception) {
      StringBuilder loggerMessage = new StringBuilder("Activator.getProjectScope(..) _ "); //$NON-NLS-1$
      __logger.error(loggerMessage.toString(), exception);

      return new ScopedPreferenceStore(scope, Activator.getDefault().getBundle().getSymbolicName());
    }

    return new ScopedPreferenceStore(InstanceScope.INSTANCE, Activator.getDefault().getBundle().getSymbolicName());

  }

  /**
   * @param targetProject
   * @return
   */
  public static ScopedPreferenceStore getProjectScope(IProject targetProject) {
    IScopeContext scope = targetProject != null ? ScopedCapellaPreferencesStore.getProjectScope(targetProject) : null;
    try {
      if (scope != null) {
        ScopedCapellaPreferencesStore.getProjectContexts().put(targetProject.getProject(), scope);
        return ScopedCapellaPreferencesStore.getInstance(Activator.getDefault().getBundle().getSymbolicName());
      }
    } catch (Exception exception) {
      StringBuilder loggerMessage = new StringBuilder("Activator.getProjectScope(..) _ "); //$NON-NLS-1$
      __logger.error(loggerMessage.toString(), exception);

      return new ScopedPreferenceStore(scope, Activator.getDefault().getBundle().getSymbolicName());
    }

    return new ScopedPreferenceStore(InstanceScope.INSTANCE, Activator.getDefault().getBundle().getSymbolicName());

  }

  /**
   * @param targetProject
   * @return
   */
  public static ScopedPreferenceStore getDefaultProjectScope(IProject targetProject) {
    IScopeContext scope = targetProject != null ? ScopedCapellaPreferencesStore.getProjectScope(targetProject) : null;
    try {
      if (scope != null) {
        IEclipsePreferences node = scope.getNode(Activator.getDefault().getBundle().getSymbolicName());
        node.flush();
        ScopedCapellaPreferencesStore.getProjectContexts().put(targetProject.getProject(), scope);
        return ScopedCapellaPreferencesStore.getInstance(Activator.getDefault().getBundle().getSymbolicName());
      }
    } catch (Exception exception) {
      StringBuilder loggerMessage = new StringBuilder("Activator.getProjectScope(..) _ "); //$NON-NLS-1$
      __logger.error(loggerMessage.toString(), exception);

      return new ScopedPreferenceStore(scope, Activator.getDefault().getBundle().getSymbolicName());
    }

    return new ScopedPreferenceStore(InstanceScope.INSTANCE, Activator.getDefault().getBundle().getSymbolicName());

  }

  /**
   * @param targetProject
   * @return
   */
  public static ScopedPreferenceStore getDefaultProjectScopeOld(IProject targetProject) {
    IScopeContext scope = targetProject != null ? ScopedCapellaPreferencesStore.getProjectScope(targetProject) : null;
    try {
      if (scope != null) {
        IEclipsePreferences node = scope.getNode(Activator.getDefault().getBundle().getSymbolicName());
        node.flush();
        ScopedCapellaPreferencesStore.getProjectContexts().put(targetProject.getProject(), scope);
        return new ScopedPreferenceStore(scope, Activator.getDefault().getBundle().getSymbolicName());
      }
    } catch (Exception exception) {
      StringBuilder loggerMessage = new StringBuilder("Activator.getProjectScope(..) _ "); //$NON-NLS-1$
      __logger.error(loggerMessage.toString(), exception);

      return new ScopedPreferenceStore(scope, Activator.getDefault().getBundle().getSymbolicName());
    }

    return new ScopedPreferenceStore(InstanceScope.INSTANCE, Activator.getDefault().getBundle().getSymbolicName());

  }

  /**
   * 
   */
  public static List<IPreferenceStore> getAllPreferencesStores() {
    List<IPreferenceStore> scopes = new ArrayList<IPreferenceStore>();
    IProject[] iProjects = ResourcesPlugin.getWorkspace().getRoot().getProjects();

    for (IProject targetProject : iProjects) {
      try {
        if (targetProject.isOpen() && targetProject.isAccessible()
            && (CapellaResourceHelper.isCapellaProject(targetProject))) {
          IScopeContext projectScope = ScopedCapellaPreferencesStore.getProjectScope(targetProject);
          scopes.add(new ScopedPreferenceStore(projectScope, Activator.getDefault().getBundle().getSymbolicName()));
        }
      } catch (Exception exception) {
        StringBuilder loggerMessage = new StringBuilder("PreferencesHelper.getAllStores(..) _ "); //$NON-NLS-1$
        __logger.error(loggerMessage.toString(), exception);
      }
    }
    return scopes;
  }

  /**
   * @param selectedCapellaProject_p
   * @return
   */
  public static boolean isConfigurationProject(IProject targetProject) {
    try {
      if (targetProject.isOpen() && targetProject.isAccessible()
          && (targetProject.getNature(PreferencesHelper.CONFUGRATION_PROJECT_NATURE_ID) != null)) {
        return true;
      }
    } catch (CoreException exception) {
      StringBuilder loggerMessage = new StringBuilder("PreferencesHelper.getAllStores(..) _ "); //$NON-NLS-1$
      __logger.error(loggerMessage.toString(), exception);
    }
    return false;
  }

  /**
   * @param selectedCapellaProject_p
   * @return
   */
  public static boolean isCapellaProject(IProject targetProject) {
    try {
      if (targetProject.isOpen() && targetProject.isAccessible()
          && (CapellaResourceHelper.isCapellaProject(targetProject))) {
        return true;
      }
    } catch (Exception exception) {
      StringBuilder loggerMessage = new StringBuilder("PreferencesHelper.getAllStores(..) _ "); //$NON-NLS-1$
      __logger.warn(loggerMessage.toString(), exception);
    }
    return false;
  }

  /**
   * @param selectionProvider
   * @return
   */
  public static IProject getCapellaProjectFromSelectedElement(ISelectionProvider selectionProvider) {
    ISelection activeSelection = selectionProvider.getSelection();
    IProject capellaProject = null;
    if ((activeSelection != null) && !activeSelection.isEmpty() && (activeSelection instanceof IStructuredSelection)) {

      Object a = ((IStructuredSelection) activeSelection).iterator().next();
      if (a instanceof IProject) {
        capellaProject = (IProject) a;
      } else {
        a = CapellaAdapterHelper.resolveSemanticObject(a);
        capellaProject = getProject((EObject) a);
      }

    }

    return capellaProject;
  }

  /**
   * 
   */
  public static String getActivePerpectiveId() {
    IWorkbench wb = PlatformUI.getWorkbench();
    if (wb != null && !wb.isStarting()) {
      IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
      IWorkbenchPage page = win != null ? win.getActivePage() : null;
      IPerspectiveDescriptor perspective = page != null ? page.getPerspective() : null;
      return perspective != null ? perspective.getId() : null;
    }
    return null;
  }

  /**
   * @param selectionProvider
   * @return
   */
  public static List<IFile> retrieveUserDefinedPreferenceFiles(ISelectionProvider selectionProvider,
      final String epf_file_extension) {

    IProject project = PreferencesHelper.getCapellaProjectFromSelectedElement(selectionProvider);

    final List<IFile> result = new ArrayList<IFile>();

    if (project == null) {
      return result;
    }
    try {
      project.accept(new IResourceVisitor() {
        @Override
        public boolean visit(IResource resource) throws CoreException {
          IProject prj = resource != null ? resource.getProject() : null;
          IProject configurationProject = (prj != null) && prj.isOpen() && (prj.getReferencedProjects().length > 0)
              ? prj.getReferencedProjects()[0]
              : null;
          if ((null != configurationProject)) {
            IFolder preferenceFolder = (configurationProject.getProject() != null)
                && (configurationProject.getProject().getFolder(".settings/") != null) //$NON-NLS-1$
                    ? configurationProject.getProject().getFolder(".settings/") //$NON-NLS-1$
                    : null;
            if ((preferenceFolder != null)) {
              getPreferencesFiles(result, configurationProject.getProject(),
                  configurationProject.getProject().getName(), epf_file_extension);
              return false;
            }
          }
          return true;
        }
      }, IResource.DEPTH_INFINITE, IResource.FILE);

    } catch (CoreException exception) {
      // Do nothing.
    }

    return result;
  }

  /**
   * @param container
   * @param projectName
   * @return List<IFile>
   */
  static List<IFile> getPreferencesFiles(List<IFile> result, IContainer container, String projectName,
      String epf_file_extension) {
    try {
      if (null != container) {
        for (IResource resource : container.members()) {
          if (resource instanceof IFile) {
            String ext = resource.getFileExtension();
            if (epf_file_extension.equals(ext)) {
              result.add((IFile) resource);
            }
          } else if (resource instanceof IFolder) {
            IFolder settingsFolder = (IFolder) resource;
            List<IFile> files = getFiles(settingsFolder, epf_file_extension);
            for (IFile preferencesFile : files) {
              java.net.URI preferenceFileURI = preferencesFile.getLocationURI();
              preferencesFile.createLink(preferenceFileURI, IResource.REPLACE, null);

            }

            getPreferencesFiles(result, settingsFolder, projectName, epf_file_extension);

          }
        }
      }
    } catch (CoreException exception) {
      exception.printStackTrace();
    }
    return result;
  }

  /**
   * Get all the files whose extension is <b>extension</b> and present in <b>folder</b>.
   * 
   * @param folder
   *          the IFolder that interest you.
   * @param extension
   *          the file extension. Use "*" for any extension.
   * @return all the IFile contained into this folder (with no limit in the level).
   */
  public static List<IFile> getFiles(IFolder folder, String extension) {
    List<IFile> result = new ArrayList<IFile>();
    try {
      IResource[] resources = folder.members();
      for (IResource resource : resources) {
        switch (resource.getType()) {
        case IResource.FILE:
          String fileExtension = resource.getFileExtension().toLowerCase();
          if (fileExtension.equals(extension) || fileExtension.equals("*")) { //$NON-NLS-1$
            result.add((IFile) resource);
          }
          break;
        case IResource.FOLDER:
          IFolder subFolder = (IFolder) resource;
          result.addAll(getFiles(subFolder, extension));
          break;
        default:
          break;
        }
      }
    } catch (CoreException e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * @param capellaPerspectiveId
   */
  public static void initializeCapellaPreferencesFromEPFFile() {
    CapellaPreferencesService service = new CapellaPreferencesService();
    try {
      Platform.getApplicationArgs();
      String epfFile = CapellaPreferencesService.getEPFPathFromApplicationArguments();
      if ((epfFile != null) && !epfFile.isEmpty()) {

        restoreAllPreferencesToDefault();// we need to restore default prefs before applying the ones stored in the
                                         // provided file
        FileInputStream fileInStream = new FileInputStream(new File(epfFile));
        service.initializePreferences(fileInStream);

      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (BackingStoreException e) {
      e.printStackTrace();
    }

  }

  /**
   * Restores all preferences to their default values
   * 
   * @throws BackingStoreException
   */
  public static void restoreAllPreferencesToDefault() throws BackingStoreException {
    Display.getDefault().syncExec(new Runnable() {

      @Override
      public void run() {
        @SuppressWarnings("restriction")
        IEclipsePreferences rootNode = PreferencesService.getDefault().getRootNode();
        final Preferences instNode = rootNode.node(InstanceScope.SCOPE);
        final Preferences configScope = rootNode.node(ConfigurationScope.SCOPE);
        final Preferences projectScope = rootNode.node(ProjectScope.SCOPE);
        try {
          clearAll(instNode);
          clearAll(configScope);
          clearAll(projectScope);
        } catch (BackingStoreException exception) {
          __logger.error(exception.getMessage());
        }
      }
    });

  }

  static void clearAll(Preferences node) throws BackingStoreException {
    node.clear();
    String[] names = node.childrenNames();
    for (String name : names) {
      // clearAll(node.node(name));
      PlatformUI.getPreferenceStore().setToDefault(name);

    }
  }

  /**
   * @param capellaPerspectiveId
   */
  public static void removeEclipseProjectReferences(String capellaPerspectiveId) {
    if (capellaPerspectiveId.equals(PreferencesHelper.getActivePerpectiveId())) {
      PreferenceManager pm = PlatformUI.getWorkbench().getPreferenceManager();
      pm.remove("org.eclipse.ui.propertypages.project.reference"); //$NON-NLS-1$
    }
  }

  /**
   * @param source
   * @param capellaProject
   * @param session
   * @return
   */
  public static boolean isNonReferencesCapellaProject(Object source, Project capellaProject, Session session) {
    boolean result = false;

    if ((source instanceof PropertyStore)) {
      PropertyStore property = (PropertyStore) source;
      IResource resource = property.getResource();
      if (resource != null) {
        IProject project = resource.getProject();
        if ((null != project) && isConfigurationProject(project)) {
          if ((null != capellaProject) && (null != session) && (null != session.getSessionResource())) {
            IFile resourceFile = getFileFromUri(session.getSessionResource().getURI());
            if (null != resourceFile) {
              IProject eclipseProject = resourceFile.getProject();
              result = isReferencedProject(project, eclipseProject);
            }
          }

        } else if ((project != null) && (capellaProject != null) && (getProject(capellaProject) != null)) {
          return project.equals(getProject(capellaProject));
        }
      }
    } else {
      result = true;
    }

    return result;
  }

  /**
   * @param project
   * @param eclipseProject
   * @return
   */
  private static boolean isReferencedProject(IProject project, IProject eclipseProject) {

    boolean existe = false;
    try {
      if ((eclipseProject.getReferencedProjects() != null) && (eclipseProject.getReferencedProjects().length > 0)
          && eclipseProject.getReferencedProjects()[0].isAccessible()) {
        for (IProject referencedProject : eclipseProject.getReferencedProjects()) {
          if (referencedProject.equals(project)) {
            existe = true;
            break;
          }
        }

      }
    } catch (CoreException exception) {
      return existe;
    }

    return existe;

  }

}
