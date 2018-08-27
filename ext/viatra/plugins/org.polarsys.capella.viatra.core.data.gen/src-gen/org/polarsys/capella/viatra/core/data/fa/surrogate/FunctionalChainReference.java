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
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChainReference__referencedFunctionalChainMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalChainReference__referencedFunctionalChainQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in FunctionalChainReference.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file FunctionalChainReference.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>FunctionalChainReference__referencedFunctionalChain</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class FunctionalChainReference extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static FunctionalChainReference instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new FunctionalChainReference();
    }
    return INSTANCE;
  }
  
  private static FunctionalChainReference INSTANCE;
  
  private FunctionalChainReference() throws ViatraQueryException {
    querySpecifications.add(FunctionalChainReference__referencedFunctionalChainQuerySpecification.instance());
  }
  
  public FunctionalChainReference__referencedFunctionalChainQuerySpecification getFunctionalChainReference__referencedFunctionalChain() throws ViatraQueryException {
    return FunctionalChainReference__referencedFunctionalChainQuerySpecification.instance();
  }
  
  public FunctionalChainReference__referencedFunctionalChainMatcher getFunctionalChainReference__referencedFunctionalChain(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalChainReference__referencedFunctionalChainMatcher.on(engine);
  }
}
