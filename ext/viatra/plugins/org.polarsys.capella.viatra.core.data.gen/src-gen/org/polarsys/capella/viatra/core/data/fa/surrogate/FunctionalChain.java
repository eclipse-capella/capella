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
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChain__enactedFunctionalBlocksMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChain__enactedFunctionsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChain__firstFunctionalChainInvolvementsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChain__involvedElementsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChain__involvedFunctionalChainInvolvementsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChain__involvedFunctionalExchangesMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChain__involvedFunctionsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChain__involvingCapabilitiesMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChain__involvingCapabilityRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChain__realizedFunctionalChainsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChain__realizingFunctionalChainsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalChain__enactedFunctionalBlocksQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalChain__enactedFunctionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalChain__firstFunctionalChainInvolvementsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalChain__involvedElementsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalChain__involvedFunctionalChainInvolvementsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalChain__involvedFunctionalExchangesQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalChain__involvedFunctionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalChain__involvingCapabilitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalChain__involvingCapabilityRealizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalChain__realizedFunctionalChainsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalChain__realizingFunctionalChainsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in FunctionalChain.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file FunctionalChain.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>FunctionalChain__involvedFunctionalChainInvolvements</li>
 * <li>FunctionalChain__involvedFunctions</li>
 * <li>FunctionalChain__involvedFunctionalExchanges</li>
 * <li>FunctionalChain__involvedElements</li>
 * <li>FunctionalChain__enactedFunctions</li>
 * <li>FunctionalChain__enactedFunctionalBlocks</li>
 * <li>FunctionalChain__firstFunctionalChainInvolvements</li>
 * <li>FunctionalChain__involvingCapabilities</li>
 * <li>FunctionalChain__involvingCapabilityRealizations</li>
 * <li>FunctionalChain__realizedFunctionalChains</li>
 * <li>FunctionalChain__realizingFunctionalChains</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class FunctionalChain extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static FunctionalChain instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new FunctionalChain();
    }
    return INSTANCE;
  }
  
  private static FunctionalChain INSTANCE;
  
  private FunctionalChain() throws ViatraQueryException {
    querySpecifications.add(FunctionalChain__involvedFunctionalChainInvolvementsQuerySpecification.instance());
    querySpecifications.add(FunctionalChain__involvedFunctionsQuerySpecification.instance());
    querySpecifications.add(FunctionalChain__involvedFunctionalExchangesQuerySpecification.instance());
    querySpecifications.add(FunctionalChain__involvedElementsQuerySpecification.instance());
    querySpecifications.add(FunctionalChain__enactedFunctionsQuerySpecification.instance());
    querySpecifications.add(FunctionalChain__enactedFunctionalBlocksQuerySpecification.instance());
    querySpecifications.add(FunctionalChain__firstFunctionalChainInvolvementsQuerySpecification.instance());
    querySpecifications.add(FunctionalChain__involvingCapabilitiesQuerySpecification.instance());
    querySpecifications.add(FunctionalChain__involvingCapabilityRealizationsQuerySpecification.instance());
    querySpecifications.add(FunctionalChain__realizedFunctionalChainsQuerySpecification.instance());
    querySpecifications.add(FunctionalChain__realizingFunctionalChainsQuerySpecification.instance());
  }
  
  public FunctionalChain__involvedFunctionalChainInvolvementsQuerySpecification getFunctionalChain__involvedFunctionalChainInvolvements() throws ViatraQueryException {
    return FunctionalChain__involvedFunctionalChainInvolvementsQuerySpecification.instance();
  }
  
  public FunctionalChain__involvedFunctionalChainInvolvementsMatcher getFunctionalChain__involvedFunctionalChainInvolvements(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalChain__involvedFunctionalChainInvolvementsMatcher.on(engine);
  }
  
  public FunctionalChain__involvedFunctionsQuerySpecification getFunctionalChain__involvedFunctions() throws ViatraQueryException {
    return FunctionalChain__involvedFunctionsQuerySpecification.instance();
  }
  
  public FunctionalChain__involvedFunctionsMatcher getFunctionalChain__involvedFunctions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalChain__involvedFunctionsMatcher.on(engine);
  }
  
  public FunctionalChain__involvedFunctionalExchangesQuerySpecification getFunctionalChain__involvedFunctionalExchanges() throws ViatraQueryException {
    return FunctionalChain__involvedFunctionalExchangesQuerySpecification.instance();
  }
  
  public FunctionalChain__involvedFunctionalExchangesMatcher getFunctionalChain__involvedFunctionalExchanges(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalChain__involvedFunctionalExchangesMatcher.on(engine);
  }
  
  public FunctionalChain__involvedElementsQuerySpecification getFunctionalChain__involvedElements() throws ViatraQueryException {
    return FunctionalChain__involvedElementsQuerySpecification.instance();
  }
  
  public FunctionalChain__involvedElementsMatcher getFunctionalChain__involvedElements(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalChain__involvedElementsMatcher.on(engine);
  }
  
  public FunctionalChain__enactedFunctionsQuerySpecification getFunctionalChain__enactedFunctions() throws ViatraQueryException {
    return FunctionalChain__enactedFunctionsQuerySpecification.instance();
  }
  
  public FunctionalChain__enactedFunctionsMatcher getFunctionalChain__enactedFunctions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalChain__enactedFunctionsMatcher.on(engine);
  }
  
  public FunctionalChain__enactedFunctionalBlocksQuerySpecification getFunctionalChain__enactedFunctionalBlocks() throws ViatraQueryException {
    return FunctionalChain__enactedFunctionalBlocksQuerySpecification.instance();
  }
  
  public FunctionalChain__enactedFunctionalBlocksMatcher getFunctionalChain__enactedFunctionalBlocks(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalChain__enactedFunctionalBlocksMatcher.on(engine);
  }
  
  public FunctionalChain__firstFunctionalChainInvolvementsQuerySpecification getFunctionalChain__firstFunctionalChainInvolvements() throws ViatraQueryException {
    return FunctionalChain__firstFunctionalChainInvolvementsQuerySpecification.instance();
  }
  
  public FunctionalChain__firstFunctionalChainInvolvementsMatcher getFunctionalChain__firstFunctionalChainInvolvements(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalChain__firstFunctionalChainInvolvementsMatcher.on(engine);
  }
  
  public FunctionalChain__involvingCapabilitiesQuerySpecification getFunctionalChain__involvingCapabilities() throws ViatraQueryException {
    return FunctionalChain__involvingCapabilitiesQuerySpecification.instance();
  }
  
  public FunctionalChain__involvingCapabilitiesMatcher getFunctionalChain__involvingCapabilities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalChain__involvingCapabilitiesMatcher.on(engine);
  }
  
  public FunctionalChain__involvingCapabilityRealizationsQuerySpecification getFunctionalChain__involvingCapabilityRealizations() throws ViatraQueryException {
    return FunctionalChain__involvingCapabilityRealizationsQuerySpecification.instance();
  }
  
  public FunctionalChain__involvingCapabilityRealizationsMatcher getFunctionalChain__involvingCapabilityRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalChain__involvingCapabilityRealizationsMatcher.on(engine);
  }
  
  public FunctionalChain__realizedFunctionalChainsQuerySpecification getFunctionalChain__realizedFunctionalChains() throws ViatraQueryException {
    return FunctionalChain__realizedFunctionalChainsQuerySpecification.instance();
  }
  
  public FunctionalChain__realizedFunctionalChainsMatcher getFunctionalChain__realizedFunctionalChains(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalChain__realizedFunctionalChainsMatcher.on(engine);
  }
  
  public FunctionalChain__realizingFunctionalChainsQuerySpecification getFunctionalChain__realizingFunctionalChains() throws ViatraQueryException {
    return FunctionalChain__realizingFunctionalChainsQuerySpecification.instance();
  }
  
  public FunctionalChain__realizingFunctionalChainsMatcher getFunctionalChain__realizingFunctionalChains(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalChain__realizingFunctionalChainsMatcher.on(engine);
  }
}
