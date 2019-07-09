/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.business.queries.la;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.fa.AbstractFunctionalBlock_AllocatedFunctions;
import org.polarsys.capella.core.data.la.LaPackage;

/**
 */
public class LogicalComponent_AllocatedFunctions extends AbstractFunctionalBlock_AllocatedFunctions implements IBusinessQuery {

	@Override
	public EClass getEClass() {
		return LaPackage.Literals.LOGICAL_COMPONENT;
	}

}
