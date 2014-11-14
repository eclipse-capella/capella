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
  private ModelElement _selectedElement = null;

  /**
   * Progress monitor
   */
  private IProgressMonitor _progressMonitor = null;

  /**
   * Logger
   */
  private Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.REFINEMENT);

  /**
   * Constructor
   *
   * @param modelElement_p
   * @param progressMonitor_p
   */
  public ScenarioRefinementCommand(ModelElement modelElement_p, IProgressMonitor progressMonitor_p) {
    _selectedElement = modelElement_p;
    _progressMonitor = progressMonitor_p;
  }

  /**
   * @see java.lang.Runnable#run()
   */
  public void run() {
    // Send long running operation events.
    // Operation is starting.
    LongRunningListenersRegistry.getInstance().operationStarting(getClass());
    try {
      if (_selectedElement != null) {
        RefinementMultiple refinement = new RefinementMultiple((NamedElement) _selectedElement);
        refinement.execute(_progressMonitor);
      }
    }
    catch (ProcessorException exception_p) {
      _logger.error(
          new EmbeddedMessage(
              exception_p.getMessage(),
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
