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
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram.I_23_ConvertResolver;
import org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram.I_23_Resolver;
import org.polarsys.capella.test.framework.api.ComposedOracleDefinition;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnIntegrityTest;

/**
 * test on I_23: This rule ensures that hyperLinks to non existing capella element or diagram are removed from the
 * description.
 * 
 * @generated
 */
public class Rule_I_23 extends AbstractRulesOnIntegrityTest {

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
    return "org.polarsys.capella.core.platform.sirius.sirius.validation.I_23";
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   * @generated
   */
  protected List<String> getScopeDefinition() {
    return Arrays
        .asList(new String[] { "24b072e6-cfd7-4cf7-8f9a-d45caea91921", "e3012a49-86bc-44a1-b91c-7c18f232ac01" });
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   * @generated
   */
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays
        .asList(new ComposedOracleDefinition("e3012a49-86bc-44a1-b91c-7c18f232ac01", 3, Collections.emptyList()));
  }

  public String extractId(String statusMessage) {
    Pattern pattern = Pattern.compile("\\(id: (.+?)\\)");

    Matcher matcher = pattern.matcher(statusMessage);

    if (matcher.find()) {
      return matcher.group(1);
    } else {
      return null;
    }
  }

  @Override
  protected boolean getCheckQuickFix() {
    return true;
  }

  @Override
  protected IStatus testCheckQuickFix(List<IMarker> markers) {
    // apply delete hyperlink QF for the first marker
    I_23_Resolver i23Resolver = new I_23_Resolver();
    Diagnostic diagnostic = MarkerViewHelper.getDiagnostic(markers.get(0));
    String linkId = i23Resolver.extractId(diagnostic.getMessage());
    i23Resolver.run(markers.get(0));
    CapellaElement element = (CapellaElement) MarkerViewHelper.getModelElementsFromMarker(markers.get(0)).get(0);
    if (element.getDescription().contains(linkId)) {
      return Status.error("The I_23_Resolver (delete hyperlink QF) has failed");
    }
    // apply convert hyperlink QF for the second marker
    Diagnostic diagnostic2 = MarkerViewHelper.getDiagnostic(markers.get(1));
    String linkId2 = i23Resolver.extractId(diagnostic2.getMessage());
    String linkName = "Root Operational Activity";
    new I_23_ConvertResolver().run(markers.get(1));
    if (element.getDescription().contains(linkId2) || !element.getDescription().contains(linkName)) {
      return Status.error("The I_23_ConvertResolver (convert hyperlink QF) has failed");
    }

    return Status.OK_STATUS;
  }

}
