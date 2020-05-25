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
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeAllocation__componentExchangeAllocated;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeAllocation__componentExchangeAllocator;

/**
 * A pattern group formed of all public patterns defined in ComponentExchangeAllocation.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ComponentExchangeAllocation.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ComponentExchangeAllocation__componentExchangeAllocated</li>
 * <li>ComponentExchangeAllocation__componentExchangeAllocator</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class ComponentExchangeAllocation extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ComponentExchangeAllocation instance() {
    if (INSTANCE == null) {
        INSTANCE = new ComponentExchangeAllocation();
    }
    return INSTANCE;
  }
  
  private static ComponentExchangeAllocation INSTANCE;
  
  private ComponentExchangeAllocation() {
    querySpecifications.add(ComponentExchangeAllocation__componentExchangeAllocated.instance());
    querySpecifications.add(ComponentExchangeAllocation__componentExchangeAllocator.instance());
  }
  
  public ComponentExchangeAllocation__componentExchangeAllocated getComponentExchangeAllocation__componentExchangeAllocated() {
    return ComponentExchangeAllocation__componentExchangeAllocated.instance();
  }
  
  public ComponentExchangeAllocation__componentExchangeAllocated.Matcher getComponentExchangeAllocation__componentExchangeAllocated(final ViatraQueryEngine engine) {
    return ComponentExchangeAllocation__componentExchangeAllocated.Matcher.on(engine);
  }
  
  public ComponentExchangeAllocation__componentExchangeAllocator getComponentExchangeAllocation__componentExchangeAllocator() {
    return ComponentExchangeAllocation__componentExchangeAllocator.instance();
  }
  
  public ComponentExchangeAllocation__componentExchangeAllocator.Matcher getComponentExchangeAllocation__componentExchangeAllocator(final ViatraQueryEngine engine) {
    return ComponentExchangeAllocation__componentExchangeAllocator.Matcher.on(engine);
  }
}
