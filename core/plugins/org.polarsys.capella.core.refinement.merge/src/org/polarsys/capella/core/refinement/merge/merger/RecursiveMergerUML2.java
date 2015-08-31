/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.merge.merger;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;

import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.helpers.interaction.services.ScenarioExt;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.refinement.merge.MergeActivator;
import org.polarsys.capella.core.refinement.merge.exception.MergeException;
import org.polarsys.capella.core.refinement.merge.helpers.ScenarioHelper;
import org.polarsys.capella.core.refinement.merge.merger.operations.ScenarioCopy;
import org.polarsys.capella.core.refinement.merge.merger.operations.ScenarioDecomposition;
import org.polarsys.capella.core.refinement.merge.merger.operations.ScenarioSubstituteIRs;
import org.polarsys.capella.core.refinement.merge.merger.operations.SeekAndRemoveUnmergeableElements;
import org.polarsys.capella.core.refinement.merge.messages.MergeMessages;
import org.polarsys.capella.core.refinement.merge.utils.MergeGarbage;

/**
 * Merge operation between a level n defined by the scenario and its n+1 level
 */
public class RecursiveMergerUML2 implements IMerger {
   
  /** the root scenario e.g. the scenario to merge */
  protected Scenario _scenarioToMerge;

  /** the result e.g. a simple copy of the root scenario to merge
   * on level 0, and finally the merged one one the end of the process.
   */
  protected Scenario _result;

  /**
   * Constructor
   * @param root the {@link Scenario} to merge.
   * @throws MergeException 
   */
  public RecursiveMergerUML2(Scenario scenarioToMerge) throws MergeException {

    _scenarioToMerge = scenarioToMerge;
    
    
  }
  
  /**
   * First recursion seed.
   * @return <code>true</code> is created Scenario must be merged, <code>false</code> otherwise.
   * @throws MergeException
   */
  protected boolean initialize() throws MergeException {
   
    boolean result = false;
    
    ScenarioCopy op = new ScenarioCopy(_scenarioToMerge, true);
    op.apply();
    
    _result = op.getResult();
    
    // set some useful attributes
    _result.setMerged(true);
    
    //
    // We hereby initialize data for the recursive merger.
    //
 
    result = ScenarioHelper.isScenarioHasDecomposedElement(_result);
    
    return result;
  }

  /**
   * Perform the merge operation
   * @throws MergeException 
   */
  public void performMerge() throws MergeException {
    
    boolean anotherLevelToMerge = false;
    
    //
    // first of all, let's initialize merger data
    //

    anotherLevelToMerge = initialize();
    
    //
    // On a second hand, let's perform the merge operation
    //
    
    while(anotherLevelToMerge) {
        anotherLevelToMerge = performLevel();
    }
    
    return;
  }
  
  /**
   * merge the current "level"
   * @throws MergeException 
   */
  @SuppressWarnings("unchecked")
  protected boolean performLevel() throws MergeException {
    
    ScenarioDecomposition op1 = new ScenarioDecomposition(_result);
    ScenarioSubstituteIRs op2 = new ScenarioSubstituteIRs(op1);
    SeekAndRemoveUnmergeableElements op3 = new SeekAndRemoveUnmergeableElements(_result);
    
    try {
      
      //
      // On a first hand, Let's decompose Scenario
      //
      
      op1.apply();
      
      //
      // No let's perform the main job 
      //
      
      op2.apply();
      
      //
      // Let's clean unmergeable elements.
      //
      op3.apply();
      
    } catch (Exception exception) {

      //
      // Let's clean everything
      //

      for (EStructuralFeature feature : ScenarioExt.getElementOfInterestOnScenario()) {
        MergeGarbage.INSTANCE.addAll( ( (Collection<EObject>) _result.eGet(feature) ));
      }
      MergeGarbage.INSTANCE.add(_result);
      MergeGarbage.INSTANCE.clear();
      
      _result = null;
      
      //
      // Let's log error
      //
      
      Logger logger = MergeActivator.getDefault().getLogger();
      
      String tmp = ICommonConstants.EMPTY_STRING;
      
      Set<InstanceRole> irs = op1.getIRsMap().keySet();
      Iterator<InstanceRole> it = irs.iterator();
      InstanceRole ir = null;
      while (it.hasNext()) {
        ir = it.next();
        tmp += ir.getName();
        if (it.hasNext()) {
          tmp += ICommonConstants.SLASH_CHARACTER;
        }
      }
            
      String msg =
        NLS.bind(
            MergeMessages.canNotPerformLevel,
            new Object[] {
                tmp,
                _scenarioToMerge.getName()
            }
        ) +
        ICommonConstants.COMMA_CHARACTER +
        ICommonConstants.WHITE_SPACE_CHARACTER +
        exception.getMessage()
      ;
      
      logger.error(
          new EmbeddedMessage(
              msg,
              IReportManagerDefaultComponents.REFINEMENT, 
              _scenarioToMerge
          )
      );
      
      throw new MergeException(msg, exception);
    } 
    
   return ScenarioHelper.isScenarioHasDecomposedElement(_result); 
  }

  
  public Scenario getResult() {
    return _result;
  }
  
}
