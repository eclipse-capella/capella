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

package org.polarsys.capella.core.model.helpers.listeners;

import java.util.Set;

import org.eclipse.emf.common.notify.impl.NotificationChainImpl;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.model.helpers.AssociationExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;

/**
 */
public class MoveDataPkgEventProcessor extends MoveElementEventProcessor {

  @Override
  public void process() {
    // move the target datapkg associations to their correct containers
    for (EObject eObj : oldValueNotificationMap.keySet()) {
      if (eObj instanceof DataPkg) {
        NotificationChainImpl notifChain = newValueNotificationMap.get(eObj);
        if ((null != notifChain) && !notifChain.isEmpty()) {
          Set<Association> allAssociations = DataPkgExt.getAllInvolvedAssociations((DataPkg) eObj);

          for (Association assoc : allAssociations) {
            AssociationExt.moveToCorrectContainer(assoc);
          }
        }
      }
    }

  }
}
