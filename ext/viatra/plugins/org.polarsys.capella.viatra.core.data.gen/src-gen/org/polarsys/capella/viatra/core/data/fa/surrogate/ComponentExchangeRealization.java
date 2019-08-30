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
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeRealization__allocatedComponentExchangeMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchangeRealization__allocatingComponentExchangeMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentExchangeRealization__allocatedComponentExchangeQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentExchangeRealization__allocatingComponentExchangeQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in ComponentExchangeRealization.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ComponentExchangeRealization.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ComponentExchangeRealization__allocatedComponentExchange</li>
 * <li>ComponentExchangeRealization__allocatingComponentExchange</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ComponentExchangeRealization extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ComponentExchangeRealization instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ComponentExchangeRealization();
    }
    return INSTANCE;
  }
  
  private static ComponentExchangeRealization INSTANCE;
  
  private ComponentExchangeRealization() throws ViatraQueryException {
    querySpecifications.add(ComponentExchangeRealization__allocatedComponentExchangeQuerySpecification.instance());
    querySpecifications.add(ComponentExchangeRealization__allocatingComponentExchangeQuerySpecification.instance());
  }
  
  public ComponentExchangeRealization__allocatedComponentExchangeQuerySpecification getComponentExchangeRealization__allocatedComponentExchange() throws ViatraQueryException {
    return ComponentExchangeRealization__allocatedComponentExchangeQuerySpecification.instance();
  }
  
  public ComponentExchangeRealization__allocatedComponentExchangeMatcher getComponentExchangeRealization__allocatedComponentExchange(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentExchangeRealization__allocatedComponentExchangeMatcher.on(engine);
  }
  
  public ComponentExchangeRealization__allocatingComponentExchangeQuerySpecification getComponentExchangeRealization__allocatingComponentExchange() throws ViatraQueryException {
    return ComponentExchangeRealization__allocatingComponentExchangeQuerySpecification.instance();
  }
  
  public ComponentExchangeRealization__allocatingComponentExchangeMatcher getComponentExchangeRealization__allocatingComponentExchange(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentExchangeRealization__allocatingComponentExchangeMatcher.on(engine);
  }
}
