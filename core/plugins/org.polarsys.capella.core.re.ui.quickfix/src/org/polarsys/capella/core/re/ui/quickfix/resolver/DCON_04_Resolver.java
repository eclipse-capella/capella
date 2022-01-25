/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.core.validation.ui.ide.PluginActivator;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public class DCON_04_Resolver extends AbstractCapellaMarkerResolution {

  @Override
  public void run(IMarker marker) {
    final CatalogElement rpl = getRPL(marker);
    if (rpl != null) {
      ExecutionManager executionManager = TransactionHelper.getExecutionManager(rpl);
      executionManager.execute(new AbstractReadWriteCommand() {
        @Override
        public void run() {
          rpl.setKind(CatalogElementKind.REC);
          rpl.setOrigin(null);
          for (CatalogElementLink element : rpl.getOwnedLinks()) {
            element.setOrigin(null);
          }
          deleteMarker(marker);
        }
      });
    }
  }

  private CatalogElement getRPL(IMarker marker) {
    final List<EObject> modelElements = getModelElements(marker);
    // The target shall be always the first element
    // (see org.polarsys.capella.common.helpers.validation.ConstraintStatusDiagnostic#getData())
    return !modelElements.isEmpty() ? (CatalogElement) modelElements.get(0) : null;
  }

  @Override
  protected void deleteMarker(IMarker marker) {
    // delete marker
    try {
      marker.delete();
    } catch (CoreException exception) {
      StatusManager.getManager().handle(
          new Status(IStatus.ERROR, PluginActivator.getDefault().getBundle().getSymbolicName(), exception.getMessage(), exception));
    }
  }
}
