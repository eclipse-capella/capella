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
package org.polarsys.capella.core.model.preferences;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.common.data.helpers.modellingcore.utils.HoldingResourceFilter;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.platform.sirius.tig.ef.SiriusSessionListener;
import org.polarsys.capella.common.platform.sirius.tig.ef.SemanticEditingDomainFactory.SemanticEditingDomain;

/**
 */
public class ResolveProxiesDataListener extends CapellaModelDataListener {

  /**
   * In some cases the inverse references are not updates if they reference an element contained in a fragment, especially:<br>
   *  - during a move of the element referenced to another resource<br>
   *  - when defragmenting a fragment containing an referenced element<br>
   *  - ... potentially other case (?)<br>
   * However, this code can also intervene in cases that are not problematic and introduce side effects (and / or performance issues).<br>
   * 
   * @param notification_p
   */
  @Override
  public void notifyChanged(Notification notification_p) {

    // pre-condition: not while closing a session
    if (SiriusSessionListener.getInstance().isClosingSession()) {
      return;
    }

    // pre-condition: call contributed filters
    if (filterNotification(notification_p)) {
      return;
    }

    //Avoid any non-containement notification
    if (notification_p.getFeature() instanceof EReference) {
      if (!((EReference) notification_p.getFeature()).isContainment()) {
        return;
      }
    }

    //related element of the notification
    EObject element = null; //

    if ((notification_p.getEventType() == Notification.ADD)) {
      if (notification_p.getNotifier() instanceof Resource) {
        if (CapellaResourceHelper.isCapellaResource(notification_p.getNotifier())) {
          element = (EObject) notification_p.getNewValue();
        }
      } else if (notification_p.getNotifier() instanceof ModelElement) {
        element = (EObject) notification_p.getNewValue();
      }
    } else if ((notification_p.getEventType() == Notification.REMOVE)) {
      if (notification_p.getNotifier() instanceof Resource) {
        if (CapellaResourceHelper.isCapellaResource(notification_p.getNotifier())) {
          element = (EObject) notification_p.getOldValue();
        }
      } else if (notification_p.getNotifier() instanceof ModelElement) {
        element = (EObject) notification_p.getOldValue();
      }
    }

    //Update contextual elements for an ADD or REMOVE notification, if the element is owned by a valid resource
    if ((notification_p.getEventType() == Notification.ADD) || (notification_p.getEventType() == Notification.REMOVE)) {
      if (element != null) {
        if ((element.eResource() != null) && !HoldingResourceFilter.getInstance().isHoldByHoldingResource(element)) {
          updateProxyElements(element);
        }
      }
    }
  }

  /**
   * @param rootContent_p
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  protected void updateProxyElements(EObject rootContent_p) {
    SemanticEditingDomain editingDomain = (SemanticEditingDomain) MDEAdapterFactory.getEditingDomain();
    ECrossReferenceAdapter crossReferencer = editingDomain.getCrossReferencer();

    //For all owned items from the same resource, we must update potential proxies
    ArrayList<EObject> contents = new ArrayList<EObject>();

    contents.add(rootContent_p);
    Resource resource = rootContent_p.eResource();
    Iterator<EObject> content = rootContent_p.eAllContents();
    while (content.hasNext()) {
      EObject element = content.next();
      if (element.eResource() == resource) {
        contents.add(element);
      }
    }

    for (EObject eObject : contents) {
      Collection<Setting> inverseReferences = crossReferencer.getInverseReferences(eObject, false);
      for (Setting setting : inverseReferences) {
        Object e = setting.get(false);
        if (e instanceof EObject) {
          updateProxyURI((EObject) e, eObject);
        } else if (e instanceof EList) {
          EList<Object> es = (EList) e;
          for (Object ess : es) {
            if (ess instanceof EObject) {
              updateProxyURI((EObject) ess, eObject);
            }
          }
        }
      }
    }
  }

  /**
   * @param proxy_p
   * @param element_p
   */
  protected void updateProxyURI(EObject proxy_p, EObject element_p) {
    if (proxy_p instanceof InternalEObject) {
      String id = EcoreUtil.getID(element_p);
      InternalEObject a = (InternalEObject) proxy_p;
      //If a proxy uri linked to the same id element
      if ((a.eProxyURI() != null) && a.eProxyURI().fragment().equals(id)) {
        URI uri = element_p.eResource().getURI().appendFragment(id);
        a.eSetProxyURI(uri);
      }
    }
  }
}