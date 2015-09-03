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
package org.polarsys.capella.core.refinement.merge.merger.operations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MergeLink;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.core.refinement.merge.exception.MergeToolException;
import org.polarsys.capella.core.refinement.merge.helpers.TimeLapseHelper;
import org.polarsys.capella.core.refinement.merge.messages.MergeMessages;
import org.polarsys.capella.core.refinement.merge.utils.LinkEnum;
import org.polarsys.capella.core.refinement.merge.utils.LinkUtils;
import org.polarsys.capella.core.refinement.merge.utils.MergeGarbage;

/**
 * Copy a Scenario
 *
 */
public class SeekAndRemoveUnmergeableElements extends AbstractMergerOperation {

  protected Scenario _result;

  protected Set<EObject> _objectsToDelete;

  public Scenario getResult() {
    return _result;
  }

  private Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.REFINEMENT);

  /**
   * Constructor
   * 
   * @param source
   *          the {@link Scenario} to copy
   * @param shouldAddMergeLink
   *          should operation add {@link MergeLink} to the copy
   */
  public SeekAndRemoveUnmergeableElements(Scenario result) {
    _result = result;

  }

  /**
   * @see org.polarsys.capella.core.common.refinement.merge.merger.operations.IMergerOperation#perform()
   */
  @Override
  public void perform() throws MergeToolException {

    if (!_objectsToDelete.isEmpty()) {

      //
      // Main warning
      //

      _logger.warn(new EmbeddedMessage(MergeMessages.cannotPerformAFullMerge,
          IReportManagerDefaultComponents.REFINEMENT, LinkUtils.getOutgoingLinkTargets(_result, LinkEnum.MERGE_LINK)));

      //
      // Information
      //
      for (EObject eobject : _objectsToDelete) {

        if (eobject.eClass() == InteractionPackage.Literals.SEQUENCE_MESSAGE) {

          _logger.info(new EmbeddedMessage(NLS.bind(MergeMessages.objectRemovedDueToLackOfData, new Object[] {
              eobject.eClass().getName(), ((NamedElement) eobject).getName() }),
              IReportManagerDefaultComponents.REFINEMENT, LinkUtils.getOutgoingLinkTargets((TraceableElement) eobject,
                  LinkEnum.MERGE_LINK)));
        }

      }

    }

    List<EObject> list = new ArrayList<EObject>();
    list.addAll(_objectsToDelete);

    MergeGarbage.INSTANCE.delete(list);

    return;
  }

  /**
   * @see org.polarsys.capella.core.common.refinement.merge.merger.operations.IMergerOperation#postOperation()
   */
  @Override
  public void postOperation() throws MergeToolException {

    _objectsToDelete.clear();

    return;
  }

  /**
   * @see org.polarsys.capella.core.common.refinement.merge.merger.operations.AbstractMergerOperation#preOperation()
   */
  @Override
  public void preOperation() throws MergeToolException {

    _objectsToDelete = new HashSet<EObject>();

    //
    // First, we check SequenceMessage
    //

    boolean mustBeRemoved;
    AbstractEnd ae = null;
    for (SequenceMessage sm : _result.getOwnedMessages()) {

      mustBeRemoved = false;

      AbstractEnd aes[] = { sm.getSendingEnd(), sm.getReceivingEnd() };

      for (int i = 0; i < aes.length; i++) {
        ae = aes[i];
        if (null == ae.getCovered()) {
          mustBeRemoved = true;
        }
      }

      if (mustBeRemoved) {
        _objectsToDelete.add(sm); // The current SequenceMessages
        _objectsToDelete.add(sm.getSendingEnd());
        _objectsToDelete.add(sm.getSendingEnd().getEvent());
        _objectsToDelete.add(sm.getReceivingEnd());
        _objectsToDelete.add(sm.getReceivingEnd().getEvent());
      }

    }

    // Execution to delete

    List<Execution> executionsToRemove = new ArrayList<Execution>();
    List<TimeLapse> executions = TimeLapseHelper.getTimeLapseOfType(_result, InteractionPackage.Literals.EXECUTION);

    for (TimeLapse tl : executions) {
      Execution execution = (Execution) tl;
      if (null == execution.getCovered() || _objectsToDelete.contains(execution.getStart())
          || _objectsToDelete.contains(execution.getFinish())) {
        executionsToRemove.add(execution);
      }
    }

    //
    // add Executions to deletion ops
    //

    for (Execution execution : executionsToRemove) {
      _objectsToDelete.add(execution); // The current execution
      for (EStructuralFeature feature : TimeLapseHelper.getBoundFeaturesList()) {
        ae = (AbstractEnd) execution.eGet(feature);
        _objectsToDelete.add(ae); // its bound AbstractEnds
        _objectsToDelete.add(ae.getEvent()); // and their events
      }
    }

    //
    // once again for StateFragment
    //

    List<StateFragment> stateFragmentToRemove = new ArrayList<StateFragment>();
    List<TimeLapse> stateFragments = TimeLapseHelper.getTimeLapseOfType(_result,
        InteractionPackage.Literals.STATE_FRAGMENT);

    for (TimeLapse tl : stateFragments) {
      StateFragment stateFragment = (StateFragment) tl;
      if (null == stateFragment.getStart() || null == stateFragment.getStart().getCoveredInstanceRoles()
          || stateFragment.getStart().getCoveredInstanceRoles().isEmpty()
          || null == stateFragment.getStart().getCoveredInstanceRoles().get(0) || null == stateFragment.getFinish()
          || null == stateFragment.getFinish().getCoveredInstanceRoles()
          || stateFragment.getFinish().getCoveredInstanceRoles().isEmpty()
          || null == stateFragment.getFinish().getCoveredInstanceRoles().get(0)) {
        stateFragmentToRemove.add(stateFragment);
      }
    }

    //
    // add StateFragment to deletion ops
    //

    for (StateFragment stateFragment : stateFragmentToRemove) {
      _objectsToDelete.add(stateFragment); // The current execution
      for (EStructuralFeature feature : TimeLapseHelper.getBoundFeaturesList()) {
        _objectsToDelete.add((EObject) stateFragment.eGet(feature));
      }
    }

    //
    // add merge links to delete
    //
    for (AbstractTrace ir : _result.getOwnedTraces()) {
      if (!isValidLinkKind(ir, _result))
        _objectsToDelete.add(ir);
    }
    return;
  }

  public boolean isValidLinkKind(AbstractTrace link, Scenario scenario) {
    TraceableElement sourceType = link.getSourceElement();

    if (sourceType != null) {
      return scenario.equals(sourceType.eContainer());
    }
    return true;
  }
}
