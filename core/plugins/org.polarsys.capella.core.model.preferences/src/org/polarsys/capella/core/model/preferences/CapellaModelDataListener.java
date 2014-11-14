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
package org.polarsys.capella.core.model.preferences;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.InternalTransaction;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;

import org.polarsys.capella.common.model.IDelegatedListener;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.tig.ef.command.ICommand;

/**
 */
public class CapellaModelDataListener extends AdapterImpl {

  private IDelegatedListener _delegatedListener;

  /**
   * @return {@code TRUE} if at least one contributor to the extension point {@code delegatedCapellaListener}
   *  wants the given notification {@code notification_p} to be filtered, and {@code FALSE} otherwise.
   */
  public boolean filterNotification(Notification notification_p) {
    IDelegatedListener delegatedListener = getDelegatedListener();
    if (null != delegatedListener) {
      return delegatedListener.filterNotification(notification_p);
    }
    return false;
  }

  /**
   * @return {@link IDelegatedListener}
   */
  private IDelegatedListener getDelegatedListener() {
    if (null == _delegatedListener) {
      for (IConfigurationElement configurationElement : ExtensionPointHelper.getConfigurationElements("org.polarsys.capella.common.model", "DelegatedListener")) { //$NON-NLS-1$ //$NON-NLS-2$
        _delegatedListener = (IDelegatedListener) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
      }
    }
    return _delegatedListener;
  }

  /**
   * This method verifies if there is an active transaction.<br>
   * If such case, the command is simply run.<br>
   * If not, the command is executed through the execution manager.<br>
   * @param command_p the command to be executed
   */
  protected void executeCommand(ICommand command_p) {
    TransactionalEditingDomain editingDomain = MDEAdapterFactory.getEditingDomain();
    if (editingDomain instanceof InternalTransactionalEditingDomain) {
      InternalTransaction activeTransaction = ((InternalTransactionalEditingDomain) editingDomain).getActiveTransaction();
      if (null != activeTransaction && activeTransaction.isActive()) {
        command_p.run();
      } else {
        MDEAdapterFactory.getExecutionManager().execute(command_p);
      }
    }
  }
}
