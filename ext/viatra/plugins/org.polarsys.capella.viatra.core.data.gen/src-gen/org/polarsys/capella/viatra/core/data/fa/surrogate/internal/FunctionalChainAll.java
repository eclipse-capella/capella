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
package org.polarsys.capella.viatra.core.data.fa.surrogate.internal;

import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChain__enactedFunctionalBlocks;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChain__enactedFunctions;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChain__firstFunctionalChainInvolvements;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChain__involvedElements;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChain__involvedFunctionalChainInvolvements;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChain__involvedFunctionalExchanges;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChain__involvedFunctions;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChain__involvingCapabilities;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChain__involvingCapabilityRealizations;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChain__realizedFunctionalChains;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChain__realizingFunctionalChains;

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
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class FunctionalChainAll extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static FunctionalChainAll instance() {
    if (INSTANCE == null) {
        INSTANCE = new FunctionalChainAll();
    }
    return INSTANCE;
  }
  
  private static FunctionalChainAll INSTANCE;
  
  private FunctionalChainAll() {
    querySpecifications.add(FunctionalChain__involvedFunctionalChainInvolvements.instance());
    querySpecifications.add(FunctionalChain__involvedFunctions.instance());
    querySpecifications.add(FunctionalChain__involvedFunctionalExchanges.instance());
    querySpecifications.add(FunctionalChain__involvedElements.instance());
    querySpecifications.add(FunctionalChain__enactedFunctions.instance());
    querySpecifications.add(FunctionalChain__enactedFunctionalBlocks.instance());
    querySpecifications.add(FunctionalChain__firstFunctionalChainInvolvements.instance());
    querySpecifications.add(_PreviousInvolvement.instance());
    querySpecifications.add(FunctionalChain__involvingCapabilities.instance());
    querySpecifications.add(FunctionalChain__involvingCapabilityRealizations.instance());
    querySpecifications.add(FunctionalChain__realizedFunctionalChains.instance());
    querySpecifications.add(FunctionalChain__realizingFunctionalChains.instance());
  }
}
