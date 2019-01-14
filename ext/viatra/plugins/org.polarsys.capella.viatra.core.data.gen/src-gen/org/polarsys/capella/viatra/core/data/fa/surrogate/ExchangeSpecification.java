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
import org.polarsys.capella.viatra.core.data.fa.surrogate.ExchangeSpecification__containingLinkMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ExchangeSpecification__incomingExchangeSpecificationRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ExchangeSpecification__outgoingExchangeSpecificationRealizationsMatcher;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ExchangeSpecification__containingLinkQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ExchangeSpecification__incomingExchangeSpecificationRealizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.fa.surrogate.util.ExchangeSpecification__outgoingExchangeSpecificationRealizationsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in ExchangeSpecification.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ExchangeSpecification.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ExchangeSpecification__containingLink</li>
 * <li>ExchangeSpecification__outgoingExchangeSpecificationRealizations</li>
 * <li>ExchangeSpecification__incomingExchangeSpecificationRealizations</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ExchangeSpecification extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ExchangeSpecification instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ExchangeSpecification();
    }
    return INSTANCE;
  }
  
  private static ExchangeSpecification INSTANCE;
  
  private ExchangeSpecification() throws ViatraQueryException {
    querySpecifications.add(ExchangeSpecification__containingLinkQuerySpecification.instance());
    querySpecifications.add(ExchangeSpecification__outgoingExchangeSpecificationRealizationsQuerySpecification.instance());
    querySpecifications.add(ExchangeSpecification__incomingExchangeSpecificationRealizationsQuerySpecification.instance());
  }
  
  public ExchangeSpecification__containingLinkQuerySpecification getExchangeSpecification__containingLink() throws ViatraQueryException {
    return ExchangeSpecification__containingLinkQuerySpecification.instance();
  }
  
  public ExchangeSpecification__containingLinkMatcher getExchangeSpecification__containingLink(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ExchangeSpecification__containingLinkMatcher.on(engine);
  }
  
  public ExchangeSpecification__outgoingExchangeSpecificationRealizationsQuerySpecification getExchangeSpecification__outgoingExchangeSpecificationRealizations() throws ViatraQueryException {
    return ExchangeSpecification__outgoingExchangeSpecificationRealizationsQuerySpecification.instance();
  }
  
  public ExchangeSpecification__outgoingExchangeSpecificationRealizationsMatcher getExchangeSpecification__outgoingExchangeSpecificationRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ExchangeSpecification__outgoingExchangeSpecificationRealizationsMatcher.on(engine);
  }
  
  public ExchangeSpecification__incomingExchangeSpecificationRealizationsQuerySpecification getExchangeSpecification__incomingExchangeSpecificationRealizations() throws ViatraQueryException {
    return ExchangeSpecification__incomingExchangeSpecificationRealizationsQuerySpecification.instance();
  }
  
  public ExchangeSpecification__incomingExchangeSpecificationRealizationsMatcher getExchangeSpecification__incomingExchangeSpecificationRealizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ExchangeSpecification__incomingExchangeSpecificationRealizationsMatcher.on(engine);
  }
}
