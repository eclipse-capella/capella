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
package org.polarsys.capella.viatra.core.data.capellacore.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.Involvement__involverMatcher;
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.util.Involvement__involverQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in Involvement.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Involvement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacore.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Involvement__involver</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class Involvement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Involvement instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new Involvement();
    }
    return INSTANCE;
  }
  
  private static Involvement INSTANCE;
  
  private Involvement() throws ViatraQueryException {
    querySpecifications.add(Involvement__involverQuerySpecification.instance());
  }
  
  public Involvement__involverQuerySpecification getInvolvement__involver() throws ViatraQueryException {
    return Involvement__involverQuerySpecification.instance();
  }
  
  public Involvement__involverMatcher getInvolvement__involver(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Involvement__involverMatcher.on(engine);
  }
}
