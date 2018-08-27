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
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.Type__typedElementsMatcher;
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.util.Type__typedElementsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in Type.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Type.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacore.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Type__typedElements</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class Type extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Type instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new Type();
    }
    return INSTANCE;
  }
  
  private static Type INSTANCE;
  
  private Type() throws ViatraQueryException {
    querySpecifications.add(Type__typedElementsQuerySpecification.instance());
  }
  
  public Type__typedElementsQuerySpecification getType__typedElements() throws ViatraQueryException {
    return Type__typedElementsQuerySpecification.instance();
  }
  
  public Type__typedElementsMatcher getType__typedElements(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Type__typedElementsMatcher.on(engine);
  }
}
