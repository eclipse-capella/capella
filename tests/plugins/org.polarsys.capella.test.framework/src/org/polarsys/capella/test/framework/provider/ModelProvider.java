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
package org.polarsys.capella.test.framework.provider;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.junit.Assert;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.test.framework.api.AbstractProvider;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.api.ModelProviderHelper;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;

/**
 * Manages input models for test. Called by @see BasicTestCase and @see BasicTestSuite to load and unload models used in
 * test cases.<br>
 * This class handles read-only check for models that are used by several test cases without being unloaded.
 * 
 * @author Erwan Brottier
 */
public class ModelProvider extends AbstractProvider {

  public ModelProvider() {
  }

  /**
   * @return the test model that corresponds to the given identifiers.<br>
   *         Notice that the test model should have been required previously (@see method requireTestModel)
   */
  @Override
  public CapellaModel getTestModel(String relativeModelPath, BasicTestCase artefact) {
    Session session = getSessionForTestModel(relativeModelPath, artefact);
    CapellaModel model = (CapellaModel) ILibraryManager.INSTANCE.getModel(session.getTransactionalEditingDomain());
    return model;
  }

  /**
   * @return the session of the test model that corresponds to the given identifiers.<br>
   *         Notice that the test model should have been required previously (@see method requireTestModel).<br>
   *         Open the session if it was closed.
   */
  @Override
  public Session getSessionForTestModel(String relativeModelPath, BasicTestArtefact artefact) {
    Session session = AbstractProvider.getExistingSessionForTestModel(relativeModelPath, artefact);
    if (!session.isOpen()) {
      session.open(new NullProgressMonitor());
    }
    return session;
  }

  @Override
  public void setUp() throws Exception {
  }

  @Override
  public void tearDown() throws Exception {
  }

  @Override
  public String getProjectSuffix() {
    return ICommonConstants.EMPTY_STRING;
  }

  public boolean hasModelIdentifier2Owner(String modelIdentifier) {
    return modelIdentifier2Owner.keySet().contains(modelIdentifier);
  }

  @Override
  protected void importCapellaProject(Set<String> relativeModelsPath, BasicTestArtefact artefact) {
    for (String relativeModelPath : relativeModelsPath) {
      File sourceFolder = artefact.getFolderInTestModelRepository(relativeModelPath);
      ModelProviderHelper.getInstance().importCapellaProject(relativeModelPath, sourceFolder);
    }
  }

  @Override
  protected void removeCapellaProject(String relativeModelPath, BasicTestArtefact artefact, boolean eraseProject) {
    ModelProviderHelper.getInstance().removeCapellaProject(relativeModelPath, artefact, eraseProject);
  }
  
  @Override
  public Resource getAirdResource(Session session) {
    if (session != null) {
      Resource diagramResource = session.getSessionResource();
      return diagramResource;
    }
    return null;
  }

  @Override
  public IFile getAirdFileForLoadedModel(String relativeModelPath) {
    return IResourceHelpers.getEclipseProjectInWorkspace(relativeModelPath).getFile(
        relativeModelPath + "." + CapellaResourceHelper.AIRD_FILE_EXTENSION); //$NON-NLS-1$
  }

  @Override
  public Resource getSemanticResource(Session session) {
    Resource semanticResource = null;
    DAnalysis root = null;
    try {
      Resource diagramResource = session.getSessionResource();
      root = (DAnalysis) diagramResource.getContents().get(0);
    } catch (Exception exception_p) {
      exception_p.printStackTrace();
      Assert.fail(exception_p.getMessage());
      return semanticResource;
    }

    List<EObject> models = root.getModels();
    if (!models.isEmpty()) {
      for (EObject model : models)
        //Exclude AFM's Metadata resource
        if (model instanceof Project)
        {
          semanticResource = model.eResource();
          if (semanticResource != null)
            return semanticResource;
        }
    }
    return semanticResource;
  }

  @Override
  public boolean undoTestCaseChanges() {
    return true;
  }
}
