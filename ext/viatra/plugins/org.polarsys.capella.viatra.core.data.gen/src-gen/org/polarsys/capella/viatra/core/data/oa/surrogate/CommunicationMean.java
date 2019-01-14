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
import org.polarsys.capella.viatra.core.data.oa.surrogate.CommunicationMean__sourceEntityMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.CommunicationMean__targetEntityMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.CommunicationMean__sourceEntityQuerySpecification;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.CommunicationMean__targetEntityQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in CommunicationMean.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file CommunicationMean.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.oa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>CommunicationMean__sourceEntity</li>
 * <li>CommunicationMean__targetEntity</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class CommunicationMean extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static CommunicationMean instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new CommunicationMean();
    }
    return INSTANCE;
  }
  
  private static CommunicationMean INSTANCE;
  
  private CommunicationMean() throws ViatraQueryException {
    querySpecifications.add(CommunicationMean__sourceEntityQuerySpecification.instance());
    querySpecifications.add(CommunicationMean__targetEntityQuerySpecification.instance());
  }
  
  public CommunicationMean__sourceEntityQuerySpecification getCommunicationMean__sourceEntity() throws ViatraQueryException {
    return CommunicationMean__sourceEntityQuerySpecification.instance();
  }
  
  public CommunicationMean__sourceEntityMatcher getCommunicationMean__sourceEntity(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CommunicationMean__sourceEntityMatcher.on(engine);
  }
  
  public CommunicationMean__targetEntityQuerySpecification getCommunicationMean__targetEntity() throws ViatraQueryException {
    return CommunicationMean__targetEntityQuerySpecification.instance();
  }
  
  public CommunicationMean__targetEntityMatcher getCommunicationMean__targetEntity(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CommunicationMean__targetEntityMatcher.on(engine);
  }
}
