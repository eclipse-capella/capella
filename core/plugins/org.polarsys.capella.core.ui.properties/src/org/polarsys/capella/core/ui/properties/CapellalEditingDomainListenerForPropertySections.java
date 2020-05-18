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
package org.polarsys.capella.core.ui.properties;

import java.util.Optional;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.polarsys.capella.common.ef.domain.IEditingDomainListener;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.model.IDelegatedListener;
import org.polarsys.kitalpha.emde.model.Element;

/**
 *
 */
public class CapellalEditingDomainListenerForPropertySections extends ResourceSetListenerImpl
    implements IEditingDomainListener {

  /**
   * Unique instance of the data listener.
   */
  private static CapellaDataListenerForPropertySections dataListenerForPropertySections;

  private NotificationFilter notificationFilter;

  private Optional<IDelegatedListener> delegatedListener;

  @Override
  public boolean isPostcommitOnly() {
    return true;
  }

  @Override
  public NotificationFilter getFilter() {
    if (notificationFilter == null) {
      notificationFilter = new NotificationFilter.Custom() {
        @Override
        public boolean matches(Notification notification) {
          Object notifier = notification.getNotifier();

          if (!(notifier instanceof EObject)) {
            return false;
          }

          if (!(notifier instanceof Element || notifier instanceof DRepresentation)) {
            return false;
          }

          if ((notification.getEventType() != Notification.SET)
              && (notification.getEventType() != Notification.UNSET)) {
            return false;
          }

          Optional<IDelegatedListener> listener = getDelegatedListener();
          if (listener.isPresent()) {
            return !listener.get().filterNotification(notification);
          }

          return true;
        }
      };
    }

    return notificationFilter;
  }

  /**
   * @return {@link IDelegatedListener}
   */
  private Optional<IDelegatedListener> getDelegatedListener() {
    // lazy optional
    if (null == delegatedListener) {
      IDelegatedListener listener = null;
      IConfigurationElement[] configurationElements = ExtensionPointHelper
          .getConfigurationElements("org.polarsys.capella.common.model", "DelegatedListener");

      if (configurationElements.length > 0) {
        IConfigurationElement configurationElement = configurationElements[0];

        listener = (IDelegatedListener) ExtensionPointHelper.createInstance(configurationElement,
            ExtensionPointHelper.ATT_CLASS);
      }

      delegatedListener = Optional.ofNullable(listener);
    }

    return delegatedListener;
  }

  /**
   * @see org.polarsys.capella.common.ef.domain.IEditingDomainListener#createdEditingDomain(EditingDomain)
   */
  @Override
  public void createdEditingDomain(EditingDomain editingDomain) {
    if (editingDomain instanceof TransactionalEditingDomain) {
      ((TransactionalEditingDomain) editingDomain).addResourceSetListener(this);
    }
  }

  /**
   * @see org.polarsys.capella.common.ef.domain.IEditingDomainListener#disposedEditingDomain(EditingDomain)
   */
  @Override
  public void disposedEditingDomain(EditingDomain editingDomain) {
    if (editingDomain instanceof TransactionalEditingDomain) {
      ((TransactionalEditingDomain) editingDomain).removeResourceSetListener(this);
    }
  }

  @Override
  public void resourceSetChanged(ResourceSetChangeEvent event) {
    super.resourceSetChanged(event);
    getCapellaDataListenerForPropertySections().refresh();
  }

  /**
   * Getter for the unique instance of the data listener
   */
  public static CapellaDataListenerForPropertySections getCapellaDataListenerForPropertySections() {
    if (null == dataListenerForPropertySections) {
      dataListenerForPropertySections = new CapellaDataListenerForPropertySections();
    }
    return dataListenerForPropertySections;
  }
}
