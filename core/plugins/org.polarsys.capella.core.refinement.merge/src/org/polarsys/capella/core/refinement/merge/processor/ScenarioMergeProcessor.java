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
package org.polarsys.capella.core.refinement.merge.processor;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.osgi.util.NLS;

import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.refinement.merge.exception.MergeException;
import org.polarsys.capella.core.refinement.merge.merger.DefaultScenarioMerger;
import org.polarsys.capella.core.refinement.merge.merger.IScenarioMerger;
import org.polarsys.capella.core.refinement.merge.messages.MergeMessages;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.ProcessorException;
import org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor;
import org.polarsys.capella.common.data.modellingcore.ModelElement;


/**
 * The processor for the scenario merge job.
 *
 */
final public class ScenarioMergeProcessor implements IProcessor {

  /**
   * The result of the merge operation
   */
  protected Scenario _result;
  
  /**
   * The merge context e.g. the root {@link Scenario} to merge.
   */
  protected Scenario _context;
  
  /**
   * The target element e.g. the root {@link CapabilityRealization}.
   */
  protected CapabilityRealization _target;
  
  /**
   * The merger implementation that will be used to perform 
   * this merge.
   * @see IScenarioMerger
   */
  protected IScenarioMerger _merger;
  
  /**
   * Default Constructor 
   */
  public ScenarioMergeProcessor() {
   _merger = new DefaultScenarioMerger();
  }
  
  /**
   * Constructor with a specific scenario merger
   * @param merger the merger to use. If null, the default one will be
   * used.
   */
  public ScenarioMergeProcessor(IScenarioMerger merger) {
    _merger = 
      ( merger == null? new DefaultScenarioMerger() : merger );
  }
  
  
  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#execute(org.eclipse.core.runtime.IProgressMonitor)
   */
  public void execute(IProgressMonitor progressMonitor_p) throws ProcessorException {

    //
    // Pre-treatment
    //
    boolean b = true;
    try {
      b = _merger.preTreatment(_context);
    } catch (MergeException exception_p) {
      throw new ProcessorException(
          NLS.bind(
              MergeMessages.preValidationErr,
              new Object[] {_context.getName(),_target.getName()}
          ), 
          this
      );
    } finally {
      if (!b) {
        throw new ProcessorException(
            NLS.bind(
                MergeMessages.preValidationErr,
                new Object[] {_context.getName(),_target.getName()}
            ), 
            this
        );
      }
    }
    
    //
    // The merge operation itself
    //
    try {
      _result = _merger.doMerge(_context);
    } catch (Exception exception_p) {
      throw new ProcessorException(
          NLS.bind(
              MergeMessages.genericError,
              new Object[] {_context.getName(),_target.getName()}
          ), 
          this
      );
    }
    
    //
    // Post treatment
    //
    try {
      _merger.postTreatment(_context);
    } catch (MergeException exception_p) {
      throw new ProcessorException(
          NLS.bind(
              MergeMessages.postValidationErr,
              new Object[] {_context.getName(),_target.getName()}
          ), 
          this
      );
    }

    _target.getOwnedScenarios().add(_result);
    
    return;
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#getResult()
   */
  public Object getResult() {
    return _result;
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setContext(org.polarsys.capella.common.data.modellingcore.ModelElement)
   */
  public void setContext(ModelElement context_p) {
    
    if (
        context_p.eClass().isSuperTypeOf(InteractionPackage.Literals.SCENARIO)
    ) {
      _context = (Scenario) context_p; 
    } else {
      throw new ClassCastException();
    }
    
    return;
  }

  /**
   * Not used at all
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setContext(java.util.List)
   */
  public void setContext(List<ModelElement> context_p) {
    throw new UnsupportedOperationException();
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setTarget(org.polarsys.capella.core.data.capellacore.NamedElement)
   */
  public void setTarget(NamedElement target_p) {
    if (
        target_p.eClass().isSuperTypeOf(LaPackage.Literals.CAPABILITY_REALIZATION)
    ) {
      _target = (CapabilityRealization) target_p; 
    } else {
      throw new ClassCastException();
    }
    
    return;
    
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IRefinementPlug#getName()
   */
  public Object getName() {
    return MergeMessages.mergeProcessorName;
  }
  
  
}
