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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.interaction.InteractionOperand;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test Scenario transition with uml2 elements
 * 
 * All uml2 elements should be transitioned into each transition possible performed
 */
public class CreateRule_ScenarioUml2_01 extends TopDownTransitionTestCase {
  private String c1 = "6b0ca281-94e3-4184-b5f7-d2b07e465f57";

  private String fs_s11 = "3d98dd46-4cf7-404e-917a-d53090742bee";
  private String ir111 = "a5e9b33e-687c-4491-8bfc-4722b130dcb4";
  private String ir112 = "e576caa2-5b83-42fb-be29-da359072614a";
  private String ir113 = "044fec11-d2dd-4a93-841d-27d3d71bf71f";
  private String fe119 = "a649a5af-727e-4465-83b4-f7994c8a3b15";
  private String fe1110 = "21db8b9b-a6a5-46f4-abe2-ea0e68a584ae";
  private String fe1112 = "821f7d3e-d8ed-4dab-98d5-6d39239ba3b2";
  private String io1113 = "ead86d44-6ac4-4da1-b8df-0ffed07d09a7";
  private String fe1114 = "1c9a7328-fa81-4de7-8c20-578ceeab078c";
  private String fe1115 = "57c75ff9-4ed6-4378-be01-6184556ccd6f";
  private String io1116 = "8e6ca45d-d0a7-46d4-bbc7-799fcf21a236";
  private String io1118 = "f227e459-1eac-42cc-9f4d-3c25acbddb29";
  private String fe1120 = "bf0ce55e-d97d-426a-a31a-b3513774d38b";
  private String iu112 = "a49a59b3-b38b-4827-9bd3-43760c460065";
  private String cf113 = "72dc1209-147a-4989-9c76-9e36a88b84e5";
  private String cf114 = "da5f6982-ae30-4f47-826a-d15df189e898";
  private String SF1117 = "a8ba1297-5fa0-4dac-9b99-79f1924aeafa";
  private String SF1119 = "b17ffcf0-5f89-40f3-8efb-5aa56717e88e";
  private String SF1111 = "9b1eeed5-76a8-4554-b9d6-0d8ed7747be5";

  private String esf_s12 = "53627943-ea14-42bd-ad0f-2ad05ba95baa";
  private String fe129 = "65a9acb2-a1ea-435a-a14d-082336387221";
  private String fe1210 = "cfaa157b-8530-4fb1-97bc-b88c6fe91111";
  private String fe1212 = "f77e810a-a770-4e53-a93d-49ac5f5f3dff";
  private String io1213 = "7c79bc7d-2fa2-4f3a-9f3e-950bb9ca6435";
  private String fe1214 = "4289235a-54cd-43b1-a7c6-35f34ddd5e10";
  private String fe1215 = "6255f420-1aae-450b-a9ec-64292be6319d";
  private String io1216 = "73882279-c4fa-47e9-a409-d5920feac107";
  private String io1218 = "36e9ebe9-4283-452d-9b4d-bec9ac835b24";
  private String fe1220 = "671ba4b6-0ac7-46bd-a373-21738e3b42a6";
  private String iu122 = "885ea985-5ce6-4057-af1c-d7273279a20a";
  private String cf123 = "3294af80-230e-4323-89c1-a863c78aa38a";
  private String cf124 = "b5905c45-2ade-44e1-a66b-ec93114238a9";
  private String SF1219 = "aa7fcc27-5ec8-48b6-b985-cbe805bc104f";
  private String SF1217 = "b818c54b-91f0-4c77-a3e8-efea3f483aa9";
  private String SF1211 = "e0365944-2864-4cea-b209-e7d1a03f7f89";

  private String esb_s13 = "22575e4c-a06c-426f-b600-157258bf2dcf";
  private String fe139 = "46e7e853-1174-4885-a544-306c3437e144";
  private String fe1310 = "2bfec0bc-19ed-4094-a6fe-9f6b0d495403";
  private String fe1312 = "04e7f753-1558-4760-a1d2-b13fb0f42ad7";
  private String io1313 = "00a29fc7-114e-4085-82a8-03bc9e1b4897";
  private String fe1314 = "59969108-a570-47e5-ba86-7349cf91495d";
  private String fe1315 = "2b2b5d02-b1c4-459b-9154-ed1ac28fe0b9";
  private String io1316 = "f6ca66ee-e133-44ba-a43c-531e95693e05";
  private String io1318 = "b0ab6a1b-fe3a-4008-b088-d640bd8638a3";
  private String fe1320 = "9b1b63c3-9992-4908-afdd-4b2e93d174e2";
  private String iu132 = "eaf8b3f0-9749-47d4-8b49-19fd796a6379";
  private String cf133 = "9e12a9e7-7cd5-4e1f-a2ab-6744184e0e6f";
  private String cf134 = "5fbb356a-1ffe-4fd3-9b79-6b64cd24b8e3";
  private String SF1319 = "1ae5e784-6db0-4899-9c0a-d3b27d438959";
  private String SF1317 = "be2d0f6e-1cce-48e4-ad85-9b9db60c7b79";
  private String SF1311 = "f3f75442-a57b-463a-b412-89213fdee104";

