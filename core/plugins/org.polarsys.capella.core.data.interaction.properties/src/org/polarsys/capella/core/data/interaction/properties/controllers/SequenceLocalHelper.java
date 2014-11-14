/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
	static SequenceMessage getOppositeSequenceMessage (SequenceMessage msg_p) {
	  MessageKind kind = msg_p.getKind();
		Scenario sc = (Scenario) msg_p.eContainer();

		if (MessageKind.UNSET.equals(kind)
	    || MessageKind.SYNCHRONOUS_CALL.equals(kind)
		  || MessageKind.ASYNCHRONOUS_CALL.equals(kind))
		{
			MessageEnd execEnd = msg_p.getReceivingEnd();
			for (TimeLapse exec : sc.getOwnedTimeLapses()) {
				if (exec.getStart() == execEnd) {
				  InteractionFragment fragment = exec.getFinish();
				  if (fragment instanceof MessageEnd) {
				    return ((MessageEnd) fragment).getMessage();
				  }
				}
			}
		} else if (MessageKind.REPLY.equals(kind)){
			MessageEnd execEnd = msg_p.getSendingEnd();
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
