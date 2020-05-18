/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.helpers.transaction;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;

/**
 * A ResourceSetListener that finds changes to the containment tree for a TransactionalEditingDomain.
 * Currently supported are detection of moved and deleted elements, added elements are not yet calculated.
 * Subclasses can still add use their private notification filters to find about more specific changes
 * to the containment tree.
 */
public abstract class ContainmentTreeListener extends ResourceSetListenerImpl {

  /**
   * This is the notification filter that is used in the no-arg constructor. It is provided as a static field
   * so that subclasses can use their own filters and combine it with this one (probably via {@link NotificationFilter#and(NotificationFilter) and})
   */
  protected static final NotificationFilter DEFAULT_FILTER = NotificationFilter.NOT_TOUCH.and(NotificationFilter.READ.negated()
      .and(new ContainmentChangeFilter())
      .and(NotificationFilter.createEventTypeFilter(Notification.REMOVE)
          .or(NotificationFilter.createEventTypeFilter(Notification.REMOVE_MANY))
          .or(NotificationFilter.createEventTypeFilter(Notification.SET)))
      );

  /**
   * A filter that matches a notification if its feature is a containment reference
   * or the RESOURCE_CONTENTS pseudo reference.
   */
  private static class ContainmentChangeFilter extends NotificationFilter.Custom {
    @Override
    public boolean matches(Notification notification) {
      // ... on containment references
      if (notification.getFeature() instanceof EReference
          && ((EReference) notification.getFeature()).isContainment()) {
        return true;
      }
      // ... or direct resource contents
      if (notification.getNotifier() instanceof Resource
          && notification.getFeatureID(Resource.class) == Resource.RESOURCE__CONTENTS) {
        return true;
      }
      return false;
    }
  }

  public ContainmentTreeListener() {
    this(DEFAULT_FILTER);
  }

  public ContainmentTreeListener(NotificationFilter filter) {
    super(filter);
  }

  @SuppressWarnings({ "unchecked" })
  @Override
  public final Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {

    Map<EObject, Notification> deleted = new LinkedHashMap<EObject, Notification>();
    Map<EObject, Notification> moved = new LinkedHashMap<EObject, Notification>();

    for (Notification notif : event.getNotifications()) {
      Object old = notif.getOldValue();
      if (old instanceof List) {
        for (EObject o : (List<EObject>) old) {
          if (o.eResource() == null) {
            deleted.put(o, notif);
          } else {
            moved.putIfAbsent(o, notif);
          }
        }
      } else if (old != null) {
        if (((EObject) old).eResource() == null){
          deleted.put((EObject)old, notif);
        } else {
          moved.putIfAbsent((EObject) old, notif);
        }
      }
    }

    if (deleted.size() > 0 || moved.size() > 0) {
      return handleContainmentTreeChange(deleted, moved);
    }

    return null;
  }

  @Override
  public final void resourceSetChanged(ResourceSetChangeEvent event) {
    try {
      transactionAboutToCommit(event);
    } catch (RollbackException e) {
      // snuff this, there can't be a rollback, because the transaction was already committed.
    }
  }


  /**
   * Handles changes to the containment tree. If this is a precommit listener, the returned command
   * will be executed. If this is a postcommit listener, the result will be ignored.
   *
   * <p>
   * A deleted element is
   * an element that is not contained in a Resource after the
   * transaction completes.
   * <p>
   * A moved element
   * is an element whose container at the end of the transaction differs from
   * its (non-null) container when the transaction completes.
   * <p>
   * The keys in the argument are roots of deleted/moved content trees,
   * and the values are the corresponding notifications that describe
   * the removal of the deleted object from its container. The argument maps
   * are sorted by order of removal.
   *
   * @param deleted a map of deleted elements to notifications
   * @param moved a map of moved elements to notifications
   */
  protected abstract Command handleContainmentTreeChange(Map<EObject, Notification> deleted, Map<EObject, Notification> moved);

}
