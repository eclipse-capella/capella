/*******************************************************************************
 *  Copyright (c) 2007, 2019 LCELB
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *      LCELB - initial API and implementation
 *     Thales - bug fix (see https://polarsys.org/bugs/show_bug.cgi?id=367)
 *     Thales - bug fix (see https://bugs.polarsys.org/show_bug.cgi?id=1570)
 *******************************************************************************/
package org.polarsys.capella.common.platform.sirius.ted;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;

/**
 * Provides EMF model notification with an unique adapter.<br>
 * An adapter can be registered :
 * <ul>
 * <li>using a given model element object ({@link #addAdapter(EObject, Adapter)}),
 * <li>or using a model element class ({@link #addAdapter(Class, Adapter)}).
 * </ul>
 * In the first case, a notification using such an object is always forwarded to registered adapters.<br>
 * In the second one, all objects used in a notification are tested against given class. If such an object implements one of the given class, then registered
 * adapters are returned, and so on for every existing class.<br>
 * That does imply the following behavior :
 * <ul>
 * <li>Inheritance rule : If object O implements A and B, then adapters for both A and B are notified.
 * <li>Uniqueness rule : If N is a registered adapter for both A and B (in the latter case), it will be notified only one time.
 * <li>Blind registration rule : A registered adapter for new object of type A does not have to know the container class of A. If a new object A is created, and
 * added to the model, then this adapter will be automatically notified. Container object is then accessible through {@link Notification#getNotifier()} method.
 * </ul>
 * </p>
 * <p>
 * It is possible to use both ways of registration for different adapters.<br>
 * </p>
 * <p>
 * An adapter registered for a given model element is always removed when this element is removed explicitly from the model.<br>
 * That does not apply to adapters registered for a class (until DataNotifier is garbage collected).
 * </p>
 */
public class DataNotifier extends ResourceSetListenerImpl implements IEditingDomainProvider {

