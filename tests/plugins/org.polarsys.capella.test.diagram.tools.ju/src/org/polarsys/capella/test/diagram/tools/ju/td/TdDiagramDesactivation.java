/*******************************************************************************
 * Copyright (c) 2026 Obeo.
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
package org.polarsys.capella.test.diagram.tools.ju.td;

import java.util.Map;

import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.TDDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * Tests the impossibility of Traceability diagram creation when .
 * 
 * @author nicolas.peransin@obeo.fr
 */
public class TdDiagramDesactivation extends AbstractDiagramTestCase {

  protected SessionContext context;
  protected Project root;
  protected TDDiagram diagram;

  private static final String PROJECT_ID = "d01a1235-0b3b-4e9c-95c6-2f6c09bc3d00"; //$NON-NLS-1$

  @Override
  public String getRequiredTestModel() {
    return EditSimpleTdDiagram.REQUIRED_TEST_MODEL;
  }

  @Override
  public void test() throws Exception {
    TDDiagram.enableTraceabilityCapacity(false);
    context = new SessionContext(getSession(getRequiredTestModel()));
    root = context.getSemanticElement(PROJECT_ID);

    var diagramTarget = ModelQueryHelper.getLogicalSystem(root);

    Map.of("Simple", false, //$NON-NLS-1$
        "Hierarchical", true //$NON-NLS-1$
    ).forEach((modeName, mode) -> {
      boolean blocked = false;
      try {
        TDDiagram.openNewDiagram(context, diagramTarget, mode);
      } catch (AssertionError e) {
        blocked = true;
      }
      if (!blocked) {
        fail(NLS.bind("Creation of {0} Traceability diagram must be blocked when Traceability Capacity is off.", //$NON-NLS-1$
            modeName));
      }
    });
  }

}
