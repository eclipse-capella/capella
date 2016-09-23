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
package org.polarsys.capella.core.refinement.merge.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MergeLink;
import org.polarsys.capella.core.data.interaction.RefinementLink;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.refinement.merge.exception.MergeToolException;
import org.polarsys.capella.core.refinement.merge.helpers.ScenarioHelper;
import org.polarsys.capella.core.refinement.merge.messages.MergeMessages;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * Navigator (through {@link MergeLink} and {@link RefinementLink}) for merge purpose.
 *
 */
public class MergeNavigator {

  
  EClass _mergeLayer = null;
  EClass _nextLayer = null;
  

  /**
   * Constructor
   * @param eobject mainly the {@link Scenario} to merge
   */
  public MergeNavigator(EObject eobject) {
    setLayers(eobject);
  }

  /**
   * Accessor
   * @return
   */
  public EClass getCurrentLayer() {
   return _mergeLayer; 
  }
  
  /**
   * Accessor
   * @return
   */
  public EClass getNextLayer() {
    return _nextLayer;
  }

  
  /**
   * From a given {@link TraceableElement} on a data {@link Scenario}, goes to its upper originator 
   * following its {@link RefinementLink} and thus, goes to its lower represented element
   * through its {@link MergeLink} 
   * @param Scenario target the {@link Scenario} to reach
   * @param tr the source {@link TraceableElement}
   * @return the list of {@link AbstractTrace} used to reach the target Scenario, an empty one whether not appliable
   * @throws MergeToolException whether The number of expected link is wrong, or the reached target is not contained into the target {@link Scenario}
   */
  public List<AbstractTrace> navigateFromDataToTarget(Scenario target, TraceableElement trElt) throws MergeToolException {
    
    if ( null == trElt ) {
      //TODO more explicit message
      throw new MergeToolException(MergeMessages.genericToolError);
    }
    
    List<AbstractTrace> result = new ArrayList<AbstractTrace>();
    
    boolean mergeLinkfound = false;
    boolean stop = false;
    
    TraceableElement current = trElt;
    List<AbstractTrace> list = null;
    
    while ( !mergeLinkfound && !stop ) { //RL's up
      
      if ( LinkUtils.hasIncomingLinksFrom(current, LinkEnum.MERGE_LINK, target) ) {
        mergeLinkfound = true;
      } else {
        
        // Special case for AbstractEnd
        // check whether we can backward 
        
        if (
            (
                trElt.eClass() == InteractionPackage.Literals.MESSAGE_END ||
                trElt.eClass() == InteractionPackage.Literals.EXECUTION_END
            ) && (
                LinkUtils.getIncomingLinks(current, LinkEnum.REFINEMENT_LINK).size() > 1
            ) && (
                !LinkUtils.hasIncomingLinks(((AbstractEnd) trElt).getCovered(), LinkEnum.MERGE_LINK)
            )
        ) {

          AbstractEnd aeCurrent = (AbstractEnd) current;
          InstanceRole irCurrent = aeCurrent.getCovered();
          AbstractInstance aiCurrent = irCurrent.getRepresentedInstance(); 
          
          for (TraceableElement elt : LinkUtils.getIncomingLinkTargets(current, LinkEnum.REFINEMENT_LINK) ) {
            AbstractEnd ae = (AbstractEnd) elt;
            InstanceRole ir = ae.getCovered();
            AbstractInstance ai = ir.getRepresentedInstance();
            if (!ai.equals(aiCurrent)) {
              current = elt;
              break;
            }
          }
        } else { // "common" cas we go up
          list = LinkUtils.getOutgoingLinks(current, LinkEnum.REFINEMENT_LINK);
        
          switch (list.size()) {
            case 0: 
              //TODO more explicit message
              //throw new Merge2ToolException(MergeMessages.genericToolError);
              result.clear();
              return result;
            case 1:
              current = list.get(0).getTargetElement();
              if (!isContainedIntoTheMergeLayer(current)) {
                // Ups, we backward to straight away...
                stop = true;
              }
              result.add(list.get(0));
              break;
              default:
                //TODO more explicit message
                throw new MergeToolException(MergeMessages.genericToolError);
          }
        }
      }
      
    } // END RL's up
    
    if ( true == mergeLinkfound ) { // ML
      
      TraceableElement finalTGT = null; 
      
      list = LinkUtils.getIncomingLinksFrom(current, LinkEnum.MERGE_LINK, target);
      
      switch (list.size()) {
        case 0: 
          //TODO more explicit message
          throw new MergeToolException(MergeMessages.genericToolError);
        case 1:
          finalTGT = list.get(0).getSourceElement();
          result.add(list.get(0));
          break;
        default:
            //TODO more explicit message
            throw new MergeToolException(MergeMessages.genericToolError);
      }
      if ( !ScenarioHelper.isContainedInto(target, finalTGT) ) {
        //TODO more explicit message
        throw new MergeToolException(MergeMessages.genericToolError);
      }
      
    } else { // No ML found
      //TODO more explicit message
      throw new MergeToolException(MergeMessages.genericToolError);
    }
    
    return result;
  }

  /**
   * @param eObject mainly a {@link Scenario}
   * @return
   */
  public boolean isContainedIntoTheMergeLayer(EObject eObject) {
    return EcoreUtil2.isContainedBy(eObject, _mergeLayer);
  }

  /**
   * @param eObject mainly a {@link Scenario}
   * @return
   */
  public boolean isContainedIntoTheNextLayer(EObject eObject) {
    return EcoreUtil2.isContainedBy(eObject, _nextLayer);
  }
  
  private final void setLayers(EObject eObject) {
    if (EcoreUtil2.isContainedBy(eObject, LaPackage.Literals.LOGICAL_ARCHITECTURE)) {
      _mergeLayer = LaPackage.Literals.LOGICAL_ARCHITECTURE;
      _nextLayer = PaPackage.Literals.PHYSICAL_ARCHITECTURE;
    } else if (EcoreUtil2.isContainedBy(eObject,  PaPackage.Literals.PHYSICAL_ARCHITECTURE)) {
      _mergeLayer = PaPackage.Literals.PHYSICAL_ARCHITECTURE;
      _nextLayer = EpbsPackage.Literals.EPBS_ARCHITECTURE;
      
    }
    return;
  }
  
}
