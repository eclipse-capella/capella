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
package org.polarsys.capella.common.menu.dynamic;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Shell;

import org.polarsys.kitalpha.emde.model.Element;

/**
 * Base class to implement action dealing with a model element.
 */
public abstract class AbstractModelElementAction extends AbstractNavigatorAction {
  /**
   * Managed model element.
   */
  private Element _modelElement;

  /**
   * Constructor.
   * @param shell_p
   * @param selectionProvider_p
   */
  protected AbstractModelElementAction(Shell shell_p, ISelectionProvider selectionProvider_p) {
    super(shell_p, selectionProvider_p);
    // Set action id based on class name.
    setId(getClass().getName());
  }

  /**
   * @see org.polarsys.capella.common.mdsofa.rootasset.ui.workbench.action.navigator.AbstractNavigatorAction#setSelectedElement(java.lang.Object)
   */
  @Override
  public void setSelectedElement(Object object_p) {
    if (object_p instanceof Element) {
      _modelElement = (Element) object_p;
    }
  }

  /**
   * Get the editing model element.
   * @return the modelElement
   */
  public Element getModelElement() {
    return _modelElement;
  }

  /**
   * Execute the command.
   * @param editingDomain_p
   * @param command_p
   */
  protected void executeCommand(AdapterFactoryEditingDomain editingDomain_p, Command command_p) {
    editingDomain_p.getCommandStack().execute(command_p);
  }
}
