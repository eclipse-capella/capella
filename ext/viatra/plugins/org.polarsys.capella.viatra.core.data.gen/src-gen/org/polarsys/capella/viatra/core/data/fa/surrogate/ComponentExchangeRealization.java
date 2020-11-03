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
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeRealization__allocatedComponentExchange;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeRealization__allocatingComponentExchange;

/**
 * A pattern group formed of all public patterns defined in ComponentExchangeRealization.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ComponentExchangeRealization.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ComponentExchangeRealization__allocatedComponentExchange</li>
 * <li>ComponentExchangeRealization__allocatingComponentExchange</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class ComponentExchangeRealization extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ComponentExchangeRealization instance() {
    if (INSTANCE == null) {
        INSTANCE = new ComponentExchangeRealization();
    }
    return INSTANCE;
  }
  
  private static ComponentExchangeRealization INSTANCE;
  
  private ComponentExchangeRealization() {
    querySpecifications.add(ComponentExchangeRealization__allocatedComponentExchange.instance());
    querySpecifications.add(ComponentExchangeRealization__allocatingComponentExchange.instance());
  }
  
  public ComponentExchangeRealization__allocatedComponentExchange getComponentExchangeRealization__allocatedComponentExchange() {
    return ComponentExchangeRealization__allocatedComponentExchange.instance();
  }
  
  public ComponentExchangeRealization__allocatedComponentExchange.Matcher getComponentExchangeRealization__allocatedComponentExchange(final ViatraQueryEngine engine) {
    return ComponentExchangeRealization__allocatedComponentExchange.Matcher.on(engine);
  }
  
  public ComponentExchangeRealization__allocatingComponentExchange getComponentExchangeRealization__allocatingComponentExchange() {
    return ComponentExchangeRealization__allocatingComponentExchange.instance();
  }
  
  public ComponentExchangeRealization__allocatingComponentExchange.Matcher getComponentExchangeRealization__allocatingComponentExchange(final ViatraQueryEngine engine) {
    return ComponentExchangeRealization__allocatingComponentExchange.Matcher.on(engine);
  }
}
