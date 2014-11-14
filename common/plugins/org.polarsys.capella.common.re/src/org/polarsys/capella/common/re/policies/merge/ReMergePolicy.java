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
package org.polarsys.capella.common.re.policies.merge;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ReMergePolicy extends org.polarsys.capella.core.transition.common.policies.merge.ExtMergePolicy {

  /**
   * @param context_p
   */
  public ReMergePolicy(IContext context_p) {
    super(context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean copy(EObject source_p) {
    IContext context = getContext();
    if (ReplicableElementHandlerHelper.getInstance(context).getReplica(context) == null) {
      return !ContextScopeHandlerHelper.getInstance(context).contains(IReConstants.UNMERGEABLE_ELEMENTS, source_p, context);
    }

    return super.copy(source_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean copyPhysicalIds() {
    return false;
  }

  @Override
  protected boolean useNewEcoreIds() {
    return true;
  }

}
