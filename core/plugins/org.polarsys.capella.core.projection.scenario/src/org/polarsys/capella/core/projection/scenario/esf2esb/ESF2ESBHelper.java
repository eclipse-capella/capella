/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.scenario.esf2esb;

import java.util.Collections;
import java.util.List;

import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.scenario.helpers.ScenarioHorizontalHelper;


public class ESF2ESBHelper extends ScenarioHorizontalHelper {

  @Override
  public List<AbstractInstance> getTargetInstances(InstanceRole role_p, IContext context_p) {
    // The instance role represents the same instance than the source.
    return Collections.singletonList(role_p.getRepresentedInstance());
  }

}
