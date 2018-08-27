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
import org.polarsys.capella.viatra.core.data.ctx.surrogate.ActorMissionInvolvement__actorMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.ActorMissionInvolvement__missionMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.ActorMissionInvolvement__actorQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.ActorMissionInvolvement__missionQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in ActorMissionInvolvement.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ActorMissionInvolvement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.ctx.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ActorMissionInvolvement__actor</li>
 * <li>ActorMissionInvolvement__mission</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ActorMissionInvolvement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ActorMissionInvolvement instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ActorMissionInvolvement();
    }
    return INSTANCE;
  }
  
  private static ActorMissionInvolvement INSTANCE;
  
  private ActorMissionInvolvement() throws ViatraQueryException {
    querySpecifications.add(ActorMissionInvolvement__actorQuerySpecification.instance());
    querySpecifications.add(ActorMissionInvolvement__missionQuerySpecification.instance());
  }
  
  public ActorMissionInvolvement__actorQuerySpecification getActorMissionInvolvement__actor() throws ViatraQueryException {
    return ActorMissionInvolvement__actorQuerySpecification.instance();
  }
  
  public ActorMissionInvolvement__actorMatcher getActorMissionInvolvement__actor(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ActorMissionInvolvement__actorMatcher.on(engine);
  }
  
  public ActorMissionInvolvement__missionQuerySpecification getActorMissionInvolvement__mission() throws ViatraQueryException {
    return ActorMissionInvolvement__missionQuerySpecification.instance();
  }
  
  public ActorMissionInvolvement__missionMatcher getActorMissionInvolvement__mission(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ActorMissionInvolvement__missionMatcher.on(engine);
  }
}