  private String IS_S14 = "423b8e8e-c66b-4f24-85c9-271030f229a9";
  private String FE149 = "aa3b5fbd-1b67-44ea-8086-62b28c528d29";
  private String FE1410 = "c561027f-e3b5-47e4-bc7a-16ebeb20ddd9";
  private String FE1412 = "02ed717d-e0fc-4df6-a0e5-b482e956d94a";
  private String IO1413 = "efee6369-502a-480b-b937-aed808ac7097";
  private String FE1414 = "05180a3b-0ad9-44af-8196-5241cc4c37e8";
  private String FE1415 = "352a6ea8-be2e-4b7c-a244-7d0c0f0f192a";
  private String IO1416 = "8540a655-115c-4f3f-b57f-2df182b23ee7";
  private String IO1418 = "6b371a13-044a-4a34-84de-c3a581befe57";
  private String FE1420 = "e0f4c662-067a-4ea1-83ac-5788709de20e";
  private String IU142 = "6b1999a7-f4ef-4e73-8010-f058478884fc";
  private String CF143 = "a0177bb9-e77b-483c-9f18-056d09909727";
  private String CF144 = "f2837819-a326-4523-8b6b-497017ea7a06";
  private String SF1419 = "d7f9a891-fe60-45e4-b3de-51a45eb4f9ef";
  private String SF1417 = "775d3c8a-0a3e-4231-8ce2-5b7516641f7f";
  private String SF1411 = "256f7175-4947-4dbc-b003-9a6e97c56a4b";

