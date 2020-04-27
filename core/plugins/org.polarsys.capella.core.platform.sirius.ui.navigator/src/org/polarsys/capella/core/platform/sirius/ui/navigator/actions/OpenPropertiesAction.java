/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;

public class OpenPropertiesAction extends BaseSelectionListenerAction {

  public OpenPropertiesAction() {
    super("Open properties");
  }

  @Override
  public void run() {
    IStructuredSelection selection = (IStructuredSelection) getStructuredSelection();
    Object element = selection.getFirstElement();

    if (CapellaResourceHelper.isSemanticElement(element)) {
      CapellaUIPropertiesPlugin.getDefault().openWizard((EObject) element);
    }
  }

  @Override
  protected boolean updateSelection(IStructuredSelection selection) {
    boolean result = false;

    if (!selection.isEmpty()) {
      result = (CapellaResourceHelper.isSemanticElements(selection.toList())) ? true : false;
    }

    return result;
  }
}
