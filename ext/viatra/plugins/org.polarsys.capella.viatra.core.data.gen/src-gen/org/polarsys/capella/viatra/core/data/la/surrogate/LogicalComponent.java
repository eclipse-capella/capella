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
package org.polarsys.capella.viatra.core.data.la.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalComponent__allocatedLogicalFunctions;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalComponent__realizedSystemComponents;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalComponent__realizingPhysicalComponents;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalComponent__subLogicalComponents;

/**
 * A pattern group formed of all public patterns defined in LogicalComponent.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file LogicalComponent.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.la.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>LogicalComponent__subLogicalComponents</li>
 * <li>LogicalComponent__allocatedLogicalFunctions</li>
 * <li>LogicalComponent__realizingPhysicalComponents</li>
 * <li>LogicalComponent__realizedSystemComponents</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class LogicalComponent extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static LogicalComponent instance() {
    if (INSTANCE == null) {
        INSTANCE = new LogicalComponent();
    }
    return INSTANCE;
  }
  
  private static LogicalComponent INSTANCE;
  
  private LogicalComponent() {
    querySpecifications.add(LogicalComponent__subLogicalComponents.instance());
    querySpecifications.add(LogicalComponent__allocatedLogicalFunctions.instance());
    querySpecifications.add(LogicalComponent__realizingPhysicalComponents.instance());
    querySpecifications.add(LogicalComponent__realizedSystemComponents.instance());
  }
  
  public LogicalComponent__subLogicalComponents getLogicalComponent__subLogicalComponents() {
    return LogicalComponent__subLogicalComponents.instance();
  }
  
  public LogicalComponent__subLogicalComponents.Matcher getLogicalComponent__subLogicalComponents(final ViatraQueryEngine engine) {
    return LogicalComponent__subLogicalComponents.Matcher.on(engine);
  }
  
  public LogicalComponent__allocatedLogicalFunctions getLogicalComponent__allocatedLogicalFunctions() {
    return LogicalComponent__allocatedLogicalFunctions.instance();
  }
  
  public LogicalComponent__allocatedLogicalFunctions.Matcher getLogicalComponent__allocatedLogicalFunctions(final ViatraQueryEngine engine) {
    return LogicalComponent__allocatedLogicalFunctions.Matcher.on(engine);
  }
  
  public LogicalComponent__realizingPhysicalComponents getLogicalComponent__realizingPhysicalComponents() {
    return LogicalComponent__realizingPhysicalComponents.instance();
  }
  
  public LogicalComponent__realizingPhysicalComponents.Matcher getLogicalComponent__realizingPhysicalComponents(final ViatraQueryEngine engine) {
    return LogicalComponent__realizingPhysicalComponents.Matcher.on(engine);
  }
  
  public LogicalComponent__realizedSystemComponents getLogicalComponent__realizedSystemComponents() {
    return LogicalComponent__realizedSystemComponents.instance();
  }
  
  public LogicalComponent__realizedSystemComponents.Matcher getLogicalComponent__realizedSystemComponents(final ViatraQueryEngine engine) {
    return LogicalComponent__realizedSystemComponents.Matcher.on(engine);
  }
}
