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
package org.polarsys.capella.viatra.core.data.fa.surrogate.internal;

import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.fa.surrogate.internal._PreviousInvolvementQuerySpecification;
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
 * A pattern group formed of all patterns defined in FunctionalChain.vql.
 * 
 * <p>A private group that includes private patterns as well. Only intended use case is for pattern testing.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>FunctionalChain__involvedFunctionalChainInvolvements</li>
 * <li>FunctionalChain__involvedFunctions</li>
 * <li>FunctionalChain__involvedFunctionalExchanges</li>
 * <li>FunctionalChain__involvedElements</li>
 * <li>FunctionalChain__enactedFunctions</li>
 * <li>FunctionalChain__enactedFunctionalBlocks</li>
 * <li>FunctionalChain__firstFunctionalChainInvolvements</li>
 * <li>_PreviousInvolvement</li>
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
public final class FunctionalChainAll extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static FunctionalChainAll instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new FunctionalChainAll();
    }
    return INSTANCE;
  }
  
  private static FunctionalChainAll INSTANCE;
  
  private FunctionalChainAll() throws ViatraQueryException {
    querySpecifications.add(FunctionalChain__involvedFunctionalChainInvolvementsQuerySpecification.instance());
    querySpecifications.add(FunctionalChain__involvedFunctionsQuerySpecification.instance());
    querySpecifications.add(FunctionalChain__involvedFunctionalExchangesQuerySpecification.instance());
    querySpecifications.add(FunctionalChain__involvedElementsQuerySpecification.instance());
    querySpecifications.add(FunctionalChain__enactedFunctionsQuerySpecification.instance());
    querySpecifications.add(FunctionalChain__enactedFunctionalBlocksQuerySpecification.instance());
    querySpecifications.add(FunctionalChain__firstFunctionalChainInvolvementsQuerySpecification.instance());
    querySpecifications.add(_PreviousInvolvementQuerySpecification.instance());
    querySpecifications.add(FunctionalChain__involvingCapabilitiesQuerySpecification.instance());
    querySpecifications.add(FunctionalChain__involvingCapabilityRealizationsQuerySpecification.instance());
    querySpecifications.add(FunctionalChain__realizedFunctionalChainsQuerySpecification.instance());
    querySpecifications.add(FunctionalChain__realizingFunctionalChainsQuerySpecification.instance());
  }
}
