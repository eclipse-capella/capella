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
package org.polarsys.capella.common.libraries.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.IModelIdentifier;

public class LibraryManagerExt {

  public static Collection<IModelIdentifier> getAllUnavailableReferences(IModel referencingModel_p) {
    LinkedHashSet<IModelIdentifier> visited = new LinkedHashSet<IModelIdentifier>();
    ArrayList<IModelIdentifier> result = new ArrayList<IModelIdentifier>();
    LinkedList<IModel> toVisit = new LinkedList<IModel>();
    toVisit.add(referencingModel_p);

    while (!toVisit.isEmpty()) {
      IModel model = toVisit.removeFirst();

      if ((model != null) && !visited.contains(model)) {
        visited.add(model.getIdentifier());
        for (IModelIdentifier subModel : model.getReferences()) {
          if (!result.contains(subModel)) {
            result.add(subModel);
          }
        }
        for (IModel subModel : model.getAvailableReferences()) {
          toVisit.add(subModel);
        }
      }
    }

    for (IModelIdentifier identifier : visited) {
      result.remove(identifier);
    }
    return result;
  }

  public static Collection<IModel> getReferences(IModel referencingModel_p) {
    return referencingModel_p.getAvailableReferences();
  }

  public static Collection<IModel> getAllReferences(IModel referencingModel_p) {
    LinkedHashSet<IModel> visited = new LinkedHashSet<IModel>();
    LinkedList<IModel> toVisit = new LinkedList<IModel>();
    toVisit.add(referencingModel_p);

    while (!toVisit.isEmpty()) {
      IModel model = toVisit.removeFirst();
      if ((model != null) && !visited.contains(model)) {
        visited.add(model);
        toVisit.addAll(model.getAvailableReferences());
      }
    }

    visited.remove(referencingModel_p);
    return visited;
  }

  public static Collection<IModel> getAllActivesReferences(IModel referencingModel_p) {

    LinkedList<IModel> result = new LinkedList<IModel>();
    for (IModel model : getAllReferences(referencingModel_p)) {
      if (referencingModel_p.isActive(model)) {
        result.add(model);
      }
    }

    return result;
  }

  public static Collection<IModel> getActivesReferences(IModel referencingModel_p) {

    LinkedList<IModel> result = new LinkedList<IModel>();
    for (IModel model : referencingModel_p.getAvailableReferences()) {
      if (referencingModel_p.isActive(model)) {
        result.add(model);
      }
    }

    return result;
  }
}
