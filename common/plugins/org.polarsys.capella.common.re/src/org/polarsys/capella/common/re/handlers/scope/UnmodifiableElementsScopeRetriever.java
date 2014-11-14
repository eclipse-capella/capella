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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.scope.IScopeRetriever;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class UnmodifiableElementsScopeRetriever implements IScopeRetriever {

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
    CatalogElement source = ReplicableElementHandlerHelper.getInstance(context_p).getSource(context_p);
    CatalogElement target = ReplicableElementHandlerHelper.getInstance(context_p).getTarget(context_p);

    Collection<EObject> result = new ArrayList<EObject>();

    Collection<CatalogElement> elements =
        ReplicableElementHandlerHelper.getInstance(context_p).getIndirectlyReplicableElements(context_p, (Collection) Collections.singletonList(element_p));
    elements.remove(source);
    elements.remove(target);
    elements.remove(element_p);

    if (!elements.isEmpty()) {
      ContextScopeHandlerHelper.getInstance(context_p).add(IReConstants.UNMODIFIABLE_ELEMENTS, element_p, context_p);
      for (CatalogElement ge : elements) {
        //We should add only child replicable elements
        ///   result.add(ge);
      }
    }

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<? extends EObject> retrieveSharedElements(IContext context_p) {
    return null;
  }

}
