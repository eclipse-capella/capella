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
package org.polarsys.capella.viatra.core.data.capellacommon.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.GenericTrace__sourceMatcher;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.GenericTrace__targetMatcher;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.util.GenericTrace__sourceQuerySpecification;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.util.GenericTrace__targetQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in GenericTrace.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file GenericTrace.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacommon.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>GenericTrace__source</li>
 * <li>GenericTrace__target</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class GenericTrace extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static GenericTrace instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new GenericTrace();
    }
    return INSTANCE;
  }
  
  private static GenericTrace INSTANCE;
  
  private GenericTrace() throws ViatraQueryException {
    querySpecifications.add(GenericTrace__sourceQuerySpecification.instance());
    querySpecifications.add(GenericTrace__targetQuerySpecification.instance());
  }
  
  public GenericTrace__sourceQuerySpecification getGenericTrace__source() throws ViatraQueryException {
    return GenericTrace__sourceQuerySpecification.instance();
  }
  
  public GenericTrace__sourceMatcher getGenericTrace__source(final ViatraQueryEngine engine) throws ViatraQueryException {
    return GenericTrace__sourceMatcher.on(engine);
  }
  
  public GenericTrace__targetQuerySpecification getGenericTrace__target() throws ViatraQueryException {
    return GenericTrace__targetQuerySpecification.instance();
  }
  
  public GenericTrace__targetMatcher getGenericTrace__target(final ViatraQueryEngine engine) throws ViatraQueryException {
    return GenericTrace__targetMatcher.on(engine);
  }
}
