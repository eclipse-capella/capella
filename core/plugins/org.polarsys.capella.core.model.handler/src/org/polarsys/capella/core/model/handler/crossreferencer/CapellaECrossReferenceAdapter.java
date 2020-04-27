/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.handler.crossreferencer;

import java.lang.ref.WeakReference;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.sirius.common.tools.api.util.SiriusCrossReferenceAdapter;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 * An {@link ECrossReferenceAdapter} that only takes capella resources into account.
 */
public class CapellaECrossReferenceAdapter extends SiriusCrossReferenceAdapter {

  class CapellaInverseCrossReferencer extends InverseCrossReferencer {
    /**
     * Generated serial UID.
     */
    private static final long serialVersionUID = -3473829340961544993L;

    @Override
    protected void addProxy(EObject proxy, EObject context) {
      // Do nothing to avoid keeping EObjects turn into proxies during the whole application life.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean resolve() {
      return CapellaECrossReferenceAdapter.this.resolve();
    }

  }

  WeakReference<EditingDomain> _editingDomain;

  public CapellaECrossReferenceAdapter(EditingDomain editingDomain) {
    super();
    _editingDomain = new WeakReference<EditingDomain>(editingDomain);
  }

  /**
   * Adapt all references of specified object against the inverse cross referencer.<br>
   * Adapted means fake a Notification against the cross referencer to make sure its internal map is correctly filled
   * in.
   * 
   * @param object_p
   */
  protected void adaptAllEReferences(EObject object) {
    EList<EReference> eAllReferences = object.eClass().getEAllReferences();
    
    // Loop over all covered references of specified object. When attaching an object to a new container, its subtree elements
    // must be self adapted too.
    // When attaching to a new container, remove notifications are sent that clears crossreferencer maps regarding this
    // subtree.
    // Hence, we must adapt again the subtree to make sure the cross referencer maps are filled in correctly.
    for (EReference eReference : eAllReferences) {
      if (eReference.isContainment() || isIncluded(eReference)) {
        int eventType = Notification.ADD_MANY;
        if (!eReference.isMany()) {
          eventType = Notification.SET;
        }
        selfAdapt(new ENotificationImpl((InternalEObject) object, eventType, eReference, null, object.eGet(eReference)));
      }
    }
  }

  /**
   * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#addAdapter(org.eclipse.emf.common.notify.Notifier)
   */
  @Override
  protected void addAdapter(Notifier notifier) {
    if (notifier instanceof Resource) {
      // Make sure this is a capella resource.
      if (!retainResource((Resource) notifier)) {
        return;
      }
    }
    super.addAdapter(notifier);
  }

  @Override
  protected InverseCrossReferencer createInverseCrossReferencer() {
    return new CapellaInverseCrossReferencer();
  }

  /**
   * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#handleContainment(org.eclipse.emf.common.notify.Notification)
   */
  @Override
  protected void handleContainment(Notification notification) {
    super.handleContainment(notification);
    int eventType = notification.getEventType();
    
    //Handle notification.newValue 
    if (eventType == Notification.ADD || eventType == Notification.ADD_MANY || eventType == Notification.SET) {
      Object newValue = notification.getNewValue();
      
      // When setting/adding an object, we must make sure its references are also adapted against the cross
      // referencer.
      // It's mandatory when moving an object from a resource to a new one.
      // When creating an object, this one is held by the "holding resource" until its attachment to a new parent.
      // If references are set before attaching (i.e adding through containment relation) the object to its parent,
      // after adding to its new parent, all
      // references data are lost in the inverse cross referencer due to the remove operation from previous resource
      // (i.e the "holding resource").
      // That's why we adapt its references again.
      
      if (newValue instanceof EObject) {
        adaptAllEReferences((EObject) newValue);
        
      } else if (newValue instanceof Collection<?>) {
        for (Object value : (Collection<?>) notification.getNewValue()) {
          if (value instanceof EObject) {
            adaptAllEReferences((EObject) value);
          }
        }
      }
    }
    
    //Handle notification.oldValue 
    if (eventType == Notification.ADD || eventType == Notification.ADD_MANY || eventType == Notification.SET || 
        eventType == Notification.REMOVE || eventType == Notification.REMOVE_MANY || eventType == Notification.UNSET) {
      
      // The add/remove notification order is not guaranteed in all
      // execution context. It means that we can have an Add
      // notification followed by a Remove one whereas it should be the
      // inverse.
      // So we have to check before doing the unset that it has not
      // been added into another reference by checking that it's
      // container is null.

      // Free references pointing this element. Call unsetTarget
      // to make sure inverse cross referencer cleans its data
      // accordingly.
      
      Object oldValue = notification.getOldValue();
      if (oldValue instanceof EObject) {
        EObject object = (EObject) oldValue;
        if (object.eContainer() == null) {
          unsetTarget(object);
        }
        
      } else if (oldValue instanceof Collection<?>) {
        for (Object value : (Collection<?>) notification.getOldValue()) {
          if (value instanceof EObject) {
            EObject object = (EObject) value;
            if (object.eContainer() == null) {
              unsetTarget(object);
            }
          }
        }
      }
    }
  }

  /**
   * Handle resource content notification.
   * @param notification_p
   */
  protected boolean handleResourceContentNotification(Notification notification) {
    boolean handleSomething = false;
    switch (notification.getFeatureID(Resource.class)) {
      case Resource.RESOURCE__CONTENTS:
        switch (notification.getEventType()) {
          case Notification.REMOVE:
            // Let the super selfAdapt method does its job i.e don't set to true the return value.
            // Override completely the super selfAdapt method to avoid keeping proxies in memory.
            handleSomething = true;
            // Handle remove operations for objects directly contains by a resource.
            EObject eObject = (EObject) notification.getOldValue();
            unsetTarget(eObject);
          break;
        }
      break;
      case Resource.RESOURCE__IS_LOADED: {
        // Override completely the super selfAdapt method to avoid keeping proxies in memory.
        handleSomething = true;
        Object notifier = notification.getNotifier();
        if (notification.getNewBooleanValue()) {
          unloadedResources.remove(notifier);
          for (Notifier child : ((Resource) notifier).getContents()) {
            addAdapter(child);
          }
        } else {
          // DO nothing rather super.selfAdapt method.
        }
        break;
      }
    }
    return handleSomething;
  }

  /**
   * Retain specified resource ?
   * @param resource_p
   * @return
   */
  protected boolean retainResource(Resource resource) {
    return CapellaResourceHelper.isCapellaResource(resource);
  }

  /**
   * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#selfAdapt(org.eclipse.emf.common.notify.Notification)
   */
  @Override
  protected void selfAdapt(Notification notification) {
    Object notifier = notification.getNotifier();

    if (notifier instanceof Resource) {
      // Make sure this is a capella resource.
      if (!retainResource((Resource) notifier)) {
        return;
      }
      // Handle resource content changes.
      if (handleResourceContentNotification(notification)) {
        return; // Stop here as already handled.
      }
    }
    super.selfAdapt(notification);
  }

}