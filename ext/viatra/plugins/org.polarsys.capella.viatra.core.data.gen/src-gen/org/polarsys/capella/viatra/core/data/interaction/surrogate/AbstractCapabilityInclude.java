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
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapabilityInclude__inclusionMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.AbstractCapabilityInclude__inclusionQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in AbstractCapabilityInclude.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractCapabilityInclude.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.interaction.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractCapabilityInclude__inclusion</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractCapabilityInclude extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractCapabilityInclude instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new AbstractCapabilityInclude();
    }
    return INSTANCE;
  }
  
  private static AbstractCapabilityInclude INSTANCE;
  
  private AbstractCapabilityInclude() throws ViatraQueryException {
    querySpecifications.add(AbstractCapabilityInclude__inclusionQuerySpecification.instance());
  }
  
  public AbstractCapabilityInclude__inclusionQuerySpecification getAbstractCapabilityInclude__inclusion() throws ViatraQueryException {
    return AbstractCapabilityInclude__inclusionQuerySpecification.instance();
  }
  
  public AbstractCapabilityInclude__inclusionMatcher getAbstractCapabilityInclude__inclusion(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractCapabilityInclude__inclusionMatcher.on(engine);
  }
}
