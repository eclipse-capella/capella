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
package org.polarsys.capella.viatra.core.data.information.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in ExchangeItemRealization.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ExchangeItemRealization.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ExchangeItemRealization__realizedItem</li>
 * <li>ExchangeItemRealization__realizingOperation</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class ExchangeItemRealization extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ExchangeItemRealization instance() {
    if (INSTANCE == null) {
        INSTANCE = new ExchangeItemRealization();
    }
    return INSTANCE;
  }
  
  private static ExchangeItemRealization INSTANCE;
  
  private ExchangeItemRealization() {
    querySpecifications.add(ExchangeItemRealization__realizedItem.instance());
    querySpecifications.add(ExchangeItemRealization__realizingOperation.instance());
  }
  
  public ExchangeItemRealization__realizedItem getExchangeItemRealization__realizedItem() {
    return ExchangeItemRealization__realizedItem.instance();
  }
  
  public ExchangeItemRealization__realizedItem.Matcher getExchangeItemRealization__realizedItem(final ViatraQueryEngine engine) {
    return ExchangeItemRealization__realizedItem.Matcher.on(engine);
  }
  
  public ExchangeItemRealization__realizingOperation getExchangeItemRealization__realizingOperation() {
    return ExchangeItemRealization__realizingOperation.instance();
  }
  
  public ExchangeItemRealization__realizingOperation.Matcher getExchangeItemRealization__realizingOperation(final ViatraQueryEngine engine) {
    return ExchangeItemRealization__realizingOperation.Matcher.on(engine);
  }
}
