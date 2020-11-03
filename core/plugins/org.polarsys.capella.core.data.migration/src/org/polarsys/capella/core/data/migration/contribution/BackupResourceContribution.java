/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.migration.contribution;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.migration.MigrationConstants;
import org.polarsys.capella.core.data.migration.context.MigrationContext;

public class BackupResourceContribution extends AbstractMigrationContribution {

  //Backup flag shall not be asked after each migration
  static Boolean _backupModels = null;

  public static void dispose() {
    _backupModels = null;
  }
  
  /**
   * Allow to check conditions before running the migration.
   * @param fileToMigrate
   * @return <code>true</code> means OK to run the migration, <code>false</code> otherwise.
   */
  @Override
  public IStatus preMigrationExecute(IResource fileToMigrate, final MigrationContext context, boolean checkVersion) {
    if (context.isSkipConfirmation()) {
      _backupModels = context.isBackupModel();
      return Status.OK_STATUS;
    }

    final IStatus[] result = { Status.OK_STATUS };
    final Boolean[] state = { Boolean.TRUE };

    if (_backupModels == null) {
      context.getShell().getDisplay().syncExec(new Runnable() {

        @Override
        public void run() {
          MessageDialogWithToggle dialog = MessageDialogWithToggle.openOkCancelConfirm(context.getShell(),
              Messages.MigrationAction_ConfirmationDialog_Title, Messages.MigrationAction_ConfirmationDialog_Message,
              Messages.MigrationAction_ConfirmationDialog_ToggleMessage, true, null, null);
          if (dialog.getReturnCode() != IDialogConstants.OK_ID) {
            result[0] = Status.CANCEL_STATUS;
          }
          state[0] = Boolean.valueOf(dialog.getToggleState());
        }
      });

      if (!result[0].isOK()) {
        return result[0];
      }
      _backupModels = state[0];
    }

    return Status.OK_STATUS;
  }

  /*
   * (non-Javadoc)
   * @see org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution#preSaveResource(org.polarsys .capella.common.ef.ExecutionManager,
   * org.eclipse.emf.ecore.resource.Resource, org.polarsys.capella.core.data.migration.context.MigrationContext)
   */
  @Override
  public void preSaveResource(ExecutionManager executionManager, Resource resource, MigrationContext context) {

    backupResource(resource, context.getProgressMonitor());
    super.preSaveResource(executionManager, resource, context);
  }

  /**
   * Creates a backup file of the resource.
   * @param resource the resource.
   */
  protected void backupResource(Resource resource, IProgressMonitor monitor) {
    if (_backupModels == Boolean.TRUE) {
      URI uri = resource.getResourceSet().getURIConverter().normalize(resource.getURI());
      String filePath = uri.toPlatformString(true);
      IFile inputXMI = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(filePath));

      try {
        createBackupFile(inputXMI, monitor);
      } catch (CoreException ex) {
        ex.printStackTrace();
      }
    }
  }

  /**
   * Creates a backup file.
   * @param file the source file.
   * @param monitor a progress monitor.
   * @return backup file
   * @throws CoreException in case of while saving file error.
   */
  protected IFile createBackupFile(IFile file, IProgressMonitor monitor) throws CoreException {
    return createBackupFile(file, createTimestamp(), monitor);
  }

  /**
   * Creates a backup file.
   * @param file the source file.
   * @param monitor a progress monitor.
   * @return backup file
   * @throws CoreException in case of while saving file error.
   */
  protected IFile createBackupFile(IFile file, String timestamp, IProgressMonitor monitor) throws CoreException {
    // Computes a candidate name.
    int lastDotIndex = file.getName().lastIndexOf('.');
    String name = null;
    if (lastDotIndex > 0) {
      name =
          file.getName().substring(0, lastDotIndex) + '-' + timestamp + ICommonConstants.POINT_CHARACTER
              + file.getName().substring(lastDotIndex + ".".length()) //$NON-NLS-1$
              + ".old"; //$NON-NLS-1$
    } else {
      name = file.getName() + '-' + timestamp + ".old"; //$NON-NLS-1$
    }

    // find the backup file.
    IFile backup;
    if (file.getParent().findMember(name, true) == null) {
      backup = file.getParent().getFile(new Path(MigrationConstants.FILE_SEPARATOR + name));
    } else {
      int i = 2;
      while (file.getParent().findMember(name + i, true) != null) {
        i++;
      }
      backup = file.getParent().getFile(new Path(MigrationConstants.FILE_SEPARATOR + name + i));
    }
    assert !backup.exists() : "the file already exists"; //$NON-NLS-1$

    // creates the backup
    file.copy(backup.getFullPath(), IResource.FORCE, monitor);
    // refresh local container.
    file.getParent().refreshLocal(IResource.DEPTH_ONE, monitor);

    return backup;
  }

  /**
   * Create a timestamp.
   * @return a not <code>null</code> string.
   */
  protected String createTimestamp() {
    // Computes a timestamp.
    return new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date()); //$NON-NLS-1$
  }

}
