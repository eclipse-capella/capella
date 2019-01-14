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
import org.polarsys.capella.viatra.core.data.cs.surrogate.AbstractPhysicalLinkEnd__involvedLinksMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.AbstractPhysicalLinkEnd__involvedLinksQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in AbstractPhysicalLinkEnd.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file AbstractPhysicalLinkEnd.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>AbstractPhysicalLinkEnd__involvedLinks</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class AbstractPhysicalLinkEnd extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static AbstractPhysicalLinkEnd instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new AbstractPhysicalLinkEnd();
    }
    return INSTANCE;
  }
  
  private static AbstractPhysicalLinkEnd INSTANCE;
  
  private AbstractPhysicalLinkEnd() throws ViatraQueryException {
    querySpecifications.add(AbstractPhysicalLinkEnd__involvedLinksQuerySpecification.instance());
  }
  
  public AbstractPhysicalLinkEnd__involvedLinksQuerySpecification getAbstractPhysicalLinkEnd__involvedLinks() throws ViatraQueryException {
    return AbstractPhysicalLinkEnd__involvedLinksQuerySpecification.instance();
  }
  
  public AbstractPhysicalLinkEnd__involvedLinksMatcher getAbstractPhysicalLinkEnd__involvedLinks(final ViatraQueryEngine engine) throws ViatraQueryException {
    return AbstractPhysicalLinkEnd__involvedLinksMatcher.on(engine);
  }
}
