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
import org.polarsys.capella.viatra.core.data.ctx.surrogate.SystemCapabilityInvolvement__capability;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.SystemCapabilityInvolvement__system;

/**
 * A pattern group formed of all public patterns defined in SystemCapabilityInvolvement.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file SystemCapabilityInvolvement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.ctx.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>SystemCapabilityInvolvement__capability</li>
 * <li>SystemCapabilityInvolvement__system</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class SystemCapabilityInvolvement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static SystemCapabilityInvolvement instance() {
    if (INSTANCE == null) {
        INSTANCE = new SystemCapabilityInvolvement();
    }
    return INSTANCE;
  }
  
  private static SystemCapabilityInvolvement INSTANCE;
  
  private SystemCapabilityInvolvement() {
    querySpecifications.add(SystemCapabilityInvolvement__capability.instance());
    querySpecifications.add(SystemCapabilityInvolvement__system.instance());
  }
  
  public SystemCapabilityInvolvement__capability getSystemCapabilityInvolvement__capability() {
    return SystemCapabilityInvolvement__capability.instance();
  }
  
  public SystemCapabilityInvolvement__capability.Matcher getSystemCapabilityInvolvement__capability(final ViatraQueryEngine engine) {
    return SystemCapabilityInvolvement__capability.Matcher.on(engine);
  }
  
  public SystemCapabilityInvolvement__system getSystemCapabilityInvolvement__system() {
    return SystemCapabilityInvolvement__system.instance();
  }
  
  public SystemCapabilityInvolvement__system.Matcher getSystemCapabilityInvolvement__system(final ViatraQueryEngine engine) {
    return SystemCapabilityInvolvement__system.Matcher.on(engine);
  }
}
