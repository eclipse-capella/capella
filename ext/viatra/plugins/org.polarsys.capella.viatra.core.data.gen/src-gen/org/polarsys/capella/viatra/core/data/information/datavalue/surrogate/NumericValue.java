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
import org.polarsys.capella.viatra.core.data.information.datavalue.surrogate.NumericValue__numericTypeMatcher;
import org.polarsys.capella.viatra.core.data.information.datavalue.surrogate.util.NumericValue__numericTypeQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in NumericValue.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file NumericValue.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.datavalue.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>NumericValue__numericType</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class NumericValue extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static NumericValue instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new NumericValue();
    }
    return INSTANCE;
  }
  
  private static NumericValue INSTANCE;
  
  private NumericValue() throws ViatraQueryException {
    querySpecifications.add(NumericValue__numericTypeQuerySpecification.instance());
  }
  
  public NumericValue__numericTypeQuerySpecification getNumericValue__numericType() throws ViatraQueryException {
    return NumericValue__numericTypeQuerySpecification.instance();
  }
  
  public NumericValue__numericTypeMatcher getNumericValue__numericType(final ViatraQueryEngine engine) throws ViatraQueryException {
    return NumericValue__numericTypeMatcher.on(engine);
  }
}
