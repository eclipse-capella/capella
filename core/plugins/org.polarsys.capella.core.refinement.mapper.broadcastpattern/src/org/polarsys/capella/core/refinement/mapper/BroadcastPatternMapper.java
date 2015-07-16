/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
	 * @param abstractInstance_p
	 * @param isIntraLayer_p
	 * @param decomposedComponent_p
	 * @param target_p
	 * @param srcScenario_p
	 * @param srcAbstractEnd_p
	 */
	public List<AbstractInstance> candidateComponents(AbstractInstance abstractInstance_p, boolean isIntraLayer_p, Component decomposedComponent_p, NamedElement target_p, Scenario srcScenario_p, AbstractEnd srcAbstractEnd_p)
	    throws MapperException {
		List<AbstractInstance> componentSet = new ArrayList<AbstractInstance>();

		if (abstractInstance_p instanceof ExchangeItemInstance) {
		  componentSet.add(abstractInstance_p);
		}

		return componentSet;
	}

	/**
	 * @param componentType_p
	 * @param invokedOperation_p
	 * @param candidateAbstractInstances_p
	 * @param abstractEnd_p
	 */
	public List<AbstractInstance> componentMapping(COMPONENT_TYPE componentType_p, AbstractEventOperation invokedOperation_p, List<AbstractInstance> candidateAbstractInstances_p, AbstractEnd abstractEnd_p)
	    throws MapperException {
		List<AbstractInstance> abstractInstanceSet = new ArrayList<AbstractInstance>();

    if (invokedOperation_p instanceof ExchangeItemAllocation) {
      for (AbstractInstance abstractInstance : candidateAbstractInstances_p) {
        AbstractType type = abstractInstance.getAbstractType();
        if (type instanceof Component) {
          Component cpnt = (Component) type;
          if (componentType_p == COMPONENT_TYPE.SENDER) {
            if (hasCallCommunicationLink(cpnt, (ExchangeItemAllocation) invokedOperation_p) ||
                hasSendCommunicationLink(cpnt, (ExchangeItemAllocation) invokedOperation_p) ||
                hasWriteCommunicationLink(cpnt, (ExchangeItemAllocation) invokedOperation_p) ||
                hasProduceCommunicationLink(cpnt, (ExchangeItemAllocation) invokedOperation_p))
            {
              abstractInstanceSet.add(abstractInstance);
            }
          } else if (componentType_p == COMPONENT_TYPE.RECEIVER) {
            if (hasAccessCommunicationLink(cpnt, (ExchangeItemAllocation) invokedOperation_p) ||
                hasExecuteCommunicationLink(cpnt, (ExchangeItemAllocation) invokedOperation_p) ||
                hasReceiveCommunicationLink(cpnt, (ExchangeItemAllocation) invokedOperation_p) ||
                hasConsumeCommunicationLink(cpnt, (ExchangeItemAllocation) invokedOperation_p))
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
   * @param cpnt_p
   * @param allocation_p
   */
  private boolean hasWriteCommunicationLink(Component cpnt_p, ExchangeItemAllocation allocation_p) {
    return hasCommunicationLink(cpnt_p, allocation_p, CommunicationLinkKind.WRITE);
  }
  
  /**
   * @param cpnt_p
   * @param allocation_p
   */
  private boolean hasAccessCommunicationLink(Component cpnt_p, ExchangeItemAllocation allocation_p) {
    return hasCommunicationLink(cpnt_p, allocation_p, CommunicationLinkKind.ACCESS);
  }

  /**
   * @param cpnt_p
   * @param allocation_p
   */
  private boolean hasSendCommunicationLink(Component cpnt_p, ExchangeItemAllocation allocation_p) {
    return hasCommunicationLink(cpnt_p, allocation_p, CommunicationLinkKind.SEND);
  }

  /**
   * @param cpnt_p
   * @param allocation_p
   */
  private boolean hasReceiveCommunicationLink(Component cpnt_p, ExchangeItemAllocation allocation_p) {
    return hasCommunicationLink(cpnt_p, allocation_p, CommunicationLinkKind.RECEIVE);
  }

  /**
   * @param cpnt_p
   * @param allocation_p
   */
  private boolean hasProduceCommunicationLink(Component cpnt_p, ExchangeItemAllocation allocation_p) {
    return hasCommunicationLink(cpnt_p, allocation_p, CommunicationLinkKind.PRODUCE);
  }

  /**
   * @param cpnt_p
   * @param allocation_p
   */
  private boolean hasConsumeCommunicationLink(Component cpnt_p, ExchangeItemAllocation allocation_p) {
    return hasCommunicationLink(cpnt_p, allocation_p, CommunicationLinkKind.CONSUME);
  }

  /**
   * @param cpnt_p
   * @param allocation_p
   */
  private boolean hasCallCommunicationLink(Component cpnt_p, ExchangeItemAllocation allocation_p) {
    return hasCommunicationLink(cpnt_p, allocation_p, CommunicationLinkKind.CALL);
  }

  /**
   * @param cpnt_p
   * @param allocation_p
   */
  private boolean hasExecuteCommunicationLink(Component cpnt_p, ExchangeItemAllocation allocation_p) {
    return hasCommunicationLink(cpnt_p, allocation_p, CommunicationLinkKind.EXECUTE);
  }

  /**
   * @param cpnt_p
   * @param allocation_p
   * @param kind_p
   */
  private boolean hasCommunicationLink(Component cpnt_p, ExchangeItemAllocation allocation_p, CommunicationLinkKind kind_p) {
    for (CommunicationLink communicationlink : cpnt_p.getOwnedCommunicationLinks()) {
      if ((communicationlink.getExchangeItem() == allocation_p.getAllocatedItem())
        && communicationlink.getKind().equals(kind_p))
      {
        return true;
      }
    }
    return false;
  }
}
