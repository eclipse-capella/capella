/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;


/**
 * Utility class providing simple reusable services
 * 
 */
public final class MiscUtil { 

	private MiscUtil() {
		// Forbids instantiation 
	}

	public static ExecutionManager getExecutionManager(Collection<? extends EObject> context) {
		return TransactionHelper.getExecutionManager(context);
	}

	public static boolean transactionallyExecute(Collection<? extends EObject> context, ICommand cmd) {
		boolean result = true;
		try {
			ExecutionManager em = getExecutionManager(context);
			em.execute(cmd);
		} catch(RuntimeException e) {
			result = false;
		}
		return result;
	}

	/**
	 * Return whether a given reference can be used to add elements 
	 */
	public static boolean supportsAddition(EReference reference) {
		return reference != null && reference.isMany() &&
		!reference.isDerived() && reference.isChangeable();
	}

}
