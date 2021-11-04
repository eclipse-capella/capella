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
package org.polarsys.capella.viatra.common.re.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in CatalogElement.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file CatalogElement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.common.re.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>CatalogElement__referencedElements</li>
 * <li>CatalogElement__replicatedElements</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class CatalogElement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static CatalogElement instance() {
    if (INSTANCE == null) {
        INSTANCE = new CatalogElement();
    }
    return INSTANCE;
  }
  
  private static CatalogElement INSTANCE;
  
  private CatalogElement() {
    querySpecifications.add(CatalogElement__referencedElements.instance());
    querySpecifications.add(CatalogElement__replicatedElements.instance());
  }
  
  public CatalogElement__referencedElements getCatalogElement__referencedElements() {
    return CatalogElement__referencedElements.instance();
  }
  
  public CatalogElement__referencedElements.Matcher getCatalogElement__referencedElements(final ViatraQueryEngine engine) {
    return CatalogElement__referencedElements.Matcher.on(engine);
  }
  
  public CatalogElement__replicatedElements getCatalogElement__replicatedElements() {
    return CatalogElement__replicatedElements.instance();
  }
  
  public CatalogElement__replicatedElements.Matcher getCatalogElement__replicatedElements(final ViatraQueryEngine engine) {
    return CatalogElement__replicatedElements.Matcher.on(engine);
  }
}
