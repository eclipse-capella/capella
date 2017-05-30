/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.common.helpers.operations.LongRunningListenersRegistry;
import org.polarsys.capella.core.data.migration.AbstractMigrationRunnable;
import org.polarsys.capella.core.data.migration.MigrationHelpers;
import org.polarsys.capella.core.data.migration.context.MigrationContext;

public class RepairSiriusRunnable extends AbstractMigrationRunnable {

  public RepairSiriusRunnable(IFile file) {
    super(file);
  }

  @Override
  public IStatus run(final MigrationContext context, boolean checkVersion) {
    // async/sync? GMF also run async... so when many
    // repair/migrate are triggered, we will mix R/Ms and
    // GMF commands between models..
    // sync should lead to UI deadlock between GMF and RMs.
    // so we should find a better way.
    // async lead to popups..

    
	try {
      LongRunningListenersRegistry.getInstance().operationStarting(getClass());
      doRepairMigrate(getName(), getFile(), context.isSkipConfirmation(), context);

    } catch (InterruptedException e) {
      LongRunningListenersRegistry.getInstance().operationAborted(getClass());
      return Status.CANCEL_STATUS;

    } catch (OutOfMemoryError error) {
      MigrationHelpers.getInstance().onOutOfMemoryError(error, context);
      throw error;

    } finally {
      LongRunningListenersRegistry.getInstance().operationEnded(getClass());

      context.getProgressMonitor().done();
    }

    return Status.OK_STATUS;
  }

  @Override
  public String getName() {
    return Messages.MigrationAction_RepairMigration;
  }

  protected void doRepairMigrate(String actionName, final IFile file, boolean backup, final MigrationContext context) throws InterruptedException {
    SiriusRepairProcess process = new SiriusRepairProcess(file, backup);
    context.getProgressMonitor().setTaskName(getName());
    process.run(context.getProgressMonitor());
  }

}
