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
import org.polarsys.capella.viatra.core.data.interaction.surrogate.Execution__coveredMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.Execution__coveredQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in Execution.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Execution.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.interaction.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Execution__covered</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class Execution extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Execution instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new Execution();
    }
    return INSTANCE;
  }
  
  private static Execution INSTANCE;
  
  private Execution() throws ViatraQueryException {
    querySpecifications.add(Execution__coveredQuerySpecification.instance());
  }
  
  public Execution__coveredQuerySpecification getExecution__covered() throws ViatraQueryException {
    return Execution__coveredQuerySpecification.instance();
  }
  
  public Execution__coveredMatcher getExecution__covered(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Execution__coveredMatcher.on(engine);
  }
}
