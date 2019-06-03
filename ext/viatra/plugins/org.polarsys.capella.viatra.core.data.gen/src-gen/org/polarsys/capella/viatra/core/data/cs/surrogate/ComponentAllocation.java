/**
 * 
 *   Copyright (c) 2006, 2019 THALES DMS FRANCE.
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License v1.0
 *   which accompanies this distribution, and is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 *  
 *   Contributors:
 *      Thales - initial API and implementation
 *  
 */
package org.polarsys.capella.viatra.core.data.cs.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.cs.surrogate.ComponentAllocation__allocatedComponent;
import org.polarsys.capella.viatra.core.data.cs.surrogate.ComponentAllocation__allocatingComponent;

/**
 * A pattern group formed of all public patterns defined in ComponentAllocation.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ComponentAllocation.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ComponentAllocation__allocatedComponent</li>
 * <li>ComponentAllocation__allocatingComponent</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class ComponentAllocation extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ComponentAllocation instance() {
    if (INSTANCE == null) {
        INSTANCE = new ComponentAllocation();
    }
    return INSTANCE;
  }
  
  private static ComponentAllocation INSTANCE;
  
  private ComponentAllocation() {
    querySpecifications.add(ComponentAllocation__allocatedComponent.instance());
    querySpecifications.add(ComponentAllocation__allocatingComponent.instance());
  }
  
  public ComponentAllocation__allocatedComponent getComponentAllocation__allocatedComponent() {
    return ComponentAllocation__allocatedComponent.instance();
  }
  
  public ComponentAllocation__allocatedComponent.Matcher getComponentAllocation__allocatedComponent(final ViatraQueryEngine engine) {
    return ComponentAllocation__allocatedComponent.Matcher.on(engine);
  }
  
  public ComponentAllocation__allocatingComponent getComponentAllocation__allocatingComponent() {
    return ComponentAllocation__allocatingComponent.instance();
  }
  
  public ComponentAllocation__allocatingComponent.Matcher getComponentAllocation__allocatingComponent(final ViatraQueryEngine engine) {
    return ComponentAllocation__allocatingComponent.Matcher.on(engine);
  }
}
