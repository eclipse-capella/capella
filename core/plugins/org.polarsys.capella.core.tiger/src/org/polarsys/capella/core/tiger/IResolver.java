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
package org.polarsys.capella.core.tiger;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 */
public interface IResolver {
  
  /**
   * Performs a selection of one or some elements from the given items_p
   * @param source_p the element which require the selection
   * @param items_p a list of choices
   * @param title_p a short description for the selection
   * @param message_p a detailed description for the selection
   * @param multipleSelection_p defines if the returned list should contain only one or many elements
   * @param transfo_p the current transfo
   * @return a list which can contains one or many elements according to the multiple_selection_p parameter
   * @throws TransfoException, if an error occurs while the selection
   */
  public List<EObject> resolve(EObject source_p, List<EObject> items_p, 
      final String title_p, final String message_p, boolean multipleSelection_p, 
      ITransfo transfo_p, EObject[] context);
  
  
}
