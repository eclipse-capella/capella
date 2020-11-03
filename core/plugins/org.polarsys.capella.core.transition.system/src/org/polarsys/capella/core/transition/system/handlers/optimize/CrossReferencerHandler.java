/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.system.handlers.optimize;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.model.handler.helpers.CrossReferencerHelper;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * <p>
 * Delete this handler is later version.
 * </p>
 * This handler perform a ResolveAll on crossReferencer at initialization
 * and forbid resolution while transition. 
 * It restore initial state of resolution at dispose.
 * 
 *
 */
@Deprecated
public class CrossReferencerHandler implements IHandler {

  private boolean isEnabled = true;

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  @Override
  public IStatus init(IContext context) {
    isEnabled = CrossReferencerHelper.resolutionEnabled();
    if (isEnabled) {
      EcoreUtil.resolveAll(TransactionHelper.getEditingDomain((Collection<? extends EObject>) context.get(ITransitionConstants.TRANSITION_SOURCES)).getResourceSet());
    }
    CrossReferencerHelper.enableResolution(false);
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus dispose(IContext context) {
    CrossReferencerHelper.enableResolution(isEnabled);
    return Status.OK_STATUS;
  }

}
