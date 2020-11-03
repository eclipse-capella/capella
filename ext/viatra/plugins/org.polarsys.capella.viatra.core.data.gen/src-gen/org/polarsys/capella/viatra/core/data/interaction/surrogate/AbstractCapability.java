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
package org.polarsys.capella.viatra.core.data.interaction.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__extendedAbstractCapabilities;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__extending;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__extendingAbstractCapabilities;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__includedAbstractCapabilities;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__including;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__includingAbstractCapabilities;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__incomingCapabilityAllocation;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__involvedAbstractFunctions;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__involvedFunctionalChains;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__outgoingCapabilityAllocation;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__sub;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__subGeneralizations;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapability__super;

/**
 * A pattern group formed of all public patterns defined in AbstractCapability.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractCapability.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.interaction.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractCapability__incomingCapabilityAllocation</li>
 * <li>AbstractCapability__outgoingCapabilityAllocation</li>
 * <li>AbstractCapability__extending</li>
 * <li>AbstractCapability__subGeneralizations</li>
 * <li>AbstractCapability__including</li>
 * <li>AbstractCapability__super</li>
 * <li>AbstractCapability__sub</li>
 * <li>AbstractCapability__includedAbstractCapabilities</li>
 * <li>AbstractCapability__includingAbstractCapabilities</li>
 * <li>AbstractCapability__extendedAbstractCapabilities</li>
 * <li>AbstractCapability__extendingAbstractCapabilities</li>
 * <li>AbstractCapability__involvedAbstractFunctions</li>
 * <li>AbstractCapability__involvedFunctionalChains</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractCapability extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractCapability instance() {
    if (INSTANCE == null) {
        INSTANCE = new AbstractCapability();
    }
    return INSTANCE;
  }
  
  private static AbstractCapability INSTANCE;
  
  private AbstractCapability() {
    querySpecifications.add(AbstractCapability__incomingCapabilityAllocation.instance());
    querySpecifications.add(AbstractCapability__outgoingCapabilityAllocation.instance());
    querySpecifications.add(AbstractCapability__extending.instance());
    querySpecifications.add(AbstractCapability__subGeneralizations.instance());
    querySpecifications.add(AbstractCapability__including.instance());
    querySpecifications.add(AbstractCapability__super.instance());
    querySpecifications.add(AbstractCapability__sub.instance());
    querySpecifications.add(AbstractCapability__includedAbstractCapabilities.instance());
    querySpecifications.add(AbstractCapability__includingAbstractCapabilities.instance());
    querySpecifications.add(AbstractCapability__extendedAbstractCapabilities.instance());
    querySpecifications.add(AbstractCapability__extendingAbstractCapabilities.instance());
    querySpecifications.add(AbstractCapability__involvedAbstractFunctions.instance());
    querySpecifications.add(AbstractCapability__involvedFunctionalChains.instance());
  }
  
  public AbstractCapability__incomingCapabilityAllocation getAbstractCapability__incomingCapabilityAllocation() {
    return AbstractCapability__incomingCapabilityAllocation.instance();
  }
  
  public AbstractCapability__incomingCapabilityAllocation.Matcher getAbstractCapability__incomingCapabilityAllocation(final ViatraQueryEngine engine) {
    return AbstractCapability__incomingCapabilityAllocation.Matcher.on(engine);
  }
  
  public AbstractCapability__outgoingCapabilityAllocation getAbstractCapability__outgoingCapabilityAllocation() {
    return AbstractCapability__outgoingCapabilityAllocation.instance();
  }
  
  public AbstractCapability__outgoingCapabilityAllocation.Matcher getAbstractCapability__outgoingCapabilityAllocation(final ViatraQueryEngine engine) {
    return AbstractCapability__outgoingCapabilityAllocation.Matcher.on(engine);
  }
  
  public AbstractCapability__extending getAbstractCapability__extending() {
    return AbstractCapability__extending.instance();
  }
  
  public AbstractCapability__extending.Matcher getAbstractCapability__extending(final ViatraQueryEngine engine) {
    return AbstractCapability__extending.Matcher.on(engine);
  }
  
  public AbstractCapability__subGeneralizations getAbstractCapability__subGeneralizations() {
    return AbstractCapability__subGeneralizations.instance();
  }
  
  public AbstractCapability__subGeneralizations.Matcher getAbstractCapability__subGeneralizations(final ViatraQueryEngine engine) {
    return AbstractCapability__subGeneralizations.Matcher.on(engine);
  }
  
  public AbstractCapability__including getAbstractCapability__including() {
    return AbstractCapability__including.instance();
  }
  
  public AbstractCapability__including.Matcher getAbstractCapability__including(final ViatraQueryEngine engine) {
    return AbstractCapability__including.Matcher.on(engine);
  }
  
  public AbstractCapability__super getAbstractCapability__super() {
    return AbstractCapability__super.instance();
  }
  
  public AbstractCapability__super.Matcher getAbstractCapability__super(final ViatraQueryEngine engine) {
    return AbstractCapability__super.Matcher.on(engine);
  }
  
  public AbstractCapability__sub getAbstractCapability__sub() {
    return AbstractCapability__sub.instance();
  }
  
  public AbstractCapability__sub.Matcher getAbstractCapability__sub(final ViatraQueryEngine engine) {
    return AbstractCapability__sub.Matcher.on(engine);
  }
  
  public AbstractCapability__includedAbstractCapabilities getAbstractCapability__includedAbstractCapabilities() {
    return AbstractCapability__includedAbstractCapabilities.instance();
  }
  
  public AbstractCapability__includedAbstractCapabilities.Matcher getAbstractCapability__includedAbstractCapabilities(final ViatraQueryEngine engine) {
    return AbstractCapability__includedAbstractCapabilities.Matcher.on(engine);
  }
  
  public AbstractCapability__includingAbstractCapabilities getAbstractCapability__includingAbstractCapabilities() {
    return AbstractCapability__includingAbstractCapabilities.instance();
  }
  
  public AbstractCapability__includingAbstractCapabilities.Matcher getAbstractCapability__includingAbstractCapabilities(final ViatraQueryEngine engine) {
    return AbstractCapability__includingAbstractCapabilities.Matcher.on(engine);
  }
  
  public AbstractCapability__extendedAbstractCapabilities getAbstractCapability__extendedAbstractCapabilities() {
    return AbstractCapability__extendedAbstractCapabilities.instance();
  }
  
  public AbstractCapability__extendedAbstractCapabilities.Matcher getAbstractCapability__extendedAbstractCapabilities(final ViatraQueryEngine engine) {
    return AbstractCapability__extendedAbstractCapabilities.Matcher.on(engine);
  }
  
  public AbstractCapability__extendingAbstractCapabilities getAbstractCapability__extendingAbstractCapabilities() {
    return AbstractCapability__extendingAbstractCapabilities.instance();
  }
  
  public AbstractCapability__extendingAbstractCapabilities.Matcher getAbstractCapability__extendingAbstractCapabilities(final ViatraQueryEngine engine) {
    return AbstractCapability__extendingAbstractCapabilities.Matcher.on(engine);
  }
  
  public AbstractCapability__involvedAbstractFunctions getAbstractCapability__involvedAbstractFunctions() {
    return AbstractCapability__involvedAbstractFunctions.instance();
  }
  
  public AbstractCapability__involvedAbstractFunctions.Matcher getAbstractCapability__involvedAbstractFunctions(final ViatraQueryEngine engine) {
    return AbstractCapability__involvedAbstractFunctions.Matcher.on(engine);
  }
  
  public AbstractCapability__involvedFunctionalChains getAbstractCapability__involvedFunctionalChains() {
    return AbstractCapability__involvedFunctionalChains.instance();
  }
  
  public AbstractCapability__involvedFunctionalChains.Matcher getAbstractCapability__involvedFunctionalChains(final ViatraQueryEngine engine) {
    return AbstractCapability__involvedFunctionalChains.Matcher.on(engine);
  }
}
