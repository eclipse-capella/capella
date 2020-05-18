/**
 * 
 *   Copyright (c) 2006, 2019 THALES DMS FRANCE.
 *   
 *   This program and the accompanying materials are made available under the
 *   terms of the Eclipse Public License 2.0 which is available at
 *   http://www.eclipse.org/legal/epl-2.0
 *   
 *   SPDX-License-Identifier: EPL-2.0
 *  
 *   Contributors:
 *      Thales - initial API and implementation
 *  
 */
package org.polarsys.capella.viatra.core.data.fa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeFunctionalExchangeAllocation__allocatingComponentExchange;

/**
 * A pattern group formed of all public patterns defined in ComponentExchangeFunctionalExchangeAllocation.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ComponentExchangeFunctionalExchangeAllocation.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange</li>
 * <li>ComponentExchangeFunctionalExchangeAllocation__allocatingComponentExchange</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class ComponentExchangeFunctionalExchangeAllocation extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ComponentExchangeFunctionalExchangeAllocation instance() {
    if (INSTANCE == null) {
        INSTANCE = new ComponentExchangeFunctionalExchangeAllocation();
    }
    return INSTANCE;
  }
  
  private static ComponentExchangeFunctionalExchangeAllocation INSTANCE;
  
  private ComponentExchangeFunctionalExchangeAllocation() {
    querySpecifications.add(ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.instance());
    querySpecifications.add(ComponentExchangeFunctionalExchangeAllocation__allocatingComponentExchange.instance());
  }
  
  public ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange getComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange() {
    return ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.instance();
  }
  
  public ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Matcher getComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange(final ViatraQueryEngine engine) {
    return ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange.Matcher.on(engine);
  }
  
  public ComponentExchangeFunctionalExchangeAllocation__allocatingComponentExchange getComponentExchangeFunctionalExchangeAllocation__allocatingComponentExchange() {
    return ComponentExchangeFunctionalExchangeAllocation__allocatingComponentExchange.instance();
  }
  
  public ComponentExchangeFunctionalExchangeAllocation__allocatingComponentExchange.Matcher getComponentExchangeFunctionalExchangeAllocation__allocatingComponentExchange(final ViatraQueryEngine engine) {
    return ComponentExchangeFunctionalExchangeAllocation__allocatingComponentExchange.Matcher.on(engine);
  }
}
