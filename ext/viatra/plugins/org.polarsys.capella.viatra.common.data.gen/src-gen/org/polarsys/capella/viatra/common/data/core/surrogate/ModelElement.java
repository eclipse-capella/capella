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
package org.polarsys.capella.viatra.common.data.core.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.common.data.core.surrogate.ModelElement__constraintsMatcher;
import org.polarsys.capella.viatra.common.data.core.surrogate.util.ModelElement__constraintsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in ModelElement.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ModelElement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.common.data.core.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ModelElement__constraints</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ModelElement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ModelElement instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ModelElement();
    }
    return INSTANCE;
  }
  
  private static ModelElement INSTANCE;
  
  private ModelElement() throws ViatraQueryException {
    querySpecifications.add(ModelElement__constraintsQuerySpecification.instance());
  }
  
  public ModelElement__constraintsQuerySpecification getModelElement__constraints() throws ViatraQueryException {
    return ModelElement__constraintsQuerySpecification.instance();
  }
  
  public ModelElement__constraintsMatcher getModelElement__constraints(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ModelElement__constraintsMatcher.on(engine);
  }
}
