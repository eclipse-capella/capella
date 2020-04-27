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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationChainImpl;
import org.eclipse.emf.ecore.EObject;

/**
 * An event process that stores ADD and REMOVE notifications and enable to clear them.
 */
public class MoveElementEventProcessor implements EventProcessor {

  /**
   * stores ADD element notifications
   */
  EObjectNotificationMap newValueNotificationMap = new EObjectNotificationMap();
  /**
   * stores REMOVE element notifications
   */
  EObjectNotificationMap oldValueNotificationMap = new EObjectNotificationMap();

  /**
   * {@inheritDoc}
   */
  @Override
  public void add(Notification notif) {
    if (notif.getEventType() == Notification.ADD) {
      Object newValue = notif.getNewValue();
      if (newValue instanceof EObject) {
        newValueNotificationMap.add((EObject) newValue, notif);
      }
    }
    if (notif.getEventType() == Notification.REMOVE) {
      Object oldValue = notif.getOldValue();
      if (oldValue instanceof EObject) {
        oldValueNotificationMap.add((EObject) oldValue, notif);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clearConsumed() {
    for ( Map.Entry<EObject,NotificationChainImpl> entry : oldValueNotificationMap.entrySet()) {
      clearNotificationChains(entry.getValue(), newValueNotificationMap.get(entry.getKey()));
    }
    clearMaps();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void process() {
    // do nothing

  }

  private void clearNotificationChains(NotificationChainImpl notifChain1, NotificationChainImpl notifChain2) {
    if ((null == notifChain1) || (null == notifChain2)) {
      return;
    }
    if (notifChain1.size() == notifChain2.size()) {
      notifChain1.clear();
      notifChain2.clear();
    } else {
      List<Notification> toRemoveFrom1 = new ArrayList<Notification>();
      List<Notification> toRemoveFrom2 = new ArrayList<Notification>();
      for (Notification n1 : notifChain1) {
        for (Notification n2 : notifChain2) {
          if (n1.getNotifier() == n2.getNotifier()) {
            toRemoveFrom1.add(n1);
            toRemoveFrom2.add(n2);
          }
        }
      }
      notifChain1.removeAll(toRemoveFrom1);
      notifChain1.removeAll(toRemoveFrom2);
    }

  }

  private void clearMaps() {
    Set<EObject> toRemoveFromNew = new HashSet<EObject>();
    Set<EObject> toRemoveFromOld = new HashSet<EObject>();

    for ( Map.Entry<EObject,NotificationChainImpl> entry : newValueNotificationMap.entrySet()) {
      NotificationChainImpl chain = entry.getValue();
      if ((null == chain) || chain.isEmpty()) {
        toRemoveFromNew.add(entry.getKey());
      }
    }
    for (Map.Entry<EObject,NotificationChainImpl> entry : oldValueNotificationMap.entrySet()) {
      NotificationChainImpl chain = entry.getValue();
      if ((null == chain) || chain.isEmpty()) {
        toRemoveFromNew.add(entry.getKey());
      }
      for (EObject c : toRemoveFromOld) {
        oldValueNotificationMap.remove(c);

      }
      for (EObject c : toRemoveFromNew) {
        newValueNotificationMap.remove(c);

      }
    }

  }

}
