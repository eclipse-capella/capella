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

package org.polarsys.capella.common.re.ui.commands;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;

import org.polarsys.capella.common.re.commands.UpdateDefCommand;
import org.polarsys.capella.common.re.ui.launcher.UpdateDefUiLauncher;

/**
 */
public class UpdateDefUiCommand extends UpdateDefCommand {

  /**
   * @param selection
   * @param progressMonitor
   */
  public UpdateDefUiCommand(Collection<Object> selection, IProgressMonitor progressMonitor) {
    super(selection, progressMonitor);
  }

  @Override
  protected void performTransformation(Collection<Object> elements) {
    UpdateDefUiLauncher launcher = new UpdateDefUiLauncher();
    launcher.run(elements, true, getProgressMonitor());
  }
}
