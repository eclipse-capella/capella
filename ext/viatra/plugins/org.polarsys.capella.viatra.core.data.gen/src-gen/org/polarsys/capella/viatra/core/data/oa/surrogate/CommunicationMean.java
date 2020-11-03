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
package org.polarsys.capella.viatra.core.data.oa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.oa.surrogate.CommunicationMean__sourceEntity;
import org.polarsys.capella.viatra.core.data.oa.surrogate.CommunicationMean__targetEntity;

/**
 * A pattern group formed of all public patterns defined in CommunicationMean.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file CommunicationMean.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.oa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>CommunicationMean__sourceEntity</li>
 * <li>CommunicationMean__targetEntity</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class CommunicationMean extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static CommunicationMean instance() {
    if (INSTANCE == null) {
        INSTANCE = new CommunicationMean();
    }
    return INSTANCE;
  }
  
  private static CommunicationMean INSTANCE;
  
  private CommunicationMean() {
    querySpecifications.add(CommunicationMean__sourceEntity.instance());
    querySpecifications.add(CommunicationMean__targetEntity.instance());
  }
  
  public CommunicationMean__sourceEntity getCommunicationMean__sourceEntity() {
    return CommunicationMean__sourceEntity.instance();
  }
  
  public CommunicationMean__sourceEntity.Matcher getCommunicationMean__sourceEntity(final ViatraQueryEngine engine) {
    return CommunicationMean__sourceEntity.Matcher.on(engine);
  }
  
  public CommunicationMean__targetEntity getCommunicationMean__targetEntity() {
    return CommunicationMean__targetEntity.instance();
  }
  
  public CommunicationMean__targetEntity.Matcher getCommunicationMean__targetEntity(final ViatraQueryEngine engine) {
    return CommunicationMean__targetEntity.Matcher.on(engine);
  }
}
