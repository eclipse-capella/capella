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
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractFunctionAbstractCapabilityInvolvement__capability;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractFunctionAbstractCapabilityInvolvement__function;

/**
 * A pattern group formed of all public patterns defined in AbstractFunctionAbstractCapabilityInvolvement.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractFunctionAbstractCapabilityInvolvement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.interaction.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractFunctionAbstractCapabilityInvolvement__capability</li>
 * <li>AbstractFunctionAbstractCapabilityInvolvement__function</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractFunctionAbstractCapabilityInvolvement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractFunctionAbstractCapabilityInvolvement instance() {
    if (INSTANCE == null) {
        INSTANCE = new AbstractFunctionAbstractCapabilityInvolvement();
    }
    return INSTANCE;
  }
  
  private static AbstractFunctionAbstractCapabilityInvolvement INSTANCE;
  
  private AbstractFunctionAbstractCapabilityInvolvement() {
    querySpecifications.add(AbstractFunctionAbstractCapabilityInvolvement__capability.instance());
    querySpecifications.add(AbstractFunctionAbstractCapabilityInvolvement__function.instance());
  }
  
  public AbstractFunctionAbstractCapabilityInvolvement__capability getAbstractFunctionAbstractCapabilityInvolvement__capability() {
    return AbstractFunctionAbstractCapabilityInvolvement__capability.instance();
  }
  
  public AbstractFunctionAbstractCapabilityInvolvement__capability.Matcher getAbstractFunctionAbstractCapabilityInvolvement__capability(final ViatraQueryEngine engine) {
    return AbstractFunctionAbstractCapabilityInvolvement__capability.Matcher.on(engine);
  }
  
  public AbstractFunctionAbstractCapabilityInvolvement__function getAbstractFunctionAbstractCapabilityInvolvement__function() {
    return AbstractFunctionAbstractCapabilityInvolvement__function.instance();
  }
  
  public AbstractFunctionAbstractCapabilityInvolvement__function.Matcher getAbstractFunctionAbstractCapabilityInvolvement__function(final ViatraQueryEngine engine) {
    return AbstractFunctionAbstractCapabilityInvolvement__function.Matcher.on(engine);
  }
}
