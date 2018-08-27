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
import org.polarsys.capella.viatra.core.data.oa.surrogate.Swimlane__representedEntityMatcher;
import org.polarsys.capella.viatra.core.data.oa.surrogate.util.Swimlane__representedEntityQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in Swimlane.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Swimlane.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.oa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>Swimlane__representedEntity</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class Swimlane extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Swimlane instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new Swimlane();
    }
    return INSTANCE;
  }
  
  private static Swimlane INSTANCE;
  
  private Swimlane() throws ViatraQueryException {
    querySpecifications.add(Swimlane__representedEntityQuerySpecification.instance());
  }
  
  public Swimlane__representedEntityQuerySpecification getSwimlane__representedEntity() throws ViatraQueryException {
    return Swimlane__representedEntityQuerySpecification.instance();
  }
  
  public Swimlane__representedEntityMatcher getSwimlane__representedEntity(final ViatraQueryEngine engine) throws ViatraQueryException {
    return Swimlane__representedEntityMatcher.on(engine);
  }
}
