/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.commandline.core.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.UIJob;
import org.osgi.framework.FrameworkUtil;

public class CloseWorkbenchJob extends UIJob {

  public CloseWorkbenchJob() {
    super(Messages.CloseWorkbenchJob_AutoClose);
  }

  @Override
  public IStatus runInUIThread(IProgressMonitor monitor) {
    if (PlatformUI.getWorkbench().close()) {
      return Status.OK_STATUS;
    }
    return new Status(IStatus.WARNING, FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), Messages.CloseWorkbenchJob_AutoCloseError);
  }

}