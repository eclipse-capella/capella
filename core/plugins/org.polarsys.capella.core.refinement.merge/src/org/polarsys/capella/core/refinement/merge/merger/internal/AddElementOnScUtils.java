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
package org.polarsys.capella.core.refinement.merge.merger.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.CombinedFragment;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.FragmentEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionOperand;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionState;
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.MergeLink;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.core.refinement.merge.exception.MergeToolException;
import org.polarsys.capella.core.refinement.merge.helpers.MergeHelper;
import org.polarsys.capella.core.refinement.merge.helpers.SequenceMessageHelper;
import org.polarsys.capella.core.refinement.merge.helpers.TimeLapseHelper;
import org.polarsys.capella.core.refinement.merge.messages.MergeMessages;
import org.polarsys.capella.core.refinement.merge.utils.LinkEnum;
import org.polarsys.capella.core.refinement.merge.utils.LinkUtils;
import org.polarsys.capella.core.refinement.merge.utils.CapellaCopier;
import org.polarsys.capella.core.refinement.merge.utils.MergeGarbage;
import org.polarsys.capella.core.refinement.merge.utils.MergeNavigator;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * Utility class in order to add/replace {@link InteractionFragment} for data scenario to merged one.
 *
 */
public class AddElementOnScUtils {
  
  static AddElementOnScUtils _singleton = null;
  
  MergeNavigator _navigator = null;
  
  private AddElementOnScUtils() {
   //Do Nothing
  }
  
  public static AddElementOnScUtils getInstance() {
  
    if (null == _singleton) {
      _singleton = new AddElementOnScUtils();
    }
    
    return _singleton;
  }
  
  public void setNavigator(MergeNavigator navigator_p) {
    _navigator = navigator_p;
  }
  
  public void addIFrag(Scenario target_p, InteractionFragment ifrag_p, boolean isNew, InteractionFragment anchor_p) throws MergeToolException {
    
    if (
        ifrag_p.eClass() == InteractionPackage.Literals.EXECUTION_END ||
        ifrag_p.eClass() == InteractionPackage.Literals.MESSAGE_END
    ) {
      if (isNew) {
        addAE(target_p, (AbstractEnd) ifrag_p, anchor_p);
      } else {
        replaceAE(target_p, (AbstractEnd) ifrag_p);
      }
    } else if (
        ifrag_p.eClass() == InteractionPackage.Literals.FRAGMENT_END
    ) {
      if (isNew) {
        addFE(target_p, (FragmentEnd) ifrag_p, anchor_p);
      } else {
        replaceFE(target_p, (FragmentEnd) ifrag_p);
      }      
    } else if (
        ifrag_p.eClass() == InteractionPackage.Literals.INTERACTION_OPERAND
    ) {
      if (isNew) {
        addIO(target_p, (InteractionOperand) ifrag_p, anchor_p);
      } else {
        replaceIO(target_p, (InteractionOperand) ifrag_p);
      }      
    } else if (
        ifrag_p.eClass() == InteractionPackage.Literals.INTERACTION_STATE
    ) {
      if (isNew) {
        addIS(target_p, (InteractionState) ifrag_p, anchor_p);
      } else {
        //replaceIS(target_p, (InteractionState) ifrag_p);
      }      
    } else if (
        ifrag_p.eClass() == InteractionPackage.Literals.INTERACTION_USE
    )
    
    
    return;
  }
  
  private void addIS(Scenario target_p, InteractionState is_p, InteractionFragment anchor_p) throws MergeToolException {

    InteractionState isSRC = is_p;
    InstanceRole irSRC = isSRC.getCovered();
    
    InteractionState isNEW = (InteractionState) CapellaCopier.copyObject(isSRC, false);
    InstanceRole irTGT = getTargetIR(target_p, irSRC);
    
    //IR
    isNEW.getCoveredInstanceRoles().clear();
    isNEW.getCoveredInstanceRoles().add(irTGT);
    
    //
    // Let's add It on target Scenario
    //
    
    InteractionFragment anchorSRC = anchor_p;
    
    // Its position on target scenario
    int pos = getPostionOnTarget(target_p, anchorSRC);
    
    //let's add it
    target_p.getOwnedInteractionFragments().add(pos, isNEW);
    
    // Add merge Link
    MergeHelper.addMergeLink(isNEW, isSRC);
    
    //
    // State Fragment
    //
    
    TimeLapse[] tls = updateCreateTimeLapse(isSRC, isNEW);
    StateFragment sfSRC = (StateFragment) tls[0];
    StateFragment sfNEW = (StateFragment) tls[1];
    
    sfNEW.setRelatedAbstractFunction(sfSRC.getRelatedAbstractFunction());
    sfNEW.setRelatedAbstractState(sfSRC.getRelatedAbstractState());
    
    return;
  }
  
