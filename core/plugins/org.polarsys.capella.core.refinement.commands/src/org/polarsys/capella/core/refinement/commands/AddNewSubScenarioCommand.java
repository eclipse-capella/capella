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
package org.polarsys.capella.core.refinement.commands;

import org.eclipse.core.runtime.IProgressMonitor;

import org.polarsys.capella.common.helpers.operations.LongRunningListenersRegistry;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.refinement.subscenario.SubScenarioUtils;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 */
public class AddNewSubScenarioCommand extends AbstractReadWriteCommand {

  /**
   * Currently selected element
   */
  private ModelElement modelElement = null;

  /**
   * Progress monitor
   */
  private IProgressMonitor progressMonitor = null;

  /**
   * Constructor
   */
  public AddNewSubScenarioCommand(ModelElement modelElement, IProgressMonitor progressMonitor) {
    this.modelElement = modelElement;
    this.progressMonitor = progressMonitor;
  }

  /**
   * @see org.polarsys.capella.common.ef.command.command.ICommand#execute(org.eclipse.core.runtime.IProgressMonitor)
   */
  public void run() {
    LongRunningListenersRegistry.getInstance().operationStarting(getClass());
    try {
      if (modelElement != null) {
        SubScenarioUtils.addNewSubScenario((Scenario) modelElement, progressMonitor);
      }
    } finally {
      // Send long running operation events.
      // Operation has finished.
      LongRunningListenersRegistry.getInstance().operationEnded(getClass());
    }
  }

  /**
   * @see org.polarsys.capella.common.ef.command.command.ICommand#getLabel()
   */
  @Override
  public String getName() {
    return "Add new sub Scenario"; //$NON-NLS-1$
  }
}
