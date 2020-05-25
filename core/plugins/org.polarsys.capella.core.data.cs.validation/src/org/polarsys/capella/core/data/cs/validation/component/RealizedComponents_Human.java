/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.validation.component;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.cs.CsPackage;

/*
 * Rule that checks if a Component realizes other Components with HUMAN different by its own.
 */
public class RealizedComponents_Human extends RuleBQComponents_Human {

  @Override
  protected IBusinessQuery getQuery(EClass cls) {
    return BusinessQueriesProvider.getInstance().getContribution(cls,
        CsPackage.Literals.COMPONENT__OWNED_COMPONENT_REALIZATIONS);
  }
}
