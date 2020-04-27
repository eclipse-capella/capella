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

import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.model.helpers.AssociationExt;
import org.polarsys.capella.core.model.helpers.ClassExt;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class CapellaModelDataListenerForAssociationProperties extends CapellaModelDataListener {
  /**
   * This listener will update association econtaining feature
   */
  @Override
  public void notifyChanged(Notification notification) {
    // pre-condition: call contributed filters
    if (filterNotification(notification)) {
      return;
    }

    // we observe only SET events
    if ((notification.getEventType() != Notification.SET)) {
      return;
    }
    // only Association notifications are relevant
    if (notification.getNotifier() instanceof Property) {
      Property prop = (Property) notification.getNotifier();
      EStructuralFeature feature = (EStructuralFeature) notification.getFeature();
      if (feature != null) {
        if (feature.equals(ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE)) {
          EObject eContainer = prop.eContainer();
          if ((null != eContainer) && (eContainer instanceof Association)) {
            Association assoc = (Association) eContainer;
            AssociationExt.moveToCorrectContainer(assoc);
          }
          if ((null != eContainer) && (eContainer instanceof Class)) {
            // An outgoing association has been moved
            Class clazz = (Class) eContainer;
            List<Association> assocs = ClassExt.getAllAssociationsButIncoming(clazz);
            for (Association assoc : assocs) {
              AssociationExt.moveToCorrectContainer(assoc);
            }
          }
        }
      }
    }
  }
}
