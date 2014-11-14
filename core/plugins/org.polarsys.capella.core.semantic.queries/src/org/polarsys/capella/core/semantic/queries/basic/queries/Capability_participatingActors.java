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

import org.polarsys.capella.core.data.cs.ActorCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return Involved Actor of current AbstractCapability
 */
public class Capability_participatingActors implements IQuery {

  /**
   * 
   */
  public Capability_participatingActors() {
    // do nothing
  }

  /**
   * current.participatingActors
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>();
    if (object_p instanceof AbstractCapability) {
      if (object_p instanceof Capability) {
        Capability cap = (Capability) object_p;
        for (ActorCapabilityInvolvement involment : cap.getInvolvedActors()) {
          Actor actor = involment.getActor();
          if (null != actor) {
            result.add(actor);
          }
        }
      } else if (object_p instanceof CapabilityRealization) {
        CapabilityRealization capReal = (CapabilityRealization) object_p;
        for (ActorCapabilityRealizationInvolvement involment : capReal.getInvolvedActors()) {
          InvolvedElement involved = involment.getInvolved();
          if (null != involved && involved instanceof Actor) {
            result.add(involved);
          }
        }
      }

    }
    return result;
  }
}
