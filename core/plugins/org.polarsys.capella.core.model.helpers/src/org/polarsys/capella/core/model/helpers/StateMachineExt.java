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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 * 
 */
public class StateMachineExt {

  /**
   * get all the States and Modes from current Component
   * @param ele_p
   * @param comp_p
   * @return
   */
  public static List<CapellaElement> getElementsFromBlockArchitecture(Component comp_p, CapellaElement ele_p) {
    List<CapellaElement> result = new ArrayList<CapellaElement>(1);
    
    // collect all the modes form Component -> StateMachin 
    if (comp_p != null) {
      EList<StateMachine> ownedStateMachines = comp_p.getOwnedStateMachines();
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

}
