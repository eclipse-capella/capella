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
package org.polarsys.capella.viatra.common.data.activity.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.common.data.activity.surrogate.AbstractActivity__ownedStructuredNodesMatcher;
import org.polarsys.capella.viatra.common.data.activity.surrogate.util.AbstractActivity__ownedStructuredNodesQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in AbstractActivity.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractActivity.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.common.data.activity.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractActivity__ownedStructuredNodes</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractActivity extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractActivity instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new AbstractActivity();
    }
    return INSTANCE;
  }
  
  private static AbstractActivity INSTANCE;
  
  private AbstractActivity() throws ViatraQueryException {
    querySpecifications.add(AbstractActivity__ownedStructuredNodesQuerySpecification.instance());
  }
  
  public AbstractActivity__ownedStructuredNodesQuerySpecification getAbstractActivity__ownedStructuredNodes() throws ViatraQueryException {
    return AbstractActivity__ownedStructuredNodesQuerySpecification.instance();
  }
  
  public AbstractActivity__ownedStructuredNodesMatcher getAbstractActivity__ownedStructuredNodes(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractActivity__ownedStructuredNodesMatcher.on(engine);
  }
}
