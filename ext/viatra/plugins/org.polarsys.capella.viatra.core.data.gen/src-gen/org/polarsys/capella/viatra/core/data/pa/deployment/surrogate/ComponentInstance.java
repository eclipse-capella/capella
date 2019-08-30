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
package org.polarsys.capella.viatra.core.data.pa.deployment.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.pa.deployment.surrogate.ComponentInstance__portInstancesMatcher;
import org.polarsys.capella.viatra.core.data.pa.deployment.surrogate.util.ComponentInstance__portInstancesQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in ComponentInstance.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ComponentInstance.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.pa.deployment.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ComponentInstance__portInstances</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ComponentInstance extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ComponentInstance instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ComponentInstance();
    }
    return INSTANCE;
  }
  
  private static ComponentInstance INSTANCE;
  
  private ComponentInstance() throws ViatraQueryException {
    querySpecifications.add(ComponentInstance__portInstancesQuerySpecification.instance());
  }
  
  public ComponentInstance__portInstancesQuerySpecification getComponentInstance__portInstances() throws ViatraQueryException {
    return ComponentInstance__portInstancesQuerySpecification.instance();
  }
  
  public ComponentInstance__portInstancesMatcher getComponentInstance__portInstances(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentInstance__portInstancesMatcher.on(engine);
  }
}
