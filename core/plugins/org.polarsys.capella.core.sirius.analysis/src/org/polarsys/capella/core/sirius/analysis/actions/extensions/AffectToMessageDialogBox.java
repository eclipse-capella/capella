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
package org.polarsys.capella.core.sirius.analysis.actions.extensions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;

import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.properties.dialogs.DialogProvider;
import org.polarsys.capella.core.model.helpers.ScenarioExt;

public class AffectToMessageDialogBox extends AbstractExternalJavaAction {
  /**
   *  
   */
  private static final String RESULT_PORT_STRATEGY_VARIABLE = "resultPortStrategyVariable"; //$NON-NLS-1$
  /**
   * 
   */
  private static final String CANCELED_DIALOG = "CANCELED_DIALOG"; //$NON-NLS-1$

  /**
   * @see org.eclipse.sirius.tools.api.ui.IExternalJavaAction#execute(java.util.Collection, java.util.Map)
   */
  public void execute(Collection<? extends EObject> selections_p, Map<String, Object> parameters_p) {
    EObject result = null;
    SequenceMessage message = (SequenceMessage) parameters_p.get(MESSAGE);
    MessageKind kind = MessageKind.valueOf((String) parameters_p.get(MESSAGE_KIND));
    
    InstanceRole sourceIR = (InstanceRole) parameters_p.get(SOURCE_IR);
    InstanceRole targetIR = (InstanceRole) parameters_p.get(TARGET_IR);
    
    Scenario scenario = (Scenario) (sourceIR == null?targetIR.eContainer():sourceIR.eContainer());
    
    if (ScenarioExt.isFunctionalScenario(scenario)) {
      String echangeType = (String) parameters_p.get(EXCHANGE_TYPE);
      result = DialogProvider.openFunctionalExchangeDialog(message, sourceIR, targetIR, echangeType);
      String resultVariable = (String) parameters_p.get(RESULT_VARIABLE);
      if (result == null) {
        InterpreterUtil.getInterpreter(scenario).setVariable(resultVariable, CANCELED_DIALOG);
        throw new OperationCanceledException();
      } 
        InterpreterUtil.getInterpreter(scenario).setVariable(resultVariable, result);
      return;
    }

    switch (scenario.getKind().getValue()) {
      case ScenarioKind.INTERFACE_VALUE:
        Object targetOnExchangeItem = ScenarioExt.getTargetOnExchangeItem (parameters_p.get(TARGET_ON_EXCHANGE_ITEM));
        result = DialogProvider.openOperationDialog(message, sourceIR, targetIR, targetOnExchangeItem, kind);
        String resultPortStrategyVariable = (String) parameters_p.get(RESULT_PORT_STRATEGY_VARIABLE);
        InterpreterUtil.getInterpreter(scenario).setVariable(resultPortStrategyVariable, Boolean.valueOf(DialogProvider.getPortStrategie()));
      break;
      case ScenarioKind.DATA_FLOW_VALUE:
      case ScenarioKind.INTERACTION_VALUE:
        String echangeType = (String) parameters_p.get(EXCHANGE_TYPE);        
        result = DialogProvider.openFunctionalExchangeDialog(message, sourceIR, targetIR, echangeType);
      break;
    }
    
    // put the result into the result variable if apply
    String resultVariable = (String) parameters_p.get(RESULT_VARIABLE);
    if (result == null) {
      InterpreterUtil.getInterpreter(scenario).setVariable(resultVariable, CANCELED_DIALOG);
      throw new OperationCanceledException();
    }
      InterpreterUtil.getInterpreter(scenario).setVariable(resultVariable, result);      
  }

  /**
   * @param object_p
   * @return
   */

}
