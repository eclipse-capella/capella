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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink;
import org.polarsys.capella.core.transition.common.handlers.scope.IScopeFilter;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class AbstractTraceFilter implements IScopeFilter {

  @Override
  public IStatus init(IContext context_p) {
    return Status.OK_STATUS;
  }

  @Override
  public IStatus dispose(IContext context_p) {
    return Status.OK_STATUS;
  }

  @Override
  public boolean isValidScopeElement(EObject element_p, IContext context_p) {
    if (element_p instanceof AbstractTrace) {
      return false;
    }
    if (element_p instanceof PartDeploymentLink) {
      return false;
    }
    return true;
  }
}
