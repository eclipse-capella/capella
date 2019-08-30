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
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapabilityGeneralization__subMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.AbstractCapabilityGeneralization__subQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in AbstractCapabilityGeneralization.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractCapabilityGeneralization.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.interaction.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractCapabilityGeneralization__sub</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractCapabilityGeneralization extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractCapabilityGeneralization instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new AbstractCapabilityGeneralization();
    }
    return INSTANCE;
  }
  
  private static AbstractCapabilityGeneralization INSTANCE;
  
  private AbstractCapabilityGeneralization() throws ViatraQueryException {
    querySpecifications.add(AbstractCapabilityGeneralization__subQuerySpecification.instance());
  }
  
  public AbstractCapabilityGeneralization__subQuerySpecification getAbstractCapabilityGeneralization__sub() throws ViatraQueryException {
    return AbstractCapabilityGeneralization__subQuerySpecification.instance();
  }
  
  public AbstractCapabilityGeneralization__subMatcher getAbstractCapabilityGeneralization__sub(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractCapabilityGeneralization__subMatcher.on(engine);
  }
}
