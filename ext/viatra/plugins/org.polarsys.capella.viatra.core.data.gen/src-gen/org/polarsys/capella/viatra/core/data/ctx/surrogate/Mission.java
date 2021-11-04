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
package org.polarsys.capella.viatra.core.data.ctx.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in Mission.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Mission.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.ctx.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Mission__exploitedCapabilities</li>
 * <li>Mission__involvedSystemComponents</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class Mission extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Mission instance() {
    if (INSTANCE == null) {
        INSTANCE = new Mission();
    }
    return INSTANCE;
  }
  
  private static Mission INSTANCE;
  
  private Mission() {
    querySpecifications.add(Mission__exploitedCapabilities.instance());
    querySpecifications.add(Mission__involvedSystemComponents.instance());
  }
  
  public Mission__exploitedCapabilities getMission__exploitedCapabilities() {
    return Mission__exploitedCapabilities.instance();
  }
  
  public Mission__exploitedCapabilities.Matcher getMission__exploitedCapabilities(final ViatraQueryEngine engine) {
    return Mission__exploitedCapabilities.Matcher.on(engine);
  }
  
  public Mission__involvedSystemComponents getMission__involvedSystemComponents() {
    return Mission__involvedSystemComponents.instance();
  }
  
  public Mission__involvedSystemComponents.Matcher getMission__involvedSystemComponents(final ViatraQueryEngine engine) {
    return Mission__involvedSystemComponents.Matcher.on(engine);
  }
}