  @Deprecated
  @SuppressWarnings({ "unused"})
  private void replaceIS(Scenario target_p, InteractionState is_p) throws MergeToolException {

    // We just have to manage IR.
    
    // get the way to the target {RL_up} + ML_down
    List<AbstractTrace> ats = _navigator.navigateFromDataToTarget(target_p, is_p);
    int sz = ats.size();

    if (ats.isEmpty()) { // 2nd and higher pass case...
      return;
    }
    
    // SRC
    InteractionState isSRC = is_p;
    InstanceRole irSRC = isSRC.getCovered();

    // TGT
    sz--;
    InteractionState isTGT = (InteractionState) ats.get(sz).getSourceElement();
    isTGT.getCoveredInstanceRoles().add(getTargetIR(target_p, irSRC));
    
    // State & Function
    isTGT.setRelatedAbstractState(isSRC.getRelatedAbstractState());
    isTGT.setRelatedAbstractFunction(isSRC.getRelatedAbstractFunction());
    
    // Add merge Link
    //FIXME OLDER LINK TO REMOVE
    MergeHelper.addMergeLink(isTGT, isSRC);
    
    return;
  }
  
  private void addIO(Scenario target_p, InteractionOperand io_p, InteractionFragment anchor_p) throws MergeToolException {
    
    //
    // Main job
    //
    
    InteractionOperand ioSRC = io_p;
    
    InteractionFragment anchorSRC = anchor_p;

    List<AbstractTrace> traces = null;
    
    List<InstanceRole> irTGTs = new ArrayList<InstanceRole>();
    InstanceRole irTGT = null;
    for (InstanceRole irSRC: ioSRC.getCoveredInstanceRoles()) {
      traces = _navigator.navigateFromDataToTarget(target_p, irSRC);
      if ( traces.isEmpty() ) { // New IR added on this level
        irTGT = (InstanceRole) CapellaCopier.copyObject(irSRC, true);
        target_p.getOwnedInstanceRoles().add(irTGT);
        MergeHelper.addMergeLink(irTGT, irSRC);
      } else { // decomposed IR
        irTGT = (InstanceRole) traces.get(traces.size()-1).getSourceElement();
      }
      irTGTs.add(irTGT);
    }
    
    //
    // New IO
    //
    
    InteractionOperand ioNEW = (InteractionOperand) CapellaCopier.copyObject(ioSRC, false);

    //Set InstanceRole(s)
    ioNEW.getCoveredInstanceRoles().addAll(irTGTs);

    //
    // Let's add It on target Scenario
    //
    
    // Its position on target scenario
    int pos = getPostionOnTarget(target_p, anchorSRC);
    target_p.getOwnedInteractionFragments().add(pos, ioNEW);
    
    // Add merge Link
    MergeHelper.addMergeLink(ioNEW, ioSRC);

    //
    // Related CombinedFragment
    //
    
    CombinedFragment combinedFragmentTGT = null;
    
    // Let's obtain src combined Fragment
    CombinedFragment combinedFragmentSRC = TimeLapseHelper.getCombinedFragment(ioSRC);
    // And thus, the target one.
    List<AbstractTrace> list = _navigator.navigateFromDataToTarget(target_p, combinedFragmentSRC);
    combinedFragmentTGT = (CombinedFragment) list.get(list.size() -1).getSourceElement();
    combinedFragmentTGT.getReferencedOperands().add(ioNEW);
        
    return;
  }
  
  private void replaceIO(Scenario target_p, InteractionOperand io_p) throws MergeToolException {

    //
    // check IR !!!!!
    //
    
    // get the way to the target {RL_up} + ML_down
    List<AbstractTrace> ats = _navigator.navigateFromDataToTarget(target_p, io_p);
    int sz = ats.size();

    if (ats.isEmpty()) { // 2nd and higher pass case...
      return;
    }
    
    // SRC
    InteractionOperand ioSRC = io_p;
    List<InstanceRole> irSRCs = ioSRC.getCoveredInstanceRoles();

    // TGT
    sz--;
    InteractionOperand ioTGT = (InteractionOperand) ats.get(sz).getSourceElement();

    // IRs
    ioTGT.getCoveredInstanceRoles().addAll(getTargetIR(target_p, irSRCs));
    
    // Add merge Link
    MergeHelper.addMergeLink(ioTGT, ioSRC);
    
    return;
  }
  
