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
package org.polarsys.capella.core.refinement.scenarios.core.plugs;

import java.util.List;

import org.polarsys.capella.core.data.helpers.interaction.services.MessageEndExt.COMPONENT_TYPE;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.refinement.scenarios.core.datastructures.ScenarioRepresentation;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.ResolverException;

/**
 */
public interface IResolver extends IRefinementPlug {

	/**
	 * @param candidateAbstractInstances
	 * @param srcTree
	 * @param tgtTree
	 * @param srcMsg
	 * @param type
	 * 
	 * @return List<AbstractInstance>
	 * 
	 * @throws ResolverException
	 */
	public List<AbstractInstance> resolving(
	    List<AbstractInstance> candidateAbstractInstances,
	    ScenarioRepresentation srcTree,
      ScenarioRepresentation tgtTree,
	    AbstractEnd srcMsg,
	    COMPONENT_TYPE type) throws ResolverException;
}
