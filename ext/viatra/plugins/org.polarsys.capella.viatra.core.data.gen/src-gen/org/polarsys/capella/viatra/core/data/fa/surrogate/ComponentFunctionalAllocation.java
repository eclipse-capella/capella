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
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentFunctionalAllocation__block;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentFunctionalAllocation__function;

/**
 * A pattern group formed of all public patterns defined in ComponentFunctionalAllocation.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ComponentFunctionalAllocation.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ComponentFunctionalAllocation__function</li>
 * <li>ComponentFunctionalAllocation__block</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class ComponentFunctionalAllocation extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ComponentFunctionalAllocation instance() {
    if (INSTANCE == null) {
        INSTANCE = new ComponentFunctionalAllocation();
    }
    return INSTANCE;
  }
  
  private static ComponentFunctionalAllocation INSTANCE;
  
  private ComponentFunctionalAllocation() {
    querySpecifications.add(ComponentFunctionalAllocation__function.instance());
    querySpecifications.add(ComponentFunctionalAllocation__block.instance());
  }
  
  public ComponentFunctionalAllocation__function getComponentFunctionalAllocation__function() {
    return ComponentFunctionalAllocation__function.instance();
  }
  
  public ComponentFunctionalAllocation__function.Matcher getComponentFunctionalAllocation__function(final ViatraQueryEngine engine) {
    return ComponentFunctionalAllocation__function.Matcher.on(engine);
  }
  
  public ComponentFunctionalAllocation__block getComponentFunctionalAllocation__block() {
    return ComponentFunctionalAllocation__block.instance();
  }
  
  public ComponentFunctionalAllocation__block.Matcher getComponentFunctionalAllocation__block(final ViatraQueryEngine engine) {
    return ComponentFunctionalAllocation__block.Matcher.on(engine);
  }
}
