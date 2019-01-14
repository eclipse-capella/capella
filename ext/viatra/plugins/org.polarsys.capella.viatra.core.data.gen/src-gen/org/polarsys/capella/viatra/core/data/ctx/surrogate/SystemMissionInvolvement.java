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
import org.polarsys.capella.viatra.core.data.ctx.surrogate.SystemMissionInvolvement__missionMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.SystemMissionInvolvement__systemMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.SystemMissionInvolvement__missionQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.SystemMissionInvolvement__systemQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in SystemMissionInvolvement.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file SystemMissionInvolvement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.ctx.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>SystemMissionInvolvement__mission</li>
 * <li>SystemMissionInvolvement__system</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class SystemMissionInvolvement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static SystemMissionInvolvement instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new SystemMissionInvolvement();
    }
    return INSTANCE;
  }
  
  private static SystemMissionInvolvement INSTANCE;
  
  private SystemMissionInvolvement() throws ViatraQueryException {
    querySpecifications.add(SystemMissionInvolvement__missionQuerySpecification.instance());
    querySpecifications.add(SystemMissionInvolvement__systemQuerySpecification.instance());
  }
  
  public SystemMissionInvolvement__missionQuerySpecification getSystemMissionInvolvement__mission() throws ViatraQueryException {
    return SystemMissionInvolvement__missionQuerySpecification.instance();
  }
  
  public SystemMissionInvolvement__missionMatcher getSystemMissionInvolvement__mission(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SystemMissionInvolvement__missionMatcher.on(engine);
  }
  
  public SystemMissionInvolvement__systemQuerySpecification getSystemMissionInvolvement__system() throws ViatraQueryException {
    return SystemMissionInvolvement__systemQuerySpecification.instance();
  }
  
  public SystemMissionInvolvement__systemMatcher getSystemMissionInvolvement__system(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SystemMissionInvolvement__systemMatcher.on(engine);
  }
}
