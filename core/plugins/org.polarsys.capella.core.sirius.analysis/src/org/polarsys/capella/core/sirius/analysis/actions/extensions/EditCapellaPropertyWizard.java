/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.actions.extensions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * Wizard to edit/update capella element <b> EEF generated wizard is opened if existed else custom wizard is opened </b> <EObject> context -- element to be
 * updated
 */
public class EditCapellaPropertyWizard extends AbstractExternalJavaAction {
  /**
   * @see org.eclipse.sirius.tools.api.ui.IExternalJavaAction#execute(java.util.Collection, java.util.Map)
   */
  public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
    // Element to be edited.
    EObject context = (EObject) parameters.get(CONTEXT);
    if (context instanceof ModelElement) {
      CapellaUIPropertiesPlugin.getDefault().openWizard((ModelElement) context);
    }
  }
}
