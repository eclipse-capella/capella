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
package org.polarsys.capella.test.diagram.misc.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.core.model.preferences.ISynchronizationPreferences;
import org.polarsys.capella.core.platform.sirius.ui.actions.AllocationManagementAction;
import org.polarsys.capella.core.preferences.Activator;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;

public class AllocationManagementTest extends BasicTestCase {

  private static final String MODEL_NAME = "allocation-management";

  private FunctionalExchange fe1;
  private FunctionalExchange fe2;
  private FunctionalExchange fe3;
  private FunctionalExchange fe4;

  private ComponentExchange ce1;
  private ComponentExchange ce2;
  private ComponentExchange ce3;
  private ComponentExchange ce4;

  private PhysicalLink pl1;
  private PhysicalLink pl2;
  private PhysicalLink pl3;
  private PhysicalLink pl4;

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    Session session = getSession(MODEL_NAME);
    SessionContext sessionContext = new SessionContext(session);

    fe1 = sessionContext.getSemanticElement("57311489-631f-4f25-91fb-98ec98a841a6");
    fe2 = sessionContext.getSemanticElement("8dc491f1-e024-4556-8d94-ac7d0a6c7c5c");
    fe3 = sessionContext.getSemanticElement("568b896a-4b2a-48dd-ba95-e58b1ea0bc16");
    fe4 = sessionContext.getSemanticElement("c9c6c219-5908-4a86-97af-e7a903abb487");

    ce1 = sessionContext.getSemanticElement("4aaba939-4f3b-4a23-bf3a-fc76085c6bbc");
    ce2 = sessionContext.getSemanticElement("2958ba11-a790-4ce1-ac3a-8ea120efdd3c");
    ce3 = sessionContext.getSemanticElement("9e5ed466-aa26-41e9-88c1-63f565e5c338");
    ce4 = sessionContext.getSemanticElement("f3e3d39d-f52c-4adf-b0c1-82361892ea4c");

