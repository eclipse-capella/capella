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

import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MergeLink;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.model.helpers.MergeLinkExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 *
 */
public class Scenario_refinedScenarios implements IQuery {

	public Scenario_refinedScenarios() {
    // do nothing
	}

	/** 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object_p) {
	  List<Object> result = new ArrayList<Object>();
	  if (object_p instanceof Scenario) {
	    Scenario s = (Scenario) object_p;
	    result.addAll(RefinementLinkExt.getRefinementRelatedSourceElements(s, InteractionPackage.Literals.SCENARIO));

	    // Add specific case for 'Merge Scenario' 
        // Compute indirection to retrieve the Target scenario in Pa layer
	    MergeLink link = MergeLinkExt.findSourceMergedLink(s);
	    if (null != link && link.getSourceElement() instanceof Scenario) {
	      Scenario srcScenrio = (Scenario) link.getSourceElement();
	      if (srcScenrio.isMerged()) {
	        result.addAll(RefinementLinkExt.getRefinementRelatedSourceElements(srcScenrio, InteractionPackage.Literals.SCENARIO));
	      }
	    }
	  }

	  return result;
	}
}
