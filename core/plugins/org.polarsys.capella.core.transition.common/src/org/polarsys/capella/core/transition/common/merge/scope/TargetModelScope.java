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

package org.polarsys.capella.core.transition.common.merge.scope;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * A model scope covering target model scope.
 */
public class TargetModelScope extends ContextModelScope implements ITargetModelScope {

  public TargetModelScope(List<? extends EObject> elements, IContext context) {
    super(elements, context);
  }

  @Override
  public Object getOriginator() {
    return "Resulting model";
  }

  public Collection<EObject> retrieveTransformedElementsFromTarget(EObject targetElement) {

    ITraceabilityHandler sourceHandler = (ITraceabilityHandler) context
        .get(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER);
    ITraceabilityHandler targetHandler = (ITraceabilityHandler) context
        .get(ITransitionConstants.TRACEABILITY_TARGET_MERGE_HANDLER);

    Collection<EObject> bounds = targetHandler.retrieveSourceElements(targetElement, context);

    if (!bounds.isEmpty()) {
      Iterator<EObject> objects = bounds.iterator();
      if (objects.hasNext()) {
        // Retrieve a transformed element which is linked to all sourceElements.
        EObject object = objects.next();
        Collection<EObject> retains = sourceHandler.retrieveTracedElements(object, context);
        while (objects.hasNext()) {
          object = objects.next();
          retains.retainAll(sourceHandler.retrieveTracedElements(object, context));
        }
        return retains;
      }
    }

    return Collections.emptyList();

  }

  @Override
  protected List<EObject> retains(List<EObject> object) {
    // If object is contained into resource of scope, but not in the scope we need to exclude it.
    // Otherwise, if it is contained into an other resource, we can add it. (this can happen using the traceability
    // link)
    EObject sourceRoot = (EObject) context.get(ITransitionConstants.TRANSITION_SOURCE_ROOT);

    for (EObject obj : new ArrayList<EObject>(object)) {
      if (!_inScope.contains(obj)) {
        if (!(EcoreUtil2.isOrIsContainedBy(obj, sourceRoot))) {
          object.remove(obj);
        }
      }
    }

    return object;
  }

}
