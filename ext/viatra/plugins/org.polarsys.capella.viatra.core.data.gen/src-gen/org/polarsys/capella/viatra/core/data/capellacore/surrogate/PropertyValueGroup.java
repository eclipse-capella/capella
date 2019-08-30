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
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.PropertyValueGroup__valuedElementsMatcher;
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.util.PropertyValueGroup__valuedElementsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in PropertyValueGroup.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PropertyValueGroup.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacore.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PropertyValueGroup__valuedElements</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class PropertyValueGroup extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PropertyValueGroup instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new PropertyValueGroup();
    }
    return INSTANCE;
  }
  
  private static PropertyValueGroup INSTANCE;
  
  private PropertyValueGroup() throws ViatraQueryException {
    querySpecifications.add(PropertyValueGroup__valuedElementsQuerySpecification.instance());
  }
  
  public PropertyValueGroup__valuedElementsQuerySpecification getPropertyValueGroup__valuedElements() throws ViatraQueryException {
    return PropertyValueGroup__valuedElementsQuerySpecification.instance();
  }
  
  public PropertyValueGroup__valuedElementsMatcher getPropertyValueGroup__valuedElements(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PropertyValueGroup__valuedElementsMatcher.on(engine);
  }
}
