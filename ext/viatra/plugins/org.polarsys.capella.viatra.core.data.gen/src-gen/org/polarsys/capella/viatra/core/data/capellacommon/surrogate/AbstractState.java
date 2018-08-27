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
package org.polarsys.capella.viatra.core.data.capellacommon.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.AbstractState__incomingMatcher;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.AbstractState__involverRegionsMatcher;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.AbstractState__outgoingMatcher;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.AbstractState__realizedAbstractStatesMatcher;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.AbstractState__realizingAbstractStatesMatcher;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.util.AbstractState__incomingQuerySpecification;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.util.AbstractState__involverRegionsQuerySpecification;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.util.AbstractState__outgoingQuerySpecification;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.util.AbstractState__realizedAbstractStatesQuerySpecification;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.util.AbstractState__realizingAbstractStatesQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in AbstractState.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractState.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacommon.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractState__realizedAbstractStates</li>
 * <li>AbstractState__realizingAbstractStates</li>
 * <li>AbstractState__outgoing</li>
 * <li>AbstractState__incoming</li>
 * <li>AbstractState__involverRegions</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractState extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractState instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new AbstractState();
    }
    return INSTANCE;
  }
  
  private static AbstractState INSTANCE;
  
  private AbstractState() throws ViatraQueryException {
    querySpecifications.add(AbstractState__realizedAbstractStatesQuerySpecification.instance());
    querySpecifications.add(AbstractState__realizingAbstractStatesQuerySpecification.instance());
    querySpecifications.add(AbstractState__outgoingQuerySpecification.instance());
    querySpecifications.add(AbstractState__incomingQuerySpecification.instance());
    querySpecifications.add(AbstractState__involverRegionsQuerySpecification.instance());
  }
  
  public AbstractState__realizedAbstractStatesQuerySpecification getAbstractState__realizedAbstractStates() throws ViatraQueryException {
    return AbstractState__realizedAbstractStatesQuerySpecification.instance();
  }
  
  public AbstractState__realizedAbstractStatesMatcher getAbstractState__realizedAbstractStates(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractState__realizedAbstractStatesMatcher.on(engine);
  }
  
  public AbstractState__realizingAbstractStatesQuerySpecification getAbstractState__realizingAbstractStates() throws ViatraQueryException {
    return AbstractState__realizingAbstractStatesQuerySpecification.instance();
  }
  
  public AbstractState__realizingAbstractStatesMatcher getAbstractState__realizingAbstractStates(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractState__realizingAbstractStatesMatcher.on(engine);
  }
  
  public AbstractState__outgoingQuerySpecification getAbstractState__outgoing() throws ViatraQueryException {
    return AbstractState__outgoingQuerySpecification.instance();
  }
  
  public AbstractState__outgoingMatcher getAbstractState__outgoing(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractState__outgoingMatcher.on(engine);
  }
  
  public AbstractState__incomingQuerySpecification getAbstractState__incoming() throws ViatraQueryException {
    return AbstractState__incomingQuerySpecification.instance();
  }
  
  public AbstractState__incomingMatcher getAbstractState__incoming(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractState__incomingMatcher.on(engine);
  }
  
  public AbstractState__involverRegionsQuerySpecification getAbstractState__involverRegions() throws ViatraQueryException {
    return AbstractState__involverRegionsQuerySpecification.instance();
  }
  
  public AbstractState__involverRegionsMatcher getAbstractState__involverRegions(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractState__involverRegionsMatcher.on(engine);
  }
}
