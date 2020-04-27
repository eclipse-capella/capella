/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
   * @param combinedFragment
   * @param scenario
   */
  public static boolean isEmpty(CombinedFragment combinedFragment, Scenario scenario) {
    InteractionFragment start = combinedFragment.getStart();
    InteractionFragment finish = combinedFragment.getFinish();
    List<InteractionFragment> interactionFragments = scenario.getOwnedInteractionFragments();
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
   * @param combinedFragment
   * @param scenario
   */
  public static List<InteractionOperand> getOwnedOperands(CombinedFragment combinedFragment, Scenario scenario) {
    List<InteractionOperand> result = new ArrayList<InteractionOperand>();

    // protection
    if ((scenario == null) || (combinedFragment == null)) {
      return result;
    }

    InteractionFragment start = combinedFragment.getStart();
    InteractionFragment finish = combinedFragment.getFinish();
    List<InteractionFragment> interactionFragments = scenario.getOwnedInteractionFragments();
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
   * @param abstractFragment
   * @param scenario
   */
  public static List<InstanceRole> getCoveredInstanceRoles(AbstractFragment abstractFragment, Scenario scenario) {
    List<InstanceRole> coveredInstanceRoles = new ArrayList<InstanceRole>();

    // protection
    if ((scenario == null) || (abstractFragment == null)) {
      return coveredInstanceRoles;
    }

    FragmentEnd srcStart = (FragmentEnd) abstractFragment.getStart();
    for (InstanceRole srcInstanceRole : srcStart.getCoveredInstanceRoles()) {
      for (CapellaElement tgtInstanceRole : CapellaElementExt.getRefinementSrcElements(srcInstanceRole, InteractionPackage.Literals.INSTANCE_ROLE, scenario)) {
        if ((tgtInstanceRole != null) && !coveredInstanceRoles.contains(tgtInstanceRole)) {
          coveredInstanceRoles.add((InstanceRole) tgtInstanceRole);
        }
      }
    }

    FragmentEnd srcFinish = (FragmentEnd) abstractFragment.getFinish();
    for (InstanceRole srcInstanceRole : srcFinish.getCoveredInstanceRoles()) {
      for (CapellaElement tgtInstanceRole : CapellaElementExt.getRefinementSrcElements(srcInstanceRole, InteractionPackage.Literals.INSTANCE_ROLE, scenario)) {
        if ((tgtInstanceRole != null) && !coveredInstanceRoles.contains(tgtInstanceRole)) {
          coveredInstanceRoles.add((InstanceRole) tgtInstanceRole);
        }
      }
    }

    return coveredInstanceRoles;
  }

  /**
   * @param fragmentEnd1
   * @param fragmentEnd1
   * @param scenario1
   */
  public static List<InstanceRole> getCoveredInstanceRoles(FragmentEnd fragmentStart, FragmentEnd fragmentFinish, Scenario scenario) {
    List<InstanceRole> coveredInstanceRoles = new ArrayList<InstanceRole>();

    // protection
    if ((scenario == null) || (fragmentStart == null) || (fragmentFinish == null)) {
      return coveredInstanceRoles;
    }

    List<InteractionFragment> interactionFragments = scenario.getOwnedInteractionFragments();
    int start = interactionFragments.indexOf(fragmentStart);
    int finish = interactionFragments.indexOf(fragmentFinish);
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

  public static Set<InstanceRole> getMinimalCoveredInstanceRoles(CombinedFragment fragment) {
    Set<InstanceRole> coveredInstanceRoles = new HashSet<InstanceRole>();

    Scenario scenario = (Scenario) fragment.eContainer();

    FragmentEnd fragStart = (FragmentEnd) fragment.getStart();
    FragmentEnd fragFinish = (FragmentEnd) fragment.getFinish();
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
        if (!fragment.getReferencedOperands().contains(currentFragment)) {
          coveredInstanceRoles.addAll(currentFragment.getCoveredInstanceRoles());
        }
      }
    }

    return coveredInstanceRoles;
  }
}
