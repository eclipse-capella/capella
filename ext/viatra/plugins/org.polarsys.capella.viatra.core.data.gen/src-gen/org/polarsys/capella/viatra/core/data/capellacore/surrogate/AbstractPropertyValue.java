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
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.AbstractPropertyValue__valuedElementsMatcher;
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.util.AbstractPropertyValue__valuedElementsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in AbstractPropertyValue.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractPropertyValue.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacore.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractPropertyValue__valuedElements</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractPropertyValue extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractPropertyValue instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new AbstractPropertyValue();
    }
    return INSTANCE;
  }
  
  private static AbstractPropertyValue INSTANCE;
  
  private AbstractPropertyValue() throws ViatraQueryException {
    querySpecifications.add(AbstractPropertyValue__valuedElementsQuerySpecification.instance());
  }
  
  public AbstractPropertyValue__valuedElementsQuerySpecification getAbstractPropertyValue__valuedElements() throws ViatraQueryException {
    return AbstractPropertyValue__valuedElementsQuerySpecification.instance();
  }
  
  public AbstractPropertyValue__valuedElementsMatcher getAbstractPropertyValue__valuedElements(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractPropertyValue__valuedElementsMatcher.on(engine);
  }
}
