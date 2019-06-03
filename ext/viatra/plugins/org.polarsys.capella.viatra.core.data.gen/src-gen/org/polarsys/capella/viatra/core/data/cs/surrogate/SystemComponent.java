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
package org.polarsys.capella.viatra.core.data.cs.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.cs.surrogate.SystemComponent__participationsInCapabilityRealizations;

/**
 * A pattern group formed of all public patterns defined in SystemComponent.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file SystemComponent.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>SystemComponent__participationsInCapabilityRealizations</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class SystemComponent extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static SystemComponent instance() {
    if (INSTANCE == null) {
        INSTANCE = new SystemComponent();
    }
    return INSTANCE;
  }
  
  private static SystemComponent INSTANCE;
  
  private SystemComponent() {
    querySpecifications.add(SystemComponent__participationsInCapabilityRealizations.instance());
  }
  
  public SystemComponent__participationsInCapabilityRealizations getSystemComponent__participationsInCapabilityRealizations() {
    return SystemComponent__participationsInCapabilityRealizations.instance();
  }
  
  public SystemComponent__participationsInCapabilityRealizations.Matcher getSystemComponent__participationsInCapabilityRealizations(final ViatraQueryEngine engine) {
    return SystemComponent__participationsInCapabilityRealizations.Matcher.on(engine);
  }
}
