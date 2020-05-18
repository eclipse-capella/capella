/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.misc.ju.testcases;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class BreakdownDiagramElements extends BasicTestCase {

  private static final String PROJECT_NAME = "component-breakdown";

  private Map<String, ExpectedResult> controlData;

  private class ExpectedResult {

    // number of diagram elements
    int nbOfExpectedElements;

    // should a graphical element exist of the diagram target
    boolean containsTarget;

    // should a graphical element exist for the system
    boolean containsSystem;

    public ExpectedResult(int nbOfExpectedElements, boolean containsTarget, boolean containsSystem) {
      this.nbOfExpectedElements = nbOfExpectedElements;
      this.containsTarget = containsTarget;
      this.containsSystem = containsSystem;
    }
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(PROJECT_NAME);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    controlData = getControlData();
  }

  protected Map<String, ExpectedResult> getControlData() {
    Map<String, ExpectedResult> data = new HashMap<>();

    // OA
    data.put("[OEBD] Root", new ExpectedResult(8, false, false));
    data.put("[OEBD] OE 1", new ExpectedResult(7, true, false));

    // LA
    data.put("[LCBD] Structure", new ExpectedResult(10, false, true));
    data.put("[LCBD] Logical System", new ExpectedResult(5, false, false));
    data.put("[LCBD] LC 6", new ExpectedResult(1, true, false));

    // PA
    data.put("[PCBD] Structure", new ExpectedResult(10, false, true));
    data.put("[PCBD] Physical System", new ExpectedResult(3, false, false));
    data.put("[PCBD] PC 1", new ExpectedResult(3, true, false));

    return data;
  }

  @Override
  public void test() throws Exception {
    Session session = getSession(PROJECT_NAME);
    assertNotNull(session);

    Collection<DRepresentationDescriptor> representationDescriptors = DialectManager.INSTANCE
        .getAllRepresentationDescriptors(session);

    for (DRepresentationDescriptor descriptor : representationDescriptors) {
      String name = descriptor.getName();
      ExpectedResult expectedResult = controlData.get(name);

      if (expectedResult != null) {
        DRepresentation representation = descriptor.getRepresentation();
        if (representation instanceof DSemanticDiagram) {
          DSemanticDiagram diagram = (DSemanticDiagram) representation;

          assertEquals(name + " contains a wrong number of elements", expectedResult.nbOfExpectedElements,
              diagram.getDiagramElements().size());

          EObject diagramTarget = diagram.getTarget();
          DDiagramElement diagramElementElement = DiagramServices.getDiagramServices().getDiagramElement(diagram,
              diagramTarget);

          assertEquals(name + " contains an error regarding the target element", expectedResult.containsTarget,
              diagramElementElement != null);

          BlockArchitecture rootBlockArchitecture = BlockArchitectureExt.getRootBlockArchitecture(diagramTarget);
          Component system = rootBlockArchitecture.getSystem();

          // there is no system for Operational Analysis
          if (system != null) {
            DDiagramElement systemElement = DiagramServices.getDiagramServices().getDiagramElement(diagram, system);

            assertEquals(name + " contains an error regarding the system element", expectedResult.containsSystem,
                systemElement != null);
          }
        }

      }
    }

  }
}
