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
package org.polarsys.capella.core.refinement.merge.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.core.model.handler.helpers.HoldingResourceHelper;
import org.polarsys.capella.core.refinement.merge.exception.MergeToolException;
import org.polarsys.capella.core.refinement.merge.messages.MergeMessages;
import org.polarsys.capella.core.refinement.merge.utils.CapellaCopier;
import org.polarsys.capella.core.refinement.merge.utils.LinkEnum;
import org.polarsys.capella.core.refinement.merge.utils.LinkUtils;

/**
 * Helper class for Scenario
 */
public class ScenarioHelper {

  /**
   * Create a new scenario, copying values from an existing ones, if given.
   * @param sc scenario to copy
   * @param name the new scenario name
   * @return the newly created scenario
   */
  public static Scenario createScenario(final Scenario sc, final String name) {

    Scenario result;

    if (null == sc) { // we create a new scenario "from scratch"
      result = InteractionFactory.eINSTANCE.createScenario();
    } else { // We copy the sc scenario.
      result = (Scenario) CapellaCopier.copyObject(sc, true);
    }

    if (null != name) {
      result.setName(name);
    }

    HoldingResourceHelper.attachToHoldingResource(result, HoldingResourceHelper.getHoldingResource(TransactionHelper.getEditingDomain(sc)));

    return result;
  }

  /**
   * Check whether a given {@link Scenario} has some {@link InteractionUse}s
   * @param sc the target {@link Scenario}
   * @return <code>true</code> if yes, <code>false</code> otherwise
   * @throws MergeToolException
   */
  public static boolean hasInteractionUse(Scenario sc) throws MergeToolException {
    
    if ( null == sc ) {
      throw new MergeToolException(MergeMessages.genericToolError);
     }
    
    boolean result = false;
    
    for (TimeLapse tl : sc.getOwnedTimeLapses()) {
      if (
          tl.eClass() == InteractionPackage.Literals.INTERACTION_USE
      ) {
        result = true;
        break;   
      }
    }
    
    return result;
  }
  
  /**
   * check whether a given Element is contained into a given {@link Scenario}.
   * @param sc the Scenario
   * @param eobject the {@link EObject} to check
   * @return <code>true</code> if yes, <code>false</code> otherwise
   */
  public static boolean isContainedInto(Scenario sc, EObject eobject) {
    
    EObject container = eobject.eContainer();
    
    return ( container != null && container.equals(sc) );
  }
  
  /**
   * Check whether any {@link InstanceRole} on a given {@link Scenario} has decomposable elements.
   * @param sc
   * @return
   * @throws MergeToolException
   */
  public static boolean isScenarioHasDecomposedElement(Scenario sc) throws MergeToolException {
    
    boolean result = false;
    
    for (InstanceRole ir: sc.getOwnedInstanceRoles()) {
      if ( !MergeHelper.getDecomposedIR(ir).isEmpty() ) {
        result = true;
        break;
      }
    }
    
    return result;
  }
  
  /**
   * Check, if for a given {@link InstanceRole}, its associated {@link AbstractInstance} has
   * been already represented on this {@link Scenario}
   * @param ir the {@link InstanceRole} to check
   * @return the existing InstanceRole if true, <code>null</code> otherwise
   */
  static public InstanceRole isPartAlreadyRepresented(Scenario sc, InstanceRole ir) {

    InstanceRole result = null;
    AbstractInstance at =  ir.getRepresentedInstance();
    
    for (InstanceRole role : sc.getOwnedInstanceRoles()) {
      if ( role.getRepresentedInstance().equals(at) ) {
        result = role;
        break;
      }
    }
    
    return result;
  }
  
  /**
   * Return all the refined {@link Scenario} for a given {@link Scenario}
   * @param sc the target {@link Scenario}
   * @param includeRoot, should the target {@link Scenario} must be included in results
   * @return 
   */
  static public List<Scenario> getRefinedScenarii(Scenario sc, boolean includeRoot) {
    
    ArrayList<Scenario> scenarii = new ArrayList<Scenario>();
    
    if ( true == includeRoot ) {
      scenarii.add(sc);
    }

    List<Scenario> nextLevel = getNextLevelRefinedScenarii(sc);
    
    while (!nextLevel.isEmpty()) {
        scenarii.addAll(nextLevel);
        
        List<Scenario> list = new ArrayList<Scenario>();
        for (Scenario scenario: nextLevel) {
          list.addAll(getNextLevelRefinedScenarii(scenario));
        }
        
        nextLevel.clear();
        nextLevel.addAll(list);
    }

    
    return scenarii;
  }

  /**
   * Return the direct refined {@link Scenario} for a given {@link Scenario}
   * @param sc the target {@link Scenario}
   * @return a {@link List} containing the refined {@link Scenario} found on the next sub-level, an empty one whether the {@link Scenario} is not refined.
   */
  @SuppressWarnings("unchecked")
  static public List<Scenario> getNextLevelRefinedScenarii(Scenario sc) {
    
    ArrayList<Scenario> result = new ArrayList<Scenario>();
    result.addAll(
        (Collection<? extends Scenario>) LinkUtils.getIncomingLinkTargets(sc, LinkEnum.REFINEMENT_LINK)
    );
    
    return result;
    
  } 
}
