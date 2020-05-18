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
package org.polarsys.capella.viatra.core.data.requirement.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.requirement.surrogate.RequirementsTrace__source;
import org.polarsys.capella.viatra.core.data.requirement.surrogate.RequirementsTrace__target;

/**
 * A pattern group formed of all public patterns defined in RequirementsTrace.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file RequirementsTrace.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.requirement.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>RequirementsTrace__source</li>
 * <li>RequirementsTrace__target</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class RequirementsTrace extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static RequirementsTrace instance() {
    if (INSTANCE == null) {
        INSTANCE = new RequirementsTrace();
    }
    return INSTANCE;
  }
  
  private static RequirementsTrace INSTANCE;
  
  private RequirementsTrace() {
    querySpecifications.add(RequirementsTrace__source.instance());
    querySpecifications.add(RequirementsTrace__target.instance());
  }
  
  public RequirementsTrace__source getRequirementsTrace__source() {
    return RequirementsTrace__source.instance();
  }
  
  public RequirementsTrace__source.Matcher getRequirementsTrace__source(final ViatraQueryEngine engine) {
    return RequirementsTrace__source.Matcher.on(engine);
  }
  
  public RequirementsTrace__target getRequirementsTrace__target() {
    return RequirementsTrace__target.instance();
  }
  
  public RequirementsTrace__target.Matcher getRequirementsTrace__target(final ViatraQueryEngine engine) {
    return RequirementsTrace__target.Matcher.on(engine);
  }
}
