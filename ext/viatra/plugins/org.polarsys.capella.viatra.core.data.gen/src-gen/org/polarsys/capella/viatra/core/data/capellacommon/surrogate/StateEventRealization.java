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
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.StateEventRealization__realizedEventMatcher;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.StateEventRealization__realizingEventMatcher;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.util.StateEventRealization__realizedEventQuerySpecification;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.util.StateEventRealization__realizingEventQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in StateEventRealization.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file StateEventRealization.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacommon.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>StateEventRealization__realizedEvent</li>
 * <li>StateEventRealization__realizingEvent</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class StateEventRealization extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static StateEventRealization instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new StateEventRealization();
    }
    return INSTANCE;
  }
  
  private static StateEventRealization INSTANCE;
  
  private StateEventRealization() throws ViatraQueryException {
    querySpecifications.add(StateEventRealization__realizedEventQuerySpecification.instance());
    querySpecifications.add(StateEventRealization__realizingEventQuerySpecification.instance());
  }
  
  public StateEventRealization__realizedEventQuerySpecification getStateEventRealization__realizedEvent() throws ViatraQueryException {
    return StateEventRealization__realizedEventQuerySpecification.instance();
  }
  
  public StateEventRealization__realizedEventMatcher getStateEventRealization__realizedEvent(final ViatraQueryEngine engine) throws ViatraQueryException {
    return StateEventRealization__realizedEventMatcher.on(engine);
  }
  
  public StateEventRealization__realizingEventQuerySpecification getStateEventRealization__realizingEvent() throws ViatraQueryException {
    return StateEventRealization__realizingEventQuerySpecification.instance();
  }
  
  public StateEventRealization__realizingEventMatcher getStateEventRealization__realizingEvent(final ViatraQueryEngine engine) throws ViatraQueryException {
    return StateEventRealization__realizingEventMatcher.on(engine);
  }
}
