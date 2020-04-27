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
    CatalogElement source = ReplicableElementHandlerHelper.getInstance(context).getSource(context);
    CatalogElement target = ReplicableElementHandlerHelper.getInstance(context).getTarget(context);

    Collection<EObject> result = new ArrayList<EObject>();

    Collection<CatalogElement> elements =
        ReplicableElementHandlerHelper.getInstance(context).getIndirectlyReplicableElements(context, (Collection) Collections.singletonList(element));
    elements.remove(source);
    elements.remove(target);
    elements.remove(element);

    if (!elements.isEmpty()) {
      ContextScopeHandlerHelper.getInstance(context).add(IReConstants.UNMODIFIABLE_ELEMENTS, element, context);
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
  public Collection<? extends EObject> retrieveSharedElements(IContext context) {
    return null;
  }

}
