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
import org.polarsys.capella.viatra.core.data.information.surrogate.Collection__containedOperationsMatcher;
import org.polarsys.capella.viatra.core.data.information.surrogate.util.Collection__containedOperationsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in Collection.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Collection.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Collection__containedOperations</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class Collection extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Collection instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new Collection();
    }
    return INSTANCE;
  }
  
  private static Collection INSTANCE;
  
  private Collection() throws ViatraQueryException {
    querySpecifications.add(Collection__containedOperationsQuerySpecification.instance());
  }
  
  public Collection__containedOperationsQuerySpecification getCollection__containedOperations() throws ViatraQueryException {
    return Collection__containedOperationsQuerySpecification.instance();
  }
  
  public Collection__containedOperationsMatcher getCollection__containedOperations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Collection__containedOperationsMatcher.on(engine);
  }
}
