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
package org.polarsys.capella.viatra.core.data.cs.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.cs.surrogate.ExchangeItemAllocation__allocatingInterface;

/**
 * A pattern group formed of all public patterns defined in ExchangeItemAllocation.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ExchangeItemAllocation.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ExchangeItemAllocation__allocatingInterface</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class ExchangeItemAllocation extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ExchangeItemAllocation instance() {
    if (INSTANCE == null) {
        INSTANCE = new ExchangeItemAllocation();
    }
    return INSTANCE;
  }
  
  private static ExchangeItemAllocation INSTANCE;
  
  private ExchangeItemAllocation() {
    querySpecifications.add(ExchangeItemAllocation__allocatingInterface.instance());
  }
  
  public ExchangeItemAllocation__allocatingInterface getExchangeItemAllocation__allocatingInterface() {
    return ExchangeItemAllocation__allocatingInterface.instance();
  }
  
  public ExchangeItemAllocation__allocatingInterface.Matcher getExchangeItemAllocation__allocatingInterface(final ViatraQueryEngine engine) {
    return ExchangeItemAllocation__allocatingInterface.Matcher.on(engine);
  }
}
