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
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__allocatedComponents;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__allocatingComponents;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__containedComponentPorts;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__containedParts;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__containedPhysicalPorts;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__implementedInterfaceLinks;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__implementedInterfaces;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__providedInterfaces;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__provisionedComponentAllocations;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__provisioningComponentAllocations;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__requiredInterfaces;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__usedInterfaceLinks;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__usedInterfaces;

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
 * <li>Component__provisionedComponentAllocations</li>
 * <li>Component__provisioningComponentAllocations</li>
 * <li>Component__allocatedComponents</li>
 * <li>Component__allocatingComponents</li>
 * <li>Component__providedInterfaces</li>
 * <li>Component__requiredInterfaces</li>
 * <li>Component__containedComponentPorts</li>
 * <li>Component__containedParts</li>
 * <li>Component__containedPhysicalPorts</li>
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
    querySpecifications.add(Component__provisionedComponentAllocations.instance());
    querySpecifications.add(Component__provisioningComponentAllocations.instance());
    querySpecifications.add(Component__allocatedComponents.instance());
    querySpecifications.add(Component__allocatingComponents.instance());
    querySpecifications.add(Component__providedInterfaces.instance());
    querySpecifications.add(Component__requiredInterfaces.instance());
    querySpecifications.add(Component__containedComponentPorts.instance());
    querySpecifications.add(Component__containedParts.instance());
    querySpecifications.add(Component__containedPhysicalPorts.instance());
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
  
  public Component__provisionedComponentAllocations getComponent__provisionedComponentAllocations() {
    return Component__provisionedComponentAllocations.instance();
  }
  
  public Component__provisionedComponentAllocations.Matcher getComponent__provisionedComponentAllocations(final ViatraQueryEngine engine) {
    return Component__provisionedComponentAllocations.Matcher.on(engine);
  }
  
  public Component__provisioningComponentAllocations getComponent__provisioningComponentAllocations() {
    return Component__provisioningComponentAllocations.instance();
  }
  
  public Component__provisioningComponentAllocations.Matcher getComponent__provisioningComponentAllocations(final ViatraQueryEngine engine) {
    return Component__provisioningComponentAllocations.Matcher.on(engine);
  }
  
  public Component__allocatedComponents getComponent__allocatedComponents() {
    return Component__allocatedComponents.instance();
  }
  
  public Component__allocatedComponents.Matcher getComponent__allocatedComponents(final ViatraQueryEngine engine) {
    return Component__allocatedComponents.Matcher.on(engine);
  }
  
  public Component__allocatingComponents getComponent__allocatingComponents() {
    return Component__allocatingComponents.instance();
  }
  
  public Component__allocatingComponents.Matcher getComponent__allocatingComponents(final ViatraQueryEngine engine) {
    return Component__allocatingComponents.Matcher.on(engine);
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
}
