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
package org.polarsys.capella.common.libraries.policy;

import java.util.HashSet;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.ef.domain.IEditingDomainListener;
import org.polarsys.capella.common.libraries.AccessPolicy;
import org.polarsys.capella.common.libraries.Activator;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;

/**
 */
public class ResourceAccessPolicyListener extends ResourceSetListenerImpl implements IEditingDomainListener {

  @Override
  public Command transactionAboutToCommit(ResourceSetChangeEvent event_p) throws RollbackException {
    TransactionalEditingDomain domain = event_p.getEditingDomain();

    //Retrieve the model linked to the editing domain
    IModel sourceModel = ILibraryManager.INSTANCE.getModel(domain);

    HashSet<IModel> modifiedModels = new HashSet<IModel>();

    //for all notifications, if there is one which can't be modified, we rollback
    for (Notification notification : event_p.getNotifications()) {
      Object notifier = notification.getNotifier();

      if (notifier instanceof EObject) {
        IModel model = ILibraryManager.INSTANCE.getModel((EObject) notifier);
        if (model != null) {
          if (!modifiedModels.contains(model)) {
            if (sourceModel.getAccess(model) == AccessPolicy.READ_ONLY) {
              Logger.getLogger(IReportManagerDefaultComponents.MODEL).error(Messages.ResourceAccessPolicyListener_RollbackReadOnly);
              throw new RollbackException(new Status(IStatus.CANCEL, Activator.PLUGIN_ID, Messages.ResourceAccessPolicyListener_RollbackReadOnly));
            }
          }
          modifiedModels.add(model);
        }
      }
    }
    return null;
  }

  /**
   * @see org.polarsys.capella.common.ef.domain.IEditingDomainListener#createdEditingDomain(EditingDomain)
   */
  @Override
  public void createdEditingDomain(EditingDomain editingDomain) {
    ((TransactionalEditingDomain) editingDomain).addResourceSetListener(this);
  }

  /**
   * @see org.polarsys.capella.common.ef.domain.IEditingDomainListener#disposedEditingDomain(EditingDomain)
   */
  @Override
  public void disposedEditingDomain(EditingDomain editingDomain) {
    ((TransactionalEditingDomain) editingDomain).removeResourceSetListener(this);
  }

}
