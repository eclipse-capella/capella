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
package org.polarsys.capella.viatra.core.data.interaction.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapabilityRealization__realizedCapability;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapabilityRealization__realizingCapability;

/**
 * A pattern group formed of all public patterns defined in AbstractCapabilityRealization.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractCapabilityRealization.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.interaction.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractCapabilityRealization__realizedCapability</li>
 * <li>AbstractCapabilityRealization__realizingCapability</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractCapabilityRealization extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractCapabilityRealization instance() {
    if (INSTANCE == null) {
        INSTANCE = new AbstractCapabilityRealization();
    }
    return INSTANCE;
  }
  
  private static AbstractCapabilityRealization INSTANCE;
  
  private AbstractCapabilityRealization() {
    querySpecifications.add(AbstractCapabilityRealization__realizedCapability.instance());
    querySpecifications.add(AbstractCapabilityRealization__realizingCapability.instance());
  }
  
  public AbstractCapabilityRealization__realizedCapability getAbstractCapabilityRealization__realizedCapability() {
    return AbstractCapabilityRealization__realizedCapability.instance();
  }
  
  public AbstractCapabilityRealization__realizedCapability.Matcher getAbstractCapabilityRealization__realizedCapability(final ViatraQueryEngine engine) {
    return AbstractCapabilityRealization__realizedCapability.Matcher.on(engine);
  }
  
  public AbstractCapabilityRealization__realizingCapability getAbstractCapabilityRealization__realizingCapability() {
    return AbstractCapabilityRealization__realizingCapability.instance();
  }
  
  public AbstractCapabilityRealization__realizingCapability.Matcher getAbstractCapabilityRealization__realizingCapability(final ViatraQueryEngine engine) {
    return AbstractCapabilityRealization__realizingCapability.Matcher.on(engine);
  }
}
