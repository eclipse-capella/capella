/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.common.commands;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.polarsys.capella.core.transition.common.launcher.DefaultLauncher;

/**
 *
 */
public abstract class LauncherCommand extends DefaultCommand {

  public LauncherCommand(Collection<?> selection) {
    super(selection);
  }

  public LauncherCommand(Collection<?> selection, IProgressMonitor progressMonitor) {
    super(selection, progressMonitor);
  }
  
  
  /**
   * @param elements
   */
  protected void performTransformation(Collection<?> elements) {
    DefaultLauncher launcher = createLauncher();
    launcher.setName(getName());
    launcher.addParameters(getParameters());
    launcher.run(elements, true, getProgressMonitor());
  }

  protected abstract DefaultLauncher createLauncher();

}
