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
package org.polarsys.capella.core.transition.system.topdown.ui.handlers;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.core.transition.common.ui.commands.CommandUIHandler;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.ui.commands.IntramodelTransitionCommand;

/**
 *
 */
public class IntramodelTransitionHandler extends CommandUIHandler {

  /**
   * {@inheritDoc}
   */
  @Override
  protected ICommand createCommand(Collection<Object> selection, IProgressMonitor progressMonitor) {
    return new IntramodelTransitionCommand((Collection) selection, progressMonitor) {

      @Override
      protected String getTransitionKind() {
        return IntramodelTransitionHandler.this.getTransitionKind();
      }

    };
  }

  protected String getTransitionKind() {
    return ITopDownConstants.TRANSITION_TOPDOWN;
  }
}
