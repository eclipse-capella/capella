/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.ui.menu.dynamic;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Shell;

/**
 * Base class to implement action dealing with a model element.
 */
public abstract class AbstractModelElementAction extends AbstractNavigatorAction {
  /**
   * Managed model element.
   */
  private EObject _modelElement;

  /**
   * Constructor.
   * @param shell
   * @param selectionProvider
   */
  protected AbstractModelElementAction(Shell shell, ISelectionProvider selectionProvider) {
    super(shell, selectionProvider);
    // Set action id based on class name.
    setId(getClass().getName());
  }

  /**
   * @see org.polarsys.capella.common.mdsofa.rootasset.ui.workbench.action.navigator.AbstractNavigatorAction#setSelectedElement(java.lang.Object)
   */
  @Override
  public void setSelectedElement(Object object) {
    if (object instanceof EObject) {
      _modelElement = (EObject) object;
    }
  }

  /**
   * Get the editing model element.
   * @return the modelElement
   */
  public EObject getModelElement() {
    return _modelElement;
  }

  /**
   * Execute the command.
   * @param editingDomain
   * @param command
   */
  protected void executeCommand(AdapterFactoryEditingDomain editingDomain, Command command) {
    editingDomain.getCommandStack().execute(command);
  }
}
