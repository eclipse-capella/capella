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
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

/**
 */
public class InteractionUse_ReferencedScenario implements IBusinessQuery {

  /**
   * @param arch_p
   * @return
   */
  private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch_p, ScenarioKind kind_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    TreeIterator<Object> allContents = EcoreUtil.getAllContents(arch_p, true);
    while (allContents.hasNext()) {
      Object object = allContents.next();
      if ((object instanceof Scenario) && ((Scenario) object).getKind().equals(kind_p)) {
        availableElements.add((CapellaElement) object);
      }
    }

    return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    Scenario scenario = getAvailableRelatedScenario(element_p);
    BlockArchitecture currentBlockArchitecture = SystemEngineeringExt.getRootBlockArchitecture(element_p);
    if (currentBlockArchitecture != null && scenario != null) {
      availableElements.addAll(getElementsFromBlockArchitecture(currentBlockArchitecture, scenario.getKind()));
      availableElements.remove(scenario);
    }

    // remove existing from the availableElements
    for (CapellaElement element : getCurrentElements(element_p, false)) {
      availableElements.remove(element);
    }

    return availableElements;
  }

  protected Scenario getAvailableRelatedScenario(CapellaElement element_p) {
    if (element_p instanceof InteractionUse) {
      return (Scenario) ((InteractionUse) element_p).eContainer();
    } else if (element_p instanceof Scenario) {
      return (Scenario)element_p;
    }
    return null;
  }

  protected Scenario getCurrentRelatedScenario(CapellaElement element_p) {
    if (element_p instanceof InteractionUse) {
      return ((InteractionUse) element_p).getReferencedScenario();
    } else if (element_p instanceof Scenario) {
      return (Scenario)element_p;
    }
    return null;
  }
  
  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.data.capellacore.CapellaElement, boolean)
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

    Scenario scenario = getCurrentRelatedScenario(element_p);
    if (scenario != null) {
      currentElements.add(scenario);
    }
    
    return currentElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return InteractionPackage.Literals.INTERACTION_USE;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeature()
   */
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(InteractionPackage.Literals.INTERACTION_USE__REFERENCED_SCENARIO);
  }

}
