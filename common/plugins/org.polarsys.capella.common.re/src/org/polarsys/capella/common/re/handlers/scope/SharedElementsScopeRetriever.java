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
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class SharedElementsScopeRetriever implements IScopeRetriever {

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus init(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus dispose(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<? extends EObject> retrieveRelatedElements(EObject element_p, IContext context_p) {

    //We retrieve shared elements only for elements in the initial source scope.
    if (!ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.INITIAL_SOURCE_SCOPE, element_p, context_p)) {
      return Collections.emptyList();
    }

    //For catalog elements used in the REC, we don't retrieve shared elements. (shared elements will be in that case theirs REC)
    if (element_p instanceof CatalogElement) {
      return Collections.emptyList();
    }

    Collection<EObject> referencedElements = new HashSet<EObject>();
    for (EReference reference : element_p.eClass().getEAllReferences()) {
      if (reference.isDerived() || reference.isContainment() || reference.isContainer() || reference.isTransient() || (reference.getEOpposite() != null)) {
        continue;
      }

      if (reference.isMany()) {
        referencedElements.addAll((EList) element_p.eGet(reference));
      } else {
        referencedElements.add((EObject) element_p.eGet(reference));
      }
    }

    return referencedElements;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<? extends EObject> retrieveSharedElements(IContext context_p) {
    return null;
  }

}
