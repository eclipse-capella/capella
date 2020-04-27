/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.viewer;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;

/**
 * This class manages active sessions, mainly used to enable/disable notification when the session is active/closed.
 */
public class ActiveSessionManager {
  final Map<TransactionalEditingDomain, Boolean> _activeSessions = new HashMap<TransactionalEditingDomain, Boolean>();

  private boolean isFullDisabled = false;

  private ActiveSessionManager() {
  }

  private static class SingletonHolder {
    private static final ActiveSessionManager INSTANCE = new ActiveSessionManager();
  }

  public static ActiveSessionManager getInstance() {
    return SingletonHolder.INSTANCE;
  }

  /**
   * Disable content notifications.<br>
   * Method {@link #notifyChanged(Notification)} will no longer do anything.
   */
  public void disableContentNotifications(SemanticEditingDomain editingDomain) {
    _activeSessions.put(editingDomain, Boolean.FALSE);
  }

  public void disableContentNotifications() {
    isFullDisabled = true;
    for (TransactionalEditingDomain key : _activeSessions.keySet()) {
      _activeSessions.put(key, Boolean.FALSE);
    }
  }

  /**
   * Re-enable content notifications.<br>
   * Method {@link #notifyChanged(Notification)} behaves as expected.
   */
  public void enableContentNotifications(SemanticEditingDomain editingDomain) {
    _activeSessions.put(editingDomain, Boolean.TRUE);
  }

  public void enableContentNotifications() {
    isFullDisabled = false;
    for (TransactionalEditingDomain key : _activeSessions.keySet()) {
      _activeSessions.put(key, Boolean.TRUE);
    }
  }

  /**
   * Returns whether notifications are enabled for the given domain
   */
  public boolean isEnabledContentNotifications(TransactionalEditingDomain editingDomain) {
    if (isFullDisabled) {
      return false;
    }
    if (editingDomain == null) {
      return true;
    }
    if (!_activeSessions.containsKey(editingDomain)) {
      return true;
    }
    return _activeSessions.get(editingDomain);
  }

  public void remove(TransactionalEditingDomain editingDomain) {
    _activeSessions.remove(editingDomain);
  }
}
