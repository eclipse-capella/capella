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
import org.polarsys.capella.viatra.core.data.cs.surrogate.DeploymentTarget__deploymentLinksMatcher;
import org.polarsys.capella.viatra.core.data.cs.surrogate.util.DeploymentTarget__deploymentLinksQuerySpecification;

/**
 * A pattern group formed of all public patterns defined in DeploymentTarget.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file DeploymentTarget.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package org.polarsys.capella.viatra.core.data.cs.surrogate, the group contains the definition of the following patterns: <ul>
 * <li>DeploymentTarget__deploymentLinks</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class DeploymentTarget extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static DeploymentTarget instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new DeploymentTarget();
    }
    return INSTANCE;
  }
  
  private static DeploymentTarget INSTANCE;
  
  private DeploymentTarget() throws ViatraQueryException {
    querySpecifications.add(DeploymentTarget__deploymentLinksQuerySpecification.instance());
  }
  
  public DeploymentTarget__deploymentLinksQuerySpecification getDeploymentTarget__deploymentLinks() throws ViatraQueryException {
    return DeploymentTarget__deploymentLinksQuerySpecification.instance();
  }
  
  public DeploymentTarget__deploymentLinksMatcher getDeploymentTarget__deploymentLinks(final ViatraQueryEngine engine) throws ViatraQueryException {
    return DeploymentTarget__deploymentLinksMatcher.on(engine);
  }
}
