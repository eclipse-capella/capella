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
package org.polarsys.capella.viatra.core.data.oa.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.oa.surrogate.OperationalAnalysis__allocatingSystemAnalysesMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.OperationalAnalysis__containedOperationalActivityPkgMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.OperationalAnalysis__containedOperationalCapabilityPkgMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.OperationalAnalysis__allocatingSystemAnalysesQuerySpecification;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.OperationalAnalysis__containedOperationalActivityPkgQuerySpecification;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.OperationalAnalysis__containedOperationalCapabilityPkgQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in OperationalAnalysis.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file OperationalAnalysis.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.oa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>OperationalAnalysis__containedOperationalCapabilityPkg</li>
 * <li>OperationalAnalysis__containedOperationalActivityPkg</li>
 * <li>OperationalAnalysis__allocatingSystemAnalyses</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class OperationalAnalysis extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static OperationalAnalysis instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new OperationalAnalysis();
    }
    return INSTANCE;
  }
  
  private static OperationalAnalysis INSTANCE;
  
  private OperationalAnalysis() throws ViatraQueryException {
    querySpecifications.add(OperationalAnalysis__containedOperationalCapabilityPkgQuerySpecification.instance());
    querySpecifications.add(OperationalAnalysis__containedOperationalActivityPkgQuerySpecification.instance());
    querySpecifications.add(OperationalAnalysis__allocatingSystemAnalysesQuerySpecification.instance());
  }
  
  public OperationalAnalysis__containedOperationalCapabilityPkgQuerySpecification getOperationalAnalysis__containedOperationalCapabilityPkg() throws ViatraQueryException {
    return OperationalAnalysis__containedOperationalCapabilityPkgQuerySpecification.instance();
  }
  
  public OperationalAnalysis__containedOperationalCapabilityPkgMatcher getOperationalAnalysis__containedOperationalCapabilityPkg(final ViatraQueryEngine engine) throws ViatraQueryException {
    return OperationalAnalysis__containedOperationalCapabilityPkgMatcher.on(engine);
  }
  
  public OperationalAnalysis__containedOperationalActivityPkgQuerySpecification getOperationalAnalysis__containedOperationalActivityPkg() throws ViatraQueryException {
    return OperationalAnalysis__containedOperationalActivityPkgQuerySpecification.instance();
  }
  
  public OperationalAnalysis__containedOperationalActivityPkgMatcher getOperationalAnalysis__containedOperationalActivityPkg(final ViatraQueryEngine engine) throws ViatraQueryException {
    return OperationalAnalysis__containedOperationalActivityPkgMatcher.on(engine);
  }
  
  public OperationalAnalysis__allocatingSystemAnalysesQuerySpecification getOperationalAnalysis__allocatingSystemAnalyses() throws ViatraQueryException {
    return OperationalAnalysis__allocatingSystemAnalysesQuerySpecification.instance();
  }
  
  public OperationalAnalysis__allocatingSystemAnalysesMatcher getOperationalAnalysis__allocatingSystemAnalyses(final ViatraQueryEngine engine) throws ViatraQueryException {
    return OperationalAnalysis__allocatingSystemAnalysesMatcher.on(engine);
  }
}
