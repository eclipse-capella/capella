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
package org.polarsys.capella.viatra.core.data.ctx.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in SystemFunction.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file SystemFunction.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.ctx.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>SystemFunction__realizedOperationalActivities</li>
 * <li>SystemFunction__realizingLogicalFunctions</li>
 * <li>SystemFunction__containedSystemFunctions</li>
 * <li>SystemFunction__childrenSystemFunctions</li>
 * <li>SystemFunction__allocatingSystemComponents</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class SystemFunction extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static SystemFunction instance() {
    if (INSTANCE == null) {
        INSTANCE = new SystemFunction();
    }
    return INSTANCE;
  }
  
  private static SystemFunction INSTANCE;
  
  private SystemFunction() {
    querySpecifications.add(SystemFunction__realizedOperationalActivities.instance());
    querySpecifications.add(SystemFunction__realizingLogicalFunctions.instance());
    querySpecifications.add(SystemFunction__containedSystemFunctions.instance());
    querySpecifications.add(SystemFunction__childrenSystemFunctions.instance());
    querySpecifications.add(SystemFunction__allocatingSystemComponents.instance());
  }
  
  public SystemFunction__realizedOperationalActivities getSystemFunction__realizedOperationalActivities() {
    return SystemFunction__realizedOperationalActivities.instance();
  }
  
  public SystemFunction__realizedOperationalActivities.Matcher getSystemFunction__realizedOperationalActivities(final ViatraQueryEngine engine) {
    return SystemFunction__realizedOperationalActivities.Matcher.on(engine);
  }
  
  public SystemFunction__realizingLogicalFunctions getSystemFunction__realizingLogicalFunctions() {
    return SystemFunction__realizingLogicalFunctions.instance();
  }
  
  public SystemFunction__realizingLogicalFunctions.Matcher getSystemFunction__realizingLogicalFunctions(final ViatraQueryEngine engine) {
    return SystemFunction__realizingLogicalFunctions.Matcher.on(engine);
  }
  
  public SystemFunction__containedSystemFunctions getSystemFunction__containedSystemFunctions() {
    return SystemFunction__containedSystemFunctions.instance();
  }
  
  public SystemFunction__containedSystemFunctions.Matcher getSystemFunction__containedSystemFunctions(final ViatraQueryEngine engine) {
    return SystemFunction__containedSystemFunctions.Matcher.on(engine);
  }
  
  public SystemFunction__childrenSystemFunctions getSystemFunction__childrenSystemFunctions() {
    return SystemFunction__childrenSystemFunctions.instance();
  }
  
  public SystemFunction__childrenSystemFunctions.Matcher getSystemFunction__childrenSystemFunctions(final ViatraQueryEngine engine) {
    return SystemFunction__childrenSystemFunctions.Matcher.on(engine);
  }
  
  public SystemFunction__allocatingSystemComponents getSystemFunction__allocatingSystemComponents() {
    return SystemFunction__allocatingSystemComponents.instance();
  }
  
  public SystemFunction__allocatingSystemComponents.Matcher getSystemFunction__allocatingSystemComponents(final ViatraQueryEngine engine) {
    return SystemFunction__allocatingSystemComponents.Matcher.on(engine);
  }
}
