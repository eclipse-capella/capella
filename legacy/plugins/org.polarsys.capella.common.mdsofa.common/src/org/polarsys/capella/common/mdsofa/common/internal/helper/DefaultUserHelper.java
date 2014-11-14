/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.mdsofa.common.internal.helper;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.polarsys.capella.common.mdsofa.common.helper.IUserEnforcedHelper;

/**
 * A default {@link IUserEnforcedHelper} implementation.<br>
 * Leaves files untouched, considering it's always possible to write them.
 */
public class DefaultUserHelper implements IUserEnforcedHelper {
  /**
   * @see org.polarsys.capella.common.mdsofa.common.helper.IUserEnforcedHelper#makeFileWritable(org.eclipse.core.resources.IFile)
   */
  public IStatus makeFileWritable(IFile file_p) {
    return Status.OK_STATUS;
  }

  /**
   * @see org.polarsys.capella.common.mdsofa.common.helper.IUserEnforcedHelper#makeFileWritable(org.eclipse.core.resources.IFile, java.lang.Object)
   */
  public IStatus makeFileWritable(IFile file_p, Object display_p) {
    return Status.OK_STATUS;
  }
}
