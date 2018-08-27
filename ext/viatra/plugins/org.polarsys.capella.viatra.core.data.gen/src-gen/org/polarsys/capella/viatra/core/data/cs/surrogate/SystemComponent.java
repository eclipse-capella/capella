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
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.cs.surrogate.SystemComponent__participationsInCapabilityRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.SystemComponent__participationsInCapabilityRealizationsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in SystemComponent.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file SystemComponent.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>SystemComponent__participationsInCapabilityRealizations</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class SystemComponent extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static SystemComponent instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new SystemComponent();
    }
    return INSTANCE;
  }
  
  private static SystemComponent INSTANCE;
  
  private SystemComponent() throws ViatraQueryException {
    querySpecifications.add(SystemComponent__participationsInCapabilityRealizationsQuerySpecification.instance());
  }
  
  public SystemComponent__participationsInCapabilityRealizationsQuerySpecification getSystemComponent__participationsInCapabilityRealizations() throws ViatraQueryException {
    return SystemComponent__participationsInCapabilityRealizationsQuerySpecification.instance();
  }
  
  public SystemComponent__participationsInCapabilityRealizationsMatcher getSystemComponent__participationsInCapabilityRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SystemComponent__participationsInCapabilityRealizationsMatcher.on(engine);
  }
}
