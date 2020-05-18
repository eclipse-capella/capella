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
package org.polarsys.capella.core.data.capellamodeller.validation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.KeyValue;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.model.helpers.query.IRootQueries;
import org.polarsys.capella.core.model.helpers.query.impl.RootQueries;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensures that in SingletonComponents mode there is no End (ComponentExchangeEnd or PhysicalLinkEnd)
 */
public class SingletonModeWithEnds extends AbstractValidationRule {

	@Override
	public IStatus validate(IValidationContext ctx) {

		EObject eObj = ctx.getTarget();

		if (eObj instanceof ComponentExchangeEnd || eObj instanceof PhysicalLinkEnd) {

			IRootQueries root = new RootQueries();
			Project pro= root.getProject((CapellaElement)eObj);
			EList<KeyValue> keyvalues = (EList<KeyValue>) pro.getKeyValuePairs();
			for(KeyValue kv: keyvalues){
				if(kv.getKey().equals("projectApproach") && kv.getValue().equals("SingletonComponents")){
					return ctx.createFailureStatus(new Object[] {});
				}
			}
		}
		return null;
	}
}
