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
	 * @param abstractInstance
	 * @param isIntraLayer
	 * @param decomposedComponent
	 * @param target
	 * @param srcScenario
	 * @param srcAbstractEnd
	 *
	 * @return List<AbstractInstance> an exhaustive set of candidate abstract instances
	 * 
	 * @throws MapperException
	 */
	public List<AbstractInstance> candidateComponents(
	  AbstractInstance abstractInstance,
		boolean isIntraLayer,
		Component decomposedComponent,
		NamedElement target,
		Scenario srcScenario,
		AbstractEnd srcAbstractEnd
	) throws MapperException;

	/**
	 * @param componentType
	 * @param invokedOperation
	 * @param candidateAbstractInstances
	 * @param srcAbstractEnd
	 *
	 * @return List<AbstractInstance> a reduced set of target abstract instances
	 *
	 * @throws MapperException
	 */
	public List<AbstractInstance> componentMapping(
		COMPONENT_TYPE componentType,
		AbstractEventOperation invokedOperation,
		List<AbstractInstance> candidateAbstractInstances,
    AbstractEnd srcAbstractEnd
	) throws MapperException;
}
