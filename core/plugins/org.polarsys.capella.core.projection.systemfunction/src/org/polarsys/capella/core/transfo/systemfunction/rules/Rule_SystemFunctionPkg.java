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
package org.polarsys.capella.core.transfo.systemfunction.rules;

import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.projection.common.rules.fa.Rule_FunctionPkg;

/**
 */
public class Rule_SystemFunctionPkg extends Rule_FunctionPkg {

  public Rule_SystemFunctionPkg() {
    super(CtxPackage.Literals.SYSTEM_FUNCTION_PKG, LaPackage.Literals.LOGICAL_FUNCTION_PKG);
    setNeedsContext(true);
  }

}
