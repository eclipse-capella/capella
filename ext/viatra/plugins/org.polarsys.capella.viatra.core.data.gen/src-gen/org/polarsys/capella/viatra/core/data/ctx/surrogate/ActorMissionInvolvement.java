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
import org.polarsys.capella.viatra.core.data.ctx.surrogate.ActorMissionInvolvement__actor;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.ActorMissionInvolvement__mission;

/**
 * A pattern group formed of all public patterns defined in ActorMissionInvolvement.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ActorMissionInvolvement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.ctx.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ActorMissionInvolvement__actor</li>
 * <li>ActorMissionInvolvement__mission</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class ActorMissionInvolvement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ActorMissionInvolvement instance() {
    if (INSTANCE == null) {
        INSTANCE = new ActorMissionInvolvement();
    }
    return INSTANCE;
  }
  
  private static ActorMissionInvolvement INSTANCE;
  
  private ActorMissionInvolvement() {
    querySpecifications.add(ActorMissionInvolvement__actor.instance());
    querySpecifications.add(ActorMissionInvolvement__mission.instance());
  }
  
  public ActorMissionInvolvement__actor getActorMissionInvolvement__actor() {
    return ActorMissionInvolvement__actor.instance();
  }
  
  public ActorMissionInvolvement__actor.Matcher getActorMissionInvolvement__actor(final ViatraQueryEngine engine) {
    return ActorMissionInvolvement__actor.Matcher.on(engine);
  }
  
  public ActorMissionInvolvement__mission getActorMissionInvolvement__mission() {
    return ActorMissionInvolvement__mission.instance();
  }
  
  public ActorMissionInvolvement__mission.Matcher getActorMissionInvolvement__mission(final ViatraQueryEngine engine) {
    return ActorMissionInvolvement__mission.Matcher.on(engine);
  }
}
