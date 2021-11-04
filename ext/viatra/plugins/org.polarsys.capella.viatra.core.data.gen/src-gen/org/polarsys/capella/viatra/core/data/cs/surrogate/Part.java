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
package org.polarsys.capella.viatra.core.data.cs.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in Part.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Part.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Part__providedInterfaces</li>
 * <li>Part__requiredInterfaces</li>
 * <li>Part__deployedParts</li>
 * <li>Part__deployingParts</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class Part extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Part instance() {
    if (INSTANCE == null) {
        INSTANCE = new Part();
    }
    return INSTANCE;
  }
  
  private static Part INSTANCE;
  
  private Part() {
    querySpecifications.add(Part__providedInterfaces.instance());
    querySpecifications.add(Part__requiredInterfaces.instance());
    querySpecifications.add(Part__deployedParts.instance());
    querySpecifications.add(Part__deployingParts.instance());
  }
  
  public Part__providedInterfaces getPart__providedInterfaces() {
    return Part__providedInterfaces.instance();
  }
  
  public Part__providedInterfaces.Matcher getPart__providedInterfaces(final ViatraQueryEngine engine) {
    return Part__providedInterfaces.Matcher.on(engine);
  }
  
  public Part__requiredInterfaces getPart__requiredInterfaces() {
    return Part__requiredInterfaces.instance();
  }
  
  public Part__requiredInterfaces.Matcher getPart__requiredInterfaces(final ViatraQueryEngine engine) {
    return Part__requiredInterfaces.Matcher.on(engine);
  }
  
  public Part__deployedParts getPart__deployedParts() {
    return Part__deployedParts.instance();
  }
  
  public Part__deployedParts.Matcher getPart__deployedParts(final ViatraQueryEngine engine) {
    return Part__deployedParts.Matcher.on(engine);
  }
  
  public Part__deployingParts getPart__deployingParts() {
    return Part__deployingParts.instance();
  }
  
  public Part__deployingParts.Matcher getPart__deployingParts(final ViatraQueryEngine engine) {
    return Part__deployingParts.Matcher.on(engine);
  }
}
