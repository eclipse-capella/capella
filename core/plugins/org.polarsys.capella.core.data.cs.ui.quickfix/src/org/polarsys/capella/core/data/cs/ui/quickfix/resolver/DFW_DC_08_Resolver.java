/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.ui.quickfix.resolver;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.core.properties.controllers.TypedElementController;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.toolkit.helpers.SelectionDialogHelper;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * A resolver to assign a type to a TypedElement
 */
public class DFW_DC_08_Resolver extends AbstractCapellaMarkerResolution {

  public void run(IMarker marker) {

    Iterator<EObject> it = getModelElements(marker).iterator();
    if (it.hasNext()) {
      EObject first = it.next();
      if (first instanceof TypedElement) {
        openWizard((TypedElement) first);
      }
    }
  }

  private void openWizard(TypedElement typedElement) {
    CapellaUIPropertiesPlugin.getDefault().openWizard(typedElement);
  }

  @SuppressWarnings("unused")
  private void openSelectionDialog(final TypedElement m) {
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      public void run() {
        List<EObject> list = new TypedElementController().readOpenValues(m, CapellacorePackage.Literals.TYPED_ELEMENT__TYPE);
        EObject firstResult = SelectionDialogHelper.simplePropertySelectionDialogWizard(list, PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
        if (null != firstResult) {
          m.setAbstractType((AbstractType) firstResult);
        }
      }
    };
    TransactionHelper.getExecutionManager(m).execute(command);
  }

}
