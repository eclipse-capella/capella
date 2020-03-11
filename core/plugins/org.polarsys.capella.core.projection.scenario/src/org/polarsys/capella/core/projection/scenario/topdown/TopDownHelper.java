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
package org.polarsys.capella.core.projection.scenario.topdown;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.capella.core.projection.scenario.helpers.ScenarioHorizontalHelper;

public class TopDownHelper extends ScenarioHorizontalHelper {

  @Override
  public List<EObject> getTargetRelatedElements(StateFragment state, IContext context_p) {

    if (state.getRelatedAbstractFunction() != null) {
      return TraceabilityHandlerHelper.getInstance(context_p).retrieveTracedElements(state.getRelatedAbstractFunction(), context_p);
    }

    if (state.getRelatedAbstractState() != null) {
      return TraceabilityHandlerHelper.getInstance(context_p).retrieveTracedElements(state.getRelatedAbstractState(), context_p);
    }
    return Collections.emptyList();
  }

  @Override
  public List<AbstractInstance> getTargetInstances(InstanceRole role_p, IContext context_p) {
    return TopDownExt.getTargetInstances(role_p, context_p.getTransfo());
  }

}
