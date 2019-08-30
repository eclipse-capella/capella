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
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.GeneralizableElement__subGeneralizationsMatcher;
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.GeneralizableElement__subMatcher;
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.GeneralizableElement__superGeneralizationsMatcher;
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.GeneralizableElement__superMatcher;
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.util.GeneralizableElement__subGeneralizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.util.GeneralizableElement__subQuerySpecification;
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.util.GeneralizableElement__superGeneralizationsQuerySpecification;
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.util.GeneralizableElement__superQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in GeneralizableElement.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
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
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class GeneralizableElement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static GeneralizableElement instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new GeneralizableElement();
    }
    return INSTANCE;
  }
  
  private static GeneralizableElement INSTANCE;
  
  private GeneralizableElement() throws ViatraQueryException {
    querySpecifications.add(GeneralizableElement__superGeneralizationsQuerySpecification.instance());
    querySpecifications.add(GeneralizableElement__subGeneralizationsQuerySpecification.instance());
    querySpecifications.add(GeneralizableElement__superQuerySpecification.instance());
    querySpecifications.add(GeneralizableElement__subQuerySpecification.instance());
  }
  
  public GeneralizableElement__superGeneralizationsQuerySpecification getGeneralizableElement__superGeneralizations() throws ViatraQueryException {
    return GeneralizableElement__superGeneralizationsQuerySpecification.instance();
  }
  
  public GeneralizableElement__superGeneralizationsMatcher getGeneralizableElement__superGeneralizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return GeneralizableElement__superGeneralizationsMatcher.on(engine);
  }
  
  public GeneralizableElement__subGeneralizationsQuerySpecification getGeneralizableElement__subGeneralizations() throws ViatraQueryException {
    return GeneralizableElement__subGeneralizationsQuerySpecification.instance();
  }
  
  public GeneralizableElement__subGeneralizationsMatcher getGeneralizableElement__subGeneralizations(final ViatraQueryEngine engine) throws ViatraQueryException {
    return GeneralizableElement__subGeneralizationsMatcher.on(engine);
  }
  
  public GeneralizableElement__superQuerySpecification getGeneralizableElement__super() throws ViatraQueryException {
    return GeneralizableElement__superQuerySpecification.instance();
  }
  
  public GeneralizableElement__superMatcher getGeneralizableElement__super(final ViatraQueryEngine engine) throws ViatraQueryException {
    return GeneralizableElement__superMatcher.on(engine);
  }
  
  public GeneralizableElement__subQuerySpecification getGeneralizableElement__sub() throws ViatraQueryException {
    return GeneralizableElement__subQuerySpecification.instance();
  }
  
  public GeneralizableElement__subMatcher getGeneralizableElement__sub(final ViatraQueryEngine engine) throws ViatraQueryException {
    return GeneralizableElement__subMatcher.on(engine);
  }
}
