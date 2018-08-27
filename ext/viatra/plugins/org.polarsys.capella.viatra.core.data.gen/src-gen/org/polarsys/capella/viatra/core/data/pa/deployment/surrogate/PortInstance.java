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
import org.polarsys.capella.viatra.core.data.pa.deployment.surrogate.PortInstance__componentMatcher;
import org.polarsys.capella.viatra.core.data.pa.deployment.surrogate.util.PortInstance__componentQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in PortInstance.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PortInstance.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.pa.deployment.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PortInstance__component</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class PortInstance extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PortInstance instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new PortInstance();
    }
    return INSTANCE;
  }
  
  private static PortInstance INSTANCE;
  
  private PortInstance() throws ViatraQueryException {
    querySpecifications.add(PortInstance__componentQuerySpecification.instance());
  }
  
  public PortInstance__componentQuerySpecification getPortInstance__component() throws ViatraQueryException {
    return PortInstance__componentQuerySpecification.instance();
  }
  
  public PortInstance__componentMatcher getPortInstance__component(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PortInstance__componentMatcher.on(engine);
  }
}
