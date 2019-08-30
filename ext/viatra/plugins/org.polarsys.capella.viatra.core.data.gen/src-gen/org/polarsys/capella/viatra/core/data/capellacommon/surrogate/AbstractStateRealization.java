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
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.AbstractStateRealization__realizedAbstractStateMatcher;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.AbstractStateRealization__realizingAbstractStateMatcher;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.util.AbstractStateRealization__realizedAbstractStateQuerySpecification;
import org.polarsys.capella.viatra.core.data.capellacommon.surrogate.util.AbstractStateRealization__realizingAbstractStateQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in AbstractStateRealization.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractStateRealization.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacommon.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractStateRealization__realizedAbstractState</li>
 * <li>AbstractStateRealization__realizingAbstractState</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractStateRealization extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractStateRealization instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new AbstractStateRealization();
    }
    return INSTANCE;
  }
  
  private static AbstractStateRealization INSTANCE;
  
  private AbstractStateRealization() throws ViatraQueryException {
    querySpecifications.add(AbstractStateRealization__realizedAbstractStateQuerySpecification.instance());
    querySpecifications.add(AbstractStateRealization__realizingAbstractStateQuerySpecification.instance());
  }
  
  public AbstractStateRealization__realizedAbstractStateQuerySpecification getAbstractStateRealization__realizedAbstractState() throws ViatraQueryException {
    return AbstractStateRealization__realizedAbstractStateQuerySpecification.instance();
  }
  
  public AbstractStateRealization__realizedAbstractStateMatcher getAbstractStateRealization__realizedAbstractState(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractStateRealization__realizedAbstractStateMatcher.on(engine);
  }
  
  public AbstractStateRealization__realizingAbstractStateQuerySpecification getAbstractStateRealization__realizingAbstractState() throws ViatraQueryException {
    return AbstractStateRealization__realizingAbstractStateQuerySpecification.instance();
  }
  
  public AbstractStateRealization__realizingAbstractStateMatcher getAbstractStateRealization__realizingAbstractState(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractStateRealization__realizingAbstractStateMatcher.on(engine);
  }
}
