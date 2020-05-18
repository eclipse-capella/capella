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
package org.polarsys.capella.viatra.core.data.fa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ExchangeSpecification__containingLink;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ExchangeSpecification__incomingExchangeSpecificationRealizations;
import org.polarsys.capella.viatra.core.data.fa.surrogate.ExchangeSpecification__outgoingExchangeSpecificationRealizations;

/**
 * A pattern group formed of all public patterns defined in ExchangeSpecification.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ExchangeSpecification.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.fa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ExchangeSpecification__containingLink</li>
 * <li>ExchangeSpecification__outgoingExchangeSpecificationRealizations</li>
 * <li>ExchangeSpecification__incomingExchangeSpecificationRealizations</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class ExchangeSpecification extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ExchangeSpecification instance() {
    if (INSTANCE == null) {
        INSTANCE = new ExchangeSpecification();
    }
    return INSTANCE;
  }
  
  private static ExchangeSpecification INSTANCE;
  
  private ExchangeSpecification() {
    querySpecifications.add(ExchangeSpecification__containingLink.instance());
    querySpecifications.add(ExchangeSpecification__outgoingExchangeSpecificationRealizations.instance());
    querySpecifications.add(ExchangeSpecification__incomingExchangeSpecificationRealizations.instance());
  }
  
  public ExchangeSpecification__containingLink getExchangeSpecification__containingLink() {
    return ExchangeSpecification__containingLink.instance();
  }
  
  public ExchangeSpecification__containingLink.Matcher getExchangeSpecification__containingLink(final ViatraQueryEngine engine) {
    return ExchangeSpecification__containingLink.Matcher.on(engine);
  }
  
  public ExchangeSpecification__outgoingExchangeSpecificationRealizations getExchangeSpecification__outgoingExchangeSpecificationRealizations() {
    return ExchangeSpecification__outgoingExchangeSpecificationRealizations.instance();
  }
  
  public ExchangeSpecification__outgoingExchangeSpecificationRealizations.Matcher getExchangeSpecification__outgoingExchangeSpecificationRealizations(final ViatraQueryEngine engine) {
    return ExchangeSpecification__outgoingExchangeSpecificationRealizations.Matcher.on(engine);
  }
  
  public ExchangeSpecification__incomingExchangeSpecificationRealizations getExchangeSpecification__incomingExchangeSpecificationRealizations() {
    return ExchangeSpecification__incomingExchangeSpecificationRealizations.instance();
  }
  
  public ExchangeSpecification__incomingExchangeSpecificationRealizations.Matcher getExchangeSpecification__incomingExchangeSpecificationRealizations(final ViatraQueryEngine engine) {
    return ExchangeSpecification__incomingExchangeSpecificationRealizations.Matcher.on(engine);
  }
}
