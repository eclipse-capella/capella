/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.semantic.browser.view;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticResourceSet;
import org.polarsys.capella.core.data.capellamodeller.util.CapellamodellerResourceImpl;

/**
 * Model change listener for Browser Semantic View. Allow postCommit refresh of view on model changes.
 * 
 * @author ebausson
 */
public class SemanticBrowserModelChangeListener extends ResourceSetListenerImpl {

  private SemanticBrowserElementFilter filter;

  private TransactionalEditingDomain editingDomain;

  private SemanticBrowserView view;

  protected SemanticBrowserModelChangeListener(SemanticBrowserView view) {
    super();
    this.filter = new SemanticBrowserElementFilter();
    this.view = view;
  }

  // Documentation copied from the interface
  @Override
  public NotificationFilter getFilter() {
    return filter;
  }

  @Override
  public boolean isPostcommitOnly() {
    return true;
  }

  @Override
  public void resourceSetChanged(ResourceSetChangeEvent event) {
    super.resourceSetChanged(event);
    if (this.view != null) {
      EclipseUIUtil.displayAsyncExec(view.viewRefreshRunnable);
    }
  }

  public void changeContext(SemanticBrowserView view) {
    if (view.getRootElement() != null) {
      refreshSession(view);
    } else {
      dispose();
    }
  }

  /* Clear listener before deletion */
  public void dispose() {
    if (editingDomain != null) {
      editingDomain.removeResourceSetListener(this);
      this.editingDomain = null;
    }
    this.view = null;
  }

  /**
   * handle change in session and context when view change
   * 
   * @param view
   */
  private void refreshSession(SemanticBrowserView view) {
    TransactionalEditingDomain newEditingDomain = resolveSession(view.getRootElement()).getTransactionalEditingDomain();
    if (editingDomain == null) {
      editingDomain = newEditingDomain;
      newEditingDomain.addResourceSetListener(this);
      this.view = null;
    } else if (editingDomain != newEditingDomain) {
      editingDomain.removeResourceSetListener(this);
      editingDomain = newEditingDomain;
      newEditingDomain.addResourceSetListener(this);
    }
    this.view = view;
  }

  private Session resolveSession(EObject eObject) {
    return SessionManager.INSTANCE.getSession(eObject);
  }

  class SemanticBrowserElementFilter extends NotificationFilter.Custom {

    @Override
    public boolean matches(Notification notification) {
      Object notifier = notification.getNotifier();
      if (notifier instanceof CapellamodellerResourceImpl) {
        CapellamodellerResourceImpl capellaModellerResource = (CapellamodellerResourceImpl) notifier;
        if (capellaModellerResource.getResourceSet() instanceof SemanticResourceSet) {
          SemanticResourceSet resourceSet = (SemanticResourceSet) capellaModellerResource.getResourceSet();
          return resourceSet.getEditingDomain().equals(editingDomain);
        }
      }
      return false;
    }
  }

}
