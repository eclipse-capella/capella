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
package org.polarsys.capella.test.model.ju.sortContent;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.helpers.GuiActions;

/**
 * This test case tests if the Sort Content works well with elements whose name begin with capitalized letter
 */
public class SortCapitalizedElementTestCase extends SortContentTestCase {
  public static String SA__ROOT_SF = "9c920182-4ebc-4d33-9fd5-385497f874b0"; //$NON-NLS-1$ 
  public static String SA__ROOT_SF__C = "4207a0df-e0d0-460a-be15-4aafc54b84ae"; //$NON-NLS-1$ 
  public static String SA__ROOT_SF__B = "5d313f03-6165-4232-97de-aa31b5f8ffdd"; //$NON-NLS-1$ 
  public static String SA__ROOT_SF__A = "8eac0ce0-db49-4a19-908b-5300e9db4e85"; //$NON-NLS-1$ 

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    ICapellaModel model = getTestModel(SortContentTestSuite.MODEL_NAME);
    IScope scope = new ScopeModelWrapper(model);
    EObject rootSF = IdManager.getInstance().getEObject(SA__ROOT_SF, scope);
    EObject sfA = IdManager.getInstance().getEObject(SA__ROOT_SF__A, scope);

    EReference containmentReference = (EReference) sfA.eContainingFeature();
    GuiActions.sortContent(rootSF);
    List<String> idOrder = Arrays.asList(SA__ROOT_SF__A, SA__ROOT_SF__B, SA__ROOT_SF__C);
    assertTrue("Children element are not in the right order after the content sort",
        checkChildrenOrder(rootSF, containmentReference, idOrder));
  }
}
