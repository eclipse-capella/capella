/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.libraries.policy;

import java.util.HashSet;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.viewpoint.DRefreshable;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.libraries.AccessPolicy;
import org.polarsys.capella.common.libraries.Activator;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;

/**
 */
public class ResourceAccessPolicyListener extends ResourceSetListenerImpl {

  @Override
  public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
    TransactionalEditingDomain domain = event.getEditingDomain();

    // Retrieve the model linked to the editing domain
    IModel sourceModel = ILibraryManager.INSTANCE.getModel(domain);

    HashSet<IModel> modifiedModels = new HashSet<>();

    // for all notifications, if there is one which can't be modified, we rollback
    for (Notification notification : event.getNotifications()) {
      Object notifier = notification.getNotifier();

      // Look only at semantic elements, not Diagram elements since Diagram elements are not shared by the library
      if (isSemanticElement(notifier)) {
        IModel model = ILibraryManager.INSTANCE.getModel((EObject) notifier);
        if (model != null) {
          if (!modifiedModels.contains(model) && sourceModel.getAccess(model) == AccessPolicy.READ_ONLY) {
              Logger.getLogger(IReportManagerDefaultComponents.MODEL)
                  .error(Messages.ResourceAccessPolicyListener_RollbackReadOnly);
              throw new RollbackException(new Status(IStatus.ERROR, Activator.getDefault().getBundle().getSymbolicName(), Messages.ResourceAccessPolicyListener_RollbackReadOnly));
          }
          modifiedModels.add(model);
        }
      }
    }
    return null;
  }
  
  private boolean isSemanticElement(Object object) {
      return (object instanceof EObject) && !isSiriusElement(object);
  }

  private static boolean isSiriusElement(Object object) {
      return (object instanceof DRefreshable) || (object instanceof DRepresentationDescriptor);
  }
}
