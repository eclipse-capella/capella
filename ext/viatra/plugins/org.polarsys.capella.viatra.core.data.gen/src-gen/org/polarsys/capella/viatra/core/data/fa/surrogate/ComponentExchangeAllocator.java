/**
 * 
 *   Copyright (c) 2006, 2020 THALES DMS FRANCE.
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
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeAllocator__allocatedComponentExchanges;

/**
 * A pattern group formed of all public patterns defined in ComponentExchangeAllocator.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ComponentExchangeAllocator.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ComponentExchangeAllocator__allocatedComponentExchanges</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class ComponentExchangeAllocator extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ComponentExchangeAllocator instance() {
    if (INSTANCE == null) {
        INSTANCE = new ComponentExchangeAllocator();
    }
    return INSTANCE;
  }
  
  private static ComponentExchangeAllocator INSTANCE;
  
  private ComponentExchangeAllocator() {
    querySpecifications.add(ComponentExchangeAllocator__allocatedComponentExchanges.instance());
  }
  
  public ComponentExchangeAllocator__allocatedComponentExchanges getComponentExchangeAllocator__allocatedComponentExchanges() {
    return ComponentExchangeAllocator__allocatedComponentExchanges.instance();
  }
  
  public ComponentExchangeAllocator__allocatedComponentExchanges.Matcher getComponentExchangeAllocator__allocatedComponentExchanges(final ViatraQueryEngine engine) {
    return ComponentExchangeAllocator__allocatedComponentExchanges.Matcher.on(engine);
  }
}
