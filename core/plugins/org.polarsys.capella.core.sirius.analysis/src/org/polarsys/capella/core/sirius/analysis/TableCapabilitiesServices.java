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
package org.polarsys.capella.core.sirius.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityPkgExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;

public class TableCapabilitiesServices {

  /**
   * Returns all capabilities of the current system analysis
   */
  public Collection<AbstractCapability> getAllCapabilities(BlockArchitecture architecture) {
    Collection<AbstractCapability> capabilities = new ArrayList<AbstractCapability>();

    if ((architecture != null) && (architecture.getOwnedAbstractCapabilityPkg() != null)) {
      getAllCapabilities(architecture.getOwnedAbstractCapabilityPkg(), capabilities);
    }
    return capabilities;
  }

  /**
   * Returns all capabilities which use the current operation
   */
  public Collection<AbstractCapability> isUsedInCapabilities(EObject exchangeItem) {
    List<AbstractCapability> capabilitiesUsed = new ArrayList<AbstractCapability>();

    ExchangeItemAllocation exchangeItem_p = (ExchangeItemAllocation) exchangeItem;

    // Find if the capability use the operation in its scenario
    for (AbstractCapability capability : getRelatedCapabilities(exchangeItem_p)) {
      for (Scenario scenario : capability.getOwnedScenarios()) {
        if (isUsedInScenario(scenario, exchangeItem_p)) {
          capabilitiesUsed.add(capability);
        }
      }
    }

    return capabilitiesUsed;
  }

  /**
   * Return all related capability which are associated to the operation
   */
  protected List<AbstractCapability> getRelatedCapabilities(ExchangeItemAllocation exchangeItem_p) {
    CsServices cciiServices = CsServices.getService();
    List<AbstractCapability> capabilities = new ArrayList<AbstractCapability>();
    try {
      Collection<Component> components = new HashSet<Component>();

      Interface inter = exchangeItem_p.getAllocatingInterface();
      if (inter != null) {
        components.addAll(InterfaceExt.getRelatedComponents(inter));
      }

      for (Component component : components) {
        if (component instanceof SystemComponent) {
          capabilities.addAll(((SystemComponent) component).getInvolvingCapabilities());
        } else {
          getAllCapabilities(component.getOwnedAbstractCapabilityPkg(), capabilities);
          for (EObject parent : cciiServices.getParentContainers(component)) {
            if (parent instanceof Component) {
              getAllCapabilities(((Component) parent).getOwnedAbstractCapabilityPkg(), capabilities);
            } else if (parent instanceof BlockArchitecture) {
              getAllCapabilities(((BlockArchitecture) parent).getOwnedAbstractCapabilityPkg(), capabilities);
            }
          }
        }
      }

    } catch (Exception e) {
      //
    }
    return capabilities;
  }

