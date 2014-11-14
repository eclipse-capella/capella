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
package org.polarsys.capella.core.transition.system.topdown.handlers.filter;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IMergeableDifference;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.filter.AbstractFilterItem;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.system.topdown.handlers.transformation.TopDownTransformationHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class RemoveRealizedFilterItem extends AbstractFilterItem {

  protected BlockArchitecture getSourceArchitecture(EObject source_p, IContext context_p) {

    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(source_p);
    Collection<EObject> selection = (Collection<EObject>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
    if (selection.size() > 0) {
      EObject source = (EObject) selection.toArray()[0];
      architecture = BlockArchitectureExt.getRootBlockArchitecture(source);
    }

    return architecture;
  }

  private boolean isTrace(EObject element_p, IContext context_p) {
    return TopDownTransformationHelper.getInstance(context_p).isTrace(element_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FilterAction getDestinationRole(IDifference difference_p, Role role_p, IContext context_p) {
    Collection<IDifference> toVisit = new ArrayList<IDifference>();

    if (role_p == Role.TARGET) {
      toVisit.add(difference_p);
    } else if (difference_p instanceof IMergeableDifference) {
      toVisit.addAll(((IMergeableDifference) difference_p).getDirectRequiresDependencies(role_p));
    }

    for (IDifference diff : toVisit) {
      if (diff instanceof IReferenceValuePresence) {
        EObject container = ((IReferenceValuePresence) diff).getElementMatch().get(Role.TARGET);
        if (!isTrace(container, context_p)) {
          EObject target = ((IReferenceValuePresence) diff).getValue().get(Role.TARGET);
          EObject blockArch = getSourceArchitecture(target, context_p);
          EObject targetArch = BlockArchitectureExt.getRootBlockArchitecture(target);

          if (blockArch.eClass().isInstance(targetArch)) {
            ITraceabilityHandler sourceHandler = (ITraceabilityHandler) context_p.get(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER);
            if (!sourceHandler.retrieveTracedElements(target, context_p).isEmpty()) {
              return FilterAction.TARGET;
            }
          }
        }

      }
    }
    return null;
  }

}
