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
   * @param capabilityUseCase_p the capability in which the component will be involved in
   * @param component_p the involved component
   */
  public static void addInvolvedComponent(AbstractCapability capability_p, Component component_p) {
    if (capability_p instanceof Capability) {
      CapabilityExt.addInvolvedComponent((Capability) capability_p, component_p);
    } else if (capability_p instanceof CapabilityRealization) {
      CapabilityRealizationExt.addInvolvedComponent((CapabilityRealization) capability_p, component_p);
    }
  }

  /**
   * This method retrieves all the scenarios from the model (contained by the given AbstractCapability).
   * @param currentElement_p
   * @return List<Scenario>
   */
  public static List<Scenario> getAllScenarios(AbstractCapability currentElement_p) {
    List<Scenario> scList = new ArrayList<Scenario>();

    Set<EObject> scSet = EObjectExt.getAll(currentElement_p, InteractionPackage.Literals.SCENARIO);
    if (scSet != null) {
      for (EObject obj : scSet) {
        scList.add((Scenario) obj);
      }
    }
    return scList;
  }

  /**
   * This method retrieves the contributing components.
   * @param capability_p the capability whose contributing components will be retrieved
   * @return the contributing components
   */
  public static List<Component> getInvolvedComponents(AbstractCapability capability_p) {
    List<Component> involvedComponents = new ArrayList<Component>();

    if (capability_p instanceof Capability) {
      involvedComponents.addAll(CapabilityExt.getInvolvedComponents((Capability) capability_p));
    } else if (capability_p instanceof CapabilityRealization) {
      involvedComponents.addAll(CapabilityRealizationExt.getInvolvedComponents((CapabilityRealization) capability_p));
    }

    return involvedComponents;
  }

  /**
   * This method retrieves the AspectPkg containing the given capability.
   * @param capability_p the capability whose container AspectPkg will be retrieved
   * @return the owner AspectPkg
   */
  public static AbstractCapabilityPkg getOwnerAbstractCapabilityPkg(AbstractCapability capability_p) {
    AbstractCapabilityPkg aspectPkg = null;

    if (capability_p != null) {
      aspectPkg = (AbstractCapabilityPkg) EcoreUtil2.getFirstContainer(capability_p, CapellacommonPackage.Literals.ABSTRACT_CAPABILITY_PKG);
    }

    return aspectPkg;
  }

  /**
   * This method removes an involved actor.
   * @param capability_p the capability in which the component will not be involved in
   * @param component_p the non involved component
   */
  public static void removeInvolvedComponent(AbstractCapability capability_p, Component component_p) {
    if (capability_p instanceof Capability) {
      CapabilityExt.removeInvolvedComponent((Capability) capability_p, component_p);
    } else if (capability_p instanceof CapabilityRealization) {
      CapabilityRealizationExt.removeInvolvedComponent((CapabilityRealization) capability_p, component_p);
    }
  }
}