  private String LogicalCapabilities = "c6092f71-d8db-4546-9588-20196d140ff4";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("CreateRule_Scenario");
  }

  @Override
  public void performTest() throws Exception {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__FUNCTIONAL, true);
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__STATE_MACHINE, false);
    fs2es();
    esf2esb();
    es2is();
    is2is();
    fs2fs();
    esf2esf();
    esb2esb();
  }

  protected EObject mustBeTransitioned(String id, EObject container, ScenarioKind kind) {
    EObject a4 = getObject(id);
    String name = EObjectLabelProviderHelper.getText(a4);
    assertNotNull(NLS.bind(Messages.NullElement, name), a4);
    EObject a4t = getAllocatingElements(a4).stream()
        .filter(s -> s instanceof Scenario && ((Scenario) s).getKind() == kind && EcoreUtil.isAncestor(container, s))
        .findAny().orElseGet(null);
    String namet = name + "t"; //$NON-NLS-1$
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, namet), a4t);
    String containerName = (container instanceof AbstractNamedElement ? ((AbstractNamedElement) container).getName()
        : container.eClass().getName());
    assertTrue(NLS.bind(Messages.ShouldBeContainedBy, namet, containerName), EcoreUtil.isAncestor(container, a4t));
    return a4t;
  }

  private void fs2es() {
    performFStoESTransition(Arrays.asList(getObject(fs_s11)));
    // Retrieve a scenario in the same capability
    Scenario scenario = (Scenario) mustBeTransitioned(fs_s11, getObject(c1), ScenarioKind.DATA_FLOW);
    mustBeTransitioned(ir111, scenario);
    mustBeTransitioned(ir112, scenario);
    mustBeTransitioned(ir113, scenario);

    mustBeTransitionedAndReference(fe119, scenario);
    mustBeTransitionedAndReference(fe1110, scenario);
    mustBeTransitionedAndReference(SF1111, scenario);
    mustBeTransitionedAndReference(fe1112, scenario);
    mustBeTransitionedAndReference(io1113, scenario);
    hasGuard(io1113, scenario);
    mustBeTransitionedAndReference(fe1114, scenario);
    mustBeTransitionedAndReference(fe1115, scenario);
    mustBeTransitionedAndReference(io1116, scenario);
    hasGuard(io1116, scenario);
    mustBeTransitionedAndReference(SF1117, scenario);
    mustBeTransitionedAndReference(io1118, scenario);
    mustBeTransitionedAndReference(SF1119, scenario);
    mustBeTransitionedAndReference(fe1120, scenario);
    mustBeTransitioned(iu112, scenario);
    mustBeTransitioned(cf113, scenario);
    mustBeTransitioned(cf114, scenario);
  }

  private void hasGuard(String id, EObject container) {
    InteractionOperand target = (InteractionOperand) super.mustBeTransitionedAndReference(id, container);
    assertTrue(target.getGuard() != null);
  }

  @Override
  protected EObject mustBeTransitionedAndReference(String id, EObject container) {
    CapellaElement source = getObject(id);
    CapellaElement target = (CapellaElement) super.mustBeTransitionedAndReference(id, container);
    assertEquals(source.getOwnedConstraints().size(), target.getOwnedConstraints().size());
    return target;
  }

  private void esf2esb() {
    performESFtoESBTransition(Arrays.asList(getObject(esf_s12)));
    // Retrieve a scenario in the same capability
    Scenario scenario = (Scenario) mustBeTransitioned(esf_s12, getObject(c1), ScenarioKind.DATA_FLOW);

    mustBeTransitionedAndReference(fe129, scenario);
    mustBeTransitionedAndReference(fe1210, scenario);
    mustBeTransitionedAndReference(SF1211, scenario);
    mustBeTransitionedAndReference(fe1212, scenario);
    mustBeTransitionedAndReference(io1213, scenario);
    hasGuard(io1213, scenario);
    mustBeTransitionedAndReference(fe1214, scenario);
    mustBeTransitionedAndReference(fe1215, scenario);
    mustBeTransitionedAndReference(io1216, scenario);
    hasGuard(io1216, scenario);
    mustBeTransitionedAndReference(SF1217, scenario);
    mustBeTransitionedAndReference(io1218, scenario);
    mustBeTransitionedAndReference(SF1219, scenario);
    mustBeTransitionedAndReference(fe1220, scenario);
    mustBeTransitioned(iu122, scenario);
    mustBeTransitioned(cf123, scenario);
    mustBeTransitioned(cf124, scenario);
  }

  private void es2is() {
    performEStoISTransition(Arrays.asList(getObject(esb_s13)));
    // Retrieve a scenario in the same capability
    Scenario scenario = (Scenario) mustBeTransitioned(esb_s13, getObject(c1), ScenarioKind.INTERFACE);

    mustBeTransitionedAndReference(fe139, scenario);
    mustBeTransitionedAndReference(fe1310, scenario);
    mustBeTransitionedAndReference(SF1311, scenario);
    mustBeTransitionedAndReference(fe1312, scenario);
    mustBeTransitionedAndReference(io1313, scenario);
    hasGuard(io1113, scenario);
    mustBeTransitionedAndReference(fe1314, scenario);
    mustBeTransitionedAndReference(fe1315, scenario);
    mustBeTransitionedAndReference(io1316, scenario);
    hasGuard(io1316, scenario);
    mustBeTransitionedAndReference(SF1317, scenario);
    mustBeTransitionedAndReference(io1318, scenario);
    mustBeTransitionedAndReference(SF1319, scenario);
    mustBeTransitionedAndReference(fe1320, scenario);
    mustBeTransitioned(iu132, scenario);
    mustBeTransitioned(cf133, scenario);
    mustBeTransitioned(cf134, scenario);
  }

  private void is2is() {
    performIStoISTransition(Arrays.asList(getObject(IS_S14)));
    // Retrieve a scenario in the same capability
    Scenario scenario = (Scenario) mustBeTransitioned(IS_S14, getObject(LogicalCapabilities), ScenarioKind.INTERFACE);

    mustBeTransitionedAndReference(FE149, scenario);
    mustBeTransitionedAndReference(FE1410, scenario);
    mustBeTransitionedAndReference(SF1411, scenario);
    mustBeTransitionedAndReference(FE1412, scenario);
    mustBeTransitionedAndReference(IO1413, scenario);
    hasGuard(IO1413, scenario);
    mustBeTransitionedAndReference(FE1414, scenario);
    mustBeTransitionedAndReference(FE1415, scenario);
    mustBeTransitionedAndReference(IO1416, scenario);
    hasGuard(IO1416, scenario);
    mustBeTransitionedAndReference(SF1417, scenario);
    mustBeTransitionedAndReference(IO1418, scenario);
    mustBeTransitionedAndReference(SF1419, scenario);
    mustBeTransitionedAndReference(FE1420, scenario);
    mustBeTransitioned(IU142, scenario);
    mustBeTransitioned(CF143, scenario);
    mustBeTransitioned(CF144, scenario);
  }

  private void fs2fs() {
    performFStoFSTransition(Arrays.asList(getObject(fs_s11)));
    // Retrieve scenario into the logical capability
    Scenario scenario = (Scenario) mustBeTransitioned(fs_s11, getObject(LogicalCapabilities), ScenarioKind.FUNCTIONAL);

    mustBeTransitionedAndReference(ir111, scenario);
    mustBeTransitionedAndReference(ir112, scenario);
    mustBeTransitionedAndReference(ir113, scenario);
    mustBeTransitionedAndReference(fe119, scenario);
    mustBeTransitionedAndReference(fe1110, scenario);
    mustBeTransitionedAndReference(SF1111, scenario);
    mustBeTransitionedAndReference(fe1112, scenario);
    mustBeTransitionedAndReference(io1113, scenario);
    hasGuard(io1113, scenario);
    mustBeTransitionedAndReference(fe1114, scenario);
    mustBeTransitionedAndReference(fe1115, scenario);
    mustBeTransitionedAndReference(io1116, scenario);
    hasGuard(io1116, scenario);
    mustBeTransitionedAndReference(SF1117, scenario);
    mustBeTransitionedAndReference(io1118, scenario);
    mustBeTransitionedAndReference(SF1119, scenario);
    mustBeTransitionedAndReference(fe1120, scenario);
    mustBeTransitionedAndReference(iu112, scenario);
    mustBeTransitionedAndReference(cf113, scenario);
    mustBeTransitionedAndReference(cf114, scenario);
  }

  private void esf2esf() {
    performEStoESTransition(Arrays.asList(getObject(esf_s12)));
    // Retrieve scenario into the logical capability
    Scenario scenario = (Scenario) mustBeTransitioned(esf_s12, getObject(LogicalCapabilities), ScenarioKind.DATA_FLOW);
    mustBeTransitionedAndReference(fe129, scenario);
    mustBeTransitionedAndReference(fe1210, scenario);
    mustBeTransitionedAndReference(SF1211, scenario);
    mustBeTransitionedAndReference(fe1212, scenario);
    mustBeTransitionedAndReference(io1213, scenario);
    hasGuard(io1213, scenario);
    mustBeTransitionedAndReference(fe1214, scenario);
    mustBeTransitionedAndReference(fe1215, scenario);
    mustBeTransitionedAndReference(io1216, scenario);
    hasGuard(io1216, scenario);
    mustBeTransitionedAndReference(SF1217, scenario);
    mustBeTransitionedAndReference(io1218, scenario);
    mustBeTransitionedAndReference(SF1219, scenario);
    mustBeTransitionedAndReference(fe1220, scenario);
    mustBeTransitionedAndReference(iu122, scenario);
    mustBeTransitionedAndReference(cf123, scenario);
    mustBeTransitionedAndReference(cf124, scenario);
  }

  private void esb2esb() {
    performEStoESTransition(Arrays.asList(getObject(esb_s13)));
    // Retrieve scenario into the logical capability
    Scenario scenario = (Scenario) mustBeTransitioned(esb_s13, getObject(LogicalCapabilities), ScenarioKind.DATA_FLOW);

    mustBeTransitionedAndReference(fe139, scenario);
    mustBeTransitionedAndReference(fe1310, scenario);
    mustBeTransitionedAndReference(SF1311, scenario);
    mustBeTransitionedAndReference(fe1312, scenario);
    mustBeTransitionedAndReference(io1313, scenario);
    hasGuard(io1313, scenario);
    mustBeTransitionedAndReference(fe1314, scenario);
    mustBeTransitionedAndReference(fe1315, scenario);
    mustBeTransitionedAndReference(io1316, scenario);
    hasGuard(io1316, scenario);
    mustBeTransitionedAndReference(SF1317, scenario);
    mustBeTransitionedAndReference(io1318, scenario);
    mustBeTransitionedAndReference(SF1319, scenario);
    mustBeTransitionedAndReference(fe1320, scenario);
    mustBeTransitionedAndReference(iu132, scenario);
    mustBeTransitionedAndReference(cf133, scenario);
    mustBeTransitionedAndReference(cf134, scenario);
  }

}
