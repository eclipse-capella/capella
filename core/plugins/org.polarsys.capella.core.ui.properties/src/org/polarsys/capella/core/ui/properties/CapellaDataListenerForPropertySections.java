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
package org.polarsys.capella.core.ui.properties;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.BaseLabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.progress.UIJob;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.core.model.preferences.CapellaModelDataListener;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;

/**
 */
public class CapellaDataListenerForPropertySections extends CapellaModelDataListener {

  /**
   * 
   */
  private static CapellaDataListenerForPropertySections _instance;

  /**
   * 
   */
  private Set<TabbedPropertySheetPage> _pages;

  /**
   * 
   */
  public static CapellaDataListenerForPropertySections getInstance() {
    if (null == _instance) {
      _instance = new CapellaDataListenerForPropertySections();
      registerAdapters();
    }
    return _instance;
  }

  /**
   * 
   */
  private CapellaDataListenerForPropertySections() {
    // cannot be directly instanciated
  }

  /**
   * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
   */
  @Override
  public void notifyChanged(Notification notification_p) {
    // pre-condition: call contributed filters
    if (filterNotification(notification_p)) {
      return;
    }

    // pre-condition: only SET and UNSET notifications are wanted
    if ((notification_p.getEventType() != Notification.SET) && (notification_p.getEventType() != Notification.UNSET)) {
      return;
    }

    Object notifier = notification_p.getNotifier();
    if (notifier instanceof EObject) {
      UIJob refreshView = new UIJob(Display.getDefault(), "Refresh properties view") { //$NON-NLS-1$
            @Override
            public IStatus runInUIThread(IProgressMonitor monitor_p) {
              for (TabbedPropertySheetPage page : getPages()) {
                page.refresh();
                page.labelProviderChanged(new LabelProviderChangedEvent(new BaseLabelProvider(), null));
              }
              return Status.OK_STATUS;
            }
          };
      refreshView.run(new NullProgressMonitor());
    }
  }

  /**
   * 
   */
  private static void registerAdapters() {
    MDEAdapterFactory.getDataNotifier().addAdapter(ModelElement.class, _instance);
    MDEAdapterFactory.getDataNotifier().addAdapter(DRepresentation.class, _instance);

  }

  /**
   * 
   */
  @SuppressWarnings("unused")
  private static void unregisterAdapters() {
    MDEAdapterFactory.getDataNotifier().remove(_instance);
  }

  /**
   * 
   */
  public void registerPropertySheetPage(TabbedPropertySheetPage page_p) {
    if (!getPages().contains(page_p)) {
      getPages().add(page_p);
    }
  }

  /**
   * 
   */
  public void unregisterPropertySheetPage(TabbedPropertySheetPage page_p) {
    getPages().remove(page_p);
  }

  /**
   * 
   */
  public Set<TabbedPropertySheetPage> getPages() {
    if (null == _pages) {
      _pages = new HashSet<TabbedPropertySheetPage>();
    }
    return _pages;
  }
}
