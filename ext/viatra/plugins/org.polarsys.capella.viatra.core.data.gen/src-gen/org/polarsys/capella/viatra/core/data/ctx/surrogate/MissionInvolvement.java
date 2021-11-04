/**
 * 
 *   Copyright (c) 2006, 2020 THALES DMS FRANCE.
 *   
 *  This program and the accompanying materials are made available under the
 *   terms of the Eclipse Public License 2.0 which is available at
 *   http://www.eclipse.org/legal/epl-2.0
 *   
 *   SPDX-License-Identifier: EPL-2.0
 *  
 *   Contributors:
 *      Thales - initial API and implementation
 *  
 */
package org.polarsys.capella.viatra.core.data.ctx.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in MissionInvolvement.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file MissionInvolvement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.ctx.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>MissionInvolvement__mission</li>
 * <li>MissionInvolvement__systemComponent</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class MissionInvolvement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static MissionInvolvement instance() {
    if (INSTANCE == null) {
        INSTANCE = new MissionInvolvement();
    }
    return INSTANCE;
  }
  
  private static MissionInvolvement INSTANCE;
  
  private MissionInvolvement() {
    querySpecifications.add(MissionInvolvement__mission.instance());
    querySpecifications.add(MissionInvolvement__systemComponent.instance());
  }
  
  public MissionInvolvement__mission getMissionInvolvement__mission() {
    return MissionInvolvement__mission.instance();
  }
  
  public MissionInvolvement__mission.Matcher getMissionInvolvement__mission(final ViatraQueryEngine engine) {
    return MissionInvolvement__mission.Matcher.on(engine);
  }
  
  public MissionInvolvement__systemComponent getMissionInvolvement__systemComponent() {
    return MissionInvolvement__systemComponent.instance();
  }
  
  public MissionInvolvement__systemComponent.Matcher getMissionInvolvement__systemComponent(final ViatraQueryEngine engine) {
    return MissionInvolvement__systemComponent.Matcher.on(engine);
  }
}
