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
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalFunction__allocatorLogicalActorsMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalFunction__allocatorLogicalComponentsMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalFunction__childrenLogicalFunctionsMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalFunction__containedLogicalFunctionsMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalFunction__realizedSystemFunctionsMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.LogicalFunction__realizingPhysicalFunctionsMatcher;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.LogicalFunction__allocatorLogicalActorsQuerySpecification;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.LogicalFunction__allocatorLogicalComponentsQuerySpecification;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.LogicalFunction__childrenLogicalFunctionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.LogicalFunction__containedLogicalFunctionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.LogicalFunction__realizedSystemFunctionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.la.surrogate.util.LogicalFunction__realizingPhysicalFunctionsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in LogicalFunction.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file LogicalFunction.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.la.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>LogicalFunction__allocatorLogicalActors</li>
 * <li>LogicalFunction__allocatorLogicalComponents</li>
 * <li>LogicalFunction__realizedSystemFunctions</li>
 * <li>LogicalFunction__realizingPhysicalFunctions</li>
 * <li>LogicalFunction__containedLogicalFunctions</li>
 * <li>LogicalFunction__childrenLogicalFunctions</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class LogicalFunction extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static LogicalFunction instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new LogicalFunction();
    }
    return INSTANCE;
  }
  
  private static LogicalFunction INSTANCE;
  
  private LogicalFunction() throws ViatraQueryException {
    querySpecifications.add(LogicalFunction__allocatorLogicalActorsQuerySpecification.instance());
    querySpecifications.add(LogicalFunction__allocatorLogicalComponentsQuerySpecification.instance());
    querySpecifications.add(LogicalFunction__realizedSystemFunctionsQuerySpecification.instance());
    querySpecifications.add(LogicalFunction__realizingPhysicalFunctionsQuerySpecification.instance());
    querySpecifications.add(LogicalFunction__containedLogicalFunctionsQuerySpecification.instance());
    querySpecifications.add(LogicalFunction__childrenLogicalFunctionsQuerySpecification.instance());
  }
  
  public LogicalFunction__allocatorLogicalActorsQuerySpecification getLogicalFunction__allocatorLogicalActors() throws ViatraQueryException {
    return LogicalFunction__allocatorLogicalActorsQuerySpecification.instance();
  }
  
  public LogicalFunction__allocatorLogicalActorsMatcher getLogicalFunction__allocatorLogicalActors(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LogicalFunction__allocatorLogicalActorsMatcher.on(engine);
  }
  
  public LogicalFunction__allocatorLogicalComponentsQuerySpecification getLogicalFunction__allocatorLogicalComponents() throws ViatraQueryException {
    return LogicalFunction__allocatorLogicalComponentsQuerySpecification.instance();
  }
  
  public LogicalFunction__allocatorLogicalComponentsMatcher getLogicalFunction__allocatorLogicalComponents(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LogicalFunction__allocatorLogicalComponentsMatcher.on(engine);
  }
  
  public LogicalFunction__realizedSystemFunctionsQuerySpecification getLogicalFunction__realizedSystemFunctions() throws ViatraQueryException {
    return LogicalFunction__realizedSystemFunctionsQuerySpecification.instance();
  }
  
  public LogicalFunction__realizedSystemFunctionsMatcher getLogicalFunction__realizedSystemFunctions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LogicalFunction__realizedSystemFunctionsMatcher.on(engine);
  }
  
  public LogicalFunction__realizingPhysicalFunctionsQuerySpecification getLogicalFunction__realizingPhysicalFunctions() throws ViatraQueryException {
    return LogicalFunction__realizingPhysicalFunctionsQuerySpecification.instance();
  }
  
  public LogicalFunction__realizingPhysicalFunctionsMatcher getLogicalFunction__realizingPhysicalFunctions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LogicalFunction__realizingPhysicalFunctionsMatcher.on(engine);
  }
  
  public LogicalFunction__containedLogicalFunctionsQuerySpecification getLogicalFunction__containedLogicalFunctions() throws ViatraQueryException {
    return LogicalFunction__containedLogicalFunctionsQuerySpecification.instance();
  }
  
  public LogicalFunction__containedLogicalFunctionsMatcher getLogicalFunction__containedLogicalFunctions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LogicalFunction__containedLogicalFunctionsMatcher.on(engine);
  }
  
  public LogicalFunction__childrenLogicalFunctionsQuerySpecification getLogicalFunction__childrenLogicalFunctions() throws ViatraQueryException {
    return LogicalFunction__childrenLogicalFunctionsQuerySpecification.instance();
  }
  
  public LogicalFunction__childrenLogicalFunctionsMatcher getLogicalFunction__childrenLogicalFunctions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return LogicalFunction__childrenLogicalFunctionsMatcher.on(engine);
  }
}
