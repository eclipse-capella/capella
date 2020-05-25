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
package org.polarsys.capella.core.projection.exchanges;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionalExt;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.PhysicalLinkExt;

/**
 * This class is the <code>IExchangesCreator</code> implementation specific to node physical components.<br>
 * This implementation creates physical links.
 */
public class PhysicalLinksCreator extends DefaultExchangesCreator {

  /**
   * Constructor
   * @param component_p
   */
  public PhysicalLinksCreator(Component component_p, Part part_p) {
    super(component_p);
  }

  /**
   * This implementation creates physical links.
   * @see org.polarsys.capella.core.projection.commands.utils.DefaultExchangesCreator#createExchanges()
   */
  @Override
  public void createExchanges() {
    if (_component instanceof LogicalComponent || _component instanceof SystemComponent) {
      createPhysicalLinksFromCExchanges(_component);
    }
  }

  /**
   * @see org.polarsys.capella.core.projection.exchanges.DefaultExchangesCreator#isValidCreation(org.polarsys.capella.core.data.fa.FunctionalExchange,
   *      org.polarsys.capella.core.data.cs.Component, org.polarsys.capella.core.data.cs.Component)
   */
  @Override
  protected boolean isValidCreation(AbstractEventOperation fe_p, Component component_p, Component allocating_p) {
    return isValidBound(component_p) && isValidBound(allocating_p);
  }

  /**
   * @param lc
   * @param type
   */
  private void createPhysicalLinksFromCExchanges(Component lc) {
    if ((lc != null)) {
      // Process each lc
      // This reference will allows to handle the processed connections
      for (ComponentPort port : ComponentExt.getOwnedComponentPort(lc)) {
        // Process the flow ports of the deployed PC
        // filter inValid port
        // if (PortExt.isOut(port)) {
        // get all the connection of the port
        for (ComponentExchange connection : port.getComponentExchanges()) {
          // filter delegation and unSet type of connection
          if ((connection.getKind() != ComponentExchangeKind.DELEGATION) && (connection.getKind() != ComponentExchangeKind.UNSET)) {
            // proceed only if port is a source of current
            // Connection
            // if (connection.getSource().equals(port)) {
            // check if physicalLink creation is necessary
            if (!doesNodeAlreadyHaveAPhysicalLinkForComponentExchange(lc, connection)) {
              // get the opposite port [which could be
              // source or target of the Connection]
              InformationsExchanger target = FunctionalExt.getOtherBound(connection, port);
              if ((target != null) && (target instanceof ComponentPort)) {
                // get the container of the target port
                EObject container = target.eContainer();
                // find the target Node
                if (container instanceof LogicalComponent || container instanceof SystemComponent) {
                  doCreatePhysicalLink(connection, lc, (Component) container);
                }
              }
            }
          }
        }
      }

    }
  }

  /**
   * Create a physical link corresponding to the given component exchange, between the given components
   * @param componentExchange_p the source component exchange
   * @param exchangeOutput_p the output component
   * @param exchangeInput_p the input component
   */
  protected void doCreatePhysicalLink(ComponentExchange componentExchange_p, Component exchangeOutput_p, Component exchangeInput_p) {
    // Precondition:
    if (exchangeOutput_p == exchangeInput_p) {
      // Not necessary to create a physical link for exchanges inside the
      // same container.
      return;
    }
    PhysicalLink physicalLink = CsFactory.eINSTANCE.createPhysicalLink(componentExchange_p.getLabel());
    PhysicalPort outP = CsFactory.eINSTANCE.createPhysicalPort(componentExchange_p.getSource().getLabel());
    PhysicalPort inP = CsFactory.eINSTANCE.createPhysicalPort(componentExchange_p.getTarget().getLabel());
    physicalLink.getLinkEnds().add(outP);
    physicalLink.getLinkEnds().add(inP);

    exchangeInput_p.getOwnedFeatures().add(inP);
    exchangeOutput_p.getOwnedFeatures().add(outP);
    CapellaElementExt.creationService(inP);
    CapellaElementExt.creationService(outP);

    PhysicalLinkExt.attachToDefaultContainer(physicalLink);
    CapellaElementExt.creationService(physicalLink);

    // Creates the exchange allocation
    ComponentExchangeAllocation cea = FaFactory.eINSTANCE.createComponentExchangeAllocation();
    cea.setSourceElement(physicalLink);
    cea.setTargetElement(componentExchange_p);
    physicalLink.getOwnedComponentExchangeAllocations().add(cea);
    CapellaElementExt.creationService(cea);

    // source side delegation
    InformationsExchanger target = componentExchange_p.getTarget();
    createComponentPortAllocation(target, inP);

    // target side Delegation
    InformationsExchanger source = componentExchange_p.getSource();
    createComponentPortAllocation(source, outP);

  }

  /**
   * @param informationExchange_p
   * @param physicalPort_p
   * @param connection_p
   */
  private ComponentPortAllocation createComponentPortAllocation(InformationsExchanger informationExchange_p, PhysicalPort physicalPort_p) {
    ComponentPortAllocation allocation = FaFactory.eINSTANCE.createComponentPortAllocation();
    allocation.setSourceElement(physicalPort_p);
    allocation.setTargetElement((TraceableElement) informationExchange_p);
    physicalPort_p.getOwnedComponentPortAllocations().add(allocation);
    CapellaElementExt.creationService(allocation);
    return allocation;
  }

  /**
   * This method allows to know if the given component exchange has already been allocated to a physical link linked to the given physical component.
   * @param physicalComponent_p the physical component
   * @param componentExchange_p the component exchange
   * @return true if its has already been allocated, false otherwise
   */
  protected boolean doesNodeAlreadyHaveAPhysicalLinkForComponentExchange(Component physicalComponent_p, ComponentExchange componentExchange_p) {
    boolean result = false;
    // Get the semantic editing domain to access the cross referencer.
    SemanticEditingDomain editingDomain = (SemanticEditingDomain) AdapterFactoryEditingDomain.getEditingDomainFor(componentExchange_p);
    // Get the cross referencer.
    ECrossReferenceAdapter crossReferencer = editingDomain.getCrossReferencer();
    // Search inverses relations on given component exchange.
    Collection<Setting> inverseReferences = crossReferencer.getInverseReferences(componentExchange_p, true);
    for (Setting setting : inverseReferences) {
      // Search for a relation targeting the ComponentExchangeAllocation metaclass.
      if (setting.getEObject() instanceof ComponentExchangeAllocation) {
        result = true;
        break;
      }
    }
    return result;
  }
}