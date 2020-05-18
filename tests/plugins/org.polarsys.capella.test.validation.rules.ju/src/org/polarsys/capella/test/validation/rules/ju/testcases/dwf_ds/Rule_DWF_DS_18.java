/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_ds;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.ui.quickfix.generator.DWF_DS_18_Resolutions;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.validation.ui.ide.quickfix.CapellaElementGoToResolver;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.framework.helpers.log.FormatedLogger;
import org.polarsys.capella.test.framework.helpers.log.FormatedSysoutLogger;
import org.polarsys.capella.test.validation.rules.ju.TestValidationRulesPlugin;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DWF_DS_18: This rule checks that Sequence Messages in OES or ES scenarios are consistent with their
 * associated Component Exchange or Functional Exchange.
 * 
 * @generated
 */
public class Rule_DWF_DS_18 extends AbstractRulesOnDesignTest {

  protected FormatedLogger logger = new FormatedSysoutLogger();

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
   * @generated
   */
  protected EClass getTargetedEClass() {
    return InteractionPackage.Literals.SEQUENCE_MESSAGE;
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
   * @generated
   */
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.interaction.validation.DWF_DS_18";
  }

  protected boolean getCheckQuickFix() {
    return true;
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
   * @generated
   */
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] { "22842940-cc2a-4e26-8541-59f4e40dd34f", "5009483d-6057-4abf-b42f-07727ef7f2a7",
        "7af4920c-e87f-42b4-a942-945b2a377058", "ba081e4c-0e93-4957-a181-863ddcedc5b8",
        "9a0dafb6-af13-49e7-b6a2-c3b275c0ed78", "68cb4eda-4d4f-4a4b-bb69-3964d0b51501",
        "11eafbd9-aeda-4354-932f-79592cae6f22", "30004fd2-52dd-4599-81c7-234209fe83b0" });
  }

  /**
   * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
   * @generated
   */
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition("9a0dafb6-af13-49e7-b6a2-c3b275c0ed78", 1),
        new OracleDefinition("68cb4eda-4d4f-4a4b-bb69-3964d0b51501", 1),
        new OracleDefinition("11eafbd9-aeda-4354-932f-79592cae6f22", 1),
        new OracleDefinition("30004fd2-52dd-4599-81c7-234209fe83b0", 1) });
  }

  protected IStatus testCheckQuickFix(List<IMarker> markers) {
    IStatus status = Status.OK_STATUS;
    boolean ok = true;
    for (IMarker marker : markers) {
      DWF_DS_18_Resolutions resolution = new DWF_DS_18_Resolutions();
      IMarkerResolution[] resolutions = resolution.getResolutions(marker);
      for (IMarkerResolution markerResolution : resolutions) {
        if (markerResolution.getLabel().contains("SequenceMessage source component")) {
          CapellaModel model = getTestModel(getRequiredTestModels().get(0));
          Session session = getSession(getRequiredTestModels().get(0));
          Project project = model.getProject(session.getTransactionalEditingDomain());
          CommonViewer viewer = getViewer();

          ISelection selectionPrj = new StructuredSelection(project);
          viewer.setSelection(selectionPrj);

          // apply the quick fix to navigate to object
          markerResolution.run(marker);

          ISelection newSelection = viewer.getSelection();

          ok = false;
          if (newSelection != null && !newSelection.isEmpty()) {
            if (markerResolution instanceof CapellaElementGoToResolver) {
              CapellaElementGoToResolver goToResolver = (CapellaElementGoToResolver) markerResolution;
              EObject navigatedObject = goToResolver.getModelElement();
              final EObject newSelectedEObject = newSelection != null
                  ? (EObject) ((TreeSelection) newSelection).iterator().next()
                  : null;
              if (navigatedObject != null && newSelectedEObject != null && navigatedObject.equals(newSelectedEObject)) {
                ok = true;
              } else {
                logger.addTextLn(this.getClass().getName() + ": failed to navigate to: " + navigatedObject);
              }
            }
          }

          if (!ok) {
            break;
          }
        }
      }
    }

    if (ok) {
      status = Status.OK_STATUS;
    } else {
      String message = "Quick fix to navigate to elements for validation rule DWF_DS_18 does not work";
      status = new Status(IStatus.ERROR, TestValidationRulesPlugin.PLUGIN_ID, message);
    }
    return status;
  }

  protected CommonViewer getViewer() {
    final CommonViewer[] viewer = new CommonViewer[1];
    Display.getDefault().syncExec(new Runnable() {

      @Override
      public void run() {
        IViewPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
            .findView(CapellaCommonNavigator.ID);

        viewer[0] = ((CapellaCommonNavigator) part).getCommonViewer();
      }
    });
    return viewer[0];
  }
}
