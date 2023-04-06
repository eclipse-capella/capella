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

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 * 
 * Return internal incoming component exchanges
 * 
 */
public class ComponentInternalIncomingComponentExchanges extends ComponentIncomingComponentExchange {

  @Override
  protected Collection<ComponentExchange> getExchanges(Object object) {
    List<ComponentExchange> exchanges = new ArrayList<>();

    Collection<Part> usedParts = ComponentExt.getAllSubUsedParts((Component) object, true);
    for (Part part : usedParts) {
      for (ComponentExchange e : ComponentExt.getAllRelatedComponentExchange(part, true)) {
        if (super.isValid(e, part.getAbstractType())) {
          exchanges.add(e);
        }
      }
    }
    usedParts.addAll(getCache(ComponentExt::getRepresentingParts, (Component) object));
    
    List<ComponentExchange> result = new ArrayList<>();

    // If source or target is one of the used parts or representing parts, filter
    for (ComponentExchange pl : exchanges) {
      if (!ListExt.containsAny(ComponentExchangeExt.getSourceParts(pl), usedParts)) {
        result.add(pl);
      }
      if (!ListExt.containsAny(ComponentExchangeExt.getTargetParts(pl), usedParts)) {
        result.add(pl);
      }
    }

    return result;
  }
  

  @Override
  protected boolean isValid(ComponentExchange exchange, Object object) {
    return !ComponentExchangeExt.isDelegation(exchange);
  }


}
