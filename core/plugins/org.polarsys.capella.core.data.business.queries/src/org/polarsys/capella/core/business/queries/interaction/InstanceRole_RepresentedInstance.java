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
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.business.abstractqueries.RefactorDebugger;
import org.polarsys.capella.core.business.abstractqueries.RefactoredBusinessQuery;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionPkgExt;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;

/**
 */
public class InstanceRole_RepresentedInstance implements RefactoredBusinessQuery, IBusinessQuery {

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  public List<CapellaElement> getOldAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    if (element_p instanceof InstanceRole) {
      Scenario scenario = (Scenario) element_p.eContainer();
      InstanceRole ir = (InstanceRole) element_p;

      SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
      if (sysEng == null) {
        return availableElements;
      }

      if (scenario.getKind() == ScenarioKind.FUNCTIONAL) {
        availableElements.addAll(getAvailableElementsForFunctionalScenario(scenario));
      } else if ((scenario.getKind() == ScenarioKind.DATA_FLOW) || (scenario.getKind() == ScenarioKind.INTERFACE)) {
        availableElements.addAll(getAvailableElementsForComponents(scenario));
      } else if (scenario.getKind() == ScenarioKind.INTERACTION) {
        if (ir.getRepresentedInstance() instanceof OperationalActivity) {
          availableElements.addAll(getAvailableElementsForFunctionalScenario(scenario));
        } else {
          availableElements.addAll(getAvailableElementsForEntities(scenario));
        }
      } else {
        throw new IllegalArgumentException();
      }

      // remove element related to element_p
      AbstractInstance representedInstance = ((InstanceRole) element_p).getRepresentedInstance();
      if (null != representedInstance) {
        availableElements.remove(representedInstance);
      }
    }
    return availableElements;
  }

  /**
   * @param scenario_p
   * @return
   */
  private Collection<? extends CapellaElement> getAvailableElementsForEntities(Scenario scenario_p) {
    List<Part> excluded = new ArrayList<Part>();
    for (InstanceRole ir : scenario_p.getOwnedInstanceRoles()) {
      if (ir.getRepresentedInstance() instanceof Part) {
        excluded.add((Part) ir.getRepresentedInstance());
      }
    }
    return ScenarioExt.getAllComponents(scenario_p, excluded);
  }

  /**
   * @param scenario
   */
  private List<CapellaElement> getAvailableElementsForComponents(Scenario scenario) {
    // looking for accessible components
    List<CapellaElement> result = new ArrayList<CapellaElement>();
    List<Part> excluded = new ArrayList<Part>();
    for (InstanceRole ir : scenario.getOwnedInstanceRoles()) {
      if (ir.getRepresentedInstance() instanceof Part) {
        excluded.add((Part) ir.getRepresentedInstance());
      }
    }
    result.addAll(ScenarioExt.getAllAvailableParts(scenario, excluded));
    // then looking for accessible actors
    for (AbstractActor aa : ScenarioExt.getAllActors(scenario)) {
      for (AbstractTypedElement ate : aa.getAbstractTypedElements()) {
        if ((ate instanceof Part) && !excluded.contains(ate)) {
          result.add((Part) ate);
        }
      }
    }
    return result;
  }

  /**
   * @param scenario
   */
  private List<CapellaElement> getAvailableElementsForFunctionalScenario(Scenario scenario) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();
    // looking for accessible functions
    AbstractFunctionalArchitecture archi =
        (AbstractFunctionalArchitecture) EcoreUtil2.getFirstContainer(scenario, FaPackage.Literals.ABSTRACT_FUNCTIONAL_ARCHITECTURE);
    FunctionPkg fpackage = archi.getOwnedFunctionPkg();
    for (Object obj : EcoreUtil2.getAllContents(FunctionPkgExt.getOwnedFunctions(fpackage))) {
      if (obj instanceof AbstractFunction) {
        AbstractFunction af = (AbstractFunction) obj;
        // we add it as result if the function is not already displayed in the scenario
        boolean found = false;
        for (InstanceRole ir : scenario.getOwnedInstanceRoles()) {
          if (ir.getRepresentedInstance() == obj) {
            found = true;
          }
        }
        if (!found) {
          result.add(af);
        }
      }
    }
    return result;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.data.capellacore.CapellaElement,
   *      boolean)
   */
  public List<CapellaElement> getOldCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

    if (element_p instanceof InstanceRole) {
      AbstractInstance representedInstance = ((InstanceRole) element_p).getRepresentedInstance();
      if (representedInstance != null) {
        currentElements.add(representedInstance);
      }
    }

    return currentElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return InteractionPackage.Literals.INSTANCE_ROLE;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeature()
   */
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(InteractionPackage.Literals.INSTANCE_ROLE__REPRESENTED_INSTANCE);
  }

  @Override
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    return RefactorDebugger.callAndTestQuery(
        "GetAvailable_InstanceRole_RepresentedInstance__Lib", element_p, getOldAvailableElements(element_p), getEClass(), getClass());//$NON-NLS-1$
  }

  @Override
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    return RefactorDebugger.callAndTestQuery(
        "GetCurrent_InstanceRole_RepresentedInstance", element_p, getOldCurrentElements(element_p, false), getEClass(), getClass());//$NON-NLS-1$
  }

}
