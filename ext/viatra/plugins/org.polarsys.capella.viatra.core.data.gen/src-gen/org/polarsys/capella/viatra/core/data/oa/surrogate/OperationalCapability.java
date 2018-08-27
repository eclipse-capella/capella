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
import org.polarsys.capella.viatra.core.data.oa.surrogate.OperationalCapability__involvedEntitiesMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.OperationalCapability__realizingCapabilitiesMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.OperationalCapability__involvedEntitiesQuerySpecification;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.OperationalCapability__realizingCapabilitiesQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in OperationalCapability.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file OperationalCapability.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.oa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>OperationalCapability__realizingCapabilities</li>
 * <li>OperationalCapability__involvedEntities</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class OperationalCapability extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static OperationalCapability instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new OperationalCapability();
    }
    return INSTANCE;
  }
  
  private static OperationalCapability INSTANCE;
  
  private OperationalCapability() throws ViatraQueryException {
    querySpecifications.add(OperationalCapability__realizingCapabilitiesQuerySpecification.instance());
    querySpecifications.add(OperationalCapability__involvedEntitiesQuerySpecification.instance());
  }
  
  public OperationalCapability__realizingCapabilitiesQuerySpecification getOperationalCapability__realizingCapabilities() throws ViatraQueryException {
    return OperationalCapability__realizingCapabilitiesQuerySpecification.instance();
  }
  
  public OperationalCapability__realizingCapabilitiesMatcher getOperationalCapability__realizingCapabilities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return OperationalCapability__realizingCapabilitiesMatcher.on(engine);
  }
  
  public OperationalCapability__involvedEntitiesQuerySpecification getOperationalCapability__involvedEntities() throws ViatraQueryException {
    return OperationalCapability__involvedEntitiesQuerySpecification.instance();
  }
  
  public OperationalCapability__involvedEntitiesMatcher getOperationalCapability__involvedEntities(final ViatraQueryEngine engine) throws ViatraQueryException {
    return OperationalCapability__involvedEntitiesMatcher.on(engine);
  }
}
