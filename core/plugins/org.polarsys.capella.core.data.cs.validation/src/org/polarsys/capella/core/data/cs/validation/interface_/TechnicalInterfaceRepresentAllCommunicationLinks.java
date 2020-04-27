/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule checks that technical interfaces are not provided or required
 */
public class TechnicalInterfaceRepresentAllCommunicationLinks extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    if (eObj instanceof Interface) {
      Interface interfaze = (Interface) eObj;
      if (!interfaze.isStructural()) {
        List<Component> userComponents = interfaze.getUserComponents();
        List<Component> implementorComponents = interfaze.getImplementorComponents();
        if (userComponents.size() > 0 && implementorComponents.size() > 0) {
          Component userComponent = userComponents.get(0);
          Component implementorComponent = implementorComponents.get(0);
          List<ExchangeItem> errorInfos = new ArrayList<ExchangeItem>();
          errorInfos.addAll(check(interfaze, userComponent.getTransmit(), implementorComponent.getAcquire()));
          errorInfos.addAll(check(interfaze, userComponent.getSend(), implementorComponent.getReceive()));
          errorInfos.addAll(check(interfaze, userComponent.getProduce(), implementorComponent.getConsume()));
          errorInfos.addAll(check(interfaze, userComponent.getCall(), implementorComponent.getExecute()));
          errorInfos.addAll(check(interfaze, userComponent.getWrite(), implementorComponent.getAccess()));
          if (errorInfos.size() > 0) {
            StringBuilder b = new StringBuilder();
            for (int i = 0; i < errorInfos.size(); i++) {
              b.append(CapellaElementExt.getCapellaExplorerLabel(errorInfos.get(i)));
              if (i < errorInfos.size() - 1) {
                b.append(", "); //$NON-NLS-1$
              }
            }
            return ctx.createFailureStatus(CapellaElementExt.getCapellaExplorerLabel(interfaze),
                CapellaElementExt.getCapellaExplorerLabel(userComponent),
                CapellaElementExt.getCapellaExplorerLabel(implementorComponent), b.toString());
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }

  private List<ExchangeItem> check(Interface interfaze, EList<CommunicationLink> linksFromLeft,
      EList<CommunicationLink> linksFromRight) {
    List<ExchangeItem> items = new ArrayList<ExchangeItem>();
    List<ExchangeItem> itemsFromRight = getExchangeItems(linksFromRight);
    for (ExchangeItem it : getExchangeItems(linksFromLeft)) {
      if (itemsFromRight.contains(it)) {
        if (!interfaze.getExchangeItems().contains(it)) {
          items.add(it);
        }
      }
    }
    return items;
  }

  private List<ExchangeItem> getExchangeItems(List<CommunicationLink> links) {
    List<ExchangeItem> exchangeItems = new ArrayList<ExchangeItem>();
    for (CommunicationLink communicationLink : links) {
      exchangeItems.add(communicationLink.getExchangeItem());
    }
    return exchangeItems;
  }
}