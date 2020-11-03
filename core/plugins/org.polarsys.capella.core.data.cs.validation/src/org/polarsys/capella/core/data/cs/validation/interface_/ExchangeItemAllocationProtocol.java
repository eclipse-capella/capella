/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.validation.interface_;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.helpers.information.services.LinkCompatibilityDefinition;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;
import org.polarsys.capella.core.model.utils.ListExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class ExchangeItemAllocationProtocol extends AbstractValidationRule {
  
  public static boolean isSenderAllocation(IStatus status) {
    return status.getMessage().contains(Messages.ExchangeItemAllocationProtocol_CommunicationLinkProtocol_Sender);
  }
  
  public static boolean isReceiverAllocation(IStatus status) {
    return status.getMessage().contains(Messages.ExchangeItemAllocationProtocol_CommunicationLinkProtocol_Receiver);
  }
  
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof ExchangeItemAllocation && ((ExchangeItemAllocation) eObj).getAllocatedItem() != null) {
        // collection of status message
        Collection<IStatus> statuses = new ArrayList<IStatus>();
        
        ExchangeMechanism mechanism = ((ExchangeItemAllocation) eObj).getAllocatedItem().getExchangeMechanism();
        CommunicationLinkProtocol sendProtocol = ((ExchangeItemAllocation) eObj).getSendProtocol();
        Collection<CommunicationLinkProtocol> expectedSendProtocols = LinkCompatibilityDefinition.INSTANCE.getCompatibleSendProtocols((ExchangeItemAllocation)eObj);
        if (!expectedSendProtocols.contains(sendProtocol)) {
          String expected = ListExt.toString(expectedSendProtocols, " || "); //$NON-NLS-1$
          IStatus status =  ctx.createFailureStatus( eObj, mechanism, expected, Messages.ExchangeItemAllocationProtocol_CommunicationLinkProtocol_Sender );
          statuses.add(status);
        }

        CommunicationLinkProtocol receiveProtocol = ((ExchangeItemAllocation) eObj).getReceiveProtocol();
        Collection<CommunicationLinkProtocol> expectedReceiveProtocols = LinkCompatibilityDefinition.INSTANCE.getCompatibleReceiveProtocols((ExchangeItemAllocation)eObj);
        if (!expectedReceiveProtocols.contains(receiveProtocol)) {
          String expected = ListExt.toString(expectedReceiveProtocols, " || "); //$NON-NLS-1$
          IStatus status =  ctx.createFailureStatus(eObj, mechanism, expected, Messages.ExchangeItemAllocationProtocol_CommunicationLinkProtocol_Receiver);
          statuses.add(status);
        }
        if (statuses.size() > 0) {
          return ConstraintStatus.createMultiStatus(ctx, statuses);
        }
      }
    }
    
    return ctx.createSuccessStatus();
  }
   
}
