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
package org.polarsys.capella.core.model.helpers.listeners;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.Region;

/**
 * This listener is used to synchronize involvedStates references when an AbstractState is moved from one Region to another
 */
public class CapellaModelDataListenerForAbstractStates extends CapellaModelDataListener {

  /**
   * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
   */
  @Override
  public void notifyChanged(Notification notification_p) {
    // Preconditions :
    // Call contributed filters.
    if (filterNotification(notification_p)) {
      return;
    }

    Object notifier = notification_p.getNotifier();
    EStructuralFeature feature = (EStructuralFeature) notification_p.getFeature();

    // Only ADD and REMOVE notifications are wanted.
    if (!((notification_p.getEventType() == Notification.ADD) || (notification_p.getEventType() == Notification.REMOVE))) {
      return;
    }

    if (CapellacommonPackage.Literals.REGION__OWNED_STATES.equals(feature)) {
      Region region = (Region) notifier;

      if (notification_p.getEventType() == Notification.REMOVE) {
        region.getInvolvedStates().remove(notification_p.getOldValue());

      } else if (notification_p.getEventType() == Notification.ADD) {
        region.getInvolvedStates().add((AbstractState) notification_p.getNewValue());
      }

    }

  }

}
