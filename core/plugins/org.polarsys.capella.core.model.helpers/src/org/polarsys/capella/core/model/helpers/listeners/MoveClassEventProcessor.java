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

package org.polarsys.capella.core.model.helpers.listeners;

import java.util.List;

import org.eclipse.emf.common.notify.impl.NotificationChainImpl;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.model.helpers.AssociationExt;
import org.polarsys.capella.core.model.helpers.ClassExt;

/**
 */
public class MoveClassEventProcessor extends MoveElementEventProcessor {

  /**
   * {@inheritDoc}
   */
  @Override
  public void process() {
    // move the associations of the moved classes to their correct containers
    for (EObject clazz : oldValueNotificationMap.keySet()) {
      if (clazz instanceof Class) {
        NotificationChainImpl notifChain = newValueNotificationMap.get(clazz);
        if ((null != notifChain) && !notifChain.isEmpty()) {
          List<Association> allAssociations = ClassExt.getAllAssociations((Classifier) clazz);

          for (Association assoc : allAssociations) {
            AssociationExt.moveToCorrectContainer(assoc);
          }
        }
      }
    }

  }

}
