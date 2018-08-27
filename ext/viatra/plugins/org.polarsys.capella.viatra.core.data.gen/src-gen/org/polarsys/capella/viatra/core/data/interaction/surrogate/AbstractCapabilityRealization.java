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
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapabilityRealization__realizedCapabilityMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.AbstractCapabilityRealization__realizingCapabilityMatcher;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.AbstractCapabilityRealization__realizedCapabilityQuerySpecification;
import org.polarsys.capella.viatra.core.data.interaction.surrogate.util.AbstractCapabilityRealization__realizingCapabilityQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in AbstractCapabilityRealization.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractCapabilityRealization.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.interaction.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractCapabilityRealization__realizedCapability</li>
 * <li>AbstractCapabilityRealization__realizingCapability</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractCapabilityRealization extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractCapabilityRealization instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new AbstractCapabilityRealization();
    }
    return INSTANCE;
  }
  
  private static AbstractCapabilityRealization INSTANCE;
  
  private AbstractCapabilityRealization() throws ViatraQueryException {
    querySpecifications.add(AbstractCapabilityRealization__realizedCapabilityQuerySpecification.instance());
    querySpecifications.add(AbstractCapabilityRealization__realizingCapabilityQuerySpecification.instance());
  }
  
  public AbstractCapabilityRealization__realizedCapabilityQuerySpecification getAbstractCapabilityRealization__realizedCapability() throws ViatraQueryException {
    return AbstractCapabilityRealization__realizedCapabilityQuerySpecification.instance();
  }
  
  public AbstractCapabilityRealization__realizedCapabilityMatcher getAbstractCapabilityRealization__realizedCapability(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractCapabilityRealization__realizedCapabilityMatcher.on(engine);
  }
  
  public AbstractCapabilityRealization__realizingCapabilityQuerySpecification getAbstractCapabilityRealization__realizingCapability() throws ViatraQueryException {
    return AbstractCapabilityRealization__realizingCapabilityQuerySpecification.instance();
  }
  
  public AbstractCapabilityRealization__realizingCapabilityMatcher getAbstractCapabilityRealization__realizingCapability(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractCapabilityRealization__realizingCapabilityMatcher.on(engine);
  }
}
