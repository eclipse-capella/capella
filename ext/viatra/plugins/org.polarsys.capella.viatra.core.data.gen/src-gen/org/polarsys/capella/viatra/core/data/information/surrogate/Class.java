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

/**
 * A pattern group formed of all public patterns defined in Class.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Class.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Class__realizedClasses</li>
 * <li>Class__realizingClasses</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class Class extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Class instance() {
    if (INSTANCE == null) {
        INSTANCE = new Class();
    }
    return INSTANCE;
  }
  
  private static Class INSTANCE;
  
  private Class() {
    querySpecifications.add(Class__realizedClasses.instance());
    querySpecifications.add(Class__realizingClasses.instance());
  }
  
  public Class__realizedClasses getClass__realizedClasses() {
    return Class__realizedClasses.instance();
  }
  
  public Class__realizedClasses.Matcher getClass__realizedClasses(final ViatraQueryEngine engine) {
    return Class__realizedClasses.Matcher.on(engine);
  }
  
  public Class__realizingClasses getClass__realizingClasses() {
    return Class__realizingClasses.instance();
  }
  
  public Class__realizingClasses.Matcher getClass__realizingClasses(final ViatraQueryEngine engine) {
    return Class__realizingClasses.Matcher.on(engine);
  }
}
