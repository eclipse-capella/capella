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
package org.polarsys.capella.common.re.ui.commands;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;

import org.polarsys.capella.common.re.commands.CreateRecCommand;
import org.polarsys.capella.common.re.ui.launcher.CreateRecUiLauncher;

/**
 */
public class CreateRecUiCommand extends CreateRecCommand {

  /**
   * @param selection_p
   * @param progressMonitor_p
   */
  public CreateRecUiCommand(Collection<Object> selection_p, IProgressMonitor progressMonitor_p) {
    super(selection_p, progressMonitor_p);
  }

  @Override
  protected void performTransformation(Collection<Object> elements_p) {
    CreateRecUiLauncher launcher = new CreateRecUiLauncher();
    launcher.run(elements_p, true, getProgressMonitor());
  }
}
