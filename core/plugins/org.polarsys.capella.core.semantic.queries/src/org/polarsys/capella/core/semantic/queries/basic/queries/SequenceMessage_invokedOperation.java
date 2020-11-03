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

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.Operation;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return invoked operation of given SequenceMessage
 *
 */
public class SequenceMessage_invokedOperation implements IQuery {

	/**
	 * 
	 */
	public SequenceMessage_invokedOperation() {
    // do nothing
	}

	/** 
	 *  
	 * current.functionalExchanges
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof SequenceMessage) {
			SequenceMessage sm = (SequenceMessage) object;
      MessageEnd sendingEnd = sm.getSendingEnd();
			MessageEnd receivingEnd = sm.getReceivingEnd();
      if (null != sendingEnd) {
        Event event = sendingEnd.getEvent();
        if (event instanceof EventSentOperation) {
          EventSentOperation eventSentOperation = (EventSentOperation) event;
          AbstractEventOperation operation = eventSentOperation.getOperation();
          if (operation instanceof Operation) {
            result.add(operation);          
          }
        }
      } else if (null != receivingEnd) {
        Event event = receivingEnd.getEvent();
  			if (event instanceof EventReceiptOperation) {
  				EventReceiptOperation eventReceiptOperation = (EventReceiptOperation) event;
  				AbstractEventOperation operation = eventReceiptOperation.getOperation();
  				if (operation instanceof Operation) {
  					result.add(operation);					
  				}
  			}
			}
		}
    return result;
	}
}
