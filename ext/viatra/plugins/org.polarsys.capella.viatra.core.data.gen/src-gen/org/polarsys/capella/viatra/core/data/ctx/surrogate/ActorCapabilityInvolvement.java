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
package org.polarsys.capella.viatra.core.data.ctx.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.ActorCapabilityInvolvement__actor;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.ActorCapabilityInvolvement__capability;

/**
 * A pattern group formed of all public patterns defined in ActorCapabilityInvolvement.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ActorCapabilityInvolvement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.ctx.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ActorCapabilityInvolvement__actor</li>
 * <li>ActorCapabilityInvolvement__capability</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class ActorCapabilityInvolvement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ActorCapabilityInvolvement instance() {
    if (INSTANCE == null) {
        INSTANCE = new ActorCapabilityInvolvement();
    }
    return INSTANCE;
  }
  
  private static ActorCapabilityInvolvement INSTANCE;
  
  private ActorCapabilityInvolvement() {
    querySpecifications.add(ActorCapabilityInvolvement__actor.instance());
    querySpecifications.add(ActorCapabilityInvolvement__capability.instance());
  }
  
  public ActorCapabilityInvolvement__actor getActorCapabilityInvolvement__actor() {
    return ActorCapabilityInvolvement__actor.instance();
  }
  
  public ActorCapabilityInvolvement__actor.Matcher getActorCapabilityInvolvement__actor(final ViatraQueryEngine engine) {
    return ActorCapabilityInvolvement__actor.Matcher.on(engine);
  }
  
  public ActorCapabilityInvolvement__capability getActorCapabilityInvolvement__capability() {
    return ActorCapabilityInvolvement__capability.instance();
  }
  
  public ActorCapabilityInvolvement__capability.Matcher getActorCapabilityInvolvement__capability(final ViatraQueryEngine engine) {
    return ActorCapabilityInvolvement__capability.Matcher.on(engine);
  }
}
