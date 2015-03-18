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
package org.polarsys.capella.test.model.ju.testcases.libraries;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * @author Erwan Brottier
 */
public class CapellaModel_unicity extends BasicTestCase {

  @SuppressWarnings("nls")
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("libraries/MonProjet1", "libraries/MaLibrairie1", "libraries/MaLibrairie2");
  }

  @SuppressWarnings("nls")
  @Override
  public void test() {
    // -- SCENARIO -- //
    CapellaModel monProjet1 = getTestModel("libraries/MonProjet1");
    CapellaModel maLibrairie1 = getTestModel("libraries/MaLibrairie1");
    CapellaModel maLibrairie2 = getTestModel("libraries/MaLibrairie2");
    // -- ORACLE -- //
    testIModelEquivalence(monProjet1);
    testIModelEquivalence(maLibrairie1);
    testIModelEquivalence(maLibrairie2);
  }
  
  private void testIModelEquivalence(CapellaModel model1) {
  	CapellaModel model2 = (CapellaModel) ILibraryManager.INSTANCE.getModel(model1.getProject(model1.getEditingDomain()));
  	Project p1 = model1.getProject(model1.getEditingDomain());
  	Project p2 = model2.getProject(model2.getEditingDomain());
  	assertTrue(p1 == p2);
  }
}
