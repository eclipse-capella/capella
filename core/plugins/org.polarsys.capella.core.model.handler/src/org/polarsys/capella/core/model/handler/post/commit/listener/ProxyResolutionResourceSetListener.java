/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.handler.post.commit.listener;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
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
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.RollbackException;
import org.polarsys.capella.common.data.helpers.modellingcore.utils.HoldingResourceFilter;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ef.domain.AbstractEditingDomainResourceSetListenerImpl;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.common.platform.sirius.ted.SiriusSessionListener;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 *
 */
public class ProxyResolutionResourceSetListener extends AbstractEditingDomainResourceSetListenerImpl {

  @Override
  public boolean isPrecommitOnly() {
    return true;
  }

  /**
   * In some cases the inverse references are not updated if they point to a content element in a fragment, especially:<br>
   *  - during a "move" of the element pointed to another resource<br>
   *  - when defragmenting a fragment containing an element pointed<br>
   *  - ...  Potentially other case (?)<br>
   * However, this code can also intervene in cases that are not problematic and introduce side effects (and / or performance problems).<br>
   * @param notification_p
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {

    // pre-condition: not while closing a session
    if (SiriusSessionListener.isClosingSession(event.getEditingDomain())) {
      return null;
    }

    Collection<EObject> toVisit = new HashSet<EObject>();

    for (Notification notification : event.getNotifications()) {
      //Avoid any non-containment notification
      if (notification.getFeature() instanceof EReference) {
        if (!((EReference) notification.getFeature()).isContainment()) {
          continue;
        }
      }

      //related element of the notification
      EObject element = null;
      Object notifier = notification.getNotifier();

      if ((notification.getEventType() == Notification.ADD)) {
        Object newValue = notification.getNewValue();
        if (notifier instanceof Resource) {
          if (CapellaResourceHelper.isCapellaResource(notifier)) {
            if (newValue instanceof EObject) {
              element = (EObject) newValue;
            }
          }
        } else if (notifier instanceof ModelElement && notification.getFeature() instanceof EReference) {
          element = (EObject) newValue;
        }
      } else if ((notification.getEventType() == Notification.REMOVE)) {
        Object oldValue = notification.getOldValue();
        if (notifier instanceof Resource) {
          if (CapellaResourceHelper.isCapellaResource(notifier)) {
            if (oldValue instanceof EObject) {
              element = (EObject) oldValue;
            }
          }
        } else if (notifier instanceof ModelElement && notification.getFeature() instanceof EReference) {
          element = (EObject) oldValue;
        }
      }

      //Update contextual elements for an ADD or REMOVE notification, if the element is owned by a valid resource
      if ((notification.getEventType() == Notification.ADD) || (notification.getEventType() == Notification.REMOVE)) {
        if (element != null) {
          if ((element.eResource() != null) && !HoldingResourceFilter.getInstance().isHoldByHoldingResource(element)) {
            toVisit.add(element);
          }
        }
      }
    }

    Collection<EObject> contents = new HashSet<EObject>();
    for (EObject object : toVisit) {
      if (!contents.contains(object)) {
        //For all owned items from the same resource, we must update potential proxies
        contents.add(object);
        Resource resource = object.eResource();
        Iterator<EObject> content = object.eAllContents();
        while (content.hasNext()) {
          EObject element = content.next();
          if (element.eResource() == resource) {
            contents.add(element);
          }
        }
      }
    }
    toVisit.clear();

    SemanticEditingDomain editingDomain = (SemanticEditingDomain) TransactionHelper.getEditingDomain(contents);
    if (null != editingDomain) {
      ECrossReferenceAdapter crossReferencer = editingDomain.getCrossReferencer();

      final HashMap<EObject, EObject> toChange = new HashMap<EObject, EObject>();

      for (EObject eObject : contents) {
        Collection<Setting> inverseReferences = crossReferencer.getInverseReferences(eObject, false);
        for (Setting setting : inverseReferences) {
          Object e = setting.get(false);
          if ((e instanceof EObject) && (((EObject) e).eIsProxy())) {
            toChange.put((EObject) e, eObject);
          } else if (e instanceof EList) {
            EList<Object> es = (EList) e;
            for (Object ess : es) {
              if ((ess instanceof EObject) && (((EObject) ess).eIsProxy())) {
                toChange.put((EObject) ess, eObject);
              }
            }
          }
        }
      }

      contents.clear();

      if (!toChange.isEmpty()) {
        return new RecordingCommand(editingDomain) {
          @Override
          protected void doExecute() {
            for (EObject key : toChange.keySet()) {
              updateProxyURI(key, toChange.get(key));
            }
          }
        };
      }
    }

    return null;
  }

  /**
   * @param proxy_p
   * @param element_p
   */
  protected void updateProxyURI(EObject proxy_p, EObject element_p) {
    if (proxy_p instanceof InternalEObject) {
      String id = EcoreUtil.getID(element_p);
      InternalEObject a = (InternalEObject) proxy_p;
      URI proxyUri = a.eProxyURI();

      //If a proxy uri linked to the same id element
      if ((proxyUri != null)) {
        String fragment = proxyUri.fragment();
        if ((fragment != null) && fragment.equals(id)) {
          URI uri = element_p.eResource().getURI().appendFragment(id);
          a.eSetProxyURI(uri);
        }
      }
    }
  }

}
