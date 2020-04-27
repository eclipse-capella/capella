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
package org.polarsys.capella.core.data.cs.ui.quickfix.resolver;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ui.quickfix.CsQuickFixActivator;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * Delete physical path involvement
 */
public abstract class SwitchHumanActorLabels_Resolver extends AbstractCapellaMarkerResolution {

  @Override
  public void run(IMarker marker) {
    // Get ModelElement associated to the marker.
    List<EObject> modelElements = getModelElements(marker);
    if (modelElements.isEmpty()) {
      return;
    }
    final EObject modelElement = modelElements.get(0);
    ExecutionManager executionManager = TransactionHelper.getExecutionManager(modelElements.get(0));
    if (modelElement instanceof Component) {
      ICommand cmd = new AbstractReadWriteCommand() {
        @Override
        public void run() {
          Component component = (Component) modelElement;
          switchLabel(component);
          try {
            marker.delete();
          } catch (CoreException e) {
            CsQuickFixActivator.getDefault().log(IStatus.ERROR, "Error while deleting marker", e);
          }
        }
      };
      executionManager.execute(cmd);
    }
  }

  protected abstract void switchLabel(Component component);

}
