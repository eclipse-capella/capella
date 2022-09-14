/*******************************************************************************
 * Copyright (c) 2018, 2022 THALES GLOBAL SERVICES.
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

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.core.commands.preferences.ui.sirius.DoubleClickBehaviourUtil;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;

public class OpenAction extends BaseSelectionListenerAction {

  public OpenAction() {
    super(Messages.OpenActionLabel);
  }

  @Override
  public void run() {
    IStructuredSelection selection = getStructuredSelection();
    if (!selection.isEmpty()) {
      Iterator<Object> selectionIterator = selection.iterator();
      while (selectionIterator.hasNext()) {
        Object element = selectionIterator.next();
        if (CapellaResourceHelper.isSemanticElement(element)) {
          EObject elementAsEObject = (EObject) element;
          if (!DoubleClickBehaviourUtil.INSTANCE.shouldOpenRelatedDiagramsOnDoubleClick(elementAsEObject)) {
            CapellaUIPropertiesPlugin.getDefault().openWizard((EObject) element);
          } else {
            OpenRelatedDiagramAction action = new OpenRelatedDiagramAction(elementAsEObject);
            action.run();
          }
        }
      }
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
