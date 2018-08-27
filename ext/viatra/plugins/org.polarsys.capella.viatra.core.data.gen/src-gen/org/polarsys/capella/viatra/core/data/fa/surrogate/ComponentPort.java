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
package org.polarsys.capella.viatra.core.data.fa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentPort__allocatedFunctionPortsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentPort__allocatingPhysicalPortsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentPort__componentExchangesMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentPort__delegatedComponentPortsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentPort__delegatingComponentPortsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentPort__realizedComponentPortsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentPort__realizingComponentPortsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentPort__allocatedFunctionPortsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentPort__allocatingPhysicalPortsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentPort__componentExchangesQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentPort__delegatedComponentPortsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentPort__delegatingComponentPortsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentPort__realizedComponentPortsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentPort__realizingComponentPortsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in ComponentPort.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ComponentPort.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ComponentPort__componentExchanges</li>
 * <li>ComponentPort__allocatedFunctionPorts</li>
 * <li>ComponentPort__delegatedComponentPorts</li>
 * <li>ComponentPort__delegatingComponentPorts</li>
 * <li>ComponentPort__allocatingPhysicalPorts</li>
 * <li>ComponentPort__realizedComponentPorts</li>
 * <li>ComponentPort__realizingComponentPorts</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ComponentPort extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ComponentPort instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ComponentPort();
    }
    return INSTANCE;
  }
  
  private static ComponentPort INSTANCE;
  
  private ComponentPort() throws ViatraQueryException {
    querySpecifications.add(ComponentPort__componentExchangesQuerySpecification.instance());
    querySpecifications.add(ComponentPort__allocatedFunctionPortsQuerySpecification.instance());
    querySpecifications.add(ComponentPort__delegatedComponentPortsQuerySpecification.instance());
    querySpecifications.add(ComponentPort__delegatingComponentPortsQuerySpecification.instance());
    querySpecifications.add(ComponentPort__allocatingPhysicalPortsQuerySpecification.instance());
    querySpecifications.add(ComponentPort__realizedComponentPortsQuerySpecification.instance());
    querySpecifications.add(ComponentPort__realizingComponentPortsQuerySpecification.instance());
  }
  
  public ComponentPort__componentExchangesQuerySpecification getComponentPort__componentExchanges() throws ViatraQueryException {
    return ComponentPort__componentExchangesQuerySpecification.instance();
  }
  
  public ComponentPort__componentExchangesMatcher getComponentPort__componentExchanges(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentPort__componentExchangesMatcher.on(engine);
  }
  
  public ComponentPort__allocatedFunctionPortsQuerySpecification getComponentPort__allocatedFunctionPorts() throws ViatraQueryException {
    return ComponentPort__allocatedFunctionPortsQuerySpecification.instance();
  }
  
  public ComponentPort__allocatedFunctionPortsMatcher getComponentPort__allocatedFunctionPorts(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentPort__allocatedFunctionPortsMatcher.on(engine);
  }
  
  public ComponentPort__delegatedComponentPortsQuerySpecification getComponentPort__delegatedComponentPorts() throws ViatraQueryException {
    return ComponentPort__delegatedComponentPortsQuerySpecification.instance();
  }
  
  public ComponentPort__delegatedComponentPortsMatcher getComponentPort__delegatedComponentPorts(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentPort__delegatedComponentPortsMatcher.on(engine);
  }
  
  public ComponentPort__delegatingComponentPortsQuerySpecification getComponentPort__delegatingComponentPorts() throws ViatraQueryException {
    return ComponentPort__delegatingComponentPortsQuerySpecification.instance();
  }
  
  public ComponentPort__delegatingComponentPortsMatcher getComponentPort__delegatingComponentPorts(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentPort__delegatingComponentPortsMatcher.on(engine);
  }
  
  public ComponentPort__allocatingPhysicalPortsQuerySpecification getComponentPort__allocatingPhysicalPorts() throws ViatraQueryException {
    return ComponentPort__allocatingPhysicalPortsQuerySpecification.instance();
  }
  
  public ComponentPort__allocatingPhysicalPortsMatcher getComponentPort__allocatingPhysicalPorts(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentPort__allocatingPhysicalPortsMatcher.on(engine);
  }
  
  public ComponentPort__realizedComponentPortsQuerySpecification getComponentPort__realizedComponentPorts() throws ViatraQueryException {
    return ComponentPort__realizedComponentPortsQuerySpecification.instance();
  }
  
  public ComponentPort__realizedComponentPortsMatcher getComponentPort__realizedComponentPorts(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentPort__realizedComponentPortsMatcher.on(engine);
  }
  
  public ComponentPort__realizingComponentPortsQuerySpecification getComponentPort__realizingComponentPorts() throws ViatraQueryException {
    return ComponentPort__realizingComponentPortsQuerySpecification.instance();
  }
  
  public ComponentPort__realizingComponentPortsMatcher getComponentPort__realizingComponentPorts(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentPort__realizingComponentPortsMatcher.on(engine);
  }
}
