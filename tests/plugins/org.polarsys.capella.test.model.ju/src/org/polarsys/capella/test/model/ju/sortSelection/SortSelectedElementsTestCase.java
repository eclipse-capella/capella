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
package org.polarsys.capella.test.model.ju.sortSelection;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.model.ju.sortContent.SortContentTestCase;

/**
 * This test case tests if the Sort Selection works well on selected elements
 */
public class SortSelectedElementsTestCase extends SortContentTestCase {
  public static String PA__ROOT_PF = "d3c50bde-b286-4473-b731-3295cb9c0d87"; //$NON-NLS-1$ 
  public static String PA__ROOT_PF__D = "f4570354-f270-4f74-873d-fbeaa7c582be"; //$NON-NLS-1$ 
  public static String PA__ROOT_PF__B = "2e658398-2794-493d-ab6d-c3a0ca215c30"; //$NON-NLS-1$ 
  public static String PA__ROOT_PF__A = "739f78a4-58dc-4deb-a2f6-0596de138844"; //$NON-NLS-1$ 
  public static String PA__ROOT_PF__C = "bd5138b2-eff1-4344-9b61-b5bf1d98ca14"; //$NON-NLS-1$ 

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    ICapellaModel model = getTestModel(SortSelectionTestSuite.MODEL_NAME);
    IScope scope = new ScopeModelWrapper(model);
    EObject rootPF = IdManager.getInstance().getEObject(PA__ROOT_PF, scope);
    EObject pfA = IdManager.getInstance().getEObject(PA__ROOT_PF__A, scope);

    List<EObject> lstSelectedObjs = Arrays.asList(IdManager.getInstance().getEObject(PA__ROOT_PF__D, scope), IdManager
        .getInstance().getEObject(PA__ROOT_PF__B, scope), IdManager.getInstance().getEObject(PA__ROOT_PF__C, scope));

    EReference containmentReference = (EReference) pfA.eContainingFeature();
    GuiActions.sortSelection(lstSelectedObjs);
    List<String> idOrder = Arrays.asList(PA__ROOT_PF__B, PA__ROOT_PF__C, PA__ROOT_PF__A, PA__ROOT_PF__D);
    assertTrue("Children element are not in the right order after the sort of selected objects",
        checkChildrenOrder(rootPF, containmentReference, idOrder));
  }
}
