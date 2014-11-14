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

import java.util.HashMap;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationChainImpl;
import org.eclipse.emf.ecore.EObject;

/**
 */
public class EObjectNotificationMap extends HashMap<EObject, NotificationChainImpl> {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public void add(EObject c, Notification n) {
    if (null == get(c)) {
      NotificationChainImpl notifChain = new NotificationChainImpl();
      notifChain.add(n);
      put(c, notifChain);
    } else {
      get(c).add(n);
    }
  }
}
