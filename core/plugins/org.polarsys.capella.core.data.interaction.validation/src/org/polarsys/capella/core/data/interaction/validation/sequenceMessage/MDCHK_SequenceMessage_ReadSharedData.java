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

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.core.data.interaction.properties.controllers.InterfaceHelper;
import org.polarsys.capella.core.model.helpers.SequenceMessageExt;

public class MDCHK_SequenceMessage_ReadSharedData extends
		AbstractModelConstraint {


	@Override
	public IStatus validate(IValidationContext ctx) {
		EObject eObj = ctx.getTarget();
        SequenceMessage seqMsg = (SequenceMessage) eObj;
        Scenario currentScenario = (Scenario) seqMsg.eContainer();
        
        if (! currentScenario.getKind().equals(ScenarioKind.INTERFACE)) 
        	return ctx.createSuccessStatus();
        
        if (! InterfaceHelper.isSharedDataAccess(seqMsg)) {
        	return ctx.createSuccessStatus();
        }
        
        if (SequenceMessageExt.getMessageNameForSharedDataAccess(seqMsg).equals("READ")){
        	// we must check this message is within an execution
        	Execution exec = SequenceMessageExt.getStartedExecution(seqMsg);
        	
        	for (TimeLapse tl : currentScenario.getOwnedTimeLapses()) {
        		if (tl instanceof Execution) {
					Execution testedExec = (Execution) tl;
					List<Execution> subExecution = SequenceMessageExt.getExecutionFromExecution(testedExec);
					if (subExecution.contains(exec)) {
						// testedExec must be started by a write message
						InteractionFragment startingEnd = testedExec.getStart();
						if (startingEnd instanceof MessageEnd) {
							MessageEnd me = (MessageEnd) startingEnd;
							SequenceMessage superSeqMsg = me.getMessage();
							if (InterfaceHelper.isSharedDataAccess(seqMsg)) {
								if (SequenceMessageExt.getMessageNameForSharedDataAccess(superSeqMsg).equals("WRITE")) {
									return ctx.createSuccessStatus();
								}
							}
						}
					}
				}
        	}
        	
        	return ctx.createFailureStatus(new Object [] {SequenceMessageExt.getSender(seqMsg).getName(), SequenceMessageExt.getReceiver(seqMsg).getName()});
			}
    
        return ctx.createSuccessStatus();
        }
	
	}

