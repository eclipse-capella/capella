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
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchange__allocatedFunctionalExchangesMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchange__allocatorPhysicalLinksMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchange__categoriesMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchange__incomingComponentExchangeRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchange__outgoingComponentExchangeFunctionalExchangeAllocationsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchange__outgoingComponentExchangeRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchange__realizedComponentExchangesMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchange__realizingComponentExchangesMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchange__sourcePartMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchange__sourcePortMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchange__targetPartMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ComponentExchange__targetPortMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentExchange__allocatedFunctionalExchangesQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentExchange__allocatorPhysicalLinksQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentExchange__categoriesQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentExchange__incomingComponentExchangeRealizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentExchange__outgoingComponentExchangeFunctionalExchangeAllocationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentExchange__outgoingComponentExchangeRealizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentExchange__realizedComponentExchangesQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentExchange__realizingComponentExchangesQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentExchange__sourcePartQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentExchange__sourcePortQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentExchange__targetPartQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ComponentExchange__targetPortQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in ComponentExchange.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
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
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ComponentExchange extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ComponentExchange instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ComponentExchange();
    }
    return INSTANCE;
  }
  
  private static ComponentExchange INSTANCE;
  
  private ComponentExchange() throws ViatraQueryException {
    querySpecifications.add(ComponentExchange__allocatedFunctionalExchangesQuerySpecification.instance());
    querySpecifications.add(ComponentExchange__incomingComponentExchangeRealizationsQuerySpecification.instance());
    querySpecifications.add(ComponentExchange__outgoingComponentExchangeRealizationsQuerySpecification.instance());
    querySpecifications.add(ComponentExchange__outgoingComponentExchangeFunctionalExchangeAllocationsQuerySpecification.instance());
    querySpecifications.add(ComponentExchange__sourcePortQuerySpecification.instance());
    querySpecifications.add(ComponentExchange__sourcePartQuerySpecification.instance());
    querySpecifications.add(ComponentExchange__targetPortQuerySpecification.instance());
    querySpecifications.add(ComponentExchange__targetPartQuerySpecification.instance());
    querySpecifications.add(ComponentExchange__categoriesQuerySpecification.instance());
    querySpecifications.add(ComponentExchange__allocatorPhysicalLinksQuerySpecification.instance());
    querySpecifications.add(ComponentExchange__realizedComponentExchangesQuerySpecification.instance());
    querySpecifications.add(ComponentExchange__realizingComponentExchangesQuerySpecification.instance());
  }
  
  public ComponentExchange__allocatedFunctionalExchangesQuerySpecification getComponentExchange__allocatedFunctionalExchanges() throws ViatraQueryException {
    return ComponentExchange__allocatedFunctionalExchangesQuerySpecification.instance();
  }
  
  public ComponentExchange__allocatedFunctionalExchangesMatcher getComponentExchange__allocatedFunctionalExchanges(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentExchange__allocatedFunctionalExchangesMatcher.on(engine);
  }
  
  public ComponentExchange__incomingComponentExchangeRealizationsQuerySpecification getComponentExchange__incomingComponentExchangeRealizations() throws ViatraQueryException {
    return ComponentExchange__incomingComponentExchangeRealizationsQuerySpecification.instance();
  }
  
  public ComponentExchange__incomingComponentExchangeRealizationsMatcher getComponentExchange__incomingComponentExchangeRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentExchange__incomingComponentExchangeRealizationsMatcher.on(engine);
  }
  
  public ComponentExchange__outgoingComponentExchangeRealizationsQuerySpecification getComponentExchange__outgoingComponentExchangeRealizations() throws ViatraQueryException {
    return ComponentExchange__outgoingComponentExchangeRealizationsQuerySpecification.instance();
  }
  
  public ComponentExchange__outgoingComponentExchangeRealizationsMatcher getComponentExchange__outgoingComponentExchangeRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentExchange__outgoingComponentExchangeRealizationsMatcher.on(engine);
  }
  
  public ComponentExchange__outgoingComponentExchangeFunctionalExchangeAllocationsQuerySpecification getComponentExchange__outgoingComponentExchangeFunctionalExchangeAllocations() throws ViatraQueryException {
    return ComponentExchange__outgoingComponentExchangeFunctionalExchangeAllocationsQuerySpecification.instance();
  }
  
  public ComponentExchange__outgoingComponentExchangeFunctionalExchangeAllocationsMatcher getComponentExchange__outgoingComponentExchangeFunctionalExchangeAllocations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentExchange__outgoingComponentExchangeFunctionalExchangeAllocationsMatcher.on(engine);
  }
  
  public ComponentExchange__sourcePortQuerySpecification getComponentExchange__sourcePort() throws ViatraQueryException {
    return ComponentExchange__sourcePortQuerySpecification.instance();
  }
  
  public ComponentExchange__sourcePortMatcher getComponentExchange__sourcePort(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentExchange__sourcePortMatcher.on(engine);
  }
  
  public ComponentExchange__sourcePartQuerySpecification getComponentExchange__sourcePart() throws ViatraQueryException {
    return ComponentExchange__sourcePartQuerySpecification.instance();
  }
  
  public ComponentExchange__sourcePartMatcher getComponentExchange__sourcePart(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentExchange__sourcePartMatcher.on(engine);
  }
  
  public ComponentExchange__targetPortQuerySpecification getComponentExchange__targetPort() throws ViatraQueryException {
    return ComponentExchange__targetPortQuerySpecification.instance();
  }
  
  public ComponentExchange__targetPortMatcher getComponentExchange__targetPort(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentExchange__targetPortMatcher.on(engine);
  }
  
  public ComponentExchange__targetPartQuerySpecification getComponentExchange__targetPart() throws ViatraQueryException {
    return ComponentExchange__targetPartQuerySpecification.instance();
  }
  
  public ComponentExchange__targetPartMatcher getComponentExchange__targetPart(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentExchange__targetPartMatcher.on(engine);
  }
  
  public ComponentExchange__categoriesQuerySpecification getComponentExchange__categories() throws ViatraQueryException {
    return ComponentExchange__categoriesQuerySpecification.instance();
  }
  
  public ComponentExchange__categoriesMatcher getComponentExchange__categories(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentExchange__categoriesMatcher.on(engine);
  }
  
  public ComponentExchange__allocatorPhysicalLinksQuerySpecification getComponentExchange__allocatorPhysicalLinks() throws ViatraQueryException {
    return ComponentExchange__allocatorPhysicalLinksQuerySpecification.instance();
  }
  
  public ComponentExchange__allocatorPhysicalLinksMatcher getComponentExchange__allocatorPhysicalLinks(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentExchange__allocatorPhysicalLinksMatcher.on(engine);
  }
  
  public ComponentExchange__realizedComponentExchangesQuerySpecification getComponentExchange__realizedComponentExchanges() throws ViatraQueryException {
    return ComponentExchange__realizedComponentExchangesQuerySpecification.instance();
  }
  
  public ComponentExchange__realizedComponentExchangesMatcher getComponentExchange__realizedComponentExchanges(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentExchange__realizedComponentExchangesMatcher.on(engine);
  }
  
  public ComponentExchange__realizingComponentExchangesQuerySpecification getComponentExchange__realizingComponentExchanges() throws ViatraQueryException {
    return ComponentExchange__realizingComponentExchangesQuerySpecification.instance();
  }
  
  public ComponentExchange__realizingComponentExchangesMatcher getComponentExchange__realizingComponentExchanges(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ComponentExchange__realizingComponentExchangesMatcher.on(engine);
  }
}
