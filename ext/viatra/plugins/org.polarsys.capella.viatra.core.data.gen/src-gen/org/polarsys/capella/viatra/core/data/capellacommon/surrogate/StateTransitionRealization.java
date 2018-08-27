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
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.StateTransitionRealization__realizedStateTransitionMatcher;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.StateTransitionRealization__realizingStateTransitionMatcher;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.util.StateTransitionRealization__realizedStateTransitionQuerySpecification;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.util.StateTransitionRealization__realizingStateTransitionQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in StateTransitionRealization.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file StateTransitionRealization.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacommon.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>StateTransitionRealization__realizedStateTransition</li>
 * <li>StateTransitionRealization__realizingStateTransition</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class StateTransitionRealization extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static StateTransitionRealization instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new StateTransitionRealization();
    }
    return INSTANCE;
  }
  
  private static StateTransitionRealization INSTANCE;
  
  private StateTransitionRealization() throws ViatraQueryException {
    querySpecifications.add(StateTransitionRealization__realizedStateTransitionQuerySpecification.instance());
    querySpecifications.add(StateTransitionRealization__realizingStateTransitionQuerySpecification.instance());
  }
  
  public StateTransitionRealization__realizedStateTransitionQuerySpecification getStateTransitionRealization__realizedStateTransition() throws ViatraQueryException {
    return StateTransitionRealization__realizedStateTransitionQuerySpecification.instance();
  }
  
  public StateTransitionRealization__realizedStateTransitionMatcher getStateTransitionRealization__realizedStateTransition(final ViatraQueryEngine engine) throws ViatraQueryException {
    return StateTransitionRealization__realizedStateTransitionMatcher.on(engine);
  }
  
  public StateTransitionRealization__realizingStateTransitionQuerySpecification getStateTransitionRealization__realizingStateTransition() throws ViatraQueryException {
    return StateTransitionRealization__realizingStateTransitionQuerySpecification.instance();
  }
  
  public StateTransitionRealization__realizingStateTransitionMatcher getStateTransitionRealization__realizingStateTransition(final ViatraQueryEngine engine) throws ViatraQueryException {
    return StateTransitionRealization__realizingStateTransitionMatcher.on(engine);
  }
}
