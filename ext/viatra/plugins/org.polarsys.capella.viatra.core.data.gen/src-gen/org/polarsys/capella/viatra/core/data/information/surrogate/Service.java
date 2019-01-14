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
package org.polarsys.capella.viatra.core.data.information.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.information.surrogate.Service__messagesMatcher;
import org.polarsys.capella.viatra.core.data.information.surrogate.util.Service__messagesQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in Service.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Service.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Service__messages</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class Service extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Service instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new Service();
    }
    return INSTANCE;
  }
  
  private static Service INSTANCE;
  
  private Service() throws ViatraQueryException {
    querySpecifications.add(Service__messagesQuerySpecification.instance());
  }
  
  public Service__messagesQuerySpecification getService__messages() throws ViatraQueryException {
    return Service__messagesQuerySpecification.instance();
  }
  
  public Service__messagesMatcher getService__messages(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Service__messagesMatcher.on(engine);
  }
}
