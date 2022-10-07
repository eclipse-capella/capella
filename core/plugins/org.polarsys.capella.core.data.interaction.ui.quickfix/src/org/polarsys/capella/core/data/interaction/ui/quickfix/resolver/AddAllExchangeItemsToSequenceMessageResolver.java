/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.interaction.ui.quickfix.resolver;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.statushandlers.StatusManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.model.helpers.SequenceMessageExt;
import org.polarsys.capella.core.validation.ui.ide.PluginActivator;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public class AddAllExchangeItemsToSequenceMessageResolver extends AbstractCapellaMarkerResolution {

  /**
   * Synchronize invoked operation according to sequence message kind
   */
  public void run(IMarker marker) {
    final List<EObject> modelElements = getModelElements(marker);

    if (!modelElements.isEmpty()) {

      // read write command
      AbstractReadWriteCommand setEICommand = new AbstractReadWriteCommand() {

        public void run() {
          for (EObject object : modelElements) {
            if (object instanceof SequenceMessage) {
              SequenceMessage msg = (SequenceMessage) object;
              msg.getExchangedItems().clear();
              SequenceMessageExt.getExchangeItemsFromOperation(msg).stream().forEach(ei -> {
                  if (ei instanceof ExchangeItem) {
                      msg.getExchangedItems().add((ExchangeItem) ei);
                  }
              });
            }
          }
        }

      };

      // execute the command
      TransactionHelper.getExecutionManager(modelElements).execute(setEICommand);

      try {
        marker.delete();
      } catch (CoreException exception) {
        StatusManager.getManager().handle(new Status(IStatus.ERROR, PluginActivator.getDefault().getPluginId(), exception.getMessage(), exception));
      }

    }

  }

}
