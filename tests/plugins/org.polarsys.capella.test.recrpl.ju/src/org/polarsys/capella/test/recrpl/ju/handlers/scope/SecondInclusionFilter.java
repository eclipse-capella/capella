/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.recrpl.ju.handlers.scope;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.transition.common.handlers.scope.IScopeFilter;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class SecondInclusionFilter implements IScopeFilter {

  @Override
  public IStatus init(IContext context) {
    return Status.OK_STATUS;
  }

  @Override
  public IStatus dispose(IContext context) {
    return Status.OK_STATUS;
  }

  @Override
  public boolean isValidScopeElement(EObject element, IContext context) {
    if (!ExternalInclusion.isEnabled()) {
      return false;
    }
    return ExternalInclusion.ACTOR_2.equals(((SystemComponent) element).getId());
  }

}
