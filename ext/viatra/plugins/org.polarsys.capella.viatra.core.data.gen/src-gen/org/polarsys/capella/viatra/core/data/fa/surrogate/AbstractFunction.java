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
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__allocationBlocksMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__componentFunctionalAllocationsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__inFunctionRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__involvingCapabilitiesMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__involvingCapabilityRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__involvingFunctionalChainsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__linkedFunctionSpecificationMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__linkedStateMachineMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__outFunctionRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.AbstractFunction__subFunctionsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.AbstractFunction__allocationBlocksQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.AbstractFunction__componentFunctionalAllocationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.AbstractFunction__inFunctionRealizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.AbstractFunction__involvingCapabilitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.AbstractFunction__involvingCapabilityRealizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.AbstractFunction__involvingFunctionalChainsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.AbstractFunction__linkedFunctionSpecificationQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.AbstractFunction__linkedStateMachineQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.AbstractFunction__outFunctionRealizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.AbstractFunction__subFunctionsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in AbstractFunction.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractFunction.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractFunction__subFunctions</li>
 * <li>AbstractFunction__outFunctionRealizations</li>
 * <li>AbstractFunction__inFunctionRealizations</li>
 * <li>AbstractFunction__componentFunctionalAllocations</li>
 * <li>AbstractFunction__allocationBlocks</li>
 * <li>AbstractFunction__involvingCapabilities</li>
 * <li>AbstractFunction__involvingCapabilityRealizations</li>
 * <li>AbstractFunction__involvingFunctionalChains</li>
 * <li>AbstractFunction__linkedStateMachine</li>
 * <li>AbstractFunction__linkedFunctionSpecification</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractFunction extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractFunction instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new AbstractFunction();
    }
    return INSTANCE;
  }
  
  private static AbstractFunction INSTANCE;
  
  private AbstractFunction() throws ViatraQueryException {
    querySpecifications.add(AbstractFunction__subFunctionsQuerySpecification.instance());
    querySpecifications.add(AbstractFunction__outFunctionRealizationsQuerySpecification.instance());
    querySpecifications.add(AbstractFunction__inFunctionRealizationsQuerySpecification.instance());
    querySpecifications.add(AbstractFunction__componentFunctionalAllocationsQuerySpecification.instance());
    querySpecifications.add(AbstractFunction__allocationBlocksQuerySpecification.instance());
    querySpecifications.add(AbstractFunction__involvingCapabilitiesQuerySpecification.instance());
    querySpecifications.add(AbstractFunction__involvingCapabilityRealizationsQuerySpecification.instance());
    querySpecifications.add(AbstractFunction__involvingFunctionalChainsQuerySpecification.instance());
    querySpecifications.add(AbstractFunction__linkedStateMachineQuerySpecification.instance());
    querySpecifications.add(AbstractFunction__linkedFunctionSpecificationQuerySpecification.instance());
  }
  
  public AbstractFunction__subFunctionsQuerySpecification getAbstractFunction__subFunctions() throws ViatraQueryException {
    return AbstractFunction__subFunctionsQuerySpecification.instance();
  }
  
  public AbstractFunction__subFunctionsMatcher getAbstractFunction__subFunctions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractFunction__subFunctionsMatcher.on(engine);
  }
  
  public AbstractFunction__outFunctionRealizationsQuerySpecification getAbstractFunction__outFunctionRealizations() throws ViatraQueryException {
    return AbstractFunction__outFunctionRealizationsQuerySpecification.instance();
  }
  
  public AbstractFunction__outFunctionRealizationsMatcher getAbstractFunction__outFunctionRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractFunction__outFunctionRealizationsMatcher.on(engine);
  }
  
  public AbstractFunction__inFunctionRealizationsQuerySpecification getAbstractFunction__inFunctionRealizations() throws ViatraQueryException {
    return AbstractFunction__inFunctionRealizationsQuerySpecification.instance();
  }
  
  public AbstractFunction__inFunctionRealizationsMatcher getAbstractFunction__inFunctionRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractFunction__inFunctionRealizationsMatcher.on(engine);
  }
  
  public AbstractFunction__componentFunctionalAllocationsQuerySpecification getAbstractFunction__componentFunctionalAllocations() throws ViatraQueryException {
    return AbstractFunction__componentFunctionalAllocationsQuerySpecification.instance();
  }
  
  public AbstractFunction__componentFunctionalAllocationsMatcher getAbstractFunction__componentFunctionalAllocations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractFunction__componentFunctionalAllocationsMatcher.on(engine);
  }
  
  public AbstractFunction__allocationBlocksQuerySpecification getAbstractFunction__allocationBlocks() throws ViatraQueryException {
    return AbstractFunction__allocationBlocksQuerySpecification.instance();
  }
  
  public AbstractFunction__allocationBlocksMatcher getAbstractFunction__allocationBlocks(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractFunction__allocationBlocksMatcher.on(engine);
  }
  
  public AbstractFunction__involvingCapabilitiesQuerySpecification getAbstractFunction__involvingCapabilities() throws ViatraQueryException {
    return AbstractFunction__involvingCapabilitiesQuerySpecification.instance();
  }
  
  public AbstractFunction__involvingCapabilitiesMatcher getAbstractFunction__involvingCapabilities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractFunction__involvingCapabilitiesMatcher.on(engine);
  }
  
  public AbstractFunction__involvingCapabilityRealizationsQuerySpecification getAbstractFunction__involvingCapabilityRealizations() throws ViatraQueryException {
    return AbstractFunction__involvingCapabilityRealizationsQuerySpecification.instance();
  }
  
  public AbstractFunction__involvingCapabilityRealizationsMatcher getAbstractFunction__involvingCapabilityRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractFunction__involvingCapabilityRealizationsMatcher.on(engine);
  }
  
  public AbstractFunction__involvingFunctionalChainsQuerySpecification getAbstractFunction__involvingFunctionalChains() throws ViatraQueryException {
    return AbstractFunction__involvingFunctionalChainsQuerySpecification.instance();
  }
  
  public AbstractFunction__involvingFunctionalChainsMatcher getAbstractFunction__involvingFunctionalChains(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractFunction__involvingFunctionalChainsMatcher.on(engine);
  }
  
  public AbstractFunction__linkedStateMachineQuerySpecification getAbstractFunction__linkedStateMachine() throws ViatraQueryException {
    return AbstractFunction__linkedStateMachineQuerySpecification.instance();
  }
  
  public AbstractFunction__linkedStateMachineMatcher getAbstractFunction__linkedStateMachine(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractFunction__linkedStateMachineMatcher.on(engine);
  }
  
  public AbstractFunction__linkedFunctionSpecificationQuerySpecification getAbstractFunction__linkedFunctionSpecification() throws ViatraQueryException {
    return AbstractFunction__linkedFunctionSpecificationQuerySpecification.instance();
  }
  
  public AbstractFunction__linkedFunctionSpecificationMatcher getAbstractFunction__linkedFunctionSpecification(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractFunction__linkedFunctionSpecificationMatcher.on(engine);
  }
}
