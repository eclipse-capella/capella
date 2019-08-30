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
import org.polarsys.capella.viatra.core.data.information.surrogate.AbstractInstance__representingInstanceRolesMatcher;
import org.polarsys.capella.viatra.core.data.information.surrogate.util.AbstractInstance__representingInstanceRolesQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in AbstractInstance.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractInstance.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractInstance__representingInstanceRoles</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractInstance extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractInstance instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new AbstractInstance();
    }
    return INSTANCE;
  }
  
  private static AbstractInstance INSTANCE;
  
  private AbstractInstance() throws ViatraQueryException {
    querySpecifications.add(AbstractInstance__representingInstanceRolesQuerySpecification.instance());
  }
  
  public AbstractInstance__representingInstanceRolesQuerySpecification getAbstractInstance__representingInstanceRoles() throws ViatraQueryException {
    return AbstractInstance__representingInstanceRolesQuerySpecification.instance();
  }
  
  public AbstractInstance__representingInstanceRolesMatcher getAbstractInstance__representingInstanceRoles(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractInstance__representingInstanceRolesMatcher.on(engine);
  }
}
