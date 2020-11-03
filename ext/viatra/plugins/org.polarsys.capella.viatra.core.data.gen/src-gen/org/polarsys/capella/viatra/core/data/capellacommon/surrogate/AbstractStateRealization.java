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
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.AbstractStateRealization__realizedAbstractState;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.AbstractStateRealization__realizingAbstractState;

/**
 * A pattern group formed of all public patterns defined in AbstractStateRealization.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractStateRealization.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacommon.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractStateRealization__realizedAbstractState</li>
 * <li>AbstractStateRealization__realizingAbstractState</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractStateRealization extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractStateRealization instance() {
    if (INSTANCE == null) {
        INSTANCE = new AbstractStateRealization();
    }
    return INSTANCE;
  }
  
  private static AbstractStateRealization INSTANCE;
  
  private AbstractStateRealization() {
    querySpecifications.add(AbstractStateRealization__realizedAbstractState.instance());
    querySpecifications.add(AbstractStateRealization__realizingAbstractState.instance());
  }
  
  public AbstractStateRealization__realizedAbstractState getAbstractStateRealization__realizedAbstractState() {
    return AbstractStateRealization__realizedAbstractState.instance();
  }
  
  public AbstractStateRealization__realizedAbstractState.Matcher getAbstractStateRealization__realizedAbstractState(final ViatraQueryEngine engine) {
    return AbstractStateRealization__realizedAbstractState.Matcher.on(engine);
  }
  
  public AbstractStateRealization__realizingAbstractState getAbstractStateRealization__realizingAbstractState() {
    return AbstractStateRealization__realizingAbstractState.instance();
  }
  
  public AbstractStateRealization__realizingAbstractState.Matcher getAbstractStateRealization__realizingAbstractState(final ViatraQueryEngine engine) {
    return AbstractStateRealization__realizingAbstractState.Matcher.on(engine);
  }
}
