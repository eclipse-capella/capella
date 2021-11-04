/**
 * 
 *   Copyright (c) 2006, 2020 THALES DMS FRANCE.
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

/**
 * A pattern group formed of all public patterns defined in ConfigurationItem.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ConfigurationItem.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.epbs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>ConfigurationItem__allocatedPhysicalArtifacts</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class ConfigurationItem extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ConfigurationItem instance() {
    if (INSTANCE == null) {
        INSTANCE = new ConfigurationItem();
    }
    return INSTANCE;
  }
  
  private static ConfigurationItem INSTANCE;
  
  private ConfigurationItem() {
    querySpecifications.add(ConfigurationItem__allocatedPhysicalArtifacts.instance());
  }
  
  public ConfigurationItem__allocatedPhysicalArtifacts getConfigurationItem__allocatedPhysicalArtifacts() {
    return ConfigurationItem__allocatedPhysicalArtifacts.instance();
  }
  
  public ConfigurationItem__allocatedPhysicalArtifacts.Matcher getConfigurationItem__allocatedPhysicalArtifacts(final ViatraQueryEngine engine) {
    return ConfigurationItem__allocatedPhysicalArtifacts.Matcher.on(engine);
  }
}
