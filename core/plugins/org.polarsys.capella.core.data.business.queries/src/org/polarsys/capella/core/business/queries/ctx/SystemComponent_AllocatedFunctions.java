/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.business.queries.ctx;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.fa.AbstractFunctionalBlock_AllocatedFunctions;
import org.polarsys.capella.core.data.ctx.CtxPackage;

public class SystemComponent_AllocatedFunctions extends AbstractFunctionalBlock_AllocatedFunctions implements IBusinessQuery {

	@Override
	public EClass getEClass() {
		return CtxPackage.Literals.SYSTEM_COMPONENT;
	}

}
