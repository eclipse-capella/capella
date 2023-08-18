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

import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test the contextual transition - Context00 - Context01
 * 
 * <pre>
 * Model is created with the following elements�
 * 
 * - Rename DataPkg from Logical Architecture to Data
 * - Create Class into Data named Class 1
 * - Create Property into Class 1 named Property 1
 * - Rename LiteralNumericValue from Property 1 to minCard
 * - Rename LiteralNumericValue from Property 1 to maxCard
 * - Create Property into Class 1 named Property 2
 * - Rename LiteralNumericValue from Property 2 to minCard
 * - Rename LiteralNumericValue from Property 2 to maxCard
 * - Create BooleanType into Data named BooleanType 1
 * - Create BooleanType into Data named BooleanType 2_Final
 * 
 * Expected Result:\
 * - Performing Data transition on Data should perform transition of only Boolean1
 * 
 * </pre>
 */
public class Context_DT01_05 extends TopDownTransitionTestCase {

  private String id_data = "e47bf112-7274-495f-afac-c6ee83e0d392"; //$NON-NLS-1$ 
  private String id_unidirectionalpkg = "b5684c4b-b8c9-4c11-b8e7-07fe9d29a60d"; //$NON-NLS-1$ 
  private String id_uniassoc = "fe8b766d-91ab-40b9-b749-d327ae70af4a"; //$NON-NLS-1$ 
  private String id_uni_source = "acaa3eb7-f3bd-4b62-b28c-1aa3f904e987"; //$NON-NLS-1$ 
  private String id_uni_target = "27a17987-c74a-4f06-b0f7-c689680264bf"; //$NON-NLS-1$ 
  private String id_nonnavigablepkg = "781ee100-bc42-41da-8a14-3b2603bf0a56"; //$NON-NLS-1$ 
  private String id_nonass = "779f5026-0f5e-421b-b5c3-bf98f6ce4789"; //$NON-NLS-1$ 
  private String id_non_a = "b7c9003f-1035-4279-83c9-a0e666b71b8a"; //$NON-NLS-1$ 
  private String id_non_b = "abb05646-504a-4ea3-9899-7708905f3639"; //$NON-NLS-1$ 
  private String id_bidirectionalpkg = "ece67fce-eeb8-4391-a260-d7a54da220ed"; //$NON-NLS-1$ 
  private String id_bidi_ass = "1c02c8ec-cdab-4b02-8c1b-a25927f51722"; //$NON-NLS-1$ 
  private String id_bidi_a = "7193937d-6f76-4ff5-a237-7c6e523bc8fd"; //$NON-NLS-1$ 
  private String id_bidi_2 = "a0890c05-de90-40ab-a7ed-53d337826924"; //$NON-NLS-1$ 

  private void initSession() {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__INTERFACE, true);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Context_I01");
  }

  @Override
  public void performTest() throws Exception {
    initSession();
    step1();
  }

  private void step1() {
    performDataTransition(Arrays.asList(getObject(id_data)));
    mustBeTransitioned(id_unidirectionalpkg);
    mustBeTransitioned(id_uniassoc);
    mustBeTransitioned(id_uni_source);
    mustBeTransitioned(id_uni_target);
    mustBeTransitioned(id_nonnavigablepkg);
    mustBeTransitioned(id_nonass);
    mustBeTransitioned(id_non_a);
    mustBeTransitioned(id_non_b);
    mustBeTransitioned(id_bidirectionalpkg);
    mustBeTransitioned(id_bidi_a);
    mustBeTransitioned(id_bidi_2);
    mustBeTransitioned(id_bidi_ass);
  }

}
