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
import org.polarsys.capella.viatra.core.data.oa.surrogate.Role__activityAllocationsMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.Role__allocatedOperationalActivitiesMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.Role__allocatingEntitiesMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.Role__roleAllocationsMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.Role__activityAllocationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.Role__allocatedOperationalActivitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.Role__allocatingEntitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.Role__roleAllocationsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in Role.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
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
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class Role extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Role instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new Role();
    }
    return INSTANCE;
  }
  
  private static Role INSTANCE;
  
  private Role() throws ViatraQueryException {
    querySpecifications.add(Role__roleAllocationsQuerySpecification.instance());
    querySpecifications.add(Role__activityAllocationsQuerySpecification.instance());
    querySpecifications.add(Role__allocatingEntitiesQuerySpecification.instance());
    querySpecifications.add(Role__allocatedOperationalActivitiesQuerySpecification.instance());
  }
  
  public Role__roleAllocationsQuerySpecification getRole__roleAllocations() throws ViatraQueryException {
    return Role__roleAllocationsQuerySpecification.instance();
  }
  
  public Role__roleAllocationsMatcher getRole__roleAllocations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Role__roleAllocationsMatcher.on(engine);
  }
  
  public Role__activityAllocationsQuerySpecification getRole__activityAllocations() throws ViatraQueryException {
    return Role__activityAllocationsQuerySpecification.instance();
  }
  
  public Role__activityAllocationsMatcher getRole__activityAllocations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Role__activityAllocationsMatcher.on(engine);
  }
  
  public Role__allocatingEntitiesQuerySpecification getRole__allocatingEntities() throws ViatraQueryException {
    return Role__allocatingEntitiesQuerySpecification.instance();
  }
  
  public Role__allocatingEntitiesMatcher getRole__allocatingEntities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Role__allocatingEntitiesMatcher.on(engine);
  }
  
  public Role__allocatedOperationalActivitiesQuerySpecification getRole__allocatedOperationalActivities() throws ViatraQueryException {
    return Role__allocatedOperationalActivitiesQuerySpecification.instance();
  }
  
  public Role__allocatedOperationalActivitiesMatcher getRole__allocatedOperationalActivities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Role__allocatedOperationalActivitiesMatcher.on(engine);
  }
}
