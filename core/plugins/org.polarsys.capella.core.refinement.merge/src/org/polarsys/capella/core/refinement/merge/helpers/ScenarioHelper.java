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
package org.polarsys.capella.core.refinement.merge.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.core.refinement.merge.exception.MergeToolException;
import org.polarsys.capella.core.refinement.merge.messages.MergeMessages;
import org.polarsys.capella.core.refinement.merge.utils.LinkEnum;
import org.polarsys.capella.core.refinement.merge.utils.LinkUtils;
import org.polarsys.capella.core.refinement.merge.utils.CapellaCopier;

/**
 * Helper class for Scenario
 */
public class ScenarioHelper {

  /**
   * Create a new scenario, copying values from an existing ones, if given.
   * @param sc_p scenario to copy
   * @param name_p the new scenario name
   * @return the newly created scenario
   */
  public static Scenario createScenario(final Scenario sc_p, final String name_p) {

    Scenario result;

    if (null == sc_p) { // we create a new scenario "from scratch"
      result = InteractionFactory.eINSTANCE.createScenario();
    } else { // We copy the sc_p scenario.
      result = (Scenario) CapellaCopier.copyObject(sc_p, true);
    }

    if (null != name_p) {
      result.setName(name_p);
    }
    
    return result;
  }

  /**
   * Check whether a given {@link Scenario} has some {@link InteractionUse}s
   * @param sc_p the target {@link Scenario}
   * @return <code>true</code> if yes, <code>false</code> otherwise
   * @throws MergeToolException
   */
  public static boolean hasInteractionUse(Scenario sc_p) throws MergeToolException {
    
    if ( null == sc_p ) {
      throw new MergeToolException(MergeMessages.genericToolError);
     }
    
    boolean result = false;
    
    for (TimeLapse tl : sc_p.getOwnedTimeLapses()) {
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
   * @param sc_p the Scenario
   * @param eobject_p the {@link EObject} to check
   * @return <code>true</code> if yes, <code>false</code> otherwise
   */
  public static boolean isContainedInto(Scenario sc_p, EObject eobject_p) {
    
    EObject container = eobject_p.eContainer();
    
    return ( container != null && container.equals(sc_p) );
  }
  
  /**
   * Check whether any {@link InstanceRole} on a given {@link Scenario} has decomposable elements.
   * @param sc_p
   * @return
   * @throws MergeToolException
   */
  public static boolean isScenarioHasDecomposedElement(Scenario sc_p) throws MergeToolException {
    
    boolean result = false;
    
    for (InstanceRole ir: sc_p.getOwnedInstanceRoles()) {
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
   * @param ir_p the {@link InstanceRole} to check
   * @return the existing InstanceRole if true, <code>null</code> otherwise
   */
  static public InstanceRole isPartAlreadyRepresented(Scenario sc_p, InstanceRole ir_p) {

    InstanceRole result = null;
    AbstractInstance at =  ir_p.getRepresentedInstance();
    
    for (InstanceRole ir: sc_p.getOwnedInstanceRoles()) {
      if ( ir.getRepresentedInstance().equals(at) ) {
        result = ir;
        break;
      }
    }
    
    return result;
  }
  
  /**
   * Return all the refined {@link Scenario} for a given {@link Scenario}
   * @param sc_p the target {@link Scenario}
   * @param includeRoot_p, should the target {@link Scenario} must be included in results
   * @return 
   */
  static public List<Scenario> getRefinedScenarii(Scenario sc_p, boolean includeRoot_p) {
    
    ArrayList<Scenario> scenarii = new ArrayList<Scenario>();
    
    if ( true == includeRoot_p ) {
      scenarii.add(sc_p);
    }

    List<Scenario> nextLevel = getNextLevelRefinedScenarii(sc_p);
    
    while (!nextLevel.isEmpty()) {
        scenarii.addAll(nextLevel);
        
        List<Scenario> list = new ArrayList<Scenario>();
        for (Scenario sc: nextLevel) {
          list.addAll(getNextLevelRefinedScenarii(sc));
        }
        
        nextLevel.clear();
        nextLevel.addAll(list);
    }

    
    return scenarii;
  }

  /**
   * Return the direct refined {@link Scenario} for a given {@link Scenario}
   * @param sc_p the target {@link Scenario}
   * @return a {@link List} containing the refined {@link Scenario} found on the next sub-level, an empty one whether the {@link Scenario} is not refined.
   */
  @SuppressWarnings("unchecked")
  static public List<Scenario> getNextLevelRefinedScenarii(Scenario sc_p) {
    
    ArrayList<Scenario> result = new ArrayList<Scenario>();
    result.addAll(
        (Collection<? extends Scenario>) LinkUtils.getIncomingLinkTargets(sc_p, LinkEnum.REFINEMENT_LINK)
    );
    
    return result;
    
  } 
}
