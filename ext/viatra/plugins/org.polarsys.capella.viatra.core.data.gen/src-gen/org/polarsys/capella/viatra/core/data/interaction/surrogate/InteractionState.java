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
import org.polarsys.capella.viatra.core.data.interaction.surrogate.InteractionState__covered;

/**
 * A pattern group formed of all public patterns defined in InteractionState.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file InteractionState.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.interaction.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>InteractionState__covered</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class InteractionState extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static InteractionState instance() {
    if (INSTANCE == null) {
        INSTANCE = new InteractionState();
    }
    return INSTANCE;
  }
  
  private static InteractionState INSTANCE;
  
  private InteractionState() {
    querySpecifications.add(InteractionState__covered.instance());
  }
  
  public InteractionState__covered getInteractionState__covered() {
    return InteractionState__covered.instance();
  }
  
  public InteractionState__covered.Matcher getInteractionState__covered(final ViatraQueryEngine engine) {
    return InteractionState__covered.Matcher.on(engine);
  }
}
