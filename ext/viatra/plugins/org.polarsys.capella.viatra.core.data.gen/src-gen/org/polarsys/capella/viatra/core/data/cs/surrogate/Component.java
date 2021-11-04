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
 * A pattern group formed of all public patterns defined in Component.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Component.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Component__usedInterfaceLinks</li>
 * <li>Component__usedInterfaces</li>
 * <li>Component__implementedInterfaceLinks</li>
 * <li>Component__implementedInterfaces</li>
 * <li>Component__providedInterfaces</li>
 * <li>Component__requiredInterfaces</li>
 * <li>Component__containedComponentPorts</li>
 * <li>Component__containedParts</li>
 * <li>Component__containedPhysicalPorts</li>
 * <li>CapabilityRealization__involvedComponents</li>
 * <li>Component__representingParts</li>
 * <li>Component__realizingComponents</li>
 * <li>Component__realizedComponents</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class Component extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Component instance() {
    if (INSTANCE == null) {
        INSTANCE = new Component();
    }
    return INSTANCE;
  }
  
  private static Component INSTANCE;
  
  private Component() {
    querySpecifications.add(Component__usedInterfaceLinks.instance());
    querySpecifications.add(Component__usedInterfaces.instance());
    querySpecifications.add(Component__implementedInterfaceLinks.instance());
    querySpecifications.add(Component__implementedInterfaces.instance());
    querySpecifications.add(Component__providedInterfaces.instance());
    querySpecifications.add(Component__requiredInterfaces.instance());
    querySpecifications.add(Component__containedComponentPorts.instance());
    querySpecifications.add(Component__containedParts.instance());
    querySpecifications.add(Component__containedPhysicalPorts.instance());
    querySpecifications.add(CapabilityRealization__involvedComponents.instance());
    querySpecifications.add(Component__representingParts.instance());
    querySpecifications.add(Component__realizingComponents.instance());
    querySpecifications.add(Component__realizedComponents.instance());
  }
  
  public Component__usedInterfaceLinks getComponent__usedInterfaceLinks() {
    return Component__usedInterfaceLinks.instance();
  }
  
  public Component__usedInterfaceLinks.Matcher getComponent__usedInterfaceLinks(final ViatraQueryEngine engine) {
    return Component__usedInterfaceLinks.Matcher.on(engine);
  }
  
  public Component__usedInterfaces getComponent__usedInterfaces() {
    return Component__usedInterfaces.instance();
  }
  
  public Component__usedInterfaces.Matcher getComponent__usedInterfaces(final ViatraQueryEngine engine) {
    return Component__usedInterfaces.Matcher.on(engine);
  }
  
  public Component__implementedInterfaceLinks getComponent__implementedInterfaceLinks() {
    return Component__implementedInterfaceLinks.instance();
  }
  
  public Component__implementedInterfaceLinks.Matcher getComponent__implementedInterfaceLinks(final ViatraQueryEngine engine) {
    return Component__implementedInterfaceLinks.Matcher.on(engine);
  }
  
  public Component__implementedInterfaces getComponent__implementedInterfaces() {
    return Component__implementedInterfaces.instance();
  }
  
  public Component__implementedInterfaces.Matcher getComponent__implementedInterfaces(final ViatraQueryEngine engine) {
    return Component__implementedInterfaces.Matcher.on(engine);
  }
  
  public Component__providedInterfaces getComponent__providedInterfaces() {
    return Component__providedInterfaces.instance();
  }
  
  public Component__providedInterfaces.Matcher getComponent__providedInterfaces(final ViatraQueryEngine engine) {
    return Component__providedInterfaces.Matcher.on(engine);
  }
  
  public Component__requiredInterfaces getComponent__requiredInterfaces() {
    return Component__requiredInterfaces.instance();
  }
  
  public Component__requiredInterfaces.Matcher getComponent__requiredInterfaces(final ViatraQueryEngine engine) {
    return Component__requiredInterfaces.Matcher.on(engine);
  }
  
  public Component__containedComponentPorts getComponent__containedComponentPorts() {
    return Component__containedComponentPorts.instance();
  }
  
  public Component__containedComponentPorts.Matcher getComponent__containedComponentPorts(final ViatraQueryEngine engine) {
    return Component__containedComponentPorts.Matcher.on(engine);
  }
  
  public Component__containedParts getComponent__containedParts() {
    return Component__containedParts.instance();
  }
  
  public Component__containedParts.Matcher getComponent__containedParts(final ViatraQueryEngine engine) {
    return Component__containedParts.Matcher.on(engine);
  }
  
  public Component__containedPhysicalPorts getComponent__containedPhysicalPorts() {
    return Component__containedPhysicalPorts.instance();
  }
  
  public Component__containedPhysicalPorts.Matcher getComponent__containedPhysicalPorts(final ViatraQueryEngine engine) {
    return Component__containedPhysicalPorts.Matcher.on(engine);
  }
  
  public CapabilityRealization__involvedComponents getCapabilityRealization__involvedComponents() {
    return CapabilityRealization__involvedComponents.instance();
  }
  
  public CapabilityRealization__involvedComponents.Matcher getCapabilityRealization__involvedComponents(final ViatraQueryEngine engine) {
    return CapabilityRealization__involvedComponents.Matcher.on(engine);
  }
  
  public Component__representingParts getComponent__representingParts() {
    return Component__representingParts.instance();
  }
  
  public Component__representingParts.Matcher getComponent__representingParts(final ViatraQueryEngine engine) {
    return Component__representingParts.Matcher.on(engine);
  }
  
  public Component__realizingComponents getComponent__realizingComponents() {
    return Component__realizingComponents.instance();
  }
  
  public Component__realizingComponents.Matcher getComponent__realizingComponents(final ViatraQueryEngine engine) {
    return Component__realizingComponents.Matcher.on(engine);
  }
  
  public Component__realizedComponents getComponent__realizedComponents() {
    return Component__realizedComponents.instance();
  }
  
  public Component__realizedComponents.Matcher getComponent__realizedComponents(final ViatraQueryEngine engine) {
    return Component__realizedComponents.Matcher.on(engine);
  }
}
