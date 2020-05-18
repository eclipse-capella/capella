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
package org.polarsys.capella.viatra.core.data.la.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalFunction__allocatingSystemComponents;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalFunction__childrenLogicalFunctions;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalFunction__containedLogicalFunctions;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalFunction__realizedSystemFunctions;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalFunction__realizingPhysicalFunctions;

/**
 * A pattern group formed of all public patterns defined in LogicalFunction.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file LogicalFunction.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.la.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>LogicalFunction__realizedSystemFunctions</li>
 * <li>LogicalFunction__realizingPhysicalFunctions</li>
 * <li>LogicalFunction__containedLogicalFunctions</li>
 * <li>LogicalFunction__childrenLogicalFunctions</li>
 * <li>LogicalFunction__allocatingSystemComponents</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class LogicalFunction extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static LogicalFunction instance() {
    if (INSTANCE == null) {
        INSTANCE = new LogicalFunction();
    }
    return INSTANCE;
  }
  
  private static LogicalFunction INSTANCE;
  
  private LogicalFunction() {
    querySpecifications.add(LogicalFunction__realizedSystemFunctions.instance());
    querySpecifications.add(LogicalFunction__realizingPhysicalFunctions.instance());
    querySpecifications.add(LogicalFunction__containedLogicalFunctions.instance());
    querySpecifications.add(LogicalFunction__childrenLogicalFunctions.instance());
    querySpecifications.add(LogicalFunction__allocatingSystemComponents.instance());
  }
  
  public LogicalFunction__realizedSystemFunctions getLogicalFunction__realizedSystemFunctions() {
    return LogicalFunction__realizedSystemFunctions.instance();
  }
  
  public LogicalFunction__realizedSystemFunctions.Matcher getLogicalFunction__realizedSystemFunctions(final ViatraQueryEngine engine) {
    return LogicalFunction__realizedSystemFunctions.Matcher.on(engine);
  }
  
  public LogicalFunction__realizingPhysicalFunctions getLogicalFunction__realizingPhysicalFunctions() {
    return LogicalFunction__realizingPhysicalFunctions.instance();
  }
  
  public LogicalFunction__realizingPhysicalFunctions.Matcher getLogicalFunction__realizingPhysicalFunctions(final ViatraQueryEngine engine) {
    return LogicalFunction__realizingPhysicalFunctions.Matcher.on(engine);
  }
  
  public LogicalFunction__containedLogicalFunctions getLogicalFunction__containedLogicalFunctions() {
    return LogicalFunction__containedLogicalFunctions.instance();
  }
  
  public LogicalFunction__containedLogicalFunctions.Matcher getLogicalFunction__containedLogicalFunctions(final ViatraQueryEngine engine) {
    return LogicalFunction__containedLogicalFunctions.Matcher.on(engine);
  }
  
  public LogicalFunction__childrenLogicalFunctions getLogicalFunction__childrenLogicalFunctions() {
    return LogicalFunction__childrenLogicalFunctions.instance();
  }
  
  public LogicalFunction__childrenLogicalFunctions.Matcher getLogicalFunction__childrenLogicalFunctions(final ViatraQueryEngine engine) {
    return LogicalFunction__childrenLogicalFunctions.Matcher.on(engine);
  }
  
  public LogicalFunction__allocatingSystemComponents getLogicalFunction__allocatingSystemComponents() {
    return LogicalFunction__allocatingSystemComponents.instance();
  }
  
  public LogicalFunction__allocatingSystemComponents.Matcher getLogicalFunction__allocatingSystemComponents(final ViatraQueryEngine engine) {
    return LogicalFunction__allocatingSystemComponents.Matcher.on(engine);
  }
}
