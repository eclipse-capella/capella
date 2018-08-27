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
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentFunctionalAllocation__blockMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentFunctionalAllocation__functionMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentFunctionalAllocation__blockQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentFunctionalAllocation__functionQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in ComponentFunctionalAllocation.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ComponentFunctionalAllocation.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ComponentFunctionalAllocation__function</li>
 * <li>ComponentFunctionalAllocation__block</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ComponentFunctionalAllocation extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ComponentFunctionalAllocation instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ComponentFunctionalAllocation();
    }
    return INSTANCE;
  }
  
  private static ComponentFunctionalAllocation INSTANCE;
  
  private ComponentFunctionalAllocation() throws ViatraQueryException {
    querySpecifications.add(ComponentFunctionalAllocation__functionQuerySpecification.instance());
    querySpecifications.add(ComponentFunctionalAllocation__blockQuerySpecification.instance());
  }
  
  public ComponentFunctionalAllocation__functionQuerySpecification getComponentFunctionalAllocation__function() throws ViatraQueryException {
    return ComponentFunctionalAllocation__functionQuerySpecification.instance();
  }
  
  public ComponentFunctionalAllocation__functionMatcher getComponentFunctionalAllocation__function(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentFunctionalAllocation__functionMatcher.on(engine);
  }
  
  public ComponentFunctionalAllocation__blockQuerySpecification getComponentFunctionalAllocation__block() throws ViatraQueryException {
    return ComponentFunctionalAllocation__blockQuerySpecification.instance();
  }
  
  public ComponentFunctionalAllocation__blockMatcher getComponentFunctionalAllocation__block(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentFunctionalAllocation__blockMatcher.on(engine);
  }
}
