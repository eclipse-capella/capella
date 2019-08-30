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
package org.polarsys.capella.viatra.core.data.capellacore.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.Namespace__containedGenericTracesMatcher;
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.Namespace__containedRequirementsTracesMatcher;
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.util.Namespace__containedGenericTracesQuerySpecification;
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.util.Namespace__containedRequirementsTracesQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in Namespace.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Namespace.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacore.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Namespace__containedGenericTraces</li>
 * <li>Namespace__containedRequirementsTraces</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class Namespace extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Namespace instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new Namespace();
    }
    return INSTANCE;
  }
  
  private static Namespace INSTANCE;
  
  private Namespace() throws ViatraQueryException {
    querySpecifications.add(Namespace__containedGenericTracesQuerySpecification.instance());
    querySpecifications.add(Namespace__containedRequirementsTracesQuerySpecification.instance());
  }
  
  public Namespace__containedGenericTracesQuerySpecification getNamespace__containedGenericTraces() throws ViatraQueryException {
    return Namespace__containedGenericTracesQuerySpecification.instance();
  }
  
  public Namespace__containedGenericTracesMatcher getNamespace__containedGenericTraces(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Namespace__containedGenericTracesMatcher.on(engine);
  }
  
  public Namespace__containedRequirementsTracesQuerySpecification getNamespace__containedRequirementsTraces() throws ViatraQueryException {
    return Namespace__containedRequirementsTracesQuerySpecification.instance();
  }
  
  public Namespace__containedRequirementsTracesMatcher getNamespace__containedRequirementsTraces(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Namespace__containedRequirementsTracesMatcher.on(engine);
  }
}
