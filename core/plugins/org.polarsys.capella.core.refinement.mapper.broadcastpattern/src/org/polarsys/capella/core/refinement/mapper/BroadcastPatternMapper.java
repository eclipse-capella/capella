/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.mapper;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.helpers.interaction.services.MessageEndExt.COMPONENT_TYPE;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemInstance;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.MapperException;
import org.polarsys.capella.core.refinement.scenarios.core.plugs.IMapper;

public class BroadcastPatternMapper implements IMapper {

	/**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IRefinementPlug#getName()
   */
  public Object getName() {
    return "Broadcast Pattern Mapper"; //$NON-NLS-1$
  }

	/**
	 * @param abstractInstance
	 * @param isIntraLayer
	 * @param decomposedComponent
	 * @param target
	 * @param srcScenario
	 * @param srcAbstractEnd
	 */
	public List<AbstractInstance> candidateComponents(AbstractInstance abstractInstance, boolean isIntraLayer, Component decomposedComponent, NamedElement target, Scenario srcScenario, AbstractEnd srcAbstractEnd)
	    throws MapperException {
		List<AbstractInstance> componentSet = new ArrayList<AbstractInstance>();

		if (abstractInstance instanceof ExchangeItemInstance) {
		  componentSet.add(abstractInstance);
		}

		return componentSet;
	}

	/**
	 * @param componentType
	 * @param invokedOperation
	 * @param candidateAbstractInstances
	 * @param abstractEnd
	 */
	public List<AbstractInstance> componentMapping(COMPONENT_TYPE componentType, AbstractEventOperation invokedOperation, List<AbstractInstance> candidateAbstractInstances, AbstractEnd abstractEnd)
	    throws MapperException {
		List<AbstractInstance> abstractInstanceSet = new ArrayList<AbstractInstance>();

    if (invokedOperation instanceof ExchangeItemAllocation) {
      for (AbstractInstance abstractInstance : candidateAbstractInstances) {
        AbstractType type = abstractInstance.getAbstractType();
        if (type instanceof Component) {
          Component cpnt = (Component) type;
          if (componentType == COMPONENT_TYPE.SENDER) {
            if (hasCallCommunicationLink(cpnt, (ExchangeItemAllocation) invokedOperation) ||
                hasSendCommunicationLink(cpnt, (ExchangeItemAllocation) invokedOperation) ||
                hasWriteCommunicationLink(cpnt, (ExchangeItemAllocation) invokedOperation) ||
                hasProduceCommunicationLink(cpnt, (ExchangeItemAllocation) invokedOperation))
            {
              abstractInstanceSet.add(abstractInstance);
            }
          } else if (componentType == COMPONENT_TYPE.RECEIVER) {
            if (hasAccessCommunicationLink(cpnt, (ExchangeItemAllocation) invokedOperation) ||
                hasExecuteCommunicationLink(cpnt, (ExchangeItemAllocation) invokedOperation) ||
                hasReceiveCommunicationLink(cpnt, (ExchangeItemAllocation) invokedOperation) ||
                hasConsumeCommunicationLink(cpnt, (ExchangeItemAllocation) invokedOperation))
            {
              abstractInstanceSet.add(abstractInstance);
            }
          }
        } else if (type instanceof ExchangeItem) {
          abstractInstanceSet.add(abstractInstance);
        }
      }
    }

		return abstractInstanceSet;
	}

  /**
   * @param cpnt
   * @param allocation
   */
  private boolean hasWriteCommunicationLink(Component cpnt, ExchangeItemAllocation allocation) {
    return hasCommunicationLink(cpnt, allocation, CommunicationLinkKind.WRITE);
  }
  
  /**
   * @param cpnt
   * @param allocation
   */
  private boolean hasAccessCommunicationLink(Component cpnt, ExchangeItemAllocation allocation) {
    return hasCommunicationLink(cpnt, allocation, CommunicationLinkKind.ACCESS);
  }

  /**
   * @param cpnt
   * @param allocation
   */
  private boolean hasSendCommunicationLink(Component cpnt, ExchangeItemAllocation allocation) {
    return hasCommunicationLink(cpnt, allocation, CommunicationLinkKind.SEND);
  }

  /**
   * @param cpnt
   * @param allocation
   */
  private boolean hasReceiveCommunicationLink(Component cpnt, ExchangeItemAllocation allocation) {
    return hasCommunicationLink(cpnt, allocation, CommunicationLinkKind.RECEIVE);
  }

  /**
   * @param cpnt
   * @param allocation
   */
  private boolean hasProduceCommunicationLink(Component cpnt, ExchangeItemAllocation allocation) {
    return hasCommunicationLink(cpnt, allocation, CommunicationLinkKind.PRODUCE);
  }

  /**
   * @param cpnt
   * @param allocation
   */
  private boolean hasConsumeCommunicationLink(Component cpnt, ExchangeItemAllocation allocation) {
    return hasCommunicationLink(cpnt, allocation, CommunicationLinkKind.CONSUME);
  }

  /**
   * @param cpnt
   * @param allocation
   */
  private boolean hasCallCommunicationLink(Component cpnt, ExchangeItemAllocation allocation) {
    return hasCommunicationLink(cpnt, allocation, CommunicationLinkKind.CALL);
  }

  /**
   * @param cpnt
   * @param allocation
   */
  private boolean hasExecuteCommunicationLink(Component cpnt, ExchangeItemAllocation allocation) {
    return hasCommunicationLink(cpnt, allocation, CommunicationLinkKind.EXECUTE);
  }

  /**
   * @param cpnt
   * @param allocation
   * @param kind
   */
  private boolean hasCommunicationLink(Component cpnt, ExchangeItemAllocation allocation, CommunicationLinkKind kind) {
    for (CommunicationLink communicationlink : cpnt.getOwnedCommunicationLinks()) {
      if ((communicationlink.getExchangeItem() == allocation.getAllocatedItem())
        && communicationlink.getKind().equals(kind))
      {
        return true;
      }
    }
    return false;
  }
}
