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
 * This test case tests if the Sort Content works well with elements whose name begin/end with spaces
 */
public class SortElementNamedWithSpaceTestCase extends SortContentTestCase {
  public static String LA__ROOT_LF = "88a616ad-0985-41fb-8a79-68c97886d003"; //$NON-NLS-1$ 
  public static String LA__ROOT_LF_____C = "aeef6d8a-0e17-4cd9-9125-d85eac91fbb4"; //$NON-NLS-1$ 
  public static String LA__ROOT_LF_____B = "ef3d0cff-d8f9-4fa4-89ec-c81b3d413797"; //$NON-NLS-1$ 
  public static String LA__ROOT_LF_____A = "e4bd8a9c-bf0a-46f9-ac85-6c167f717ff7"; //$NON-NLS-1$ 

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    ICapellaModel model = getTestModel(SortContentTestSuite.MODEL_NAME);
    IScope scope = new ScopeModelWrapper(model);
    EObject rootLF = IdManager.getInstance().getEObject(LA__ROOT_LF, scope);
    EObject lfA = IdManager.getInstance().getEObject(LA__ROOT_LF_____A, scope);

    EReference containmentReference = (EReference) lfA.eContainingFeature();
    GuiActions.sortContent(rootLF);
    List<String> idOrder = Arrays.asList(LA__ROOT_LF_____A, LA__ROOT_LF_____B, LA__ROOT_LF_____C);
    assertTrue("Children element are not in the right order after the content sort",
        checkChildrenOrder(rootLF, containmentReference, idOrder));
  }
}
