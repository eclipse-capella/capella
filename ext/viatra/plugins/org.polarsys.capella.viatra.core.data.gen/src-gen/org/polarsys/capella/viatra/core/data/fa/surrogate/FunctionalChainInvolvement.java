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
package org.polarsys.capella.viatra.core.data.fa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in FunctionalChainInvolvement.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file FunctionalChainInvolvement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>FunctionalChainInvolvement__previousFunctionalChainInvolvements</li>
 * <li>FunctionalChainInvolvement_nextFunctionalChainInvolvements</li>
 * <li>FunctionalChainInvolvement__involvedElement</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class FunctionalChainInvolvement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static FunctionalChainInvolvement instance() {
    if (INSTANCE == null) {
        INSTANCE = new FunctionalChainInvolvement();
    }
    return INSTANCE;
  }
  
  private static FunctionalChainInvolvement INSTANCE;
  
  private FunctionalChainInvolvement() {
    querySpecifications.add(FunctionalChainInvolvement__previousFunctionalChainInvolvements.instance());
    querySpecifications.add(FunctionalChainInvolvement_nextFunctionalChainInvolvements.instance());
    querySpecifications.add(FunctionalChainInvolvement__involvedElement.instance());
  }
  
  public FunctionalChainInvolvement__previousFunctionalChainInvolvements getFunctionalChainInvolvement__previousFunctionalChainInvolvements() {
    return FunctionalChainInvolvement__previousFunctionalChainInvolvements.instance();
  }
  
  public FunctionalChainInvolvement__previousFunctionalChainInvolvements.Matcher getFunctionalChainInvolvement__previousFunctionalChainInvolvements(final ViatraQueryEngine engine) {
    return FunctionalChainInvolvement__previousFunctionalChainInvolvements.Matcher.on(engine);
  }
  
  public FunctionalChainInvolvement_nextFunctionalChainInvolvements getFunctionalChainInvolvement_nextFunctionalChainInvolvements() {
    return FunctionalChainInvolvement_nextFunctionalChainInvolvements.instance();
  }
  
  public FunctionalChainInvolvement_nextFunctionalChainInvolvements.Matcher getFunctionalChainInvolvement_nextFunctionalChainInvolvements(final ViatraQueryEngine engine) {
    return FunctionalChainInvolvement_nextFunctionalChainInvolvements.Matcher.on(engine);
  }
  
  public FunctionalChainInvolvement__involvedElement getFunctionalChainInvolvement__involvedElement() {
    return FunctionalChainInvolvement__involvedElement.instance();
  }
  
  public FunctionalChainInvolvement__involvedElement.Matcher getFunctionalChainInvolvement__involvedElement(final ViatraQueryEngine engine) {
    return FunctionalChainInvolvement__involvedElement.Matcher.on(engine);
  }
}
