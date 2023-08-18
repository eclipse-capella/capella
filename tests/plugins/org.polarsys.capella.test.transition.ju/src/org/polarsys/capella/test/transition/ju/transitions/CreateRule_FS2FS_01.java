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
package org.polarsys.capella.test.transition.ju.transitions;

import java.util.Arrays;
import java.util.List;

import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test Scenario transition CES2CIS
 * 
 * * - Rename OperationalActivityPkg from Operational Analysis to Operational Activities - Create OperationalActivity
 * into Operational Activities named OA1 - Create OperationalActivity into OA1 named OA11 - Create FunctionalExchange
 * into OA11 named FE111 from to - Create OperationalActivity into OA1 named OA12 - Create FunctionalExchange into OA12
 * named FE121 from to - Rename OperationalCapabilityPkg from Operational Analysis to Operational Capabilities - Create
 * OperationalCapability into Operational Capabilities named OC1 - Create Scenario into OC1 named S11 -> Create
 * InstanceRole IR111 covering OA11 -> Create InstanceRole IR112 covering OA12 -> Start ME111 for message SM111
 * SYNCHRONOUS_CALL from (IR111) -> Message SM111 linked to (FE121) -> End ME112 for message SM111 SYNCHRONOUS_CALL from
 * (IR112) -> Start execution E111 from (ME112) -> Start ME113 for message SM112 REPLY from (IR112) -> Message SM112
 * linked to (FE121) -> End execution E111 for (ME113) -> End ME114 for message SM112 REPLY from (IR111) -> Start ME115
 * for message SM113 ASYNCHRONOUS_CALL from (IR112) -> Message SM113 linked to (FE111) -> End ME116 for message SM113
 * ASYNCHRONOUS_CALL from (IR111) -> Start execution E112 from (ME116) -> End execution E112 for (SM113) -> Start ME118
 * for message SM114 SYNCHRONOUS_CALL from (IR112) -> Message SM114 linked to (FE111) -> End ME119 for message SM114
 * SYNCHRONOUS_CALL from (IR111) -> Start execution E113 from (ME119) -> Start ME1110 for message SM115 REPLY from
 * (IR111) -> Message SM115 linked to (FE111) -> End execution E113 for (ME1110) -> End ME1111 for message SM115 REPLY
 * from (IR112) -> Start ME1112 for message SM116 ASYNCHRONOUS_CALL from (IR111) -> Message SM116 linked to (FE121) ->
 * End ME1113 for message SM116 ASYNCHRONOUS_CALL from (IR112) -> Start execution E114 from (ME1113) -> End execution
 * E114 for (SM116)
 * 
 * * Expected Result:\
 *
 * OA scenario should be transitioned to FA scenario
 * 
 */
public class CreateRule_FS2FS_01 extends TopDownTransitionTestCase {
  private String id_s11 = "f11b4103-4d3e-4e56-861d-8e4e23ab56a0";
  private String id_sm111 = "cc430bc6-40e3-48ef-8147-68bd2c5f4d76";
  private String id_sm112 = "0550532e-8e91-4499-ae27-ba89dfcc689a";
  private String id_sm113 = "b0b2c406-8b0a-4a50-9005-26cffce4a45d";
  private String id_sm114 = "7873aa47-48a0-49c3-9a07-4287aabf3891";
  private String id_sm115 = "17b728f4-6ce4-4df3-89b4-b57a3fcae876";
  private String id_sm116 = "a04f4f76-3f4b-4c49-a0da-27d0c2a5432b";
  private String id_ee117 = "742d6d63-16ae-4892-937d-f626bd93a8b4";
  private String id_me118 = "f07ba6d2-76c2-42f5-9ae2-e4b6cd190012";
  private String id_me119 = "f43a3b2f-2f12-41e9-8dc2-1eaa8589f05b";
  private String id_me1110 = "9969b676-8176-4aaf-b5b6-670c2a7e2e9f";
  private String id_me1111 = "e7b91b0e-1310-43b9-839a-7e8a7aa80fa7";
  private String id_me1112 = "acf730f4-11ec-4ca9-8c60-bb312435f438";
  private String id_me1113 = "497ea595-f824-406a-9291-1bfd167e4405";
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("CreateRule_Scenario");
  }

  @Override
  public void performTest() throws Exception {
    step1();
  }

  private void step1() {
    performFStoFSTransition(Arrays.asList(getObject(id_s11)));
    Scenario sc = (Scenario) mustBeTransitioned(id_s11);
    assertTrue(NLS.bind(Messages.ShouldBeA, "SC1", "FUNCTIONAL"), sc.getKind() == ScenarioKind.FUNCTIONAL);

    mustBeTransitioned(id_sm111);
    mustBeTransitioned(id_sm111);
    mustBeTransitioned(id_sm112);
    mustBeTransitioned(id_sm113);
    mustBeTransitioned(id_sm114);
    mustBeTransitioned(id_sm115);
    mustBeTransitioned(id_sm116);
    mustBeTransitioned(id_ee117);
    mustBeTransitioned(id_me118);
    mustBeTransitioned(id_me119);
    mustBeTransitioned(id_me1110);
    mustBeTransitioned(id_me1111);
    mustBeTransitioned(id_me1112);
    mustBeTransitioned(id_me1113);
  }

}
