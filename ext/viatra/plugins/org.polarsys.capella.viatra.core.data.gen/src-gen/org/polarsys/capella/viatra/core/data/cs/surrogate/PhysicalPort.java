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
package org.polarsys.capella.viatra.core.data.cs.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in PhysicalPort.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PhysicalPort.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PhysicalPort__allocatedComponentPorts</li>
 * <li>PhysicalPort__realizedPhysicalPorts</li>
 * <li>PhysicalPort__realizingPhysicalPorts</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class PhysicalPort extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PhysicalPort instance() {
    if (INSTANCE == null) {
        INSTANCE = new PhysicalPort();
    }
    return INSTANCE;
  }
  
  private static PhysicalPort INSTANCE;
  
  private PhysicalPort() {
    querySpecifications.add(PhysicalPort__allocatedComponentPorts.instance());
    querySpecifications.add(PhysicalPort__realizedPhysicalPorts.instance());
    querySpecifications.add(PhysicalPort__realizingPhysicalPorts.instance());
  }
  
  public PhysicalPort__allocatedComponentPorts getPhysicalPort__allocatedComponentPorts() {
    return PhysicalPort__allocatedComponentPorts.instance();
  }
  
  public PhysicalPort__allocatedComponentPorts.Matcher getPhysicalPort__allocatedComponentPorts(final ViatraQueryEngine engine) {
    return PhysicalPort__allocatedComponentPorts.Matcher.on(engine);
  }
  
  public PhysicalPort__realizedPhysicalPorts getPhysicalPort__realizedPhysicalPorts() {
    return PhysicalPort__realizedPhysicalPorts.instance();
  }
  
  public PhysicalPort__realizedPhysicalPorts.Matcher getPhysicalPort__realizedPhysicalPorts(final ViatraQueryEngine engine) {
    return PhysicalPort__realizedPhysicalPorts.Matcher.on(engine);
  }
  
  public PhysicalPort__realizingPhysicalPorts getPhysicalPort__realizingPhysicalPorts() {
    return PhysicalPort__realizingPhysicalPorts.instance();
  }
  
  public PhysicalPort__realizingPhysicalPorts.Matcher getPhysicalPort__realizingPhysicalPorts(final ViatraQueryEngine engine) {
    return PhysicalPort__realizingPhysicalPorts.Matcher.on(engine);
  }
}
