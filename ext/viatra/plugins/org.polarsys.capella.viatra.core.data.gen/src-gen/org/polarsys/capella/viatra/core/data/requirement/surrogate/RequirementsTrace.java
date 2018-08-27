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
package org.polarsys.capella.viatra.core.data.requirement.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.requirement.surrogate.RequirementsTrace__sourceMatcher;
import org.polarsys.capella.viatra.core.data.requirement.surrogate.RequirementsTrace__targetMatcher;
import org.polarsys.capella.viatra.core.data.requirement.surrogate.util.RequirementsTrace__sourceQuerySpecification;
import org.polarsys.capella.viatra.core.data.requirement.surrogate.util.RequirementsTrace__targetQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in RequirementsTrace.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file RequirementsTrace.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.requirement.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>RequirementsTrace__source</li>
 * <li>RequirementsTrace__target</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class RequirementsTrace extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static RequirementsTrace instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new RequirementsTrace();
    }
    return INSTANCE;
  }
  
  private static RequirementsTrace INSTANCE;
  
  private RequirementsTrace() throws ViatraQueryException {
    querySpecifications.add(RequirementsTrace__sourceQuerySpecification.instance());
    querySpecifications.add(RequirementsTrace__targetQuerySpecification.instance());
  }
  
  public RequirementsTrace__sourceQuerySpecification getRequirementsTrace__source() throws ViatraQueryException {
    return RequirementsTrace__sourceQuerySpecification.instance();
  }
  
  public RequirementsTrace__sourceMatcher getRequirementsTrace__source(final ViatraQueryEngine engine) throws ViatraQueryException {
    return RequirementsTrace__sourceMatcher.on(engine);
  }
  
  public RequirementsTrace__targetQuerySpecification getRequirementsTrace__target() throws ViatraQueryException {
    return RequirementsTrace__targetQuerySpecification.instance();
  }
  
  public RequirementsTrace__targetMatcher getRequirementsTrace__target(final ViatraQueryEngine engine) throws ViatraQueryException {
    return RequirementsTrace__targetMatcher.on(engine);
  }
}
