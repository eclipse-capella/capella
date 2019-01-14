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
package org.polarsys.capella.viatra.core.data.interaction.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.FunctionalChainAbstractCapabilityInvolvement__capabilityMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.FunctionalChainAbstractCapabilityInvolvement__functionalChainMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.FunctionalChainAbstractCapabilityInvolvement__capabilityQuerySpecification;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.FunctionalChainAbstractCapabilityInvolvement__functionalChainQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in FunctionalChainAbstractCapabilityInvolvement.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file FunctionalChainAbstractCapabilityInvolvement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.interaction.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>FunctionalChainAbstractCapabilityInvolvement__capability</li>
 * <li>FunctionalChainAbstractCapabilityInvolvement__functionalChain</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class FunctionalChainAbstractCapabilityInvolvement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static FunctionalChainAbstractCapabilityInvolvement instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new FunctionalChainAbstractCapabilityInvolvement();
    }
    return INSTANCE;
  }
  
  private static FunctionalChainAbstractCapabilityInvolvement INSTANCE;
  
  private FunctionalChainAbstractCapabilityInvolvement() throws ViatraQueryException {
    querySpecifications.add(FunctionalChainAbstractCapabilityInvolvement__capabilityQuerySpecification.instance());
    querySpecifications.add(FunctionalChainAbstractCapabilityInvolvement__functionalChainQuerySpecification.instance());
  }
  
  public FunctionalChainAbstractCapabilityInvolvement__capabilityQuerySpecification getFunctionalChainAbstractCapabilityInvolvement__capability() throws ViatraQueryException {
    return FunctionalChainAbstractCapabilityInvolvement__capabilityQuerySpecification.instance();
  }
  
  public FunctionalChainAbstractCapabilityInvolvement__capabilityMatcher getFunctionalChainAbstractCapabilityInvolvement__capability(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalChainAbstractCapabilityInvolvement__capabilityMatcher.on(engine);
  }
  
  public FunctionalChainAbstractCapabilityInvolvement__functionalChainQuerySpecification getFunctionalChainAbstractCapabilityInvolvement__functionalChain() throws ViatraQueryException {
    return FunctionalChainAbstractCapabilityInvolvement__functionalChainQuerySpecification.instance();
  }
  
  public FunctionalChainAbstractCapabilityInvolvement__functionalChainMatcher getFunctionalChainAbstractCapabilityInvolvement__functionalChain(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalChainAbstractCapabilityInvolvement__functionalChainMatcher.on(engine);
  }
}
