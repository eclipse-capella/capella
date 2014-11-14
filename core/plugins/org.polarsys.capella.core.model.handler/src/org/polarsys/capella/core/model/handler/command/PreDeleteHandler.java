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
package org.polarsys.capella.core.model.handler.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;

/**
 * A structure that maintains the pre-delete notifications, and others shared elements between commands.
 */
public class PreDeleteHandler {
  /**
   * Synthetic notifications list, used to display the impact analysis.
   */
  public List<Notification> _notifications = new ArrayList<Notification>(0);
  /**
   * Map of elements that might be deleted in the end, if the list of specified elements becomes empty during the impact analysis.
   */
  public Map<EObject, List<EObject>> _pendingElements = new HashMap<EObject, List<EObject>>(0);

  /**
   * Free memory.
   */
  public void dispose() {
    if (null != _notifications) {
      _notifications.clear();
      _notifications = null;
    }
    if (null != _pendingElements) {
      _pendingElements.clear();
      _pendingElements = null;
    }
  }
}
