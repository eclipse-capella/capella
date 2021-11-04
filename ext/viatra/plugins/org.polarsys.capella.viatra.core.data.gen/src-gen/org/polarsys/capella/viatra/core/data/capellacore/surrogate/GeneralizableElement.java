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
package org.polarsys.capella.viatra.core.data.capellacore.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in GeneralizableElement.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file GeneralizableElement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacore.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>GeneralizableElement__superGeneralizations</li>
 * <li>GeneralizableElement__subGeneralizations</li>
 * <li>GeneralizableElement__super</li>
 * <li>GeneralizableElement__sub</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class GeneralizableElement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static GeneralizableElement instance() {
    if (INSTANCE == null) {
        INSTANCE = new GeneralizableElement();
    }
    return INSTANCE;
  }
  
  private static GeneralizableElement INSTANCE;
  
  private GeneralizableElement() {
    querySpecifications.add(GeneralizableElement__superGeneralizations.instance());
    querySpecifications.add(GeneralizableElement__subGeneralizations.instance());
    querySpecifications.add(GeneralizableElement__super.instance());
    querySpecifications.add(GeneralizableElement__sub.instance());
  }
  
  public GeneralizableElement__superGeneralizations getGeneralizableElement__superGeneralizations() {
    return GeneralizableElement__superGeneralizations.instance();
  }
  
  public GeneralizableElement__superGeneralizations.Matcher getGeneralizableElement__superGeneralizations(final ViatraQueryEngine engine) {
    return GeneralizableElement__superGeneralizations.Matcher.on(engine);
  }
  
  public GeneralizableElement__subGeneralizations getGeneralizableElement__subGeneralizations() {
    return GeneralizableElement__subGeneralizations.instance();
  }
  
  public GeneralizableElement__subGeneralizations.Matcher getGeneralizableElement__subGeneralizations(final ViatraQueryEngine engine) {
    return GeneralizableElement__subGeneralizations.Matcher.on(engine);
  }
  
  public GeneralizableElement__super getGeneralizableElement__super() {
    return GeneralizableElement__super.instance();
  }
  
  public GeneralizableElement__super.Matcher getGeneralizableElement__super(final ViatraQueryEngine engine) {
    return GeneralizableElement__super.Matcher.on(engine);
  }
  
  public GeneralizableElement__sub getGeneralizableElement__sub() {
    return GeneralizableElement__sub.instance();
  }
  
  public GeneralizableElement__sub.Matcher getGeneralizableElement__sub(final ViatraQueryEngine engine) {
    return GeneralizableElement__sub.Matcher.on(engine);
  }
}
