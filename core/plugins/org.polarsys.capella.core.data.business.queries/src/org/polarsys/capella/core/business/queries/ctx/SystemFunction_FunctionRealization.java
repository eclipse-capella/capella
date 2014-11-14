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
package org.polarsys.capella.core.business.queries.ctx;

import org.eclipse.emf.ecore.EClass;

import org.polarsys.capella.core.business.queries.fa.AbstractFunction_RealizedFunctions;
import org.polarsys.capella.core.data.ctx.CtxPackage;

/**
 */
public class SystemFunction_FunctionRealization extends AbstractFunction_RealizedFunctions {

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getClass()
   */
	public EClass getEClass() {
		return CtxPackage.Literals.SYSTEM_FUNCTION;
	}
}
