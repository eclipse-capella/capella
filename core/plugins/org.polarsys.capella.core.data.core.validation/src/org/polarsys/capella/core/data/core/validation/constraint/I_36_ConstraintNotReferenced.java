/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.core.validation.constraint;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacommon.StateEvent;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InteractionOperand;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check whether a constraint is not referenced.
 */
public class I_36_ConstraintNotReferenced extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
	EObject eObject = ctx.getTarget();
	if (eObject instanceof Constraint) {
		Constraint constraint = (Constraint) eObject;
		EObject container = eObject.eContainer();
		
		// StateTransition
		if (container instanceof StateTransition) {
			StateTransition stateTransition = (StateTransition) container;
			if (constraint != stateTransition.getGuard()) {
				return failMessage(ctx, constraint, "Guard", stateTransition);
			}
		// StateEvent
		} else if (container instanceof StateEvent) {
			StateEvent stateEvent = (StateEvent) container;
			if (constraint != stateEvent.getExpression()) {
				return failMessage(ctx, constraint, "Expression", stateEvent);
			}
		// SequenceMessage
		} else if (container instanceof SequenceMessage) {
			SequenceMessage sequenceMessage = (SequenceMessage) container;
			if (constraint != sequenceMessage.getExchangeContext()) {
				return failMessage(ctx, constraint, "Exchange Context", sequenceMessage);
			}
		// Scenario
		} else if (container instanceof Scenario) {
			Scenario scenario = (Scenario) container;
			if (constraint != scenario.getPreCondition() && constraint != scenario.getPostCondition()) {
				return failMessage(ctx, constraint, "Pre/Post Condition", scenario);
			}
		// AbstractCapability
		} else if (container instanceof AbstractCapability) {
			AbstractCapability capability = (AbstractCapability) container;
			if (constraint != capability.getPreCondition() && constraint != capability.getPostCondition()) {
				return failMessage(ctx, constraint, "Pre/Post Condition", capability);
			}
		// InteractionOperand
		} else if (container instanceof InteractionOperand) {
			InteractionOperand interactionOperand = (InteractionOperand) container;
			if (constraint != interactionOperand.getGuard()) {
				return failMessage(ctx, constraint, "Guard", interactionOperand);
			}
		}
	}
    return ctx.createSuccessStatus();
  }
	
  /**
   * 
   * @param ctx
   * @param constraint the constraint under validation
   * @param feature the name of the feature referencing the constraint
   * @param container the container of the constraint
   * @return
   */
  protected IStatus failMessage(IValidationContext ctx, Constraint constraint, String feature, NamedElement container) {
	  String a = constraint.getName() == null || constraint.getName().isEmpty() ? "" : "\""+constraint.getName()+"\"";
	  String b = container.getName() == null || container.getName().isEmpty() ? "unnamed element" : "\""+container.getName()+"\"";
	  String c = container.eClass().getName().replaceAll("([a-z])([A-Z])", "$1 $2");
	  return ctx.createFailureStatus(new Object[] { a, feature, b, c});
  }
}
