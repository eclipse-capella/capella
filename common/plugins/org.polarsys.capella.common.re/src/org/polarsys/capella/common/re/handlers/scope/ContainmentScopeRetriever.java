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
import java.util.HashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.core.transition.common.handlers.scope.IScopeRetriever;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ContainmentScopeRetriever implements IScopeRetriever {

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

    Collection<EObject> referencedElements = new HashSet<EObject>();
    if (!isFilteredElement(element_p, context_p)) {

      for (EReference reference : element_p.eClass().getEAllReferences()) {
        if (reference.isContainment() && !isFilteredReference(reference, element_p, context_p)) {

          if (reference.isMany()) {
            referencedElements.addAll((EList) element_p.eGet(reference));
          } else {
            referencedElements.add((EObject) element_p.eGet(reference));
          }
        }
      }
    }

    return referencedElements;
  }

  /**
   * @param element_p
   * @param context_p
   * @return
   */
  protected boolean isFilteredElement(EObject element_p, IContext context_p) {
    return element_p instanceof CatalogElement;
  }

  /**
   * @param reference_p
   * @param element_p
   * @param context_p
   * @return
   */
  protected boolean isFilteredReference(EReference reference_p, EObject element_p, IContext context_p) {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<? extends EObject> retrieveSharedElements(IContext context_p) {
    return null;
  }

}
