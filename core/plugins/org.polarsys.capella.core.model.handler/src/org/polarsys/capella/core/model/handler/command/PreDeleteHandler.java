/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
  public List<Notification> notifications = new ArrayList<Notification>(0);
  /**
   * Map of elements that might be deleted in the end, if the list of specified elements becomes empty during the impact analysis.
   */
  public Map<EObject, List<EObject>> pendingElements = new HashMap<EObject, List<EObject>>(0);

  /**
   * Free memory.
   */
  public void dispose() {
    if (null != notifications) {
      notifications.clear();
      notifications = null;
    }
    if (null != pendingElements) {
      pendingElements.clear();
      pendingElements = null;
    }
  }
}
