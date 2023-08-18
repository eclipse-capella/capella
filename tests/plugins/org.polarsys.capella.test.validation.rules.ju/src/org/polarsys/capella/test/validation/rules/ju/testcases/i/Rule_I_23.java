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
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram.AbstractReplaceInvalidHyperLinkInDescription;
import org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram.I_23_ConvertResolver;
import org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram.I_23_ReplaceWithCapellaElementResolver;
import org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram.I_23_ReplaceWithDiagramElementResolver;
import org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram.I_23_Resolver;
import org.polarsys.capella.core.platform.sirius.sirius.validation.parser.helper.DescriptionParserHelper;
import org.polarsys.capella.test.framework.api.ComposedOracleDefinition;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnIntegrityTest;
import org.polarsys.kitalpha.richtext.widget.tools.dialogs.IEncodedURLHandler;
import org.polarsys.kitalpha.richtext.widget.tools.manager.LinkManager;
import org.polarsys.kitalpha.richtext.widget.tools.utils.Constants;

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
        .asList(new ComposedOracleDefinition("e3012a49-86bc-44a1-b91c-7c18f232ac01", 5, Collections.emptyList()));
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
    String linkId = DescriptionParserHelper.getLinkIdFromStatus(diagnostic.getMessage());
    i23Resolver.run(markers.get(0));
    CapellaElement element = (CapellaElement) MarkerViewHelper.getModelElementsFromMarker(markers.get(0)).get(0);
    if (element.getDescription().contains(linkId)) {
      return Status.error("The I_23_Resolver (delete hyperlink QF) has failed");
    }
    // apply convert hyperlink QF for the second marker
    Diagnostic diagnostic2 = MarkerViewHelper.getDiagnostic(markers.get(1));
    String linkId2 = DescriptionParserHelper.getLinkIdFromStatus(diagnostic2.getMessage());
    String linkName = "Root Operational Activity";
    new I_23_ConvertResolver().run(markers.get(1));
    if (element.getDescription().contains(linkId2) || !element.getDescription().contains(linkName)) {
      return Status.error("The I_23_ConvertResolver (convert hyperlink QF) has failed");
    }

    OperationalActivity op_activity = null;

    CapellaModel model = getTestModel(getRequiredTestModel());
    TransactionalEditingDomain domain = model.getEditingDomain();
    Project project = model.getProject(domain);
    for (Iterator<EObject> it = project.eAllContents(); it.hasNext();) {
      EObject next = it.next();
      if (next instanceof OperationalActivity) {
        op_activity = (OperationalActivity) next;
        break;
      }
    }

    Diagnostic diagnostic3 = MarkerViewHelper.getDiagnostic(markers.get(2));
    String linkId3 = DescriptionParserHelper.getLinkIdFromStatus(diagnostic3.getMessage());
    final String linkName2 = "My Label";
    final String newLinkId = op_activity.getId();

    new I_23_ReplaceWithCapellaElementResolver() {
      @Override
      protected IEncodedURLHandler getEncodedURLHandler(EObject targetModelElement,
          AbstractReplaceInvalidHyperLinkInDescription replaceDescription) {
        return new IEncodedURLHandler() {
          public String getEncodedURL() {
            LinkManager linkManager = getLinkManager(replaceDescription);
            return linkManager.encode(Constants.MODEL_ELEMENT_LABEL, newLinkId,
                linkName2);
          };

          public boolean handle() {
            return true;
          };
        };
      }
    }.run(markers.get(2));
    if (element.getDescription().contains(linkId3) || !element.getDescription().contains(linkName2)
        || !element.getDescription().contains(newLinkId)) {
      return Status.error("The I_23_ReplaceWithCapellaElementResolver (convert hyperlink QF) has failed");
    }

    Diagnostic diagnostic4 = MarkerViewHelper.getDiagnostic(markers.get(3));
    String linkId4 = DescriptionParserHelper.getLinkIdFromStatus(diagnostic4.getMessage());
    final String linkName3 = "My Other Label";
    final String newLinkId2 = "_OTvN8LzVEe2RdMJrxo6ssg";

    new I_23_ReplaceWithDiagramElementResolver() {
      @Override
      protected IEncodedURLHandler getEncodedURLHandler(EObject targetModelElement,
          AbstractReplaceInvalidHyperLinkInDescription replaceDescription) {
        return new IEncodedURLHandler() {
          public String getEncodedURL() {
            LinkManager linkManager = getLinkManager(replaceDescription);
            return linkManager.encode(Constants.MODEL_ELEMENT_LABEL, newLinkId2, linkName3);
          };

          public boolean handle() {
            return true;
          };
        };
      }
    }.run(markers.get(3));
    if (element.getDescription().contains(linkId4) || !element.getDescription().contains(linkName3)
        || !element.getDescription().contains(newLinkId2)) {
      return Status.error("The I_23_ReplaceWithDiagramElementResolver (convert hyperlink QF) has failed");
    }

    return Status.OK_STATUS;
  }

}
