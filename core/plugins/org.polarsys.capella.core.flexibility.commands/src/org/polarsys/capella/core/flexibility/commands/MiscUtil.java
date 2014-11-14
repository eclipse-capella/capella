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
package org.polarsys.capella.core.flexibility.commands;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;


/**
 * Utility class providing simple reusable services
 * 
 */
public final class MiscUtil { 

	private MiscUtil() {
		// Forbids instantiation 
	}

	public static ExecutionManager getExecutionManager(Collection<? extends EObject> context_p) {
		return TransactionHelper.getExecutionManager(context_p);
	}

	public static boolean transactionallyExecute(Collection<? extends EObject> context_p, ICommand cmd_p) {
		boolean result = true;
		try {
			ExecutionManager em = getExecutionManager(context_p);
			em.execute(cmd_p);
		} catch(RuntimeException e) {
			result = false;
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
