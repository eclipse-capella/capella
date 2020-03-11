/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.model.ju.testcase.dialoglabel;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.projection.scenario.commands.ESToISCommand;
import org.polarsys.capella.core.sirius.ui.actions.NewRepresentationAction;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.api.BasicTestCase;
/**
 * This test case tests the label of the dialog that creates new diagrams
 */
public class NewDiagramDialogLabel extends BasicTestCase {

  public static String MODEL_NAME = "DiagramLabelModel"; //$NON-NLS-1$ 
  public static String SA__CAPABILITIES__CAPABILITY_1__ES_CAPABILITY_1 = "6d6a0f72-7a57-4642-8644-34479dc37508"; //$NON-NLS-1$ 
  public static String TESTED_LABEL1 = "[IS] [ES] Capability 1";
  public static String SA__CAPABILITIES__CAPABILITY_1__IS_CAPABILITY_1 = "efca3884-db70-459c-95a8-bc7eb90af5b6"; //$NON-NLS-1$
  public static String TESTED_LABEL2 = "[IS] Capability 1";
  public static String LA__CAPABILITIES__CAPABILITYREALIZATION_1__ES_CAPABILITYREALIZATION_1 = "d7006415-2c80-46b5-8c37-af29de2ddf2f"; //$NON-NLS-1$  
  public static String LA__CAPABILITIES__CAPABILITYREALIZATION_1 = "f11a2250-317f-4f42-8cc8-9f2094a09e39"; //$NON-NLS-1$  
  public static String TESTED_LABEL3 = "[IS] [ES] CapabilityRealization 1 (IS)";

  /**
   * {@inheritDoc}
   */
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(MODEL_NAME);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    ICapellaModel model = getTestModel(MODEL_NAME);
    IScope scope = new ScopeModelWrapper(model);
    final EObject scenario1 = IdManager.getInstance()
        .getEObject(SA__CAPABILITIES__CAPABILITY_1__ES_CAPABILITY_1, scope);
    final EObject scenario2 = IdManager.getInstance()
        .getEObject(SA__CAPABILITIES__CAPABILITY_1__IS_CAPABILITY_1, scope);
    final EObject scenario3 = IdManager.getInstance().getEObject(
        LA__CAPABILITIES__CAPABILITYREALIZATION_1__ES_CAPABILITYREALIZATION_1, scope);
    final CapabilityRealization capability = (CapabilityRealization) IdManager.getInstance().getEObject(
        LA__CAPABILITIES__CAPABILITYREALIZATION_1, scope);

    Session session = getSessionForTestModel(getRequiredTestModels().get(0));

    //Execute a transition from ES to IS
    TransactionHelper.getExecutionManager(scenario3).execute((ICommand) new ESToISCommand(Arrays.asList(scenario3)));
    //Get the transformed scenario to test
    final EObject transformedScenario = capability.getOwnedScenarios().get(1);

    Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);
    for (Viewpoint vp : selectedViewpoints) {
      for (RepresentationDescription representationDescription : vp.getOwnedRepresentations()) {
        if (representationDescription instanceof SequenceDiagramDescription) {
          // 1st test on a scenario that has changed type from ES to IS
          testScenarioActionLabel(scenario1, session, (SequenceDiagramDescription) representationDescription,
              TESTED_LABEL1);
          // 2nd Test on a scenario that has kept its type
          testScenarioActionLabel(scenario2, session, (SequenceDiagramDescription) representationDescription,
              TESTED_LABEL2);
          // 3rd test on an IS scenario that has been transformed from an ES scenario
          testScenarioActionLabel(transformedScenario, session, (SequenceDiagramDescription) representationDescription,
              TESTED_LABEL3);
        }
      }
    }
  }

  /**
   * Test the label of the dialog that creates a new Scenario diagram
   * 
   * @param object
   * @param session
   * @param sdd
   * @param labelToTest
   */
  private void testScenarioActionLabel(final EObject object, Session session, final SequenceDiagramDescription sdd,
      final String labelToTest) {
    if (DialectManager.INSTANCE.canCreate(object, sdd)) {
      String computedActionLabel = new NewRepresentationAction(sdd, object, session) {
        @Override
        public String computeDefaultName(EObject eObject, RepresentationDescription repDescription) {
          return super.computeDefaultName(eObject, repDescription);
        }
      }.computeDefaultName(object, sdd);
      assertTrue("Dialog label for new diagram should be: " + labelToTest, computedActionLabel.equals(labelToTest));
    }
  }
}
