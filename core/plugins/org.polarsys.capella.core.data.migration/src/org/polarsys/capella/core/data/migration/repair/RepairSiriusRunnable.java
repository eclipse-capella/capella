/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.migration.repair;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.business.api.repair.SiriusRepairProcess;
import org.polarsys.capella.core.data.migration.AbstractMigrationRunnable;
import org.polarsys.capella.core.data.migration.MigrationHelpers;
import org.polarsys.capella.core.data.migration.context.MigrationContext;

public class RepairSiriusRunnable extends AbstractMigrationRunnable {

  public RepairSiriusRunnable(IFile file) {
    super(file);
  }

  @Override
  public IStatus run(final MigrationContext context) {
    // async/sync? GMF also run async... so when many
    // repair/migrate are triggered, we will mix R/Ms and
    // GMF commands between models..
    // sync should lead to UI deadlock between GMF and RMs.
    // so we should find a better way.
    // async lead to popups..

    try {
      doRepairMigrate(getName(), getFile(), true, context);

    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (OutOfMemoryError error) {
      MigrationHelpers.getInstance().onOutOfMemoryError(error, context);
      throw error;

    } finally {
      MigrationHelpers.getInstance().dispose();
      context.getProgressMonitor().done();
    }

    return Status.OK_STATUS;
  }

  @Override
  public String getName() {
    return Messages.MigrationAction_RepairMigration;
  }

  protected void doRepairMigrate(String actionName, final IFile file, boolean backup, final MigrationContext context)
      throws InterruptedException {
    final SiriusRepairProcess process = new SiriusRepairProcess(file, backup);
    context.getProgressMonitor().setTaskName(getName());
    process.run(context.getProgressMonitor());
  }

  protected void showMessage(final String title, final String message) {
    /*
     * final Shell shell = _activePart.getSite().getShell(); if ((shell != null) && !(shell.isDisposed())) { Display
     * display = shell.getDisplay(); if ((display != null) && !(display.isDisposed())) { display.syncExec(new Runnable()
     * { public void run() { MessageDialog.openError(shell, title, message); } }); } }
     */
  }
}
