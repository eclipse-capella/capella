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
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeAllocation__componentExchangeAllocatedMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeAllocation__componentExchangeAllocatorMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentExchangeAllocation__componentExchangeAllocatedQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentExchangeAllocation__componentExchangeAllocatorQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in ComponentExchangeAllocation.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ComponentExchangeAllocation.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ComponentExchangeAllocation__componentExchangeAllocated</li>
 * <li>ComponentExchangeAllocation__componentExchangeAllocator</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ComponentExchangeAllocation extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ComponentExchangeAllocation instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ComponentExchangeAllocation();
    }
    return INSTANCE;
  }
  
  private static ComponentExchangeAllocation INSTANCE;
  
  private ComponentExchangeAllocation() throws ViatraQueryException {
    querySpecifications.add(ComponentExchangeAllocation__componentExchangeAllocatedQuerySpecification.instance());
    querySpecifications.add(ComponentExchangeAllocation__componentExchangeAllocatorQuerySpecification.instance());
  }
  
  public ComponentExchangeAllocation__componentExchangeAllocatedQuerySpecification getComponentExchangeAllocation__componentExchangeAllocated() throws ViatraQueryException {
    return ComponentExchangeAllocation__componentExchangeAllocatedQuerySpecification.instance();
  }
  
  public ComponentExchangeAllocation__componentExchangeAllocatedMatcher getComponentExchangeAllocation__componentExchangeAllocated(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentExchangeAllocation__componentExchangeAllocatedMatcher.on(engine);
  }
  
  public ComponentExchangeAllocation__componentExchangeAllocatorQuerySpecification getComponentExchangeAllocation__componentExchangeAllocator() throws ViatraQueryException {
    return ComponentExchangeAllocation__componentExchangeAllocatorQuerySpecification.instance();
  }
  
  public ComponentExchangeAllocation__componentExchangeAllocatorMatcher getComponentExchangeAllocation__componentExchangeAllocator(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentExchangeAllocation__componentExchangeAllocatorMatcher.on(engine);
  }
}
