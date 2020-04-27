/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.helpers.listeners;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.helpers.cs.services.ExchangeItemAllocationExt;
import org.polarsys.capella.core.data.helpers.information.services.CommunicationLinkExt;
import org.polarsys.capella.core.data.helpers.information.services.ExchangeItemElementExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;

/**
 */
public class CapellaModelDataListenerForExchangeItemsAndCommunicationLinks extends CapellaModelDataListener {
  /**
   * This listener will update: <li>all element
   * 
   * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
   */
  @Override
  public void notifyChanged(Notification notification) {
    // pre-condition: call contributed filters
    if (filterNotification(notification)) {
      return;
    }

    // pre-condition: only SET notifications are wanted
    if ((notification.getEventType() != Notification.SET) && (notification.getEventType() != Notification.ADD)) {
      return;
    }

    EStructuralFeature feature = (EStructuralFeature) notification.getFeature();
    if (feature != null) {
      if (notification.getEventType() == Notification.SET
          && (feature.equals(InformationPackage.Literals.EXCHANGE_ITEM__EXCHANGE_MECHANISM) || (feature
              .equals(InformationPackage.Literals.EXCHANGE_ITEM_ELEMENT__KIND)))) {
        Object notifier = notification.getNotifier();
        if (notifier instanceof ExchangeItem) {
          final ExchangeItem item = (ExchangeItem) notifier;
          executeCommand(item, new AbstractReadWriteCommand() {
            @Override
            public void run() {
              for (CommunicationLink link : getRelatedCommunicationLinks(item)) {
                CommunicationLinkExt.changeExchangeItem(link, item);
              }
              for (ExchangeItemAllocation alloc : getRelatedAllocations(item)) {
                ExchangeItemAllocationExt.changeExchangeItem(alloc, item);
              }
              for (ExchangeItemElement element : item.getOwnedElements()) {
                ExchangeItemElementExt.changeExchangeItemElementKind(element, item.getExchangeMechanism());
              }
            }
          });
        } else if (notifier instanceof ExchangeItemElement) {
          final ExchangeItemElement eie = (ExchangeItemElement) notifier;
          executeCommand(eie, new AbstractReadWriteCommand() {

            @Override
            public void run() {
              if (eie.eContainer() != null)
                ExchangeItemElementExt.changeExchangeItemElementDirection(eie,
                    ((ExchangeItem) eie.eContainer()).getExchangeMechanism());
            }

          });

        }

      } else if ((notification.getEventType() == Notification.ADD && feature
          .equals(InformationPackage.Literals.EXCHANGE_ITEM__OWNED_ELEMENTS))) {
        Object notifier = notification.getNotifier();
        if (notifier instanceof ExchangeItem) {
          final ExchangeItem item = (ExchangeItem) notifier;
          final Object newValue = notification.getNewValue();
          if (null != newValue && newValue instanceof ExchangeItemElement) {
            executeCommand(item, new AbstractReadWriteCommand() {
              @Override
              public void run() {
                ExchangeItemElementExt.changeExchangeItemElementKind((ExchangeItemElement) newValue,
                    item.getExchangeMechanism());
              }
            });
          }
        }
      }
    }
  }

  public static Collection<CommunicationLink> getRelatedCommunicationLinks(AbstractExchangeItem sndItem) {
    HashSet<CommunicationLink> result = new HashSet<CommunicationLink>();
    for (Object objectRef : EObjectExt.getReferencers(sndItem,
        CommunicationPackage.Literals.COMMUNICATION_LINK__EXCHANGE_ITEM)) {
      result.add((CommunicationLink) objectRef);
    }
    return result;
  }

  public static Collection<ExchangeItemAllocation> getRelatedAllocations(AbstractExchangeItem item) {
    HashSet<ExchangeItemAllocation> result = new HashSet<ExchangeItemAllocation>();
    for (Object objectRef : EObjectExt
        .getReferencers(item, CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM)) {
      result.add((ExchangeItemAllocation) objectRef);
    }
    return result;
  }
}
