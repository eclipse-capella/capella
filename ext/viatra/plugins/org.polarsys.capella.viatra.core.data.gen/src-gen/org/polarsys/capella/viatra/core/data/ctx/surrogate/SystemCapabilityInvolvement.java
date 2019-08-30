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
import org.polarsys.capella.viatra.core.data.ctx.surrogate.SystemCapabilityInvolvement__capabilityMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.SystemCapabilityInvolvement__systemMatcher;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.SystemCapabilityInvolvement__capabilityQuerySpecification;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.util.SystemCapabilityInvolvement__systemQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in SystemCapabilityInvolvement.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file SystemCapabilityInvolvement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.ctx.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>SystemCapabilityInvolvement__capability</li>
 * <li>SystemCapabilityInvolvement__system</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class SystemCapabilityInvolvement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static SystemCapabilityInvolvement instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new SystemCapabilityInvolvement();
    }
    return INSTANCE;
  }
  
  private static SystemCapabilityInvolvement INSTANCE;
  
  private SystemCapabilityInvolvement() throws ViatraQueryException {
    querySpecifications.add(SystemCapabilityInvolvement__capabilityQuerySpecification.instance());
    querySpecifications.add(SystemCapabilityInvolvement__systemQuerySpecification.instance());
  }
  
  public SystemCapabilityInvolvement__capabilityQuerySpecification getSystemCapabilityInvolvement__capability() throws ViatraQueryException {
    return SystemCapabilityInvolvement__capabilityQuerySpecification.instance();
  }
  
  public SystemCapabilityInvolvement__capabilityMatcher getSystemCapabilityInvolvement__capability(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SystemCapabilityInvolvement__capabilityMatcher.on(engine);
  }
  
  public SystemCapabilityInvolvement__systemQuerySpecification getSystemCapabilityInvolvement__system() throws ViatraQueryException {
    return SystemCapabilityInvolvement__systemQuerySpecification.instance();
  }
  
  public SystemCapabilityInvolvement__systemMatcher getSystemCapabilityInvolvement__system(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SystemCapabilityInvolvement__systemMatcher.on(engine);
  }
}
