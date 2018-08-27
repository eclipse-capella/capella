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
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChainInvolvement__involvedElementMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalChainInvolvement__involvedElementQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalChainInvolvement__previousFunctionalChainInvolvementsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in FunctionalChainInvolvement.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file FunctionalChainInvolvement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>FunctionalChainInvolvement__previousFunctionalChainInvolvements</li>
 * <li>FunctionalChainInvolvement__involvedElement</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class FunctionalChainInvolvement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static FunctionalChainInvolvement instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new FunctionalChainInvolvement();
    }
    return INSTANCE;
  }
  
  private static FunctionalChainInvolvement INSTANCE;
  
  private FunctionalChainInvolvement() throws ViatraQueryException {
    querySpecifications.add(FunctionalChainInvolvement__previousFunctionalChainInvolvementsQuerySpecification.instance());
    querySpecifications.add(FunctionalChainInvolvement__involvedElementQuerySpecification.instance());
  }
  
  public FunctionalChainInvolvement__previousFunctionalChainInvolvementsQuerySpecification getFunctionalChainInvolvement__previousFunctionalChainInvolvements() throws ViatraQueryException {
    return FunctionalChainInvolvement__previousFunctionalChainInvolvementsQuerySpecification.instance();
  }
  
  public FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatcher getFunctionalChainInvolvement__previousFunctionalChainInvolvements(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalChainInvolvement__previousFunctionalChainInvolvementsMatcher.on(engine);
  }
  
  public FunctionalChainInvolvement__involvedElementQuerySpecification getFunctionalChainInvolvement__involvedElement() throws ViatraQueryException {
    return FunctionalChainInvolvement__involvedElementQuerySpecification.instance();
  }
  
  public FunctionalChainInvolvement__involvedElementMatcher getFunctionalChainInvolvement__involvedElement(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalChainInvolvement__involvedElementMatcher.on(engine);
  }
}
