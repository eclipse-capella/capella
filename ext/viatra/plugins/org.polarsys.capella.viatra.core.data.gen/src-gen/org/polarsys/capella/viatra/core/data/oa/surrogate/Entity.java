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
import org.polarsys.capella.viatra.core.data.oa.surrogate.Entity__allocatedOperationalActivitiesMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.Entity__allocatedRolesMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.Entity__involvingOperationalCapabilitiesMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.Entity__realizingActorsMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.Entity__realizingSystemsMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.Entity__roleAllocationsMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.Entity__subEntitiesMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.Entity__allocatedOperationalActivitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.Entity__allocatedRolesQuerySpecification;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.Entity__involvingOperationalCapabilitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.Entity__realizingActorsQuerySpecification;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.Entity__realizingSystemsQuerySpecification;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.Entity__roleAllocationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.Entity__subEntitiesQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in Entity.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Entity.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.oa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Entity__roleAllocations</li>
 * <li>Entity__subEntities</li>
 * <li>Entity__allocatedOperationalActivities</li>
 * <li>Entity__allocatedRoles</li>
 * <li>Entity__realizingSystems</li>
 * <li>Entity__realizingActors</li>
 * <li>Entity__involvingOperationalCapabilities</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class Entity extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Entity instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new Entity();
    }
    return INSTANCE;
  }
  
  private static Entity INSTANCE;
  
  private Entity() throws ViatraQueryException {
    querySpecifications.add(Entity__roleAllocationsQuerySpecification.instance());
    querySpecifications.add(Entity__subEntitiesQuerySpecification.instance());
    querySpecifications.add(Entity__allocatedOperationalActivitiesQuerySpecification.instance());
    querySpecifications.add(Entity__allocatedRolesQuerySpecification.instance());
    querySpecifications.add(Entity__realizingSystemsQuerySpecification.instance());
    querySpecifications.add(Entity__realizingActorsQuerySpecification.instance());
    querySpecifications.add(Entity__involvingOperationalCapabilitiesQuerySpecification.instance());
  }
  
  public Entity__roleAllocationsQuerySpecification getEntity__roleAllocations() throws ViatraQueryException {
    return Entity__roleAllocationsQuerySpecification.instance();
  }
  
  public Entity__roleAllocationsMatcher getEntity__roleAllocations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Entity__roleAllocationsMatcher.on(engine);
  }
  
  public Entity__subEntitiesQuerySpecification getEntity__subEntities() throws ViatraQueryException {
    return Entity__subEntitiesQuerySpecification.instance();
  }
  
  public Entity__subEntitiesMatcher getEntity__subEntities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Entity__subEntitiesMatcher.on(engine);
  }
  
  public Entity__allocatedOperationalActivitiesQuerySpecification getEntity__allocatedOperationalActivities() throws ViatraQueryException {
    return Entity__allocatedOperationalActivitiesQuerySpecification.instance();
  }
  
  public Entity__allocatedOperationalActivitiesMatcher getEntity__allocatedOperationalActivities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Entity__allocatedOperationalActivitiesMatcher.on(engine);
  }
  
  public Entity__allocatedRolesQuerySpecification getEntity__allocatedRoles() throws ViatraQueryException {
    return Entity__allocatedRolesQuerySpecification.instance();
  }
  
  public Entity__allocatedRolesMatcher getEntity__allocatedRoles(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Entity__allocatedRolesMatcher.on(engine);
  }
  
  public Entity__realizingSystemsQuerySpecification getEntity__realizingSystems() throws ViatraQueryException {
    return Entity__realizingSystemsQuerySpecification.instance();
  }
  
  public Entity__realizingSystemsMatcher getEntity__realizingSystems(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Entity__realizingSystemsMatcher.on(engine);
  }
  
  public Entity__realizingActorsQuerySpecification getEntity__realizingActors() throws ViatraQueryException {
    return Entity__realizingActorsQuerySpecification.instance();
  }
  
  public Entity__realizingActorsMatcher getEntity__realizingActors(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Entity__realizingActorsMatcher.on(engine);
  }
  
  public Entity__involvingOperationalCapabilitiesQuerySpecification getEntity__involvingOperationalCapabilities() throws ViatraQueryException {
    return Entity__involvingOperationalCapabilitiesQuerySpecification.instance();
  }
  
  public Entity__involvingOperationalCapabilitiesMatcher getEntity__involvingOperationalCapabilities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Entity__involvingOperationalCapabilitiesMatcher.on(engine);
  }
}
