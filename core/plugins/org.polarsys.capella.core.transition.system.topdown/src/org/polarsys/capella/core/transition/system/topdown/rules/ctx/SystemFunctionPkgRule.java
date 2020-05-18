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
package org.polarsys.capella.core.transition.system.topdown.rules.ctx;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class SystemFunctionPkgRule extends org.polarsys.capella.core.transition.system.topdown.rules.fa.FunctionPkgRule {

  @Override
  protected EClass getSourceType() {
    return CtxPackage.Literals.SYSTEM_FUNCTION_PKG;
  }

  @Override
  public EClass getTargetType(EObject element_p, IContext context_p) {
    return LaPackage.Literals.LOGICAL_FUNCTION_PKG;
  }
}
