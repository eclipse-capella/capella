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
package org.polarsys.capella.test.framework.api;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.mdsofa.common.helper.ProjectHelper;
import org.polarsys.capella.test.framework.CapellaTestFrameworkPlugin;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.framework.helpers.TestHelper;
import org.polarsys.capella.test.framework.provider.ModelProvider;

public class ModelProviderHelper {
  private IModelProvider modelProvider;

  private ModelProviderHelper() {
    initExtensionListeners();
  }

  private static class SingletonHolder {
    private static final ModelProviderHelper INSTANCE = new ModelProviderHelper();
  }

  public static ModelProviderHelper getInstance() {
    return SingletonHolder.INSTANCE;
  }

  public static String MODEL_PROVIDER_EXT_POINT = "modelprovider";

  /**
   * Build the extension listener list.
   */
  private void initExtensionListeners() {
    for (IConfigurationElement configElement : ExtensionPointHelper
        .getConfigurationElements(CapellaTestFrameworkPlugin.PLUGIN_ID, MODEL_PROVIDER_EXT_POINT)) {
      modelProvider = (IModelProvider) ExtensionPointHelper.createInstance(configElement,
          ExtensionPointHelper.ATT_CLASS);
    }

    if (modelProvider == null) {
      modelProvider = new ModelProvider();
    }

  }

  public IModelProvider getModelProvider() {
    return modelProvider;
  }

  
  /**
   * Ill-construct legacy method. Use {@link #importCapellaProject(File)} instead.
   * 
   * @param unusedName
   * @param sourceFolder folder containing the project to import
   */
  @Deprecated
  public void importCapellaProject(String unusedName, File sourceFolder) {
    importCapellaProject(sourceFolder);
  }
  
  
  /**
   * Imports a Capella Project.
   * <p>
   * The project name will be the name of the folder.
   * </p>
   * <p>
   * Existing project is removed. If location is the same, files are overridden.
   * </p>
   * 
   * @param sourceFolder folder containing the project to import.
   * @return imported project
   */
  public IProject importCapellaProject(File sourceFolder) {
    String projectName = sourceFolder.getName();
    
    IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
    IProject oldProject = root.getProject(projectName); 
    if (oldProject.exists()) {
      try {
        oldProject.delete(IResource.NEVER_DELETE_PROJECT_CONTENT, null);
      } catch (CoreException e) {
        throw new IllegalStateException("Cannot override old project: " + projectName);
      }
    }
    
    File targetFolder = new File(root.getRawLocation().toString() + "/" + projectName + "/"); //$NON-NLS-1$ //$NON-NLS-2$
    try {
      TestHelper.copy(sourceFolder, targetFolder);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
    IProject project = TestHelper.createCapellaProject(projectName);
    AbstractProvider.normalizeEclipseProjectForTest(project);
    ProjectHelper.refreshProject(project, new NullProgressMonitor());
    
    return project;
  }

  public void removeCapellaProject(String relativeModelPath, BasicTestArtefact artefact, boolean eraseProject) {
    Session session = AbstractProvider.getExistingSessionForTestModel(relativeModelPath, artefact);
    if (session.isOpen()) {
      //GuiActions.saveSession(session);
      GuiActions.closeSession(session, false);
    }

    IProject eclipseProject = AbstractProvider.getEclipseProjectForTestModel(relativeModelPath, artefact);
    try {
      if (eraseProject) {
        GuiActions.eraseEclipseProject(eclipseProject);
      } else {
        GuiActions.deleteEclipseProject(eclipseProject);
      }
    } catch (CoreException e) {
      e.printStackTrace();
    }
  }

  public void removeCapellaProject(String relativeModelPath) {
    String projectName = new File(relativeModelPath).getName();
    IProject eclipseProject = IResourceHelpers.getEclipseProjectInWorkspace(projectName);
    try {
      GuiActions.eraseEclipseProject(eclipseProject);
    } catch (CoreException e) {
      e.printStackTrace();
    }
  }
}
