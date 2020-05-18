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
package org.polarsys.capella.viatra.core.data.information.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.information.surrogate.ExchangeItem__allocatorInterfaces;
import org.polarsys.capella.viatra.core.data.information.surrogate.ExchangeItem__realizedExchangeItems;
import org.polarsys.capella.viatra.core.data.information.surrogate.ExchangeItem__realizingExchangeItems;
import org.polarsys.capella.viatra.core.data.information.surrogate.ExchangeItem__realizingOperations;

/**
 * A pattern group formed of all public patterns defined in ExchangeItem.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ExchangeItem.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ExchangeItem__allocatorInterfaces</li>
 * <li>ExchangeItem__realizedExchangeItems</li>
 * <li>ExchangeItem__realizingExchangeItems</li>
 * <li>ExchangeItem__realizingOperations</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class ExchangeItem extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ExchangeItem instance() {
    if (INSTANCE == null) {
        INSTANCE = new ExchangeItem();
    }
    return INSTANCE;
  }
  
  private static ExchangeItem INSTANCE;
  
  private ExchangeItem() {
    querySpecifications.add(ExchangeItem__allocatorInterfaces.instance());
    querySpecifications.add(ExchangeItem__realizedExchangeItems.instance());
    querySpecifications.add(ExchangeItem__realizingExchangeItems.instance());
    querySpecifications.add(ExchangeItem__realizingOperations.instance());
  }
  
  public ExchangeItem__allocatorInterfaces getExchangeItem__allocatorInterfaces() {
    return ExchangeItem__allocatorInterfaces.instance();
  }
  
  public ExchangeItem__allocatorInterfaces.Matcher getExchangeItem__allocatorInterfaces(final ViatraQueryEngine engine) {
    return ExchangeItem__allocatorInterfaces.Matcher.on(engine);
  }
  
  public ExchangeItem__realizedExchangeItems getExchangeItem__realizedExchangeItems() {
    return ExchangeItem__realizedExchangeItems.instance();
  }
  
  public ExchangeItem__realizedExchangeItems.Matcher getExchangeItem__realizedExchangeItems(final ViatraQueryEngine engine) {
    return ExchangeItem__realizedExchangeItems.Matcher.on(engine);
  }
  
  public ExchangeItem__realizingExchangeItems getExchangeItem__realizingExchangeItems() {
    return ExchangeItem__realizingExchangeItems.instance();
  }
  
  public ExchangeItem__realizingExchangeItems.Matcher getExchangeItem__realizingExchangeItems(final ViatraQueryEngine engine) {
    return ExchangeItem__realizingExchangeItems.Matcher.on(engine);
  }
  
  public ExchangeItem__realizingOperations getExchangeItem__realizingOperations() {
    return ExchangeItem__realizingOperations.instance();
  }
  
  public ExchangeItem__realizingOperations.Matcher getExchangeItem__realizingOperations(final ViatraQueryEngine engine) {
    return ExchangeItem__realizingOperations.Matcher.on(engine);
  }
}
