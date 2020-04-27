/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.ui.services.helper;

import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.helpers.IUserEnforcedHelper2;

/**
 * Helper methods enforced by user interaction.<br>
 * It should not be overridden nor instantiated.<br>
 * It is located here for dependencies issues.
 */
public class UserEnforcedHelper implements IUserEnforcedHelper2 {
  /**
   * @see org.polarsys.capella.common.mdsofa.common.helper.IUserEnforcedHelper#makeFileWritable(org.eclipse.core.resources.IFile)
   */
  public IStatus makeFileWritable(final IFile file) {
    return makeFilesWritable(new IFile[] { file });
  }

  /**
   * @see org.polarsys.capella.common.mdsofa.common.helper.IUserEnforcedHelper#makeFileWritable(org.eclipse.core.resources.IFile, java.lang.Object)
   */
  public IStatus makeFileWritable(IFile file, Object display) {
    return makeFilesWritable(new IFile[] { file }, display);
  }

  /**
   * @see org.polarsys.capella.common.helpers.IUserEnforcedHelper2#makeFilesWritable(org.eclipse.core.resources.IFile[])
   */
  public IStatus makeFilesWritable(final IFile[] files) {
    // Resulting status.
    final IStatus[] result = new IStatus[] { Status.CANCEL_STATUS };
    // Get display, if any.
    // Do not create a new one if none, simply ignore it.
    final Display display = PlatformUI.isWorkbenchRunning() ? PlatformUI.getWorkbench().getDisplay() : null;
    // Execute through a runnable.
    Runnable makeFileWritableRunnable = new Runnable() {
      public void run() {
        result[0] = makeFilesWritable(files, display);
      }
    };
    // Whether it is displaying a user interface dialog or not, the execution is always synchronous.
    if (null != display) {
      // Ask user, if needed.
      display.syncExec(makeFileWritableRunnable);
    } else {
      // No way to ask user, execute it.
      makeFileWritableRunnable.run();
    }
    return result[0];
  }

  /**
   * @see org.polarsys.capella.common.helpers.IUserEnforcedHelper2#makeFilesWritable(org.eclipse.core.resources.IFile[], java.lang.Object)
   */
  public IStatus makeFilesWritable(final IFile[] files, Object display) {
    // Given files must not be null.
    if (null == files) {
      return Status.CANCEL_STATUS;
    }
    ArrayList<IFile> existingFiles = new ArrayList<IFile>(0);
    // Loop over given files to check if all exist or not.
    for (IFile file : files) {
      // If file does not exist, then it is writable, hence ignore it.
      if (file.exists()) {
        existingFiles.add(file);
      }
    }
    // Precondition, all given files do not exist, no problem, there are writable.
    if (existingFiles.isEmpty()) {
      return Status.OK_STATUS;
    }
    // Resulting shell.
    Shell shell = null;
    // Given display object must indeed be a display.
    if (display instanceof Display) {
      // Get shell from display.
      shell = ((Display) display).getActiveShell();
    }
    // If a shell is active, use it to make sure an end-user confirmation is performed (as needed).
    // Try validate prompt behavior otherwise.
    Object context = (null == shell) ? IWorkspace.VALIDATE_PROMPT : shell;
    // Check given files.
    return ResourcesPlugin.getWorkspace().validateEdit(existingFiles.toArray(new IFile[existingFiles.size()]), context);
  }
}
