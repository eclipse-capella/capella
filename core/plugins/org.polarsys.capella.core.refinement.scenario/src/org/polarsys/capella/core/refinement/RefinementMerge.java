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
package org.polarsys.capella.core.refinement;

import java.text.MessageFormat;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;

import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.refinement.merge.merger.IScenarioMerger;
import org.polarsys.capella.core.refinement.merge.processor.ScenarioMergeProcessor;
import org.polarsys.capella.core.refinement.scenarios.core.ScenarioRefinement;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.ProcessorException;
import org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor;

/**
 */
public class RefinementMerge extends ScenarioRefinement {

  Scenario _scenario;
  
  private IProcessor _merge = null;
  private Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.REFINEMENT);

  /**
   *
   */
  public RefinementMerge(Scenario srcDiagram_p, ComponentArchitecture arch_p) {
    super(srcDiagram_p, arch_p);

    _scenario = srcDiagram_p;
    
    _merge = new ScenarioMergeProcessor();
    _merge.setContext(_scenario);
    _merge.setTarget((AbstractCapability) _scenario.eContainer());
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.ScenarioRefinement#execute()
   */
  @Override
  public void execute(IProgressMonitor progressMonitor_p) throws ProcessorException {
    if (_merge != null) {

      // First of all, we execute the merge operation
      try {
        _merge.execute(progressMonitor_p);
      } catch (ProcessorException exception_p) {
        
        _logger.error(
            new EmbeddedMessage(
                Messages.refinementAbortedDueToMergeFailure,
                IReportManagerDefaultComponents.REFINEMENT, 
                _scenario
            )
        );
        
        throw exception_p;
      }
      
      // Let's obtain the result of this operation
      Scenario mergeResult = (Scenario) _merge.getResult();

      //Let's set context for the refinement step 
      setContext(mergeResult);
      
      // On a second hand, we perform the refinement to the physical level
      super.execute(progressMonitor_p);

      // Rename the merged scenario in order to easily identify it 
      String scName = mergeResult.getName();
      scName += IScenarioMerger.MERGED_SCENARIO_SUFFIX;
      mergeResult.setName(scName);
      
      // Rename the result on physical level
      NamedElement elt = (NamedElement) getResult();
      scName = elt.getName();
      scName = scName.replace(
          MessageFormat.format(
              ScenarioRefinement.REFINED_SCENARIO_PREFIX,
              new Integer(0)
          ),
          ICommonConstants.EMPTY_STRING
      );
    }

    return;
  }

}
