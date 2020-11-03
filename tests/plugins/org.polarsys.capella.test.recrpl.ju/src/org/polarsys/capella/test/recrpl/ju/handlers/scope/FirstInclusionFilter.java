/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.transition.common.handlers.scope.IScopeFilter;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class FirstInclusionFilter implements IScopeFilter {

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
    return ExternalInclusion.ACTOR_1.equals(((SystemComponent) element).getId());
  }

}
