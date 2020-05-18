/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.interaction.validation.sequenceMessage;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.properties.controllers.DataFlowHelper;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;

/**
 * In ES or OES scnearios this check insures that a SequenceMessage is consistent with its associated exchange (ComponentExchange or FunctionalExchange).<br>
 * To do that, we check that the exchange currently associated with this SequenceMessage is still available between the source and target InstanceRoles of this
 * SequenceMessage.
 */
public class MDCHK_SequenceMessage_ES_OES_InvokedOperation extends AbstractModelConstraint {
  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("nls")
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    //
    // Preconditions.
    //
    EMFEventType eType = ctx_p.getEventType();
    if (EMFEventType.NULL != eType) {
      return ctx_p.createSuccessStatus();
    }
    EObject eObj = ctx_p.getTarget();
    if (!(eObj instanceof SequenceMessage)) {
      return ctx_p.createSuccessStatus();
    }
    SequenceMessage sequenceMessage = (SequenceMessage) eObj;
    // Ignore REPLY SequenceMessages.
    if (sequenceMessage.getKind() == MessageKind.REPLY) {
      return ctx_p.createSuccessStatus();
    }
    // This rule is only valid for Exchange Scenarios (DATA_FLOW) or Operational Entity Scenarios (INTERACTION without instance role of functions).
    Scenario containingScenario = (Scenario) sequenceMessage.eContainer();
    if (!((containingScenario.getKind() == ScenarioKind.DATA_FLOW) || ((containingScenario.getKind() == ScenarioKind.INTERACTION) && !ScenarioExt
        .isFunctionalScenario((Scenario) sequenceMessage.eContainer())))) {
      return ctx_p.createSuccessStatus();
    }
    // Get exchange (FunctionalExchange or ComponentExchange) from SequenceMessage.
    AbstractEventOperation invokedOperation = sequenceMessage.getInvokedOperation();
    if (null == invokedOperation) {
      // null value for invokedOperarion is tested in another validation rule.
      return ctx_p.createSuccessStatus();
    } else if (!(invokedOperation instanceof FunctionalExchange) && !(invokedOperation instanceof ComponentExchange)) {
      return createFailureStatus(ctx_p, sequenceMessage, "Sequence Message invoked operation is not a Functional Exchange or a Component Exchange");
    }
    // Get available exchanges between source and target InstanceRoles and check previously found exchange is amongst them.
    if (invokedOperation instanceof ComponentExchange) {
      Collection<? extends AbstractEventOperation> availableComponentExchanges = DataFlowHelper.getAvailableComponentExchanges(sequenceMessage);
      if (!availableComponentExchanges.contains(invokedOperation)) {
        String exchangeSourceComponentName = EObjectLabelProviderHelper.getText(ComponentExchangeExt.getSourceComponent((ComponentExchange) invokedOperation));
        String exchangeTargetComponentName = EObjectLabelProviderHelper.getText(ComponentExchangeExt.getTargetComponent((ComponentExchange) invokedOperation));
        return createFailureStatus(ctx_p, sequenceMessage, "Invoked Component Exchange (source: " + exchangeSourceComponentName + ", target: "
                                                           + exchangeTargetComponentName + ") is no more available for this Sequence Message");
      }
    } else if (invokedOperation instanceof FunctionalExchange) {
      Collection<FunctionalExchange> availableFunctionalExchanges = DataFlowHelper.getAvailableFonctionalExchanges(sequenceMessage);
      if (!availableFunctionalExchanges.contains(invokedOperation)) {
        String exchangeSourceFunctionName = EObjectLabelProviderHelper.getText(FunctionalExchangeExt.getSourceFunction((FunctionalExchange) invokedOperation));
        String exchangeTargetFunctionName = EObjectLabelProviderHelper.getText(FunctionalExchangeExt.getTargetFunction((FunctionalExchange) invokedOperation));
        return createFailureStatus(ctx_p, sequenceMessage, "Invoked Functional Exchange (source: " + exchangeSourceFunctionName + ", target: "
                                                           + exchangeTargetFunctionName + ") is no more available for this Sequence Message");
      }
    }
    return ctx_p.createSuccessStatus();
  }

  /**
   * Create a Status with the following message arguments: SequenceMessageName, SequenceMessageMetaclass, ScenarioName, ScenarioMetaClass + an additional
   * message.
   * @param ctx_p
   * @param sequenceMessage_p
   * @param additionalMessage_p
   * @return
   */
  public IStatus createFailureStatus(IValidationContext ctx_p, SequenceMessage sequenceMessage_p, String additionalMessage_p) {
    String sequenceMessageName = EObjectLabelProviderHelper.getText(sequenceMessage_p);
    String sequenceMessageMetaClass = EObjectLabelProviderHelper.getMetaclassLabel(sequenceMessage_p, false);
    String scenarioName = EObjectLabelProviderHelper.getText(sequenceMessage_p.eContainer());
    String scenarioMetaClass = EObjectLabelProviderHelper.getMetaclassLabel(sequenceMessage_p.eContainer(), false);
    return ctx_p.createFailureStatus(sequenceMessageName, sequenceMessageMetaClass, scenarioName, scenarioMetaClass, additionalMessage_p);
  }
}
