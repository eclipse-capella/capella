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
package org.polarsys.capella.core.transfo.operationalactivity.rules;

import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.projection.common.rules.fa.Rule_FunctionPkg;

/**
 */
public class Rule_OperationalActivityPkg extends Rule_FunctionPkg {

  public Rule_OperationalActivityPkg() {
    super(OaPackage.Literals.OPERATIONAL_ACTIVITY_PKG, CtxPackage.Literals.SYSTEM_FUNCTION_PKG);
    setNeedsContext(true);
  }

}
