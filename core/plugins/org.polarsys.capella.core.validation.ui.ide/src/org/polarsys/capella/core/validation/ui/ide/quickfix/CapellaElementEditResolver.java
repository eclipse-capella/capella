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
package org.polarsys.capella.core.validation.ui.ide.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * Open capella editor for given element, delete the marker(ok:yes, cancel:no)
 */
public abstract class CapellaElementEditResolver extends AbstractCapellaMarkerResolution {

  /**
   * {@inheritDoc}
   */
  public void run(IMarker marker) {
    final EObject value = getModelElements(marker).get(0);
    if ((null != value) && isEditableInstance(value) && (value instanceof ModelElement)) {
      // open editor
      boolean editElement = CapellaUIPropertiesPlugin.getDefault().openWizard((ModelElement) value);
      if (editElement) {
        try {
          // delete marker
          marker.delete();
        } catch (CoreException exception) {
          // no nothing
        }
      }
    }
  }

  public abstract boolean isEditableInstance(EObject value);
}
