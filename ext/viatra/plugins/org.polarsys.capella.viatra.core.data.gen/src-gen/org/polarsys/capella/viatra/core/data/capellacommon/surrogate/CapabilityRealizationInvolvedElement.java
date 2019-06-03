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
package org.polarsys.capella.viatra.core.data.capellacommon.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvements;

/**
 * A pattern group formed of all public patterns defined in CapabilityRealizationInvolvedElement.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file CapabilityRealizationInvolvedElement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacommon.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvements</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class CapabilityRealizationInvolvedElement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static CapabilityRealizationInvolvedElement instance() {
    if (INSTANCE == null) {
        INSTANCE = new CapabilityRealizationInvolvedElement();
    }
    return INSTANCE;
  }
  
  private static CapabilityRealizationInvolvedElement INSTANCE;
  
  private CapabilityRealizationInvolvedElement() {
    querySpecifications.add(CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvements.instance());
  }
  
  public CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvements getCapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvements() {
    return CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvements.instance();
  }
  
  public CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvements.Matcher getCapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvements(final ViatraQueryEngine engine) {
    return CapabilityRealizationInvolvedElement__involvingCapabilityRealizationInvolvements.Matcher.on(engine);
  }
}
