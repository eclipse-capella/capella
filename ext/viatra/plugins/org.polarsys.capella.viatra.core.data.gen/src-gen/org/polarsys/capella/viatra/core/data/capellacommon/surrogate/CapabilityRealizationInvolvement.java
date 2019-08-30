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
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElementMatcher;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.util.CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElementQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in CapabilityRealizationInvolvement.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file CapabilityRealizationInvolvement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacommon.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class CapabilityRealizationInvolvement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static CapabilityRealizationInvolvement instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new CapabilityRealizationInvolvement();
    }
    return INSTANCE;
  }
  
  private static CapabilityRealizationInvolvement INSTANCE;
  
  private CapabilityRealizationInvolvement() throws ViatraQueryException {
    querySpecifications.add(CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElementQuerySpecification.instance());
  }
  
  public CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElementQuerySpecification getCapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement() throws ViatraQueryException {
    return CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElementQuerySpecification.instance();
  }
  
  public CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElementMatcher getCapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElement(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CapabilityRealizationInvolvement__involvedCapabilityRealizationInvolvedElementMatcher.on(engine);
  }
}
