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
import org.polarsys.capella.viatra.core.data.fa.surrogate.ExchangeLink__exchangesMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ExchangeLink__exchangesQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in ExchangeLink.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ExchangeLink.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ExchangeLink__exchanges</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ExchangeLink extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ExchangeLink instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ExchangeLink();
    }
    return INSTANCE;
  }
  
  private static ExchangeLink INSTANCE;
  
  private ExchangeLink() throws ViatraQueryException {
    querySpecifications.add(ExchangeLink__exchangesQuerySpecification.instance());
  }
  
  public ExchangeLink__exchangesQuerySpecification getExchangeLink__exchanges() throws ViatraQueryException {
    return ExchangeLink__exchangesQuerySpecification.instance();
  }
  
  public ExchangeLink__exchangesMatcher getExchangeLink__exchanges(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ExchangeLink__exchangesMatcher.on(engine);
  }
}
