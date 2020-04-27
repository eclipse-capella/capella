/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.validation.function;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.model.helpers.AbstractFunctionExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check if leaf functions or ControlNode are allocated to a component.
 * 
 */
public class LFCompAllocationLeastwise extends AbstractValidationRule {

	@Override
	public IStatus validate(IValidationContext ctx) {
		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();

		if (eType == EMFEventType.NULL) {
			// Object is of type AbstractFunction
			if (eObj instanceof AbstractFunction) {
				AbstractFunction absfunction = (AbstractFunction) eObj;
				BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(absfunction);
				if (null != arch) {
				  AbstractFunction rootFunction = BlockArchitectureExt.getRootFunction(arch);
					if (null != rootFunction && !absfunction.equals(rootFunction)) {
						String leafNotAllocated = ICommonConstants.EMPTY_STRING;
						// consider Function type
						if (AbstractFunctionExt.isLeaf(absfunction)) {
						  // check if leaf
							List<Object> allocationBlocks = AbstractFunctionExt.getAllocationBlocks(absfunction);
							if (allocationBlocks.isEmpty()) {
								// add element to failure list
								leafNotAllocated = absfunction.getName();
							}
						}
						if (!ICommonConstants.EMPTY_STRING.equals(leafNotAllocated)) {
							return createFailureStatus(ctx, new Object[] { leafNotAllocated, eObj.eClass().getName() });
						}
					}
				}
			}
		}
		return ctx.createSuccessStatus();
	}
}
