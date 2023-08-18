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

import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test the functional transition - CreateRule-SF01
 * 
 * <pre>
 * Model is created with the following elements�
 * - Create SF1, SF2 SF3 SF4 SF5
 * - Create FE8 FE9 FE10 FE11 between functions
 * - Create FC6 (SF1 FE8 SF2 FE9 SF3 FE10 OR(((SF4 FE11 SF5)), SF4) 
 * - Create FC7 (SF4 FE11 SF5)
 * 
 * 
 * Expected Result:\
 * -  A functional transition on root should transition all elements except FC7
 * </pre>
 * 
 */
public class Exception_FCI01_01 extends TopDownTransitionTestCase {
  private String id_root_system_function = "156230a0-76b8-41ed-94af-be6f9daeee8c";
  private String id_sf1 = "c7865a05-c2e2-43d7-8db6-3d21ae8507b8";
  private String id_fop11 = "72f67499-64ee-40c0-9ba8-9ceac11d68dc";
  private String id_sf2 = "a5da702f-a8be-4b17-94ae-bf6dd1101fcc";
  private String id_fip21 = "33b3f2a8-0d37-4a66-aa93-b167b401bf5e";
  private String id_fop22 = "72d5b20a-32ee-4962-a5a5-e8aa174ce622";
  private String id_sf3 = "ee381f61-6840-4220-a8fd-3f474793bf78";
  private String id_fip31 = "c9448bd8-3441-453e-a6e7-b21b62b662de";
  private String id_fop32 = "d1511a53-7828-4867-a65f-6784a51ec1b4";
  private String id_fip41 = "c294a0b2-2e83-4c5d-b7ea-a5314ced001c";
  private String id_fop42 = "45154dc7-28d2-42a1-805e-41e7c5eddbb5";
  private String id_sf5 = "61916cc2-305b-40c8-b9e0-1b9a36af84fb";
  private String id_fip51 = "c7218b36-abba-483e-82fb-eb4030a09e95";
  private String id_fc6 = "26ed7779-8dd0-44c9-aba5-24872f755c02";
  private String id_fc7 = "eaf8525a-1ecb-4205-9817-81408c1809cd";
  private String id_fe8 = "7037b418-a106-4959-a896-d59a5129c250";
  private String id_fe9 = "45fb9697-7696-40a3-a6db-5875aeddea08";
  private String id_fe10 = "cfa43910-539d-4552-8265-bffba2e10042";
  private String id_fe11 = "90e6d6ba-7e53-4387-8ec5-7508ecdc78d2";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(getClass().getSimpleName());
  }

  @Override
  public void performTest() throws Exception {
    step1();
  }

  private void step1() {
    performFunctionalTransition(Arrays.asList(getObject(id_fc6)));
    mustBeTransitioned(id_root_system_function);
    mustBeTransitioned(id_sf1);
    mustBeTransitioned(id_fop11);
    mustBeTransitioned(id_sf2);
    mustBeTransitioned(id_fip21);

    mustBeTransitioned(id_fop22);
    mustBeTransitioned(id_sf3);
    mustBeTransitioned(id_fip31);
    mustBeTransitioned(id_fop32);

    /// mustBeTransitioned("SF4", id_sf4);

    mustBeTransitioned(id_fip41);
    mustBeTransitioned(id_fop42);
    mustBeTransitioned(id_sf5);

    mustBeTransitioned(id_fip51);
    mustBeTransitioned(id_fc6);

    shouldNotBeTransitioned(id_fc7);

    mustBeTransitioned(id_fe8);
    mustBeTransitioned(id_fe9);
    mustBeTransitioned(id_fe10);
    mustBeTransitioned(id_fe11);
  }

}
