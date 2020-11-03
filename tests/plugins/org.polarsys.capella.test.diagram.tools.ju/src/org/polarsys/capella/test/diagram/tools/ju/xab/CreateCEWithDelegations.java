/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.xab;

import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CreateCEWithDelegations extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    XABDiagram diagram = XABDiagram.openDiagram(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA);
    diagram.createComponentExchangeWithDelegation(LA__LAB_LC1_PART, LA_7_1_PART, GenericModel.COMPONENT_EXCHANGE_1);
    EObject laStructure = context.getSemanticElement(LA_STRUCTURE);
    EObject laSystem = context.getSemanticElement(LA__LAB_LOGICAL_SYSTEM);
    EObject la7 = context.getSemanticElement(LA_7);
    if (laStructure instanceof LogicalComponentPkg) {
      Optional<ComponentExchange> ceOptional = ((LogicalComponentPkg) laStructure).getOwnedComponentExchanges().stream()
          .filter(ce -> ComponentExchangeExt.getSourceComponent(ce) == laSystem
              && ComponentExchangeExt.getTargetComponent(ce) == la7)
          .findAny();
      assertTrue(
          ceOptional.isPresent() && ComponentExchangeExt.getDelegatedComponentExchanges(ceOptional.get()).size() == 2);
    }
  }
}
