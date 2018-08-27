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
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapabilityExtend__extensionMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.AbstractCapabilityExtend__extensionQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in AbstractCapabilityExtend.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractCapabilityExtend.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.interaction.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractCapabilityExtend__extension</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractCapabilityExtend extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractCapabilityExtend instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new AbstractCapabilityExtend();
    }
    return INSTANCE;
  }
  
  private static AbstractCapabilityExtend INSTANCE;
  
  private AbstractCapabilityExtend() throws ViatraQueryException {
    querySpecifications.add(AbstractCapabilityExtend__extensionQuerySpecification.instance());
  }
  
  public AbstractCapabilityExtend__extensionQuerySpecification getAbstractCapabilityExtend__extension() throws ViatraQueryException {
    return AbstractCapabilityExtend__extensionQuerySpecification.instance();
  }
  
  public AbstractCapabilityExtend__extensionMatcher getAbstractCapabilityExtend__extension(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractCapabilityExtend__extensionMatcher.on(engine);
  }
}
