/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.ui.quickfix.resolver;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.statushandlers.StatusManager;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.core.validation.ui.ide.PluginActivator;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public abstract class AbstractSelectOneResolver extends AbstractCapellaMarkerResolution {
  public AbstractSelectOneResolver() {
  }

  protected abstract boolean isAvailableFor(EObject obj);

  /**
   * {@inheritDoc}
   */
  @Override
  public void run(IMarker marker) {
    final List<EObject> modelElements = getModelElements(marker);

    if (!modelElements.isEmpty()) {
      if (isAvailableFor(modelElements.get(0))) {
        openSelectionDialog(modelElements.get(0), marker);
      }
    }
  }

  protected abstract void openSelectionDialog(EObject semanticElement, IMarker marker);

  protected void confirmDeletion(IMarker marker, EObject semantic, List<? extends EObject> toBeDeleted) {
    boolean confirmDeletion = CapellaDeleteCommand.confirmDeletion(TransactionHelper.getExecutionManager(semantic),
        toBeDeleted);
    if (confirmDeletion) {
      CapellaDeleteCommand command = new CapellaDeleteCommand(TransactionHelper.getExecutionManager(semantic),
          toBeDeleted, false, false, true);
      if (command.canExecute()) {
        command.execute();
        deleteMarker(marker);
      }
    }
  }

  @Override
  protected void deleteMarker(IMarker marker) {
    // delete marker
    try {
      marker.delete();
    } catch (CoreException exception) {
      StatusManager.getManager().handle(
          new Status(IStatus.ERROR, PluginActivator.getDefault().getPluginId(), exception.getMessage(), exception));
    }
  }

}
