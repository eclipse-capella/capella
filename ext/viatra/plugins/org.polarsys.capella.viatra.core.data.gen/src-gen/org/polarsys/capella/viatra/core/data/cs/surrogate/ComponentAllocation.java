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
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.cs.surrogate.ComponentAllocation__allocatedComponentMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.ComponentAllocation__allocatingComponentMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.ComponentAllocation__allocatedComponentQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.ComponentAllocation__allocatingComponentQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in ComponentAllocation.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ComponentAllocation.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ComponentAllocation__allocatedComponent</li>
 * <li>ComponentAllocation__allocatingComponent</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ComponentAllocation extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ComponentAllocation instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ComponentAllocation();
    }
    return INSTANCE;
  }
  
  private static ComponentAllocation INSTANCE;
  
  private ComponentAllocation() throws ViatraQueryException {
    querySpecifications.add(ComponentAllocation__allocatedComponentQuerySpecification.instance());
    querySpecifications.add(ComponentAllocation__allocatingComponentQuerySpecification.instance());
  }
  
  public ComponentAllocation__allocatedComponentQuerySpecification getComponentAllocation__allocatedComponent() throws ViatraQueryException {
    return ComponentAllocation__allocatedComponentQuerySpecification.instance();
  }
  
  public ComponentAllocation__allocatedComponentMatcher getComponentAllocation__allocatedComponent(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentAllocation__allocatedComponentMatcher.on(engine);
  }
  
  public ComponentAllocation__allocatingComponentQuerySpecification getComponentAllocation__allocatingComponent() throws ViatraQueryException {
    return ComponentAllocation__allocatingComponentQuerySpecification.instance();
  }
  
  public ComponentAllocation__allocatingComponentMatcher getComponentAllocation__allocatingComponent(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentAllocation__allocatingComponentMatcher.on(engine);
  }
}
