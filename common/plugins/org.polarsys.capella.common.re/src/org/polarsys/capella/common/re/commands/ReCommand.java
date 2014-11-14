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
package org.polarsys.capella.common.re.commands;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;

import org.polarsys.capella.core.transition.common.commands.TransitionCommand;
import org.polarsys.capella.common.re.launcher.ReLauncher;

/**
 */
public abstract class ReCommand extends TransitionCommand {

  /**
   * @param _rootElement_p
   * @param progressMonitor_p
   */
  public ReCommand(Collection<Object> selection_p, IProgressMonitor progressMonitor_p) {
    super(selection_p, progressMonitor_p);
  }

  @Override
  public String getName() {
    return getClass().getName();
  }

  protected abstract ReLauncher createLauncher();

  @Override
  protected void performTransformation(Collection<Object> elements_p) {
    Collection<Object> sources = elements_p;
    ReLauncher launcher = createLauncher();
    launcher.run(sources, true, getProgressMonitor());
  }

}
