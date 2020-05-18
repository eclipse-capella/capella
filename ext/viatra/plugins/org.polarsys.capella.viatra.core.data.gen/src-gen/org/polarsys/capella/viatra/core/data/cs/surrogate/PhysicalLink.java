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
package org.polarsys.capella.viatra.core.data.cs.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalLink__categories;
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalLink__realizedPhysicalLinks;
import org.polarsys.capella.viatra.core.data.cs.surrogate.PhysicalLink__realizingPhysicalLinks;

/**
 * A pattern group formed of all public patterns defined in PhysicalLink.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PhysicalLink.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PhysicalLink__categories</li>
 * <li>PhysicalLink__realizedPhysicalLinks</li>
 * <li>PhysicalLink__realizingPhysicalLinks</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class PhysicalLink extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PhysicalLink instance() {
    if (INSTANCE == null) {
        INSTANCE = new PhysicalLink();
    }
    return INSTANCE;
  }
  
  private static PhysicalLink INSTANCE;
  
  private PhysicalLink() {
    querySpecifications.add(PhysicalLink__categories.instance());
    querySpecifications.add(PhysicalLink__realizedPhysicalLinks.instance());
    querySpecifications.add(PhysicalLink__realizingPhysicalLinks.instance());
  }
  
  public PhysicalLink__categories getPhysicalLink__categories() {
    return PhysicalLink__categories.instance();
  }
  
  public PhysicalLink__categories.Matcher getPhysicalLink__categories(final ViatraQueryEngine engine) {
    return PhysicalLink__categories.Matcher.on(engine);
  }
  
  public PhysicalLink__realizedPhysicalLinks getPhysicalLink__realizedPhysicalLinks() {
    return PhysicalLink__realizedPhysicalLinks.instance();
  }
  
  public PhysicalLink__realizedPhysicalLinks.Matcher getPhysicalLink__realizedPhysicalLinks(final ViatraQueryEngine engine) {
    return PhysicalLink__realizedPhysicalLinks.Matcher.on(engine);
  }
  
  public PhysicalLink__realizingPhysicalLinks getPhysicalLink__realizingPhysicalLinks() {
    return PhysicalLink__realizingPhysicalLinks.instance();
  }
  
  public PhysicalLink__realizingPhysicalLinks.Matcher getPhysicalLink__realizingPhysicalLinks(final ViatraQueryEngine engine) {
    return PhysicalLink__realizingPhysicalLinks.Matcher.on(engine);
  }
}
