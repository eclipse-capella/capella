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
package org.polarsys.capella.core.data.interaction.properties.controllers;

import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.TimeLapse;

public class SequenceLocalHelper {
	static SequenceMessage getOppositeSequenceMessage (SequenceMessage msg) {
	  MessageKind kind = msg.getKind();
		Scenario sc = (Scenario) msg.eContainer();

		if (MessageKind.UNSET.equals(kind)
	    || MessageKind.SYNCHRONOUS_CALL.equals(kind)
		  || MessageKind.ASYNCHRONOUS_CALL.equals(kind))
		{
			MessageEnd execEnd = msg.getReceivingEnd();
			for (TimeLapse exec : sc.getOwnedTimeLapses()) {
				if (exec.getStart() == execEnd) {
				  InteractionFragment fragment = exec.getFinish();
				  if (fragment instanceof MessageEnd) {
				    return ((MessageEnd) fragment).getMessage();
				  }
				}
			}
		} else if (MessageKind.REPLY.equals(kind)){
			MessageEnd execEnd = msg.getSendingEnd();
			for (TimeLapse exec : sc.getOwnedTimeLapses()) {
				if (exec.getFinish() == execEnd) {
          InteractionFragment fragment = exec.getStart();
          if (fragment instanceof MessageEnd) {
            return ((MessageEnd) fragment).getMessage();
          }
				}
			}
		}
		return null;
	}
}
