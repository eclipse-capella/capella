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

import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.interaction.services.ScenarioExt;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.MergeLink;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.NamespaceExt;
import org.polarsys.capella.core.refinement.merge.exception.MergeException;
import org.polarsys.capella.core.refinement.merge.exception.MergeToolException;
import org.polarsys.capella.core.refinement.merge.helpers.MergeHelper;
import org.polarsys.capella.core.refinement.merge.helpers.ScenarioHelper;
import org.polarsys.capella.core.refinement.merge.utils.LinkEnum;
import org.polarsys.capella.core.refinement.merge.utils.LinkUtils;

/**
 * Copy a Scenario
 *
 */
public class ScenarioCopy extends AbstractMergerOperation {

  protected Scenario _source;
  protected Scenario _result;
  
  private boolean _shouldAddMergeLink; 
  
  public Scenario getResult() {return _result;}
  
  /**
   * Constructor
   * @param source the {@link Scenario} to copy
   * @param shouldAddMergeLink should operation add {@link MergeLink} to the copy 
   */
  public ScenarioCopy(Scenario source, boolean shouldAddMergeLink) {
    _source = source;
    _shouldAddMergeLink = shouldAddMergeLink;
    
  }
  
  /**
   * @see org.polarsys.capella.core.common.refinement.merge.merger.operations.IMergerOperation#perform()
   */
  @Override
  public void perform() throws MergeToolException {
    
    // We create the target as a copy of the root Scenario
    _result =
      ScenarioHelper.createScenario(
          _source,
          _source.getName()
    );
    
    //We remove old Traces
    NamespaceExt.removeTraces(_result);
       
    // And we finally add new ones about this initial seed.
    createInitialMergeLink();

    // set references for TimeLapses
    
    TimeLapse tlSRC = null;
    InteractionFragment start = null;
    InteractionFragment finish = null;
    
    for (TimeLapse tl: _result.getOwnedTimeLapses() ) {
      
      tlSRC = (TimeLapse) LinkUtils.getOutgoingLinkTargets(tl, LinkEnum.MERGE_LINK).get(0);
      
      start = (InteractionFragment)( LinkUtils.getIncomingLinkTargetsFrom(tlSRC.getStart(), LinkEnum.MERGE_LINK, _result).get(0) );
      finish = (InteractionFragment)( LinkUtils.getIncomingLinkTargetsFrom(tlSRC.getFinish(), LinkEnum.MERGE_LINK, _result).get(0) );

      tl.setStart(start);
      tl.setFinish(finish);
    }   
    
    // Set references for messages...
    
    MessageEnd sending = null;
    MessageEnd receiving = null;
    
    SequenceMessage smSrc = null;
    
    for (SequenceMessage sm: _result.getOwnedMessages() ) {

      smSrc = (SequenceMessage) ( LinkUtils.getOutgoingLinkTargets(sm, LinkEnum.MERGE_LINK).get(0) );
        
      sending = (MessageEnd)( LinkUtils.getIncomingLinkTargetsFrom(smSrc.getSendingEnd(), LinkEnum.MERGE_LINK, _result)).get(0);
      receiving = (MessageEnd)( LinkUtils.getIncomingLinkTargetsFrom(smSrc.getReceivingEnd(), LinkEnum.MERGE_LINK, _result)).get(0);
        
      sm.setSendingEnd(sending);
      sm.setReceivingEnd(receiving);
    }
    
    
  }

  /**
   * @see org.polarsys.capella.core.common.refinement.merge.merger.operations.IMergerOperation#postOperation()
   */
  @Override
  public void postOperation() throws MergeToolException {
    
    //
    // remove MergeLink if needed
    //
    if ( false == _shouldAddMergeLink) {
      NamespaceExt.removeTraces(_result);
    }
    
    return;
  }
  
  /** must be used after the initial copy of the root scenario
   * @throws MergeException */
  private void createInitialMergeLink() throws MergeToolException {
    
    //we add a merge link on the new scenario
    MergeHelper.addMergeLink(_result, _source);
    
    // we add merge links to interesting element of the scenario
    List<EStructuralFeature> features = ScenarioExt.getElementOfInterestOnScenario();
        
    List<?> root = null;
    List<?> tgt = null;
    for (EStructuralFeature feature: features) {
      root = (List<?>) _source.eGet(feature);
      tgt = (List<?>) _result.eGet(feature);
      for (int i= 0; i < root.size(); i++) {
        MergeHelper.addMergeLink(
            (CapellaElement) tgt.get(i),
            (CapellaElement) root.get(i)
        );
      }
    }
    
    return;
  }

  /**
   * @see org.polarsys.capella.core.common.refinement.merge.merger.operations.AbstractMergerOperation#preOperation()
   */
  @Override
  public void preOperation() throws MergeToolException {
    //Do nothing
    return;
  }
  
}
