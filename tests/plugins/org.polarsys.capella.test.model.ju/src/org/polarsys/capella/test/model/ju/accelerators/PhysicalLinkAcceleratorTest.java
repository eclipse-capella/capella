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
package org.polarsys.capella.test.model.ju.accelerators;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.ISources;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.projection.exchanges.handlers.GenerateLinksHandler;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.model.ju.model.MiscModel;

public class PhysicalLinkAcceleratorTest extends MiscModel {

  @Override
  public void test() throws Exception {
    ICapellaModel model = getTestModel();
    IScope scope = new ScopeModelWrapper(model);

	testOnSystemArchitecture(scope);
    // PAB
    testOnPhysicalArchitecture(scope);
    
    // LAB
    testOnLogicalArchitecture(scope);
  }

  private void testOnSystemArchitecture(IScope scope) {
	// test that the accelerator create physical link from root system to an actor
	testPhysicalLinkAccelerator(SA__SYSTEM_CONTEXT__PART_SYSTEM__SYSTEM, scope, 1, SA_ACTOR_2,
				SA__SYSTEM_CONTEXT__PART_SYSTEM__SYSTEM);
	}

  private void testOnLogicalArchitecture(IScope scope) {
	// test that the accelerator create physical links
    testPhysicalLinkAccelerator(LA_1, scope, 2, LA_1_EXPECTED_PL_TARGET, LA_2);
    testPhysicalLinkAccelerator(LA_SYSTEM, scope, 1, LA_SYSTEM_EXPECTED_PL_TARGET, LA_SYSTEM);

    // test that the accelerator do not create physical links
    testPhysicalLinkAccelerator(LA_2, scope, 0, EMPTY_STRING, EMPTY_STRING);
    testPhysicalLinkAccelerator(LA_3, scope, 0, EMPTY_STRING, EMPTY_STRING);
  }

  private void testOnPhysicalArchitecture(IScope scope) {
    // test that accelerator create physical links
    testPhysicalLinkAccelerator(PA_1, scope, 2, PA_1_EXPECTED_PL_TARGET, PA_2);
    testPhysicalLinkAccelerator(PC_1, scope, 1, PC_1_EXPECTED_PL_TARGET, PC_1_CONTAINED);
    testPhysicalLinkAccelerator(PC_2, scope, 1, PC_2_EXPECTED_PL_TARGET, PC_2_CONTAINED);

	// test that accelerator do not create physical links
    testPhysicalLinkAccelerator(PA_2, scope, 0, EMPTY_STRING, EMPTY_STRING);
    testPhysicalLinkAccelerator(PA_3, scope, 0, EMPTY_STRING, EMPTY_STRING);

    //test that the accelerator do not create phsysical link on Behaviour Component
    testPhysicalLinkAccelerator(PA_6, scope, 1, PA_6_EXPECTED_PL_TARGET, PA_6);
    
    //test that the accelerator create a PL on actor as target if actor has no parent
    //and source has a parent
    testPhysicalLinkAccelerator(PA_7, scope, 1, PA_7_EXPECTED_PL_TARGET, PA_7);
    
    //test that the accelerator create a PL from an actor to an actor
    testPhysicalLinkAccelerator(PA_8, scope, 1, PA_8_EXPECTED_PL_TARGET, PA_8);

    // test that the accelerator do not create physical links on deployed Behaviour Component onto Behavior
    testPhysicalLinkAccelerator(PA_10, scope, 0, EMPTY_STRING, EMPTY_STRING);
  }

protected void testPhysicalLinkAccelerator(String selectedElementId, IScope scope, int expectedNumberPL,
      String expectedPLtargetId, String expectedPortAllocationTargetId) {
    Part inputElement = (Part) IdManager.getInstance().getEObject(selectedElementId, scope);
    Component component = (Component) inputElement.getAbstractType();
    assertTrue(component.getOwnedPhysicalLinks().isEmpty());
    try {
      new GenerateLinksHandler().execute(createExecutionEvent(inputElement));
    } catch (ExecutionException e) {
    }

    assertEquals(expectedNumberPL, component.getOwnedPhysicalLinks().size());

    if (expectedNumberPL != 0) {
      
      // check that the target of the PL is the expected one
      Part plTargetPart = (Part) IdManager.getInstance().getEObject(expectedPLtargetId, scope);
      Component expectedPLTarget = (Component) plTargetPart.getAbstractType();
      EObject plTarget = component.getOwnedPhysicalLinks().get(0).getLinkEnds().get(1).eContainer();
      assertEquals(expectedPLTarget, plTarget);
      
      // check that the CE was allocated
      PhysicalLink physicalLink = component.getOwnedPhysicalLinks().get(0);
      assertFalse(physicalLink.getAllocatedComponentExchanges().isEmpty());

      // check that the port allocation was created
      PhysicalPort port = physicalLink.getSourcePhysicalPort();
      assertFalse(port.getOwnedComponentPortAllocations().isEmpty());
      
      // check that the (component) target of the port allocation is the expected one
      ComponentPortAllocation portAllocation = port.getOwnedComponentPortAllocations().get(0);
      EObject portAllocationTargetComponent = portAllocation.getTargetElement().eContainer();
      
      Part target = (Part) IdManager.getInstance().getEObject(expectedPortAllocationTargetId, scope);
      Component expectedTargetComponent = (Component) target.getAbstractType();
      assertEquals(portAllocationTargetComponent, expectedTargetComponent);
    }

    try {
      new GenerateLinksHandler().execute(createExecutionEvent(inputElement));
    } catch (ExecutionException e) {
    }

    assertEquals(expectedNumberPL, component.getOwnedPhysicalLinks().size());
  }

  private ExecutionEvent createExecutionEvent(Object element) {
    EvaluationContext context = new EvaluationContext(null, new Object());
    Map<String, String> parameters = new HashMap<>();
    ExecutionEvent event = new ExecutionEvent(null, parameters, null, context);

    context.addVariable(ISources.ACTIVE_CURRENT_SELECTION_NAME, new StructuredSelection(element));

    return event;
  }
}