/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.commands;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.helpers.operations.LongRunningListenersRegistry;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.refinement.subscenario.SubScenarioUtils;

/**
 */
public class AddExistingSubScenarioCommand extends AbstractReadWriteCommand {

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
  public AddExistingSubScenarioCommand(ModelElement modelElement, IProgressMonitor progressMonitor) {
    this.modelElement = modelElement;
    this.progressMonitor = progressMonitor;
  }

  /**
   * @see org.polarsys.capella.common.ef.command.command.ICommand#execute(org.eclipse.core.runtime.IProgressMonitor)
   */
  @Override
  public void run() {
    LongRunningListenersRegistry.getInstance().operationStarting(getClass());
    try {
      if (modelElement != null) {

        // TODO remove when https://bugs.polarsys.org/show_bug.cgi?id=2052 is fixed
        if (ScenarioExt.isMultiInstanceRole((Scenario) modelElement)) {
          Display display = PlatformUI.getWorkbench().getDisplay();
          if (display != null) {
            if (!MessageDialog.openConfirm(display.getActiveShell(), Messages.MultiInstanceRoleExtension_title,
                Messages.MultiInstanceRoleExtension_message)) {
              return;
            } else {
              Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.REFINEMENT);
              logger.warn(Messages.MultiInstanceRoleExtension_logmsg_confirm + EObjectLabelProviderHelper.getText(modelElement));
            }
          }
        }

        SubScenarioUtils.addExistingSubScenario((Scenario) modelElement, progressMonitor);
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
    return "Add existing sub Scenario"; //$NON-NLS-1$
  }
}
