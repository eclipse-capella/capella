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
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.ActorCapabilityInvolvement__actorMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.ActorCapabilityInvolvement__capabilityMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.ActorCapabilityInvolvement__actorQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.ActorCapabilityInvolvement__capabilityQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in ActorCapabilityInvolvement.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ActorCapabilityInvolvement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.ctx.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ActorCapabilityInvolvement__actor</li>
 * <li>ActorCapabilityInvolvement__capability</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ActorCapabilityInvolvement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ActorCapabilityInvolvement instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ActorCapabilityInvolvement();
    }
    return INSTANCE;
  }
  
  private static ActorCapabilityInvolvement INSTANCE;
  
  private ActorCapabilityInvolvement() throws ViatraQueryException {
    querySpecifications.add(ActorCapabilityInvolvement__actorQuerySpecification.instance());
    querySpecifications.add(ActorCapabilityInvolvement__capabilityQuerySpecification.instance());
  }
  
  public ActorCapabilityInvolvement__actorQuerySpecification getActorCapabilityInvolvement__actor() throws ViatraQueryException {
    return ActorCapabilityInvolvement__actorQuerySpecification.instance();
  }
  
  public ActorCapabilityInvolvement__actorMatcher getActorCapabilityInvolvement__actor(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ActorCapabilityInvolvement__actorMatcher.on(engine);
  }
  
  public ActorCapabilityInvolvement__capabilityQuerySpecification getActorCapabilityInvolvement__capability() throws ViatraQueryException {
    return ActorCapabilityInvolvement__capabilityQuerySpecification.instance();
  }
  
  public ActorCapabilityInvolvement__capabilityMatcher getActorCapabilityInvolvement__capability(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ActorCapabilityInvolvement__capabilityMatcher.on(engine);
  }
}
