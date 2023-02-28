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

import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.ui.tools.api.views.common.item.ItemWrapper;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.commands.preferences.ui.sirius.DoubleClickBehaviourUtil;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.sirius.ui.actions.OpenRepresentationsAction;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;

public class OpenAction extends OpenRepresentationsAction {

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
        if (element instanceof ItemWrapper) {
          element = ((ItemWrapper) element).getWrappedObject();
        }
        if (CapellaResourceHelper.isSemanticElement(element)) {
          EObject elementAsEObject = (EObject) element;
          if (!DoubleClickBehaviourUtil.INSTANCE.shouldOpenRelatedDiagramsOnDoubleClick(elementAsEObject)) {
            CapellaUIPropertiesPlugin.getDefault().openWizard((EObject) element);
          } else {
            OpenRelatedDiagramAction action = new OpenRelatedDiagramAction(elementAsEObject);
            action.run();
          }
        }else {
          if(isRepresentationDescriptor(element)) {
            openRepresentations(Collections.singletonList((DRepresentationDescriptor)element));
          }
        }
      }
    }
  }

  /**
   * returns true if action should be enabled
   * Selection is valid if it contains SemanticElements and RepresentationDescriptors(can be wrapped) only
   */
  @Override
  protected boolean updateSelection(IStructuredSelection selection) {
    boolean result = true;
    if (!selection.isEmpty()) {
      for(Object selectedElement : selection.toList()) {
        if( !CapellaResourceHelper.isSemanticElement(selectedElement) || !isRepresentationDescriptor(selectedElement)) {
          return false;
        }
      }
    }
    return result;
  }


  private boolean isRepresentationDescriptor(Object element) {    
    if (element instanceof DRepresentationDescriptor) {
      return true;
    }
    return false;
  }
}
