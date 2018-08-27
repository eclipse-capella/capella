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
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__allocatingComponentsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__allocatingInterfacesMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__exchangeItemsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__implementorComponentsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__interfaceImplementationsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__interfaceUsesMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__providingComponentPortsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__providingComponentsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__provisioningInterfaceAllocationsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__realizedContextInterfacesMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__realizedLogicalInterfacesMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__realizingLogicalInterfacesMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__realizingPhysicalInterfacesMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__requiringComponentPortsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__requiringComponentsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__userComponentsMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Interface__allocatingComponentsQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Interface__allocatingInterfacesQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Interface__exchangeItemsQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Interface__implementorComponentsQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Interface__interfaceImplementationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Interface__interfaceUsesQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Interface__providingComponentPortsQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Interface__providingComponentsQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Interface__provisioningInterfaceAllocationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Interface__realizedContextInterfacesQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Interface__realizedLogicalInterfacesQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Interface__realizingLogicalInterfacesQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Interface__realizingPhysicalInterfacesQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Interface__requiringComponentPortsQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Interface__requiringComponentsQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.Interface__userComponentsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in Interface.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Interface.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Interface__implementorComponents</li>
 * <li>Interface__userComponents</li>
 * <li>Interface__interfaceImplementations</li>
 * <li>Interface__interfaceUses</li>
 * <li>Interface__provisioningInterfaceAllocations</li>
 * <li>Interface__allocatingInterfaces</li>
 * <li>Interface__allocatingComponents</li>
 * <li>Interface__exchangeItems</li>
 * <li>Interface__requiringComponents</li>
 * <li>Interface__requiringComponentPorts</li>
 * <li>Interface__providingComponents</li>
 * <li>Interface__providingComponentPorts</li>
 * <li>Interface__realizingLogicalInterfaces</li>
 * <li>Interface__realizedContextInterfaces</li>
 * <li>Interface__realizingPhysicalInterfaces</li>
 * <li>Interface__realizedLogicalInterfaces</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class Interface extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Interface instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new Interface();
    }
    return INSTANCE;
  }
  
  private static Interface INSTANCE;
  
  private Interface() throws ViatraQueryException {
    querySpecifications.add(Interface__implementorComponentsQuerySpecification.instance());
    querySpecifications.add(Interface__userComponentsQuerySpecification.instance());
    querySpecifications.add(Interface__interfaceImplementationsQuerySpecification.instance());
    querySpecifications.add(Interface__interfaceUsesQuerySpecification.instance());
    querySpecifications.add(Interface__provisioningInterfaceAllocationsQuerySpecification.instance());
    querySpecifications.add(Interface__allocatingInterfacesQuerySpecification.instance());
    querySpecifications.add(Interface__allocatingComponentsQuerySpecification.instance());
    querySpecifications.add(Interface__exchangeItemsQuerySpecification.instance());
    querySpecifications.add(Interface__requiringComponentsQuerySpecification.instance());
    querySpecifications.add(Interface__requiringComponentPortsQuerySpecification.instance());
    querySpecifications.add(Interface__providingComponentsQuerySpecification.instance());
    querySpecifications.add(Interface__providingComponentPortsQuerySpecification.instance());
    querySpecifications.add(Interface__realizingLogicalInterfacesQuerySpecification.instance());
    querySpecifications.add(Interface__realizedContextInterfacesQuerySpecification.instance());
    querySpecifications.add(Interface__realizingPhysicalInterfacesQuerySpecification.instance());
    querySpecifications.add(Interface__realizedLogicalInterfacesQuerySpecification.instance());
  }
  
  public Interface__implementorComponentsQuerySpecification getInterface__implementorComponents() throws ViatraQueryException {
    return Interface__implementorComponentsQuerySpecification.instance();
  }
  
  public Interface__implementorComponentsMatcher getInterface__implementorComponents(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Interface__implementorComponentsMatcher.on(engine);
  }
  
  public Interface__userComponentsQuerySpecification getInterface__userComponents() throws ViatraQueryException {
    return Interface__userComponentsQuerySpecification.instance();
  }
  
  public Interface__userComponentsMatcher getInterface__userComponents(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Interface__userComponentsMatcher.on(engine);
  }
  
  public Interface__interfaceImplementationsQuerySpecification getInterface__interfaceImplementations() throws ViatraQueryException {
    return Interface__interfaceImplementationsQuerySpecification.instance();
  }
  
  public Interface__interfaceImplementationsMatcher getInterface__interfaceImplementations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Interface__interfaceImplementationsMatcher.on(engine);
  }
  
  public Interface__interfaceUsesQuerySpecification getInterface__interfaceUses() throws ViatraQueryException {
    return Interface__interfaceUsesQuerySpecification.instance();
  }
  
  public Interface__interfaceUsesMatcher getInterface__interfaceUses(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Interface__interfaceUsesMatcher.on(engine);
  }
  
  public Interface__provisioningInterfaceAllocationsQuerySpecification getInterface__provisioningInterfaceAllocations() throws ViatraQueryException {
    return Interface__provisioningInterfaceAllocationsQuerySpecification.instance();
  }
  
  public Interface__provisioningInterfaceAllocationsMatcher getInterface__provisioningInterfaceAllocations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Interface__provisioningInterfaceAllocationsMatcher.on(engine);
  }
  
  public Interface__allocatingInterfacesQuerySpecification getInterface__allocatingInterfaces() throws ViatraQueryException {
    return Interface__allocatingInterfacesQuerySpecification.instance();
  }
  
  public Interface__allocatingInterfacesMatcher getInterface__allocatingInterfaces(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Interface__allocatingInterfacesMatcher.on(engine);
  }
  
  public Interface__allocatingComponentsQuerySpecification getInterface__allocatingComponents() throws ViatraQueryException {
    return Interface__allocatingComponentsQuerySpecification.instance();
  }
  
  public Interface__allocatingComponentsMatcher getInterface__allocatingComponents(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Interface__allocatingComponentsMatcher.on(engine);
  }
  
  public Interface__exchangeItemsQuerySpecification getInterface__exchangeItems() throws ViatraQueryException {
    return Interface__exchangeItemsQuerySpecification.instance();
  }
  
  public Interface__exchangeItemsMatcher getInterface__exchangeItems(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Interface__exchangeItemsMatcher.on(engine);
  }
  
  public Interface__requiringComponentsQuerySpecification getInterface__requiringComponents() throws ViatraQueryException {
    return Interface__requiringComponentsQuerySpecification.instance();
  }
  
  public Interface__requiringComponentsMatcher getInterface__requiringComponents(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Interface__requiringComponentsMatcher.on(engine);
  }
  
  public Interface__requiringComponentPortsQuerySpecification getInterface__requiringComponentPorts() throws ViatraQueryException {
    return Interface__requiringComponentPortsQuerySpecification.instance();
  }
  
  public Interface__requiringComponentPortsMatcher getInterface__requiringComponentPorts(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Interface__requiringComponentPortsMatcher.on(engine);
  }
  
  public Interface__providingComponentsQuerySpecification getInterface__providingComponents() throws ViatraQueryException {
    return Interface__providingComponentsQuerySpecification.instance();
  }
  
  public Interface__providingComponentsMatcher getInterface__providingComponents(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Interface__providingComponentsMatcher.on(engine);
  }
  
  public Interface__providingComponentPortsQuerySpecification getInterface__providingComponentPorts() throws ViatraQueryException {
    return Interface__providingComponentPortsQuerySpecification.instance();
  }
  
  public Interface__providingComponentPortsMatcher getInterface__providingComponentPorts(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Interface__providingComponentPortsMatcher.on(engine);
  }
  
  public Interface__realizingLogicalInterfacesQuerySpecification getInterface__realizingLogicalInterfaces() throws ViatraQueryException {
    return Interface__realizingLogicalInterfacesQuerySpecification.instance();
  }
  
  public Interface__realizingLogicalInterfacesMatcher getInterface__realizingLogicalInterfaces(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Interface__realizingLogicalInterfacesMatcher.on(engine);
  }
  
  public Interface__realizedContextInterfacesQuerySpecification getInterface__realizedContextInterfaces() throws ViatraQueryException {
    return Interface__realizedContextInterfacesQuerySpecification.instance();
  }
  
  public Interface__realizedContextInterfacesMatcher getInterface__realizedContextInterfaces(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Interface__realizedContextInterfacesMatcher.on(engine);
  }
  
  public Interface__realizingPhysicalInterfacesQuerySpecification getInterface__realizingPhysicalInterfaces() throws ViatraQueryException {
    return Interface__realizingPhysicalInterfacesQuerySpecification.instance();
  }
  
  public Interface__realizingPhysicalInterfacesMatcher getInterface__realizingPhysicalInterfaces(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Interface__realizingPhysicalInterfacesMatcher.on(engine);
  }
  
  public Interface__realizedLogicalInterfacesQuerySpecification getInterface__realizedLogicalInterfaces() throws ViatraQueryException {
    return Interface__realizedLogicalInterfacesQuerySpecification.instance();
  }
  
  public Interface__realizedLogicalInterfacesMatcher getInterface__realizedLogicalInterfaces(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Interface__realizedLogicalInterfacesMatcher.on(engine);
  }
}
