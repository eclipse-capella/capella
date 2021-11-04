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
package org.polarsys.capella.viatra.core.data.fa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in ExchangeSpecificationRealization.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ExchangeSpecificationRealization.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ExchangeSpecificationRealization__realizedExchangeSpecification</li>
 * <li>ExchangeSpecificationRealization__realizingExchangeSpecification</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class ExchangeSpecificationRealization extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ExchangeSpecificationRealization instance() {
    if (INSTANCE == null) {
        INSTANCE = new ExchangeSpecificationRealization();
    }
    return INSTANCE;
  }
  
  private static ExchangeSpecificationRealization INSTANCE;
  
  private ExchangeSpecificationRealization() {
    querySpecifications.add(ExchangeSpecificationRealization__realizedExchangeSpecification.instance());
    querySpecifications.add(ExchangeSpecificationRealization__realizingExchangeSpecification.instance());
  }
  
  public ExchangeSpecificationRealization__realizedExchangeSpecification getExchangeSpecificationRealization__realizedExchangeSpecification() {
    return ExchangeSpecificationRealization__realizedExchangeSpecification.instance();
  }
  
  public ExchangeSpecificationRealization__realizedExchangeSpecification.Matcher getExchangeSpecificationRealization__realizedExchangeSpecification(final ViatraQueryEngine engine) {
    return ExchangeSpecificationRealization__realizedExchangeSpecification.Matcher.on(engine);
  }
  
  public ExchangeSpecificationRealization__realizingExchangeSpecification getExchangeSpecificationRealization__realizingExchangeSpecification() {
    return ExchangeSpecificationRealization__realizingExchangeSpecification.instance();
  }
  
  public ExchangeSpecificationRealization__realizingExchangeSpecification.Matcher getExchangeSpecificationRealization__realizingExchangeSpecification(final ViatraQueryEngine engine) {
    return ExchangeSpecificationRealization__realizingExchangeSpecification.Matcher.on(engine);
  }
}
