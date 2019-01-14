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
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__allocatedComponentsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__allocatingComponentsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__containedComponentPortsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__containedPartsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__containedPhysicalPortsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__implementedInterfaceLinksMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__implementedInterfacesMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__providedInterfacesMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__provisionedComponentAllocationsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__provisioningComponentAllocationsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__requiredInterfacesMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__usedInterfaceLinksMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Component__usedInterfacesMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Component__allocatedComponentsQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Component__allocatingComponentsQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Component__containedComponentPortsQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Component__containedPartsQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Component__containedPhysicalPortsQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Component__implementedInterfaceLinksQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Component__implementedInterfacesQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Component__providedInterfacesQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Component__provisionedComponentAllocationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Component__provisioningComponentAllocationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Component__requiredInterfacesQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Component__usedInterfaceLinksQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Component__usedInterfacesQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in Component.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
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
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class Component extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Component instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new Component();
    }
    return INSTANCE;
  }
  
  private static Component INSTANCE;
  
  private Component() throws ViatraQueryException {
    querySpecifications.add(Component__usedInterfaceLinksQuerySpecification.instance());
    querySpecifications.add(Component__usedInterfacesQuerySpecification.instance());
    querySpecifications.add(Component__implementedInterfaceLinksQuerySpecification.instance());
    querySpecifications.add(Component__implementedInterfacesQuerySpecification.instance());
    querySpecifications.add(Component__provisionedComponentAllocationsQuerySpecification.instance());
    querySpecifications.add(Component__provisioningComponentAllocationsQuerySpecification.instance());
    querySpecifications.add(Component__allocatedComponentsQuerySpecification.instance());
    querySpecifications.add(Component__allocatingComponentsQuerySpecification.instance());
    querySpecifications.add(Component__providedInterfacesQuerySpecification.instance());
    querySpecifications.add(Component__requiredInterfacesQuerySpecification.instance());
    querySpecifications.add(Component__containedComponentPortsQuerySpecification.instance());
    querySpecifications.add(Component__containedPartsQuerySpecification.instance());
    querySpecifications.add(Component__containedPhysicalPortsQuerySpecification.instance());
  }
  
  public Component__usedInterfaceLinksQuerySpecification getComponent__usedInterfaceLinks() throws ViatraQueryException {
    return Component__usedInterfaceLinksQuerySpecification.instance();
  }
  
  public Component__usedInterfaceLinksMatcher getComponent__usedInterfaceLinks(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Component__usedInterfaceLinksMatcher.on(engine);
  }
  
  public Component__usedInterfacesQuerySpecification getComponent__usedInterfaces() throws ViatraQueryException {
    return Component__usedInterfacesQuerySpecification.instance();
  }
  
  public Component__usedInterfacesMatcher getComponent__usedInterfaces(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Component__usedInterfacesMatcher.on(engine);
  }
  
  public Component__implementedInterfaceLinksQuerySpecification getComponent__implementedInterfaceLinks() throws ViatraQueryException {
    return Component__implementedInterfaceLinksQuerySpecification.instance();
  }
  
  public Component__implementedInterfaceLinksMatcher getComponent__implementedInterfaceLinks(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Component__implementedInterfaceLinksMatcher.on(engine);
  }
  
  public Component__implementedInterfacesQuerySpecification getComponent__implementedInterfaces() throws ViatraQueryException {
    return Component__implementedInterfacesQuerySpecification.instance();
  }
  
  public Component__implementedInterfacesMatcher getComponent__implementedInterfaces(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Component__implementedInterfacesMatcher.on(engine);
  }
  
  public Component__provisionedComponentAllocationsQuerySpecification getComponent__provisionedComponentAllocations() throws ViatraQueryException {
    return Component__provisionedComponentAllocationsQuerySpecification.instance();
  }
  
  public Component__provisionedComponentAllocationsMatcher getComponent__provisionedComponentAllocations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Component__provisionedComponentAllocationsMatcher.on(engine);
  }
  
  public Component__provisioningComponentAllocationsQuerySpecification getComponent__provisioningComponentAllocations() throws ViatraQueryException {
    return Component__provisioningComponentAllocationsQuerySpecification.instance();
  }
  
  public Component__provisioningComponentAllocationsMatcher getComponent__provisioningComponentAllocations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Component__provisioningComponentAllocationsMatcher.on(engine);
  }
  
  public Component__allocatedComponentsQuerySpecification getComponent__allocatedComponents() throws ViatraQueryException {
    return Component__allocatedComponentsQuerySpecification.instance();
  }
  
  public Component__allocatedComponentsMatcher getComponent__allocatedComponents(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Component__allocatedComponentsMatcher.on(engine);
  }
  
  public Component__allocatingComponentsQuerySpecification getComponent__allocatingComponents() throws ViatraQueryException {
    return Component__allocatingComponentsQuerySpecification.instance();
  }
  
  public Component__allocatingComponentsMatcher getComponent__allocatingComponents(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Component__allocatingComponentsMatcher.on(engine);
  }
  
  public Component__providedInterfacesQuerySpecification getComponent__providedInterfaces() throws ViatraQueryException {
    return Component__providedInterfacesQuerySpecification.instance();
  }
  
  public Component__providedInterfacesMatcher getComponent__providedInterfaces(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Component__providedInterfacesMatcher.on(engine);
  }
  
  public Component__requiredInterfacesQuerySpecification getComponent__requiredInterfaces() throws ViatraQueryException {
    return Component__requiredInterfacesQuerySpecification.instance();
  }
  
  public Component__requiredInterfacesMatcher getComponent__requiredInterfaces(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Component__requiredInterfacesMatcher.on(engine);
  }
  
  public Component__containedComponentPortsQuerySpecification getComponent__containedComponentPorts() throws ViatraQueryException {
    return Component__containedComponentPortsQuerySpecification.instance();
  }
  
  public Component__containedComponentPortsMatcher getComponent__containedComponentPorts(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Component__containedComponentPortsMatcher.on(engine);
  }
  
  public Component__containedPartsQuerySpecification getComponent__containedParts() throws ViatraQueryException {
    return Component__containedPartsQuerySpecification.instance();
  }
  
  public Component__containedPartsMatcher getComponent__containedParts(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Component__containedPartsMatcher.on(engine);
  }
  
  public Component__containedPhysicalPortsQuerySpecification getComponent__containedPhysicalPorts() throws ViatraQueryException {
    return Component__containedPhysicalPortsQuerySpecification.instance();
  }
  
  public Component__containedPhysicalPortsMatcher getComponent__containedPhysicalPorts(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Component__containedPhysicalPortsMatcher.on(engine);
  }
}
