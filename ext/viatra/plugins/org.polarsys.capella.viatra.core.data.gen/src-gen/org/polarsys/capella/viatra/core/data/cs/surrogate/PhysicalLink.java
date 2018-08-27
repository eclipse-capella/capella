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
package org.polarsys.capella.viatra.core.data.cs.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalLink__categoriesMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalLink__realizedPhysicalLinksMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalLink__realizingPhysicalLinksMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.PhysicalLink__categoriesQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.PhysicalLink__realizedPhysicalLinksQuerySpecification;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.PhysicalLink__realizingPhysicalLinksQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in PhysicalLink.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PhysicalLink.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PhysicalLink__categories</li>
 * <li>PhysicalLink__realizedPhysicalLinks</li>
 * <li>PhysicalLink__realizingPhysicalLinks</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class PhysicalLink extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PhysicalLink instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new PhysicalLink();
    }
    return INSTANCE;
  }
  
  private static PhysicalLink INSTANCE;
  
  private PhysicalLink() throws ViatraQueryException {
    querySpecifications.add(PhysicalLink__categoriesQuerySpecification.instance());
    querySpecifications.add(PhysicalLink__realizedPhysicalLinksQuerySpecification.instance());
    querySpecifications.add(PhysicalLink__realizingPhysicalLinksQuerySpecification.instance());
  }
  
  public PhysicalLink__categoriesQuerySpecification getPhysicalLink__categories() throws ViatraQueryException {
    return PhysicalLink__categoriesQuerySpecification.instance();
  }
  
  public PhysicalLink__categoriesMatcher getPhysicalLink__categories(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalLink__categoriesMatcher.on(engine);
  }
  
  public PhysicalLink__realizedPhysicalLinksQuerySpecification getPhysicalLink__realizedPhysicalLinks() throws ViatraQueryException {
    return PhysicalLink__realizedPhysicalLinksQuerySpecification.instance();
  }
  
  public PhysicalLink__realizedPhysicalLinksMatcher getPhysicalLink__realizedPhysicalLinks(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalLink__realizedPhysicalLinksMatcher.on(engine);
  }
  
  public PhysicalLink__realizingPhysicalLinksQuerySpecification getPhysicalLink__realizingPhysicalLinks() throws ViatraQueryException {
    return PhysicalLink__realizingPhysicalLinksQuerySpecification.instance();
  }
  
  public PhysicalLink__realizingPhysicalLinksMatcher getPhysicalLink__realizingPhysicalLinks(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PhysicalLink__realizingPhysicalLinksMatcher.on(engine);
  }
}
