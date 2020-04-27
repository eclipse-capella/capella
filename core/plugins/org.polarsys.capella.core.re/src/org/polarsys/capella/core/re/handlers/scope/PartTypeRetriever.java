/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.handlers.scope;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.transition.common.handlers.scope.IScopeRetriever;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class PartTypeRetriever implements IScopeRetriever {

  @Override
  public IStatus init(IContext context_p) {
    return Status.OK_STATUS;
  }

  @Override
  public IStatus dispose(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<? extends EObject> retrieveRelatedElements(EObject element_p, IContext context_p) {
    if (element_p instanceof Part) {
      return Collections.singleton(((Part) element_p).getAbstractType());
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<? extends EObject> retrieveSharedElements(IContext context_p) {
    return null;
  }

}
