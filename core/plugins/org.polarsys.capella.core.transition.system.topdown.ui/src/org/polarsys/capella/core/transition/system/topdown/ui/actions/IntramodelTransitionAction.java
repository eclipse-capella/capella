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
package org.polarsys.capella.core.transition.system.topdown.ui.actions;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;

import org.polarsys.capella.common.helpers.operations.LongRunningListenersRegistry;
import org.polarsys.capella.core.transition.common.ui.actions.TransitionAction;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.ui.commands.IntramodelTransitionCommand;
import org.polarsys.capella.common.tig.ef.command.ICommand;

/**
 *
 */
public class IntramodelTransitionAction extends TransitionAction {

  @Override
  public void run(final IAction action) {
    try {
      LongRunningListenersRegistry.getInstance().operationStarting(getClass());
      super.run(action);
    } finally {
      LongRunningListenersRegistry.getInstance().operationEnded(getClass());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ICommand createCommand(Collection<Object> selection_p, IProgressMonitor progressMonitor_p) {
    return new IntramodelTransitionCommand((Collection) selection_p, progressMonitor_p) {

      @Override
      protected String getTransitionKind() {
        return IntramodelTransitionAction.this.getTransitionKind();
      }

    };
  }

  protected String getTransitionKind() {
    return ITopDownConstants.TRANSITION_TOPDOWN;
  }
}
