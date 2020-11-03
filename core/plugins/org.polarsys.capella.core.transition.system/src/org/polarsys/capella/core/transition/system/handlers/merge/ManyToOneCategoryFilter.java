/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.handlers.merge;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.diffmerge.diffdata.EElementPresence;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class ManyToOneCategoryFilter extends CategoryFilter {

  public ManyToOneCategoryFilter(IContext context) {
    super(context, Messages.ManyToOneCategoryFilter, Messages.ManyToOneCategoryFilter_Description);
    setCategorySet(ITransitionConstants.CATEGORY_BUSINESS);
    setInFocusMode(false);
    setActive(true);
    setVisible(true);
  }

  @Override
  public boolean covers(IDifference<EObject> difference) {
    // We exclude the difference if the source element or one of its parent is realized by an element realizing many
    // elements

    if (difference instanceof EElementPresence) {
      EElementPresence current = (EElementPresence) difference;

      while (current != null) {
        EObject container = current.getElementMatch().get(Role.REFERENCE);
        if (container == null) {
          break;
        }

        ITraceabilityHandler sourceHandler = (ITraceabilityHandler) context
            .get(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER);
        ITraceabilityHandler targetHandler = (ITraceabilityHandler) context
            .get(ITransitionConstants.TRACEABILITY_TARGET_MERGE_HANDLER);

        Collection<EObject> nbElements = new HashSet<EObject>();
        Collection<EObject> sources = sourceHandler.retrieveSourceElements(container, context);
        for (EObject source : sources) {
          for (EObject target : targetHandler.retrieveTracedElements(source, context)) {
            nbElements.addAll(targetHandler.retrieveSourceElements(target, context));
          }
        }

        if (nbElements.size() > 1) {
          return true;
        }

        IMatch<EObject> containerMatch = current.getComparison().getMapping().getMatchFor(container.eContainer(),
            Role.REFERENCE);
        if (containerMatch != null) {
          current = (EElementPresence) containerMatch.getElementPresenceDifference();
        } else {
          current = null;
        }
      }
    }
    return false;
  }

}
