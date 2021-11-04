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

/**
 * A pattern group formed of all public patterns defined in Entity.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Entity.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.oa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Entity__roleAllocations</li>
 * <li>Entity__subEntities</li>
 * <li>Entity__allocatedOperationalActivities</li>
 * <li>Entity__allocatedRoles</li>
 * <li>Entity__involvingOperationalCapabilities</li>
 * <li>Entity__realizingSystemComponents</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class Entity extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Entity instance() {
    if (INSTANCE == null) {
        INSTANCE = new Entity();
    }
    return INSTANCE;
  }
  
  private static Entity INSTANCE;
  
  private Entity() {
    querySpecifications.add(Entity__roleAllocations.instance());
    querySpecifications.add(Entity__subEntities.instance());
    querySpecifications.add(Entity__allocatedOperationalActivities.instance());
    querySpecifications.add(Entity__allocatedRoles.instance());
    querySpecifications.add(Entity__involvingOperationalCapabilities.instance());
    querySpecifications.add(Entity__realizingSystemComponents.instance());
  }
  
  public Entity__roleAllocations getEntity__roleAllocations() {
    return Entity__roleAllocations.instance();
  }
  
  public Entity__roleAllocations.Matcher getEntity__roleAllocations(final ViatraQueryEngine engine) {
    return Entity__roleAllocations.Matcher.on(engine);
  }
  
  public Entity__subEntities getEntity__subEntities() {
    return Entity__subEntities.instance();
  }
  
  public Entity__subEntities.Matcher getEntity__subEntities(final ViatraQueryEngine engine) {
    return Entity__subEntities.Matcher.on(engine);
  }
  
  public Entity__allocatedOperationalActivities getEntity__allocatedOperationalActivities() {
    return Entity__allocatedOperationalActivities.instance();
  }
  
  public Entity__allocatedOperationalActivities.Matcher getEntity__allocatedOperationalActivities(final ViatraQueryEngine engine) {
    return Entity__allocatedOperationalActivities.Matcher.on(engine);
  }
  
  public Entity__allocatedRoles getEntity__allocatedRoles() {
    return Entity__allocatedRoles.instance();
  }
  
  public Entity__allocatedRoles.Matcher getEntity__allocatedRoles(final ViatraQueryEngine engine) {
    return Entity__allocatedRoles.Matcher.on(engine);
  }
  
  public Entity__involvingOperationalCapabilities getEntity__involvingOperationalCapabilities() {
    return Entity__involvingOperationalCapabilities.instance();
  }
  
  public Entity__involvingOperationalCapabilities.Matcher getEntity__involvingOperationalCapabilities(final ViatraQueryEngine engine) {
    return Entity__involvingOperationalCapabilities.Matcher.on(engine);
  }
  
  public Entity__realizingSystemComponents getEntity__realizingSystemComponents() {
    return Entity__realizingSystemComponents.instance();
  }
  
  public Entity__realizingSystemComponents.Matcher getEntity__realizingSystemComponents(final ViatraQueryEngine engine) {
    return Entity__realizingSystemComponents.Matcher.on(engine);
  }
}
