/*******************************************************************************
 * Copyright (c) 2016, 2017 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.system.topdown.handlers.merge;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.system.topdown.handlers.transformation.TopDownTransformationHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * This category adds dependencies between IReferenceValuePresence: When an element is still referencing an element A
 * located in the source architecture whereas A is also realized in the target architecture (A'), we remove the
 * reference to A when we merge difference towards A'
 */
public class RemoveRealizedCategoryFilter extends CategoryFilter {

  public static final String ID = RemoveRealizedCategoryFilter.class.getCanonicalName();

  public RemoveRealizedCategoryFilter(IContext context) {
    super(context, ID, Messages.RemoveRealizedCategoryFilter, Messages.RemoveRealizedCategoryFilter_Description, null);
    setCategorySet(ITransitionConstants.CATEGORY_BUSINESS);
    setInFocusMode(true);
    setVisible(false);
    setActive(false);
  }

  protected boolean isTrace(EObject element, IContext context) {
    return TopDownTransformationHelper.getInstance(context).isTrace(element, context);
  }

  protected BlockArchitecture getSourceArchitecture(EObject source_p, IContext context_p) {

    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(source_p);
    Collection<EObject> selection = (Collection<EObject>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
    if (selection.size() > 0) {
      EObject source = (EObject) selection.toArray()[0];
      architecture = BlockArchitectureExt.getRootBlockArchitecture(source);
    }

    return architecture;
  }

  @Override
  public void setDependencies(IMergeableDifference<EObject> difference) {
    super.setDependencies(difference);

    if (difference instanceof EReferenceValuePresence) {
      EReferenceValuePresence presence = (EReferenceValuePresence) difference;

      IMatch<EObject> element = presence.getElementMatch();
      IMatch<EObject> value = presence.getValueMatch();
      EObject valueElement = value.get(Role.REFERENCE);
      if (valueElement == null) {
        return;
      }
      Collection<IReferenceValuePresence<EObject>> diffs = element.getReferenceDifferences(presence.getFeature());
      for (IReferenceValuePresence<EObject> diff : diffs) {
        IMatch<EObject> ei2 = diff.getValueMatch();
        EObject eiTransfo2 = ei2.get(Role.TARGET);
        BlockArchitecture architecture = getSourceArchitecture(valueElement, context);
        BlockArchitecture current = BlockArchitectureExt.getRootBlockArchitecture(eiTransfo2);

        if (current != null && current.eClass().isInstance(architecture)) {
          ITraceabilityHandler sourceHandler = (ITraceabilityHandler) context
              .get(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER);
          Collection<EObject> sources = sourceHandler.retrieveSourceElements(valueElement, context);
          Collection<EObject> sources2 = Collections.singletonList(eiTransfo2);
          if (sources2.containsAll(sources)) {
            if (diff != difference) {
              ((IMergeableDifference.Editable) diff).markRequires((IMergeableDifference) difference, Role.TARGET);
              ((IMergeableDifference.Editable) difference).markRequires(diff, Role.TARGET);
            }
          }
        }
      }
    }
  }

  @Override
  public boolean covers(IDifference<EObject> difference) {
    return difference instanceof EReferenceValuePresence
        && ((EReferenceValuePresence) difference).getElementMatch().get(Role.REFERENCE) != null;
  }

}
