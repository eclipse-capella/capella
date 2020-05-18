/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.libraries.model.CapellaModel;

public interface IModelProvider {

  void requireTestModel(List<String> relativeModelsPath, BasicTestArtefact artefact) throws Exception;

  void releaseTestModel(String relativeModelPath, BasicTestArtefact artefact);

  void setUp() throws Exception;

  void tearDown() throws Exception;

  Session getSessionForTestModel(String relativeModelPath, BasicTestArtefact artefact);

  CapellaModel getTestModel(String relativeModelPath, BasicTestCase basicTestCase);

  IFile getAirdFileForLoadedModel(String relativeModelPath);

  String getProjectSuffix();

  Resource getAirdResource(Session session);

  Resource getSemanticResource(Session session);
  
  /**
   * 
   * @return whether changes should be undone after each test case
   */
  boolean undoTestCaseChanges();
}
