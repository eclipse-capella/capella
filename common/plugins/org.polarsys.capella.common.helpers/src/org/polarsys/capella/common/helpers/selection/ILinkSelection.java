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

package org.polarsys.capella.common.helpers.selection;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;


/**
 * This interface is used for selection wizard of links. The context is the following:
 * From a Capella Diagram, a user selects an Element (Container or Node) and wants to display links that rely this element
 * A List of available links must be proposed in a selection wizard. The links are ordered in the selection wizard according to their targets
 * 
 */
public interface ILinkSelection {
  
  /**
   * @return the list of available EClass as target of the links
   */
  public List<EClass> getAvailableTargetEClass();
  
  /**
   * This method must be implemented
   * @param object a given link (extends <code>EObject</code>)
   *        
   * @param context the context of the link
   *        the context is a Node Object from which we want to know all the links that relies this element
   * @return the target to be displayed in the selection tree wizard
   *         is often the source or the target (or their container) of the link
   */
  public EObject getDisplayedTarget (EObject object, EObject context);

  /**
   * This method must be implemented
   * @return the EClass of the link that should be selected
   */
  public EClass getEClass();
  
}
