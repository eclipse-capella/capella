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
package org.polarsys.capella.viatra.core.data.information.communication.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.CommunicationItem__propertiesMatcher;
import org.polarsys.capella.viatra.core.data.information.communication.surrogate.util.CommunicationItem__propertiesQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in CommunicationItem.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file CommunicationItem.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.communication.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>CommunicationItem__properties</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class CommunicationItem extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static CommunicationItem instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new CommunicationItem();
    }
    return INSTANCE;
  }
  
  private static CommunicationItem INSTANCE;
  
  private CommunicationItem() throws ViatraQueryException {
    querySpecifications.add(CommunicationItem__propertiesQuerySpecification.instance());
  }
  
  public CommunicationItem__propertiesQuerySpecification getCommunicationItem__properties() throws ViatraQueryException {
    return CommunicationItem__propertiesQuerySpecification.instance();
  }
  
  public CommunicationItem__propertiesMatcher getCommunicationItem__properties(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CommunicationItem__propertiesMatcher.on(engine);
  }
}
