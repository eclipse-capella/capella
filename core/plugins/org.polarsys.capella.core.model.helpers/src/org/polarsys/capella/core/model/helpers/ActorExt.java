/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.model.helpers;

import java.util.LinkedList;
import java.util.List;

import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;

/**
 */
public class ActorExt {
  public static  List<AbstractActor> getAncestors(AbstractActor actor) {
    List<AbstractActor> result = new LinkedList<AbstractActor>();
    result.add(actor);

    for (GeneralizableElement current : actor.getSuper()) {
      if (current instanceof AbstractActor) {
        result.addAll(getAncestors((AbstractActor) current));
      }
    }

    return result;
  }
}
