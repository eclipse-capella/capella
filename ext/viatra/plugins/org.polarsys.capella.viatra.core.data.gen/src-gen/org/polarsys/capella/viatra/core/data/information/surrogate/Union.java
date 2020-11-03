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
import org.polarsys.capella.viatra.core.data.information.surrogate.Union__containedUnionProperties;

/**
 * A pattern group formed of all public patterns defined in Union.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Union.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Union__containedUnionProperties</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class Union extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Union instance() {
    if (INSTANCE == null) {
        INSTANCE = new Union();
    }
    return INSTANCE;
  }
  
  private static Union INSTANCE;
  
  private Union() {
    querySpecifications.add(Union__containedUnionProperties.instance());
  }
  
  public Union__containedUnionProperties getUnion__containedUnionProperties() {
    return Union__containedUnionProperties.instance();
  }
  
  public Union__containedUnionProperties.Matcher getUnion__containedUnionProperties(final ViatraQueryEngine engine) {
    return Union__containedUnionProperties.Matcher.on(engine);
  }
}
