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
package org.polarsys.capella.test.diagram.misc.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.ProjectApproach;
import org.polarsys.capella.core.sirius.analysis.refresh.extension.ComponentArchitectureBlankRefreshExtension;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * We ensure here that the target of the specified diagram is updated according to business rules.
 * For more details see {@link ComponentArchitectureBlankRefreshExtension};
 *
 */
public class DiagramTargetUpdateDuringRefreshTest extends BasicTestCase {

  private static final String MODEL_NAME = "diagrams-having-parts-as-targets";
  private static final String PROJECT_ID = "e712857e-aeec-49de-8bb2-5536c4fb6dd3";
  private static final List<String> DIAGRAM_NAMES = Arrays.asList("[LAB] Logical System", "[PAB] Physical System", "[EAB] System");

  private Session session;
  private Project project;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    
    this.session = getSession(MODEL_NAME);
    assertNotNull(this.session);
    SessionContext context = new SessionContext(session);

    this.project = context.getSemanticElement(PROJECT_ID);
    assertNotNull(this.project);   
  }

  @Override
  public void test() throws Exception {
    ensureThatTargetRemainsUnchaingedInMultiPartMode();
    ensureThatTargetIsUpdatedInMonoPartMode();
  }

  private void ensureThatTargetRemainsUnchaingedInMultiPartMode() {
    
    ProjectApproach originalApproach = CapellaProjectHelper.getProjectApproach(this.project);
    
    TestHelper.setProjectApproach(this.project, ProjectApproach.ReusableComponents);
    
    for (String diagramName : DIAGRAM_NAMES) {
      DRepresentation representation = DiagramHelper.getDRepresentation(session, diagramName);

      assertTrue(representation instanceof DSemanticDiagram);
      DSemanticDiagram diagram = (DSemanticDiagram) representation;

      EObject initialTarget = diagram.getTarget();
      assertTrue(initialTarget instanceof Part);

      DiagramHelper.refreshDiagram(diagram);

      EObject updatedTarget = diagram.getTarget();
      assertEquals(initialTarget, updatedTarget);
    }
    
    TestHelper.setProjectApproach(this.project, originalApproach);
  }

  private void ensureThatTargetIsUpdatedInMonoPartMode() {
    ProjectApproach originalApproach = CapellaProjectHelper.getProjectApproach(this.project);
    
    TestHelper.setProjectApproach(this.project, ProjectApproach.SingletonComponents);
    
    for (String diagramName : DIAGRAM_NAMES) {
      DRepresentation representation = DiagramHelper.getDRepresentation(session, diagramName);

      assertTrue(representation instanceof DSemanticDiagram);
      DSemanticDiagram diagram = (DSemanticDiagram) representation;

      EObject initialTarget = diagram.getTarget();
      assertTrue(initialTarget instanceof Part);

      Part part = (Part) initialTarget;
      AbstractType type = part.getAbstractType();

      DiagramHelper.refreshDiagram(diagram);

      EObject updatedTarget = diagram.getTarget();
      assertEquals(type, updatedTarget);
    }
    
    TestHelper.setProjectApproach(this.project, originalApproach);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(MODEL_NAME);
  }

}
