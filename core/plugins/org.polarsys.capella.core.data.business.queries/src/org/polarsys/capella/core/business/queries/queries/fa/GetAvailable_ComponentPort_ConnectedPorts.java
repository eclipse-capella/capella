/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.business.queries.queries.fa;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionalExt;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.PortExt;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_ComponentPort_ConnectedPorts extends AbstractQuery {

  private ComponentPort thePort;

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<EObject> availableElements = getAvailableElements(capellaElement);
    return (List) availableElements;
  }

  public List<EObject> getAvailableElements(CapellaElement element) {
    List<EObject> availableElements = new ArrayList<EObject>();
    if (element instanceof Part) {
      Part property = (Part) element;
      availableElements.addAll(getRule_MQRY_StandardPort_ConnectedPorts_11(property));
    }
    availableElements = ListExt.removeDuplicates(availableElements);
    availableElements.remove(thePort);
    return availableElements;
  }

  private List<CapellaElement> getRule_MQRY_StandardPort_ConnectedPorts_11(Part currentPhysicalPart) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(CsPackage.Literals.PART,
        CapellacorePackage.Literals.TYPED_ELEMENT__TYPE);
    if (query != null) {
      List<EObject> pcs = query.getAvailableElements(currentPhysicalPart);
      pcs.add(currentPhysicalPart.eContainer());
      for (EObject capellaElement : pcs) {
        if (capellaElement instanceof PhysicalComponent) {
          PhysicalComponent pc = (PhysicalComponent) capellaElement;
          for (ComponentPort port : pc.getContainedComponentPorts()) {
            if (!PortExt.isNotCompatibleWith((ComponentPort) port, thePort)) {
              availableElements.add(port);
            }
          }
        }
      }

    }
    return availableElements;
  }

  public List<EObject> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
    List<EObject> currentElements = new ArrayList<EObject>(1);
    if (element instanceof Part && thePort != null) {
      for (ComponentExchange connection : thePort.getComponentExchanges()) {
        currentElements.addAll(FunctionalExt.getRelatedPorts(connection));
      }
    }
    return ListExt.removeDuplicates(currentElements);
  }

}