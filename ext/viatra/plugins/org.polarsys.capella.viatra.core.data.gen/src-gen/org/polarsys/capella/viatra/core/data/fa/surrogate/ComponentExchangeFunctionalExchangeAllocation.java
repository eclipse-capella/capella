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
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchangeMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeFunctionalExchangeAllocation__allocatingComponentExchangeMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchangeQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentExchangeFunctionalExchangeAllocation__allocatingComponentExchangeQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in ComponentExchangeFunctionalExchangeAllocation.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ComponentExchangeFunctionalExchangeAllocation.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange</li>
 * <li>ComponentExchangeFunctionalExchangeAllocation__allocatingComponentExchange</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ComponentExchangeFunctionalExchangeAllocation extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ComponentExchangeFunctionalExchangeAllocation instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ComponentExchangeFunctionalExchangeAllocation();
    }
    return INSTANCE;
  }
  
  private static ComponentExchangeFunctionalExchangeAllocation INSTANCE;
  
  private ComponentExchangeFunctionalExchangeAllocation() throws ViatraQueryException {
    querySpecifications.add(ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchangeQuerySpecification.instance());
    querySpecifications.add(ComponentExchangeFunctionalExchangeAllocation__allocatingComponentExchangeQuerySpecification.instance());
  }
  
  public ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchangeQuerySpecification getComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange() throws ViatraQueryException {
    return ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchangeQuerySpecification.instance();
  }
  
  public ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchangeMatcher getComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchange(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentExchangeFunctionalExchangeAllocation__allocatedFunctionalExchangeMatcher.on(engine);
  }
  
  public ComponentExchangeFunctionalExchangeAllocation__allocatingComponentExchangeQuerySpecification getComponentExchangeFunctionalExchangeAllocation__allocatingComponentExchange() throws ViatraQueryException {
    return ComponentExchangeFunctionalExchangeAllocation__allocatingComponentExchangeQuerySpecification.instance();
  }
  
  public ComponentExchangeFunctionalExchangeAllocation__allocatingComponentExchangeMatcher getComponentExchangeFunctionalExchangeAllocation__allocatingComponentExchange(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentExchangeFunctionalExchangeAllocation__allocatingComponentExchangeMatcher.on(engine);
  }
}
