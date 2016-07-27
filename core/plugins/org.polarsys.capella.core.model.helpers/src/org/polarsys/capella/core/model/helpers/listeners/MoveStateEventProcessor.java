/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.helpers.listeners;

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
    for (EObject clazz : oldValueNotificationMap.keySet()) {
      // eContainer can be null (e.g.: during deletions)
      if (clazz instanceof AbstractState && clazz.eContainer() != null) {

        NotificationChainImpl oldNotifChain = oldValueNotificationMap.get(clazz);
        NotificationChainImpl newNotifChain = newValueNotificationMap.get(clazz);
        if ((null != newNotifChain) && !newNotifChain.isEmpty()) {

          for (StateTransition transition : ((AbstractState) clazz).getOutgoing()) {
            if (transition.eContainer() != null) {

              for (int i = 0; i < oldNotifChain.size(); i++) {
                Notification notification = oldNotifChain.get(i);
                if (transition.eContainer().equals(notification.getNotifier())) {
                  ((Region) clazz.eContainer()).getOwnedTransitions().add(transition);
                }
              }
            }
          }
        }
      }
    }

  }

}
