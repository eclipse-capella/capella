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
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;

/**
 *  This rule ensure that a some CommunicationLink kind are only used with specific CommunicatinLink protocol 
 */
public class CommunicationLinkkindRule extends AbstractValidationRule {
 
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof CommunicationLink) {
        // collection of status message
        Collection<IStatus> statuses = new ArrayList<IStatus>();
        
        CommunicationLink link = (CommunicationLink) eObj;
        CommunicationLinkKind kind = link.getKind();
        AbstractExchangeItem exchangeItem = link.getExchangeItem();
        
        if (null != exchangeItem && exchangeItem instanceof ExchangeItem) {
          ExchangeItem item  = (ExchangeItem) exchangeItem;
          ExchangeMechanism exchangeMechanism = item.getExchangeMechanism();
         
          if (kind != null) {
            if (kind == CommunicationLinkKind.PRODUCE || kind == CommunicationLinkKind.CONSUME) {
              String expectedKind = ICommonConstants.EMPTY_STRING;
              String expectedExchangeMechanism = ICommonConstants.EMPTY_STRING;
              if (exchangeMechanism != ExchangeMechanism.FLOW) {
                expectedKind = CommunicationLinkKind.PRODUCE.getName() + "||" + CommunicationLinkKind.CONSUME.getName(); //$NON-NLS-1$
                expectedExchangeMechanism = ExchangeMechanism.FLOW.getName();
                // message : communicationLink + exchangeItem + expected communication link kind + expected exchangeItem kind
                IStatus status =  createFailureStatus(ctx_p, new Object[] { link.getLabel(), item.getName() ,expectedKind,expectedExchangeMechanism});
                statuses.add(status);
              }
            }
            if (kind == CommunicationLinkKind.SEND || kind == CommunicationLinkKind.RECEIVE) {
              String expectedKind = ICommonConstants.EMPTY_STRING;
              String expectedExchangeMechanism = ICommonConstants.EMPTY_STRING;
              if (exchangeMechanism != ExchangeMechanism.EVENT) {
                expectedKind =CommunicationLinkKind.SEND.getName() + "||" + CommunicationLinkKind.RECEIVE.getName(); //$NON-NLS-1$
                expectedExchangeMechanism = ExchangeMechanism.EVENT.getName();
                IStatus status =  createFailureStatus(ctx_p, new Object[] { link.getLabel(), item.getName() ,expectedKind,expectedExchangeMechanism});
                statuses.add(status);
              }
            }
            if (kind == CommunicationLinkKind.CALL   || kind == CommunicationLinkKind.EXECUTE) {
              String expectedKind = ICommonConstants.EMPTY_STRING;
              String expectedExchangeMechanism = ICommonConstants.EMPTY_STRING;
              if (exchangeMechanism != ExchangeMechanism.OPERATION) {
                expectedKind =CommunicationLinkKind.CALL.getName() + "||" + CommunicationLinkKind.EXECUTE.getName(); //$NON-NLS-1$
                expectedExchangeMechanism = ExchangeMechanism.OPERATION.getName();
                IStatus status =  createFailureStatus(ctx_p, new Object[] { link.getLabel(), item.getName() ,expectedKind,expectedExchangeMechanism});
                statuses.add(status);
              }
            }
            if (kind == CommunicationLinkKind.WRITE   || kind == CommunicationLinkKind.ACCESS) {
              String expectedKind = ICommonConstants.EMPTY_STRING;
              String expectedExchangeMechanism = ICommonConstants.EMPTY_STRING;
              if (exchangeMechanism != ExchangeMechanism.SHARED_DATA) {
                expectedKind =CommunicationLinkKind.WRITE.getName() + "||" + CommunicationLinkKind.ACCESS.getName(); //$NON-NLS-1$
                expectedExchangeMechanism = ExchangeMechanism.SHARED_DATA.getName();
                IStatus status =  createFailureStatus(ctx_p, new Object[] { link.getLabel(), item.getName() ,expectedKind,expectedExchangeMechanism});
                statuses.add(status);
              }
            }
          }
          if(statuses.size()>0){
            return ConstraintStatus.createMultiStatus(ctx_p, statuses);
          }
        }
        
      }
      //
    }
    
    return ctx_p.createSuccessStatus();
  }
   
}
