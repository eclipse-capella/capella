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
package org.polarsys.capella.common.platform.sirius.ted;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManagerListener;

/**
 * Used to monitor sessions to enable / disable the Capella Cross referencing and the Data Notifier.
 */
public class SiriusSessionListener extends SessionManagerListener.Stub {

  // FIXME verify that this map does not introduce a memory leak
  private static Map<EditingDomain, Integer> _status = new HashMap<EditingDomain, Integer>();

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notify(org.eclipse.sirius.business.api.session.Session, int)
   */
  @Override
  public void notify(Session updatedSession_p, int notification_p) {
    if (SessionListener.CLOSED == notification_p) {
      _status.remove(updatedSession_p.getTransactionalEditingDomain());

    } else {

      _status.put(updatedSession_p.getTransactionalEditingDomain(), Integer.valueOf(notification_p));
    }
  }

  public static boolean isClosingSession(EditingDomain editingDomain) {
    return (existSession(editingDomain) && (SessionListener.CLOSING == _status.get(editingDomain).intValue()));
  }

  public static boolean isOpeningSession(EditingDomain editingDomain) {
    return (existSession(editingDomain) && (SessionListener.OPENING == _status.get(editingDomain).intValue()));
  }

  public static boolean existSession(EditingDomain editingDomain) {
    return _status.containsKey(editingDomain);
  }
}
