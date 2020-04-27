/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.ef.command.AbstractCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 * 
 */
public class StateMachineExt {

  /**
   * get all the States and Modes from current Component
   * @param ele
   * @param comp
   * @return
   */
  public static List<CapellaElement> getElementsFromBlockArchitecture(Component comp, CapellaElement ele) {
    List<CapellaElement> result = new ArrayList<CapellaElement>(1);

    // collect all the modes form Component -> StateMachin
    if (comp != null) {
      EList<StateMachine> ownedStateMachines = comp.getOwnedStateMachines();
      for (StateMachine stateMachine : ownedStateMachines) {
        TreeIterator<Object> allContents = EcoreUtil.getAllContents(stateMachine, false);
        while (allContents.hasNext()) {
          Object object = allContents.next();
          if (object instanceof State) {
            result.add((CapellaElement) object);
          }
        }
      }
    }

    return result;
  }

  /**
   * get all the Regions for all the State Machines from current Component
   * 
   * @param comp
   * @return List<Region>
   */
  public static List<Region> getAllStateMachinesRegions(Component comp) {
    List<Region> result = new ArrayList<Region>();

    if (comp != null) {
      EList<StateMachine> ownedStateMachines = comp.getOwnedStateMachines();
      for (StateMachine stateMachine : ownedStateMachines) {
        EList<Region> regions = stateMachine.getOwnedRegions();
        result.addAll(regions);
      }
    }
    return result;
  }

  /**
   * get the first Region for all the State Machines from current Component
   * 
   * @param comp
   * @return Region
   */
  public static Region getFirstStateMachinesRegion(Component comp) {
    List<Region> regions = getAllStateMachinesRegions(comp);
    if (!regions.isEmpty()) {
      return regions.get(0);
    }
    return null;
  }
}
