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
import org.polarsys.capella.core.data.cs.Component;
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

  public static String LCDECOMPOSITION__LA__LOGICAL_COMPONENT_1 = "6d9c518e-0510-4d0d-8568-fdc7a3aa46ea"; //$NON-NLS-1$

  @Override
  public void test() {
    ICapellaModel model = getTestModel(MODEL_NAME);
    IScope scope = new ScopeModelWrapper(model);

    LogicalComponent logicalSystem = (LogicalComponent) IdManager.getInstance()
        .getEObject(LCDECOMPOSITION__LA__LOGICAL_SYSTEM, scope);
    LogicalComponent lc1 = (LogicalComponent) IdManager.getInstance()
        .getEObject(LCDECOMPOSITION__LA__LOGICAL_COMPONENT_1, scope);

    LCDecompositionController controller = new LCDecompositionController();

    DecompositionModel decompositionModel = controller.createDecompositionModel(logicalSystem);
    DecompositionComponent sourceComponent = decompositionModel.getSourceComponent();
    Decomposition decomposition = decompositionModel.getDecompositions().get(0);
    List<DecompositionComponent> targetComponents = decomposition.getTargetComponents();

    List<DecompositionItem> items = targetComponents.get(0).getItems();

    checkDetachingItems(decompositionModel, sourceComponent, targetComponents, items);
    checkAttachingItems(decompositionModel, sourceComponent, targetComponents, items);

    checkRemoveTargetComponent(logicalSystem, lc1, decompositionModel, targetComponents);
  }

  private void checkRemoveTargetComponent(LogicalComponent logicalSystem, LogicalComponent lc1,
      DecompositionModel decompositionModel, List<DecompositionComponent> targetComponents) {
    int targetComponentsSize = targetComponents.size();

    assertTrue(logicalSystem.getOwnedLogicalComponents().contains(lc1));

    decompositionModel.removeTargetComponent(decompositionModel.getDecompositions().get(0), targetComponents.get(0));

    assertTrue(decompositionModel.finishDecomposition());
    assertEquals(targetComponentsSize - 1, targetComponents.size());
    assertFalse(logicalSystem.getOwnedLogicalComponents().contains(lc1));
  }

  private void checkAttachingItems(DecompositionModel decompositionModel, DecompositionComponent sourceComponent,
      List<DecompositionComponent> targetComponents, List<DecompositionItem> items) {
    sourceComponent.getItems().stream()
        .forEach(item -> decompositionModel.attachInterface(targetComponents.get(0), item));

    assertTrue(decompositionModel.finishDecomposition());
    assertFalse(items.isEmpty());
    sourceComponent.getItems().stream().forEach(item -> assertEquals(1, item.getStatus()));
    assertFalse(((Component) targetComponents.get(0).getValue()).getUsedInterfaces().isEmpty());
    assertFalse(((Component) targetComponents.get(0).getValue()).getOwnedCommunicationLinks().isEmpty());
  }

  private void checkDetachingItems(DecompositionModel decompositionModel, DecompositionComponent sourceComponent,
      List<DecompositionComponent> targetComponents, List<DecompositionItem> items) {
    assertFalse(items.isEmpty());

    sourceComponent.getItems().stream()
        .forEach(item -> decompositionModel.detachInterface(targetComponents.get(0), item));

    assertTrue(decompositionModel.finishDecomposition());
    assertTrue(items.isEmpty());
    sourceComponent.getItems().stream().forEach(item -> assertEquals(2, item.getStatus()));
    assertTrue(((Component) targetComponents.get(0).getValue()).getUsedInterfaces().isEmpty());
    assertTrue(((Component) targetComponents.get(0).getValue()).getOwnedCommunicationLinks().isEmpty());
  }

}
