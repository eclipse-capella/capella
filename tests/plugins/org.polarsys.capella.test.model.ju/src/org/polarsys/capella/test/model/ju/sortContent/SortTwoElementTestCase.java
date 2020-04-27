/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
 * This test case tests if the Sort Content works well when the selected element has only 2 children
 */
public class SortTwoElementTestCase extends SortContentTestCase {
  public static String rootOA = "4bdf7d37-4ca3-495e-b90f-f2929b04af4d"; //$NON-NLS-1$
  public static String OA__OPERATIONAL_ACTIVITIES__ROOT_OA__A = "b79213e3-1825-4b29-ab6f-93ed872d5a67"; //$NON-NLS-1$ 
  public static String OA__OPERATIONAL_ACTIVITIES__ROOT_OA__B = "6606c1fd-8ab1-45cd-a68f-faa4a141dd4c"; //$NON-NLS-1$ 

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    ICapellaModel model = getTestModel(SortContentTestSuite.MODEL_NAME);
    IScope scope = new ScopeModelWrapper(model);
    EObject oa = IdManager.getInstance().getEObject(rootOA, scope);
    EObject oaA = IdManager.getInstance().getEObject(OA__OPERATIONAL_ACTIVITIES__ROOT_OA__A, scope);

    EReference containmentReference = (EReference) oaA.eContainingFeature();
    GuiActions.sortContent(oa);
    List<String> idOrder = Arrays
        .asList(OA__OPERATIONAL_ACTIVITIES__ROOT_OA__A, OA__OPERATIONAL_ACTIVITIES__ROOT_OA__B);
    assertTrue("Children element are not in the right order after the content sort",
        checkChildrenOrder(oa, containmentReference, idOrder));
  }
}
