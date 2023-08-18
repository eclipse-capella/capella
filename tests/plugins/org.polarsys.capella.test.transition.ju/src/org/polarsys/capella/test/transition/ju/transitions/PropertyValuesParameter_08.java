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

import org.polarsys.capella.core.transition.system.constants.ISystemConstants;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

public class PropertyValuesParameter_08 extends TopDownTransitionTestCase {
  private String id_pvp1 = "01494976-6bb9-4755-9ee7-53bdda4a7def";
  private String id_bpv11 = "e761ae14-dd94-4109-9925-c31388f07774";
  private String id_ipv12 = "97289608-81b9-4ed8-89be-101a99a6ba50";
  private String id_bpv11_1 = "0e57e486-5963-48f6-8ad1-1b19794191bf";
  private String id_ipv12_1 = "0d35eeec-4c68-4bb0-8e32-df21ee77a134";
  private String id_bpv111 = "1ebddab6-3490-4ff2-ac54-de9ab61d7985";
  private String id_ipv112 = "6f353814-76ef-4e4d-a564-24fa3b201063";

  private String id_bpv1111 = "06f60249-2de7-458c-9b74-b01f761c1980";
  private String id_bpv1112 = "4361fffa-c1c0-462f-b26d-6e0125925726";
  private String id_ipv1113 = "a96f9021-71c5-4901-beef-a2933bd6b92c";

  private void initSession() {
    setPreferenceValue(ISystemConstants.SCOPE__APPLIED_PROPERTY_VALUES, false);
    setPreferenceValue(ISystemConstants.SCOPE__OWNED_PROPERTY_VALUES, false);
    setPreferenceValue(ITopDownConstants.OPTIONS__PROPERTY_VALUE__APPLIED_PROPERTY_VALUES, false);
    setPreferenceValue(ITopDownConstants.OPTIONS__PROPERTY_VALUE__INVOLVED_ELEMENTS, false);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Context_SF01");
  }

  @Override
  public void performTest() throws Exception {
    initSession();
    step1();
  }

  private void step1() {
    performPropertyValueTransition(Arrays.asList(getObject(id_bpv11)));
    mustBeTransitioned(id_pvp1);
    mustBeTransitioned(id_bpv11);
    shouldNotBeTransitioned(id_ipv12);

    shouldNotBeTransitioned(id_bpv11_1);
    shouldNotBeTransitioned(id_ipv12_1);
    shouldNotBeTransitioned(id_bpv111);
    shouldNotBeTransitioned(id_ipv112);
    shouldNotBeTransitioned(id_bpv1111);
    shouldNotBeTransitioned(id_bpv1112);
    shouldNotBeTransitioned(id_ipv1113);
  }

}
