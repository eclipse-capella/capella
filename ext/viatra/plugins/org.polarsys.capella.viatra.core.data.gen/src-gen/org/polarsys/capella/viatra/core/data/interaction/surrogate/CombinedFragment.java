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
package org.polarsys.capella.viatra.core.data.interaction.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.CombinedFragment__expressionGatesMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.CombinedFragment__expressionGatesQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in CombinedFragment.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file CombinedFragment.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.interaction.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>CombinedFragment__expressionGates</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class CombinedFragment extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static CombinedFragment instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new CombinedFragment();
    }
    return INSTANCE;
  }
  
  private static CombinedFragment INSTANCE;
  
  private CombinedFragment() throws ViatraQueryException {
    querySpecifications.add(CombinedFragment__expressionGatesQuerySpecification.instance());
  }
  
  public CombinedFragment__expressionGatesQuerySpecification getCombinedFragment__expressionGates() throws ViatraQueryException {
    return CombinedFragment__expressionGatesQuerySpecification.instance();
  }
  
  public CombinedFragment__expressionGatesMatcher getCombinedFragment__expressionGates(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CombinedFragment__expressionGatesMatcher.on(engine);
  }
}
