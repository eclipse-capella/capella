/**
 * 
 *   Copyright (c) 2006, 2019 THALES DMS FRANCE.
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
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.StateTransition__realizedStateTransitions;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.StateTransition__realizingStateTransitions;

/**
 * A pattern group formed of all public patterns defined in StateTransition.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file StateTransition.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacommon.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>StateTransition__realizedStateTransitions</li>
 * <li>StateTransition__realizingStateTransitions</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class StateTransition extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static StateTransition instance() {
    if (INSTANCE == null) {
        INSTANCE = new StateTransition();
    }
    return INSTANCE;
  }
  
  private static StateTransition INSTANCE;
  
  private StateTransition() {
    querySpecifications.add(StateTransition__realizedStateTransitions.instance());
    querySpecifications.add(StateTransition__realizingStateTransitions.instance());
  }
  
  public StateTransition__realizedStateTransitions getStateTransition__realizedStateTransitions() {
    return StateTransition__realizedStateTransitions.instance();
  }
  
  public StateTransition__realizedStateTransitions.Matcher getStateTransition__realizedStateTransitions(final ViatraQueryEngine engine) {
    return StateTransition__realizedStateTransitions.Matcher.on(engine);
  }
  
  public StateTransition__realizingStateTransitions getStateTransition__realizingStateTransitions() {
    return StateTransition__realizingStateTransitions.instance();
  }
  
  public StateTransition__realizingStateTransitions.Matcher getStateTransition__realizingStateTransitions(final ViatraQueryEngine engine) {
    return StateTransition__realizingStateTransitions.Matcher.on(engine);
  }
}
