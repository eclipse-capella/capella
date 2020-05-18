/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamespaceHelper;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioRealization;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

public class ScenarioHelper {
  private static ScenarioHelper instance;

  private ScenarioHelper() {
    // do nothing
  }

  public static ScenarioHelper getInstance() {
    if (instance == null)
      instance = new ScenarioHelper();
    return instance;
  }

  public Object doSwitch(Scenario element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(InteractionPackage.Literals.SCENARIO__CONTAINED_FUNCTIONS)) {
      ret = getContainedFunctions(element);
    } else if (feature.equals(InteractionPackage.Literals.SCENARIO__CONTAINED_PARTS)) {
      ret = getContainedParts(element);
    } else if (feature.equals(InteractionPackage.Literals.SCENARIO__REFERENCED_SCENARIOS)) {
      ret = getReferencedScenarios(element);
    } else if (feature.equals(InteractionPackage.Literals.SCENARIO__REALIZED_SCENARIOS)) {
      ret = getRealizedScenarios(element);
    } else if (feature.equals(InteractionPackage.Literals.SCENARIO__REALIZING_SCENARIOS)) {
      ret = getRealizingScenarios(element);
    }

    // no helper found... searching in super classes...
    if (ret == null) {
      ret = NamespaceHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<AbstractFunction> getContainedFunctions(Scenario element) {
    List<AbstractFunction> res = new ArrayList<>();
    for (InstanceRole instanceRole : element.getOwnedInstanceRoles()) {
      AbstractInstance inst = instanceRole.getRepresentedInstance();
      if (inst instanceof AbstractFunction) {
        res.add((AbstractFunction) inst);
      }
    }
    return res;
  }

  protected List<Part> getContainedParts(Scenario element) {
    List<Part> res = new ArrayList<>();
    for (InstanceRole instanceRole : element.getOwnedInstanceRoles()) {
      AbstractInstance inst = instanceRole.getRepresentedInstance();
      if (inst instanceof Part) {
        res.add((Part) inst);
      }
    }
    return res;
  }

  protected List<Scenario> getReferencedScenarios(Scenario element) {
    List<Scenario> res = new ArrayList<>();
    for (TimeLapse timeLapse : element.getOwnedTimeLapses()) {
      if (timeLapse instanceof InteractionUse) {
        Scenario sc = ((InteractionUse) timeLapse).getReferencedScenario();
        if (null != sc) {
          res.add(sc);
        }
      }
    }
    return res;
  }

  protected List<Scenario> getRealizedScenarios(Scenario element) {
    List<Scenario> res = new ArrayList<>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof ScenarioRealization) {
        Scenario sc = ((ScenarioRealization) trace).getRealizedScenario();
        if (null != sc) {
          res.add(sc);
        }
      }
    }
    return res;
  }

  protected List<Scenario> getRealizingScenarios(Scenario element) {
    List<Scenario> res = new ArrayList<>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof ScenarioRealization) {
        Scenario sc = ((ScenarioRealization) trace).getRealizingScenario();
        if (null != sc) {
          res.add(sc);
        }
      }
    }
    return res;
  }
}
