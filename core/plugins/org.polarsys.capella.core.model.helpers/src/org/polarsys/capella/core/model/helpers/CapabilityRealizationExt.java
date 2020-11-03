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

package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionInvolvement;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponent;

/**
 * The CapabilityRealizationUseCase helpers.
 */
public class CapabilityRealizationExt {

  /**
   * This method adds an involved component.
   *
   * @param capability
   *          the capability in which the component will be involved in
   * @param component
   *          the involved component
   */
  public static void addInvolvedComponent(CapabilityRealization capability, Component component) {
    if (component instanceof CapabilityRealizationInvolvedElement
        && !capability.getInvolvedComponents().contains((InvolvedElement) component)) {
      CapabilityRealizationInvolvement involvementLnk = CapellacommonFactory.eINSTANCE
          .createCapabilityRealizationInvolvement();
      capability.getOwnedCapabilityRealizationInvolvements().add(involvementLnk);
      involvementLnk.setInvolved((CapabilityRealizationInvolvedElement) component);
    }
  }

  /**
   * This method removes an involved component.
   * 
   * @param capability
   *          the capability in which the component will not be involved in
   * @param component
   *          the non involved component
   */
  public static void removeInvolvedComponent(CapabilityRealization capability, Component component) {
    if (component instanceof CapabilityRealizationInvolvedElement) {
      CapabilityRealizationInvolvement capabilityInvolvement = null;
      for (CapabilityRealizationInvolvement involvement : capability.getOwnedCapabilityRealizationInvolvements()) {
        if (component.equals(involvement.getInvolvedCapabilityRealizationInvolvedElement())) {
          capabilityInvolvement = involvement;
        }
      }
      if (capabilityInvolvement != null) {
        capabilityInvolvement.destroy();
      }
    }
  }

  /**
   * Return list of upper CapabilityRealization linked by RefinementLink from CapabilityRealization given in parameter
   */
  public static List<CapabilityRealization> getParentsCapabilityRealizationLinked(CapabilityRealization srcCapa) {
    List<CapabilityRealization> upperCapaList = new ArrayList<CapabilityRealization>();

    for (CapellaElement upperCapa : RefinementLinkExt.getRefinementRelatedTargetElements(srcCapa,
        InteractionPackage.Literals.ABSTRACT_CAPABILITY)) {
      if (upperCapa instanceof CapabilityRealization) {
        upperCapaList.add((CapabilityRealization) upperCapa);
        upperCapaList
            .addAll(CapabilityRealizationExt.getParentsCapabilityRealizationLinked((CapabilityRealization) upperCapa));
      }
    }
    return upperCapaList;
  }

  public static List<LogicalComponent> retrieveLcInvolvedByUpperCapabilityRealization(AbstractCapability currentCapa) {
    List<LogicalComponent> involvedLcByUpperCapa = new ArrayList<LogicalComponent>();

    if (currentCapa != null) {
      for (CapabilityRealization upperCapa : CapabilityRealizationExt
          .getParentsCapabilityRealizationLinked((CapabilityRealization) currentCapa)) {
        for (CapabilityRealizationInvolvedElement currentLc : upperCapa.getInvolvedComponents()) {
          if (currentLc instanceof LogicalComponent && !involvedLcByUpperCapa.contains(currentLc)) {
            involvedLcByUpperCapa.add((LogicalComponent) currentLc);
          }
        }
      }
    }
    return involvedLcByUpperCapa;
  }

  public static List<PhysicalComponent> retrievePcInvolvedByUpperCapabilityRealization(AbstractCapability currentCapa) {
    List<PhysicalComponent> involvedPcByUpperCapa = new ArrayList<PhysicalComponent>();

    if (currentCapa != null) {
      for (CapabilityRealization upperCapa : CapabilityRealizationExt
          .getParentsCapabilityRealizationLinked((CapabilityRealization) currentCapa)) {
        for (CapabilityRealizationInvolvedElement currentPc : upperCapa.getInvolvedComponents()) {
          if (currentPc instanceof PhysicalComponent && !involvedPcByUpperCapa.contains(currentPc)) {
            involvedPcByUpperCapa.add((PhysicalComponent) currentPc);
          }
        }
      }
    }
    return involvedPcByUpperCapa;
  }

  /**
   * used in context
   * 
   * @param context
   * @param preSource
   * @param preTarget
   * @return
   */
  public static boolean isComponentTargetAvailableForCapInvolvement(EObject context, EObject preSource,
      EObject preTarget) {
    if ((null == preSource) || (null == preTarget)) {
      return false;
    }

    if (preSource instanceof Capability) {
      Capability cap = (Capability) preSource;
      EList<CapabilityInvolvement> ownedActInvol = cap.getOwnedCapabilityInvolvements();
      if (!ownedActInvol.isEmpty()) {
        if (ownedActInvol.contains(preTarget)) {
          return false;
        }
      }
    } else if (preSource instanceof Mission) {
      Mission mis = (Mission) preSource;
      EList<MissionInvolvement> ownedActInvol = mis.getOwnedMissionInvolvements();
      if (!ownedActInvol.isEmpty()) {
        if (ownedActInvol.contains(preTarget)) {
          return false;
        }
      }
    } else if (preSource instanceof CapabilityRealization) {
      CapabilityRealization capReal = (CapabilityRealization) preSource;
      EList<CapabilityRealizationInvolvement> capRealization = capReal.getOwnedCapabilityRealizationInvolvements();
      for (CapabilityRealizationInvolvement element : capRealization) {
        InvolvedElement involved = element.getInvolved();
        if (null != involved && involved.equals(preTarget)) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * This method retrieves all the capability realizations from the model.
   * 
   * @param currentElement
   * @return List<CapabilityRealization>
   */
  public static List<CapabilityRealization> getAllCapabilityRealizations(BlockArchitecture architecture) {
    List<CapabilityRealization> result = new ArrayList<CapabilityRealization>();
    if (null != architecture) {
      Set<EObject> capabilityRealizations = EObjectExt.getAll(architecture, LaPackage.Literals.CAPABILITY_REALIZATION);
      for (EObject obj : capabilityRealizations) {
        result.add((CapabilityRealization) obj);
      }
    }
    return result;
  }
}
