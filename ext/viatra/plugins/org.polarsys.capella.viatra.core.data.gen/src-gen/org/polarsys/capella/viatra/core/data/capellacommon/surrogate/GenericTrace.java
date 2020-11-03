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
package org.polarsys.capella.viatra.core.data.capellacommon.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.GenericTrace__source;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.GenericTrace__target;

/**
 * A pattern group formed of all public patterns defined in GenericTrace.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file GenericTrace.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacommon.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>GenericTrace__source</li>
 * <li>GenericTrace__target</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class GenericTrace extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static GenericTrace instance() {
    if (INSTANCE == null) {
        INSTANCE = new GenericTrace();
    }
    return INSTANCE;
  }
  
  private static GenericTrace INSTANCE;
  
  private GenericTrace() {
    querySpecifications.add(GenericTrace__source.instance());
    querySpecifications.add(GenericTrace__target.instance());
  }
  
  public GenericTrace__source getGenericTrace__source() {
    return GenericTrace__source.instance();
  }
  
  public GenericTrace__source.Matcher getGenericTrace__source(final ViatraQueryEngine engine) {
    return GenericTrace__source.Matcher.on(engine);
  }
  
  public GenericTrace__target getGenericTrace__target() {
    return GenericTrace__target.instance();
  }
  
  public GenericTrace__target.Matcher getGenericTrace__target(final ViatraQueryEngine engine) {
    return GenericTrace__target.Matcher.on(engine);
  }
}
