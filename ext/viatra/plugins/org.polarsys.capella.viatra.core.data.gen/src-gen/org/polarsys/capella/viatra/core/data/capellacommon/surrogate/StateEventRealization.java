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
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.StateEventRealization__realizedEvent;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.StateEventRealization__realizingEvent;

/**
 * A pattern group formed of all public patterns defined in StateEventRealization.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file StateEventRealization.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacommon.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>StateEventRealization__realizedEvent</li>
 * <li>StateEventRealization__realizingEvent</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class StateEventRealization extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static StateEventRealization instance() {
    if (INSTANCE == null) {
        INSTANCE = new StateEventRealization();
    }
    return INSTANCE;
  }
  
  private static StateEventRealization INSTANCE;
  
  private StateEventRealization() {
    querySpecifications.add(StateEventRealization__realizedEvent.instance());
    querySpecifications.add(StateEventRealization__realizingEvent.instance());
  }
  
  public StateEventRealization__realizedEvent getStateEventRealization__realizedEvent() {
    return StateEventRealization__realizedEvent.instance();
  }
  
  public StateEventRealization__realizedEvent.Matcher getStateEventRealization__realizedEvent(final ViatraQueryEngine engine) {
    return StateEventRealization__realizedEvent.Matcher.on(engine);
  }
  
  public StateEventRealization__realizingEvent getStateEventRealization__realizingEvent() {
    return StateEventRealization__realizingEvent.instance();
  }
  
  public StateEventRealization__realizingEvent.Matcher getStateEventRealization__realizingEvent(final ViatraQueryEngine engine) {
    return StateEventRealization__realizingEvent.Matcher.on(engine);
  }
}
