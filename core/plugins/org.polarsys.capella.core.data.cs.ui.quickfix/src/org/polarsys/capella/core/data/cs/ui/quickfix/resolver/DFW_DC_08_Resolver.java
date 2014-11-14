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
package org.polarsys.capella.core.data.cs.ui.quickfix.resolver;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.PlatformUI;

import org.polarsys.capella.core.ui.toolkit.helpers.SelectionDialogHelper;
import org.polarsys.capella.core.data.core.properties.controllers.TypedElementController;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

/**
 * A resolver to assign a type to a TypedElement
 */
public class DFW_DC_08_Resolver extends AbstractCapellaMarkerResolution {

  public void run(IMarker marker_p) {

    Iterator<EObject> it = getModelElements(marker_p).iterator();
    if (it.hasNext()) {
      EObject first = it.next();
      if (first instanceof TypedElement) {
        openWizard((TypedElement) first);
      }
    }
  }

  private void openWizard(TypedElement typedElement_p) {
    CapellaUIPropertiesPlugin.getDefault().openWizard(typedElement_p);
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
    MDEAdapterFactory.getExecutionManager().execute(command);
  }

}