  private void addFE(Scenario target_p, FragmentEnd fe_p, InteractionFragment anchor_p) throws MergeToolException {
    
    //
    // Main job
    //
    
    FragmentEnd feSRC = fe_p;
    
    InteractionFragment anchorSRC = anchor_p;

    List<AbstractTrace> traces = null;
    
    List<InstanceRole> irTGTs = new ArrayList<InstanceRole>();
    InstanceRole irTGT = null;
    for (InstanceRole irSRC: fe_p.getCoveredInstanceRoles()) {
      traces = _navigator.navigateFromDataToTarget(target_p, irSRC);
      if ( traces.isEmpty() ) { // New IR added on this level
        irTGT = (InstanceRole) CapellaCopier.copyObject(irSRC, true);
        target_p.getOwnedInstanceRoles().add(irTGT);
        MergeHelper.addMergeLink(irTGT, irSRC);
      } else { // decomposed IR
        irTGT = (InstanceRole) traces.get(traces.size()-1).getSourceElement();
      }
      irTGTs.add(irTGT);
    }
    
    //
    // New FE
    //
    
    FragmentEnd feNEW = (FragmentEnd) CapellaCopier.copyObject(feSRC, false);

    //Set InstanceRole(s)
    feNEW.getCoveredInstanceRoles().addAll(irTGTs);

    //
    // Let's add It on target Scenario
    //
    
    // Its position on target scenario
    int pos = getPostionOnTarget(target_p, anchorSRC);
    target_p.getOwnedInteractionFragments().add(pos, feNEW);
    
    // Add merge Link
    MergeHelper.addMergeLink(feNEW, feSRC);
    
    //
    // TimeLapse
    //
    
    updateCreateTimeLapse(feSRC, feNEW);
    
    return;
  }
  
  private void replaceFE(Scenario target_p, FragmentEnd fe_p) throws MergeToolException {

    //
    // check IR !!!!!
    //
    
    // get the way to the target {RL_up} + ML_down
    List<AbstractTrace> ats = _navigator.navigateFromDataToTarget(target_p, fe_p);
    int sz = ats.size();

    if (ats.isEmpty()) { // 2nd and higher pass case...
      return;
    }
    
    // SRC
    FragmentEnd feSRC = fe_p;
    List<InstanceRole> irSRCs = feSRC.getCoveredInstanceRoles();

    // TGT
    sz--;
    FragmentEnd feTGT = (FragmentEnd) ats.get(sz).getSourceElement();

    // IRs
    feTGT.getCoveredInstanceRoles().addAll(getTargetIR(target_p, irSRCs));
    
    // Add merge Link
    MergeHelper.addMergeLink(feTGT, feSRC);
 
    return;
  }
  
