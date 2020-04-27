/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.tiger;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 */
public interface IResolver {
  
  /**
   * Performs a selection of one or some elements from the given items
   * @param source the element which require the selection
   * @param items a list of choices
   * @param title a short description for the selection
   * @param message a detailed description for the selection
   * @param multipleSelection defines if the returned list should contain only one or many elements
   * @param transfo the current transfo
   * @param context
   * @return a list which can contains one or many elements according to the multiple_selection parameter
   * @throws TransfoException, if an error occurs while the selection
   */
  public List<EObject> resolve(EObject source, List<EObject> items, 
      final String title, final String message, boolean multipleSelection, 
      ITransfo transfo, EObject[] context);
  
  
}
