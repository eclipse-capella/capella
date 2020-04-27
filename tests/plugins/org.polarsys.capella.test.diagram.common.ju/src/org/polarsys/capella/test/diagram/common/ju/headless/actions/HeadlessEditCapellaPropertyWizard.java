/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.headless.actions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.PropertySheet;

import org.polarsys.capella.core.sirius.analysis.actions.extensions.EditCapellaPropertyWizard;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 *
 */
public class HeadlessEditCapellaPropertyWizard extends EditCapellaPropertyWizard {
  /**
   * @see org.polarsys.capella.core.sirius.analysis.actions.extensions.EditCapellaPropertyWizard#execute(java.util.Collection, java.util.Map)
   */
  @Override
  public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
    EObject context = (EObject) parameters.get("context"); //$NON-NLS-1$
    if (context instanceof ModelElement) {
      // FIXME: not sure about the real functionality of this wizard
      // Force a the property view to refresh.
      IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
      // See PropertySheet doc to get the id.
      PropertySheet propertyView = (PropertySheet) activePage.findView("org.eclipse.ui.views.PropertySheet"); //$NON-NLS-1$
      if (null != propertyView) {
        propertyView.selectionChanged(activePage.getActivePart(), new StructuredSelection(context));
      }
    }
  }
}
