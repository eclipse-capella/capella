/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.model.helpers.formatterLabels;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OperationalActor;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.data.pa.PhysicalActor;

public class FormatterLabels {

  public static String getLevelOfCapability(AbstractCapability capability) {
    String level = "Capability";
    if (capability instanceof OperationalCapability)
      level = "Operational" + level;
    else
      level = "CapabilityRealization";

    return level;
  }

  public static String getLevelOfComponents(Component element) {
    String level = "Component";
    if (element instanceof Entity) {
      if (element instanceof OperationalActor) {
        level = "OperationalActor";
      } else {
        level = "OperationalEntity";
      }
    } else if (element instanceof Actor)
      level = "SystemActor";
    else if (element instanceof LogicalActor)
      level = "LogicalActor";
    else if (element instanceof PhysicalActor)
      level = "PhysicalActor";

    return level;
  }

  public static String getFunctionalChainType(AbstractCapability capability) {
    String type = "functional chains";
    if (capability instanceof OperationalCapability)
      type = "operational processes";

    return type;
  }
}
