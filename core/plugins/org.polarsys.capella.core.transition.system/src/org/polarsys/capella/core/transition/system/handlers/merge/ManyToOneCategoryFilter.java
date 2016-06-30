/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.system.handlers.merge;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class ManyToOneCategoryFilter extends CategoryFilter {

  public ManyToOneCategoryFilter(IContext context) {
    super(context, Messages.ManyToOneCategoryFilter, null);
    setInFocusMode(false);
    setActive(true);
    setVisible(true);
  }

  @Override
  public boolean covers(IDifference difference) {
    // We merge ElementPresence only if it from source

    if (difference instanceof IElementPresence) {
      EObject container = ((IElementRelativeDifference) difference).getElementMatch().get(Role.REFERENCE);
      if (container != null) {

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
      }
    }
    return false;
  }

}
