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

/**
 * A pattern group formed of all public patterns defined in ComponentPortAllocation.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ComponentPortAllocation.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ComponentPortAllocation__allocatedPort</li>
 * <li>ComponentPortAllocation__allocatingPort</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class ComponentPortAllocation extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ComponentPortAllocation instance() {
    if (INSTANCE == null) {
        INSTANCE = new ComponentPortAllocation();
    }
    return INSTANCE;
  }
  
  private static ComponentPortAllocation INSTANCE;
  
  private ComponentPortAllocation() {
    querySpecifications.add(ComponentPortAllocation__allocatedPort.instance());
    querySpecifications.add(ComponentPortAllocation__allocatingPort.instance());
  }
  
  public ComponentPortAllocation__allocatedPort getComponentPortAllocation__allocatedPort() {
    return ComponentPortAllocation__allocatedPort.instance();
  }
  
  public ComponentPortAllocation__allocatedPort.Matcher getComponentPortAllocation__allocatedPort(final ViatraQueryEngine engine) {
    return ComponentPortAllocation__allocatedPort.Matcher.on(engine);
  }
  
  public ComponentPortAllocation__allocatingPort getComponentPortAllocation__allocatingPort() {
    return ComponentPortAllocation__allocatingPort.instance();
  }
  
  public ComponentPortAllocation__allocatingPort.Matcher getComponentPortAllocation__allocatingPort(final ViatraQueryEngine engine) {
    return ComponentPortAllocation__allocatingPort.Matcher.on(engine);
  }
}
