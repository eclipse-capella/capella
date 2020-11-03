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
package org.polarsys.capella.test.libraries.ju.testcases.basic;

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
    return Arrays.asList("MyProject1", "MyLibrary1", "MyLibrary2");
  }

  @SuppressWarnings("nls")
  @Override
  public void test() {
    // -- SCENARIO -- //
    CapellaModel monProjet1 = getTestModel("MyProject1");
    CapellaModel maLibrairie1 = getTestModel("MyLibrary1");
    CapellaModel maLibrairie2 = getTestModel("MyLibrary2");
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
