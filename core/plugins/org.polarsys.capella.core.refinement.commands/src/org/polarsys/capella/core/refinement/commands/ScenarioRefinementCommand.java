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

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;

import org.polarsys.capella.common.helpers.operations.LongRunningListenersRegistry;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.refinement.RefinementMultiple;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.ProcessorException;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

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
  public void run() {
    // Send long running operation events.
    // Operation is starting.
    LongRunningListenersRegistry.getInstance().operationStarting(getClass());
    try {
      if (selectedElement != null) {
        RefinementMultiple refinement = new RefinementMultiple((NamedElement) selectedElement);
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
