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
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__allocatingComponents;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__allocatingInterfaces;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__exchangeItems;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__implementorComponents;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__interfaceImplementations;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__interfaceUses;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__providingComponentPorts;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__providingComponents;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__provisioningInterfaceAllocations;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__realizedContextInterfaces;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__realizedLogicalInterfaces;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__realizingLogicalInterfaces;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__realizingPhysicalInterfaces;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__requiringComponentPorts;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__requiringComponents;
import org.polarsys.capella.viatra.core.data.cs.surrogate.Interface__userComponents;

/**
 * A pattern group formed of all public patterns defined in Interface.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
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
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class Interface extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Interface instance() {
    if (INSTANCE == null) {
        INSTANCE = new Interface();
    }
    return INSTANCE;
  }
  
  private static Interface INSTANCE;
  
  private Interface() {
    querySpecifications.add(Interface__implementorComponents.instance());
    querySpecifications.add(Interface__userComponents.instance());
    querySpecifications.add(Interface__interfaceImplementations.instance());
    querySpecifications.add(Interface__interfaceUses.instance());
    querySpecifications.add(Interface__provisioningInterfaceAllocations.instance());
    querySpecifications.add(Interface__allocatingInterfaces.instance());
    querySpecifications.add(Interface__allocatingComponents.instance());
    querySpecifications.add(Interface__exchangeItems.instance());
    querySpecifications.add(Interface__requiringComponents.instance());
    querySpecifications.add(Interface__requiringComponentPorts.instance());
    querySpecifications.add(Interface__providingComponents.instance());
    querySpecifications.add(Interface__providingComponentPorts.instance());
    querySpecifications.add(Interface__realizingLogicalInterfaces.instance());
    querySpecifications.add(Interface__realizedContextInterfaces.instance());
    querySpecifications.add(Interface__realizingPhysicalInterfaces.instance());
    querySpecifications.add(Interface__realizedLogicalInterfaces.instance());
  }
  
  public Interface__implementorComponents getInterface__implementorComponents() {
    return Interface__implementorComponents.instance();
  }
  
  public Interface__implementorComponents.Matcher getInterface__implementorComponents(final ViatraQueryEngine engine) {
    return Interface__implementorComponents.Matcher.on(engine);
  }
  
  public Interface__userComponents getInterface__userComponents() {
    return Interface__userComponents.instance();
  }
  
  public Interface__userComponents.Matcher getInterface__userComponents(final ViatraQueryEngine engine) {
    return Interface__userComponents.Matcher.on(engine);
  }
  
  public Interface__interfaceImplementations getInterface__interfaceImplementations() {
    return Interface__interfaceImplementations.instance();
  }
  
  public Interface__interfaceImplementations.Matcher getInterface__interfaceImplementations(final ViatraQueryEngine engine) {
    return Interface__interfaceImplementations.Matcher.on(engine);
  }
  
  public Interface__interfaceUses getInterface__interfaceUses() {
    return Interface__interfaceUses.instance();
  }
  
  public Interface__interfaceUses.Matcher getInterface__interfaceUses(final ViatraQueryEngine engine) {
    return Interface__interfaceUses.Matcher.on(engine);
  }
  
  public Interface__provisioningInterfaceAllocations getInterface__provisioningInterfaceAllocations() {
    return Interface__provisioningInterfaceAllocations.instance();
  }
  
  public Interface__provisioningInterfaceAllocations.Matcher getInterface__provisioningInterfaceAllocations(final ViatraQueryEngine engine) {
    return Interface__provisioningInterfaceAllocations.Matcher.on(engine);
  }
  
  public Interface__allocatingInterfaces getInterface__allocatingInterfaces() {
    return Interface__allocatingInterfaces.instance();
  }
  
  public Interface__allocatingInterfaces.Matcher getInterface__allocatingInterfaces(final ViatraQueryEngine engine) {
    return Interface__allocatingInterfaces.Matcher.on(engine);
  }
  
  public Interface__allocatingComponents getInterface__allocatingComponents() {
    return Interface__allocatingComponents.instance();
  }
  
  public Interface__allocatingComponents.Matcher getInterface__allocatingComponents(final ViatraQueryEngine engine) {
    return Interface__allocatingComponents.Matcher.on(engine);
  }
  
  public Interface__exchangeItems getInterface__exchangeItems() {
    return Interface__exchangeItems.instance();
  }
  
  public Interface__exchangeItems.Matcher getInterface__exchangeItems(final ViatraQueryEngine engine) {
    return Interface__exchangeItems.Matcher.on(engine);
  }
  
  public Interface__requiringComponents getInterface__requiringComponents() {
    return Interface__requiringComponents.instance();
  }
  
  public Interface__requiringComponents.Matcher getInterface__requiringComponents(final ViatraQueryEngine engine) {
    return Interface__requiringComponents.Matcher.on(engine);
  }
  
  public Interface__requiringComponentPorts getInterface__requiringComponentPorts() {
    return Interface__requiringComponentPorts.instance();
  }
  
  public Interface__requiringComponentPorts.Matcher getInterface__requiringComponentPorts(final ViatraQueryEngine engine) {
    return Interface__requiringComponentPorts.Matcher.on(engine);
  }
  
  public Interface__providingComponents getInterface__providingComponents() {
    return Interface__providingComponents.instance();
  }
  
  public Interface__providingComponents.Matcher getInterface__providingComponents(final ViatraQueryEngine engine) {
    return Interface__providingComponents.Matcher.on(engine);
  }
  
  public Interface__providingComponentPorts getInterface__providingComponentPorts() {
    return Interface__providingComponentPorts.instance();
  }
  
  public Interface__providingComponentPorts.Matcher getInterface__providingComponentPorts(final ViatraQueryEngine engine) {
    return Interface__providingComponentPorts.Matcher.on(engine);
  }
  
  public Interface__realizingLogicalInterfaces getInterface__realizingLogicalInterfaces() {
    return Interface__realizingLogicalInterfaces.instance();
  }
  
  public Interface__realizingLogicalInterfaces.Matcher getInterface__realizingLogicalInterfaces(final ViatraQueryEngine engine) {
    return Interface__realizingLogicalInterfaces.Matcher.on(engine);
  }
  
  public Interface__realizedContextInterfaces getInterface__realizedContextInterfaces() {
    return Interface__realizedContextInterfaces.instance();
  }
  
  public Interface__realizedContextInterfaces.Matcher getInterface__realizedContextInterfaces(final ViatraQueryEngine engine) {
    return Interface__realizedContextInterfaces.Matcher.on(engine);
  }
  
  public Interface__realizingPhysicalInterfaces getInterface__realizingPhysicalInterfaces() {
    return Interface__realizingPhysicalInterfaces.instance();
  }
  
  public Interface__realizingPhysicalInterfaces.Matcher getInterface__realizingPhysicalInterfaces(final ViatraQueryEngine engine) {
    return Interface__realizingPhysicalInterfaces.Matcher.on(engine);
  }
  
  public Interface__realizedLogicalInterfaces getInterface__realizedLogicalInterfaces() {
    return Interface__realizedLogicalInterfaces.instance();
  }
  
  public Interface__realizedLogicalInterfaces.Matcher getInterface__realizedLogicalInterfaces(final ViatraQueryEngine engine) {
    return Interface__realizedLogicalInterfaces.Matcher.on(engine);
  }
}
