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
package org.polarsys.capella.viatra.core.data.interaction.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractFunctionAbstractCapabilityInvolvement__capabilityMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractFunctionAbstractCapabilityInvolvement__functionMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.AbstractFunctionAbstractCapabilityInvolvement__capabilityQuerySpecification;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.AbstractFunctionAbstractCapabilityInvolvement__functionQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in AbstractFunctionAbstractCapabilityInvolvement.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractFunctionAbstractCapabilityInvolvement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.interaction.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractFunctionAbstractCapabilityInvolvement__capability</li>
 * <li>AbstractFunctionAbstractCapabilityInvolvement__function</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractFunctionAbstractCapabilityInvolvement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractFunctionAbstractCapabilityInvolvement instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new AbstractFunctionAbstractCapabilityInvolvement();
    }
    return INSTANCE;
  }
  
  private static AbstractFunctionAbstractCapabilityInvolvement INSTANCE;
  
  private AbstractFunctionAbstractCapabilityInvolvement() throws ViatraQueryException {
    querySpecifications.add(AbstractFunctionAbstractCapabilityInvolvement__capabilityQuerySpecification.instance());
    querySpecifications.add(AbstractFunctionAbstractCapabilityInvolvement__functionQuerySpecification.instance());
  }
  
  public AbstractFunctionAbstractCapabilityInvolvement__capabilityQuerySpecification getAbstractFunctionAbstractCapabilityInvolvement__capability() throws ViatraQueryException {
    return AbstractFunctionAbstractCapabilityInvolvement__capabilityQuerySpecification.instance();
  }
  
  public AbstractFunctionAbstractCapabilityInvolvement__capabilityMatcher getAbstractFunctionAbstractCapabilityInvolvement__capability(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractFunctionAbstractCapabilityInvolvement__capabilityMatcher.on(engine);
  }
  
  public AbstractFunctionAbstractCapabilityInvolvement__functionQuerySpecification getAbstractFunctionAbstractCapabilityInvolvement__function() throws ViatraQueryException {
    return AbstractFunctionAbstractCapabilityInvolvement__functionQuerySpecification.instance();
  }
  
  public AbstractFunctionAbstractCapabilityInvolvement__functionMatcher getAbstractFunctionAbstractCapabilityInvolvement__function(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractFunctionAbstractCapabilityInvolvement__functionMatcher.on(engine);
  }
}
