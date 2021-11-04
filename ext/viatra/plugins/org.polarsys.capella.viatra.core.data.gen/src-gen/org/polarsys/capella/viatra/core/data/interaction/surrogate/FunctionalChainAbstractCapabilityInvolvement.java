/**
 * 
 *   Copyright (c) 2006, 2020 THALES DMS FRANCE.
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
package org.polarsys.capella.viatra.core.data.interaction.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in FunctionalChainAbstractCapabilityInvolvement.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file FunctionalChainAbstractCapabilityInvolvement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.interaction.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>FunctionalChainAbstractCapabilityInvolvement__capability</li>
 * <li>FunctionalChainAbstractCapabilityInvolvement__functionalChain</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class FunctionalChainAbstractCapabilityInvolvement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static FunctionalChainAbstractCapabilityInvolvement instance() {
    if (INSTANCE == null) {
        INSTANCE = new FunctionalChainAbstractCapabilityInvolvement();
    }
    return INSTANCE;
  }
  
  private static FunctionalChainAbstractCapabilityInvolvement INSTANCE;
  
  private FunctionalChainAbstractCapabilityInvolvement() {
    querySpecifications.add(FunctionalChainAbstractCapabilityInvolvement__capability.instance());
    querySpecifications.add(FunctionalChainAbstractCapabilityInvolvement__functionalChain.instance());
  }
  
  public FunctionalChainAbstractCapabilityInvolvement__capability getFunctionalChainAbstractCapabilityInvolvement__capability() {
    return FunctionalChainAbstractCapabilityInvolvement__capability.instance();
  }
  
  public FunctionalChainAbstractCapabilityInvolvement__capability.Matcher getFunctionalChainAbstractCapabilityInvolvement__capability(final ViatraQueryEngine engine) {
    return FunctionalChainAbstractCapabilityInvolvement__capability.Matcher.on(engine);
  }
  
  public FunctionalChainAbstractCapabilityInvolvement__functionalChain getFunctionalChainAbstractCapabilityInvolvement__functionalChain() {
    return FunctionalChainAbstractCapabilityInvolvement__functionalChain.instance();
  }
  
  public FunctionalChainAbstractCapabilityInvolvement__functionalChain.Matcher getFunctionalChainAbstractCapabilityInvolvement__functionalChain(final ViatraQueryEngine engine) {
    return FunctionalChainAbstractCapabilityInvolvement__functionalChain.Matcher.on(engine);
  }
}
