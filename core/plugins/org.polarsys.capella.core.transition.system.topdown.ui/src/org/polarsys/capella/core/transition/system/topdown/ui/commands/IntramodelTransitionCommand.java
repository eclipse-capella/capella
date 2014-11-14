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
package org.polarsys.capella.core.transition.system.topdown.ui.commands;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;

import org.polarsys.capella.core.transition.system.topdown.ui.launcher.IntramodelLauncher;

public class IntramodelTransitionCommand extends org.polarsys.capella.core.transition.system.topdown.commands.IntramodelTransitionCommand {

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return "Capella Transition";
  }

  /**
   * @param _rootElement_p
   * @param progressMonitor_p
   */
  public IntramodelTransitionCommand(Collection<Object> selection_p, IProgressMonitor progressMonitor_p) {
    super(selection_p, progressMonitor_p);
  }

  @Override
  protected void performTransformation(Collection<Object> elements_p) {
    IntramodelLauncher launcher = new IntramodelLauncher() {

      @Override
      protected String getMapping() {
        return getTransitionMapping();
      }

      @Override
      protected String getOptionScope() {
        return getTransitionKind();
      }
    };
    launcher.run(elements_p, true, getProgressMonitor());
  }
}
