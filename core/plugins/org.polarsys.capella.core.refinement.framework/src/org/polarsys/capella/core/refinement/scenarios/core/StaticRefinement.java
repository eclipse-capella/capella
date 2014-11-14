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
package org.polarsys.capella.core.refinement.scenarios.core;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;

import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.ProcessorException;
import org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 */
public abstract class StaticRefinement implements IProcessor {
  /**
   * 
   */
  private Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.REFINEMENT);

  /**
   * 
   */
  protected ModelElement _context = null;
  protected NamedElement _target = null;

  /**
   * 
   */
  private List<IProcessor> _pluggedProcessors = null;

  /**
   * Constructor
   */
  public StaticRefinement() {
    _pluggedProcessors = new ArrayList<IProcessor>();
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setContext(org.polarsys.capella.core.common.model.CapellaElement)
   */
  public void setContext(ModelElement context_p) {
    _context = context_p;
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setContext(java.util.List)
   */
  public void setContext(List<ModelElement> context_p) {
    if ((context_p != null) && (context_p.size()>0)) {
      setContext(context_p.get(0));
    }
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setTarget(org.polarsys.capella.core.common.model.NamedElement)
   */
  public void setTarget(NamedElement target_p) {
    _target = target_p;
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#getResult()
   */
  public Object getResult() {
    return null;
  }

  /**
   * Appends a new processor at the end of the processors list.
   * @param processor
   */
  public void addPlug(IProcessor processor) {
    addPlug(-1, processor);
  }

  /**
   * Adds a new preprocessor to the preprocessors list at the 'order' position.
   * @param order
   * @param processor
   */
  public void addPlug(int order, IProcessor processor) {
    if (null == _pluggedProcessors) {
      _pluggedProcessors = new ArrayList<IProcessor>();
    }
    if ((order < 0) || (order > _pluggedProcessors.size()))
      _pluggedProcessors.add(processor);
    else
      _pluggedProcessors.add(order, processor);
  }

  /**
   * 
   */
  public void execute(IProgressMonitor progressMonitor_p) throws ProcessorException {
    String loggedMsg;

    try {
      /**
       * Processing
       */
      for (IProcessor processor : _pluggedProcessors) {
        processor.execute(progressMonitor_p);

        /** logging */
        Object processorName = processor.getName();
        if (processorName != null) {
          loggedMsg = MessageFormat.format(Messages.Processing_Succeeded, processor.getName());
          _logger.info(new EmbeddedMessage(loggedMsg, IReportManagerDefaultComponents.REFINEMENT, _context));
        }
      }
    }
    catch (ProcessorException ex) {
      loggedMsg = MessageFormat.format(Messages.Processing_Failed, ex.getPlug().getName());
      _logger.error(new EmbeddedMessage(loggedMsg, IReportManagerDefaultComponents.REFINEMENT, _context));
    }
  }
}
