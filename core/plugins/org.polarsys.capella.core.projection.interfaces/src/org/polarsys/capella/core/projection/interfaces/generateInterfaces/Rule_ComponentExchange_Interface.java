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
package org.polarsys.capella.core.projection.interfaces.generateInterfaces;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.OrientationPortKind;
import org.polarsys.capella.core.tiger.ITransfo;

public class Rule_ComponentExchange_Interface extends InterfaceGenerationRule {

  private final TracingStrategy tracingStrategy = new ExchangeTracing();

  public Rule_ComponentExchange_Interface() {
    super(FaPackage.Literals.COMPONENT_EXCHANGE, CsPackage.Literals.INTERFACE);
  }

  @Override
  protected Collection<InterfaceInfo> transformToInterfaceInfo(EObject element, ITransfo transfo) {
    InterfaceInfo info = getInterfaceInfo((ComponentExchange) element);
    if (info != null) {
      return Collections.singleton(info);
    }
    return Collections.emptyList();
  }

  private InterfaceInfo getInterfaceInfo(ComponentExchange exchange) {

    InterfaceInfo result = null;

    /* already covered by functional exchange rule */
    if (exchange.getAllocatedFunctionalExchanges().size() > 0) {
      return null;
    }

    /* Only generate something if there are allocated exchange items */
    if (exchange.getConvoyedInformations().isEmpty()) {
      return null;
    }

    /* which side is provider, which is requirer? */
    ComponentPort sourceCP = (ComponentPort) exchange.getSourcePort();
    ComponentPort targetCP = (ComponentPort) exchange.getTargetPort();

    if (sourceCP.getOrientation() == OrientationPortKind.OUT && targetCP.getOrientation() == OrientationPortKind.IN) {
      result = new InterfaceInfo(new ComponentPortInterfaceAdapter(targetCP),
          new ComponentPortInterfaceAdapter(sourceCP), tracingStrategy);

    } else if (sourceCP.getOrientation() == OrientationPortKind.IN
        && targetCP.getOrientation() == OrientationPortKind.OUT) {
      result = new InterfaceInfo(new ComponentPortInterfaceAdapter(sourceCP),
          new ComponentPortInterfaceAdapter(targetCP), tracingStrategy);

    } else {
      // just guess as a last resort
      result = new InterfaceInfo(new ComponentPortInterfaceAdapter(targetCP),
          new ComponentPortInterfaceAdapter(sourceCP), tracingStrategy);
    }
    return result;
  }

  @Override
  protected void doGoDeep(EObject element, List<EObject> result) {
    result.addAll(((ComponentExchange) element).getAllocatedFunctionalExchanges());
  }

}