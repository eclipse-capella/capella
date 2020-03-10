/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.projection.scenario.is2is.handler;

import java.util.List;

import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.scenario.es2es.rules.ScenarioFinalizer;
import org.polarsys.capella.core.projection.scenario.es2es.rules.ScenarioHelper;
import org.polarsys.capella.core.projection.scenario.handlers.ScenarioHorizontalHandler;

public class ScenarioIS2ISHandler extends ScenarioHorizontalHandler {

  @Override
  public List<AbstractInstance> getRelatedInstances(InstanceRole role, IContext context) {
    return ScenarioHelper.getRelatedInstances(role, context.getTransfo());
  }

  @Override
  public InstanceRole getInstanceRole(AbstractInstance tracedInstance, IContext context) {
    return ScenarioFinalizer.getInstanceRole(tracedInstance);
  }
}
