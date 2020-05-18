/**
 * 
 *   Copyright (c) 2006, 2019 THALES DMS FRANCE.
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
package org.polarsys.capella.viatra.core.data.cs.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.cs.surrogate.ComponentRealization__realizedComponent;
import org.polarsys.capella.viatra.core.data.cs.surrogate.ComponentRealization__realizingComponent;

/**
 * A pattern group formed of all public patterns defined in ComponentRealization.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ComponentRealization.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ComponentRealization__realizingComponent</li>
 * <li>ComponentRealization__realizedComponent</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class ComponentRealization extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ComponentRealization instance() {
    if (INSTANCE == null) {
        INSTANCE = new ComponentRealization();
    }
    return INSTANCE;
  }
  
  private static ComponentRealization INSTANCE;
  
  private ComponentRealization() {
    querySpecifications.add(ComponentRealization__realizingComponent.instance());
    querySpecifications.add(ComponentRealization__realizedComponent.instance());
  }
  
  public ComponentRealization__realizingComponent getComponentRealization__realizingComponent() {
    return ComponentRealization__realizingComponent.instance();
  }
  
  public ComponentRealization__realizingComponent.Matcher getComponentRealization__realizingComponent(final ViatraQueryEngine engine) {
    return ComponentRealization__realizingComponent.Matcher.on(engine);
  }
  
  public ComponentRealization__realizedComponent getComponentRealization__realizedComponent() {
    return ComponentRealization__realizedComponent.instance();
  }
  
  public ComponentRealization__realizedComponent.Matcher getComponentRealization__realizedComponent(final ViatraQueryEngine engine) {
    return ComponentRealization__realizedComponent.Matcher.on(engine);
  }
}
