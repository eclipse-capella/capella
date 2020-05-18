/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.mdsofa.common.helper;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * User enforced helper.<br>
 * Handles user interactions when automatic ones are no longer sufficient.<br>
 * Allows the user to be warned about specific changes (sometimes through a user confirmation request).
 */
public interface IUserEnforcedHelper {
  /**
   * Make given file writable.<br>
   * Such an implementation deals with the UI issues spawn by the act of asking the user (if needed).<br>
   * That includes executing the enforcement in the UI thread.
   * @param file_p The file that should be made writable.
   * @return {@link Status#OK_STATUS} if file was made writable or doesn't exist. {@link Status#CANCEL_STATUS} otherwise.
   */
  public IStatus makeFileWritable(IFile file_p);

  /**
   * Make given file writable.<br>
   * The caller is responsible for making sure it is executed in the UI thread.<br>
   * Otherwise an invalid thread access exception might be thrown by the platform.
   * @param file_p The file that should be made writable.
   * @param display_p Used to ask the user permission, if needed.
   * @return {@link Status#OK_STATUS} if file was made writable or doesn't exist. {@link Status#CANCEL_STATUS} otherwise.
   */
  public IStatus makeFileWritable(IFile file_p, Object display_p);
}
