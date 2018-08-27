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
package org.polarsys.capella.viatra.core.data.information.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.polarsys.capella.viatra.core.data.information.surrogate.PortRealization__realizedPortMatcher;
import org.polarsys.capella.viatra.core.data.information.surrogate.PortRealization__realizingPortMatcher;
import org.polarsys.capella.viatra.core.data.information.surrogate.util.PortRealization__realizedPortQuerySpecification;
import org.polarsys.capella.viatra.core.data.information.surrogate.util.PortRealization__realizingPortQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in PortRealization.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PortRealization.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.information.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PortRealization__realizedPort</li>
 * <li>PortRealization__realizingPort</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class PortRealization extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PortRealization instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new PortRealization();
    }
    return INSTANCE;
  }
  
  private static PortRealization INSTANCE;
  
  private PortRealization() throws ViatraQueryException {
    querySpecifications.add(PortRealization__realizedPortQuerySpecification.instance());
    querySpecifications.add(PortRealization__realizingPortQuerySpecification.instance());
  }
  
  public PortRealization__realizedPortQuerySpecification getPortRealization__realizedPort() throws ViatraQueryException {
    return PortRealization__realizedPortQuerySpecification.instance();
  }
  
  public PortRealization__realizedPortMatcher getPortRealization__realizedPort(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PortRealization__realizedPortMatcher.on(engine);
  }
  
  public PortRealization__realizingPortQuerySpecification getPortRealization__realizingPort() throws ViatraQueryException {
    return PortRealization__realizingPortQuerySpecification.instance();
  }
  
  public PortRealization__realizingPortMatcher getPortRealization__realizingPort(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PortRealization__realizingPortMatcher.on(engine);
  }
}
