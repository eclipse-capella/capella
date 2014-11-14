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
package org.polarsys.capella.core.data.cs.validation.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;

/**
 * This rule ensure that a component can't have same kind of communication link targeting to same exchange item. 
 */
public class ComponentCommunicationLinkRule extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Component) {
        // collection of status message
        Collection<IStatus> statuses = new ArrayList<IStatus>();
        // current component
        Component comp = (Component) eObj;
        // map of exchagneItem and its incoming communication links
        Map<ExchangeItem, List<CommunicationLink>> communicaitionLinkMap = new HashMap<ExchangeItem, List<CommunicationLink>>();
        // fill the map
        EList<CommunicationLink> ownedCommunicationLinks = comp.getOwnedCommunicationLinks();
        for (CommunicationLink communicationLink : ownedCommunicationLinks) {
          AbstractExchangeItem exchangeItem = communicationLink.getExchangeItem();
          List<CommunicationLink> list = communicaitionLinkMap.get(exchangeItem);
          if (null != list && !list.isEmpty()) {
            List<CommunicationLink> cLink = new ArrayList<CommunicationLink>();
            cLink.addAll(list);
            cLink.add(communicationLink);
            communicaitionLinkMap.put((ExchangeItem)exchangeItem, cLink);
          }else
          communicaitionLinkMap.put((ExchangeItem)exchangeItem, Collections.singletonList(communicationLink)); 
        }

        // create error message if same kind of communication link target is same ExchageItem 
        Set<ExchangeItem> keySet = communicaitionLinkMap.keySet();
        for (ExchangeItem exchangeItem : keySet) {
          List<CommunicationLink> links = communicaitionLinkMap.get(exchangeItem);
          if (links.size()>0) {
            List<CommunicationLinkKind> kindList = new ArrayList<CommunicationLinkKind>();
            for (CommunicationLink link : links) {
              CommunicationLinkKind kind = link.getKind();
              if (!kindList.contains(kind)) {
                kindList.add(kind);                
              }else{
                // add error message
                IStatus status = createFailureStatus(ctx_p, new Object[] { comp.getName(),link.getLabel(), kind.getName(), exchangeItem.getName()});
                statuses.add(status);
                break;
              }
            }
          }
        }
        // multiStatus message creation
        if (statuses.size() > 0) {
          return ConstraintStatus.createMultiStatus(ctx_p, statuses);
        }

      }
    }

    return ctx_p.createSuccessStatus();
  }

}
