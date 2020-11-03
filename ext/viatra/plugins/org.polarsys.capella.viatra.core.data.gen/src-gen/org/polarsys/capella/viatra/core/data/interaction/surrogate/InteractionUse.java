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
package org.polarsys.capella.viatra.core.data.interaction.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.InteractionUse__actualGates;

/**
 * A pattern group formed of all public patterns defined in InteractionUse.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file InteractionUse.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.interaction.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>InteractionUse__actualGates</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class InteractionUse extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static InteractionUse instance() {
    if (INSTANCE == null) {
        INSTANCE = new InteractionUse();
    }
    return INSTANCE;
  }
  
  private static InteractionUse INSTANCE;
  
  private InteractionUse() {
    querySpecifications.add(InteractionUse__actualGates.instance());
  }
  
  public InteractionUse__actualGates getInteractionUse__actualGates() {
    return InteractionUse__actualGates.instance();
  }
  
  public InteractionUse__actualGates.Matcher getInteractionUse__actualGates(final ViatraQueryEngine engine) {
    return InteractionUse__actualGates.Matcher.on(engine);
  }
}
