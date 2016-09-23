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
package org.polarsys.capella.core.refinement.processor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;

/**
 */
public class DynamicRealizationContributionProcessor extends UpdateRealizationContributionProcessor {

  /**
   * Default constructor
   */
  public DynamicRealizationContributionProcessor() {
    super(Kind.DYNAMIC);
  }

  /**
   * Constructor
   *
   * @param context the {@link NamedElement} on which the processing will be applied
   */
  public DynamicRealizationContributionProcessor(NamedElement context) {
    super(Kind.DYNAMIC, context);
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IRefinementPlug#getName()
   */
  public Object getName() {
    return "realization contribution update based on dynamic informations"; //$NON-NLS-1$
  }

  /**
   * 
   * @param currentCapability
   * @param target
   */
  @Override
  protected void updateInvolvements(AbstractCapability currentCapability, EClass target) {
    AbstractCapability updatedCapability = null;
    List<Component> involvedCpntSet = new ArrayList<Component>();

    /**
     * retrieve the realization linked to the current realization
     */
    for (CapellaElement elt : RefinementLinkExt.getRefinementRelatedSourceElements(currentCapability, InteractionPackage.Literals.ABSTRACT_CAPABILITY)) {
      if (EcoreUtil2.isContainedBy(elt, target)) {
        updatedCapability = (AbstractCapability) elt;
      }
    }

    if (updatedCapability != null) {
      /**
       * collects the components involved in all the scenario of the current use case
       */
      for (Scenario sc : updatedCapability.getOwnedScenarios()) {
        List<Component> cpntSet = ScenarioExt.getOwnedComponents(sc);
        for (Component cpnt : cpntSet) {
          if (!involvedCpntSet.contains(cpnt)) {
            involvedCpntSet.add(cpnt);
          }
        }
      }

      /**
       * updates the current use case according to the involved components
       */
      for (Component cpnt : involvedCpntSet) {
        if (!AbstractCapabilityExt.getInvolvedComponents(updatedCapability).contains(cpnt)) {
          AbstractCapabilityExt.addInvolvedComponent(updatedCapability, cpnt);
        }
      }

      /**
       * removes the non involved components from the current use case
       */
      for (Component cpnt : AbstractCapabilityExt.getInvolvedComponents(updatedCapability)) {
        if (!involvedCpntSet.contains(cpnt)) {
          AbstractCapabilityExt.removeInvolvedComponent(updatedCapability, cpnt);
        }
      }
    }
  }
}
