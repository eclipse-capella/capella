/**
 * 
 *   Copyright (c) 2019 THALES DMS FRANCE.
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
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChainInvolvementFunction__incomingInvolvementLinksMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChainInvolvementFunction__outgoingInvolvementLinksMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalChainInvolvementFunction__incomingInvolvementLinksQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.FunctionalChainInvolvementFunction__outgoingInvolvementLinksQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in FunctionalChainInvolvementFunction.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file FunctionalChainInvolvementFunction.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>FunctionalChainInvolvementFunction__outgoingInvolvementLinks</li>
 * <li>FunctionalChainInvolvementFunction__incomingInvolvementLinks</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class FunctionalChainInvolvementFunction extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static FunctionalChainInvolvementFunction instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new FunctionalChainInvolvementFunction();
    }
    return INSTANCE;
  }
  
  private static FunctionalChainInvolvementFunction INSTANCE;
  
  private FunctionalChainInvolvementFunction() throws ViatraQueryException {
    querySpecifications.add(FunctionalChainInvolvementFunction__outgoingInvolvementLinksQuerySpecification.instance());
    querySpecifications.add(FunctionalChainInvolvementFunction__incomingInvolvementLinksQuerySpecification.instance());
  }
  
  public FunctionalChainInvolvementFunction__outgoingInvolvementLinksQuerySpecification getFunctionalChainInvolvementFunction__outgoingInvolvementLinks() throws ViatraQueryException {
    return FunctionalChainInvolvementFunction__outgoingInvolvementLinksQuerySpecification.instance();
  }
  
  public FunctionalChainInvolvementFunction__outgoingInvolvementLinksMatcher getFunctionalChainInvolvementFunction__outgoingInvolvementLinks(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalChainInvolvementFunction__outgoingInvolvementLinksMatcher.on(engine);
  }
  
  public FunctionalChainInvolvementFunction__incomingInvolvementLinksQuerySpecification getFunctionalChainInvolvementFunction__incomingInvolvementLinks() throws ViatraQueryException {
    return FunctionalChainInvolvementFunction__incomingInvolvementLinksQuerySpecification.instance();
  }
  
  public FunctionalChainInvolvementFunction__incomingInvolvementLinksMatcher getFunctionalChainInvolvementFunction__incomingInvolvementLinks(final ViatraQueryEngine engine) throws ViatraQueryException {
    return FunctionalChainInvolvementFunction__incomingInvolvementLinksMatcher.on(engine);
  }
}
