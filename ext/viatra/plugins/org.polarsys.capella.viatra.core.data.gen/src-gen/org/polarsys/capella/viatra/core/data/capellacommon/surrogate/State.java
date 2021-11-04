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
package org.polarsys.capella.viatra.core.data.capellacommon.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in State.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file State.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacommon.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>State__availableAbstractFunctions</li>
 * <li>State__availableFunctionalChains</li>
 * <li>State__availableAbstractCapabilities</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class State extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static State instance() {
    if (INSTANCE == null) {
        INSTANCE = new State();
    }
    return INSTANCE;
  }
  
  private static State INSTANCE;
  
  private State() {
    querySpecifications.add(State__availableAbstractFunctions.instance());
    querySpecifications.add(State__availableFunctionalChains.instance());
    querySpecifications.add(State__availableAbstractCapabilities.instance());
  }
  
  public State__availableAbstractFunctions getState__availableAbstractFunctions() {
    return State__availableAbstractFunctions.instance();
  }
  
  public State__availableAbstractFunctions.Matcher getState__availableAbstractFunctions(final ViatraQueryEngine engine) {
    return State__availableAbstractFunctions.Matcher.on(engine);
  }
  
  public State__availableFunctionalChains getState__availableFunctionalChains() {
    return State__availableFunctionalChains.instance();
  }
  
  public State__availableFunctionalChains.Matcher getState__availableFunctionalChains(final ViatraQueryEngine engine) {
    return State__availableFunctionalChains.Matcher.on(engine);
  }
  
  public State__availableAbstractCapabilities getState__availableAbstractCapabilities() {
    return State__availableAbstractCapabilities.instance();
  }
  
  public State__availableAbstractCapabilities.Matcher getState__availableAbstractCapabilities(final ViatraQueryEngine engine) {
    return State__availableAbstractCapabilities.Matcher.on(engine);
  }
}
