/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class OneToManyCategoryFilter extends CategoryFilter {

  public OneToManyCategoryFilter(IContext context) {
    super(context, Messages.OneToManyCategoryFilter, Messages.OneToManyCategoryFilter_Description);
    setCategorySet(ITransitionConstants.CATEGORY_BUSINESS);
    setInFocusMode(false);
    setActive(true);
    setVisible(true);
  }

  @Override
  public boolean covers(IDifference<EObject> difference) {
    // We merge ElementPresence only if it from source

    if (difference instanceof EReferenceValuePresence) {
      EObject container = ((EReferenceValuePresence) difference).getElementMatch().get(Role.REFERENCE);
      if (container != null) {

        ITraceabilityHandler sourceHandler = (ITraceabilityHandler) context
            .get(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER);
        ITraceabilityHandler targetHandler = (ITraceabilityHandler) context
            .get(ITransitionConstants.TRACEABILITY_TARGET_MERGE_HANDLER);

        Collection<EObject> nbElements = new HashSet<EObject>();
        Collection<EObject> sources = sourceHandler.retrieveSourceElements(container, context);
        for (EObject source : sources) {
          nbElements.addAll(targetHandler.retrieveTracedElements(source, context));
        }

        if (nbElements.size() > 1) {
          return true;
        }
      }
    }
    return false;
  }

}
