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

package org.polarsys.capella.common.platform.sirius.ted;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;

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
  public void notify(Session updatedSession, int changeKind) {
    TransactionalEditingDomain transactionalEditingDomain = updatedSession.getTransactionalEditingDomain();
    switch (changeKind) {
    
    case SessionListener.CLOSED:
      _status.remove(transactionalEditingDomain);
      break;
    case SessionListener.OPENING:
      if(transactionalEditingDomain instanceof SemanticEditingDomain){
        ((SemanticEditingDomain)transactionalEditingDomain).getCrossReferencer().enableResolveProxy();
      }
      _status.put(transactionalEditingDomain, Integer.valueOf(changeKind));
      break;
    
    case SessionListener.OPENED:
      if(transactionalEditingDomain instanceof SemanticEditingDomain){
        ((SemanticEditingDomain)transactionalEditingDomain).getCrossReferencer().disableResolveProxy();
      }
      _status.put(transactionalEditingDomain, Integer.valueOf(changeKind));
      break;

    default:
      _status.put(transactionalEditingDomain, Integer.valueOf(changeKind));
      break;
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
