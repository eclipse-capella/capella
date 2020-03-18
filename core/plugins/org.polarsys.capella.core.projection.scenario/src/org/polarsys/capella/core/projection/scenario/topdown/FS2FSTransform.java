/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.scenario.topdown;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

/**
 */
public class FS2FSTransform extends TopDownTransform {

  /**
   * @see org.polarsys.capella.core.projection.scenario.ScenarioTransform#isValidTransitionedScenario(org.polarsys.capella.common.data.modellingcore.ModelElement,
   *      org.polarsys.capella.core.data.interaction.Scenario)
   */
  @Override
  protected boolean isValidTransitionedScenario(Scenario contextElement_p, Scenario scenario_p) {
    BlockArchitecture sourceBlock = BlockArchitectureExt.getRootBlockArchitecture(contextElement_p);
    BlockArchitecture targetBlock = BlockArchitectureExt.getRootBlockArchitecture(scenario_p);

    return (scenario_p.getKind() == ScenarioKind.FUNCTIONAL) && !sourceBlock.equals(targetBlock);
  }
}
