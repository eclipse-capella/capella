/**
 * 
 *   Copyright (c) 2006, 2019 THALES DMS FRANCE.
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
package org.polarsys.capella.viatra.core.data.fa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
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
 * A pattern group formed of all public patterns defined in FunctionalChain.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
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
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class FunctionalChain extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static FunctionalChain instance() {
    if (INSTANCE == null) {
        INSTANCE = new FunctionalChain();
    }
    return INSTANCE;
  }
  
  private static FunctionalChain INSTANCE;
  
  private FunctionalChain() {
    querySpecifications.add(FunctionalChain__involvedFunctionalChainInvolvements.instance());
    querySpecifications.add(FunctionalChain__involvedFunctions.instance());
    querySpecifications.add(FunctionalChain__involvedFunctionalExchanges.instance());
    querySpecifications.add(FunctionalChain__involvedElements.instance());
    querySpecifications.add(FunctionalChain__enactedFunctions.instance());
    querySpecifications.add(FunctionalChain__enactedFunctionalBlocks.instance());
    querySpecifications.add(FunctionalChain__firstFunctionalChainInvolvements.instance());
    querySpecifications.add(FunctionalChain__involvingCapabilities.instance());
    querySpecifications.add(FunctionalChain__involvingCapabilityRealizations.instance());
    querySpecifications.add(FunctionalChain__realizedFunctionalChains.instance());
    querySpecifications.add(FunctionalChain__realizingFunctionalChains.instance());
  }
  
  public FunctionalChain__involvedFunctionalChainInvolvements getFunctionalChain__involvedFunctionalChainInvolvements() {
    return FunctionalChain__involvedFunctionalChainInvolvements.instance();
  }
  
  public FunctionalChain__involvedFunctionalChainInvolvements.Matcher getFunctionalChain__involvedFunctionalChainInvolvements(final ViatraQueryEngine engine) {
    return FunctionalChain__involvedFunctionalChainInvolvements.Matcher.on(engine);
  }
  
  public FunctionalChain__involvedFunctions getFunctionalChain__involvedFunctions() {
    return FunctionalChain__involvedFunctions.instance();
  }
  
  public FunctionalChain__involvedFunctions.Matcher getFunctionalChain__involvedFunctions(final ViatraQueryEngine engine) {
    return FunctionalChain__involvedFunctions.Matcher.on(engine);
  }
  
  public FunctionalChain__involvedFunctionalExchanges getFunctionalChain__involvedFunctionalExchanges() {
    return FunctionalChain__involvedFunctionalExchanges.instance();
  }
  
  public FunctionalChain__involvedFunctionalExchanges.Matcher getFunctionalChain__involvedFunctionalExchanges(final ViatraQueryEngine engine) {
    return FunctionalChain__involvedFunctionalExchanges.Matcher.on(engine);
  }
  
  public FunctionalChain__involvedElements getFunctionalChain__involvedElements() {
    return FunctionalChain__involvedElements.instance();
  }
  
  public FunctionalChain__involvedElements.Matcher getFunctionalChain__involvedElements(final ViatraQueryEngine engine) {
    return FunctionalChain__involvedElements.Matcher.on(engine);
  }
  
  public FunctionalChain__enactedFunctions getFunctionalChain__enactedFunctions() {
    return FunctionalChain__enactedFunctions.instance();
  }
  
  public FunctionalChain__enactedFunctions.Matcher getFunctionalChain__enactedFunctions(final ViatraQueryEngine engine) {
    return FunctionalChain__enactedFunctions.Matcher.on(engine);
  }
  
  public FunctionalChain__enactedFunctionalBlocks getFunctionalChain__enactedFunctionalBlocks() {
    return FunctionalChain__enactedFunctionalBlocks.instance();
  }
  
  public FunctionalChain__enactedFunctionalBlocks.Matcher getFunctionalChain__enactedFunctionalBlocks(final ViatraQueryEngine engine) {
    return FunctionalChain__enactedFunctionalBlocks.Matcher.on(engine);
  }
  
  public FunctionalChain__firstFunctionalChainInvolvements getFunctionalChain__firstFunctionalChainInvolvements() {
    return FunctionalChain__firstFunctionalChainInvolvements.instance();
  }
  
  public FunctionalChain__firstFunctionalChainInvolvements.Matcher getFunctionalChain__firstFunctionalChainInvolvements(final ViatraQueryEngine engine) {
    return FunctionalChain__firstFunctionalChainInvolvements.Matcher.on(engine);
  }
  
  public FunctionalChain__involvingCapabilities getFunctionalChain__involvingCapabilities() {
    return FunctionalChain__involvingCapabilities.instance();
  }
  
  public FunctionalChain__involvingCapabilities.Matcher getFunctionalChain__involvingCapabilities(final ViatraQueryEngine engine) {
    return FunctionalChain__involvingCapabilities.Matcher.on(engine);
  }
  
  public FunctionalChain__involvingCapabilityRealizations getFunctionalChain__involvingCapabilityRealizations() {
    return FunctionalChain__involvingCapabilityRealizations.instance();
  }
  
  public FunctionalChain__involvingCapabilityRealizations.Matcher getFunctionalChain__involvingCapabilityRealizations(final ViatraQueryEngine engine) {
    return FunctionalChain__involvingCapabilityRealizations.Matcher.on(engine);
  }
  
  public FunctionalChain__realizedFunctionalChains getFunctionalChain__realizedFunctionalChains() {
    return FunctionalChain__realizedFunctionalChains.instance();
  }
  
  public FunctionalChain__realizedFunctionalChains.Matcher getFunctionalChain__realizedFunctionalChains(final ViatraQueryEngine engine) {
    return FunctionalChain__realizedFunctionalChains.Matcher.on(engine);
  }
  
  public FunctionalChain__realizingFunctionalChains getFunctionalChain__realizingFunctionalChains() {
    return FunctionalChain__realizingFunctionalChains.instance();
  }
  
  public FunctionalChain__realizingFunctionalChains.Matcher getFunctionalChain__realizingFunctionalChains(final ViatraQueryEngine engine) {
    return FunctionalChain__realizingFunctionalChains.Matcher.on(engine);
  }
}
