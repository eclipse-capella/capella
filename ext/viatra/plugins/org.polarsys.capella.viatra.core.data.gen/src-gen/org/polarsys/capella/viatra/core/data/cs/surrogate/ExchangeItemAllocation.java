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
package org.polarsys.capella.viatra.core.data.cs.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.cs.surrogate.ExchangeItemAllocation__allocatingInterfaceMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.ExchangeItemAllocation__allocatingInterfaceQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in ExchangeItemAllocation.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ExchangeItemAllocation.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ExchangeItemAllocation__allocatingInterface</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ExchangeItemAllocation extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ExchangeItemAllocation instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ExchangeItemAllocation();
    }
    return INSTANCE;
  }
  
  private static ExchangeItemAllocation INSTANCE;
  
  private ExchangeItemAllocation() throws ViatraQueryException {
    querySpecifications.add(ExchangeItemAllocation__allocatingInterfaceQuerySpecification.instance());
  }
  
  public ExchangeItemAllocation__allocatingInterfaceQuerySpecification getExchangeItemAllocation__allocatingInterface() throws ViatraQueryException {
    return ExchangeItemAllocation__allocatingInterfaceQuerySpecification.instance();
  }
  
  public ExchangeItemAllocation__allocatingInterfaceMatcher getExchangeItemAllocation__allocatingInterface(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ExchangeItemAllocation__allocatingInterfaceMatcher.on(engine);
  }
}
