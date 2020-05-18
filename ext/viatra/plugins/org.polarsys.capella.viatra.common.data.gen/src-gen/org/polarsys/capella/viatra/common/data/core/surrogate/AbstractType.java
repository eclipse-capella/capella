/**
 * 
 *   Copyright (c) 2006, 2019 THALES DMS FRANCE.
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
package org.polarsys.capella.viatra.common.data.core.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.common.data.core.surrogate.AbstractType__abstractTypedElements;

/**
 * A pattern group formed of all public patterns defined in AbstractType.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractType.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.common.data.core.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractType__abstractTypedElements</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractType extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractType instance() {
    if (INSTANCE == null) {
        INSTANCE = new AbstractType();
    }
    return INSTANCE;
  }
  
  private static AbstractType INSTANCE;
  
  private AbstractType() {
    querySpecifications.add(AbstractType__abstractTypedElements.instance());
  }
  
  public AbstractType__abstractTypedElements getAbstractType__abstractTypedElements() {
    return AbstractType__abstractTypedElements.instance();
  }
  
  public AbstractType__abstractTypedElements.Matcher getAbstractType__abstractTypedElements(final ViatraQueryEngine engine) {
    return AbstractType__abstractTypedElements.Matcher.on(engine);
  }
}
