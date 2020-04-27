/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
	public List<Object> compute(Object object) {
	  List<Object> result = new ArrayList<Object>();
	  if (object instanceof Scenario) {
	    Scenario s = (Scenario) object;
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
