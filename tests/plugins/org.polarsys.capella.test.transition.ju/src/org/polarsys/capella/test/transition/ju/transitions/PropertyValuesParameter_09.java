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

import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.transition.system.constants.ISystemConstants;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

public class PropertyValuesParameter_09 extends TopDownTransitionTestCase {
  private String id_PVG1 = "4ed3d916-d0e7-4613-893c-a67c8b94bb5f";
  private String id_PVBPV11 = "aabff156-2561-4a3e-9284-f713bf84401c";
  private String id_PVBPV111 = "23d1c974-791b-46b1-8282-df61c36f1cc8";
  private String id_EI1 = "4babd847-225c-44e6-97f7-3a75d4ea007d";
  private String id_EIBPV11 = "150376b7-e710-4a12-bf89-e24f9946b17f";
  private String id_C1 = "b6d0ef7c-ecfd-4f1b-8760-a104bc91cf7d";
  private String id_CIBPV11 = "1cf57750-7edf-4399-8691-fb4d724b6c9a";
  private String id_DATA = "e4cb8ec1-75ce-4dbb-bcc2-a0113722dbf3";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Context_SF01");
  }

  @Override
  public void performTest() throws Exception {
    step1();
    step2();
  }

  private void step1() {
    setPreferenceValue(ISystemConstants.SCOPE__APPLIED_PROPERTY_VALUES, false);
    setPreferenceValue(ISystemConstants.SCOPE__OWNED_PROPERTY_VALUES, false);
    setPreferenceValue(ITopDownConstants.OPTIONS__PROPERTY_VALUE__APPLIED_PROPERTY_VALUES, true);
    setPreferenceValue(ITopDownConstants.OPTIONS__PROPERTY_VALUE__INVOLVED_ELEMENTS, false);
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM, true);
    performDataTransition(Arrays.asList(getObject(id_DATA)));
    shouldNotBeTransitioned(id_PVG1);
    shouldNotBeTransitioned(id_PVBPV11);
    shouldNotBeTransitioned(id_PVBPV111);
    shouldNotBeTransitioned(id_EIBPV11);
    shouldNotBeTransitioned(id_CIBPV11);

    mustBeTransitioned(id_C1);
    mustBeTransitioned(id_EI1);

    mustBeTransitionedAndLinkedTo(id_EI1, id_PVG1,
        CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS);
    mustBeTransitionedAndLinkedTo(id_C1, id_PVG1,
        CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS);
  }

  private void step2() {
    setPreferenceValue(ISystemConstants.SCOPE__APPLIED_PROPERTY_VALUES, true);
    setPreferenceValue(ISystemConstants.SCOPE__OWNED_PROPERTY_VALUES, false);
    setPreferenceValue(ITopDownConstants.OPTIONS__PROPERTY_VALUE__APPLIED_PROPERTY_VALUES, true);
    setPreferenceValue(ITopDownConstants.OPTIONS__PROPERTY_VALUE__INVOLVED_ELEMENTS, false);
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM, true);
    performDataTransition(Arrays.asList(getObject(id_DATA)));
    mustBeTransitioned(id_PVG1);

    mustBeTransitioned(id_C1);
    mustBeTransitioned(id_EI1);

    mustBeTransitionedAndLinkedToTransitioned(id_EI1, id_PVG1,
        CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS);
    mustBeTransitionedAndLinkedToTransitioned(id_C1, id_PVG1,
        CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS);
  }

}
