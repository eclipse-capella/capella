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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.AbstractFragment;
import org.polarsys.capella.core.data.interaction.CombinedFragment;
import org.polarsys.capella.core.data.interaction.FragmentEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionOperand;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 */
public class AbstractFragmentExt {

  /**
   * @param combinedFragment_p
   * @param scenario_p
   */
  public static boolean isEmpty(CombinedFragment combinedFragment_p, Scenario scenario_p) {
    InteractionFragment start = combinedFragment_p.getStart();
    InteractionFragment finish = combinedFragment_p.getFinish();
    List<InteractionFragment> interactionFragments = scenario_p.getOwnedInteractionFragments();
    int startIndex = interactionFragments.indexOf(start);
    int finishIndex = interactionFragments.indexOf(finish);

    for (InteractionFragment fragment : interactionFragments.subList(startIndex, finishIndex + 1)) {
      if (fragment instanceof AbstractEnd) {
        return false;
      }
    }

    return true;
  }

  /**
   * @param combinedFragment_p
   * @param scenario_p
   */
  public static List<InteractionOperand> getOwnedOperands(CombinedFragment combinedFragment_p, Scenario scenario_p) {
    List<InteractionOperand> result = new ArrayList<InteractionOperand>();

    // protection
    if ((scenario_p == null) || (combinedFragment_p == null)) {
      return result;
    }

    InteractionFragment start = combinedFragment_p.getStart();
    InteractionFragment finish = combinedFragment_p.getFinish();
    List<InteractionFragment> interactionFragments = scenario_p.getOwnedInteractionFragments();
    int startIndex = interactionFragments.indexOf(start);
    int finishIndex = interactionFragments.indexOf(finish);

    for (InteractionFragment fragment : interactionFragments.subList(startIndex, finishIndex + 1)) {
      if (fragment instanceof InteractionOperand) {
        result.add((InteractionOperand) fragment);
      }
    }

    return result;
  }

  /**
   * @param abstractFragment_p
   * @param scenario_p
   */
  public static List<InstanceRole> getCoveredInstanceRoles(AbstractFragment abstractFragment_p, Scenario scenario_p) {
    List<InstanceRole> coveredInstanceRoles = new ArrayList<InstanceRole>();

    // protection
    if ((scenario_p == null) || (abstractFragment_p == null)) {
      return coveredInstanceRoles;
    }

    FragmentEnd srcStart = (FragmentEnd) abstractFragment_p.getStart();
    for (InstanceRole srcInstanceRole : srcStart.getCoveredInstanceRoles()) {
      for (CapellaElement tgtInstanceRole : CapellaElementExt.getRefinementSrcElements(srcInstanceRole, InteractionPackage.Literals.INSTANCE_ROLE, scenario_p)) {
        if ((tgtInstanceRole != null) && !coveredInstanceRoles.contains(tgtInstanceRole)) {
          coveredInstanceRoles.add((InstanceRole) tgtInstanceRole);
        }
      }
    }

    FragmentEnd srcFinish = (FragmentEnd) abstractFragment_p.getFinish();
    for (InstanceRole srcInstanceRole : srcFinish.getCoveredInstanceRoles()) {
      for (CapellaElement tgtInstanceRole : CapellaElementExt.getRefinementSrcElements(srcInstanceRole, InteractionPackage.Literals.INSTANCE_ROLE, scenario_p)) {
        if ((tgtInstanceRole != null) && !coveredInstanceRoles.contains(tgtInstanceRole)) {
          coveredInstanceRoles.add((InstanceRole) tgtInstanceRole);
        }
      }
    }

    return coveredInstanceRoles;
  }

  /**
   * @param start_p
   * @param finish_p
   * @param scenario_p
   */
  public static List<InstanceRole> getCoveredInstanceRoles(FragmentEnd start_p, FragmentEnd finish_p, Scenario scenario_p) {
    List<InstanceRole> coveredInstanceRoles = new ArrayList<InstanceRole>();

    // protection
    if ((scenario_p == null) || (start_p == null) || (finish_p == null)) {
      return coveredInstanceRoles;
    }

    List<InteractionFragment> interactionFragments = scenario_p.getOwnedInteractionFragments();
    int start = interactionFragments.indexOf(start_p);
    int finish = interactionFragments.indexOf(finish_p);
    for (InteractionFragment fragment : interactionFragments.subList(start, finish)) {
      if (fragment instanceof AbstractEnd) {
        InstanceRole instanceRole = ((AbstractEnd) fragment).getCovered();
        if ((instanceRole != null) && !coveredInstanceRoles.contains(instanceRole)) {
          coveredInstanceRoles.add(instanceRole);
        }
      } else if (fragment instanceof FragmentEnd) {
        for (InstanceRole instanceRole : ((FragmentEnd) fragment).getCoveredInstanceRoles()) {
          if ((instanceRole != null) && !coveredInstanceRoles.contains(instanceRole)) {
            coveredInstanceRoles.add(instanceRole);
          }
        }
      }
    }

    return coveredInstanceRoles;
  }

  public static Set<InstanceRole> getMinimalCoveredInstanceRoles(CombinedFragment fragment_p) {
    Set<InstanceRole> coveredInstanceRoles = new HashSet<InstanceRole>();

    Scenario scenario = (Scenario) fragment_p.eContainer();

    FragmentEnd fragStart = (FragmentEnd) fragment_p.getStart();
    FragmentEnd fragFinish = (FragmentEnd) fragment_p.getFinish();
    List<InteractionFragment> interactionFragments = scenario.getOwnedInteractionFragments();
    int start = interactionFragments.indexOf(fragStart);
    int finish = interactionFragments.indexOf(fragFinish);

    int currentIndex = start + 1;
    while (currentIndex < finish) {
      InteractionFragment currentFragment = interactionFragments.get(currentIndex++);
      if (currentFragment instanceof AbstractEnd) {
        AbstractEnd ae = (AbstractEnd) currentFragment;
        if (fragStart.getCoveredInstanceRoles().contains(ae.getCovered())) {
          coveredInstanceRoles.add(ae.getCovered());
        }
      } else {
    	// case of the fragments and the internals use (forgetting the operand CF being tested ...)  
        if (!fragment_p.getReferencedOperands().contains(currentFragment)) {
          coveredInstanceRoles.addAll(currentFragment.getCoveredInstanceRoles());
        }
      }
    }

    return coveredInstanceRoles;
  }
}
