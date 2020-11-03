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
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.AbstractState__incoming;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.AbstractState__involverRegions;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.AbstractState__outgoing;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.AbstractState__realizedAbstractStates;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.AbstractState__realizingAbstractStates;

/**
 * A pattern group formed of all public patterns defined in AbstractState.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractState.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacommon.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractState__realizedAbstractStates</li>
 * <li>AbstractState__realizingAbstractStates</li>
 * <li>AbstractState__outgoing</li>
 * <li>AbstractState__incoming</li>
 * <li>AbstractState__involverRegions</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractState extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractState instance() {
    if (INSTANCE == null) {
        INSTANCE = new AbstractState();
    }
    return INSTANCE;
  }
  
  private static AbstractState INSTANCE;
  
  private AbstractState() {
    querySpecifications.add(AbstractState__realizedAbstractStates.instance());
    querySpecifications.add(AbstractState__realizingAbstractStates.instance());
    querySpecifications.add(AbstractState__outgoing.instance());
    querySpecifications.add(AbstractState__incoming.instance());
    querySpecifications.add(AbstractState__involverRegions.instance());
  }
  
  public AbstractState__realizedAbstractStates getAbstractState__realizedAbstractStates() {
    return AbstractState__realizedAbstractStates.instance();
  }
  
  public AbstractState__realizedAbstractStates.Matcher getAbstractState__realizedAbstractStates(final ViatraQueryEngine engine) {
    return AbstractState__realizedAbstractStates.Matcher.on(engine);
  }
  
  public AbstractState__realizingAbstractStates getAbstractState__realizingAbstractStates() {
    return AbstractState__realizingAbstractStates.instance();
  }
  
  public AbstractState__realizingAbstractStates.Matcher getAbstractState__realizingAbstractStates(final ViatraQueryEngine engine) {
    return AbstractState__realizingAbstractStates.Matcher.on(engine);
  }
  
  public AbstractState__outgoing getAbstractState__outgoing() {
    return AbstractState__outgoing.instance();
  }
  
  public AbstractState__outgoing.Matcher getAbstractState__outgoing(final ViatraQueryEngine engine) {
    return AbstractState__outgoing.Matcher.on(engine);
  }
  
  public AbstractState__incoming getAbstractState__incoming() {
    return AbstractState__incoming.instance();
  }
  
  public AbstractState__incoming.Matcher getAbstractState__incoming(final ViatraQueryEngine engine) {
    return AbstractState__incoming.Matcher.on(engine);
  }
  
  public AbstractState__involverRegions getAbstractState__involverRegions() {
    return AbstractState__involverRegions.instance();
  }
  
  public AbstractState__involverRegions.Matcher getAbstractState__involverRegions(final ViatraQueryEngine engine) {
    return AbstractState__involverRegions.Matcher.on(engine);
  }
}
