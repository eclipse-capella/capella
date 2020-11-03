/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.data.helpers.interaction.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioRealization;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class ScenarioRealizationHelper {
  private static ScenarioRealizationHelper instance;

  private ScenarioRealizationHelper() {
    // do nothing
  }

  public static ScenarioRealizationHelper getInstance() {
    if (instance == null)
      instance = new ScenarioRealizationHelper();
    return instance;
  }

  public Object doSwitch(ScenarioRealization element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(InteractionPackage.Literals.SCENARIO_REALIZATION__REALIZED_SCENARIO)) {
      ret = getRealizedScenario(element);
    } else if (feature.equals(InteractionPackage.Literals.SCENARIO_REALIZATION__REALIZING_SCENARIO)) {
      ret = getRealizingScenario(element);
    }

    // no helper found... searching in super classes...
    if (ret == null) {
      ret = AllocationHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected Scenario getRealizingScenario(ScenarioRealization element) {
    TraceableElement ret = element.getSourceElement();
    if (ret instanceof Scenario)
      return (Scenario) ret;
    return null;
  }

  protected Scenario getRealizedScenario(ScenarioRealization element) {
    TraceableElement ret = element.getTargetElement();
    if (ret instanceof Scenario)
      return (Scenario) ret;
    return null;
  }
}
