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
import org.polarsys.capella.viatra.core.data.information.surrogate.ExchangeItem__allocatorInterfacesMatcher;
import org.polarsys.capella.viatra.core.data.information.surrogate.ExchangeItem__realizedExchangeItemsMatcher;
import org.polarsys.capella.viatra.core.data.information.surrogate.ExchangeItem__realizingExchangeItemsMatcher;
import org.polarsys.capella.viatra.core.data.information.surrogate.ExchangeItem__realizingOperationsMatcher;
import org.polarsys.capella.viatra.core.data.information.surrogate.util.ExchangeItem__allocatorInterfacesQuerySpecification;
import org.polarsys.capella.viatra.core.data.information.surrogate.util.ExchangeItem__realizedExchangeItemsQuerySpecification;
import org.polarsys.capella.viatra.core.data.information.surrogate.util.ExchangeItem__realizingExchangeItemsQuerySpecification;
import org.polarsys.capella.viatra.core.data.information.surrogate.util.ExchangeItem__realizingOperationsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in ExchangeItem.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
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
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ExchangeItem extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ExchangeItem instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ExchangeItem();
    }
    return INSTANCE;
  }
  
  private static ExchangeItem INSTANCE;
  
  private ExchangeItem() throws ViatraQueryException {
    querySpecifications.add(ExchangeItem__allocatorInterfacesQuerySpecification.instance());
    querySpecifications.add(ExchangeItem__realizedExchangeItemsQuerySpecification.instance());
    querySpecifications.add(ExchangeItem__realizingExchangeItemsQuerySpecification.instance());
    querySpecifications.add(ExchangeItem__realizingOperationsQuerySpecification.instance());
  }
  
  public ExchangeItem__allocatorInterfacesQuerySpecification getExchangeItem__allocatorInterfaces() throws ViatraQueryException {
    return ExchangeItem__allocatorInterfacesQuerySpecification.instance();
  }
  
  public ExchangeItem__allocatorInterfacesMatcher getExchangeItem__allocatorInterfaces(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ExchangeItem__allocatorInterfacesMatcher.on(engine);
  }
  
  public ExchangeItem__realizedExchangeItemsQuerySpecification getExchangeItem__realizedExchangeItems() throws ViatraQueryException {
    return ExchangeItem__realizedExchangeItemsQuerySpecification.instance();
  }
  
  public ExchangeItem__realizedExchangeItemsMatcher getExchangeItem__realizedExchangeItems(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ExchangeItem__realizedExchangeItemsMatcher.on(engine);
  }
  
  public ExchangeItem__realizingExchangeItemsQuerySpecification getExchangeItem__realizingExchangeItems() throws ViatraQueryException {
    return ExchangeItem__realizingExchangeItemsQuerySpecification.instance();
  }
  
  public ExchangeItem__realizingExchangeItemsMatcher getExchangeItem__realizingExchangeItems(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ExchangeItem__realizingExchangeItemsMatcher.on(engine);
  }
  
  public ExchangeItem__realizingOperationsQuerySpecification getExchangeItem__realizingOperations() throws ViatraQueryException {
    return ExchangeItem__realizingOperationsQuerySpecification.instance();
  }
  
  public ExchangeItem__realizingOperationsMatcher getExchangeItem__realizingOperations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ExchangeItem__realizingOperationsMatcher.on(engine);
  }
}
