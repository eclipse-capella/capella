/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.ui.quickfix.FaQuickFixActivator;
import org.polarsys.capella.core.data.fa.validation.functionPort.DCOM_21_UnusedExchangeItems;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * Removes all unused exchange items from a function port
 */
public class DCOM_21_RemoveUnusedExchangeItemsResolver extends AbstractCapellaMarkerResolution {
  /**
   * {@inheritDoc}
   */
  @Override
  public void run(IMarker marker) {
    final FunctionPort port = (FunctionPort) getModelElements(marker).get(0);
    final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(port);
    if (domain != null) {
      Command removeCommand = new RecordingCommand(domain) {
        @Override
        protected void doExecute() {
          Collection<ExchangeItem> toUpdate = Collections.emptyList();
          if (port instanceof FunctionInputPort){
            toUpdate = ((FunctionInputPort)port).getIncomingExchangeItems();
          } else if (port instanceof FunctionOutputPort){
            toUpdate = ((FunctionOutputPort)port).getOutgoingExchangeItems();
          }
          toUpdate.removeAll(DCOM_21_UnusedExchangeItems.getUnusedExchangeItems(port));
        }
      };
      domain.getCommandStack().execute(removeCommand);
      try {
        marker.delete();
      } catch (CoreException e) {
        IStatus s = new Status(e.getStatus().getSeverity(), FaQuickFixActivator.getDefault().getPluginId(), e.getStatus().getMessage(), e);
        FaQuickFixActivator.getDefault().getLog().log(s);
      }
    }
  }
}
