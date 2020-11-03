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
package org.polarsys.capella.core.data.interaction.validation.sequenceMessage;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.ArmTimerEvent;
import org.polarsys.capella.core.data.interaction.CancelTimerEvent;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_SequenceMessage_CancelTimer extends AbstractValidationRule {


	@Override
	public IStatus validate(IValidationContext ctx) {
	SequenceMessage message = (SequenceMessage) ctx.getTarget();
	Scenario scenario = (Scenario) message.eContainer();
	if (message.getKind().equals(MessageKind.TIMER)) {
		MessageEnd sendingEnd = message.getSendingEnd();
		Event event = sendingEnd.getEvent();
		if (event instanceof CancelTimerEvent) {
			boolean isInTimerArm = false;
			InstanceRole testedIr = sendingEnd.getCovered();
			for (InteractionFragment if_ : scenario.getOwnedInteractionFragments()) {
				if (if_ instanceof AbstractEnd) {
					AbstractEnd ae = (AbstractEnd) if_;
					if (ae.getEvent() instanceof ArmTimerEvent && testedIr.equals(ae.getCovered())) {
						isInTimerArm = ! isInTimerArm;
					}
				}
				if (if_.equals(sendingEnd)) {
					if (isInTimerArm)
						return ctx.createSuccessStatus();
					else 
						return ctx.createFailureStatus(new Object [] {message.getName()});
				}
			}
			
		}
	}
	return ctx.createSuccessStatus();
	}

}
