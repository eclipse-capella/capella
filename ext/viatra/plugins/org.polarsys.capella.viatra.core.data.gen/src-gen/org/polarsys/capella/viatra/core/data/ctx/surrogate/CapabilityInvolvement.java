/**
 * 
 *   Copyright (c) 2006, 2019 THALES DMS FRANCE.
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
package org.polarsys.capella.viatra.core.data.ctx.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.CapabilityInvolvement__capability;
import org.polarsys.capella.viatra.core.data.ctx.surrogate.CapabilityInvolvement__systemComponent;

/**
 * A pattern group formed of all public patterns defined in CapabilityInvolvement.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file CapabilityInvolvement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.ctx.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>CapabilityInvolvement__systemComponent</li>
 * <li>CapabilityInvolvement__capability</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class CapabilityInvolvement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static CapabilityInvolvement instance() {
    if (INSTANCE == null) {
        INSTANCE = new CapabilityInvolvement();
    }
    return INSTANCE;
  }
  
  private static CapabilityInvolvement INSTANCE;
  
  private CapabilityInvolvement() {
    querySpecifications.add(CapabilityInvolvement__systemComponent.instance());
    querySpecifications.add(CapabilityInvolvement__capability.instance());
  }
  
  public CapabilityInvolvement__systemComponent getCapabilityInvolvement__systemComponent() {
    return CapabilityInvolvement__systemComponent.instance();
  }
  
  public CapabilityInvolvement__systemComponent.Matcher getCapabilityInvolvement__systemComponent(final ViatraQueryEngine engine) {
    return CapabilityInvolvement__systemComponent.Matcher.on(engine);
  }
  
  public CapabilityInvolvement__capability getCapabilityInvolvement__capability() {
    return CapabilityInvolvement__capability.instance();
  }
  
  public CapabilityInvolvement__capability.Matcher getCapabilityInvolvement__capability(final ViatraQueryEngine engine) {
    return CapabilityInvolvement__capability.Matcher.on(engine);
  }
}
