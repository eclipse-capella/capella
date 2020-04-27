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
package org.polarsys.capella.core.data.information.communication;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.core.data.helpers.information.services.LinkCompatibilityDefinition;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 *  This rule ensure that a some CommunicationLink kind are only used with specific CommunicatinLink protocol 
 */
public class CommunicationLinkkindRule extends AbstractValidationRule {
 
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
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
            if (!LinkCompatibilityDefinition.INSTANCE.isDefaultKind(kind)) {
              ExchangeMechanism defaultMechanism = LinkCompatibilityDefinition.INSTANCE.getMechanism(kind);
              if (exchangeMechanism != defaultMechanism) {
                IStatus status =  ctx.createFailureStatus(link, kind, defaultMechanism, item, exchangeMechanism);
                statuses.add(status);
              }
            }
            
          }
          if(statuses.size()>0){
            return ConstraintStatus.createMultiStatus(ctx, statuses);
          }
        }
        
      }
    }
    
    return ctx.createSuccessStatus();
  }
   
}
