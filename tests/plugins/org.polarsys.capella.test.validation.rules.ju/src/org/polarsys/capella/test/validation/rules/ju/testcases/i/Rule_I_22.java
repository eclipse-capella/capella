/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.i;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram.I_22_Resolver;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnIntegrityTest;

/**
 * test on I_22: This rule ensures that hyperLinks to capella elements or diagrams names are up to date.
 * 
 * @generated
 */
public class Rule_I_22 extends AbstractRulesOnIntegrityTest {

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
   * @generated
   */
  protected EClass getTargetedEClass() {
    return CapellacorePackage.Literals.CAPELLA_ELEMENT;
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
   * @generated
   */
  protected String getRuleID() {
    return "org.polarsys.capella.core.platform.sirius.sirius.validation.I_22";
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   * @generated
   */
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] { "21f79e9a-700b-4673-9599-58c738a208d2", "58fcff83-081f-49e8-b64d-e503042642ab",
        "a9972f3e-dcb8-4d59-a0db-56c14a13890f" });
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   * @generated
   */
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition("58fcff83-081f-49e8-b64d-e503042642ab", 1),
        new OracleDefinition("a9972f3e-dcb8-4d59-a0db-56c14a13890f", 1) });
  }

  @Override
  protected boolean getCheckQuickFix() {
    return true;
  }

  @Override
  protected IStatus testCheckQuickFix(List<IMarker> markers) {
    I_22_Resolver i22Resolver = new I_22_Resolver();
    i22Resolver.run(markers.get(0));
    String falseName = "OperationalActivity 1";
    String trueName = "OA&#160; _&#160; to_be_linked &eacute;&agrave;&euml;";
    CapellaElement element = (CapellaElement) MarkerViewHelper.getModelElementsFromMarker(markers.get(0)).get(0);
    if (element.getDescription().contains(falseName) || !element.getDescription().contains(trueName)) {
      return Status.error("The I_22_Resolver has failed");
    }

    i22Resolver.run(markers.get(1));
    falseName = "[OABD] Diagram to be linked 2";
    trueName = "[OABD] Diagram&#160;&#160; to&#160;&#160; be linked &eacute;&agrave;&auml;";
    element = (CapellaElement) MarkerViewHelper.getModelElementsFromMarker(markers.get(1)).get(0);
    if (element.getDescription().contains(falseName) || !element.getDescription().contains(trueName)) {
      return Status.error("The I_22_Resolver has failed");
    }

    return Status.OK_STATUS;
  }
}
