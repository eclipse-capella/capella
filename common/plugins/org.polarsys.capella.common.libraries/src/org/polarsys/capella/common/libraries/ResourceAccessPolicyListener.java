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
package org.polarsys.capella.common.libraries;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.sirius.viewpoint.DAnalysis;

import org.polarsys.capella.common.queries.debug.FormatedLogger;
import org.polarsys.capella.common.queries.debug.Log;

/**
 */
public class ResourceAccessPolicyListener extends ResourceSetListenerImpl /* implements SessionManagerListener2 */{

  private static ResourceAccessPolicyListener INSTANCE = null;

  public static ResourceAccessPolicyListener getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new ResourceAccessPolicyListener();
    }
    return INSTANCE;
  }

  @SuppressWarnings("nls")
  @Override
  public Command transactionAboutToCommit(ResourceSetChangeEvent event_p) throws RollbackException {
    FormatedLogger.addTextLn("-- Resource Access Policy Listener --", Log.RESOURCE_ACCESS_POLICY_LISTENER);
    FormatedLogger.incIndent(Log.RESOURCE_ACCESS_POLICY_LISTENER);
    boolean check = true;
    List<Notification> notifications = event_p.getNotifications();
    for (int i = 0; check && (i < notifications.size()); i++) {
      Notification notification = notifications.get(i);
      Object notifier = notification.getNotifier();
      FormatedLogger.addTextLn(notifier, Log.RESOURCE_ACCESS_POLICY_LISTENER);
      if (notifier instanceof EObject) {
        EObject object = (EObject) notifier;
        if (!(object instanceof DAnalysis) && !(object instanceof ModelInformation) && !(object instanceof LibraryReference)) {
          if (!ILibraryManager.INSTANCE.doesEditionIsAuthorized(object)) {
            FormatedLogger.addTextLn("=> !!!!!!!!!!!!!!!!!!!!!!!!!!!!", Log.RESOURCE_ACCESS_POLICY_LISTENER);
            FormatedLogger.addTextLn("=> !! EDITION IS NOT ALLOWED !!", Log.RESOURCE_ACCESS_POLICY_LISTENER);
            FormatedLogger.addTextLn("=> !!!!!!!!!!!!!!!!!!!!!!!!!!!!", Log.RESOURCE_ACCESS_POLICY_LISTENER);
            FormatedLogger.decIndent(Log.RESOURCE_ACCESS_POLICY_LISTENER);
            FormatedLogger.carriageReturn(Log.RESOURCE_ACCESS_POLICY_LISTENER);
            throw new RollbackException(new Status(IStatus.CANCEL, Activator.PLUGIN_ID,
                "This element is part of a library configured with read-only access policy."));
          }
        }
      }
    }
    FormatedLogger.addTextLn("=> edition allowed", Log.RESOURCE_ACCESS_POLICY_LISTENER);
    FormatedLogger.decIndent(Log.RESOURCE_ACCESS_POLICY_LISTENER);
    FormatedLogger.carriageReturn(Log.RESOURCE_ACCESS_POLICY_LISTENER);
    return null;
  }

  // initialize the corresponding model
  // This code shoud be used but event opened is triggered while the melodymodeller file has not been created (in the case of a project
  // creation) ... so the getAbstractModel(session) is done for now in OpenSessionAction, NewProjectWizard.
  // ----------------------------
  // @Override
  // // get the abstract model from session so that the session of the model will be set
  // // we do that by listener because the OpenSessionAction is not always called when a session is opened (for instance at workspace start)
  // public void notify(Session session, int notification_p) {
  // System.out.println(session + " " + notification_p);
  // // if (notification_p == SessionListener.OPENED) {
  // // // initialize the corresponding model
  // // ILibraryManager.INSTANCE.getAbstractModel(session);
  // // }
  // }
  // @SuppressWarnings("deprecation")
  // @Override
  // public void notifyAddSession(Session newSession_p) {
  // // NOTHING
  // }
  //
  // @SuppressWarnings("deprecation")
  // @Override
  // public void notifyRemoveSession(Session removedSession_p) {
  // // NOTHING
  // }
  //
  // @SuppressWarnings("deprecation")
  // @Override
  // public void notifyUpdatedSession(Session updated_p) {
  // // NOTHING
  // }
  //
  // @SuppressWarnings("deprecation")
  // @Override
  // public void viewpointSelected(Viewpoint selectedViewpoint_p) {
  // // NOTHING
  // }
  //
  // @SuppressWarnings("deprecation")
  // @Override
  // public void viewpointDeselected(Viewpoint deselectedViewpoint_p) {
  // // NOTHING
  // }

}
