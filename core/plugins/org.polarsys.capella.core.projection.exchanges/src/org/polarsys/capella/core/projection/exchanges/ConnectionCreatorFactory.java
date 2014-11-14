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
package org.polarsys.capella.core.projection.exchanges;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.pa.AbstractPhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;

/**
 * This factory creates a <code>IExchangesCreator</code> according to the given sub-type of <code>Component</code>.
 */
public class ConnectionCreatorFactory {
  /**
   * Creates a <code>IExchangesCreator</code> according to the given <code>Component</code>
   * @param component_p the component on which you want to automatically create the exchanges
   * @return an instance of a sub-class of <code>IExchangesCreator</code>
   */
  public static IExchangesCreator createConnectionCreator(Component component_p, Part part_p) {
    if (component_p instanceof Entity) {
      return new EntityExchangesCreator(component_p);
    }
    // logical architecture
    if ((component_p instanceof LogicalComponent) || (component_p instanceof LogicalActor)) {
      return new ComponentExchangesCreator(component_p, part_p);
    }
    // System analysis
    if ((component_p instanceof System) || (component_p instanceof Actor)) {
      return new ComponentExchangesCreator(component_p, part_p);
    }

    if (component_p instanceof AbstractPhysicalComponent) {
      AbstractPhysicalComponent pc = (AbstractPhysicalComponent) component_p;
      PhysicalComponentNature nature = pc.getNature();
      if (pc instanceof PhysicalActor) {
        return new PhysicalActorExchangesCreator(component_p, part_p);
      }

      if (nature == PhysicalComponentNature.NODE) {
        return new NodePhysicalComponentExchangesCreator(component_p, part_p);
      }
    }
    return new DefaultExchangesCreator(component_p);
  }

}
