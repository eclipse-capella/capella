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
package org.polarsys.capella.core.business.queries.cs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.business.abstractqueries.ExtendedBusinessQueryForLib;
import org.polarsys.capella.core.business.abstractqueries.RefactorDebugger;
import org.polarsys.capella.core.business.abstractqueries.RefactoredBusinessQuery;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 */
public class Part_DeployedElements implements ExtendedBusinessQueryForLib, RefactoredBusinessQuery, IBusinessQuery {

  public boolean isMultipleDeploymentAllowed() {
    return CapellaModelPreferencesPlugin.getDefault().isMultipleDeploymentAllowed();
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  public List<CapellaElement> getOldAvailableElements(CapellaElement element_p) {
    Part currentPart = (Part) element_p;

    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    Collection<Part> parts = ComponentExt.getPartAncestors(currentPart);

    AbstractType abstractType = currentPart.getAbstractType();
    if ((null != abstractType) && ((abstractType instanceof PhysicalComponent) || (abstractType instanceof PhysicalActor))) {
      List<PhysicalComponent> behaviourComps = SystemEngineeringExt.getAllPhysicalComponents((CapellaElement) abstractType);

      // retrieve all Parts from list of physical components
      if (abstractType instanceof PhysicalComponent) {
        for (PhysicalComponent physicalComponent : behaviourComps) {
          PhysicalComponent currentPC = (PhysicalComponent) abstractType;
          if (!(currentPC.getNature().equals(PhysicalComponentNature.BEHAVIOR) && physicalComponent.getNature().equals(PhysicalComponentNature.NODE))
              && !(currentPC.getNature().equals(PhysicalComponentNature.UNSET)) && !(physicalComponent.getNature().equals(PhysicalComponentNature.UNSET))
              && !physicalComponent.equals(currentPC)) {

            getValidDeployablePart(availableElements, parts, physicalComponent);

          }
        }
      } else if (abstractType instanceof PhysicalActor) {
        for (PhysicalComponent physicalComponent : behaviourComps) {
          if (!(physicalComponent.getNature().equals(PhysicalComponentNature.NODE)) && !(physicalComponent.getNature().equals(PhysicalComponentNature.UNSET))) {
            getValidDeployablePart(availableElements, parts, physicalComponent);
          }
        }
      }

    }
    availableElements.removeAll(getCurrentElements(element_p, false));
    return availableElements;
  }

  private void getValidDeployablePart(List<CapellaElement> availableElements, Collection<Part> parts, Component physicalComponent) {
    EList<Partition> representingPartitions = physicalComponent.getRepresentingPartitions();
    for (Partition partition : representingPartitions) {
      if (partition instanceof Part) {
        Part part = (Part) partition;
        // part is not one of the ancestor
        if (!parts.contains(part)) {
          // check if part is deployed (if apply depending on options)
          EList<AbstractDeploymentLink> deployingLinks = part.getDeployingLinks();
          // isMulti deployment allowed or deployedLinks is empty
          if (isMultipleDeploymentAllowed() || deployingLinks.isEmpty()) {
            availableElements.add(part);
          }
        }
      }
    }
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.data.capellacore.CapellaElement,
   *      boolean)
   */
  public List<CapellaElement> getOldCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

    if (element_p instanceof Part) {
      Part pc = (Part) element_p;
      for (AbstractDeploymentLink abstractDeployment : pc.getDeploymentLinks()) {
        if (abstractDeployment instanceof PartDeploymentLink) {
          currentElements.add(abstractDeployment.getDeployedElement());
        }
      }
    }

    return currentElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return CsPackage.Literals.PART;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeatures()
   */
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(CsPackage.Literals.ABSTRACT_DEPLOYMENT_LINK__DEPLOYED_ELEMENT);
  }

  @Override
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    return RefactorDebugger.callAndTestQuery("GetAvailable_Part_DeployedElements__Lib", element_p, getOldAvailableElements(element_p), getEClass(), getClass());//$NON-NLS-1$
  }

  @Override
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    return RefactorDebugger.callAndTestQuery("GetCurrent_Part_DeployedElements", element_p, getOldCurrentElements(element_p, false), getEClass(), getClass());//$NON-NLS-1$
  }

}
