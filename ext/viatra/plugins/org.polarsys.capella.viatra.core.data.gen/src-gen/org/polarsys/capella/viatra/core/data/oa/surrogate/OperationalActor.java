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
import org.polarsys.capella.viatra.core.data.oa.surrogate.OperationalActor__realizingSystemActors;

/**
 * A pattern group formed of all public patterns defined in OperationalActor.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file OperationalActor.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.oa.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>OperationalActor__realizingSystemActors</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class OperationalActor extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static OperationalActor instance() {
    if (INSTANCE == null) {
        INSTANCE = new OperationalActor();
    }
    return INSTANCE;
  }
  
  private static OperationalActor INSTANCE;
  
  private OperationalActor() {
    querySpecifications.add(OperationalActor__realizingSystemActors.instance());
  }
  
  public OperationalActor__realizingSystemActors getOperationalActor__realizingSystemActors() {
    return OperationalActor__realizingSystemActors.instance();
  }
  
  public OperationalActor__realizingSystemActors.Matcher getOperationalActor__realizingSystemActors(final ViatraQueryEngine engine) {
    return OperationalActor__realizingSystemActors.Matcher.on(engine);
  }
}
