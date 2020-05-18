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
package org.polarsys.capella.test.model.ju.testcase.LCDecomposition;

import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionComponent;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionItem;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;

/**
 * This test case tests the check of internal interface for a decomposed component
 *
 */
public class LCDecompositionWithInternalInterface extends LCDecompositionTestCase {

  public static String LCDECOMPOSITION__LC_1 = "0c4006a8-a0f8-45a9-b4ee-d5807b3e1569"; //$NON-NLS-1$
  public static String LCDECOMPOSITION__RUNTIME_CREATED_INTERFACE = "ae0b93ee-f562-4fa6-a6ae-d7b8a279a254"; //$NON-NLS-1$

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    ICapellaModel model = getTestModel(MODEL_NAME);
    IScope scope = new ScopeModelWrapper(model);

    LogicalComponent lc1 = (LogicalComponent)IdManager.getInstance().getEObject(LCDECOMPOSITION__LC_1, scope);
    Interface runtimeCreatedInterface = (Interface)IdManager.getInstance().getEObject(LCDECOMPOSITION__RUNTIME_CREATED_INTERFACE, scope);
    
    DecompositionComponent decompositionComponent = new DecompositionComponent();
    decompositionComponent.setValue(lc1);
    //A decomposition item with a null value corresponds to a new interface
    DecompositionItem decompositionItem = new DecompositionItem();
    assertTrue("A new interface should be considered as an internal to the component", decompositionItem.isInternal());
    decompositionItem.setValue(runtimeCreatedInterface);
    decompositionItem.setParentComponent(decompositionComponent);
    assertTrue("An inteface created at runtime should be considered as an internal to the component", decompositionItem.isInternal());
  }
}
