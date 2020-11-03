/**
 * 
 *   Copyright (c) 2006, 2020 THALES DMS FRANCE.
 *   
 *   This program and the accompanying materials are made available under the
 *   terms of the Eclipse Public License 2.0 which is available at
 *   http://www.eclipse.org/legal/epl-2.0
 *   
 *   SPDX-License-Identifier: EPL-2.0
 *  
 *   Contributors:
 *      Thales - initial API and implementation
 *  
 */
package org.polarsys.capella.viatra.core.data.information.datavalue.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.information.datavalue.surrogate.AbstractComplexValue__complexType;

/**
 * A pattern group formed of all public patterns defined in AbstractComplexValue.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractComplexValue.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.datavalue.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractComplexValue__complexType</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractComplexValue extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractComplexValue instance() {
    if (INSTANCE == null) {
        INSTANCE = new AbstractComplexValue();
    }
    return INSTANCE;
  }
  
  private static AbstractComplexValue INSTANCE;
  
  private AbstractComplexValue() {
    querySpecifications.add(AbstractComplexValue__complexType.instance());
  }
  
  public AbstractComplexValue__complexType getAbstractComplexValue__complexType() {
    return AbstractComplexValue__complexType.instance();
  }
  
  public AbstractComplexValue__complexType.Matcher getAbstractComplexValue__complexType(final ViatraQueryEngine engine) {
    return AbstractComplexValue__complexType.Matcher.on(engine);
  }
}
