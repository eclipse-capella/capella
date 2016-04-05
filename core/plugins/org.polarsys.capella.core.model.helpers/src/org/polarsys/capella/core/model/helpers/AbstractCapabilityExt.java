/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.helpers.ctx.services.CapabilityExt;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;

/**
 * AbstractCapability helpers
 */
public class AbstractCapabilityExt {

  /**
   * This method adds an involved actor.
   * @param capabilityUseCase the capability in which the component will be involved in
   * @param component the involved component
   */
  public static void addInvolvedComponent(AbstractCapability capability, Component component) {
    if (capability instanceof Capability) {
      CapabilityExt.addInvolvedComponent((Capability) capability, component);
    } else if (capability instanceof CapabilityRealization) {
      CapabilityRealizationExt.addInvolvedComponent((CapabilityRealization) capability, component);
    }
  }

  /**
   * This method retrieves all the scenarios from the model (contained by the given AbstractCapability).
   * @param currentElement
   * @return List<Scenario>
   */
  public static List<Scenario> getAllScenarios(AbstractCapability currentElement) {
    List<Scenario> scList = new ArrayList<Scenario>();

    Set<EObject> scSet = EObjectExt.getAll(currentElement, InteractionPackage.Literals.SCENARIO);
    if (scSet != null) {
      for (EObject obj : scSet) {
        scList.add((Scenario) obj);
      }
    }
    return scList;
  }

  /**
   * This method retrieves the contributing components.
   * @param capability the capability whose contributing components will be retrieved
   * @return the contributing components
   */
  public static List<Component> getInvolvedComponents(AbstractCapability capability) {
    List<Component> involvedComponents = new ArrayList<Component>();

    if (capability instanceof Capability) {
      involvedComponents.addAll(CapabilityExt.getInvolvedComponents((Capability) capability));
    } else if (capability instanceof CapabilityRealization) {
      involvedComponents.addAll(CapabilityRealizationExt.getInvolvedComponents((CapabilityRealization) capability));
    }

    return involvedComponents;
  }

  /**
   * This method retrieves the AspectPkg containing the given capability.
   * @param capability the capability whose container AspectPkg will be retrieved
   * @return the owner AspectPkg
   */
  public static AbstractCapabilityPkg getOwnerAbstractCapabilityPkg(AbstractCapability capability) {
    AbstractCapabilityPkg aspectPkg = null;

    if (capability != null) {
      aspectPkg = (AbstractCapabilityPkg) EcoreUtil2.getFirstContainer(capability, CapellacommonPackage.Literals.ABSTRACT_CAPABILITY_PKG);
    }

    return aspectPkg;
  }

  /**
   * This method removes an involved actor.
   * @param capability the capability in which the component will not be involved in
   * @param component the non involved component
   */
  public static void removeInvolvedComponent(AbstractCapability capability, Component component) {
    if (capability instanceof Capability) {
      CapabilityExt.removeInvolvedComponent((Capability) capability, component);
    } else if (capability instanceof CapabilityRealization) {
      CapabilityRealizationExt.removeInvolvedComponent((CapabilityRealization) capability, component);
    }
  }
}
