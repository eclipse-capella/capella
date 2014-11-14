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
package org.polarsys.capella.core.transition.system.handlers.optimize;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.polarsys.capella.core.model.handler.helpers.CrossReferencerHelper;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * This handler perform a ResolveAll on crossReferencer at initialization
 * and forbid resolution while transition. 
 * It restore initial state of resolution at dispose.
 *
 */
public class CrossReferencerHandler implements IHandler {

  private boolean isEnabled = true;

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus init(IContext context_p) {
    isEnabled = CrossReferencerHelper.resolutionEnabled();
    if (isEnabled) {
      EcoreUtil.resolveAll(MDEAdapterFactory.getResourceSet());
    }
    CrossReferencerHelper.enableResolution(false);
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus dispose(IContext context_p) {
    CrossReferencerHelper.enableResolution(isEnabled);
    return Status.OK_STATUS;
  }

}
