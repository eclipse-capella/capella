/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * This query allows to get the ExchangeItemAllocation allocated to the current sequence message.
 */
public class SequenceMessageInvokedExchangeItemAllocation implements IQuery {

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof SequenceMessage) {
      SequenceMessage sm = (SequenceMessage) object;
      // Gets the sending end of the sequence message
      MessageEnd sendingEnd = sm.getSendingEnd();
      // Gets the receiving end of the sequence message
      MessageEnd receivingEnd = sm.getReceivingEnd();

      if (null != sendingEnd) {
        // Gets the event related to the sending end
        Event event = sendingEnd.getEvent();
        if (event instanceof EventSentOperation) {
          // If it is a <code>EventSentOperation</code>, then, gets the related functional exchange
          EventSentOperation eso = (EventSentOperation) event;
          AbstractEventOperation operation = eso.getOperation();
          if (operation instanceof ExchangeItemAllocation) {
          	  result.add(operation);
          }
        }
      } else if (null != receivingEnd) {
        // Gets the event related to the receiving end
        Event event = receivingEnd.getEvent();
        if (event instanceof EventReceiptOperation) {
          // If it is a <code>EventReceiptOperation</code>, then, gets the related functional exchange
          EventReceiptOperation ero = (EventReceiptOperation) event;
          AbstractEventOperation operation = ero.getOperation();
          if (operation instanceof ExchangeItemAllocation) {
              result.add(operation);
          }
        }
      }
    }
    return result;
  }
}
