/**
 * 
 *   Copyright (c) 2019, 2020 THALES DMS FRANCE.
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
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChainInvolvementFunction__incomingInvolvementLinks;
import org.polarsys.capella.viatra.core.data.fa.surrogate.FunctionalChainInvolvementFunction__outgoingInvolvementLinks;

/**
 * A pattern group formed of all public patterns defined in FunctionalChainInvolvementFunction.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file FunctionalChainInvolvementFunction.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>FunctionalChainInvolvementFunction__outgoingInvolvementLinks</li>
 * <li>FunctionalChainInvolvementFunction__incomingInvolvementLinks</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class FunctionalChainInvolvementFunction extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static FunctionalChainInvolvementFunction instance() {
    if (INSTANCE == null) {
        INSTANCE = new FunctionalChainInvolvementFunction();
    }
    return INSTANCE;
  }
  
  private static FunctionalChainInvolvementFunction INSTANCE;
  
  private FunctionalChainInvolvementFunction() {
    querySpecifications.add(FunctionalChainInvolvementFunction__outgoingInvolvementLinks.instance());
    querySpecifications.add(FunctionalChainInvolvementFunction__incomingInvolvementLinks.instance());
  }
  
  public FunctionalChainInvolvementFunction__outgoingInvolvementLinks getFunctionalChainInvolvementFunction__outgoingInvolvementLinks() {
    return FunctionalChainInvolvementFunction__outgoingInvolvementLinks.instance();
  }
  
  public FunctionalChainInvolvementFunction__outgoingInvolvementLinks.Matcher getFunctionalChainInvolvementFunction__outgoingInvolvementLinks(final ViatraQueryEngine engine) {
    return FunctionalChainInvolvementFunction__outgoingInvolvementLinks.Matcher.on(engine);
  }
  
  public FunctionalChainInvolvementFunction__incomingInvolvementLinks getFunctionalChainInvolvementFunction__incomingInvolvementLinks() {
    return FunctionalChainInvolvementFunction__incomingInvolvementLinks.instance();
  }
  
  public FunctionalChainInvolvementFunction__incomingInvolvementLinks.Matcher getFunctionalChainInvolvementFunction__incomingInvolvementLinks(final ViatraQueryEngine engine) {
    return FunctionalChainInvolvementFunction__incomingInvolvementLinks.Matcher.on(engine);
  }
}
