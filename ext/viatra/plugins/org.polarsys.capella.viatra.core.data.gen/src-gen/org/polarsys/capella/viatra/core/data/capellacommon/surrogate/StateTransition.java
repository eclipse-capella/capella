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
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.StateTransition__realizedStateTransitionsMatcher;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.StateTransition__realizingStateTransitionsMatcher;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.util.StateTransition__realizedStateTransitionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.util.StateTransition__realizingStateTransitionsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in StateTransition.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file StateTransition.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacommon.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>StateTransition__realizedStateTransitions</li>
 * <li>StateTransition__realizingStateTransitions</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class StateTransition extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static StateTransition instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new StateTransition();
    }
    return INSTANCE;
  }
  
  private static StateTransition INSTANCE;
  
  private StateTransition() throws ViatraQueryException {
    querySpecifications.add(StateTransition__realizedStateTransitionsQuerySpecification.instance());
    querySpecifications.add(StateTransition__realizingStateTransitionsQuerySpecification.instance());
  }
  
  public StateTransition__realizedStateTransitionsQuerySpecification getStateTransition__realizedStateTransitions() throws ViatraQueryException {
    return StateTransition__realizedStateTransitionsQuerySpecification.instance();
  }
  
  public StateTransition__realizedStateTransitionsMatcher getStateTransition__realizedStateTransitions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return StateTransition__realizedStateTransitionsMatcher.on(engine);
  }
  
  public StateTransition__realizingStateTransitionsQuerySpecification getStateTransition__realizingStateTransitions() throws ViatraQueryException {
    return StateTransition__realizingStateTransitionsQuerySpecification.instance();
  }
  
  public StateTransition__realizingStateTransitionsMatcher getStateTransition__realizingStateTransitions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return StateTransition__realizingStateTransitionsMatcher.on(engine);
  }
}
