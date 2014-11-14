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
package org.polarsys.capella.core.model.handler.post.commit.listener;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;

/**
 * Find elements that have been deleted during a transaction.
 * 
 */
public abstract class DeleteElementListener extends ResourceSetListenerImpl {

  public DeleteElementListener() {
    super(NotificationFilter.NOT_TOUCH.and(NotificationFilter.READ.negated()
        .and(new NotificationFilter.Custom() {
          @Override
          public boolean matches(Notification notif) {

            // we look only at remove and set notifications ...
            switch (notif.getEventType()) {
            case Notification.SET:
            case Notification.REMOVE:
            case Notification.REMOVE_MANY:
              break;
            default:
              return false;
            }

            // ... on containment references
            if (notif.getFeature() instanceof EReference
                && ((EReference) notif.getFeature()).isContainment()) {
              return true;
            }

            // ... or direct resource contents
            if (notif.getNotifier() instanceof Resource
                && notif.getFeatureID(Resource.class) == Resource.RESOURCE__CONTENTS) {
              return true;
            }

            return false;
          }
        })));
  }

  @Override
  public boolean isPostcommitOnly(){
    return true;
  }
  
  private void checkEResource(Object o, Set<EObject> deleted){
    if (o instanceof EObject && ((EObject) o).eResource() == null){
      deleted.add((EObject)o);
    }
  }
  
  @SuppressWarnings({ "rawtypes" })
  @Override
  public void resourceSetChanged(ResourceSetChangeEvent event) {
    Set<EObject> deleted = new HashSet<EObject>();
    for (Notification notif : event.getNotifications()) {
      Object old = notif.getOldValue();
      if (old instanceof List) {
        for (Object o : (List) old) {
          checkEResource(o, deleted);
        }
      } else {
        checkEResource(old, deleted);
      }
    }
    if (deleted.size() > 0) {
      handleDelete(deleted);
    }
  }
  
  /**
   * Called for deleted elements. NOTE: Containment children of the deleted 
   * elements are of course also considered deleted, 
   * but not explicitly added to this list. If you need them, use
   * EcoreUtil.getAllContents(..)
   * 
   * @param orphans
   */
  protected abstract void handleDelete(Set<? extends EObject> deleted);
  
}
