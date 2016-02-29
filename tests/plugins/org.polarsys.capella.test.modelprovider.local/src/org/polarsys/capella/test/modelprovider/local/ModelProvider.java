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
package org.polarsys.capella.test.modelprovider.local;

import java.io.File;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.test.framework.api.AbstractProvider;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.api.ModelProviderHelper;


/**
 * Manages input models for test. Called by @see BasicTestCase and @see BasicTestSuite to load and unload models used in
 * test cases.<br>
 * This class handles read-only check for models that are used by several test cases without being unloaded.
 * 
 * @author Erwan Brottier
 */
public class ModelProvider extends AbstractProvider{

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
  protected void importCapellaProject(String relativeModelPath, BasicTestArtefact artefact) {
    File sourceFolder = artefact.getFolderInTestModelRepository(relativeModelPath);
    ModelProviderHelper.getInstance().importCapellaProject(relativeModelPath, sourceFolder);
  }
  
  @Override
  protected void removeCapellaProject(String relativeModelPath, BasicTestArtefact artefact, boolean eraseProject){
    ModelProviderHelper.getInstance().removeCapellaProject(relativeModelPath, artefact, eraseProject);
  }
}
