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
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacommon.FinalState;
import org.polarsys.capella.core.data.capellacommon.Mode;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;

public class StateExt {

  /**
   * Returns the first region of the given state and create one if none
   */
  public static Region getPrimaryRegion(State state) {
    return getPrimaryRegion(state, true);
  }

  /**
   * Returns the first region of the given state and create one if required
   */
  public static Region getPrimaryRegion(State state, boolean create) {
    if (state.getOwnedRegions().isEmpty() && create) {
      Region region = CapellacommonFactory.eINSTANCE.createRegion(NamingConstants.Region_DefaultRegion);
      state.getOwnedRegions().add(region);
      CapellaElementExt.creationService(region);
    }
    if (state.getOwnedRegions().isEmpty()) {
      return null;
    }
    return state.getOwnedRegions().get(0);
  }

  /**
   * Returns whether the given object is a pure State (a State with no special kinds like Final State or Mode). 
   */
  public static boolean isStrictState(EObject state) {
    return (state instanceof State) && !(state instanceof Mode) && !(state instanceof FinalState);
  }

  /**
   * Returns whether the given object is a Mode (a State with kind Mode). 
   */
  public static boolean isMode(EObject state) {
    return (state instanceof Mode);
  }

  /**
   * Returns whether the given object is strictly a State or a Mode (it excludes other special kind Final State)
   */
  public static boolean isStrictModeState(EObject state) {
    return ((state instanceof State) || (state instanceof Mode)) && !(state instanceof FinalState);
  }
  
  public static List<Object> getActiveElements(State state) {
    List<Object> result = new ArrayList<Object>();
    Collection<Setting> inverseReferences = CapellaElementExt.getInverseReferencesOfEObject(state);
    for (Setting setting : inverseReferences) {
      EObject eObject = setting.getEObject();
      if (eObject != null) {
        // add to result only Function, Capability,OperationalCapability  and FunctionalChain
        if (eObject instanceof AbstractFunction 
            || eObject instanceof AbstractCapability || eObject instanceof OperationalCapability 
            || eObject instanceof FunctionalChain) { 
          result.add(eObject);
        }
      }
    }
    return result;
  }
  
  public static List<Object> getRecursiveSubStates(State state) {
    List<Object> result = new ArrayList<Object>();
    for (Region region : state.getOwnedRegions()) {
      List<AbstractState> rStates = region.getOwnedStates();
      for(AbstractState s : rStates) {
        result.add(s);
        result.addAll(getRecursiveSubStates((State)s));
      }
    }
    return result;
  }
}
