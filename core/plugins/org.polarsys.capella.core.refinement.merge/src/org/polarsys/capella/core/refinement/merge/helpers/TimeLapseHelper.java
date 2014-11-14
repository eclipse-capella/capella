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
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.interaction.CombinedFragment;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionOperand;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.core.refinement.merge.exception.MergeToolException;
import org.polarsys.capella.core.refinement.merge.messages.MergeMessages;

/**
 *
 */
public class TimeLapseHelper {
  
  /** START and FINISH feature on TimeLapse */
  static private List<EStructuralFeature> BOUNDS = null;
  
  /**
   * Return the list of {@link TimeLapse}s of a selected kind contained by a given {@link Scenario}
   * @param sc_p the target {@link Scenario}
   * @param eclass_p the type 
   * @return {@link List} of matching TimeLapse, an empty one otherwise.
   * @throws MergeToolException
   */
  static public List<TimeLapse> getTimeLapseOfType(final Scenario sc_p, final EClass eclass_p) throws MergeToolException {
  
    List<TimeLapse> result = new ArrayList<TimeLapse>();
    
    if (
        null == sc_p ||
        !InteractionPackage.Literals.TIME_LAPSE.isSuperTypeOf(eclass_p)
    ) {
      //TODO more explicit message
      throw new MergeToolException(MergeMessages.genericToolError);
    }
    
    for (TimeLapse tl: sc_p.getOwnedTimeLapses())  {
      if (tl.eClass().equals(eclass_p)) {
        result.add(tl);
      }
    }

    return result;
  }
  
  /**
   * Check if a given {@link InteractionFragment} is used as "bound" of a {@link TimeLapse} contained
   * into a given {@link Scenario}
   * @param sc_p the target {@link Scenario}
   * @param ifrag_p the target {@link InteractionFragment}
   * @return the matching {@link TimeLapse}, whether exists, <code>null</code> otherwise
   */
  public static TimeLapse isAnyTimeLapseUseThisIfrag(final Scenario sc_p, final InteractionFragment ifrag_p) throws MergeToolException {
    
    TimeLapse result = null;
    
    if (!ifrag_p.eContainer().equals(sc_p)) { // The InteractionFragment must be contained into the Scenario
      //TODO more explicit message
      throw new MergeToolException(MergeMessages.genericToolError);
    }
    
    Object target = null;
    for (TimeLapse tl: sc_p.getOwnedTimeLapses()) {
     
      for (EStructuralFeature feature: getBoundFeaturesList()) {
        target = tl.eGet(feature); 
        if (null!= target && target.equals(ifrag_p)) {
          result = tl;
          break;
        } 
      }
      
      if (null != result) {
        break;
      }
      
    }
    
    return result;
  }
  
  /**
   * Check the position of a given {@link InteractionFragment} e.g. is starting or finishing on a {@link TimeLapse}
   * @param tl_p the target {@link TimeLapse}
   * @param ifrag_p the {@link InteractionFragment}
   * @return The feature whether found, <code>null</code> otherwise.
   * @throws MergeToolException
   */
  public static EStructuralFeature returnPositionOn(TimeLapse tl_p, InteractionFragment ifrag_p) throws MergeToolException {
    
    EStructuralFeature result = null;
    
    if (null == tl_p || null == ifrag_p) {
      //TODO more explicit message
      throw new MergeToolException(MergeMessages.genericToolError);
    }
    
    for (EStructuralFeature feature: getBoundFeaturesList()) {
      if (tl_p.eGet(feature).equals(ifrag_p)) {
        result = feature;
        break;
      }
    }
    
    return result;
    
  }
  
  /**
   * return the "owner" of an {@link InteractionOperand}. 
   * @param io_p the {@link InteractionOperand} to check
   * @return <code>null</code> whether not found, the matching {@link CombinedFragment} otherwise.
   */
  public static CombinedFragment getCombinedFragment(InteractionOperand io_p) {
    
    CombinedFragment result = null;
    
    try {
      
      Scenario scenario = (Scenario) io_p.eContainer();
      CombinedFragment current = null;

      List<TimeLapse> combinedFragments = TimeLapseHelper.getTimeLapseOfType(scenario, InteractionPackage.Literals.COMBINED_FRAGMENT);
      for (TimeLapse tl: combinedFragments) {
        current = (CombinedFragment) tl;
        if (current.getReferencedOperands().contains(io_p)) {
          result = current;
          break;
        }
      }
      
    } catch (MergeToolException exception_p) {
      // do nothing
    }
    
    return result;
  }
  
  /**
   * Return the {@link EStructuralFeature}s used as bound for {@link TimeLapse} 
   * @return the {@link List} of corresponding {@link EStructuralFeature}s.
   */
  public static List<EStructuralFeature> getBoundFeaturesList() {
 
    if (null == BOUNDS) {
      BOUNDS = new ArrayList<EStructuralFeature>();
      BOUNDS.add(InteractionPackage.Literals.TIME_LAPSE__START);
      BOUNDS.add(InteractionPackage.Literals.TIME_LAPSE__FINISH);
    }
    
    return BOUNDS;
  }
  
}
