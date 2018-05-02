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

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.helpers.operations.LongRunningListenersRegistry;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.refinement.RefinementMultiple;
import org.polarsys.capella.core.refinement.scenarios.core.ScenarioRefinement;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.ProcessorException;

/**
 */
public class ScenarioRefinementCommand extends AbstractReadWriteCommand {

  /**
   * Currently selected element
   */
  private ModelElement selectedElement = null;

  /**
   * Progress monitor
   */
  private IProgressMonitor progressMonitor = null;

  /**
   * Logger
   */
  private Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.REFINEMENT);

  /**
   * Constructor
   *
   * @param modelElement
   * @param progressMonitor
   */
  public ScenarioRefinementCommand(ModelElement modelElement, IProgressMonitor progressMonitor) {
    this.selectedElement = modelElement;
    this.progressMonitor = progressMonitor;
  }

  /**
   * @see java.lang.Runnable#run()
   */
  @Override
  public void run() {
    // Send long running operation events.
    // Operation is starting.
    LongRunningListenersRegistry.getInstance().operationStarting(getClass());
    try {
      if (selectedElement != null) {

        RefinementMultiple refinement = new RefinementMultiple((NamedElement) selectedElement);
        for (Iterator<ScenarioRefinement> sr = refinement.getRefinements(); sr.hasNext();) {
          Scenario scenario = sr.next().getContext();

          // TODO remove when https://bugs.polarsys.org/show_bug.cgi?id=2052 is fixed
          if (ScenarioExt.isMultiInstanceRole(scenario)) {
            Display display = PlatformUI.getWorkbench().getDisplay();
            if (display != null) {
              if (!MessageDialog.openConfirm(display.getActiveShell(), Messages.MultiInstanceRoleExtension_title,
                  Messages.MultiInstanceRoleExtension_message)) {
                sr.remove();
              } else {
                Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.REFINEMENT);
                logger.warn(Messages.MultiInstanceRoleExtension_logmsg_confirm + EObjectLabelProviderHelper.getText(scenario));
              }
            }
          }

        }

        refinement.execute(progressMonitor);
      }
    }
    catch (ProcessorException exception) {
      _logger.error(
          new EmbeddedMessage(
              exception.getMessage(),
              IReportManagerDefaultComponents.REFINEMENT
          )
      );
    }
    finally {
      // Send long running operation events.
      // Operation has finished.
      LongRunningListenersRegistry.getInstance().operationEnded(getClass());
    }
  }
}
