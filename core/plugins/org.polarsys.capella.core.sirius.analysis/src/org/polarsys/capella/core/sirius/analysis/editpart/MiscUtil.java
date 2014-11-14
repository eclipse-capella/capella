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
package org.polarsys.capella.core.sirius.analysis.editpart;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.platform.sirius.tig.ef.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.common.tig.ef.command.ICommand;


/**
 * Utility class providing simple reusable services
 * 
 */
public final class MiscUtil { 

	private MiscUtil() {
		// Forbids instantiation 
	}

	public static boolean transactionallyExecute(ICommand cmd_p) {
		boolean result = true;
		try {
			MDEAdapterFactory.getExecutionManager().execute(cmd_p);
		} catch(RuntimeException e) {
			result = false;
		}
		return result;
	}

	// INTER-LAYER NAVIGATION SERVICES

	/**
	 * Retrieve a cross referencer which only covers the semantic layer
	 */
	public static ECrossReferenceAdapter getSemanticReferencer() {
		ECrossReferenceAdapter result = null;
		if (MDEAdapterFactory.getEditingDomain() instanceof SemanticEditingDomain) {
			result = ((SemanticEditingDomain) MDEAdapterFactory.getEditingDomain()).getCrossReferencer();
		}
		return result;
	}

	/**
	 * Return whether a given reference can be used to add elements 
	 */
	public static boolean supportsAddition(EReference reference_p) {
		return reference_p != null && reference_p.isMany() &&
		!reference_p.isDerived() && reference_p.isChangeable();
	}
}
