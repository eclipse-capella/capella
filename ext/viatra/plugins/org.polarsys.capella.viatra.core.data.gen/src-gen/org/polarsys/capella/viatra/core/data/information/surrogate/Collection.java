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
package org.polarsys.capella.viatra.core.data.information.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.information.surrogate.Collection__containedOperations;

/**
 * A pattern group formed of all public patterns defined in Collection.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Collection.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Collection__containedOperations</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class Collection extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Collection instance() {
    if (INSTANCE == null) {
        INSTANCE = new Collection();
    }
    return INSTANCE;
  }
  
  private static Collection INSTANCE;
  
  private Collection() {
    querySpecifications.add(Collection__containedOperations.instance());
  }
  
  public Collection__containedOperations getCollection__containedOperations() {
    return Collection__containedOperations.instance();
  }
  
  public Collection__containedOperations.Matcher getCollection__containedOperations(final ViatraQueryEngine engine) {
    return Collection__containedOperations.Matcher.on(engine);
  }
}
