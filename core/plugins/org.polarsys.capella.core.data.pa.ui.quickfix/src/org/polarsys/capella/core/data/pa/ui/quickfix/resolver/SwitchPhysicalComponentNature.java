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
package org.polarsys.capella.core.data.pa.ui.quickfix.resolver;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.ui.quickfix.PaQuickFixActivator;

/**
 * Open capella element editor to edit respected values
 * 
 */
public class SwitchPhysicalComponentNature extends AbstractPhysicalComponentNatureChangeResolver {
  PhysicalComponentNature physicalComponentNature = null;

  @Override
  public void run(final IMarker marker) {
    final List<EObject> modelElements = getModelElements(marker);
    if (!modelElements.isEmpty()) {
      ExecutionManager executionManager = TransactionHelper.getExecutionManager(modelElements.get(0));
      ICommand cmd = new AbstractReadWriteCommand() {
        @Override
        public void run() {
          for (EObject element : modelElements) {
            if (element instanceof PhysicalComponent) {
              PhysicalComponent component = (PhysicalComponent) element;
              physicalComponentNature = component.getNature();
              component.setNature(getPhysicalComponentNature());
            }
          }
          try {
            marker.delete();
          } catch (CoreException e) {
            PaQuickFixActivator.getDefault().log(IStatus.ERROR, "Error while deleting marker", e);
          }
        }
      };
      executionManager.execute(cmd);
    }
  }

  @Override
  protected PhysicalComponentNature getPhysicalComponentNature() {
    if (physicalComponentNature == PhysicalComponentNature.NODE)
      return PhysicalComponentNature.BEHAVIOR;
    return PhysicalComponentNature.NODE;
  }

}
