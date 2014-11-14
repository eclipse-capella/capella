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

import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioRealization;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 *
 */
public class Scenario_realizedScenario implements IQuery {

  public Scenario_realizedScenario() {
    // do nothing
  }

  /** 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>();
    if (object_p instanceof Scenario) {
      Scenario af = (Scenario) object_p;
      for (AbstractTrace functionRealisation : af.getOutgoingTraces()) {
        if (functionRealisation instanceof ScenarioRealization) {
          TraceableElement element = functionRealisation.getTargetElement();
          if (element != null && element instanceof Scenario) {
            result.add(element);
          }
        }
      }
    }
    return result;
  }
}
