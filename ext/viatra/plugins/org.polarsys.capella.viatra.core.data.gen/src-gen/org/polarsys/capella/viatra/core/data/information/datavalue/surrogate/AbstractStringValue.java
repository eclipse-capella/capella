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
package org.polarsys.capella.viatra.core.data.information.datavalue.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.information.datavalue.surrogate.AbstractStringValue__stringTypeMatcher;
import org.polarsys.capella.viatra.core.data.information.datavalue.surrogate.util.AbstractStringValue__stringTypeQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in AbstractStringValue.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractStringValue.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.datavalue.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractStringValue__stringType</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractStringValue extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractStringValue instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new AbstractStringValue();
    }
    return INSTANCE;
  }
  
  private static AbstractStringValue INSTANCE;
  
  private AbstractStringValue() throws ViatraQueryException {
    querySpecifications.add(AbstractStringValue__stringTypeQuerySpecification.instance());
  }
  
  public AbstractStringValue__stringTypeQuerySpecification getAbstractStringValue__stringType() throws ViatraQueryException {
    return AbstractStringValue__stringTypeQuerySpecification.instance();
  }
  
  public AbstractStringValue__stringTypeMatcher getAbstractStringValue__stringType(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractStringValue__stringTypeMatcher.on(engine);
  }
}
