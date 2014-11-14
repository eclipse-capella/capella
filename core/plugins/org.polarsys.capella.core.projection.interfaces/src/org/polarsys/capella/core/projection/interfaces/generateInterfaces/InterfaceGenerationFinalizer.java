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
package org.polarsys.capella.core.projection.interfaces.generateInterfaces;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortKind;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.OrientationPortKind;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.PortExt;
import org.polarsys.capella.core.projection.preferences.ProjectionPreferencesPlugin;
import org.polarsys.capella.core.tiger.IFinalizer;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;

/**
 *
 */
public class InterfaceGenerationFinalizer implements IFinalizer {

  /** List of to be made interface links between components */
  private static List<OperationInformation> listInfos = new ArrayList<OperationInformation>();

  /**
   * An internal class to register which functional exchange exists between two components
   * in order to set interface links between them
   */
  static class OperationInformation {
    private AbstractEventOperation operation;
    private Component cProvider;
    private Component cRequirer;

    OperationInformation(AbstractEventOperation operation_p, Component cProvider_p, Component cRequirer_p) {
      operation = operation_p;
      cProvider = cProvider_p;
      cRequirer = cRequirer_p;
    }

    public AbstractEventOperation getOperation() {
      return operation;
    }

    public Component getComponentProvider() {
      return cProvider;
    }

    public Component getComponentRequirer() {
      return cRequirer;
    }

  }

  /**
   * @param exchange_p
   * @param subComponent_p
   * @param allocating_p
   */
  public static void register(FunctionalExchange exchange_p, Component subComponent_p, Component allocating_p) {
    listInfos.add(new OperationInformation(exchange_p, subComponent_p, allocating_p));
  }

  /**
   * @param exchange_p
   * @param subComponent_p
   * @param allocating_p
   */
  public static void register(ComponentExchange exchange_p, Component subComponent_p, Component allocating_p) {
    listInfos.add(new OperationInformation(exchange_p, subComponent_p, allocating_p));
  }

  /**
   * @see org.polarsys.capella.core.tiger.IFinalizer#finalize(org.polarsys.capella.core.tiger.ITransfo)
   */
  public void finalize(ITransfo transfo_p) {

    try {
      for (OperationInformation info : listInfos) {
        registerInterface(info.getOperation(), info.getComponentProvider(), info.getComponentRequirer(), transfo_p);
      }

    } finally {
      clean();
    }
  }

  /**
   * Cleanup the finalizer
   */
  private void clean() {
    listInfos.clear();
  }

  /**
   * Register the transitioned interface of the given functional exchange between both components
   */
  private void registerInterface(AbstractEventOperation a_p, Component b_p, Component c_p, ITransfo transfo_p) {
    Interface itf = (Interface) Query.retrieveFirstTransformedElement(a_p, transfo_p, CsPackage.Literals.INTERFACE);

    //Nothing to do if the connection or the functional exchange has not been transitioned
    if (itf == null) {
      return;
    }

    for (ExchangeItemAllocation allocation : itf.getOwnedExchangeItemAllocations()) {
      if (allocation.getAllocatedItem() != null) {

        if (!ProjectionPreferencesPlugin.getDefault().generateComponentPort()) {
          Component provider = b_p;
          Component requirer = c_p;

          //Invert components according to exchange item allocation
          if (mustInverse(allocation.getAllocatedItem())) {
            provider = c_p;
            requirer = b_p;
          }

          //Attach interface to component
          ComponentExt.addImplementedInterface(provider, allocation.getAllocatingInterface());
          ComponentExt.addUsedInterface(requirer, allocation.getAllocatingInterface());

        } else {

          //Retrieve two ComponentPort related to bounds of the given operation
          ComponentPort cPortProviding = null;
          ComponentPort cPortRequiring = null;

          if (a_p instanceof FunctionalExchange) {
            FunctionalExchange exchange = (FunctionalExchange) a_p;

            //Find ports which is related to the related bound of the exchange
            //or related to the interface by the correct feature
            EReference sourceReference = InformationPackage.Literals.PORT__PROVIDED_INTERFACES;
            EReference targetReference = InformationPackage.Literals.PORT__REQUIRED_INTERFACES;
            if (mustInverse(allocation.getAllocatedItem())) {
              sourceReference = InformationPackage.Literals.PORT__REQUIRED_INTERFACES;
              targetReference = InformationPackage.Literals.PORT__PROVIDED_INTERFACES;
            }
            cPortProviding = getRelatedPort(b_p, itf, exchange.getSource(), exchange, sourceReference);
            cPortRequiring = getRelatedPort(c_p, itf, exchange.getTarget(), exchange, targetReference);

          } else if (a_p instanceof ComponentExchange) {
            ComponentExchange connection = (ComponentExchange) a_p;
            Port tmp = ComponentExchangeExt.getSourcePort(connection);
            if (tmp != null && tmp instanceof ComponentPort) {
              cPortProviding = (ComponentPort) tmp;
            }
            tmp = ComponentExchangeExt.getTargetPort(connection);
            if (tmp != null && tmp instanceof ComponentPort) {
              cPortRequiring = (ComponentPort) tmp;
            }
          }

          //Invert port according to exchange item allocation
          if (mustInverse(allocation.getAllocatedItem())) {
            ComponentPort tmp = cPortProviding;
            cPortProviding = cPortRequiring;
            cPortRequiring = tmp;
          }

          //Attach interface to ports
          if (cPortProviding != null) {
            if (!cPortProviding.getProvidedInterfaces().contains(itf)) {
              cPortProviding.getProvidedInterfaces().add(itf);
            }
          }
          if (cPortRequiring != null) {
            if (!cPortRequiring.getRequiredInterfaces().contains(itf)) {
              cPortRequiring.getRequiredInterfaces().add(itf);
            }
          }
        }
      }
    }
  }

