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

import java.util.Collection;
import java.util.LinkedHashSet;

import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacommon.TransfoLink;
import org.polarsys.capella.core.data.capellacore.CapellacoreFactory;
import org.polarsys.capella.core.data.capellacore.KeyValue;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.fa.ComponentPort;

/**
 * Trace an inteface by two transfo links: One link goes to the
 * intended interface provider, and the other to the intended
 * interface requirer. This is currently only used in cases where
 * no exchanges are present (Set of Function Ports allocated to a component port, see test8), but
 * it would also work fine as a replacement for ExchangeTracing.
 */
class ProviderRequirerTracing implements TracingStrategy {

  /**
   * TransfoLink key to remember if a component/port is providing or requiring a generated interface
   */
  public static final String KEY_ROLE = "role"; //$NON-NLS-1$

  @Override
  public Collection<Interface> getTracingInterfaces(InterfaceInfo info) {
    if (info.getProvider() == null){
      return getTracingInterfaces((TraceableElement)info.getRequirer().getEObject(), ProviderRequirerRole.REQUIRER, true);
    } else if (info.getRequirer() == null) {
      return getTracingInterfaces((TraceableElement)info.getProvider().getEObject(), ProviderRequirerRole.PROVIDER, true);
    } else {
      Collection<Interface> result = getTracingInterfaces((TraceableElement) info.getProvider().getEObject(), ProviderRequirerRole.PROVIDER, false);
      Collection<Interface> required = getTracingInterfaces((TraceableElement) info.getRequirer().getEObject(), ProviderRequirerRole.REQUIRER, false);
      result.retainAll(required);
      return result;
    }
  }

  @Override
  public void traceInterface(Interface iface, InterfaceInfo info) {
    if (info.getProvider() != null){
      traceInterface(iface, ProviderRequirerRole.PROVIDER, (TraceableElement)info.getProvider().getEObject());
    }
    if (info.getRequirer() != null){
      traceInterface(iface, ProviderRequirerRole.REQUIRER, (TraceableElement)info.getRequirer().getEObject());
    }    
  }


  void traceInterface(Interface iface, ProviderRequirerRole role, TraceableElement target) {
    
    for (AbstractTrace t : iface.getOutgoingTraces() ) {
      if (t instanceof TransfoLink && t.getTargetElement() == target){
        for (KeyValue kv : ((TransfoLink)t).getKeyValuePairs()){
          if (KEY_ROLE.equals(kv.getKey()) && role.name().equals(kv.getValue())){
            return; // already traced
          }
        }
      }
    }

    TransfoLink trace = CapellacommonFactory.eINSTANCE.createTransfoLink();
    KeyValue roleKv = CapellacoreFactory.eINSTANCE.createKeyValue();
    roleKv.setKey(KEY_ROLE);
    roleKv.setValue(role.name());
    trace.getKeyValuePairs().add(roleKv);
    trace.setSourceElement(iface);
    trace.setTargetElement(target);
    iface.getOwnedTraces().add(trace);
  }

  /**
   * Find interfaces that have traces to the target element with the given role.
   * 
   * @param target the trace target
   * @param role the role for the trace target
   * @param singleRole if true, interfaces that also trace the other role to some element are excluded from the result
   * @return
   */
  private Collection<Interface> getTracingInterfaces(TraceableElement target, ProviderRequirerRole role, boolean singleRole){
    Collection<Interface> result = new LinkedHashSet<>();
    for (AbstractTrace t : target.getIncomingTraces()){
      if (t instanceof TransfoLink && t.getSourceElement() instanceof Interface) {
        Interface iface = null;
        for (KeyValue kv : ((TransfoLink) t).getKeyValuePairs()){
          if (KEY_ROLE.equals(kv.getKey()))
            if (role.name().equals(kv.getValue())){
              iface = (Interface) t.getSourceElement();
            } else if (singleRole) {
              iface = null;
              break;
          }
        }
        if (iface != null) {
          result.add(iface);
        }
      }
    }
    return result;
  }

  public InterfaceInfo getTracedInterfaceInfos(Interface generated){
    InterfaceInfo result = null;
    InterfaceProvider provider = null;
    InterfaceRequirer requirer = null;
    
    for (AbstractTrace t : generated.getOutgoingTraces()){
      if (t instanceof TransfoLink){
        if (provider == null) {
          provider = getProvider((TransfoLink)t);
        }
        if (requirer == null) {
          requirer = getRequirer((TransfoLink)t);
        }
      }
    }
    
    if (provider != null || requirer != null){
      result = new InterfaceInfo(provider, requirer, this);
    }

    return result;
  }

  private InterfaceRequirer getRequirer(TransfoLink t){
    return (InterfaceRequirer) getTarget(t, ProviderRequirerRole.REQUIRER);
  }

  private InterfaceProvider getProvider(TransfoLink t){
    return (InterfaceProvider) getTarget(t, ProviderRequirerRole.PROVIDER);
  }

  private Object getTarget(TransfoLink t, ProviderRequirerRole role){
    for (KeyValue kv : t.getKeyValuePairs()){
      if (KEY_ROLE.equals(kv.getKey()) && role == ProviderRequirerRole.valueOf(kv.getValue())){
          TraceableElement provider = t.getTarget();
          if (provider instanceof ComponentPort){
            return new ComponentPortInterfaceAdapter((ComponentPort) provider);
          }
          if (provider instanceof Component){
            return new ComponentInterfaceAdapter((Component) provider);
          }
        }
    }
    return null;
  }

}
