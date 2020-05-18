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
package org.polarsys.capella.viatra.common.data.activity.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.common.data.activity.surrogate.AbstractActivity__ownedStructuredNodes;

/**
 * A pattern group formed of all public patterns defined in AbstractActivity.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractActivity.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.common.data.activity.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractActivity__ownedStructuredNodes</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractActivity extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractActivity instance() {
    if (INSTANCE == null) {
        INSTANCE = new AbstractActivity();
    }
    return INSTANCE;
  }
  
  private static AbstractActivity INSTANCE;
  
  private AbstractActivity() {
    querySpecifications.add(AbstractActivity__ownedStructuredNodes.instance());
  }
  
  public AbstractActivity__ownedStructuredNodes getAbstractActivity__ownedStructuredNodes() {
    return AbstractActivity__ownedStructuredNodes.instance();
  }
  
  public AbstractActivity__ownedStructuredNodes.Matcher getAbstractActivity__ownedStructuredNodes(final ViatraQueryEngine engine) {
    return AbstractActivity__ownedStructuredNodes.Matcher.on(engine);
  }
}
