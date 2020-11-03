/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacommon.TransfoLink;
import org.polarsys.capella.core.data.cs.Interface;

/**
 * Finds traces Interface->FunctionalExchange, Interface->ComponentExchange
 */
class ExchangeTracing implements TracingStrategy {

  @Override
  public Collection<Interface> getTracingInterfaces(InterfaceInfo info) {
    Collection<Interface> candidates = new LinkedHashSet<Interface>();
    Collection<TraceableElement> targets =new ArrayList<TraceableElement>(info.getFunctionalExchanges());
    targets.addAll(info.getComponentExchanges());
    for (TraceableElement e : targets){
      for (AbstractTrace t : e.getIncomingTraces()){
        if (t instanceof TransfoLink && t.getSourceElement() instanceof Interface){
          candidates.add((Interface)t.getSourceElement());
        }
      }
    }
    return candidates;
  }

  private void traceTarget(Interface iface, TraceableElement target){
    for (AbstractTrace t : target.getIncomingTraces()){
      if (t instanceof TransfoLink && t.getSourceElement() == iface){
        return;
      }
    }
    TransfoLink tl = CapellacommonFactory.eINSTANCE.createTransfoLink();
    tl.setSourceElement(iface);
    tl.setTargetElement(target);
    iface.getOwnedTraces().add(tl);
  }

  @Override
  public void traceInterface(Interface iface, InterfaceInfo info) {
    // avoid tracing component exchanges because such a trace gives no information about which side is provider and which is requirer
    Collection<? extends TraceableElement> targets = info.getFunctionalExchanges().size() > 0 ? info.getFunctionalExchanges() : info.getComponentExchanges();
    for (TraceableElement target : targets) {
      traceTarget(iface, target);
    }
  }

}
