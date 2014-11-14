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
package org.polarsys.capella.core.refinement.merge.merger.operations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.refinement.merge.exception.MergeToolException;
import org.polarsys.capella.core.refinement.merge.helpers.MergeHelper;
import org.polarsys.capella.core.refinement.merge.helpers.ScenarioHelper;
import org.polarsys.capella.core.refinement.merge.merger.internal.AddElementOnScUtils;
import org.polarsys.capella.core.refinement.merge.utils.LinkEnum;
import org.polarsys.capella.core.refinement.merge.utils.LinkUtils;
import org.polarsys.capella.core.refinement.merge.utils.CapellaCopier;
import org.polarsys.capella.core.refinement.merge.utils.MergeGarbage;
import org.polarsys.capella.core.refinement.merge.utils.MergeNavigator;

/**
 *
 */
public class ScenarioSubstituteIRs extends AbstractMergerOperation {

  // operation which embed all decomposition data
  protected ScenarioDecomposition _decompositionOp;
  
  // the target Scenario
  protected Scenario _target;
  
  protected MergeNavigator _navigator;
  
  public ScenarioSubstituteIRs(ScenarioDecomposition decompositionOp) {

    _decompositionOp = decompositionOp;
    _target = decompositionOp.getTargetScenario();
    
  }
  
  /**
   * @see org.polarsys.capella.core.common.refinement.merge.merger.operations.AbstractMergerOperation#preOperation()
   */
  @Override
  public void preOperation() throws MergeToolException {

    //
    // for each InstanceRole to substitute,
    // We add the new ones to the target Scenario 
    //
    
    Map<InstanceRole, List<InstanceRole>> irToSubstituteMap = _decompositionOp.getIRsMap();
    
    InstanceRole newIR = null;
    InstanceRole ir = null;
    for (InstanceRole iRToReplace: irToSubstituteMap.keySet()) {
      // Add new one(s)
      for (InstanceRole irToAdd: irToSubstituteMap.get(iRToReplace)) {
        ir = ScenarioHelper.isPartAlreadyRepresented(_target, irToAdd);
        // case of re-use with decomposition on different target scenario
        boolean test =
          null != ir && 
          (
              !MergeHelper.getDecomposedIR(ir).isEmpty() &&
              !MergeHelper.getDecomposedIR(irToAdd).isEmpty() &&
              (
                  MergeHelper.getDecomposedIR(ir).get(0).eContainer() !=
                    MergeHelper.getDecomposedIR(irToAdd).get(0).eContainer()
              )
          )
        ;
        if ( 
            null == ir || test
        ) {
          newIR = (InstanceRole) CapellaCopier.copyObject(irToAdd, true);
          // let's add this new InstanceRole to the target
          _target.getOwnedInstanceRoles().add(newIR);
          // Let's create a merge link
          MergeHelper.addMergeLink(newIR, irToAdd);
        }
      }
    }
    
    // Navigator
    _navigator = new MergeNavigator(
        irToSubstituteMap.get(irToSubstituteMap.keySet().iterator().next()).get(0)
    );
    
    return;
  }
  
  
  /**
   * @see org.polarsys.capella.core.common.refinement.merge.merger.operations.AbstractMergerOperation#perform()
   */
  @Override
  public void perform() throws MergeToolException {
    
    Set<InstanceRole> irsToSubstitute = _decompositionOp.getIRsMap().keySet();

    // Re-use and multi-part cases...
    Set<Scenario> datas = new HashSet<Scenario>();

    AddElementOnScUtils.getInstance().setNavigator(_navigator);
    
    Scenario data = null;
    List<List<InteractionFragment>> strands = null;
    for (InstanceRole irToSubstitue: irsToSubstitute) {

      strands = _decompositionOp.getStrandsForToReplace(irToSubstitue);
      
      data = (Scenario) strands.get(0).get(0).eContainer(); //...
      
      if (!datas.contains(data)) {
      
        datas.add(data);
        
        // add strand
        
        boolean areNewElements;
        
        Iterator<List<InteractionFragment>> it = strands.iterator();
        
        InteractionFragment previousAnchor = null;
        List<InteractionFragment> strand = null;
        while (it.hasNext()) {
          
          strand = it.next();
  
          // First, let's check whether this strand is 
          // composed of new elements
          areNewElements = !LinkUtils.hasOutgoingLinks(strand.get(0), LinkEnum.REFINEMENT_LINK);
          
          // Let's perform job
          
          if (areNewElements) { //
            addNewElements(previousAnchor, strand);
          } else { // No anchor needed
            addExistingElements(strand);
            previousAnchor = strand.get(strand.size() - 1);
            
          }  
        }
        
      }
      
    }

    return;
  }

  /**
   * @see org.polarsys.capella.core.common.refinement.merge.merger.operations.AbstractMergerOperation#postOperation()
   */
  @Override
  public void postOperation() throws MergeToolException {
   
    //
    // We hereby clean everything
    //
    
    Set<InstanceRole> irs = _decompositionOp.getIRsMap().keySet();
    
    //
    // We check IO and FE (for not "decomposed one")
    //
    List<InstanceRole> irsOwned = null; // IRs on target
    for (InteractionFragment ifrag: _target.getOwnedInteractionFragments()) {
      if (
          ifrag.eClass() == InteractionPackage.Literals.INTERACTION_OPERAND ||
          ifrag.eClass() == InteractionPackage.Literals.FRAGMENT_END
      ) {
        
        irsOwned = ifrag.getCoveredInstanceRoles();
        ArrayList<InstanceRole> irsToAdd = new ArrayList<InstanceRole>();
        for (InstanceRole ir: irsOwned) {
          if (irs.contains(ir)) {
            List<InstanceRole> listOnDATA = _decompositionOp.getIRsMap().get(ir);
            irsToAdd.addAll(
                AddElementOnScUtils.getTargetIR(_target, listOnDATA)
            );
          }
        }
        if (!irsToAdd.isEmpty()) {
          ifrag.getCoveredInstanceRoles().removeAll(irs);
          ifrag.getCoveredInstanceRoles().addAll(irsToAdd);
        }
      }
    }
    
    //
    // substituted IRs
    //
    for (InstanceRole iRToReplace: irs) {
      _target.getOwnedInstanceRoles().remove(iRToReplace);
      MergeGarbage.INSTANCE.add(iRToReplace);  
    }
    
    MergeGarbage.INSTANCE.clear();
   
    return;
  }
  
  private void addNewElements(InteractionFragment anchor_p, List<InteractionFragment> strand_p)  throws MergeToolException {
    
    InteractionFragment anchor = anchor_p;
    
    for (InteractionFragment ifrag: strand_p) {
      AddElementOnScUtils.getInstance().addIFrag(_target, ifrag, true, anchor);      
      anchor = ifrag;
    }
    
    return;
  }
  
  private void addExistingElements(List<InteractionFragment> strand_p) throws MergeToolException {

    for (InteractionFragment ifrag: strand_p) {
      AddElementOnScUtils.getInstance().addIFrag(_target, ifrag, false, null);      
    }
    
    return;
  }
  
  
}
