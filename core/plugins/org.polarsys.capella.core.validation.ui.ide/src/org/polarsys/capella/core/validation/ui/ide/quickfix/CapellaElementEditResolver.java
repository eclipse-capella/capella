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
  public void run(IMarker marker_p) {
    final EObject value = getModelElements(marker_p).get(0);
    if ((null != value) && isEditableInstance(value) && (value instanceof ModelElement)) {
      // open editor
      boolean editElement = CapellaUIPropertiesPlugin.getDefault().openWizard((ModelElement) value);
      if (editElement) {
        try {
          // delete marker
          marker_p.delete();
        } catch (CoreException exception_p) {
          // no nothing
        }
      }
    }
  }

  public abstract boolean isEditableInstance(EObject value_p);
}
