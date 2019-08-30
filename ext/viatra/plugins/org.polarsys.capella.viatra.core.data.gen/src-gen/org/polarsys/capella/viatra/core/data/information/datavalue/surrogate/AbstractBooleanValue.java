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
import org.polarsys.capella.viatra.core.data.information.datavalue.surrogate.AbstractBooleanValue__booleanTypeMatcher;
import org.polarsys.capella.viatra.core.data.information.datavalue.surrogate.util.AbstractBooleanValue__booleanTypeQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in AbstractBooleanValue.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractBooleanValue.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.datavalue.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractBooleanValue__booleanType</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractBooleanValue extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractBooleanValue instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new AbstractBooleanValue();
    }
    return INSTANCE;
  }
  
  private static AbstractBooleanValue INSTANCE;
  
  private AbstractBooleanValue() throws ViatraQueryException {
    querySpecifications.add(AbstractBooleanValue__booleanTypeQuerySpecification.instance());
  }
  
  public AbstractBooleanValue__booleanTypeQuerySpecification getAbstractBooleanValue__booleanType() throws ViatraQueryException {
    return AbstractBooleanValue__booleanTypeQuerySpecification.instance();
  }
  
  public AbstractBooleanValue__booleanTypeMatcher getAbstractBooleanValue__booleanType(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractBooleanValue__booleanTypeMatcher.on(engine);
  }
}