  private void addAE(Scenario target_p, AbstractEnd ae_p, InteractionFragment anchor_p) throws MergeToolException {
    
    //
    // Main job
    //
    
    AbstractEnd aeSRC = ae_p;
    InstanceRole irSRC = ae_p.getCovered();
    
    InteractionFragment anchorSRC = anchor_p;

    InstanceRole irTGT = null;
    List<AbstractTrace> traces = _navigator.navigateFromDataToTarget(target_p, irSRC);
    
    if ( traces.isEmpty() ) { // New IR added on this level
    
      irTGT = (InstanceRole) CapellaCopier.copyObject(irSRC, true);
      target_p.getOwnedInstanceRoles().add(irTGT);
      MergeHelper.addMergeLink(irTGT, irSRC);
      
      
    } else { // decomposed IR
      irTGT = (InstanceRole) traces.get(traces.size()-1).getSourceElement();
    }
    
    //
    // New AE
    //
    
    AbstractEnd aeNEW = (AbstractEnd) CapellaCopier.copyObject(aeSRC, false);

    //Set InstanceRole(s)
    aeNEW.getCoveredInstanceRoles().clear();
    aeNEW.getCoveredInstanceRoles().add(irTGT);
    
    // create connected event
    Event eventSRC = aeSRC.getEvent();
    Event eventNEW = (Event) CapellaCopier.copyObject(eventSRC, true); 
    
    //Set event
    aeNEW.setEvent(eventNEW);
    
    //Add it
    target_p.getOwnedEvents().add(eventNEW);
    //Put a Merge Link
    MergeHelper.addMergeLink(eventNEW, eventSRC);
    
    //
    // Let's add It on target Scenario
    //
    
    // Its position on target scenario
    int pos = getPostionOnTarget(target_p, anchorSRC);
    target_p.getOwnedInteractionFragments().add(pos, aeNEW);
    
    // Add merge Link
    MergeHelper.addMergeLink(aeNEW, aeSRC);

    
    //
    // SEQUENCE MESSAGE
    //
    
    List<TraceableElement> list = null;
    
    if (aeSRC.eClass() == InteractionPackage.Literals.MESSAGE_END ) {
      
      MessageEnd meSRC = (MessageEnd) aeSRC;
      SequenceMessage smSRC = meSRC.getMessage();
      
      SequenceMessage smTGT = null;
      
      boolean hasBeenAlreadyAdded = LinkUtils.hasIncomingLinksFrom(smSRC, LinkEnum.MERGE_LINK, target_p);
      
      if (!hasBeenAlreadyAdded) { // Thus let's add it to the target Scenario
        smTGT = (SequenceMessage) CapellaCopier.copyObject(smSRC, false);
        // Add it to the target Scenario
        target_p.getOwnedMessages().add(smTGT);
        // Add Merge Link
        MergeHelper.addMergeLink(smTGT, smSRC);
      } else {
        list = LinkUtils.getIncomingLinkTargetsFrom(smSRC, LinkEnum.MERGE_LINK, target_p);
        smTGT = (SequenceMessage) list.get(0);
      }
  
      // Set bound
      EStructuralFeature feature = SequenceMessageHelper.returnPositionOn(smSRC, meSRC);
      smTGT.eSet(feature, aeNEW);  
    }
    
    
    //
    // EXECUTION
    //
    
    updateCreateTimeLapse(aeSRC, aeNEW);
    
    return;
  }
  
