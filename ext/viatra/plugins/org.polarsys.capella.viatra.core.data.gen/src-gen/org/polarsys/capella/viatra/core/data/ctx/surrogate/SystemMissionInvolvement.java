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
import org.polarsys.capella.viatra.core.data.ctx.surrogate.SystemMissionInvolvement__mission;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.SystemMissionInvolvement__system;

/**
 * A pattern group formed of all public patterns defined in SystemMissionInvolvement.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file SystemMissionInvolvement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.ctx.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>SystemMissionInvolvement__mission</li>
 * <li>SystemMissionInvolvement__system</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class SystemMissionInvolvement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static SystemMissionInvolvement instance() {
    if (INSTANCE == null) {
        INSTANCE = new SystemMissionInvolvement();
    }
    return INSTANCE;
  }
  
  private static SystemMissionInvolvement INSTANCE;
  
  private SystemMissionInvolvement() {
    querySpecifications.add(SystemMissionInvolvement__mission.instance());
    querySpecifications.add(SystemMissionInvolvement__system.instance());
  }
  
  public SystemMissionInvolvement__mission getSystemMissionInvolvement__mission() {
    return SystemMissionInvolvement__mission.instance();
  }
  
  public SystemMissionInvolvement__mission.Matcher getSystemMissionInvolvement__mission(final ViatraQueryEngine engine) {
    return SystemMissionInvolvement__mission.Matcher.on(engine);
  }
  
  public SystemMissionInvolvement__system getSystemMissionInvolvement__system() {
    return SystemMissionInvolvement__system.instance();
  }
  
  public SystemMissionInvolvement__system.Matcher getSystemMissionInvolvement__system(final ViatraQueryEngine engine) {
    return SystemMissionInvolvement__system.Matcher.on(engine);
  }
}
