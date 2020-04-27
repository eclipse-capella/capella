/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.helpers;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.NotificationFilter;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class NotificationAnalysis {

  /**
   * Walk over a collection of notifications to find out which elements have been added to the given feature.
   * @param f
   * @param notifications
   * @param clazz
   * @return
   */
  public static <S extends EObject, T extends EObject> Multimap<S,T> getAddedElements(Collection<Notification> notifications, NotificationFilter filter) {

    Multimap<S,T> added = HashMultimap.create();

    for (Notification notif : notifications) {

      if (filter.matches(notif)) {

        S ce = (S) notif.getNotifier();
        if (ce.eIsSet((EStructuralFeature)notif.getFeature())) {
          switch (notif.getEventType()) {
          case Notification.ADD: {
            if (((Collection<?>)ce.eGet((EStructuralFeature) notif.getFeature())).contains((notif.getNewValue()))) {
              added.put(ce, (T) notif.getNewValue());
            }
            break;
          }
          case Notification.ADD_MANY: {
            for (Object o : (Collection<?>)notif.getNewValue()) {
              if (((Collection<?>)ce.eGet((EStructuralFeature) notif.getFeature())).contains(o)) {
                added.put(ce, (T) o);
              }
            }
          }
          }
        }
      }
    }

    return added;

  }

  /**
   * Walk over a collection of notifications to find out which elements have been removed from the given feature
   * @param f
   * @param notifications
   * @param clazz
   * @return
   */
  public static <S extends EObject, T> Multimap<S,T> getRemovedElements(Collection<Notification> notification, NotificationFilter filter) {

    Multimap<S,T> removed = HashMultimap.create();

    for (Notification notif : notification) {

      if (filter.matches(notif)) {

        S ce = (S) notif.getNotifier();
        Collection<?> actual = (Collection<?>) ce.eGet((EStructuralFeature)notif.getFeature());

        switch (notif.getEventType()) {

        case Notification.REMOVE: {
          if (actual == null || !actual.contains(notif.getOldValue())){
            removed.put(ce,(T)notif.getOldValue());
          }
          break;
        }
        case Notification.REMOVE_MANY: {
          if (actual == null) {
            removed.putAll(ce, ((Collection<T>)notif.getOldValue()));
          } else {
            for (Object o : ((Collection<?>)notif.getOldValue())) {
              if (actual.contains(o)){
                removed.put(ce, (T)o);
              }
            }
          }
          break;
        }
        }
      }
    }
    return removed;
  }



  public static Collection<EObject> getDeletedElements(Collection<Notification> notifications) {

    Collection<EObject> deleted = new LinkedHashSet<EObject>();

    for (Notification n : notifications) {

      if (n.getFeature() instanceof EReference && ((EReference)n.getFeature()).isContainment()) {

        if (n.getEventType() == Notification.SET || n.getEventType() == Notification.REMOVE) {

          EObject oldValue = (EObject) n.getOldValue();

          if (oldValue.eContainer() == null && oldValue.eResource() != null) {
            deleted.add(oldValue);
          }
        }

        if (n.getEventType() == Notification.REMOVE_MANY) {
          Collection<EObject> oldValues = (Collection<EObject>) n.getOldValue();
          for (EObject oldValue : oldValues) {
            if (oldValue.eContainer() == null && oldValue.eResource() != null) {
              deleted.addAll(oldValues);
            }
          }
        }
      }

    }

    return deleted;

  }




  public static <T> T getOriginalSingleValue(EObject e, EStructuralFeature feature, List<Notification> notifications) {

    // if the feature was changed, return the value before the first change
    for (Notification n : notifications) {
      if (n.getFeature() == feature && n.getNotifier() == e) {
        return (T) n.getOldValue();
      }
    }

    // otherwise return the current value
    return (T) e.eGet(feature);

  }


}
