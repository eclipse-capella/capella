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

/**
 * A pattern group formed of all public patterns defined in ComponentExchange.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ComponentExchange.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ComponentExchange__allocatedFunctionalExchanges</li>
 * <li>ComponentExchange__incomingComponentExchangeRealizations</li>
 * <li>ComponentExchange__outgoingComponentExchangeRealizations</li>
 * <li>ComponentExchange__outgoingComponentExchangeFunctionalExchangeAllocations</li>
 * <li>ComponentExchange__sourcePort</li>
 * <li>ComponentExchange__sourcePart</li>
 * <li>ComponentExchange__targetPort</li>
 * <li>ComponentExchange__targetPart</li>
 * <li>ComponentExchange__categories</li>
 * <li>ComponentExchange__allocatorPhysicalLinks</li>
 * <li>ComponentExchange__realizedComponentExchanges</li>
 * <li>ComponentExchange__realizingComponentExchanges</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class ComponentExchange extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ComponentExchange instance() {
    if (INSTANCE == null) {
        INSTANCE = new ComponentExchange();
    }
    return INSTANCE;
  }
  
  private static ComponentExchange INSTANCE;
  
  private ComponentExchange() {
    querySpecifications.add(ComponentExchange__allocatedFunctionalExchanges.instance());
    querySpecifications.add(ComponentExchange__incomingComponentExchangeRealizations.instance());
    querySpecifications.add(ComponentExchange__outgoingComponentExchangeRealizations.instance());
    querySpecifications.add(ComponentExchange__outgoingComponentExchangeFunctionalExchangeAllocations.instance());
    querySpecifications.add(ComponentExchange__sourcePort.instance());
    querySpecifications.add(ComponentExchange__sourcePart.instance());
    querySpecifications.add(ComponentExchange__targetPort.instance());
    querySpecifications.add(ComponentExchange__targetPart.instance());
    querySpecifications.add(ComponentExchange__categories.instance());
    querySpecifications.add(ComponentExchange__allocatorPhysicalLinks.instance());
    querySpecifications.add(ComponentExchange__realizedComponentExchanges.instance());
    querySpecifications.add(ComponentExchange__realizingComponentExchanges.instance());
  }
  
  public ComponentExchange__allocatedFunctionalExchanges getComponentExchange__allocatedFunctionalExchanges() {
    return ComponentExchange__allocatedFunctionalExchanges.instance();
  }
  
  public ComponentExchange__allocatedFunctionalExchanges.Matcher getComponentExchange__allocatedFunctionalExchanges(final ViatraQueryEngine engine) {
    return ComponentExchange__allocatedFunctionalExchanges.Matcher.on(engine);
  }
  
  public ComponentExchange__incomingComponentExchangeRealizations getComponentExchange__incomingComponentExchangeRealizations() {
    return ComponentExchange__incomingComponentExchangeRealizations.instance();
  }
  
  public ComponentExchange__incomingComponentExchangeRealizations.Matcher getComponentExchange__incomingComponentExchangeRealizations(final ViatraQueryEngine engine) {
    return ComponentExchange__incomingComponentExchangeRealizations.Matcher.on(engine);
  }
  
  public ComponentExchange__outgoingComponentExchangeRealizations getComponentExchange__outgoingComponentExchangeRealizations() {
    return ComponentExchange__outgoingComponentExchangeRealizations.instance();
  }
  
  public ComponentExchange__outgoingComponentExchangeRealizations.Matcher getComponentExchange__outgoingComponentExchangeRealizations(final ViatraQueryEngine engine) {
    return ComponentExchange__outgoingComponentExchangeRealizations.Matcher.on(engine);
  }
  
  public ComponentExchange__outgoingComponentExchangeFunctionalExchangeAllocations getComponentExchange__outgoingComponentExchangeFunctionalExchangeAllocations() {
    return ComponentExchange__outgoingComponentExchangeFunctionalExchangeAllocations.instance();
  }
  
  public ComponentExchange__outgoingComponentExchangeFunctionalExchangeAllocations.Matcher getComponentExchange__outgoingComponentExchangeFunctionalExchangeAllocations(final ViatraQueryEngine engine) {
    return ComponentExchange__outgoingComponentExchangeFunctionalExchangeAllocations.Matcher.on(engine);
  }
  
  public ComponentExchange__sourcePort getComponentExchange__sourcePort() {
    return ComponentExchange__sourcePort.instance();
  }
  
  public ComponentExchange__sourcePort.Matcher getComponentExchange__sourcePort(final ViatraQueryEngine engine) {
    return ComponentExchange__sourcePort.Matcher.on(engine);
  }
  
  public ComponentExchange__sourcePart getComponentExchange__sourcePart() {
    return ComponentExchange__sourcePart.instance();
  }
  
  public ComponentExchange__sourcePart.Matcher getComponentExchange__sourcePart(final ViatraQueryEngine engine) {
    return ComponentExchange__sourcePart.Matcher.on(engine);
  }
  
  public ComponentExchange__targetPort getComponentExchange__targetPort() {
    return ComponentExchange__targetPort.instance();
  }
  
  public ComponentExchange__targetPort.Matcher getComponentExchange__targetPort(final ViatraQueryEngine engine) {
    return ComponentExchange__targetPort.Matcher.on(engine);
  }
  
  public ComponentExchange__targetPart getComponentExchange__targetPart() {
    return ComponentExchange__targetPart.instance();
  }
  
  public ComponentExchange__targetPart.Matcher getComponentExchange__targetPart(final ViatraQueryEngine engine) {
    return ComponentExchange__targetPart.Matcher.on(engine);
  }
  
  public ComponentExchange__categories getComponentExchange__categories() {
    return ComponentExchange__categories.instance();
  }
  
  public ComponentExchange__categories.Matcher getComponentExchange__categories(final ViatraQueryEngine engine) {
    return ComponentExchange__categories.Matcher.on(engine);
  }
  
  public ComponentExchange__allocatorPhysicalLinks getComponentExchange__allocatorPhysicalLinks() {
    return ComponentExchange__allocatorPhysicalLinks.instance();
  }
  
  public ComponentExchange__allocatorPhysicalLinks.Matcher getComponentExchange__allocatorPhysicalLinks(final ViatraQueryEngine engine) {
    return ComponentExchange__allocatorPhysicalLinks.Matcher.on(engine);
  }
  
  public ComponentExchange__realizedComponentExchanges getComponentExchange__realizedComponentExchanges() {
    return ComponentExchange__realizedComponentExchanges.instance();
  }
  
  public ComponentExchange__realizedComponentExchanges.Matcher getComponentExchange__realizedComponentExchanges(final ViatraQueryEngine engine) {
    return ComponentExchange__realizedComponentExchanges.Matcher.on(engine);
  }
  
  public ComponentExchange__realizingComponentExchanges getComponentExchange__realizingComponentExchanges() {
    return ComponentExchange__realizingComponentExchanges.instance();
  }
  
  public ComponentExchange__realizingComponentExchanges.Matcher getComponentExchange__realizingComponentExchanges(final ViatraQueryEngine engine) {
    return ComponentExchange__realizingComponentExchanges.Matcher.on(engine);
  }
}
