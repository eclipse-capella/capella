/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.exchanges;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.ComponentExt;

/**
 * This factory creates a <code>IExchangesCreator</code> according to the given sub-type of <code>Component</code>.
 */
public class LinkCreatorFactory {
  /**
   * Creates a <code>IExchangesCreator</code> according to the given <code>Component</code>
   * @param component_p the component on which you want to automatically create the exchanges
   * @return an instance of a sub-class of <code>IExchangesCreator</code>
   */
  public static IExchangesCreator createConnectionCreator(Component component_p, Part part_p) {
    if (component_p instanceof Entity) {
      return new EntityExchangesCreator(component_p);
      
    } else if (component_p instanceof LogicalComponent) {
      return new PhysicalLinksCreator(component_p, part_p);
      
    } else if (component_p instanceof SystemComponent) {
      return new PhysicalLinksCreator(component_p, part_p);
      
    } else if (component_p instanceof PhysicalComponent) {
      PhysicalComponent pc = (PhysicalComponent) component_p;
      if (ComponentExt.isActor(pc)) {
        return new PhysicalLinksCreator(component_p, part_p);
        
      } else if (pc.getNature() == PhysicalComponentNature.NODE) {
        return new NodePhysicalComponentExchangesCreator(component_p, part_p);
      }
    }
    return new DefaultExchangesCreator(component_p);
  }

}
