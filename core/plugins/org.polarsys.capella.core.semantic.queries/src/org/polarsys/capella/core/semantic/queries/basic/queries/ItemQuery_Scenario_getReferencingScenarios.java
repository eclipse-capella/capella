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
package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 *
 */
public class ItemQuery_Scenario_getReferencingScenarios implements IQuery {

  public ItemQuery_Scenario_getReferencingScenarios() {
    // do nothing
  }

  /** 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object_p) {
    List<Object> referencingScenarios = new ArrayList<Object>();
    if (object_p instanceof InteractionUse) {
    	InteractionUse interactionUse = (InteractionUse) object_p;
    	if (interactionUse.eContainer() instanceof Scenario) {
			Scenario scenario = (Scenario) interactionUse.eContainer();
			referencingScenarios.add(scenario);
		}
    }
  
    return referencingScenarios;
  }
}
