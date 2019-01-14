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
package org.polarsys.capella.viatra.core.data.oa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.oa.surrogate.RoleAllocation__entityMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.RoleAllocation__roleMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.RoleAllocation__entityQuerySpecification;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.RoleAllocation__roleQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in RoleAllocation.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file RoleAllocation.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.oa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>RoleAllocation__role</li>
 * <li>RoleAllocation__entity</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class RoleAllocation extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static RoleAllocation instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new RoleAllocation();
    }
    return INSTANCE;
  }
  
  private static RoleAllocation INSTANCE;
  
  private RoleAllocation() throws ViatraQueryException {
    querySpecifications.add(RoleAllocation__roleQuerySpecification.instance());
    querySpecifications.add(RoleAllocation__entityQuerySpecification.instance());
  }
  
  public RoleAllocation__roleQuerySpecification getRoleAllocation__role() throws ViatraQueryException {
    return RoleAllocation__roleQuerySpecification.instance();
  }
  
  public RoleAllocation__roleMatcher getRoleAllocation__role(final ViatraQueryEngine engine) throws ViatraQueryException {
    return RoleAllocation__roleMatcher.on(engine);
  }
  
  public RoleAllocation__entityQuerySpecification getRoleAllocation__entity() throws ViatraQueryException {
    return RoleAllocation__entityQuerySpecification.instance();
  }
  
  public RoleAllocation__entityMatcher getRoleAllocation__entity(final ViatraQueryEngine engine) throws ViatraQueryException {
    return RoleAllocation__entityMatcher.on(engine);
  }
}
