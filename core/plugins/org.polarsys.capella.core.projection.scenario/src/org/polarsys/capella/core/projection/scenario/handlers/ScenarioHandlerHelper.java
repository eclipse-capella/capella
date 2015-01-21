/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.scenario.handlers;

import org.polarsys.capella.core.projection.common.context.IContext;

public class ScenarioHandlerHelper {

  public static final String SCENARIO_HELPER = "SCENARIO_HELPER";

  public static IScenarioHandler getInstance(IContext context_p) {
    if (context_p.containsKey(SCENARIO_HELPER)) {
      return (IScenarioHandler) context_p.get(SCENARIO_HELPER);
    }
    return null;
  }

  public static void setInstance(IContext context_p, IScenarioHandler handler) {
    context_p.put(SCENARIO_HELPER, handler);
  }
}
