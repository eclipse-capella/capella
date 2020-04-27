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

package org.polarsys.capella.common.helpers;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.polarsys.capella.common.mdsofa.common.helper.IUserEnforcedHelper;

/**
 * Extends {@link IUserEnforcedHelper} services to handle bunch of files.
 */
public interface IUserEnforcedHelper2 extends IUserEnforcedHelper {
  /**
   * Make given files writable.<br>
   * Such an implementation deals with the UI issues spawn by the act of asking the user (if needed).<br>
   * That includes executing the enforcement in the UI thread.
   * @param files The files that should be made writable.
   * @return {@link Status#OK_STATUS} if file was made writable or doesn't exist. {@link Status#CANCEL_STATUS} otherwise.
   */
  public IStatus makeFilesWritable(IFile[] files);

  /**
   * Make given files writable.<br>
   * The caller is responsible for making sure it is executed in the UI thread.<br>
   * Otherwise an invalid thread access exception might be thrown by the platform.
   * @param files The files that should be made writable.
   * @param display Used to ask the user permission, if needed.
   * @return {@link Status#OK_STATUS} if file was made writable or doesn't exist. {@link Status#CANCEL_STATUS} otherwise.
   */
  public IStatus makeFilesWritable(IFile[] files, Object display);
}
