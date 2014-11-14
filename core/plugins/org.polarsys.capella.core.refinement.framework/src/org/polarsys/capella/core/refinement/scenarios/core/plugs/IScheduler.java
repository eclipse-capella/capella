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
package org.polarsys.capella.core.refinement.scenarios.core.plugs;

import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.refinement.scenarios.core.datastructures.Node;
import org.polarsys.capella.core.refinement.scenarios.core.datastructures.ScenarioRepresentation;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.SchedulerException;

/**
 */
public interface IScheduler extends IRefinementPlug {

	/**
	 * @param srcTree
	 * @param tgtTree
	 * @param finalAbstractInstance
	 * @param currentSrcNode
	 * @param tgtElement
	 * 
	 * @throws SchedulerException
	 */
	public void doOrdering(
		ScenarioRepresentation srcTree,
		ScenarioRepresentation tgtTree,
		AbstractInstance finalAbstractInstance,
		Node<InteractionFragment> currentSrcNode,
		NamedElement tgtElement
	) throws SchedulerException;
}
