/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.semantic.queries.basic.queries;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.ctx.SystemFunction;

/**
 */
public class FunctionalChainInvolvmentInvolvedSystemFunction extends AbstractFCInvolvmentInvolved {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isInstaceOf(EObject involved) {
		if (involved instanceof SystemFunction) {
			return true;
		}
		
		return false;
	}

}
