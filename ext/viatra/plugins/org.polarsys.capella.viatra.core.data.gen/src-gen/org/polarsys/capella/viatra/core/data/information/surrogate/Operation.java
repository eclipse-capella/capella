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
import org.polarsys.capella.viatra.core.data.information.surrogate.Operation__allocatedOperations;
import org.polarsys.capella.viatra.core.data.information.surrogate.Operation__allocatingOperations;
import org.polarsys.capella.viatra.core.data.information.surrogate.Operation__realizedExchangeItems;

/**
 * A pattern group formed of all public patterns defined in Operation.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Operation.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Operation__allocatingOperations</li>
 * <li>Operation__allocatedOperations</li>
 * <li>Operation__realizedExchangeItems</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class Operation extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Operation instance() {
    if (INSTANCE == null) {
        INSTANCE = new Operation();
    }
    return INSTANCE;
  }
  
  private static Operation INSTANCE;
  
  private Operation() {
    querySpecifications.add(Operation__allocatingOperations.instance());
    querySpecifications.add(Operation__allocatedOperations.instance());
    querySpecifications.add(Operation__realizedExchangeItems.instance());
  }
  
  public Operation__allocatingOperations getOperation__allocatingOperations() {
    return Operation__allocatingOperations.instance();
  }
  
  public Operation__allocatingOperations.Matcher getOperation__allocatingOperations(final ViatraQueryEngine engine) {
    return Operation__allocatingOperations.Matcher.on(engine);
  }
  
  public Operation__allocatedOperations getOperation__allocatedOperations() {
    return Operation__allocatedOperations.instance();
  }
  
  public Operation__allocatedOperations.Matcher getOperation__allocatedOperations(final ViatraQueryEngine engine) {
    return Operation__allocatedOperations.Matcher.on(engine);
  }
  
  public Operation__realizedExchangeItems getOperation__realizedExchangeItems() {
    return Operation__realizedExchangeItems.instance();
  }
  
  public Operation__realizedExchangeItems.Matcher getOperation__realizedExchangeItems(final ViatraQueryEngine engine) {
    return Operation__realizedExchangeItems.Matcher.on(engine);
  }
}
