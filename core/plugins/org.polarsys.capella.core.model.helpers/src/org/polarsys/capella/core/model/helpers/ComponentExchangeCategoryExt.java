/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.helpers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeCategory;

public class ComponentExchangeCategoryExt {
  /**
   * 
   * @param category
   * @return component exchanges that are covered by the given category
   */
  public static Collection<ComponentExchange> getCoveredComponentExchanges(ComponentExchangeCategory category) {
    Set<ComponentExchange> coveredCEs = new HashSet<>();
    coveredCEs.addAll(category.getExchanges());
    // Delegated CEs are also considered to be covered by the category
    for (ComponentExchange ce : category.getExchanges()) {
      coveredCEs.addAll(ComponentExchangeExt.getDelegatedComponentExchanges((ComponentExchange) ce));
    }
    return coveredCEs;
  }
}
