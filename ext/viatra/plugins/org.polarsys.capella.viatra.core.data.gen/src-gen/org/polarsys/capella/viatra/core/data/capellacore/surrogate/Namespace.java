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

import org.eclipse.viatra.query.runtime.api.IQueryGroup;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.matchers.ViatraQueryRuntimeException;

/**
 * A pattern group formed of all public patterns defined in Namespace.vql.
 * 
 * <p>
 * Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently
 * prepare a VIATRA Query engine for matching all patterns originally defined in file Namespace.vql, in order to achieve
 * better performance than one-by-one on-demand matcher initialization.
 * 
 * <p>
 * From package org.polarsys.capella.viatra.core.data.capellacore.surrogate, the group contains the definition of the
 * following patterns:
 * <ul>
 * <li>Namespace__containedGenericTraces</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class Namespace extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException
   *           if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Namespace instance() {
    if (INSTANCE == null) {
      INSTANCE = new Namespace();
    }
    return INSTANCE;
  }

  private static Namespace INSTANCE;

  private Namespace() {
    querySpecifications.add(Namespace__containedGenericTraces.instance());
  }

  public Namespace__containedGenericTraces getNamespace__containedGenericTraces() {
    return Namespace__containedGenericTraces.instance();
  }

  public Namespace__containedGenericTraces.Matcher getNamespace__containedGenericTraces(
      final ViatraQueryEngine engine) {
    return Namespace__containedGenericTraces.Matcher.on(engine);
  }

}