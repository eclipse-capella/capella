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
package org.polarsys.capella.viatra.core.data.fa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentPort__allocatedFunctionPorts;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentPort__allocatingPhysicalPorts;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentPort__componentExchanges;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentPort__delegatedComponentPorts;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentPort__delegatingComponentPorts;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentPort__realizedComponentPorts;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentPort__realizingComponentPorts;

/**
 * A pattern group formed of all public patterns defined in ComponentPort.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
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
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class ComponentPort extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ComponentPort instance() {
    if (INSTANCE == null) {
        INSTANCE = new ComponentPort();
    }
    return INSTANCE;
  }
  
  private static ComponentPort INSTANCE;
  
  private ComponentPort() {
    querySpecifications.add(ComponentPort__componentExchanges.instance());
    querySpecifications.add(ComponentPort__allocatedFunctionPorts.instance());
    querySpecifications.add(ComponentPort__delegatedComponentPorts.instance());
    querySpecifications.add(ComponentPort__delegatingComponentPorts.instance());
    querySpecifications.add(ComponentPort__allocatingPhysicalPorts.instance());
    querySpecifications.add(ComponentPort__realizedComponentPorts.instance());
    querySpecifications.add(ComponentPort__realizingComponentPorts.instance());
  }
  
  public ComponentPort__componentExchanges getComponentPort__componentExchanges() {
    return ComponentPort__componentExchanges.instance();
  }
  
  public ComponentPort__componentExchanges.Matcher getComponentPort__componentExchanges(final ViatraQueryEngine engine) {
    return ComponentPort__componentExchanges.Matcher.on(engine);
  }
  
  public ComponentPort__allocatedFunctionPorts getComponentPort__allocatedFunctionPorts() {
    return ComponentPort__allocatedFunctionPorts.instance();
  }
  
  public ComponentPort__allocatedFunctionPorts.Matcher getComponentPort__allocatedFunctionPorts(final ViatraQueryEngine engine) {
    return ComponentPort__allocatedFunctionPorts.Matcher.on(engine);
  }
  
  public ComponentPort__delegatedComponentPorts getComponentPort__delegatedComponentPorts() {
    return ComponentPort__delegatedComponentPorts.instance();
  }
  
  public ComponentPort__delegatedComponentPorts.Matcher getComponentPort__delegatedComponentPorts(final ViatraQueryEngine engine) {
    return ComponentPort__delegatedComponentPorts.Matcher.on(engine);
  }
  
  public ComponentPort__delegatingComponentPorts getComponentPort__delegatingComponentPorts() {
    return ComponentPort__delegatingComponentPorts.instance();
  }
  
  public ComponentPort__delegatingComponentPorts.Matcher getComponentPort__delegatingComponentPorts(final ViatraQueryEngine engine) {
    return ComponentPort__delegatingComponentPorts.Matcher.on(engine);
  }
  
  public ComponentPort__allocatingPhysicalPorts getComponentPort__allocatingPhysicalPorts() {
    return ComponentPort__allocatingPhysicalPorts.instance();
  }
  
  public ComponentPort__allocatingPhysicalPorts.Matcher getComponentPort__allocatingPhysicalPorts(final ViatraQueryEngine engine) {
    return ComponentPort__allocatingPhysicalPorts.Matcher.on(engine);
  }
  
  public ComponentPort__realizedComponentPorts getComponentPort__realizedComponentPorts() {
    return ComponentPort__realizedComponentPorts.instance();
  }
  
  public ComponentPort__realizedComponentPorts.Matcher getComponentPort__realizedComponentPorts(final ViatraQueryEngine engine) {
    return ComponentPort__realizedComponentPorts.Matcher.on(engine);
  }
  
  public ComponentPort__realizingComponentPorts getComponentPort__realizingComponentPorts() {
    return ComponentPort__realizingComponentPorts.instance();
  }
  
  public ComponentPort__realizingComponentPorts.Matcher getComponentPort__realizingComponentPorts(final ViatraQueryEngine engine) {
    return ComponentPort__realizingComponentPorts.Matcher.on(engine);
  }
}
