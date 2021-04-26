/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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

import java.util.List;

import org.polarsys.capella.core.common.ui.wizards.LCDecompositionController;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.ui.toolkit.decomposition.Decomposition;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionComponent;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionItem;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionModel;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;

/**
 * The test model has a Logical System containing a Logical Component. Both of them have ACQUIRE link towards an
 * Exchange Item and use an Interface. The test checks if the link and the interface it's correctly detached/ attached
 * to the component from LCDecomposition Wizard Also, it checks if removing target component works
 */
public class LCDecompositionAttachDetachCommunicationLinkInterface extends LCDecompositionTestCase {

  @Override
  public void test() {
    ICapellaModel model = getTestModel(MODEL_NAME);
    IScope scope = new ScopeModelWrapper(model);

    LogicalComponent logicalSystem = (LogicalComponent) IdManager.getInstance()
        .getEObject(LCDECOMPOSITION__LA__LOGICAL_SYSTEM, scope);

    LCDecompositionController controller = new LCDecompositionController();

    DecompositionModel decompositionModel = controller.createDecompositionModel(logicalSystem);
    DecompositionComponent sourceComponent = decompositionModel.getSourceComponent();
    Decomposition decomposition = decompositionModel.getDecompositions().get(0);
    List<DecompositionComponent> targetComponents = decomposition.getTargetComponents();
    sourceComponent.getItems().stream()
        .forEach(x -> checkAttachingDetachingItems(decompositionModel, x, targetComponents.get(0)));

    int targetComponentsSize = targetComponents.size();
    decompositionModel.removeTargetComponent(decompositionModel.getDecompositions().get(0), targetComponents.get(0));
    assertEquals(targetComponentsSize - 1, targetComponents.size());
  }

  private void checkAttachingDetachingItems(DecompositionModel model, DecompositionItem item,
      DecompositionComponent decompositionComponent) {

    List<DecompositionItem> items = decompositionComponent.getItems();
    int itemsSize = items.size();
    assertFalse(items.isEmpty());

    model.detachInterface(decompositionComponent, item);

    assertEquals(itemsSize - 1, items.size());
    assertEquals(2, item.getStatus());

    model.attachInterface(decompositionComponent, item);

    assertEquals(itemsSize, items.size());
    assertEquals(1, item.getStatus());
  }

}
