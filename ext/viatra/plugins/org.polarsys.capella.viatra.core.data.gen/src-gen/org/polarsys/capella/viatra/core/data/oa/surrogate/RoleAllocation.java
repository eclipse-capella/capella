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
package org.polarsys.capella.viatra.core.data.oa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.oa.surrogate.RoleAllocation__entity;
import org.polarsys.capella.viatra.core.data.oa.surrogate.RoleAllocation__role;

/**
 * A pattern group formed of all public patterns defined in RoleAllocation.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file RoleAllocation.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.oa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>RoleAllocation__role</li>
 * <li>RoleAllocation__entity</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class RoleAllocation extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static RoleAllocation instance() {
    if (INSTANCE == null) {
        INSTANCE = new RoleAllocation();
    }
    return INSTANCE;
  }
  
  private static RoleAllocation INSTANCE;
  
  private RoleAllocation() {
    querySpecifications.add(RoleAllocation__role.instance());
    querySpecifications.add(RoleAllocation__entity.instance());
  }
  
  public RoleAllocation__role getRoleAllocation__role() {
    return RoleAllocation__role.instance();
  }
  
  public RoleAllocation__role.Matcher getRoleAllocation__role(final ViatraQueryEngine engine) {
    return RoleAllocation__role.Matcher.on(engine);
  }
  
  public RoleAllocation__entity getRoleAllocation__entity() {
    return RoleAllocation__entity.instance();
  }
  
  public RoleAllocation__entity.Matcher getRoleAllocation__entity(final ViatraQueryEngine engine) {
    return RoleAllocation__entity.Matcher.on(engine);
  }
}
