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
package org.polarsys.capella.viatra.common.re.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.common.re.surrogate.CatalogElement__referencedElementsMatcher;
import org.polarsys.capella.viatra.common.re.surrogate.CatalogElement__replicatedElementsMatcher;
import org.polarsys.capella.viatra.common.re.surrogate.util.CatalogElement__referencedElementsQuerySpecification;
import org.polarsys.capella.viatra.common.re.surrogate.util.CatalogElement__replicatedElementsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in CatalogElement.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file CatalogElement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.common.re.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>CatalogElement__referencedElements</li>
 * <li>CatalogElement__replicatedElements</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class CatalogElement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static CatalogElement instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new CatalogElement();
    }
    return INSTANCE;
  }
  
  private static CatalogElement INSTANCE;
  
  private CatalogElement() throws ViatraQueryException {
    querySpecifications.add(CatalogElement__referencedElementsQuerySpecification.instance());
    querySpecifications.add(CatalogElement__replicatedElementsQuerySpecification.instance());
  }
  
  public CatalogElement__referencedElementsQuerySpecification getCatalogElement__referencedElements() throws ViatraQueryException {
    return CatalogElement__referencedElementsQuerySpecification.instance();
  }
  
  public CatalogElement__referencedElementsMatcher getCatalogElement__referencedElements(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CatalogElement__referencedElementsMatcher.on(engine);
  }
  
  public CatalogElement__replicatedElementsQuerySpecification getCatalogElement__replicatedElements() throws ViatraQueryException {
    return CatalogElement__replicatedElementsQuerySpecification.instance();
  }
  
  public CatalogElement__replicatedElementsMatcher getCatalogElement__replicatedElements(final ViatraQueryEngine engine) throws ViatraQueryException {
    return CatalogElement__replicatedElementsMatcher.on(engine);
  }
}