  private void replaceAE(Scenario target_p, AbstractEnd ae_p) throws MergeToolException {

    //
    // We check whether some jobs have to be performed
    //

    boolean mustBeReplaced = true;

    // get the way to the target {RL_up} + ML_down
    List<AbstractTrace> ats = _navigator.navigateFromDataToTarget(target_p, ae_p);
    int sz = ats.size();

    if (ats.isEmpty()) { // 2nd and higher pass case...
      return;
    }

    // SRC
    AbstractEnd aeSRC = ae_p;
    InstanceRole irSRC = ae_p.getCovered();
    AbstractInstance aiSRC = irSRC.getRepresentedInstance();

    // TGT
    sz--;
    AbstractEnd aeTGT = (AbstractEnd) ats.get(sz).getSourceElement();
    InstanceRole irTGT = aeTGT.getCovered();
    AbstractInstance aiTGT = irTGT.getRepresentedInstance();

    // ORIGINATOR e.g. LAST from RL
    sz--;
    AbstractEnd aeORIGINATOR = (AbstractEnd) ats.get(sz).getTargetElement();
    InstanceRole irORIGINATOR = aeORIGINATOR.getCovered();
    AbstractInstance aiORIGINATOR = irORIGINATOR.getRepresentedInstance();

    mustBeReplaced = !aiSRC.equals(aiORIGINATOR) && aiORIGINATOR.equals(aiTGT);
    
    //
    // Main job
    //
    if (mustBeReplaced ) {

      //
      // New AE
      //
      AbstractEnd aeNEW = (AbstractEnd) CapellaCopier.copyObject(aeSRC, false);

      // Set InstanceRole(s)
      aeNEW.getCoveredInstanceRoles().clear();
      aeNEW.getCoveredInstanceRoles().add(getTargetIR(target_p, irSRC));

      // create connected event
      Event eventSRC = aeSRC.getEvent();
      Event eventTGT = aeTGT.getEvent();
      Event eventNEW = (Event) CapellaCopier.copyObject(eventSRC, true);
      
      // Set event
      aeNEW.setEvent(eventNEW);

      //Add it
      target_p.getOwnedEvents().add(eventNEW);
      
      // Remove old event
      MergeGarbage.INSTANCE.add(eventTGT);
      target_p.getOwnedEvents().remove(eventTGT);
      
      // Add Merge Link between Events
      MergeHelper.addMergeLink(eventNEW, eventSRC);

      // Replace Interaction Fragment
      replaceIFrag(aeTGT, aeNEW);

      // Add merge Link
      MergeHelper.addMergeLink(aeNEW, aeSRC);

      //
      // EXECUTION
      //

      Scenario data = (Scenario) aeSRC.eContainer();
      Execution exeSRC = null;
      Execution exeTGT = null;

      // Let's get potential Execution
      TimeLapse tl = TimeLapseHelper.isAnyTimeLapseUseThisIfrag(data, aeSRC);

      if (null != tl) {

        exeSRC = (Execution) tl;
        
        List<AbstractTrace> list = _navigator.navigateFromDataToTarget(target_p, exeSRC);

        // Last ML
        AbstractTrace ml = list.get(list.size() - 1);
        exeTGT = (Execution) ml.getSourceElement();

        EStructuralFeature feature = TimeLapseHelper.returnPositionOn(exeSRC, aeSRC);

        exeTGT.eSet(feature, aeNEW);

        // Add new ML
        if ( !LinkUtils.doesMergeLinkAlreadyExist(exeTGT, exeSRC, LinkEnum.MERGE_LINK) ) {
          MergeHelper.addMergeLink(exeTGT, exeSRC);
        }
        
        // Old ML is kept for navigation purpose
        if (!ml.getTargetElement().equals(exeSRC)) {
          MergeGarbage.INSTANCE.add(ml);
        }
      }

      //
      // SequenceMessage case for MessageEnd
      //

      if (InteractionPackage.Literals.MESSAGE_END == aeSRC.eClass()) {

        MessageEnd meSRC = (MessageEnd) aeSRC;
        SequenceMessage smSRC = meSRC.getMessage();

        EStructuralFeature feature = SequenceMessageHelper.returnPositionOn(smSRC, meSRC);
        
        SequenceMessage smTGT = null;

        MessageEnd meNEW = (MessageEnd) aeNEW;

        List<AbstractTrace> list = _navigator.navigateFromDataToTarget(target_p, smSRC);
        // Last ML
        AbstractTrace ml = list.get(list.size() - 1);
        smTGT = (SequenceMessage) ml.getSourceElement();

        if (null != feature) { // set SequenceMessage attributes
          smTGT.eSet(feature, meNEW);
        } else {
          //TODO more explicit message
          throw new MergeToolException(MergeMessages.genericToolError);
        }
        
        // Add new ML whether no ML has been already put
        
        boolean alreadyPut = false;
        for (TraceableElement target: LinkUtils.getOutgoingLinkTargets(smTGT, LinkEnum.MERGE_LINK) ) {
          if (target.equals(smSRC)) {
            alreadyPut = true;
            break;
          }
        }
        if (!alreadyPut) {
          MergeHelper.addMergeLink(smTGT, smSRC);
        }

      }

    } // END MAIN CONTROL

    return;
  }
  
  /**
   * Replace {@link InteractionFragment} in a Scenario
   * @param ifragToReplace_p
   * @param newIFrag_p
   */
  private void replaceIFrag(InteractionFragment ifragToReplace_p, InteractionFragment newIFrag_p) {
 
    //
    // Let's remove traces
    //
    LinkUtils.removeAllLinksOn(ifragToReplace_p);
    
    //
    // The main job
    //
    
    Scenario sc = (Scenario) ifragToReplace_p.eContainer();
    
    int pos = sc.getOwnedInteractionFragments().indexOf(ifragToReplace_p);
    sc.getOwnedInteractionFragments().set(pos, newIFrag_p);
 
    return;
  }
  
  /**
   * @see #getTargetIR(Scenario, InstanceRole)
   */
  public static List<InstanceRole> getTargetIR(Scenario target_p, List<InstanceRole> irs_p) {
    
    List<InstanceRole> result = new ArrayList<InstanceRole>();
    
    InstanceRole irToAdd = null;
    for (InstanceRole ir: irs_p) {
      irToAdd = getTargetIR(target_p, ir);
      if (null != irToAdd) {
        result.add(irToAdd);
      }
    }
    
    return result;
  }
  
