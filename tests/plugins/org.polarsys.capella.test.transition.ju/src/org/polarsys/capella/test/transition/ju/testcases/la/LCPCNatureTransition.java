/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.transition.ju.testcases.la;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentPkgExt;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

public class LCPCNatureTransition extends TopDownTransitionTestCase {

  private static final String MODEL_NAME = "lc-to-pc-nature-transition"; //$NON-NLS-1$

  public static final String LA_3_1_ID = "30288654-bae6-4bc8-a7a5-f2528f9611e9"; //$NON-NLS-1$
  public static final String LA_3_ID = "faa4c7ce-31d0-49cc-8747-e7fe91e03e96"; //$NON-NLS-1$

  protected LogicalComponent la1;

  protected LogicalComponentPkg logicalComponentPkg;
  protected PhysicalComponentPkg physicalComponentPkg;

  protected Map<LogicalComponent, PhysicalComponentNature> expectedComponentToNature;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(MODEL_NAME);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    Session session = getSession(MODEL_NAME);
    SessionContext sessionContext = new SessionContext(session);

    la1 = sessionContext.getSemanticElement("2e04b442-1fb6-4aca-bfdb-bf36dc232775");

    logicalComponentPkg = sessionContext.getSemanticElement("db6418bb-f0e8-40e0-a8ee-ec55b7f29153");
    physicalComponentPkg = sessionContext.getSemanticElement("781143cf-9620-40de-89a6-9ec662da8b88");

    List<Component> logicalComponents = ComponentPkgExt.getAllSubDefinedComponents(logicalComponentPkg);
    Component logicalSystem = BlockArchitectureExt.getRootBlockArchitecture(logicalComponentPkg).getSystem();

    assertTrue(logicalComponents.remove(logicalSystem));
    assertEquals(8, logicalComponents.size());
    
    expectedComponentToNature = new HashMap<>();

    for (Component component : logicalComponents) {
      if (component instanceof LogicalComponent) {
        LogicalComponent logicalComponent = (LogicalComponent) component;
        String id = logicalComponent.getId();

        if (LA_3_ID.equals(id) || LA_3_1_ID.equals(id)) {
          expectedComponentToNature.put(logicalComponent, PhysicalComponentNature.NODE);
        } else {
          expectedComponentToNature.put(logicalComponent, PhysicalComponentNature.BEHAVIOR);
        }
      }
    }

  }

  
  // TODO refactor this in a single method that takes a predicate to test on all transitioned components
  // first method -> all noed
  // second method -> check map containment
  
  protected void performSingularTransition() {
    performLCtoPCTransition(Arrays.asList(la1));

    List<Component> transionedPhysicalComponents = ComponentPkgExt.getAllSubDefinedComponents(physicalComponentPkg);
    Component physicalSystem = BlockArchitectureExt.getRootBlockArchitecture(physicalComponentPkg).getSystem();

    assertTrue(transionedPhysicalComponents.remove(physicalSystem));
    assertEquals(1, transionedPhysicalComponents.size());

    Component component = transionedPhysicalComponents.get(0);
    assertTrue(component instanceof PhysicalComponent);

    PhysicalComponent transitionedComponent = (PhysicalComponent) component;

    assertEquals(la1, ProjectionTestUtils.getRealizedTargetElement(transitionedComponent));
    assertEquals(PhysicalComponentNature.NODE, transitionedComponent.getNature());
  }

  protected void performFullTransition() {
    performLCtoPCTransition(Arrays.asList(logicalComponentPkg));

    List<Component> transionedPhysicalComponents = ComponentPkgExt.getAllSubDefinedComponents(physicalComponentPkg);
    Component physicalSystem = BlockArchitectureExt.getRootBlockArchitecture(physicalComponentPkg).getSystem();

    assertTrue(transionedPhysicalComponents.remove(physicalSystem));
    assertEquals(8, transionedPhysicalComponents.size());

    for (Component component : transionedPhysicalComponents) {
      assertTrue(component instanceof PhysicalComponent);
      PhysicalComponent transitionedComponent = (PhysicalComponent) component;

      TraceableElement realizedTargetElement = ProjectionTestUtils.getRealizedTargetElement(transitionedComponent);
      assertTrue(realizedTargetElement instanceof LogicalComponent);
      
      LogicalComponent logicalComponent = (LogicalComponent) realizedTargetElement;
      PhysicalComponentNature expectedNature = expectedComponentToNature.get(logicalComponent);

      assertEquals(expectedNature, transitionedComponent.getNature());
    }
  }

  @Override
  public void performTest() throws Exception {
    performSingularTransition();
    performFullTransition();
  }

}
