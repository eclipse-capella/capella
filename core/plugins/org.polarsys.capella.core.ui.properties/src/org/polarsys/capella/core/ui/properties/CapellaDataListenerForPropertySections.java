/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.progress.UIJob;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.model.helpers.listeners.CapellaModelDataListener;

/**
 */
public class CapellaDataListenerForPropertySections extends CapellaModelDataListener {

  /**
   * 
   */
  private Set<TabbedPropertySheetPage> _pages;

  /**
   * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
   */
  @Override
  public void notifyChanged(Notification notification) {
    // pre-condition: call contributed filters
    if (filterNotification(notification)) {
      return;
    }

    // pre-condition: only SET and UNSET notifications are wanted
    if ((notification.getEventType() != Notification.SET) && (notification.getEventType() != Notification.UNSET)) {
      return;
    }

    Object notifier = notification.getNotifier();
    if (notifier instanceof EObject) {
      UIJob refreshView = new UIJob(Display.getDefault(), "Refresh properties view") { //$NON-NLS-1$
            @Override
            public IStatus runInUIThread(IProgressMonitor monitor) {
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
  public void registerPropertySheetPage(TabbedPropertySheetPage page) {
    if (!getPages().contains(page)) {
      getPages().add(page);
    }
  }

  /**
   * 
   */
  public void unregisterPropertySheetPage(TabbedPropertySheetPage page) {
    getPages().remove(page);
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
