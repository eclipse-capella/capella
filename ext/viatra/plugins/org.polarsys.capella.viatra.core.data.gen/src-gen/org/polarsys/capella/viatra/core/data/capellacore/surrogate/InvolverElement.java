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
package org.polarsys.capella.viatra.core.data.capellacore.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.InvolverElement__involvedInvolvements;

/**
 * A pattern group formed of all public patterns defined in InvolverElement.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file InvolverElement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacore.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>InvolverElement__involvedInvolvements</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class InvolverElement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static InvolverElement instance() {
    if (INSTANCE == null) {
        INSTANCE = new InvolverElement();
    }
    return INSTANCE;
  }
  
  private static InvolverElement INSTANCE;
  
  private InvolverElement() {
    querySpecifications.add(InvolverElement__involvedInvolvements.instance());
  }
  
  public InvolverElement__involvedInvolvements getInvolverElement__involvedInvolvements() {
    return InvolverElement__involvedInvolvements.instance();
  }
  
  public InvolverElement__involvedInvolvements.Matcher getInvolverElement__involvedInvolvements(final ViatraQueryEngine engine) {
    return InvolverElement__involvedInvolvements.Matcher.on(engine);
  }
}
