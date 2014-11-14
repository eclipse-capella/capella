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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.refinement.merge.exception.MergeToolException;
import org.polarsys.capella.core.refinement.merge.helpers.MergeHelper;
import org.polarsys.capella.core.refinement.merge.messages.MergeMessages;
import org.polarsys.capella.core.refinement.merge.utils.LinkEnum;
import org.polarsys.capella.core.refinement.merge.utils.LinkUtils;

/**
 *
 */
public class ScenarioDecomposition extends AbstractMergerOperation {

  protected Scenario _target;
  
   // (Key, Value) <=> (IR to replace, corresponding decomposed IRs from n+1 level) 
  protected Map<InstanceRole, List<InstanceRole>> _irs; 
  
  // (Key, Value) <=> (IR to replace, strands)
  protected Map<InstanceRole, List<List<InteractionFragment>>> _strandsMap;
  
  /** accessor */
  public Map<InstanceRole, List<InstanceRole>> getIRsMap() { return _irs;}
  /** accessor */
  public Map<InstanceRole, List<List<InteractionFragment>>> getStrandsMap() { return _strandsMap;}
  /** accessor */
  public Scenario getTargetScenario() {return _target;}
  /** accessor */
  public List<List<InteractionFragment>> getStrandsForToReplace(InstanceRole ir_p) throws MergeToolException {
    if ( null == ir_p || _irs.get(ir_p) == null) {
      //TODO log, send clearer message
      throw new MergeToolException(MergeMessages.genericInternalError);
    }
    return _strandsMap.get(ir_p);
  }
  
  /**
   * Constructor
   */
  public ScenarioDecomposition(Scenario target) {
   _target = target;
  }

  /**
   * @throws MergeToolException 
   * @see org.polarsys.capella.core.common.refinement.merge.merger.operations.IMergerOperation#preOperation()
   */
  @Override
  public void preOperation() throws MergeToolException {
    
    //
    // We obtain the decomposable InstanceRole on the target Scenarion
    // and the corresponding decomposed InstanceRole on the n+1 level 
    //
    
    _irs = new HashMap<InstanceRole, List<InstanceRole>>();
    
    List<InstanceRole> newIRs = null; 

    for (InstanceRole ir: _target.getOwnedInstanceRoles()) {
      
      newIRs = MergeHelper.getDecomposedIR(ir);
      
      if (!newIRs.isEmpty()) {
        // check for added IR
        Scenario data = (Scenario) newIRs.get(0).eContainer();
        for (InstanceRole ir2: data.getOwnedInstanceRoles()) {
          if (!LinkUtils.hasOutgoingLinks(ir2, LinkEnum.REFINEMENT_LINK)) {
            newIRs.add(ir2);
          }
        }

        //Let's finish the job
        _irs.put(ir, newIRs);
      }
      
    }
   
    return;
  }
  
  /**
   * @see org.polarsys.capella.core.common.refinement.merge.merger.operations.IMergerOperation#perform()
   */
  @Override
  public void perform() {
   
    // 
    // So now Let's cut IFrags
    //
   
    _strandsMap = new HashMap<InstanceRole, List<List<InteractionFragment>>>();
    
    Scenario dataScenario = null;
    for (InstanceRole irToReplace: _irs.keySet()) {
      
      dataScenario = (Scenario) _irs.get(irToReplace).get(0).eContainer();
      List<List<InteractionFragment>> strands = new ArrayList<List<InteractionFragment>>();
      
      List<InteractionFragment> strand = null;
      boolean isFirstElementHasRL = false;
      
      for ( InteractionFragment ifrag: dataScenario.getOwnedInteractionFragments() ) {
        if (null == strand) {
          strand = new ArrayList<InteractionFragment>();
          strand.add(ifrag);
          isFirstElementHasRL = LinkUtils.hasOutgoingLinks(ifrag, LinkEnum.REFINEMENT_LINK);
        } else {
            if (
                isFirstElementHasRL ==
                  LinkUtils.hasOutgoingLinks(ifrag, LinkEnum.REFINEMENT_LINK)
            ) {
              strand.add(ifrag);
            } else {
              strands.add(strand);
              strand = new ArrayList<InteractionFragment>();
              strand.add(ifrag);
              isFirstElementHasRL = LinkUtils.hasOutgoingLinks(ifrag, LinkEnum.REFINEMENT_LINK);              
            }
        }
      }
      //Last strand
      strands.add(strand);
      //Update the strands map.
      _strandsMap.put(irToReplace, strands);
    }

    return;
  }
  /**
   * @see org.polarsys.capella.core.common.refinement.merge.merger.operations.AbstractMergerOperation#postOperation()
   */
  @Override
  public void postOperation() throws MergeToolException {
    //Do nothing
    return;
  }

}