  @Override
  public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
    // Must copy Notifications, see ResourceSetChangeEvent doc
    final List<Notification> notifications = new ArrayList<>(event.getNotifications());
    return new RecordingCommand(event.getEditingDomain()) {
      @Override
      protected void doExecute() {
        for (Notification n : notifications) {
          notifyChanged(n);
        }
      }
    };
  }

  @Override
  public boolean isPrecommitOnly() {
    return true;
  }

  /**
   * Model element class to adapters.
   */
  private Map<Class<?>, Set<Adapter>> _classToAdapters;

  /**
   * Model element object to adapters.
   */
  private Map<EObject, Set<Adapter>> _modelElementToAdapters;

  WeakReference<EditingDomain> _editingDomain;

  /**
   * Constructor.
   */
  public DataNotifier(EditingDomain editingDomain) {
    _classToAdapters = new HashMap<Class<?>, Set<Adapter>>(0);
    _modelElementToAdapters = new HashMap<EObject, Set<Adapter>>(0);
    _editingDomain = new WeakReference<EditingDomain>(editingDomain);
  }

  /**
   * Add an adapter for model elements that implement given class.
   * @param cls The Java type for looked after elements.<br>
   *          This is <b>not</b> the EMF model EClass.<br>
   *          Thus this can only be called on generated models.
   * @param adapter A not <code>null</code> {@link Adapter} listening to changes.
   */
  public void addAdapter(Class<?> cls, Adapter adapter) {
    // Preconditions.
    if ((null == cls) || (null == adapter)) {
      return;
    }
    // Get set from class.
    Set<Adapter> adapters = _classToAdapters.get(cls);
    // Create set if it does not exist.
    if (null == adapters) {
      adapters = new HashSet<Adapter>(1);
      _classToAdapters.put(cls, adapters);
    }
    // Add notifier to set.
    adapters.add(adapter);
  }

  /**
   * Add an adapter for given model element.
   * @param element A not <code>null</code> model element.
   * @param adapter A not <code>null</code> {@link Adapter} listening to changes.
   */
  public void addAdapter(EObject element, Adapter adapter) {
    // Preconditions.
    if ((null == element) || (null == adapter)) {
      return;
    }
    // Get set from object.
    Set<Adapter> adapters = _modelElementToAdapters.get(element);
    // Create set if it does not exist.
    if (null == adapters) {
      adapters = new HashSet<Adapter>(1);
      _modelElementToAdapters.put(element, adapters);
    }
    // Add notifier to set.
    adapters.add(adapter);
  }


  private void notifyChanged(Notification notification) {
    Object notifier = notification.getNotifier();
    Object oldValue = notification.getOldValue();
    Object newValue = notification.getNewValue();
    Object feature = notification.getFeature();
    boolean isContainmentReference = ((feature instanceof EReference) && ((EReference) feature).isContainment());
    // Add adapters by class first, then by reference.
    Set<Adapter> adapters = new HashSet<Adapter>(0);
    // Always search for notifier adapters.
    if (null != notifier) {
      adapters.addAll(searchAdapters(notifier, false));
    }
    // Add adapters depending on notification type.
    switch (notification.getEventType()) {
      // Model element attribute set.
      case Notification.SET:
        // Do not add new value adapters if new value is indeed the notifier.
        if ((null != notifier) && (null != newValue) && (newValue != notifier)) {
          adapters.addAll(searchAdapters(newValue, false));
          // Most unlikely. ModelElement object does launch set notification having same
          // old value and new value, but...
          if ((null != oldValue) && (oldValue != newValue)) {
            adapters.addAll(searchAdapters(oldValue, false));
          }
        }
      break;
      case Notification.ADD:
        // New model element added.
        // Add adapter for new value.
        adapters.addAll(searchAdapters(newValue, false));
      break;
      case Notification.ADD_MANY:
        // New list of model elements added.
        // Add adapters for new values.
        adapters.addAll(searchAdapters((Collection<?>) newValue, false));
      break;
      case Notification.REMOVE:
        // Model element removed.
        // Add adapters for old value.
        // Do remove those that used old value as reference key.
        adapters.addAll(searchAdapters(oldValue, isContainmentReference));
      break;
      case Notification.REMOVE_MANY:
        // List of model elements removed.
        // Add adapters for old values.
        // Do remove those that used old values as reference keys.
        adapters.addAll(searchAdapters((Collection<?>) oldValue, isContainmentReference));
      break;
      default:
      break;
    }
    // Do notify adapters.
    doNotifyAdapters(adapters, notification);
  }

  /**
   * Do notify adapters.
   * @param adapters
   * @param notification void
   */
  protected void doNotifyAdapters(Collection<Adapter> adapters, Notification notification) {
    // Pre-conditions.
    if ((null == adapters) || (null == notification)) {
      return;
    }
    // Notify adapters.
    for (Adapter adapter : adapters) {
      adapter.notifyChanged(notification);
    }
  }

  /**
   * Search adapters for given object.
   * @param object The model object being dealt within the notification.<br>
   *          Empty result is returned if this is not indeed a model element.
   * @param removeExistingAdapters Should existing adapters be removed ? <code>true</code> if so, <code>false</code> otherwise.
   * @return A not <code>null</code> collection of {@link Adapter}. May be empty.
   */
  protected Collection<Adapter> searchAdapters(Object object, boolean removeExistingAdapters) {
    Collection<Adapter> result = searchAdaptersByReference(object, removeExistingAdapters);
    Collection<Adapter> resultByClass = searchAdaptersByClass(object);
    if (null == result) {
      result = resultByClass;
    } else {
      result.addAll(resultByClass);
    }
    return result;
  }

  /**
   * Search adapters for given collection of objects.
   * @param objects Model objects being dealt within the notification.<br>
   *          Empty result is returned if these are not indeed model elements.
   * @param removeExistingAdapters Should existing adapters be removed ? <code>true</code> if so, <code>false</code> otherwise.
   * @return A not <code>null</code> collection of {@link Adapter}. May be empty.
   */
  protected Collection<Adapter> searchAdapters(Collection<?> objects, boolean removeExistingAdapters) {
    Collection<Adapter> result = new HashSet<Adapter>(0);
    for (Object object : objects) {
      result.addAll(searchAdapters(object, removeExistingAdapters));
    }
    return result;
  }

  /**
   * Search adapters by object class.
   * @param object The model object being dealt within the notification.<br>
   *          Empty result is returned if this is not indeed a model element.
   * @return A not <code>null</code> collection of {@link Adapter}. May be empty.
   */
  protected Collection<Adapter> searchAdaptersByClass(Object object) {
    Set<Adapter> result = new HashSet<Adapter>(0);
    if (object instanceof EObject) {
      for (Class<?> modelElementClass : _classToAdapters.keySet()) {
        if (modelElementClass.isInstance(object)) {
          result.addAll(_classToAdapters.get(modelElementClass));
        }
      }
    }
    return result;
  }

  /**
   * Search adapters by object reference.
   * @param object The model object being dealt within the notification.<br>
   *          Empty result is returned if this is not indeed a model element.
   * @param removeAdapters Should existing adapters be removed ? <code>true</code> if so, <code>false</code> otherwise.
   * @return A not <code>null</code> collection of {@link Adapter}. May be empty.
   */
  protected Collection<Adapter> searchAdaptersByReference(Object object, boolean removeAdapters) {
    Collection<Adapter> result = null;
    if (object instanceof EObject) {
      if (removeAdapters) {
        // Remove adapters.
        result = _modelElementToAdapters.remove(object);
      } else {
        // Just get adapters.
        result = _modelElementToAdapters.get(object);
      }
    }
    return result;
  }

  /**
   * Remove adapter from registered ones.
   * @param adapter A not <code>null</code> adapter to remove.
   */
  public void remove(Adapter adapter) {
    // Retrieve all adapters.
    Collection<Set<Adapter>> allAdapters = new ArrayList<Set<Adapter>>(0);
    allAdapters.addAll(_modelElementToAdapters.values());
    allAdapters.addAll(_classToAdapters.values());
    // Iterate over them, so as to remove each reference of given notifier.
    for (Set<Adapter> adaptersSet : allAdapters) {
      // Is given notifier found in current set ?
      boolean found = false;
      for (Iterator<Adapter> adapters = adaptersSet.iterator(); adapters.hasNext() && !found;) {
        // Compare references.
        found = (adapters.next() == adapter);
        if (found) {
          adapters.remove();
        }
      }
    }
  }

  @Override
  public EditingDomain getEditingDomain(){
	  return _editingDomain.get();
  }
}
