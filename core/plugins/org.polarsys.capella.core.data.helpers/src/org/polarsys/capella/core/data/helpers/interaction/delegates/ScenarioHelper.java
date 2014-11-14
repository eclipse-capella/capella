/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

  public Object doSwitch(Scenario element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(InteractionPackage.Literals.SCENARIO__CONTAINED_FUNCTIONS)) {
      ret = getContainedFunctions(element_p);
    } else if (feature_p.equals(InteractionPackage.Literals.SCENARIO__CONTAINED_PARTS)) {
      ret = getContainedParts(element_p);
    } else if (feature_p.equals(InteractionPackage.Literals.SCENARIO__REFERENCED_SCENARIOS)) {
      ret = getReferencedScenarios(element_p);
    } else if (feature_p.equals(InteractionPackage.Literals.SCENARIO__REALIZED_SCENARIOS)) {
      ret = getRealizedScenarios(element_p);
    } else if (feature_p.equals(InteractionPackage.Literals.SCENARIO__REALIZING_SCENARIOS)) {
      ret = getRealizingScenarios(element_p);
    }

    // no helper found... searching in super classes...
    if (ret == null) {
      ret = NamespaceHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected List<AbstractFunction> getContainedFunctions(Scenario element_p) {
    List<AbstractFunction> res = new ArrayList<AbstractFunction>();
    for (InstanceRole instanceRole : element_p.getOwnedInstanceRoles()) {
      AbstractInstance inst = instanceRole.getRepresentedInstance();
      if (inst instanceof AbstractFunction) {
        res.add((AbstractFunction) inst);
      }
    }
    return res;
  }

  protected List<Part> getContainedParts(Scenario element_p) {
    List<Part> res = new ArrayList<Part>();
    for (InstanceRole instanceRole : element_p.getOwnedInstanceRoles()) {
      AbstractInstance inst = instanceRole.getRepresentedInstance();
      if (inst instanceof Part) {
        res.add((Part) inst);
      }
    }
    return res;
  }

  protected List<Scenario> getReferencedScenarios(Scenario element_p) {
    List<Scenario> res = new ArrayList<Scenario>();
    for (TimeLapse timeLapse : element_p.getOwnedTimeLapses()) {
      if (timeLapse instanceof InteractionUse) {
        Scenario sc = ((InteractionUse) timeLapse).getReferencedScenario();
        if (null != sc) {
          res.add(sc);
        }
      }
    }
    return res;
  }

  protected List<Scenario> getRealizedScenarios(Scenario element_p) {
    List<Scenario> res = new ArrayList<Scenario>();
    for (AbstractTrace trace : element_p.getOutgoingTraces()) {
      if (trace instanceof ScenarioRealization) {
        Scenario sc = ((ScenarioRealization) trace).getRealizedScenario();
        if (null != sc) {
          res.add(sc);
        }
      }
    }
    return res;
  }

  protected List<Scenario> getRealizingScenarios(Scenario element_p) {
    List<Scenario> res = new ArrayList<Scenario>();
    for (AbstractTrace trace : element_p.getIncomingTraces()) {
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
