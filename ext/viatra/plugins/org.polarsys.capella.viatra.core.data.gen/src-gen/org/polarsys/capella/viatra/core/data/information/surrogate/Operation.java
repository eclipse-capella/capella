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
import org.polarsys.capella.viatra.core.data.information.surrogate.Operation__allocatedOperationsMatcher;
import org.polarsys.capella.viatra.core.data.information.surrogate.Operation__allocatingOperationsMatcher;
import org.polarsys.capella.viatra.core.data.information.surrogate.Operation__realizedExchangeItemsMatcher;
import org.polarsys.capella.viatra.core.data.information.surrogate.util.Operation__allocatedOperationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.information.surrogate.util.Operation__allocatingOperationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.information.surrogate.util.Operation__realizedExchangeItemsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in Operation.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Operation.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Operation__allocatingOperations</li>
 * <li>Operation__allocatedOperations</li>
 * <li>Operation__realizedExchangeItems</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class Operation extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Operation instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new Operation();
    }
    return INSTANCE;
  }
  
  private static Operation INSTANCE;
  
  private Operation() throws ViatraQueryException {
    querySpecifications.add(Operation__allocatingOperationsQuerySpecification.instance());
    querySpecifications.add(Operation__allocatedOperationsQuerySpecification.instance());
    querySpecifications.add(Operation__realizedExchangeItemsQuerySpecification.instance());
  }
  
  public Operation__allocatingOperationsQuerySpecification getOperation__allocatingOperations() throws ViatraQueryException {
    return Operation__allocatingOperationsQuerySpecification.instance();
  }
  
  public Operation__allocatingOperationsMatcher getOperation__allocatingOperations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Operation__allocatingOperationsMatcher.on(engine);
  }
  
  public Operation__allocatedOperationsQuerySpecification getOperation__allocatedOperations() throws ViatraQueryException {
    return Operation__allocatedOperationsQuerySpecification.instance();
  }
  
  public Operation__allocatedOperationsMatcher getOperation__allocatedOperations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Operation__allocatedOperationsMatcher.on(engine);
  }
  
  public Operation__realizedExchangeItemsQuerySpecification getOperation__realizedExchangeItems() throws ViatraQueryException {
    return Operation__realizedExchangeItemsQuerySpecification.instance();
  }
  
  public Operation__realizedExchangeItemsMatcher getOperation__realizedExchangeItems(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Operation__realizedExchangeItemsMatcher.on(engine);
  }
}
