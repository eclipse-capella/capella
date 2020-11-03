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

package org.polarsys.capella.common.ui.toolkit.browser.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 *
 */
public class BrowserElementUtil {
  /**
   * FIXME : the element can be moved in the transaction! in this case just do a refresh.
   * Takes as input an element, and a list of notifications (that of the emf transaction).
   * and determines whether the element passed as parameter is destroyed or not.
   * @param rootElement
   * @param notifications
   * @return true if element is destroyed.
   */
  @SuppressWarnings("unchecked")
  public static boolean isDestroyed(EObject rootElement, List<Notification> notifications) {
    try {
      List<EObject> list = new LinkedList<EObject>();
      // include itself in the result.
      list.add(rootElement);
      List<EObject> parents = getParents(list, rootElement);
      for (Notification notification : notifications) {
        EStructuralFeature feature = (EStructuralFeature) notification.getFeature();
        if (feature instanceof EReference) {
          Object objectToLink = notification.getNewValue();
          Object formerObject = notification.getOldValue();
          switch (notification.getEventType()) {
            case Notification.SET:
              // relation 0..1. Unset = set(null).
            case Notification.ADD:
              // shouldn't had null but in case it arrives...
              if (null == objectToLink) {
                if (null != formerObject) {
                  if (parents.contains(formerObject)) {
                    return true;
                  }
                }
              }
            break;
            case Notification.REMOVE:
              // relation 0..*
              // check if the old value is among parents of root element.
              if (parents.contains(formerObject)) {
                return true;
              }
            break;
            case Notification.REMOVE_MANY:
              // relation 0..* with multiple objects to remove.
              Collection<EObject> children = (Collection<EObject>) notification.getOldValue();
              // // check if one of old values is among parents of root element.
              for (EObject child : children) {
                if (parents.contains(child))
                  return true;
              }
            break;
            default:
              return false;
          }
        }
      }
      return false;
    } catch (NoSuchElementException exception) {
      return false;
    }
  }

  /**
   * Gather parents of an element.
   * @param parents
   * @param rootElement
   * @return list of parents ordered from the closest to the farest.
   */
  public static List<EObject> getParents(List<EObject> parents, EObject rootElement) {
    EObject parent = rootElement.eContainer();
    if (parent == null)
      return parents;
    parents.add(parent);
    return getParents(parents, parent);
  }

}
