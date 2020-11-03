/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.clipboard;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.AbstractStatusHandler;
import org.eclipse.ui.statushandlers.StatusAdapter;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;

public class ClipboardStatusHandler extends AbstractStatusHandler {

  @Override
  public void handle(StatusAdapter statusAdapter, int style) {
    if (PlatformUI.isWorkbenchRunning()) {
      IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
      if (window != null) {
        IStatus status = statusAdapter.getStatus();
        if (!status.isOK()) {
          Shell activeShell = window.getShell();
          Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.DIAGRAM);
          logger.warn(new EmbeddedMessage(status.getMessage(), IReportManagerDefaultComponents.DIAGRAM), status.getException());
          MessageDialog.openWarning(activeShell, Activator.LABEL, status.getMessage());
        }
      }
    }
  }

}
