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
package org.polarsys.capella.viatra.core.data.oa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.oa.surrogate.OperationalProcess__involvingOperationalCapabilitiesMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.OperationalProcess__involvingOperationalCapabilitiesQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in OperationalProcess.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file OperationalProcess.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.oa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>OperationalProcess__involvingOperationalCapabilities</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class OperationalProcess extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static OperationalProcess instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new OperationalProcess();
    }
    return INSTANCE;
  }
  
  private static OperationalProcess INSTANCE;
  
  private OperationalProcess() throws ViatraQueryException {
    querySpecifications.add(OperationalProcess__involvingOperationalCapabilitiesQuerySpecification.instance());
  }
  
  public OperationalProcess__involvingOperationalCapabilitiesQuerySpecification getOperationalProcess__involvingOperationalCapabilities() throws ViatraQueryException {
    return OperationalProcess__involvingOperationalCapabilitiesQuerySpecification.instance();
  }
  
  public OperationalProcess__involvingOperationalCapabilitiesMatcher getOperationalProcess__involvingOperationalCapabilities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return OperationalProcess__involvingOperationalCapabilitiesMatcher.on(engine);
  }
}
