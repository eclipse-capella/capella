/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.ui.quickfix.resolver;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.statushandlers.StatusManager;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.core.validation.ui.ide.PluginActivator;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * 
 * set target to null with confirmation (ok:yes, cancel:no)
 *
 */
public class I_43_Replace extends AbstractCapellaMarkerResolution {

  @Override
  public void run(IMarker marker) {
    final EObject cat = getElementTest(marker);
    if (cat != null) {
      ExecutionManager executionManager = TransactionHelper.getExecutionManager(cat);
      executionManager.execute(new AbstractReadWriteCommand() {
        @Override
        public void run() {
          ((CatalogElementLink) cat).setTarget(null);
          deleteMarker(marker);
        }
      });
    }
  }

  private EObject getElementTest(IMarker marker) {
    final List<EObject> modelElements = getModelElements(marker);
    // The target shall be always the first element
    // (see org.polarsys.capella.common.helpers.validation.ConstraintStatusDiagnostic#getData())
    return !modelElements.isEmpty() ? modelElements.get(0) : null;
  }

}