  /**
   * Return whether the scenario use the operation into a sequence message
   */
  protected boolean isUsedInScenario(Scenario scenario, AbstractEventOperation operation) {
    for (SequenceMessage message : scenario.getOwnedMessages()) {
      if (isAssociatedToOperation(message.getSendingEnd(), operation) || isAssociatedToOperation(message.getReceivingEnd(), operation)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Return scenarios which use the given operation
   */
  public Collection<Scenario> isUsedInScenarios(ExchangeItemAllocation operation) {
    List<Scenario> scenarioUsed = new ArrayList<Scenario>();
    for (AbstractCapability capability : getRelatedCapabilities(operation)) {
      for (Scenario scenario : capability.getOwnedScenarios()) {
        if (isUsedInScenario(scenario, operation)) {
          scenarioUsed.add(scenario);
        }
      }
    }
    return scenarioUsed;
  }

  /**
   * Returns whether the given service is used into the message end
   */
  protected boolean isAssociatedToOperation(MessageEnd end, AbstractEventOperation operation) {
    if ((end != null) && (end.getEvent() != null) && (operation != null)) {

      AbstractEventOperation operationEvent = null;

      if (end.getEvent() instanceof EventSentOperation) {
        operationEvent = ((EventSentOperation) end.getEvent()).getOperation();
      } else if (end.getEvent() instanceof EventReceiptOperation) {
        operationEvent = ((EventReceiptOperation) end.getEvent()).getOperation();
      }

      if (operationEvent != null) {
        if (operation.equals(operationEvent)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Returns all interfaces which are involved into capabilities of the given system analysis
   */
  public Collection<Interface> getAllInterfacesInvolved(BlockArchitecture system) {
    Collection<AbstractCapability> capabilities = getAllCapabilities(system);
    Collection<Interface> involvedInterfaces = new ArrayList<Interface>();

    // Add for all capabilities, any interfaces involved
    for (AbstractCapability capability : capabilities) {
      addInvolvedInterfaces(capability, involvedInterfaces);
    }

    return involvedInterfaces;
  }

  /**
   * Returns all interfaces which are involved into the given capability
   */
  public Collection<Interface> getAllInterfacesInvolved(AbstractCapability capability) {
    Collection<Interface> involvedInterfaces = new ArrayList<Interface>();

    // Add any interfaces involved
    addInvolvedInterfaces(capability, involvedInterfaces);

    return involvedInterfaces;
  }

  /**
   * Returns all interfaces which are involved into the given capability
   */
  protected void addInvolvedInterfaces(AbstractCapability abstractCapability, Collection<Interface> interfaces) {
    List<Involvement> involvements = new ArrayList<Involvement>();
    CsServices se = CsServices.getService();

    if (abstractCapability instanceof Capability) {
      Capability capability = (Capability) abstractCapability;
      for (CapabilityInvolvement i : capability.getOwnedCapabilityInvolvements()) {
        if (i != null) {
          involvements.add(i);
        }
      }

    } else if (abstractCapability instanceof CapabilityRealization) {

      CapabilityRealization capability = (CapabilityRealization) abstractCapability;
      for (CapabilityRealizationInvolvement i : capability.getOwnedCapabilityRealizationInvolvements()) {
        if (i != null) {
          involvements.add(i);
        }
      }
    }

    if (involvements != null) {

      for (Involvement involvement : involvements) {
        if ((involvement.getInvolved() != null) && (involvement.getInvolved() instanceof Component)) {
          Component component = (Component) involvement.getInvolved();

          for (Interface itf : se.getAllRelatedInterfaces(component)) {
            if (!interfaces.contains(itf)) {
              interfaces.add(itf);
            }
          }

          // If capability realization, add related interfaces from sub components
          if (abstractCapability instanceof CapabilityRealization) {
            for (Component subComponent : se.getAllSubUsedComponents(component)) {
              for (Interface itf : se.getAllRelatedInterfaces(subComponent)) {
                if (!interfaces.contains(itf)) {
                  interfaces.add(itf);
                }
              }
            }
          }
        }
      }
    }
  }

  /**
   * Returns all services from the given interface
   */
  public Collection<ExchangeItemAllocation> getDefinedExchangeItems(Interface inter) {
    return inter.getOwnedExchangeItemAllocations();
  }

  /**
   * Fill the given list with all capabilities which are contained into the package
   */
  public void getAllCapabilities(AbstractCapabilityPkg pkg, Collection<AbstractCapability> list) {
    list.addAll(AbstractCapabilityPkgExt.getAllAbstractCapabilities(pkg));
  }

  /**
   * Returns a string of scenario of the given capability which use the given operation
   */
  public String getScenariosUsingService(AbstractEventOperation operation, AbstractCapability capability) {
    String res = ""; //$NON-NLS-1$
    List<Scenario> scenariosUsed = new ArrayList<Scenario>();
    for (Scenario scenario : capability.getOwnedScenarios()) {
      if (isUsedInScenario(scenario, operation)) {
        scenariosUsed.add(scenario);
      }
    }

    if (scenariosUsed.size() > 0) {
      for (int i = 0; i < scenariosUsed.size() - 1; i++) {
        res += EObjectExt.getText(scenariosUsed.get(i));
        res += ICommonConstants.SEMICOLON_CHARACTER;
      }
      res += EObjectExt.getText(scenariosUsed.get(scenariosUsed.size() - 1));
    }
    return res;
  }
}
