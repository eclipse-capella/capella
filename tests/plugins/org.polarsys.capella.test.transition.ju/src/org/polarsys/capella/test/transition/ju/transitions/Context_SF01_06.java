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
 * Test the contextual transition - Context00 - Context01
 * 
 * <pre>
 * Model is created with the following elements�
 * - Create RootSF [SystemFunction] owned by System Functions [SystemFunctionPkg]
 * - Create SFP1 [SystemFunctionPkg] owned by RootSF [SystemFunction]
 * - Create SF11 [SystemFunction] owned by SFP1 [SystemFunctionPkg]
 * - Create SF12 [SystemFunction] owned by SFP1 [SystemFunctionPkg]
 * - Create SFP2 [SystemFunctionPkg] owned by RootSF [SystemFunction]
 * - Create SF21 [SystemFunction] owned by SFP2 [SystemFunctionPkg]
 * - Create SFP211 [SystemFunctionPkg] owned by SF21 [SystemFunction]
 * - Create SF2111 [SystemFunction] owned by SFP211 [SystemFunctionPkg]
 * - Create SF2112 [SystemFunction] owned by SFP211 [SystemFunctionPkg]
 * - Create SFP21 [SystemFunctionPkg] owned by SFP2 [SystemFunctionPkg]
 * - Create SF211 [SystemFunction] owned by SFP21 [SystemFunctionPkg]
 * - Create SF212 [SystemFunction] owned by SFP21 [SystemFunctionPkg]
 * - Create SFP211 [SystemFunctionPkg] owned by SFP21 [SystemFunctionPkg]
 * - Create SFP212 [SystemFunctionPkg] owned by SFP21 [SystemFunctionPkg]
 * - Create SFP3 [SystemFunctionPkg] owned by RootSF [SystemFunction]
 * - Create SF31 [SystemFunction] owned by SFP3 [SystemFunctionPkg]
 * - Create SFP31 [SystemFunctionPkg] owned by SFP3 [SystemFunctionPkg]
 * - Create SF311 [SystemFunction] owned by SFP31 [SystemFunctionPkg]
 * - Create SF312 [SystemFunction] owned by SFP31 [SystemFunctionPkg]
 * - Create SFP311 [SystemFunctionPkg] owned by SFP31 [SystemFunctionPkg]
 * - Create SFP312 [SystemFunctionPkg] owned by SFP31 [SystemFunctionPkg]
 * 
 * 
 * Expected Result:\
 * FunctionPkgs should be created
 * </pre>
 */
public class Context_SF01_06 extends TopDownTransitionTestCase {

  private String id_sfp1 = "ca9bff12-71fc-4d5d-ac2b-a18094ca07aa";
  private String id_sf11 = "8ab1335d-3152-4c38-9e34-93c0e56fc784";
  private String id_sf12 = "cac7bb26-c1cb-424d-a2a2-a85de0c69581";
  private String id_sfp2 = "4febc69d-8033-4630-9213-91e62f6416f7";
  private String id_sf21 = "221d4d05-8e43-48c8-99e3-17cfebf268f3";
  private String id_sfp211 = "4ee27e58-d154-4896-a5a4-11c04e212102";
  private String id_sf2111 = "71be5156-0e45-44ba-94bb-62c589462d2d";
  private String id_sfp21 = "f3272869-3a75-416e-8261-ed6b823ad218";
  private String id_sf212 = "96c1e03b-c94f-4529-98e2-a561f7ab4c76";
  private String id_sfp212 = "86f5ca39-d3d3-4680-8545-7c9cab098a6c";

  private String id_sfp3 = "a672a0bf-74f9-4db9-a2c0-cfb0792037a7";
  private String id_sfp31 = "87e11282-5c8b-442b-aefe-e6e5eff1e4c5";
  private String id_sfp311 = "ea71aec0-3b43-475b-b19d-d2820ebd9447";
  private String id_sfp312 = "2929fe9e-93cc-4f42-9ec2-02b43f5bf890";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Context_SF01");
  }

  @Override
  public void performTest() throws Exception {
    step1();
    step2();
    step3();
  }

  private void step1() {
    performFunctionalTransition(Arrays.asList(getObject(id_sf11)));
    mustBeTransitioned(id_sfp1);
    mustBeTransitioned(id_sf11);
    shouldNotBeTransitioned(id_sf12);
  }

  private void step2() {
    performFunctionalTransition(Arrays.asList(getObject(id_sfp2)));
    mustBeTransitioned(id_sfp2);
    mustBeTransitioned(id_sf21);
    mustBeTransitioned(id_sfp211);
    mustBeTransitioned(id_sf2111);
    mustBeTransitioned(id_sfp21);
    mustBeTransitioned(id_sf212);
    mustBeTransitioned(id_sfp211);
    mustBeTransitioned(id_sfp212);
  }

  private void step3() {
    performFunctionalTransition(Arrays.asList(getObject(id_sfp311)));
    mustBeTransitioned(id_sfp3);
    mustBeTransitioned(id_sfp31);
    shouldNotBeTransitioned(id_sfp312);
    mustBeTransitioned(id_sfp311);
  }
}