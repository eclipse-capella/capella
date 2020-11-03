/**
 * 
 *   Copyright (c) 2006, 2020 THALES DMS FRANCE.
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
package org.polarsys.capella.viatra.core.data.information.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.information.surrogate.PortAllocation__allocatedPort;
import org.polarsys.capella.viatra.core.data.information.surrogate.PortAllocation__allocatingPort;

/**
 * A pattern group formed of all public patterns defined in PortAllocation.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PortAllocation.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PortAllocation__allocatedPort</li>
 * <li>PortAllocation__allocatingPort</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class PortAllocation extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PortAllocation instance() {
    if (INSTANCE == null) {
        INSTANCE = new PortAllocation();
    }
    return INSTANCE;
  }
  
  private static PortAllocation INSTANCE;
  
  private PortAllocation() {
    querySpecifications.add(PortAllocation__allocatedPort.instance());
    querySpecifications.add(PortAllocation__allocatingPort.instance());
  }
  
  public PortAllocation__allocatedPort getPortAllocation__allocatedPort() {
    return PortAllocation__allocatedPort.instance();
  }
  
  public PortAllocation__allocatedPort.Matcher getPortAllocation__allocatedPort(final ViatraQueryEngine engine) {
    return PortAllocation__allocatedPort.Matcher.on(engine);
  }
  
  public PortAllocation__allocatingPort getPortAllocation__allocatingPort() {
    return PortAllocation__allocatingPort.instance();
  }
  
  public PortAllocation__allocatingPort.Matcher getPortAllocation__allocatingPort(final ViatraQueryEngine engine) {
    return PortAllocation__allocatingPort.Matcher.on(engine);
  }
}
