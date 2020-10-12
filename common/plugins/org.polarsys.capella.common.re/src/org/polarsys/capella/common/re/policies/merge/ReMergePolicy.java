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

package org.polarsys.capella.common.re.policies.merge;

import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ReMergePolicy extends org.polarsys.capella.core.transition.common.policies.merge.ExtMergePolicy {

  /**
   * @param context
   */
  public ReMergePolicy(IContext context) {
    super(context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean copy(EObject source) {
    IContext context = getContext();

    if (ReplicableElementHandlerHelper.getInstance(context).getSource(context) == null) {
      return !ContextScopeHandlerHelper.getInstance(context).contains(IReConstants.UNMERGEABLE_ELEMENTS, source, context);
    }

    return super.copy(source);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean copyExtrinsicIDs(ITreeDataScope<EObject> sourceScope, ITreeDataScope<EObject> targetScope) {
    return false;
  }
  
  @Override
  protected boolean requiresNewIntrinsicID(EObject element, ITreeDataScope<EObject> scope) {
    return true;
  }

}
