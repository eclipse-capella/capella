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
package org.polarsys.capella.core.business.queries.interaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.ScenarioRealization;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

/**
 */
public class Scenario_RealizedScenario implements IBusinessQuery {

  /**
   * @param arch_p
   * @return
   */
  private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch_p, Scenario scenario_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    ScenarioKind kind = scenario_p.getKind();

    // if unset one we return an empty list
    if (kind == ScenarioKind.UNSET) {
      return availableElements;
    }

    TreeIterator<EObject> allContents = EcoreUtil.getAllContents(arch_p, true);
    EObject eobject = null;

    while (allContents.hasNext()) {
      eobject = allContents.next();

      if (eobject instanceof Scenario) {
        if (ScenarioExt.canRealize(scenario_p, (Scenario) eobject)) {
          availableElements.add((CapellaElement) eobject);
        }
      }
    }

    return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    BlockArchitecture currentBlockArchitecture = SystemEngineeringExt.getRootBlockArchitecture(element_p);
    if (currentBlockArchitecture != null) {
      List<BlockArchitecture> previousBlockArchitectures = BlockArchitectureExt.getPreviousBlockArchitectures(currentBlockArchitecture);
      if (!previousBlockArchitectures.isEmpty()) {
        availableElements
            .addAll(getElementsFromBlockArchitecture(previousBlockArchitectures.get(previousBlockArchitectures.size() - 1), ((Scenario) element_p)));
      }
      availableElements.addAll(getElementsFromBlockArchitecture(currentBlockArchitecture, (Scenario) element_p));
    }

    availableElements.remove(element_p);

    // remove existing from the availableElements
    for (CapellaElement element : getCurrentElements(element_p, false)) {
      availableElements.remove(element);
    }

    return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.data.capellacore.CapellaElement, boolean)
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

    if (element_p instanceof Scenario) {
      for (ScenarioRealization realization : ((Scenario) element_p).getOwnedScenarioRealization()) {
        currentElements.add((CapellaElement) realization.getTargetElement());
      }
    }
    return currentElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return InteractionPackage.Literals.SCENARIO;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeature()
   */
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(InteractionPackage.Literals.SCENARIO__OWNED_SCENARIO_REALIZATION);
  }
}
