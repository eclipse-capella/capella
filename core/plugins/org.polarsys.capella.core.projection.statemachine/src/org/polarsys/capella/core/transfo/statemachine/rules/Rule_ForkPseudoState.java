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
package org.polarsys.capella.core.transfo.statemachine.rules;

import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;

/**
 */
public class Rule_ForkPseudoState extends Rule_AbstractState {

	public Rule_ForkPseudoState() {
		super(CapellacommonPackage.Literals.FORK_PSEUDO_STATE, CapellacommonPackage.Literals.FORK_PSEUDO_STATE);
	}

}
