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
import org.eclipse.sirius.common.tools.api.util.SiriusCrossReferenceAdapterImpl;
import org.polarsys.capella.common.platform.sirius.ted.SiriusSessionListener;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.CrossReferencerHelper;

/**
  * An {@link ECrossReferenceAdapter} that only takes capella resources into account.
  */
public class CapellaECrossReferenceAdapter extends SiriusCrossReferenceAdapterImpl {

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
   * Adapted means fake a Notification against the cross referencer to make sure its internal map is correctly filled in.
   * @param object_p
   */
  protected void adaptAllEReferences(EObject object_p) {
    EList<EReference> eAllReferences = object_p.eClass().getEAllReferences();
    // Loop over all references of specified object. When attaching an object to a new container, its subtree elements must be self adapted too.
    // When attaching to a new container, remove notifications are sent that clears crossreferencer maps regarding this subtree.
    // Hence, we must adapt again the subtree to make sure the cross referencer maps are filled in correctly.
    for (EReference eReference : eAllReferences) {
      int eventType = Notification.ADD_MANY;
      if (!eReference.isMany()) {
        eventType = Notification.SET;
      }
      selfAdapt(new ENotificationImpl((InternalEObject) object_p, eventType, eReference, null, object_p.eGet(eReference)));
    }
  }

  /**
   * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#addAdapter(org.eclipse.emf.common.notify.Notifier)
   */
  @Override
  protected void addAdapter(Notifier notifier_p) {
    if (notifier_p instanceof Resource) {
      // Make sure this is a capella resource.
      if (!retainResource((Resource) notifier_p)) {
        return;
      }
    }
    super.addAdapter(notifier_p);
  }

  @Override
  protected InverseCrossReferencer createInverseCrossReferencer() {
    return new CapellaInverseCrossReferencer();
  }

  /**
   * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#handleContainment(org.eclipse.emf.common.notify.Notification)
   */
  @SuppressWarnings("fallthrough")
  @Override
  protected void handleContainment(Notification notification_p) {
    super.handleContainment(notification_p);
    int eventType = notification_p.getEventType();
    switch (eventType) {
      case Notification.ADD:
      case Notification.SET:
        Object newValue = notification_p.getNewValue();
        if (null != newValue) {
          if (newValue instanceof EObject) {
            // When setting/adding an object, we must make sure its references are also adapted against the cross referencer.
            // It's mandatory when moving an object from a resource to a new one.
            // When creating an object, this one is held by the "holding resource" until its attachment to a new parent.
            // If references are set before attaching (i.e adding through containment relation) the object to its parent, after adding to its new parent, all
            // references data are lost in the inverse cross referencer due to the remove operation from previous resource (i.e the "holding resource").
            // That's why we adapt its references again.
            adaptAllEReferences((EObject) newValue);
          }
          break;
        }
        // 'unset' case equivalent to remove case...
      case Notification.REMOVE:
        EObject oldValue = null;
        try {
          oldValue = EObject.class.cast(notification_p.getOldValue());
        } catch (Exception exception_p) {
          // Do not deal with this value.
        }
        if (null != oldValue) {
          // Free references pointing this element. Call unsetTarget to make sure inverse cross referencer cleans its data accordingly.
          unsetTarget(oldValue);
        }
      break;
      case Notification.ADD_MANY:
        for (Object value : (Collection<?>) notification_p.getNewValue()) {
          if (value instanceof EObject) {
            // See explanations in ADD, SET case.
            adaptAllEReferences((EObject) value);
          }
        }
      break;
      default:
      break;
    }
  }

  /**
   * Handle resource content notification.
   * @param notification_p
   */
  protected boolean handleResourceContentNotification(Notification notification_p) {
    boolean handleSomething = false;
    switch (notification_p.getFeatureID(Resource.class)) {
      case Resource.RESOURCE__CONTENTS:
        switch (notification_p.getEventType()) {
          case Notification.REMOVE:
            // Let the super selfAdapt method does its job i.e don't set to true the return value.
            // Override completely the super selfAdapt method to avoid keeping proxies in memory.
            handleSomething = true;
            // Handle remove operations for objects directly contains by a resource.
            EObject eObject = (EObject) notification_p.getOldValue();
            unsetTarget(eObject);
          break;
        }
      break;
      case Resource.RESOURCE__IS_LOADED: {
        // Override completely the super selfAdapt method to avoid keeping proxies in memory.
        handleSomething = true;
        Object notifier = notification_p.getNotifier();
        if (notification_p.getNewBooleanValue()) {
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
  protected boolean retainResource(Resource resource_p) {
    return CapellaResourceHelper.isCapellaResource(resource_p);
  }

  /**
   * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#selfAdapt(org.eclipse.emf.common.notify.Notification)
   */
  @Override
  protected void selfAdapt(Notification notification_p) {
    Object notifier = notification_p.getNotifier();

    if (notifier instanceof Resource) {
      // Make sure this is a capella resource.
      if (!retainResource((Resource) notifier)) {
        return;
      }
      // Handle resource content changes.
      if (handleResourceContentNotification(notification_p)) {
        return; // Stop here as already handled.
      }
    }
    super.selfAdapt(notification_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean resolve() {
    if (SiriusSessionListener.isClosingSession(_editingDomain.get())) {
      return false;
    }
    
    if (!CrossReferencerHelper.resolutionEnabled()) {
      return false;
    }

    return super.resolve();
  }
}