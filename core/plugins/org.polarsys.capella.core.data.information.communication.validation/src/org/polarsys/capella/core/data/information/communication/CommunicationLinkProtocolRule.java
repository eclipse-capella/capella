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
package org.polarsys.capella.core.data.information.communication;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensure that a some CommunicationLink protocol are only used with specific CommunicatinLink kind 
 *
 */
public class CommunicationLinkProtocolRule extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof CommunicationLink) {
        // collection of status message
        Collection<IStatus> statuses = new ArrayList<IStatus>();

        CommunicationLink link = (CommunicationLink) eObj;
        CommunicationLinkProtocol protocol = link.getProtocol();
        CommunicationLinkKind kind = link.getKind();

        if (null != protocol && null != kind) {
          if (protocol == CommunicationLinkProtocol.ASYNCHRONOUS || protocol == CommunicationLinkProtocol.SYNCHRONOUS) {
            String expectedProtocol = ICommonConstants.EMPTY_STRING;
            String expectedKind = ICommonConstants.EMPTY_STRING;
            if (kind != CommunicationLinkKind.CALL) {
              expectedProtocol = CommunicationLinkProtocol.ASYNCHRONOUS.getName() + " || " + CommunicationLinkProtocol.SYNCHRONOUS.getName(); //$NON-NLS-1$
              expectedKind = CommunicationLinkKind.CALL.getName();
              // message : CommunicatinLink + kind + protocol 
              IStatus status = createFailureStatus(ctx_p, new Object[] { link.getLabel(),expectedKind, expectedProtocol});
              statuses.add(status);
            }
          }
          if (protocol == CommunicationLinkProtocol.UNICAST || protocol == CommunicationLinkProtocol.BROADCAST
              || protocol == CommunicationLinkProtocol.MULTICAST) {
            String expectedProtocol = ICommonConstants.EMPTY_STRING;
            String expectedKind = ICommonConstants.EMPTY_STRING;
            if (kind != CommunicationLinkKind.SEND) {
              expectedProtocol =
                  CommunicationLinkProtocol.UNICAST.getName()
                      + " || " + CommunicationLinkProtocol.BROADCAST.getName() + " || " + CommunicationLinkProtocol.MULTICAST; //$NON-NLS-1$ //$NON-NLS-2$
              expectedKind = CommunicationLinkKind.SEND.getName();
              // message : CommunicatinLink  + kind + protocol  
              IStatus status = createFailureStatus(ctx_p, new Object[] { link.getLabel(),expectedKind, expectedProtocol});
              statuses.add(status);
            }
          }
          if (protocol == CommunicationLinkProtocol.READ || protocol == CommunicationLinkProtocol.ACCEPT) {
            String expectedProtocol = ICommonConstants.EMPTY_STRING;
            String expectedKind = ICommonConstants.EMPTY_STRING;
            if (kind != CommunicationLinkKind.ACCESS) {
              expectedProtocol = CommunicationLinkProtocol.READ.getName() + " || " + CommunicationLinkProtocol.ACCEPT.getName(); //$NON-NLS-1$
              expectedKind = CommunicationLinkKind.ACCESS.getName();
              // message : CommunicatinLink  + kind +  protocol
              IStatus status = createFailureStatus(ctx_p, new Object[] { link.getLabel(),expectedKind, expectedProtocol});
              statuses.add(status);
            }
          }
        }

        if (statuses.size() > 0) {
          return ConstraintStatus.createMultiStatus(ctx_p, statuses);
        }

      }
    }

    return ctx_p.createSuccessStatus();
  }

}
