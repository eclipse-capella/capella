/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;

public class CapellaModelDataListenerForClass extends CapellaModelDataListener {

  /**
   * organise associations after a move of a class from a data pkg to another
   */
  EventProcessor eventProcessor = new MoveClassEventProcessor();

  @Override
  public void notifyChanged(Notification notif_p) {
    // pre-condition: call contributed filters
    if (filterNotification(notif_p)) {
      return;
    }

    int eventType = notif_p.getEventType();
    if ((eventType != Notification.ADD) && (eventType != Notification.REMOVE)) {
      return;
    }

    if ((notif_p.getNotifier() instanceof DataPkg) && InformationPackage.Literals.DATA_PKG__OWNED_CLASSES.equals(notif_p.getFeature())) {

      eventProcessor.add(notif_p);
      eventProcessor.process();
      eventProcessor.clearConsumed();

    }

  }

}
