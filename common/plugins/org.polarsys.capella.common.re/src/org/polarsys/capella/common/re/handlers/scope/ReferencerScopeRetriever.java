/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.re.handlers.scope;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.scope.IScopeRetriever;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ReferencerScopeRetriever implements IScopeRetriever {

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus init(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus dispose(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<? extends EObject> retrieveRelatedElements(EObject element, IContext context) {

    if (!ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.INITIAL_SOURCE_SCOPE, element, context)) {
      return Collections.emptyList();
    }

    Collection<EObject> referencedElements = new HashSet<EObject>();
    for (EReference reference : element.eClass().getEAllReferences()) {
      if (reference.isMany()) {
        referencedElements.addAll((EList) element.eGet(reference));
      } else {
        referencedElements.add((EObject) element.eGet(reference));
      }
    }
    referencedElements.add(element.eContainer());

    return referencedElements;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<? extends EObject> retrieveSharedElements(IContext context) {
    return null;
  }

}
