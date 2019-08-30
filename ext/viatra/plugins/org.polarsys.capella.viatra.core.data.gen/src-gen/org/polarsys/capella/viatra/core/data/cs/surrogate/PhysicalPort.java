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
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalPort__allocatedComponentPortsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalPort__realizedPhysicalPortsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalPort__realizingPhysicalPortsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.PhysicalPort__allocatedComponentPortsQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.PhysicalPort__realizedPhysicalPortsQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.PhysicalPort__realizingPhysicalPortsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in PhysicalPort.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PhysicalPort.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PhysicalPort__allocatedComponentPorts</li>
 * <li>PhysicalPort__realizedPhysicalPorts</li>
 * <li>PhysicalPort__realizingPhysicalPorts</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class PhysicalPort extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PhysicalPort instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new PhysicalPort();
    }
    return INSTANCE;
  }
  
  private static PhysicalPort INSTANCE;
  
  private PhysicalPort() throws ViatraQueryException {
    querySpecifications.add(PhysicalPort__allocatedComponentPortsQuerySpecification.instance());
    querySpecifications.add(PhysicalPort__realizedPhysicalPortsQuerySpecification.instance());
    querySpecifications.add(PhysicalPort__realizingPhysicalPortsQuerySpecification.instance());
  }
  
  public PhysicalPort__allocatedComponentPortsQuerySpecification getPhysicalPort__allocatedComponentPorts() throws ViatraQueryException {
    return PhysicalPort__allocatedComponentPortsQuerySpecification.instance();
  }
  
  public PhysicalPort__allocatedComponentPortsMatcher getPhysicalPort__allocatedComponentPorts(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalPort__allocatedComponentPortsMatcher.on(engine);
  }
  
  public PhysicalPort__realizedPhysicalPortsQuerySpecification getPhysicalPort__realizedPhysicalPorts() throws ViatraQueryException {
    return PhysicalPort__realizedPhysicalPortsQuerySpecification.instance();
  }
  
  public PhysicalPort__realizedPhysicalPortsMatcher getPhysicalPort__realizedPhysicalPorts(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalPort__realizedPhysicalPortsMatcher.on(engine);
  }
  
  public PhysicalPort__realizingPhysicalPortsQuerySpecification getPhysicalPort__realizingPhysicalPorts() throws ViatraQueryException {
    return PhysicalPort__realizingPhysicalPortsQuerySpecification.instance();
  }
  
  public PhysicalPort__realizingPhysicalPortsMatcher getPhysicalPort__realizingPhysicalPorts(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalPort__realizingPhysicalPortsMatcher.on(engine);
  }
}
