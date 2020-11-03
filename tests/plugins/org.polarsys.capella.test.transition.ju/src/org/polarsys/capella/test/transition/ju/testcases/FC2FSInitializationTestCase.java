/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.projection.scenario.fc2fs.FC2FSInitialization;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.EObjectHelper;

public class FC2FSInitializationTestCase extends BasicTestCase {

  private static final String TEST_MODEL = "fc2fs";
  private static final String OP_ID = "9083a21d-3cf8-450d-bbaa-0091f19fce38";
  private static final String FC_ID = "dccc19a7-be7f-4aae-b29b-22ad966637dc";
  public static final String FUNCTIONALCHAIN_2 = "ec81f42a-b463-4902-b5c4-6b4797a04252"; //$NON-NLS-1$

  private static final String[] EXPECTED_OAS_INSTANCE_ROLES_ORDER = new String[] { "OA1", "OA2", "OA3", "OA4", "OA5" };
  private static final String[] EXPECTED_FS_INSTANCE_ROLES_ORDER = new String[] { "SF1", "SF2", "SF4", "SF5", "SF3" };

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(TEST_MODEL);
  }

  @Override
  public void test() throws Exception {
    CapellaModel testModel = getTestModel(TEST_MODEL);
    doTest(testModel, OP_ID, 5, 5, 15, 15, 5, EXPECTED_OAS_INSTANCE_ROLES_ORDER);
    doTest(testModel, FC_ID, 5, 4, 12, 12, 4, EXPECTED_FS_INSTANCE_ROLES_ORDER);
    doTest(testModel, FUNCTIONALCHAIN_2, 6, 6, 18, 18, 6, null);
  }

  private void doTest(CapellaModel testModel, String funcChainID, int expectedNbOfInstRole, int expectedNbOfMsg,
      int excpectedNbOfEvents, int expectedNbOfInteractionFragments, int expectedNbOfTimeLapses,
      String[] expectedOrderOfInstRole) throws Exception {

    EObject object = EObjectHelper.getObject(testModel, funcChainID);
    assertNotNull(object);
    assertTrue(object instanceof FunctionalChain);

    FunctionalChain fc = (FunctionalChain) object;

    // Run the transformation
    FC2FSInitialization transformation = new FC2FSInitialization();
    transformation.execute(Arrays.asList(fc));

    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(fc);
    assertNotNull(architecture);

    // Check the Capability Pkg
    AbstractCapabilityPkg abstractCapabilityPkg = BlockArchitectureExt.getAbstractCapabilityPkg(architecture);
    assertNotNull(abstractCapabilityPkg);

    // Check the Capability
    AbstractCapability abstractCapability = checkAbstractCapability(abstractCapabilityPkg);
    assertNotNull(abstractCapability);

    // Check the Scenarios
    Scenario scenario = checkScenario(abstractCapability);
    assertNotNull(scenario);

    // Check the InstanceRoles
    assertFalse(scenario.getOwnedInstanceRoles().isEmpty());
    assertTrue(scenario.getOwnedInstanceRoles().size() == expectedNbOfInstRole);

    // Check the order of the InstanceRoles
    if (expectedOrderOfInstRole != null)
      assertTrue(Arrays.equals(expectedOrderOfInstRole, buildCurrentOrder(scenario.getOwnedInstanceRoles())));

    // Check the SequenceMessages
    assertFalse(scenario.getOwnedMessages().isEmpty());
    assertTrue(scenario.getOwnedMessages().size() == expectedNbOfMsg);

    for (SequenceMessage msg : scenario.getOwnedMessages()) {
      // SendingEnd
      MessageEnd sendingEnd = msg.getSendingEnd();
      assertNotNull(sendingEnd);
      assertNotNull(sendingEnd.getEvent());
      ;
      // ReceivingEnd
      MessageEnd receivingEnd = msg.getReceivingEnd();
      assertNotNull(receivingEnd);
      assertNotNull(receivingEnd.getEvent());
    }

    assertEquals(excpectedNbOfEvents, scenario.getOwnedEvents().size());
    assertEquals(expectedNbOfTimeLapses, scenario.getOwnedTimeLapses().size());
    assertEquals(expectedNbOfInteractionFragments, scenario.getOwnedInteractionFragments().size());

    // Check the TransfoLink
    assertFalse(scenario.getOwnedTraces().isEmpty());
    assertTrue(scenario.getOwnedTraces().size() == 1);
    Trace trace = scenario.getOwnedTraces().get(0);
    assertNotNull(trace.getSourceElement());
    assertNotNull(trace.getTargetElement());
  }

  private AbstractCapability checkAbstractCapability(AbstractCapabilityPkg abstractCapabilityPkg) throws Exception {
    if (abstractCapabilityPkg instanceof OperationalCapabilityPkg) {
      OperationalCapabilityPkg opCapaPkg = (OperationalCapabilityPkg) abstractCapabilityPkg;
      assertFalse(opCapaPkg.getOwnedOperationalCapabilities().isEmpty());
      assertTrue(opCapaPkg.getOwnedOperationalCapabilities().size() == 1);
      return opCapaPkg.getOwnedOperationalCapabilities().get(0);
    } else if (abstractCapabilityPkg instanceof CapabilityPkg) {
      CapabilityPkg capabilityPkg = (CapabilityPkg) abstractCapabilityPkg;
      assertFalse(capabilityPkg.getOwnedCapabilities().isEmpty());
      assertTrue(capabilityPkg.getOwnedCapabilities().size() == 1);
      return capabilityPkg.getOwnedCapabilities().get(0);
    }
    return null;
  }

  private Scenario checkScenario(AbstractCapability abstractCapability) {
    if (abstractCapability instanceof OperationalCapability) {
      OperationalCapability opCapa = (OperationalCapability) abstractCapability;
      assertFalse(opCapa.getOwnedScenarios().isEmpty());
      assertTrue(opCapa.getOwnedScenarios().size() == 1);
      return opCapa.getOwnedScenarios().get(0);
    } else if (abstractCapability instanceof Capability) {
      Capability capability = (Capability) abstractCapability;
      assertFalse(capability.getOwnedScenarios().isEmpty());
      return capability.getOwnedScenarios().get(capability.getOwnedScenarios().size() - 1);
    }
    return null;
  }

  private String[] buildCurrentOrder(EList<InstanceRole> instanceRoles) {
    List<String> result = new ArrayList<>();
    for (InstanceRole instRole : instanceRoles) {
      result.add(instRole.getName());
    }
    return result.toArray(new String[] {});
  }
}
