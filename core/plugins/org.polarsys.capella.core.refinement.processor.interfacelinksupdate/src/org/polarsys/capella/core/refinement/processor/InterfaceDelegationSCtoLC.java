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
package org.polarsys.capella.core.refinement.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortRealization;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;

public class InterfaceDelegationSCtoLC {

  Component _sourceComponent;
  
  Component _targetComponent;

  public InterfaceDelegationSCtoLC(LogicalComponent logicalComponent_p) {
    _targetComponent=logicalComponent_p;
  }

  public InterfaceDelegationSCtoLC(System systemComponent) {
    _sourceComponent=systemComponent;
  }
  
  public void perform() {

    if (_targetComponent!=null) {
      for (Component sourceComponent : _targetComponent.getAllocatedComponents()) {
        perform(sourceComponent, _targetComponent);
      }
    }
    
    if (_sourceComponent!=null) {
      for (Component targetComponent : _sourceComponent.getAllocatingComponents()) {
        perform(_sourceComponent, targetComponent);
      }
    }
    
  }

  protected void perform(Component source, Component target) {
    Collection<EObject> elementsToRemove = new HashSet<EObject>();
    ArrayList<EObject> elementsToUnset = new ArrayList<EObject>();

    //Remove interfaceImplementations which are no longer implemented by system (only to system interfaces, we keep logical interfaces)
    for (InterfaceImplementation inter : target.getImplementedInterfaceLinks()) {
      if (inter.getImplementedInterface() != null) {
        if (!source.getImplementedInterfaces().contains(inter.getImplementedInterface()) && areInSameLayer(inter.getImplementedInterface(), source)) {
          elementsToRemove.add(inter);
        }
      }
    }

    //Create an interfaceImplementation if link was not already created for all implemented interfaces
    for (InterfaceImplementation inter : source.getImplementedInterfaceLinks()) {
      if (inter.getImplementedInterface() != null) {
        ComponentExt.addImplementedInterface(target, inter.getImplementedInterface());
      }
    }

    //Remove interfaceUses which are no longer used by system (only to system interfaces, we keep logical interfaces)
    for (InterfaceUse inter : target.getUsedInterfaceLinks()) {
      if (inter.getUsedInterface() != null) {
        if (!source.getUsedInterfaces().contains(inter.getUsedInterface()) && areInSameLayer(inter.getUsedInterface(), source)) {
          elementsToRemove.add(inter);
        }
      }
    }

    //Create an interfaceUse if link was not already created for all used interfaces
    for (InterfaceUse inter : source.getUsedInterfaceLinks()) {
      if (inter.getUsedInterface() != null) {
        ComponentExt.addUsedInterface(target, inter.getUsedInterface());
      }
    }

    //Create for all ports links between provided and required interfaces and create a port if necessary
    for (Feature feature : source.getOwnedFeatures()) {
      if (feature instanceof ComponentPort) {
        ComponentPort portSource = (ComponentPort)feature;
        ComponentPort portTarget = getRealizingComponentPort(portSource, target);

        //Remove providedInterfaces of target which are no longer provided by system (only to system interfaces, we keep logical interfaces)
        for (Interface i : portTarget.getProvidedInterfaces()) {
          if (!portSource.getProvidedInterfaces().contains(i) && areInSameLayer(i, portSource)) {
            elementsToUnset.add(i);
          }
        }
        for (EObject i : elementsToUnset) {
          portTarget.getProvidedInterfaces().remove(i);
        }
        elementsToUnset.clear();

        //Remove requiredInterfaces of target which are no longer required by system (only to system interfaces, we keep logical interfaces)
        for (Interface i : portTarget.getRequiredInterfaces()) {
          if (!portSource.getRequiredInterfaces().contains(i) && areInSameLayer(i, portSource)) {
            elementsToUnset.add(i);
          }
        }
        for (EObject i : elementsToUnset) {
          portTarget.getRequiredInterfaces().remove(i);
        }
        elementsToUnset.clear();


        //Create provided interface links
        for (Interface i : portSource.getProvidedInterfaces()) {
          if (!portTarget.getProvidedInterfaces().contains(i)) {
            portTarget.getProvidedInterfaces().add(i);
          }
        }

        //Create required interface links
        for (Interface i : portSource.getRequiredInterfaces()) {
          if (!portTarget.getRequiredInterfaces().contains(i)) {
            portTarget.getRequiredInterfaces().add(i);
          }
        }
      }
    }

    //Remove elements to be removed
    for (EObject obj : elementsToRemove) {
      EcoreUtil.delete(obj);
    }
  }

  /**
   * Returns whether both elements are in the same block architecture
   */
  private boolean areInSameLayer(EObject source, EObject target) {
    return CapellaLayerCheckingExt.areInSameLayer(source, target);
  }

  /**
   * Returns the realizingPort of port into the target
   * Create one if not found
   */
  public ComponentPort getRealizingComponentPort(ComponentPort port, Component target) {

    for (PortRealization realization : port.getIncomingPortRealizations()) {
      Port realizing = realization.getRealizingPort();
      if (realizing!=null && realizing instanceof ComponentPort) {
        if (realizing.eContainer().equals(target)) {
          return (ComponentPort)realizing;
        }
      }
    }

    ComponentPort cloned = FaFactory.eINSTANCE.createComponentPort();
    cloned.setOrientation(port.getOrientation());
    cloned.setKind(port.getKind());
    cloned.setName(port.getName());
    target.getOwnedFeatures().add(cloned);

    PortRealization realization = InformationFactory.eINSTANCE.createPortRealization();
    realization.setSourceElement(cloned);
    realization.setTargetElement(port);
    cloned.getOwnedPortRealizations().add(realization);
    return cloned;
  }

}
