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
package org.polarsys.capella.core.data.interaction.ui.quickfix.resolver;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 */
public class DWF_DS_17_Resolver1 extends AbstractCapellaMarkerResolution {
  
  /**
   * Synchronize invoked operation according to sequence message kind
   * 
   * {@inheritDoc}
   */
  public void run(IMarker marker) {
    final List<EObject> modelElements = getModelElements(marker);
    if (!modelElements.isEmpty()) {
      AbstractReadWriteCommand abstrctCommand = new AbstractReadWriteCommand() {
        @Override
        public String getName() {
          return getLabel();
        }
        public void run() {
          for (EObject object : modelElements) {
            if (object instanceof SequenceMessage) {
              SequenceMessage msg = (SequenceMessage) object;
              if (msg.getInvokedOperation() instanceof ExchangeItemAllocation) {
                ExchangeItemAllocation allocation = (ExchangeItemAllocation) msg.getInvokedOperation();
                ExchangeItem item = allocation.getAllocatedItem();
                if (ExchangeMechanism.OPERATION.equals(item.getExchangeMechanism())) {
                  MessageKind kind = msg.getKind();
                  CommunicationLinkProtocol protocol = allocation.getSendProtocol();
                  if (MessageKind.UNSET.equals(kind) && !CommunicationLinkProtocol.UNSET.equals(protocol)) {
                    allocation.setSendProtocol(CommunicationLinkProtocol.UNSET);
                  } else if (MessageKind.SYNCHRONOUS_CALL.equals(kind) && !CommunicationLinkProtocol.SYNCHRONOUS.equals(protocol)) {
                    allocation.setSendProtocol(CommunicationLinkProtocol.SYNCHRONOUS);
                  } else if (MessageKind.ASYNCHRONOUS_CALL.equals(kind) && !CommunicationLinkProtocol.ASYNCHRONOUS.equals(protocol)) {
                    allocation.setSendProtocol(CommunicationLinkProtocol.ASYNCHRONOUS);
                  }
                }
              }
            }
          }
        }
      };

      // execute the command
      TransactionHelper.getExecutionManager(modelElements).execute(abstrctCommand);
      try {
        marker.delete();
      } catch (CoreException exception) {
        //Do nothing
      }
    }
  }
}
