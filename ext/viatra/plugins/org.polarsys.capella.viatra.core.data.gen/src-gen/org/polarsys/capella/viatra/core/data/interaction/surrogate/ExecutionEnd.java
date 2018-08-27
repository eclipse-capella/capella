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
package org.polarsys.capella.viatra.core.data.interaction.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.ExecutionEnd__executionMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.ExecutionEnd__executionQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in ExecutionEnd.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ExecutionEnd.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.interaction.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ExecutionEnd__execution</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ExecutionEnd extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ExecutionEnd instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ExecutionEnd();
    }
    return INSTANCE;
  }
  
  private static ExecutionEnd INSTANCE;
  
  private ExecutionEnd() throws ViatraQueryException {
    querySpecifications.add(ExecutionEnd__executionQuerySpecification.instance());
  }
  
  public ExecutionEnd__executionQuerySpecification getExecutionEnd__execution() throws ViatraQueryException {
    return ExecutionEnd__executionQuerySpecification.instance();
  }
  
  public ExecutionEnd__executionMatcher getExecutionEnd__execution(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ExecutionEnd__executionMatcher.on(engine);
  }
}
