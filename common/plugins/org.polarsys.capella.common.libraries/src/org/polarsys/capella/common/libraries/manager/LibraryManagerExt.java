/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.libraries.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.IModelIdentifier;

public class LibraryManagerExt {

  public static Collection<IModelIdentifier> getAllUnavailableReferences(IModel referencingModel) {
    LinkedHashSet<IModelIdentifier> visited = new LinkedHashSet<IModelIdentifier>();
    ArrayList<IModelIdentifier> result = new ArrayList<IModelIdentifier>();
    LinkedList<IModel> toVisit = new LinkedList<IModel>();
    toVisit.add(referencingModel);

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

  public static Collection<IModel> getReferences(IModel referencingModel) {
    return referencingModel.getAvailableReferences();
  }

  public static Collection<IModel> getAllReferences(IModel referencingModel) {
    LinkedHashSet<IModel> visited = new LinkedHashSet<IModel>();
    LinkedList<IModel> toVisit = new LinkedList<IModel>();
    toVisit.add(referencingModel);

    while (!toVisit.isEmpty()) {
      IModel model = toVisit.removeFirst();
      if ((model != null) && !visited.contains(model)) {
        visited.add(model);
        toVisit.addAll(model.getAvailableReferences());
      }
    }

    visited.remove(referencingModel);
    return visited;
  }

  public static Collection<IModel> getAllActivesReferences(IModel referencingModel) {

    LinkedList<IModel> result = new LinkedList<IModel>();
    for (IModel model : getAllReferences(referencingModel)) {
      if (referencingModel.isActive(model)) {
        result.add(model);
      }
    }

    return result;
  }

  public static Collection<IModel> getActivesReferences(IModel referencingModel) {

    LinkedList<IModel> result = new LinkedList<IModel>();
    for (IModel model : referencingModel.getAvailableReferences()) {
      if (referencingModel.isActive(model)) {
        result.add(model);
      }
    }

    return result;
  }
}
