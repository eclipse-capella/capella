/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;

/**
 * Allow to select an object in a viewer.
 */
@Deprecated
public class NavigateAction extends Action {
  /**
   * Element to select.
   */
  private EObject element;
  /**
   * Viewer that selects the model element.
   */
  private StructuredViewer viewer;

  /**
   * Constructor.
   * 
   * @param element
   * @param viewer
   */
  public NavigateAction(EObject element, StructuredViewer viewer) {
    this.element = element;
    this.viewer = viewer;
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    IStructuredSelection selection = new StructuredSelection(element);
    viewer.setSelection(selection, true);
  }
}
