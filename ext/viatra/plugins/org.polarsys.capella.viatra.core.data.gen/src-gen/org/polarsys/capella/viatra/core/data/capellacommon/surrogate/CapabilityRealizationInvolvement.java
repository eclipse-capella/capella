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
package org.polarsys.capella.viatra.core.data.capellacommon.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement;

/**
 * A pattern group formed of all public patterns defined in CapabilityRealizationInvolvement.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file CapabilityRealizationInvolvement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacommon.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class CapabilityRealizationInvolvement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static CapabilityRealizationInvolvement instance() {
    if (INSTANCE == null) {
        INSTANCE = new CapabilityRealizationInvolvement();
    }
    return INSTANCE;
  }
  
  private static CapabilityRealizationInvolvement INSTANCE;
  
  private CapabilityRealizationInvolvement() {
    querySpecifications.add(CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.instance());
  }
  
  public CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement getCapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement() {
    return CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.instance();
  }
  
  public CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Matcher getCapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement(final ViatraQueryEngine engine) {
    return CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement.Matcher.on(engine);
  }
}
