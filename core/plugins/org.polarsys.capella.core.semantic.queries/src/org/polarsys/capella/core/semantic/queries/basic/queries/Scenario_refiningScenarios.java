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
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.MergeLinkExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 */
public class Scenario_refiningScenarios implements IQuery {

  public Scenario_refiningScenarios() {
    // do nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof Scenario) {
      Scenario s = (Scenario) object;
      for (CapellaElement meloElt : RefinementLinkExt.getRefinementRelatedTargetElements(s, InteractionPackage.Literals.SCENARIO)) {
        Scenario tgtScenario = (Scenario) meloElt;
        if (tgtScenario.isMerged()) {
          // Add specific case for 'Merge Scenario' 
          // Compute indirection to retrieve the Source scenario in La layer
          MergeLink link = MergeLinkExt.findTargetMergedLink(tgtScenario);
          if (null != link && link.getTargetElement() instanceof Scenario)
            result.add(link.getTargetElement());
        } else {
          result.add(tgtScenario);
        }
      }
    }
    return result;
  }
}
