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

import java.util.List;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.helpers.interaction.services.MessageEndExt.COMPONENT_TYPE;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.MapperException;

/**
 */
public interface IMapper extends IRefinementPlug {

	/**
	 * @param abstractInstance_p
	 * @param isIntraLayer_p
	 * @param decomposedComponent_p
	 * @param target_p
	 * @param srcScenario_p
	 * @param srcAbstractEnd_p
	 *
	 * @return List<AbstractInstance> an exhaustive set of candidate abstract instances
	 * 
	 * @throws MapperException
	 */
	public List<AbstractInstance> candidateComponents(
	  AbstractInstance abstractInstance_p,
		boolean isIntraLayer_p,
		Component decomposedComponent_p,
		NamedElement target_p,
		Scenario srcScenario_p,
		AbstractEnd srcAbstractEnd_p
	) throws MapperException;

	/**
	 * @param componentType_p
	 * @param invokedOperation_p
	 * @param candidateAbstractInstances_p
	 * @param srcAbstractEnd_p
	 *
	 * @return List<AbstractInstance> a reduced set of target abstract instances
	 *
	 * @throws MapperException
	 */
	public List<AbstractInstance> componentMapping(
		COMPONENT_TYPE componentType_p,
		AbstractEventOperation invokedOperation_p,
		List<AbstractInstance> candidateAbstractInstances_p,
    AbstractEnd srcAbstractEnd_p
	) throws MapperException;
}
