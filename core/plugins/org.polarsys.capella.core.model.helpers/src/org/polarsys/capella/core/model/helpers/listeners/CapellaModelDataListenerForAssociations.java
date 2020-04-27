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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.model.helpers.AssociationExt;

/**
 */
public class CapellaModelDataListenerForAssociations extends CapellaModelDataListener {
  /**
   * This listener will update association econtaining feature
   */
  @Override
  public void notifyChanged(Notification notification) {
    // pre-condition: call contributed filters
    if (filterNotification(notification)) {
      return;
    }

    // we observe only REMOVE and ADD events
    if ((notification.getEventType() != Notification.ADD) && (notification.getEventType() != Notification.REMOVE)) {
      return;
    }
    // only Association notifications are relevant
    if (notification.getNotifier() instanceof Association) {
      Association assoc = (Association) notification.getNotifier();
      EStructuralFeature feature = (EStructuralFeature) notification.getFeature();
      if (feature != null) {
        if (feature.equals(InformationPackage.Literals.ASSOCIATION__OWNED_MEMBERS)
            || feature.equals(InformationPackage.Literals.ASSOCIATION__NAVIGABLE_MEMBERS)) {
          // check if we are in a stable state i.e. assoc.navigableMembers.size+assoc.ownedMembers.size==2.
          // Association is in stable state when both ADD and REMOVE modifications are been applied.
          if (2 == (assoc.getOwnedMembers().size() + assoc.getNavigableMembers().size())) {
            // move the association to its correct container
            AssociationExt.moveToCorrectContainer(assoc);
          }
        }
      }
    }
  }
}
