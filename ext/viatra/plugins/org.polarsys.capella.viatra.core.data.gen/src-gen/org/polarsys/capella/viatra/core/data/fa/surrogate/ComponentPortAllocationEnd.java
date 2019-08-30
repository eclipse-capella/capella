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
package org.polarsys.capella.viatra.core.data.fa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentPortAllocationEnd__owningComponentPortAllocationMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentPortAllocationEnd__owningComponentPortAllocationQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in ComponentPortAllocationEnd.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ComponentPortAllocationEnd.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ComponentPortAllocationEnd__owningComponentPortAllocation</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ComponentPortAllocationEnd extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ComponentPortAllocationEnd instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ComponentPortAllocationEnd();
    }
    return INSTANCE;
  }
  
  private static ComponentPortAllocationEnd INSTANCE;
  
  private ComponentPortAllocationEnd() throws ViatraQueryException {
    querySpecifications.add(ComponentPortAllocationEnd__owningComponentPortAllocationQuerySpecification.instance());
  }
  
  public ComponentPortAllocationEnd__owningComponentPortAllocationQuerySpecification getComponentPortAllocationEnd__owningComponentPortAllocation() throws ViatraQueryException {
    return ComponentPortAllocationEnd__owningComponentPortAllocationQuerySpecification.instance();
  }
  
  public ComponentPortAllocationEnd__owningComponentPortAllocationMatcher getComponentPortAllocationEnd__owningComponentPortAllocation(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentPortAllocationEnd__owningComponentPortAllocationMatcher.on(engine);
  }
}
