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
package org.polarsys.capella.viatra.core.data.capellacommon.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.State__availableAbstractCapabilitiesMatcher;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.State__availableAbstractFunctionsMatcher;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.State__availableFunctionalChainsMatcher;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.util.State__availableAbstractCapabilitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.util.State__availableAbstractFunctionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.util.State__availableFunctionalChainsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in State.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file State.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacommon.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>State__availableAbstractFunctions</li>
 * <li>State__availableFunctionalChains</li>
 * <li>State__availableAbstractCapabilities</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class State extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static State instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new State();
    }
    return INSTANCE;
  }
  
  private static State INSTANCE;
  
  private State() throws ViatraQueryException {
    querySpecifications.add(State__availableAbstractFunctionsQuerySpecification.instance());
    querySpecifications.add(State__availableFunctionalChainsQuerySpecification.instance());
    querySpecifications.add(State__availableAbstractCapabilitiesQuerySpecification.instance());
  }
  
  public State__availableAbstractFunctionsQuerySpecification getState__availableAbstractFunctions() throws ViatraQueryException {
    return State__availableAbstractFunctionsQuerySpecification.instance();
  }
  
  public State__availableAbstractFunctionsMatcher getState__availableAbstractFunctions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return State__availableAbstractFunctionsMatcher.on(engine);
  }
  
  public State__availableFunctionalChainsQuerySpecification getState__availableFunctionalChains() throws ViatraQueryException {
    return State__availableFunctionalChainsQuerySpecification.instance();
  }
  
  public State__availableFunctionalChainsMatcher getState__availableFunctionalChains(final ViatraQueryEngine engine) throws ViatraQueryException {
    return State__availableFunctionalChainsMatcher.on(engine);
  }
  
  public State__availableAbstractCapabilitiesQuerySpecification getState__availableAbstractCapabilities() throws ViatraQueryException {
    return State__availableAbstractCapabilitiesQuerySpecification.instance();
  }
  
  public State__availableAbstractCapabilitiesMatcher getState__availableAbstractCapabilities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return State__availableAbstractCapabilitiesMatcher.on(engine);
  }
}
