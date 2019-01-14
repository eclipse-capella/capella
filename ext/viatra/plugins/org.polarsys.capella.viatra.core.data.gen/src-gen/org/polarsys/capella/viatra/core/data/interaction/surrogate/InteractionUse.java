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
import org.polarsys.capella.viatra.core.data.interaction.surrogate.InteractionUse__actualGatesMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.InteractionUse__actualGatesQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in InteractionUse.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file InteractionUse.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.interaction.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>InteractionUse__actualGates</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class InteractionUse extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static InteractionUse instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new InteractionUse();
    }
    return INSTANCE;
  }
  
  private static InteractionUse INSTANCE;
  
  private InteractionUse() throws ViatraQueryException {
    querySpecifications.add(InteractionUse__actualGatesQuerySpecification.instance());
  }
  
  public InteractionUse__actualGatesQuerySpecification getInteractionUse__actualGates() throws ViatraQueryException {
    return InteractionUse__actualGatesQuerySpecification.instance();
  }
  
  public InteractionUse__actualGatesMatcher getInteractionUse__actualGates(final ViatraQueryEngine engine) throws ViatraQueryException {
    return InteractionUse__actualGatesMatcher.on(engine);
  }
}
