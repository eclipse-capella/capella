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
package org.polarsys.capella.core.data.cs.validation.interface_;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class IntefaceDoesNotContainSimilarExchangeItemAllocation extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
  	Collection<IStatus> statuses = new ArrayList<IStatus>();
    EObject eObj = ctx.getTarget();
    if (eObj instanceof Interface) {
    	Interface interfaze = (Interface) eObj;
      // map of exchagneItem and its incoming communication links
      Map<ExchangeItem, Map<String, List<ExchangeItemAllocation>>> allocationMap = new HashMap<ExchangeItem, Map<String, List<ExchangeItemAllocation>>>();
      for (ExchangeItemAllocation allocation : interfaze.getOwnedExchangeItemAllocations()) {
        AbstractExchangeItem exchangeItem = allocation.getAllocatedItem();
        Map<String, List<ExchangeItemAllocation>> signature2Links = allocationMap.get(exchangeItem);
        if (signature2Links == null) {
        	signature2Links = new HashMap<String, List<ExchangeItemAllocation>>();
        	allocationMap.put((ExchangeItem)exchangeItem, signature2Links);
        }
        String signature = allocation.getSendProtocol().toString()+allocation.getReceiveProtocol();
        List<ExchangeItemAllocation> links = signature2Links.get(signature);
        if (links == null) {
        	links = new ArrayList<ExchangeItemAllocation>();
        	signature2Links.put(signature, links);
        }
        links.add(allocation);
      }
      // create error message if same kind of communication link target is same ExchageItem 
      for (ExchangeItem exchangeItem : allocationMap.keySet()) {
      	Map<String, List<ExchangeItemAllocation>> signature2Links = allocationMap.get(exchangeItem);
      	for (String signature : signature2Links.keySet()) {
      		List<ExchangeItemAllocation> allocations = signature2Links.get(signature);
      		if (allocations.size() > 1) {
      			ExchangeItemAllocation firstAllocation = allocations.get(0);
      			statuses.add(ctx.createFailureStatus(
      					CapellaElementExt.getCapellaExplorerLabel(interfaze),
      					allocations.size(),
      					firstAllocation.getSendProtocol(),
      					firstAllocation.getReceiveProtocol(),
      					CapellaElementExt.getCapellaExplorerLabel(exchangeItem)));
      		}
      	}        	
      }
    }
	  if (statuses.size() > 0) {
	  	return ConstraintStatus.createMultiStatus(ctx, statuses);
	  } else {
	  	return ctx.createSuccessStatus();
	  }	  	
  }

}
