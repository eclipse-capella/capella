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
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.Classifier__containedPropertiesMatcher;
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.util.Classifier__containedPropertiesQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in Classifier.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Classifier.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacore.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Classifier__containedProperties</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class Classifier extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Classifier instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new Classifier();
    }
    return INSTANCE;
  }
  
  private static Classifier INSTANCE;
  
  private Classifier() throws ViatraQueryException {
    querySpecifications.add(Classifier__containedPropertiesQuerySpecification.instance());
  }
  
  public Classifier__containedPropertiesQuerySpecification getClassifier__containedProperties() throws ViatraQueryException {
    return Classifier__containedPropertiesQuerySpecification.instance();
  }
  
  public Classifier__containedPropertiesMatcher getClassifier__containedProperties(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Classifier__containedPropertiesMatcher.on(engine);
  }
}
