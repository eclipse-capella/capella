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
import org.polarsys.capella.viatra.core.data.information.datavalue.surrogate.AbstractStringValue__stringType;

/**
 * A pattern group formed of all public patterns defined in AbstractStringValue.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractStringValue.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.datavalue.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractStringValue__stringType</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractStringValue extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractStringValue instance() {
    if (INSTANCE == null) {
        INSTANCE = new AbstractStringValue();
    }
    return INSTANCE;
  }
  
  private static AbstractStringValue INSTANCE;
  
  private AbstractStringValue() {
    querySpecifications.add(AbstractStringValue__stringType.instance());
  }
  
  public AbstractStringValue__stringType getAbstractStringValue__stringType() {
    return AbstractStringValue__stringType.instance();
  }
  
  public AbstractStringValue__stringType.Matcher getAbstractStringValue__stringType(final ViatraQueryEngine engine) {
    return AbstractStringValue__stringType.Matcher.on(engine);
  }
}
