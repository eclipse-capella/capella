/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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

import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationChainImpl;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.StateTransition;

public class MoveStateEventProcessor extends MoveElementEventProcessor {

  @Override
  public void process() {
    // We move outgoing transitions of a state from its old owning region to the new one
    for (Map.Entry<EObject,NotificationChainImpl> entry : oldValueNotificationMap.entrySet()) {
      // eContainer can be null (e.g.: during deletions)
      if (entry.getKey() instanceof AbstractState && entry.getKey().eContainer() != null) {

        NotificationChainImpl oldNotifChain = entry.getValue();
        NotificationChainImpl newNotifChain = newValueNotificationMap.get(entry.getKey());
        if ((null != newNotifChain) && !newNotifChain.isEmpty()) {

          for (StateTransition transition : ((AbstractState) entry.getKey()).getOutgoing()) {
            if (transition.eContainer() != null) {

              for (int i = 0; i < oldNotifChain.size(); i++) {
                Notification notification = oldNotifChain.get(i);
                if (transition.eContainer().equals(notification.getNotifier())) {
                  ((Region) entry.getKey().eContainer()).getOwnedTransitions().add(transition);
                }
              }
            }
          }
        }
      }
    }
  }
}