    pl1 = sessionContext.getSemanticElement("833b3d46-755d-46cf-9f98-294927a0cdcb");
    pl2 = sessionContext.getSemanticElement("c6914540-3b8a-4e2e-9b48-18284bfb7577");
    pl3 = sessionContext.getSemanticElement("4c021004-eaea-4c45-aa33-3abdd54b59fb");
    pl4 = sessionContext.getSemanticElement("49c0e7ee-a929-4184-a1eb-fb2f64b9a068");
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(MODEL_NAME);
  }

  @Override
  public void test() throws Exception {
    assertFunctionalExchangeToComponentExchangeAllocationInDeployedComponent();
    assertFunctionalExchangeToComponentExchangeAllocationInDeployedActor();

    assertComponentExchangeToPhysicalLinkAllocationInDeployedComponent();
    assertComponentExchangeToPhysicalLinkAllocationInInDeployedActor();
  }

  protected void assertFunctionalExchangeToComponentExchangeAllocationInDeployedComponent() {
    assertFunctionalExchangeToComponentExchangeAllocation(fe1, ce1, true);
    assertFunctionalExchangeToComponentExchangeAllocation(fe2, ce2, false);
  }

  protected void assertFunctionalExchangeToComponentExchangeAllocationInDeployedActor() {
    assertFunctionalExchangeToComponentExchangeAllocation(fe3, ce3, true);
    assertFunctionalExchangeToComponentExchangeAllocation(fe4, ce4, false);
  }

  protected void assertComponentExchangeToPhysicalLinkAllocationInDeployedComponent() {
    assertComponentExchangeToPhysicalLinkAllocation(ce1, pl1, true);
    assertComponentExchangeToPhysicalLinkAllocation(ce2, pl2, false);
  }

  protected void assertComponentExchangeToPhysicalLinkAllocationInInDeployedActor() {
    assertComponentExchangeToPhysicalLinkAllocation(ce3, pl3, true);
    assertComponentExchangeToPhysicalLinkAllocation(ce4, pl4, false);
  }

  protected void assertFunctionalExchangeToComponentExchangeAllocation(FunctionalExchange functionalExchange,
      ComponentExchange componentExchange, boolean syncFunctionPortsToComponentPorts) {

    Activator.getDefault().getPreferenceStore().putValue(
        ISynchronizationPreferences.PREFS_ALLOW_SYNC_COMPONENTPORT_TO_FUNCTIONPORT,
        String.valueOf(syncFunctionPortsToComponentPorts));

    assertEquals(syncFunctionPortsToComponentPorts,
        CapellaModelPreferencesPlugin.getDefault().isSynchronizationOfComponentPortToFunctionPortAllowed());

    TransactionHelper.getExecutionManager(functionalExchange).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        AllocationManagementAction.getInstance().allocatingFEsToComponentExchanges(Arrays.asList(functionalExchange),
            componentExchange);
      }
    });

    assertTrue(functionalExchange.getAllocatingComponentExchanges().contains(componentExchange));
    assertTrue(componentExchange.getAllocatedFunctionalExchanges().contains(functionalExchange));

    FunctionOutputPort sourceFunctionOutputPort = functionalExchange.getSourceFunctionOutputPort();
    FunctionInputPort targetFunctionInputPort = functionalExchange.getTargetFunctionInputPort();
    ComponentPort sourceComponentPort = (ComponentPort) componentExchange.getSourcePort();
    ComponentPort targetComponentPort = (ComponentPort) componentExchange.getTargetPort();

    if (syncFunctionPortsToComponentPorts) {
      assertTrue(sourceFunctionOutputPort.getAllocatorComponentPorts().contains(sourceComponentPort));
      assertTrue(targetFunctionInputPort.getAllocatorComponentPorts().contains(targetComponentPort));
      assertTrue(sourceComponentPort.getAllocatedFunctionPorts().contains(sourceFunctionOutputPort));
      assertTrue(targetComponentPort.getAllocatedFunctionPorts().contains(targetFunctionInputPort));

    } else {
      assertTrue(sourceFunctionOutputPort.getAllocatorComponentPorts().isEmpty());
      assertTrue(targetFunctionInputPort.getAllocatorComponentPorts().isEmpty());
      assertTrue(sourceComponentPort.getAllocatedFunctionPorts().isEmpty());
      assertTrue(targetComponentPort.getAllocatedFunctionPorts().isEmpty());
    }
  }
  
  protected void assertComponentExchangeToPhysicalLinkAllocation(ComponentExchange componentExchange,
      PhysicalLink physicalLink, boolean syncPhysicalPortsToComponentPorts) {

    Activator.getDefault().getPreferenceStore().putValue(
        ISynchronizationPreferences.PREFS_ALLOW_SYNC_PHYSICALPORT_TO_COMPONENTPORT_ON_PHYSICALLINK,
        String.valueOf(syncPhysicalPortsToComponentPorts));

    assertEquals(syncPhysicalPortsToComponentPorts, CapellaModelPreferencesPlugin.getDefault()
        .isSynchronizationOfPhysicalPortToComponentPortOnPhysicalLinkAllowed());

    TransactionHelper.getExecutionManager(componentExchange).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        AllocationManagementAction.getInstance().allocatingCEsToPhysicalLinks(Arrays.asList(componentExchange),
            physicalLink);
      }
    });

    assertTrue(componentExchange.getAllocatorPhysicalLinks().contains(physicalLink));
    assertTrue(physicalLink.getAllocatedComponentExchanges().contains(componentExchange));

    ComponentPort sourceComponentPort = (ComponentPort) componentExchange.getSourcePort();
    ComponentPort targetComponentPort = (ComponentPort) componentExchange.getTargetPort();
    PhysicalPort sourcePhysicalPort = physicalLink.getSourcePhysicalPort();
    PhysicalPort targetPhysicalPort = physicalLink.getTargetPhysicalPort();

    if (syncPhysicalPortsToComponentPorts) {
      sourceComponentPort.getAllocatingPhysicalPorts().contains(sourcePhysicalPort);
      targetComponentPort.getAllocatingPhysicalPorts().contains(targetPhysicalPort);
      sourcePhysicalPort.getAllocatedComponentPorts().contains(sourceComponentPort);
      targetPhysicalPort.getAllocatedComponentPorts().contains(targetComponentPort);

    } else {
      sourceComponentPort.getAllocatingPhysicalPorts().isEmpty();
      targetComponentPort.getAllocatingPhysicalPorts().isEmpty();
      sourcePhysicalPort.getAllocatedComponentPorts().isEmpty();
      targetPhysicalPort.getAllocatedComponentPorts().isEmpty();
    }

  }
}