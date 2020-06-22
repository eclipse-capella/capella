/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.xdfb;

import java.util.Arrays;
import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.framework.context.SessionContext;

public class FunctionalChainCycleDetection extends AbstractDiagramTestCase {

  private static final String MODEL_NAME = "opd-fcd-cycle-model"; //$NON-NLS-1$

  public static final String SDFB_A = "_bVwWA7FGEeqoD5VLzCQ3nw"; //$NON-NLS-1$
  public static final String SDFB_A_INVALID_CYCLE = "_iy6sGrFGEeqoD5VLzCQ3nw"; //$NON-NLS-1$
  public static final String SDFB_B = "__nnmo7FGEeqoD5VLzCQ3nw"; //$NON-NLS-1$
  public static final String SDFB_B_INVALID_CYCLE = "_Ls3k1rFLEeqoD5VLzCQ3nw"; //$NON-NLS-1$
  public static final String SDFB_DEEP = "_ho84wLFOEeq-EJ4697_U6Q"; //$NON-NLS-1$

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext sessionContext = new SessionContext(session);

    List<String> validDiagramNames = Arrays.asList(SDFB_A, SDFB_B, SDFB_DEEP);
    List<String> invalidDiagramsNames = Arrays.asList(SDFB_A_INVALID_CYCLE, SDFB_B_INVALID_CYCLE);

    testFunctionalChainStatus(sessionContext, validDiagramNames, true);
    testFunctionalChainStatus(sessionContext, invalidDiagramsNames, false);
  }

  private void testFunctionalChainStatus(SessionContext sessionContext, List<String> diagramNames, boolean valid) {

    for (String name : diagramNames) {
      DiagramContext diagramContext = DiagramContext.getDiagram(sessionContext, name);
      diagramContext.refreshDiagram();

      DDiagram diagram = diagramContext.getDiagram();
      DDiagramElement functionalChainElement = getFunctionalChainElement(diagram);
      assertNotNull(functionalChainElement);

      FunctionalChain functionalChain = (FunctionalChain) functionalChainElement.getTarget();
      assertEquals(valid, !FunctionalChainExt.containsACycle(functionalChain));

      String functionalChainElementName = functionalChainElement.getName();

      assertEquals(valid, !functionalChainElementName.contains("invalid"));
    }

  }

  private DDiagramElement getFunctionalChainElement(DDiagram diagram) {
    for (DDiagramElement element : diagram.getOwnedDiagramElements()) {
      if (element.getTarget() instanceof FunctionalChain) {
        return element;
      }
    }

    return null;
  }

  @Override
  protected String getRequiredTestModel() {
    return MODEL_NAME;
  }

}
