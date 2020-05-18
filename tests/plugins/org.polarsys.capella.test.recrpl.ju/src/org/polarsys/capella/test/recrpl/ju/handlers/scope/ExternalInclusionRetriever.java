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
package org.polarsys.capella.test.recrpl.ju.handlers.scope;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.transition.common.handlers.scope.IScopeRetriever;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class ExternalInclusionRetriever implements IScopeRetriever {

  @Override
  public IStatus init(IContext context) {

    return Status.OK_STATUS;
  }

  @Override
  public IStatus dispose(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<? extends EObject> retrieveRelatedElements(EObject element, IContext context) {
    Collection<EObject> result = new ArrayList<EObject>();
    if (!ExternalInclusion.isEnabled()) {
      return result;
    }

    if (element instanceof LogicalComponent) {
      Resource resource = ExternalInclusion.getExternalResource(element);
      result.add(resource.getEObject(ExternalInclusion.ACTOR_1));
      result.add(resource.getEObject(ExternalInclusion.ACTOR_2));
      result.add(resource.getEObject(ExternalInclusion.ACTOR_3));
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
