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
package org.polarsys.capella.viatra.core.data.epbs.surrogate;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.polarsys.capella.viatra.core.data.epbs.surrogate.PhysicalArtifactRealization__realizedPhysicalArtifact;
import org.polarsys.capella.viatra.core.data.epbs.surrogate.PhysicalArtifactRealization__realizingConfigurationItem;

/**
 * A pattern group formed of all public patterns defined in PhysicalArtifactRealization.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PhysicalArtifactRealization.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.epbs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>PhysicalArtifactRealization__realizedPhysicalArtifact</li>
 * <li>PhysicalArtifactRealization__realizingConfigurationItem</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class PhysicalArtifactRealization extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PhysicalArtifactRealization instance() {
    if (INSTANCE == null) {
        INSTANCE = new PhysicalArtifactRealization();
    }
    return INSTANCE;
  }
  
  private static PhysicalArtifactRealization INSTANCE;
  
  private PhysicalArtifactRealization() {
    querySpecifications.add(PhysicalArtifactRealization__realizedPhysicalArtifact.instance());
    querySpecifications.add(PhysicalArtifactRealization__realizingConfigurationItem.instance());
  }
  
  public PhysicalArtifactRealization__realizedPhysicalArtifact getPhysicalArtifactRealization__realizedPhysicalArtifact() {
    return PhysicalArtifactRealization__realizedPhysicalArtifact.instance();
  }
  
  public PhysicalArtifactRealization__realizedPhysicalArtifact.Matcher getPhysicalArtifactRealization__realizedPhysicalArtifact(final ViatraQueryEngine engine) {
    return PhysicalArtifactRealization__realizedPhysicalArtifact.Matcher.on(engine);
  }
  
  public PhysicalArtifactRealization__realizingConfigurationItem getPhysicalArtifactRealization__realizingConfigurationItem() {
    return PhysicalArtifactRealization__realizingConfigurationItem.instance();
  }
  
  public PhysicalArtifactRealization__realizingConfigurationItem.Matcher getPhysicalArtifactRealization__realizingConfigurationItem(final ViatraQueryEngine engine) {
    return PhysicalArtifactRealization__realizingConfigurationItem.Matcher.on(engine);
  }
}
