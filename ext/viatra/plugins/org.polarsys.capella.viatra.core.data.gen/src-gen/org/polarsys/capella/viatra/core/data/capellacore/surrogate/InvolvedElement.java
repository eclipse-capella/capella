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
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.InvolvedElement__involvingInvolvementsMatcher;
import org.polarsys.capella.viatra.core.data.capellacore.surrogate.util.InvolvedElement__involvingInvolvementsQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in InvolvedElement.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file InvolvedElement.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.capellacore.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>InvolvedElement__involvingInvolvements</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class InvolvedElement extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static InvolvedElement instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new InvolvedElement();
    }
    return INSTANCE;
  }
  
  private static InvolvedElement INSTANCE;
  
  private InvolvedElement() throws ViatraQueryException {
    querySpecifications.add(InvolvedElement__involvingInvolvementsQuerySpecification.instance());
  }
  
  public InvolvedElement__involvingInvolvementsQuerySpecification getInvolvedElement__involvingInvolvements() throws ViatraQueryException {
    return InvolvedElement__involvingInvolvementsQuerySpecification.instance();
  }
  
  public InvolvedElement__involvingInvolvementsMatcher getInvolvedElement__involvingInvolvements(final ViatraQueryEngine engine) throws ViatraQueryException {
    return InvolvedElement__involvingInvolvementsMatcher.on(engine);
  }
}
