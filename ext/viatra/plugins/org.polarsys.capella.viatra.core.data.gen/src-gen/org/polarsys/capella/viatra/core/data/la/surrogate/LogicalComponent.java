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
package org.polarsys.capella.viatra.core.data.la.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalComponent__allocatedLogicalFunctionsMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalComponent__realizedSystemsMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalComponent__realizingPhysicalComponentsMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalComponent__subLogicalComponentsMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalComponent__systemRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.LogicalComponent__allocatedLogicalFunctionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.LogicalComponent__realizedSystemsQuerySpecification;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.LogicalComponent__realizingPhysicalComponentsQuerySpecification;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.LogicalComponent__subLogicalComponentsQuerySpecification;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.LogicalComponent__systemRealizationsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in LogicalComponent.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file LogicalComponent.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.la.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>LogicalComponent__systemRealizations</li>
 * <li>LogicalComponent__subLogicalComponents</li>
 * <li>LogicalComponent__allocatedLogicalFunctions</li>
 * <li>LogicalComponent__realizingPhysicalComponents</li>
 * <li>LogicalComponent__realizedSystems</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class LogicalComponent extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static LogicalComponent instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new LogicalComponent();
    }
    return INSTANCE;
  }
  
  private static LogicalComponent INSTANCE;
  
  private LogicalComponent() throws ViatraQueryException {
    querySpecifications.add(LogicalComponent__systemRealizationsQuerySpecification.instance());
    querySpecifications.add(LogicalComponent__subLogicalComponentsQuerySpecification.instance());
    querySpecifications.add(LogicalComponent__allocatedLogicalFunctionsQuerySpecification.instance());
    querySpecifications.add(LogicalComponent__realizingPhysicalComponentsQuerySpecification.instance());
    querySpecifications.add(LogicalComponent__realizedSystemsQuerySpecification.instance());
  }
  
  public LogicalComponent__systemRealizationsQuerySpecification getLogicalComponent__systemRealizations() throws ViatraQueryException {
    return LogicalComponent__systemRealizationsQuerySpecification.instance();
  }
  
  public LogicalComponent__systemRealizationsMatcher getLogicalComponent__systemRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LogicalComponent__systemRealizationsMatcher.on(engine);
  }
  
  public LogicalComponent__subLogicalComponentsQuerySpecification getLogicalComponent__subLogicalComponents() throws ViatraQueryException {
    return LogicalComponent__subLogicalComponentsQuerySpecification.instance();
  }
  
  public LogicalComponent__subLogicalComponentsMatcher getLogicalComponent__subLogicalComponents(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LogicalComponent__subLogicalComponentsMatcher.on(engine);
  }
  
  public LogicalComponent__allocatedLogicalFunctionsQuerySpecification getLogicalComponent__allocatedLogicalFunctions() throws ViatraQueryException {
    return LogicalComponent__allocatedLogicalFunctionsQuerySpecification.instance();
  }
  
  public LogicalComponent__allocatedLogicalFunctionsMatcher getLogicalComponent__allocatedLogicalFunctions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LogicalComponent__allocatedLogicalFunctionsMatcher.on(engine);
  }
  
  public LogicalComponent__realizingPhysicalComponentsQuerySpecification getLogicalComponent__realizingPhysicalComponents() throws ViatraQueryException {
    return LogicalComponent__realizingPhysicalComponentsQuerySpecification.instance();
  }
  
  public LogicalComponent__realizingPhysicalComponentsMatcher getLogicalComponent__realizingPhysicalComponents(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LogicalComponent__realizingPhysicalComponentsMatcher.on(engine);
  }
  
  public LogicalComponent__realizedSystemsQuerySpecification getLogicalComponent__realizedSystems() throws ViatraQueryException {
    return LogicalComponent__realizedSystemsQuerySpecification.instance();
  }
  
  public LogicalComponent__realizedSystemsMatcher getLogicalComponent__realizedSystems(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LogicalComponent__realizedSystemsMatcher.on(engine);
  }
}
