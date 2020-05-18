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
package org.polarsys.capella.viatra.core.data.capellacommon.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.CapabilityRealizationInvolvedElement__capabilityRealizationInvolvements;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.CapabilityRealizationInvolvedElement__involvingCapabilityRealizations;

/**
 * A pattern group formed of all public patterns defined in CapabilityRealizationInvolvedElement.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file CapabilityRealizationInvolvedElement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacommon.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>CapabilityRealizationInvolvedElement__involvingCapabilityRealizations</li>
 * <li>CapabilityRealizationInvolvedElement__capabilityRealizationInvolvements</li>
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
    querySpecifications.add(CapabilityRealizationInvolvedElement__involvingCapabilityRealizations.instance());
    querySpecifications.add(CapabilityRealizationInvolvedElement__capabilityRealizationInvolvements.instance());
  }
  
  public CapabilityRealizationInvolvedElement__involvingCapabilityRealizations getCapabilityRealizationInvolvedElement__involvingCapabilityRealizations() {
    return CapabilityRealizationInvolvedElement__involvingCapabilityRealizations.instance();
  }
  
  public CapabilityRealizationInvolvedElement__involvingCapabilityRealizations.Matcher getCapabilityRealizationInvolvedElement__involvingCapabilityRealizations(final ViatraQueryEngine engine) {
    return CapabilityRealizationInvolvedElement__involvingCapabilityRealizations.Matcher.on(engine);
  }
  
  public CapabilityRealizationInvolvedElement__capabilityRealizationInvolvements getCapabilityRealizationInvolvedElement__capabilityRealizationInvolvements() {
    return CapabilityRealizationInvolvedElement__capabilityRealizationInvolvements.instance();
  }
  
  public CapabilityRealizationInvolvedElement__capabilityRealizationInvolvements.Matcher getCapabilityRealizationInvolvedElement__capabilityRealizationInvolvements(final ViatraQueryEngine engine) {
    return CapabilityRealizationInvolvedElement__capabilityRealizationInvolvements.Matcher.on(engine);
  }
}
