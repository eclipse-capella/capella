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
package org.polarsys.capella.viatra.core.data.fa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ExchangeSpecificationRealization__realizedExchangeSpecificationMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ExchangeSpecificationRealization__realizingExchangeSpecificationMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ExchangeSpecificationRealization__realizedExchangeSpecificationQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ExchangeSpecificationRealization__realizingExchangeSpecificationQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in ExchangeSpecificationRealization.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ExchangeSpecificationRealization.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ExchangeSpecificationRealization__realizedExchangeSpecification</li>
 * <li>ExchangeSpecificationRealization__realizingExchangeSpecification</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ExchangeSpecificationRealization extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ExchangeSpecificationRealization instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ExchangeSpecificationRealization();
    }
    return INSTANCE;
  }
  
  private static ExchangeSpecificationRealization INSTANCE;
  
  private ExchangeSpecificationRealization() throws ViatraQueryException {
    querySpecifications.add(ExchangeSpecificationRealization__realizedExchangeSpecificationQuerySpecification.instance());
    querySpecifications.add(ExchangeSpecificationRealization__realizingExchangeSpecificationQuerySpecification.instance());
  }
  
  public ExchangeSpecificationRealization__realizedExchangeSpecificationQuerySpecification getExchangeSpecificationRealization__realizedExchangeSpecification() throws ViatraQueryException {
    return ExchangeSpecificationRealization__realizedExchangeSpecificationQuerySpecification.instance();
  }
  
  public ExchangeSpecificationRealization__realizedExchangeSpecificationMatcher getExchangeSpecificationRealization__realizedExchangeSpecification(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ExchangeSpecificationRealization__realizedExchangeSpecificationMatcher.on(engine);
  }
  
  public ExchangeSpecificationRealization__realizingExchangeSpecificationQuerySpecification getExchangeSpecificationRealization__realizingExchangeSpecification() throws ViatraQueryException {
    return ExchangeSpecificationRealization__realizingExchangeSpecificationQuerySpecification.instance();
  }
  
  public ExchangeSpecificationRealization__realizingExchangeSpecificationMatcher getExchangeSpecificationRealization__realizingExchangeSpecification(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ExchangeSpecificationRealization__realizingExchangeSpecificationMatcher.on(engine);
  }
}
