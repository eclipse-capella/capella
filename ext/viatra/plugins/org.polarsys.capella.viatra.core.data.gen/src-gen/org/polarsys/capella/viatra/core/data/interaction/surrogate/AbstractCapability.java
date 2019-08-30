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
package org.polarsys.capella.viatra.core.data.interaction.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__extendedAbstractCapabilitiesMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__extendingAbstractCapabilitiesMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__extendingMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__includedAbstractCapabilitiesMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__includingAbstractCapabilitiesMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__includingMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__incomingCapabilityAllocationMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__involvedAbstractFunctionsMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__involvedFunctionalChainsMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__outgoingCapabilityAllocationMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__subGeneralizationsMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__subMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__superMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.AbstractCapability__extendedAbstractCapabilitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.AbstractCapability__extendingAbstractCapabilitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.AbstractCapability__extendingQuerySpecification;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.AbstractCapability__includedAbstractCapabilitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.AbstractCapability__includingAbstractCapabilitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.AbstractCapability__includingQuerySpecification;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.AbstractCapability__incomingCapabilityAllocationQuerySpecification;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.AbstractCapability__involvedAbstractFunctionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.AbstractCapability__involvedFunctionalChainsQuerySpecification;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.AbstractCapability__outgoingCapabilityAllocationQuerySpecification;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.AbstractCapability__subGeneralizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.AbstractCapability__subQuerySpecification;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.AbstractCapability__superQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in AbstractCapability.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractCapability.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.interaction.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractCapability__incomingCapabilityAllocation</li>
 * <li>AbstractCapability__outgoingCapabilityAllocation</li>
 * <li>AbstractCapability__extending</li>
 * <li>AbstractCapability__subGeneralizations</li>
 * <li>AbstractCapability__including</li>
 * <li>AbstractCapability__super</li>
 * <li>AbstractCapability__sub</li>
 * <li>AbstractCapability__includedAbstractCapabilities</li>
 * <li>AbstractCapability__includingAbstractCapabilities</li>
 * <li>AbstractCapability__extendedAbstractCapabilities</li>
 * <li>AbstractCapability__extendingAbstractCapabilities</li>
 * <li>AbstractCapability__involvedAbstractFunctions</li>
 * <li>AbstractCapability__involvedFunctionalChains</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractCapability extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractCapability instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new AbstractCapability();
    }
    return INSTANCE;
  }
  
  private static AbstractCapability INSTANCE;
  
  private AbstractCapability() throws ViatraQueryException {
    querySpecifications.add(AbstractCapability__incomingCapabilityAllocationQuerySpecification.instance());
    querySpecifications.add(AbstractCapability__outgoingCapabilityAllocationQuerySpecification.instance());
    querySpecifications.add(AbstractCapability__extendingQuerySpecification.instance());
    querySpecifications.add(AbstractCapability__subGeneralizationsQuerySpecification.instance());
    querySpecifications.add(AbstractCapability__includingQuerySpecification.instance());
    querySpecifications.add(AbstractCapability__superQuerySpecification.instance());
    querySpecifications.add(AbstractCapability__subQuerySpecification.instance());
    querySpecifications.add(AbstractCapability__includedAbstractCapabilitiesQuerySpecification.instance());
    querySpecifications.add(AbstractCapability__includingAbstractCapabilitiesQuerySpecification.instance());
    querySpecifications.add(AbstractCapability__extendedAbstractCapabilitiesQuerySpecification.instance());
    querySpecifications.add(AbstractCapability__extendingAbstractCapabilitiesQuerySpecification.instance());
    querySpecifications.add(AbstractCapability__involvedAbstractFunctionsQuerySpecification.instance());
    querySpecifications.add(AbstractCapability__involvedFunctionalChainsQuerySpecification.instance());
  }
  
  public AbstractCapability__incomingCapabilityAllocationQuerySpecification getAbstractCapability__incomingCapabilityAllocation() throws ViatraQueryException {
    return AbstractCapability__incomingCapabilityAllocationQuerySpecification.instance();
  }
  
  public AbstractCapability__incomingCapabilityAllocationMatcher getAbstractCapability__incomingCapabilityAllocation(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractCapability__incomingCapabilityAllocationMatcher.on(engine);
  }
  
  public AbstractCapability__outgoingCapabilityAllocationQuerySpecification getAbstractCapability__outgoingCapabilityAllocation() throws ViatraQueryException {
    return AbstractCapability__outgoingCapabilityAllocationQuerySpecification.instance();
  }
  
  public AbstractCapability__outgoingCapabilityAllocationMatcher getAbstractCapability__outgoingCapabilityAllocation(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractCapability__outgoingCapabilityAllocationMatcher.on(engine);
  }
  
  public AbstractCapability__extendingQuerySpecification getAbstractCapability__extending() throws ViatraQueryException {
    return AbstractCapability__extendingQuerySpecification.instance();
  }
  
  public AbstractCapability__extendingMatcher getAbstractCapability__extending(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractCapability__extendingMatcher.on(engine);
  }
  
  public AbstractCapability__subGeneralizationsQuerySpecification getAbstractCapability__subGeneralizations() throws ViatraQueryException {
    return AbstractCapability__subGeneralizationsQuerySpecification.instance();
  }
  
  public AbstractCapability__subGeneralizationsMatcher getAbstractCapability__subGeneralizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractCapability__subGeneralizationsMatcher.on(engine);
  }
  
  public AbstractCapability__includingQuerySpecification getAbstractCapability__including() throws ViatraQueryException {
    return AbstractCapability__includingQuerySpecification.instance();
  }
  
  public AbstractCapability__includingMatcher getAbstractCapability__including(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractCapability__includingMatcher.on(engine);
  }
  
  public AbstractCapability__superQuerySpecification getAbstractCapability__super() throws ViatraQueryException {
    return AbstractCapability__superQuerySpecification.instance();
  }
  
  public AbstractCapability__superMatcher getAbstractCapability__super(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractCapability__superMatcher.on(engine);
  }
  
  public AbstractCapability__subQuerySpecification getAbstractCapability__sub() throws ViatraQueryException {
    return AbstractCapability__subQuerySpecification.instance();
  }
  
  public AbstractCapability__subMatcher getAbstractCapability__sub(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractCapability__subMatcher.on(engine);
  }
  
  public AbstractCapability__includedAbstractCapabilitiesQuerySpecification getAbstractCapability__includedAbstractCapabilities() throws ViatraQueryException {
    return AbstractCapability__includedAbstractCapabilitiesQuerySpecification.instance();
  }
  
  public AbstractCapability__includedAbstractCapabilitiesMatcher getAbstractCapability__includedAbstractCapabilities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractCapability__includedAbstractCapabilitiesMatcher.on(engine);
  }
  
  public AbstractCapability__includingAbstractCapabilitiesQuerySpecification getAbstractCapability__includingAbstractCapabilities() throws ViatraQueryException {
    return AbstractCapability__includingAbstractCapabilitiesQuerySpecification.instance();
  }
  
  public AbstractCapability__includingAbstractCapabilitiesMatcher getAbstractCapability__includingAbstractCapabilities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractCapability__includingAbstractCapabilitiesMatcher.on(engine);
  }
  
  public AbstractCapability__extendedAbstractCapabilitiesQuerySpecification getAbstractCapability__extendedAbstractCapabilities() throws ViatraQueryException {
    return AbstractCapability__extendedAbstractCapabilitiesQuerySpecification.instance();
  }
  
  public AbstractCapability__extendedAbstractCapabilitiesMatcher getAbstractCapability__extendedAbstractCapabilities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractCapability__extendedAbstractCapabilitiesMatcher.on(engine);
  }
  
  public AbstractCapability__extendingAbstractCapabilitiesQuerySpecification getAbstractCapability__extendingAbstractCapabilities() throws ViatraQueryException {
    return AbstractCapability__extendingAbstractCapabilitiesQuerySpecification.instance();
  }
  
  public AbstractCapability__extendingAbstractCapabilitiesMatcher getAbstractCapability__extendingAbstractCapabilities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractCapability__extendingAbstractCapabilitiesMatcher.on(engine);
  }
  
  public AbstractCapability__involvedAbstractFunctionsQuerySpecification getAbstractCapability__involvedAbstractFunctions() throws ViatraQueryException {
    return AbstractCapability__involvedAbstractFunctionsQuerySpecification.instance();
  }
  
  public AbstractCapability__involvedAbstractFunctionsMatcher getAbstractCapability__involvedAbstractFunctions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractCapability__involvedAbstractFunctionsMatcher.on(engine);
  }
  
  public AbstractCapability__involvedFunctionalChainsQuerySpecification getAbstractCapability__involvedFunctionalChains() throws ViatraQueryException {
    return AbstractCapability__involvedFunctionalChainsQuerySpecification.instance();
  }
  
  public AbstractCapability__involvedFunctionalChainsMatcher getAbstractCapability__involvedFunctionalChains(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractCapability__involvedFunctionalChainsMatcher.on(engine);
  }
}
