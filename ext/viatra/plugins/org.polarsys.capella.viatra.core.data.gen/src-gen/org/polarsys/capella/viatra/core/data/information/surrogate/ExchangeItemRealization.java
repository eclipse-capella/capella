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
package org.polarsys.capella.viatra.core.data.information.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.information.surrogate.ExchangeItemRealization__realizedItemMatcher;
import org.polarsys.capella.viatra.core.data.information.surrogate.ExchangeItemRealization__realizingOperationMatcher;
import org.polarsys.capella.viatra.core.data.information.surrogate.util.ExchangeItemRealization__realizedItemQuerySpecification;
import org.polarsys.capella.viatra.core.data.information.surrogate.util.ExchangeItemRealization__realizingOperationQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in ExchangeItemRealization.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ExchangeItemRealization.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ExchangeItemRealization__realizedItem</li>
 * <li>ExchangeItemRealization__realizingOperation</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ExchangeItemRealization extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ExchangeItemRealization instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ExchangeItemRealization();
    }
    return INSTANCE;
  }
  
  private static ExchangeItemRealization INSTANCE;
  
  private ExchangeItemRealization() throws ViatraQueryException {
    querySpecifications.add(ExchangeItemRealization__realizedItemQuerySpecification.instance());
    querySpecifications.add(ExchangeItemRealization__realizingOperationQuerySpecification.instance());
  }
  
  public ExchangeItemRealization__realizedItemQuerySpecification getExchangeItemRealization__realizedItem() throws ViatraQueryException {
    return ExchangeItemRealization__realizedItemQuerySpecification.instance();
  }
  
  public ExchangeItemRealization__realizedItemMatcher getExchangeItemRealization__realizedItem(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ExchangeItemRealization__realizedItemMatcher.on(engine);
  }
  
  public ExchangeItemRealization__realizingOperationQuerySpecification getExchangeItemRealization__realizingOperation() throws ViatraQueryException {
    return ExchangeItemRealization__realizingOperationQuerySpecification.instance();
  }
  
  public ExchangeItemRealization__realizingOperationMatcher getExchangeItemRealization__realizingOperation(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ExchangeItemRealization__realizingOperationMatcher.on(engine);
  }
}
