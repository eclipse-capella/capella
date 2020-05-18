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
package org.polarsys.capella.viatra.core.data.information.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.information.surrogate.AbstractInstance__representingInstanceRoles;

/**
 * A pattern group formed of all public patterns defined in AbstractInstance.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractInstance.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractInstance__representingInstanceRoles</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractInstance extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractInstance instance() {
    if (INSTANCE == null) {
        INSTANCE = new AbstractInstance();
    }
    return INSTANCE;
  }
  
  private static AbstractInstance INSTANCE;
  
  private AbstractInstance() {
    querySpecifications.add(AbstractInstance__representingInstanceRoles.instance());
  }
  
  public AbstractInstance__representingInstanceRoles getAbstractInstance__representingInstanceRoles() {
    return AbstractInstance__representingInstanceRoles.instance();
  }
  
  public AbstractInstance__representingInstanceRoles.Matcher getAbstractInstance__representingInstanceRoles(final ViatraQueryEngine engine) {
    return AbstractInstance__representingInstanceRoles.Matcher.on(engine);
  }
}