  /**
   * For a given {@link InstanceRole} on data {@link Scenario}, return its the target {@link InstanceRole} on
   * the target {@link Scenario}. Note that this method is based on {@link MergeLink}.
   * @param target_p, the target {@link Scenario}
   * @param ir_p the {@link IntanceRole} to check 
   * @return <code>null</code> whether no corresponding element on the target {@link Scenario}.
   */
  public static InstanceRole getTargetIR(Scenario target_p, InstanceRole ir_p) {
    
    InstanceRole result = null;
        
    InstanceRole ir = null;
    for (InstanceRole irOnTGT: target_p.getOwnedInstanceRoles()) {
      ir = (InstanceRole) LinkUtils.getOutgoingLinkTargets(irOnTGT, LinkEnum.MERGE_LINK).get(0);
      if ( ir.equals(ir_p)) {
        result = irOnTGT;
        break;
      }      
    }

    if ( null == result ) {
      AbstractInstance ai = ir_p.getRepresentedInstance();
      AbstractInstance aiTGT = null;
      for (InstanceRole ir2: target_p.getOwnedInstanceRoles()) {
        aiTGT = ir2.getRepresentedInstance();
        if (aiTGT.equals(ai)) {
          result = ir2;
          break;
        }
    }
    }
    
    return result;
  }
  
  /**
   * Get the position of a given {@link InteractionFragment} on target {@link Scenario}.
   * The {@link InteractionFragment} could be null (then we return 0 as result.) 
   * @param target_p the target {@link Scenario}
   * @param anchorSRC_p the {@link InteractionFragment} on data {@link Scenario}
   * @return the position of corresponding {@link InteractionFragment} on target {@link Scenario}.
   * @throws MergeToolException
   */
  private int getPostionOnTarget(Scenario target_p, InteractionFragment anchorSRC_p) throws MergeToolException {
    
    int pos = 0; // For null anchor e.g. beginning of the InteractionFragment list
    if ( null != anchorSRC_p) {
      
      List<AbstractTrace> traces = _navigator.navigateFromDataToTarget(target_p, anchorSRC_p);
      
      InteractionFragment anchorTGT = (InteractionFragment) traces.get(traces.size()-1).getSourceElement();
      
      pos = target_p.getOwnedInteractionFragments().indexOf(anchorTGT) + 1;
    }
    
    return pos;
  }

  /**
   * Update/create the connected {@link TimeLapse}.
   * @param ifragSRC_p the source {@link InteractionFragment} e.g. on data {@link Scenario}.
   * @param ifragTGT_p the target {@link InteractionFragment} e.g. on target {@link Scenario}.
   * @return array containing the source and the target {@link TimeLapse}.
   * @throws MergeToolException
   */
  private TimeLapse[] updateCreateTimeLapse(InteractionFragment ifragSRC_p, InteractionFragment ifragTGT_p) throws MergeToolException {
  
    TimeLapse[] tls = new TimeLapse[2];
    
    Scenario data = (Scenario) ifragSRC_p.eContainer();
    Scenario target = (Scenario) ifragTGT_p.eContainer();
    
    TimeLapse tlSRC = null;
    TimeLapse tlTGT = null;

    // Let's get potential Execution
    tlSRC = TimeLapseHelper.isAnyTimeLapseUseThisIfrag(data, ifragSRC_p);

    if (null != tlSRC) {
      
      boolean hasBeenAlreadyAdded = LinkUtils.hasIncomingLinksFrom(tlSRC, LinkEnum.MERGE_LINK, target);
      
      if (!hasBeenAlreadyAdded) { // Thus let's add it to the target Scenario
        tlTGT = (TimeLapse) CapellaCopier.copyObject(tlSRC, false);
        
        if (tlTGT.eClass() == InteractionPackage.Literals.INTERACTION_USE) {
          InteractionUse iuSRC = (InteractionUse) tlSRC;
          InteractionUse uiTGT = (InteractionUse) tlTGT;
          uiTGT.setReferencedScenario(iuSRC.getReferencedScenario());
        }
        
        // Add it to the target Scenario
        target.getOwnedTimeLapses().add(tlTGT);
        // Add Merge Link
        MergeHelper.addMergeLink(tlTGT, tlSRC);
      } else {
        
        List<TraceableElement> list = LinkUtils.getIncomingLinkTargetsFrom(tlSRC, LinkEnum.MERGE_LINK, target);
        tlTGT = (TimeLapse) list.get(0);
        
      }
      
      EStructuralFeature feature = TimeLapseHelper.returnPositionOn(tlSRC, ifragSRC_p);

      tlTGT.eSet(feature, ifragTGT_p);
    }
    
    //Set return
    tls[0] = tlSRC;
    tls[1] = tlTGT;
    
    return tls;
  }
  
}

