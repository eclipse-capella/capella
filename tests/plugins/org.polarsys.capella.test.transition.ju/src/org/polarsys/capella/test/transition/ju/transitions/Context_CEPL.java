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

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Expected Result:
 * When transition of CE/PL, only this CE/PL and its source target ports and components shall be propagated.
 * Other component, other ports or childs of source/target components shall not be included.
 */
public class Context_CEPL extends TopDownTransitionTestCase {
  public static final String SYSTEMCP_1 = "95538894-cba4-4167-b1a1-6f874e58dfb0"; //$NON-NLS-1$
  public static final String C_1 = "cfde4379-8383-4cfd-9dd5-f93b319cf87e"; //$NON-NLS-1$
  public static final String PL_2 = "b9a3e19d-8205-40f2-abb8-c9a6e77d447e"; //$NON-NLS-1$
  public static final String SYSTEM = "d8aa3c19-4692-4f8d-871b-7db0125c13ad"; //$NON-NLS-1$
  public static final String SA_2 = "b0d14ab7-257f-4191-afa4-40f981c33500"; //$NON-NLS-1$
  public static final String SCP_1 = "2f3a809f-d0f7-4ee0-8a20-2f4861bc5f1e"; //$NON-NLS-1$
  public static final String SCP_2 = "faed714e-32d7-4d52-9dc0-f2f37810f41a"; //$NON-NLS-1$
  public static final String SPP_3 = "8c5212e8-f1fd-4ba6-94b6-752d4cba8ea0"; //$NON-NLS-1$
  public static final String SPP_4 = "082a4108-c625-4c65-a0c4-f326e0b559e0"; //$NON-NLS-1$
  public static final String SA_3 = "ba188ef4-3aa2-47e6-9305-8878df30537c"; //$NON-NLS-1$
  public static final String ACP_1 = "14ae59ce-64a7-42c7-91b6-1683d0a82156"; //$NON-NLS-1$
  public static final String ACP_2 = "b7446406-76c6-45ce-81cb-0a87bb9dec14"; //$NON-NLS-1$
  public static final String APP_3 = "2c36da6c-cc3a-4e91-b561-870d4c286b23"; //$NON-NLS-1$
  public static final String APP_4 = "1e30316c-17d1-41b4-b9dc-1c4f15e9d1c9"; //$NON-NLS-1$
  public static final String CL_1 = "187ffc7d-5102-4054-9daa-0dd709eaaf32"; //$NON-NLS-1$
  public static final String PLL_1 = "3de3e35c-6df9-462f-89aa-ea04ad2f9fb6"; //$NON-NLS-1$
  public static final String LOGICAL_SYSTEM = "8c79ae38-4d14-4d8b-a702-856787f50dba"; //$NON-NLS-1$
  public static final String LA_2 = "a5594bb9-03c3-4fb3-b71f-f0feb423093d"; //$NON-NLS-1$
  public static final String LSPP_1 = "7688811f-52df-425a-94c0-e648c26c3feb"; //$NON-NLS-1$
  public static final String LSCP_2 = "cd4e51bc-f929-45ee-912c-cd266664a396"; //$NON-NLS-1$
  public static final String LSPP_3 = "fb04e387-8c2b-4558-aebb-c4a1b6b5d724"; //$NON-NLS-1$
  public static final String LSCP_4 = "b65173f0-8dda-4c66-8798-a8d753812c50"; //$NON-NLS-1$
  public static final String LA_3 = "aa79a379-4089-4d16-a9ce-fd57b6fccceb"; //$NON-NLS-1$
  public static final String LAPP_1 = "f07e94a0-445b-4213-ac3d-2d7ad2c0f1a4"; //$NON-NLS-1$
  public static final String LACP_2 = "793ff5c0-47d8-4dac-ad52-1d6f3084fd7b"; //$NON-NLS-1$
  public static final String LAPP_3 = "f73c1bab-13e3-417d-916b-13339ffa7634"; //$NON-NLS-1$
  public static final String LACP_4 = "96c16071-0ebc-4c9e-a7f9-1ce2d1d426d8"; //$NON-NLS-1$
  public static final String SA_4 = "94b02997-9dd1-4ca4-83dd-28ba945937dc"; //$NON-NLS-1$
  public static final String LA_4 = "a82bcb55-fa8e-4990-9cfc-7a6cf6e24db6"; //$NON-NLS-1$
  public static final String LOGICALCP_1 = "6ebb1db9-abbe-4ac6-ac3f-e04b6e90052c"; //$NON-NLS-1$
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Context_CEPL");
  }

  @Override
  public void performTest() throws Exception {
    step1();
    step2();
    step3();
    step4();
  }

  private void step1() {
    performActorTransition(Arrays.asList(getObject(C_1)));
    mustBeTransitioned(SCP_1);
    mustBeTransitioned(ACP_1);
    mustBeTransitioned(SA_2);
    mustBeTransitioned(SA_3);
    assertTrue(!((Component)mustBeTransitioned(SA_3)).getRepresentingParts().isEmpty());
    
    shouldNotBeTransitioned(SYSTEMCP_1);
    shouldNotBeTransitioned(SA_4);
    shouldNotBeTransitioned(SCP_2);
    shouldNotBeTransitioned(ACP_2);
  }
  

  private void step2() {
    performActorTransition(Arrays.asList(getObject(PL_2)));
    mustBeTransitioned(SPP_4);
    mustBeTransitioned(APP_4);
    mustBeTransitioned(SA_2);
    mustBeTransitioned(SA_3);
    shouldNotBeTransitioned(SYSTEMCP_1);
    shouldNotBeTransitioned(SA_4);
    shouldNotBeTransitioned(SPP_3);
    shouldNotBeTransitioned(APP_3);
  }
  
  private void step3() {
    performActorTransition(Arrays.asList(getObject(CL_1)));
    mustBeTransitioned(LSCP_2);
    mustBeTransitioned(LACP_2);
    mustBeTransitioned(LA_2);
    mustBeTransitioned(LA_3);
    shouldNotBeTransitioned(LOGICALCP_1);
    shouldNotBeTransitioned(LSCP_4);
    shouldNotBeTransitioned(LACP_4);
    shouldNotBeTransitioned(LA_4);
  }

  private void step4() {
    performActorTransition(Arrays.asList(getObject(PLL_1)));
    mustBeTransitioned(LSPP_1);
    mustBeTransitioned(LAPP_1);
    mustBeTransitioned(LA_2);
    mustBeTransitioned(LA_3);
    shouldNotBeTransitioned(LOGICALCP_1);
    shouldNotBeTransitioned(LSPP_3);
    shouldNotBeTransitioned(LAPP_3);
    shouldNotBeTransitioned(LA_4);
  }
}
