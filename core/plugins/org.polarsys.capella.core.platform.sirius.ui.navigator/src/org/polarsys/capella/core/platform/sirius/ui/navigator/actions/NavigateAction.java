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
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;

/**
 */
public/**
* Allow to select an object in a viewer.
*/
class NavigateAction extends Action {
  /**
   * Element to select.
   */
  private EObject _element;
  /**
   * Viewer that selects the model element.
   */
  private StructuredViewer _viewer;

  /**
   * Constructor.
   * @param element_p
   * @param viewer_p
   */
  public NavigateAction(EObject element_p, StructuredViewer viewer_p) {
    _element = element_p;
    _viewer = viewer_p;
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    _viewer.setSelection(new StructuredSelection(_element), true);
  }
}
