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
import org.polarsys.capella.viatra.core.data.oa.surrogate.Role__activityAllocations;
import org.polarsys.capella.viatra.core.data.oa.surrogate.Role__allocatedOperationalActivities;
import org.polarsys.capella.viatra.core.data.oa.surrogate.Role__allocatingEntities;
import org.polarsys.capella.viatra.core.data.oa.surrogate.Role__roleAllocations;

/**
 * A pattern group formed of all public patterns defined in Role.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Role.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.oa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Role__roleAllocations</li>
 * <li>Role__activityAllocations</li>
 * <li>Role__allocatingEntities</li>
 * <li>Role__allocatedOperationalActivities</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class Role extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Role instance() {
    if (INSTANCE == null) {
        INSTANCE = new Role();
    }
    return INSTANCE;
  }
  
  private static Role INSTANCE;
  
  private Role() {
    querySpecifications.add(Role__roleAllocations.instance());
    querySpecifications.add(Role__activityAllocations.instance());
    querySpecifications.add(Role__allocatingEntities.instance());
    querySpecifications.add(Role__allocatedOperationalActivities.instance());
  }
  
  public Role__roleAllocations getRole__roleAllocations() {
    return Role__roleAllocations.instance();
  }
  
  public Role__roleAllocations.Matcher getRole__roleAllocations(final ViatraQueryEngine engine) {
    return Role__roleAllocations.Matcher.on(engine);
  }
  
  public Role__activityAllocations getRole__activityAllocations() {
    return Role__activityAllocations.instance();
  }
  
  public Role__activityAllocations.Matcher getRole__activityAllocations(final ViatraQueryEngine engine) {
    return Role__activityAllocations.Matcher.on(engine);
  }
  
  public Role__allocatingEntities getRole__allocatingEntities() {
    return Role__allocatingEntities.instance();
  }
  
  public Role__allocatingEntities.Matcher getRole__allocatingEntities(final ViatraQueryEngine engine) {
    return Role__allocatingEntities.Matcher.on(engine);
  }
  
  public Role__allocatedOperationalActivities getRole__allocatedOperationalActivities() {
    return Role__allocatedOperationalActivities.instance();
  }
  
  public Role__allocatedOperationalActivities.Matcher getRole__allocatedOperationalActivities(final ViatraQueryEngine engine) {
    return Role__allocatedOperationalActivities.Matcher.on(engine);
  }
}
