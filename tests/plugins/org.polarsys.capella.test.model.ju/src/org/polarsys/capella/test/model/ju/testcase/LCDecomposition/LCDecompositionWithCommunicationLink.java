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
package org.polarsys.capella.test.model.ju.testcase.LCDecomposition;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.common.ui.wizards.LCDecompositionController;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionModel;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;

/**
 * The test model has a Logical System containing a Logical Component. Both of them have ACQUIRE link towards an
 * Exchange Item. Before the bug fix, the decomposition cannot finish due to an exception in DeleteCommand. The test
 * case should check that the decomposition operation can be finished
 */
public class LCDecompositionWithCommunicationLink extends LCDecompositionTestCase {
  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    ICapellaModel model = getTestModel(MODEL_NAME);
    IScope scope = new ScopeModelWrapper(model);

    EObject object = IdManager.getInstance().getEObject(LCDECOMPOSITION__LA__LOGICAL_SYSTEM, scope);

    LCDecompositionController controller = new LCDecompositionController();
    DecompositionModel dm = controller.createDecompositionModel((LogicalComponent) object);

    boolean canFinish = dm.finishDecomposition();

    assertTrue("The LC decomposition dialog cannot be finished", canFinish);
  }
}
