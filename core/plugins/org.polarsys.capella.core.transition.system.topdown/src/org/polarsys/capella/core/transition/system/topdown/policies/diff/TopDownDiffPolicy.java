/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.policies.diff;

import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.handlers.level.ILevelHandler.Level;
import org.polarsys.capella.core.transition.system.topdown.handlers.level.LevelHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * A match policy for diff/merge within pairs of corresponding elements.
 */
public class TopDownDiffPolicy extends org.polarsys.capella.core.transition.common.policies.diff.ExtDiffPolicy {

  /**
   * Constructor
   * @param a non-null mapping of corresponding elements whose further modifications will impact this policy
   */
  public TopDownDiffPolicy(IContext context_p2) {
    super(context_p2);
  }
  
  @Override
  public boolean considerOrdered(EStructuralFeature feature_p) {
    return super.considerOrdered(feature_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean coverMatch(IMatch match_p) {

    IContext context = getContext();
    EClass clazz = LevelHandlerHelper.getInstance(context).getLevel(context, Level.TARGET);

    EObject source = match_p.get(Role.REFERENCE);
    if (source != null) {
      BlockArchitecture archi = BlockArchitectureExt.getRootBlockArchitecture(source);
      if ((archi != null) && !(clazz.isInstance(archi))) {
        return false;
      }
    }

    source = match_p.get(Role.TARGET);
    if (source != null) {
      BlockArchitecture archi = BlockArchitectureExt.getRootBlockArchitecture(source);
      if ((archi != null) && !(clazz.isInstance(archi))) {
        return false;
      }
    }

    return super.coverMatch(match_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean coverMatchOnReference(IMatch match_p, EReference reference_p) {

    if (reference_p.isContainment()) {
      EObject source = match_p.get(Role.REFERENCE);
      if (source != null) {
        ITraceabilityHandler handler = (ITraceabilityHandler) getContext().get(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER);
        for (EObject sourceElement : handler.retrieveSourceElements(source, getContext())) {
          if (ContextScopeHandlerHelper.getInstance(getContext()).contains(ITopDownConstants.CONTEXT_SCOPE__AVOID_DIFF_ELEMENTS, sourceElement, getContext())) {
            return false;
          }
        }
      }
    }
    return true;
  }

}
