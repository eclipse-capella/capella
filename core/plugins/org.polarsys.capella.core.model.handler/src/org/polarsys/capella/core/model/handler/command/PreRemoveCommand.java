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
package org.polarsys.capella.core.model.handler.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.edit.command.RemoveCommand;

/**
 * A kind of {@link RemoveCommand} but it does nothing to the model.<br>
 * Instead, it creates EMF notifications of what will be done, letting the user confirm the behavior.<br>
 * Note that any list of elements to remove can be provided to the command directly, unlike the original {@link RemoveCommand}.
 */
public class PreRemoveCommand extends AbstractCommand {
  /**
   * Collection of elements that are to be removed.
   */
  private Collection<EObject> _elementsToRemove;
  /**
   * Pre-delete handler.
   */
  private PreDeleteHandler _handler;

  /**
   * Constructor.
   * @param elementsToRemove_p
   * @param handler_p
   */
  public PreRemoveCommand(Collection<EObject> elementsToRemove_p, PreDeleteHandler handler_p) {
    _elementsToRemove = new ArrayList<EObject>(elementsToRemove_p);
    _handler = handler_p;
  }

  /**
   * @see org.eclipse.emf.common.command.AbstractCommand#prepare()
   */
  @Override
  protected boolean prepare() {
    return true;
  }

  /**
   * @see org.eclipse.emf.common.command.Command#execute()
   */
  public void execute() {
    // Cycle through elements to delete.
    for (EObject object : _elementsToRemove) {
      createRemoveNotification(object);
    }
  }

  /**
   * Create remove notification for specified element.
   * @param elementToRemove_p
   * @return
   */
  @SuppressWarnings("unchecked")
  protected void createRemoveNotification(final EObject elementToRemove_p) {
    Notifier notifier = elementToRemove_p.eContainer();
    if (null == notifier) {
      // Container is a resource.
      // Since a resource is not an EObject, just return the resource itself.
      _handler.notifications.add(new NotificationImpl(Notification.REMOVE, elementToRemove_p, null) {
        /**
         * @see org.eclipse.emf.common.notify.impl.NotificationImpl#getNotifier()
         */
        @Override
        public Object getNotifier() {
          return elementToRemove_p.eResource();
        }
      });
    } else {
      // The container is an EObject.
      EObject container = (EObject) notifier;
      // Get all features.
      EList<EStructuralFeature> allStructuralFeatures = container.eClass().getEAllStructuralFeatures();
      // Search for the one that is containing the element to remove.
      for (EStructuralFeature structuralFeature : allStructuralFeatures) {
        if (structuralFeature instanceof EReference) {
          // It has to be a containment reference.
          EReference reference = (EReference) structuralFeature;
          if (reference.isContainment()) {
            Object value = container.eGet(reference);
            // Reference is a container with a multiplicity > 1.
            if (reference.isMany()) {
              // Get reference values for container element.
              List<EObject> objects = (List<EObject>) value;
              if (objects.contains(elementToRemove_p)) {
                // It does contain the element to remove, thus the feature is found.
                _handler.notifications.add(createNotification((InternalEObject) container, Notification.REMOVE, elementToRemove_p, structuralFeature));
                break;
              }
            } else { // Reference is a container with unary multiplicity.
              if (value == elementToRemove_p) {
                // The feature is found.
                _handler.notifications.add(createNotification((InternalEObject) container, Notification.SET, elementToRemove_p, structuralFeature));
              }
            }
          }
        }
      }
    }
  }

  /**
   * Create remove notification with specified type (must be either {@link Notification#REMOVE} or {@link Notification#SET}).
   * @param owner_p
   * @param notificationType_p
   * @param oldValue_p
   * @param feature_p
   * @return
   */
  protected static Notification createNotification(InternalEObject owner_p, int notificationType_p, Object oldValue_p, EStructuralFeature feature_p) {
    return new ENotificationImpl(owner_p, notificationType_p, feature_p, oldValue_p, null);
  }

  /**
   * @see org.eclipse.emf.common.command.AbstractCommand#canUndo()
   */
  @Override
  public boolean canUndo() {
    return false;
  }

  /**
   * @see org.eclipse.emf.common.command.AbstractCommand#undo()
   */
  @Override
  public void undo() {
    // Nothing to do, the command is neither undoable, nor redoable.
  }

  /**
   * @see org.eclipse.emf.common.command.Command#redo()
   */
  public void redo() {
    // Nothing to do, the command is neither undoable, nor redoable.
  }
}
