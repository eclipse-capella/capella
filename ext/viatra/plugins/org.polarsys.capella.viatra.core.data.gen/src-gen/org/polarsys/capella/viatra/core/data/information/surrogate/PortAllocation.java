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
package org.polarsys.capella.viatra.core.data.information.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.information.surrogate.PortAllocation__allocatedPortMatcher;
import org.polarsys.capella.viatra.core.data.information.surrogate.PortAllocation__allocatingPortMatcher;
import org.polarsys.capella.viatra.core.data.information.surrogate.util.PortAllocation__allocatedPortQuerySpecification;
import org.polarsys.capella.viatra.core.data.information.surrogate.util.PortAllocation__allocatingPortQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in PortAllocation.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PortAllocation.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PortAllocation__allocatedPort</li>
 * <li>PortAllocation__allocatingPort</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class PortAllocation extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PortAllocation instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new PortAllocation();
    }
    return INSTANCE;
  }
  
  private static PortAllocation INSTANCE;
  
  private PortAllocation() throws ViatraQueryException {
    querySpecifications.add(PortAllocation__allocatedPortQuerySpecification.instance());
    querySpecifications.add(PortAllocation__allocatingPortQuerySpecification.instance());
  }
  
  public PortAllocation__allocatedPortQuerySpecification getPortAllocation__allocatedPort() throws ViatraQueryException {
    return PortAllocation__allocatedPortQuerySpecification.instance();
  }
  
  public PortAllocation__allocatedPortMatcher getPortAllocation__allocatedPort(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PortAllocation__allocatedPortMatcher.on(engine);
  }
  
  public PortAllocation__allocatingPortQuerySpecification getPortAllocation__allocatingPort() throws ViatraQueryException {
    return PortAllocation__allocatingPortQuerySpecification.instance();
  }
  
  public PortAllocation__allocatingPortMatcher getPortAllocation__allocatingPort(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PortAllocation__allocatingPortMatcher.on(engine);
  }
}
