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
package org.polarsys.capella.core.transition.system.handlers.filter;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.filter.AbstractFilterItem;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ElementPresenceOneToMany extends AbstractFilterItem {

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDescription(IDifference difference_p) {
    return "A source element is traced many elements in target scope";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FilterAction getDestinationRole(IDifference difference_p, Role role_p, IContext context_p) {
    if (difference_p instanceof IReferenceValuePresence) {
      // We merge ElementPresence only if it from source
      if (role_p == Role.REFERENCE) {

        if (difference_p instanceof IReferenceValuePresence) {
          EObject container = ((IReferenceValuePresence) difference_p).getElementMatch().get(Role.REFERENCE);

          ITraceabilityHandler sourceHandler = (ITraceabilityHandler) context_p.get(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER);
          ITraceabilityHandler targetHandler = (ITraceabilityHandler) context_p.get(ITransitionConstants.TRACEABILITY_TARGET_MERGE_HANDLER);

          Collection<EObject> nbElements = new HashSet<EObject>();
          Collection<EObject> sources = sourceHandler.retrieveSourceElements(container, context_p);
          for (EObject source : sources) {
            nbElements.addAll(targetHandler.retrieveTracedElements(source, context_p));
          }

          if (nbElements.size() > 1) {
            return FilterAction.NO_ACTION;
          }
        }
      }
    }
    return null;
  }

}
