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
package org.polarsys.capella.test.framework.api;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.libraries.model.CapellaModel;

public interface IModelProvider {
  
  void requireTestModel(String relativeModelPath, BasicTestArtefact artefact) throws Exception;
  
  void releaseTestModel(String relativeModelPath, BasicTestArtefact artefact);
  
  void setUp() throws Exception;
  
  void tearDown() throws Exception;
  
  Session getSessionForTestModel(String relativeModelPath, BasicTestArtefact artefact);
  
  CapellaModel getTestModel(String relativeModelPath, BasicTestCase basicTestCase);
  
  String getProjectSuffix();
}