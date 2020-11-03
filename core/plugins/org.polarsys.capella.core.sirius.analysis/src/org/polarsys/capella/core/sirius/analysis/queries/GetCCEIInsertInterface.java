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
package org.polarsys.capella.core.sirius.analysis.queries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.sirius.analysis.CsServices;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;

public class GetCCEIInsertInterface extends AbstractQuery {

  @Override
  public List<Object> execute(Object input_p, IQueryContext context_p) throws QueryException {

    List<Object> interfaces = new ArrayList<Object>();
    Collection<EObject> components = new java.util.HashSet<EObject>();

    // go to the first parent component (which can append when component.eContainer is a package)
    EObject object = CsServices.getService().getFirstParentContainer((EObject) input_p);

    // Add related interfaces
    if (object instanceof ConfigurationItem) {

      components = CsServices.getService().getParentContainersByParts((Component) object);
      components.add(object);
      components.addAll(CsServices.getService().getAllSubUsedComponents((ConfigurationItem) object));

      // For all components, add all linked interfaces
      for (EObject subObject : components) {
        if (subObject instanceof Component) {
          Component subComponent = (Component) subObject;
          interfaces.addAll(CsServices.getService().getRelatedInterfacesFromAllocatedComponent(subComponent));
          interfaces.addAll(CsServices.getService().getRelatedInterfaces(subComponent));
        }
      }

    } else if (object instanceof Component) {
      // Add components accessible by namespace

      components = CsServices.getService().getParentContainersByParts((Component) object);

      // For all components, add all defined and linked interfaces
      for (EObject subObject : components) {
        if (subObject instanceof Component) {
          Component subComponent = (Component) subObject;
          interfaces.addAll(CsServices.getService().getSubDefinedInterfaces(subComponent));
          interfaces.addAll(CsServices.getService().getRelatedInterfaces(subComponent));
          interfaces.addAll(CsServices.getService().getInterfacesFromAllocatedComponent(subComponent));

        } else if (subObject instanceof BlockArchitecture) {
          interfaces.addAll(CsServices.getService().getSubDefinedInterfaces((BlockArchitecture) subObject));
          interfaces.addAll(CsServices.getService().getInterfacesFromAllocatedArchitecture((BlockArchitecture) subObject));
        }
      }

    } else if (object instanceof EPBSArchitecture) {

      interfaces.addAll(CsServices.getService().getSubDefinedInterfaces((BlockArchitecture) object));
      components.addAll(PartExt
          .getComponentsOfParts((CsServices.getService().getContext((EPBSArchitecture) object)).getOwnedParts()));

      // For all components, add all linked interfaces
      for (EObject subObject : components) {
        if (subObject instanceof Component) {
          Component subComponent = (Component) subObject;
          interfaces.addAll(CsServices.getService().getRelatedInterfaces(subComponent));
          interfaces.addAll(CsServices.getService().getRelatedInterfacesFromAllocatedComponent(subComponent));
        }
      }

    } else if (object instanceof BlockArchitecture) {
      interfaces.addAll(CsServices.getService().getSubDefinedInterfaces((BlockArchitecture) object));
      interfaces.addAll(CsServices.getService().getInterfacesFromAllocatedArchitecture((BlockArchitecture) object));

    }

    // Remove all allocated interfaces
    CsServices.getService().removeAllAllocatedInterfaces((List) interfaces);

    return interfaces;
  }
}
