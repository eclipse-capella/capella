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
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeAllocator__allocatedComponentExchangesMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentExchangeAllocator__allocatedComponentExchangesQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in ComponentExchangeAllocator.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ComponentExchangeAllocator.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ComponentExchangeAllocator__allocatedComponentExchanges</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ComponentExchangeAllocator extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ComponentExchangeAllocator instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ComponentExchangeAllocator();
    }
    return INSTANCE;
  }
  
  private static ComponentExchangeAllocator INSTANCE;
  
  private ComponentExchangeAllocator() throws ViatraQueryException {
    querySpecifications.add(ComponentExchangeAllocator__allocatedComponentExchangesQuerySpecification.instance());
  }
  
  public ComponentExchangeAllocator__allocatedComponentExchangesQuerySpecification getComponentExchangeAllocator__allocatedComponentExchanges() throws ViatraQueryException {
    return ComponentExchangeAllocator__allocatedComponentExchangesQuerySpecification.instance();
  }
  
  public ComponentExchangeAllocator__allocatedComponentExchangesMatcher getComponentExchangeAllocator__allocatedComponentExchanges(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentExchangeAllocator__allocatedComponentExchangesMatcher.on(engine);
  }
}
