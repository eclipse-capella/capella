/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.actions.extensions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.sirius.analysis.SequenceDiagramServices;

/**
 *
 */
public class DoReorder extends AbstractExternalJavaAction {

	/**
	 * 
	 */
	private static final String FINISHING_END_PREDECESSOR = "FINISHING_END_PREDECESSOR";	 //$NON-NLS-1$
	private static final String STARTING_END_PREDECESSOR = "STARTING_END_PREDECESSOR"; //$NON-NLS-1$

	/**
	 * {@inheritDoc}
	 */
	public void execute(Collection<? extends EObject> selections_p,
			Map<String, Object> parameters_p) {
		EObject context = (EObject) parameters_p.get(CONTEXT);
		EObject startingEndPredecessor = (EObject) parameters_p.get(STARTING_END_PREDECESSOR);
		EObject finishingEndPredecessor = (EObject) parameters_p.get(FINISHING_END_PREDECESSOR);
		
		SequenceDiagramServices.doReorder(context, startingEndPredecessor, finishingEndPredecessor);

	}

}