  /**
   * @param allocatedItem_p
   * @return
   */
  private boolean mustInverse(AbstractExchangeItem allocatedItem_p) {
    return true;
  }

  /**
   * Retrieve a component port on the given component which :
   * is related to the given FunctionPort and has a connection allocating the FunctionalExchange
   * or
   * eGet(reference) contains the interface
   */
  protected ComponentPort getRelatedPort(Component component_p, Interface itf_p, ActivityNode activityNode_p, FunctionalExchange a_p, EReference reference_p) {
    ComponentPort port = null;

    if (activityNode_p != null) {
      port = getRelatedPort(activityNode_p, component_p, a_p);
    }

    if (port == null) {
      for (Feature f : component_p.getOwnedFeatures()) {
        if (f instanceof ComponentPort) {
          if (((EList<?>) ((ComponentPort) f).eGet(reference_p)).contains(itf_p)) {
            port = ((ComponentPort) f);
            break;
          }
        }
      }
    }

    if (port == null) {
      port = createPort(component_p, activityNode_p);
    }

    if (port != null && activityNode_p != null) {
      PortExt.attachPort(port, activityNode_p);
    }

    return port;
  }

  /** 
   * Retrieve port related to the function port
   */
  protected ComponentPort getRelatedPort(ActivityNode fport, Component component_p, FunctionalExchange a_p) {
    if (fport == null || component_p == null) {
      return null;
    }

    for (Feature f : component_p.getOwnedFeatures()) {
      if (f instanceof ComponentPort) {
        ComponentPort cport = (ComponentPort) f;
        if (isRelatedTo(cport, fport, a_p)) {
          return cport;
        }
      }
    }
    return null;
  }

  /**
   * Returns whether the component port is related to the function port and has a connection 
   * which allocate the functional exchange
   */
  public static boolean isRelatedTo(ComponentPort cport_p, ActivityNode fport_p, FunctionalExchange a_p) {
    boolean isValid = PortExt.isRelatedTo(cport_p, fport_p);

    if (isValid) {
      for (ComponentExchangeFunctionalExchangeAllocation allocation : a_p.getIncomingComponentExchangeFunctionalExchangeRealizations()) {
        if (cport_p.getComponentExchanges().contains(allocation.getAllocatingComponentExchange())) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * Create a component port into the given component and set kind/orientation according to given activityNode)
   */
  protected ComponentPort createPort(Component parent, ActivityNode node) {
    ComponentPort port = FaFactory.eINSTANCE.createComponentPort();
    if (ProjectionPreferencesPlugin.getDefault().generateStandardPortRatherThanFlowPort()) {
      port.setKind(ComponentPortKind.STANDARD);
    } else {
      port.setKind(ComponentPortKind.FLOW);
      if (node instanceof FunctionOutputPort) {
        port.setOrientation(OrientationPortKind.OUT);
      } else if (node instanceof FunctionInputPort) {
        port.setOrientation(OrientationPortKind.IN);
      }
    }
    if (node != null) {
      port.setName(node.getName());
    }
    parent.getOwnedFeatures().add(port);
    return port;
  }

}
