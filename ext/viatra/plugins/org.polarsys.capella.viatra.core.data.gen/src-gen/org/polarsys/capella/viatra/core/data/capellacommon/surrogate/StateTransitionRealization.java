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
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.StateTransitionRealization__realizedStateTransition;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.StateTransitionRealization__realizingStateTransition;

/**
 * A pattern group formed of all public patterns defined in StateTransitionRealization.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file StateTransitionRealization.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacommon.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>StateTransitionRealization__realizedStateTransition</li>
 * <li>StateTransitionRealization__realizingStateTransition</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class StateTransitionRealization extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static StateTransitionRealization instance() {
    if (INSTANCE == null) {
        INSTANCE = new StateTransitionRealization();
    }
    return INSTANCE;
  }
  
  private static StateTransitionRealization INSTANCE;
  
  private StateTransitionRealization() {
    querySpecifications.add(StateTransitionRealization__realizedStateTransition.instance());
    querySpecifications.add(StateTransitionRealization__realizingStateTransition.instance());
  }
  
  public StateTransitionRealization__realizedStateTransition getStateTransitionRealization__realizedStateTransition() {
    return StateTransitionRealization__realizedStateTransition.instance();
  }
  
  public StateTransitionRealization__realizedStateTransition.Matcher getStateTransitionRealization__realizedStateTransition(final ViatraQueryEngine engine) {
    return StateTransitionRealization__realizedStateTransition.Matcher.on(engine);
  }
  
  public StateTransitionRealization__realizingStateTransition getStateTransitionRealization__realizingStateTransition() {
    return StateTransitionRealization__realizingStateTransition.instance();
  }
  
  public StateTransitionRealization__realizingStateTransition.Matcher getStateTransitionRealization__realizingStateTransition(final ViatraQueryEngine engine) {
    return StateTransitionRealization__realizingStateTransition.Matcher.on(engine);
  }
}
