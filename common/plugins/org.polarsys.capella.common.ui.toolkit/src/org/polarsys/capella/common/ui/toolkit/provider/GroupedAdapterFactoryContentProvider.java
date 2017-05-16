/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.ui.toolkit.provider;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IViewerNotification;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.NotifyChangedToViewerRefresh;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.ui.progress.UIJob;
import org.polarsys.capella.common.platform.sirius.ted.SiriusSessionListener;

/**
 * A content provider which doens't sent async refresh of ui for each notification.
 * At end of transaction, for all notification received, we trigger only one refresh performing refreshes of all notified objects. 
 */
public class GroupedAdapterFactoryContentProvider extends AdapterFactoryContentProvider {

  protected ResourceSetListener listener = null;

  protected Collection<Notification> notifications = null;

  protected Collection<EObject> toRefresh = new HashSet<EObject>();

  /**
   * @param adapterFactory
   */
  public GroupedAdapterFactoryContentProvider(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  protected ResourceSetListener getListener() {
    if (listener == null) {
      listener = new ResourceSetListenerImpl() {
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isPostcommitOnly() {
          return true;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void resourceSetChanged(ResourceSetChangeEvent event) {
          super.resourceSetChanged(event);
          if (refreshRequired(event)) {
            runRefresh();
          }
        }
      };
    }
    return listener;
  }

  /**
   * @param event
   * @return
   */
  protected boolean refreshRequired(ResourceSetChangeEvent event) {
    if (SiriusSessionListener.isOpeningSession(event.getEditingDomain())) {
      return false;
    }
    if (SiriusSessionListener.isClosingSession(event.getEditingDomain())) {
      return false;
    }

    boolean shouldRefresh = true;

    synchronized (this) {
      shouldRefresh = ((notifications != null) && (notifications.size() > 0)) || ((toRefresh != null) && (toRefresh.size() > 0));
    }

    return shouldRefresh;
  }

  public void notifyChanged2(Notification notification) {
    // If the notification is an IViewerNotification, it specifies how ViewerRefresh should behave.  Otherwise fall
    // back to NotifyChangedToViewerRefresh, which determines how to refresh the viewer directly from the model
    // notification.
    if (notification instanceof IViewerNotification) {
      viewerRefresh.addNotification((IViewerNotification) notification);

    } else {
      new NotifyChangedToViewerRefresh().refresh(viewer, notification.getNotifier(), notification.getEventType(), notification.getFeature(),
          notification.getOldValue(), notification.getNewValue(), notification.getPosition());
    }
  }

  public void runRefresh() {
    if ((viewer != null) && (viewer.getControl() != null) && !viewer.getControl().isDisposed()) {

      UIJob job = new UIJob(viewer.getControl().getDisplay(), Messages.GroupedAdapterFactoryContentProvider_RefreshViewer) {
        @Override
        public IStatus runInUIThread(IProgressMonitor monitor) {
          processRefresh();
          return Status.OK_STATUS;
        }
      };
      job.schedule();
    }
  }

  protected void processRefresh() {
    Collection<Notification> currentNotifications = null;
    Collection<EObject> currentRefresh = null;

    synchronized (this) {
      currentNotifications = notifications;
      notifications = null;
      currentRefresh = toRefresh;
      toRefresh = null;
    }

    if ((viewer != null) && (viewer.getControl() != null) && !viewer.getControl().isDisposed()) {
      HashSet<ChangeNotification> duplicateNotifications = new HashSet<ChangeNotification>();

      if ((currentNotifications != null) && (currentNotifications.size() > 0)) {
        viewerRefresh = new ViewerRefresh(viewer);
        for (Notification notification : currentNotifications) {
          ChangeNotification changeNotification = new ChangeNotification(notification);
          if (!duplicateNotifications.contains(changeNotification)) {
            duplicateNotifications.add(changeNotification);
            notifyChanged2(notification);
          }
        }

        duplicateNotifications.clear();

        if (viewerRefresh != null) {
          viewerRefresh.run();
          viewerRefresh = null;
        }
        currentNotifications.clear();
      }

      if ((currentRefresh != null) && (currentRefresh.size() > 0)) {
        ((StructuredViewer) viewer).update(currentRefresh.toArray(), null);
        currentRefresh.clear();
      }
    }
  }

  public synchronized void addNotification(Notification notification) {
    if (notifications == null) {
      notifications = new ArrayList<Notification>();
    }
    notifications.add(notification);
  }

  /**
   * @param notifications
   */
  protected synchronized void addNotifications(List<Notification> notifications) {
    if (this.notifications == null) {
      this.notifications = new ArrayList<Notification>();
    }
    this.notifications.addAll(notifications);
  }

  public synchronized void addObject(EObject eObject) {
    if (toRefresh == null) {
      toRefresh = new HashSet<EObject>();
    }
    toRefresh.add(eObject);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void notifyChanged(Notification notification) {
    addNotification(notification);
  }

  /**
   * Name change notification
   */
  protected class ChangeNotification {
    private WeakReference<Object> _notifierReference;
    private WeakReference<Object> _featureReference;
    private WeakReference<Object> _newValueReference;
    private WeakReference<Object> _element;
    private int _eventType;

    /**
     * Constructor.
     * @param eventType
     * @param newValue
     * @param notifier
     */
    @Deprecated
    public ChangeNotification(Object notifier, Object feature, Object newValue, int eventType) {
      _notifierReference = new WeakReference<Object>(notifier);
      _featureReference = new WeakReference<Object>(feature);
      _newValueReference = new WeakReference<Object>(newValue);
      _eventType = eventType;
    }

    public ChangeNotification(Notification notification) {
      this(notification.getNotifier(), notification.getFeature(), notification.getNewValue(), notification.getEventType());
      if (notification instanceof ViewerNotification) {
        _element = new WeakReference<Object>(((ViewerNotification) notification).getElement());
      }
    }
    
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
      boolean cr = super.equals(object);

      if (!cr && (object instanceof ChangeNotification)) {
        ChangeNotification notification = (ChangeNotification) object;
        cr = true;

        if (!(((_notifierReference.get() == null) && (notification._notifierReference.get() == null)) || ((_notifierReference.get() != null) && _notifierReference
            .get().equals(notification._notifierReference.get())))) {
          cr = false;

        } else if (!(((_featureReference.get() == null) && (notification._featureReference.get() == null)) || ((_featureReference.get() != null) && _featureReference
            .get().equals(notification._featureReference.get())))) {
          cr = false;

        } else if (!(((_newValueReference.get() == null) && (notification._newValueReference.get() == null)) || ((_newValueReference.get() != null) && _newValueReference
            .get().equals(notification._newValueReference.get())))) {
          cr = false;
          
        } else if (_element != null && !(((_element.get() == null) && (notification._element.get() == null)) || ((_element.get() != null) && _element
            .get().equals(notification._element.get())))) {
          cr = false;
        }

        cr = (cr) ? _eventType == notification._eventType : false;
      }
      return cr;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
      // Based on Thinking In Java book : Overriding hashCode( ) chapter 11
      int result = 17;
      if (_notifierReference.get() != null) {
        result = (37 * result) + _notifierReference.get().hashCode();
      }
      if (_featureReference.get() != null) {
        result = (37 * result) + _featureReference.get().hashCode();
      }
      if (_newValueReference.get() != null) {
        result = (37 * result) + _newValueReference.get().hashCode();
      }
      if (_element != null && _element.get() != null) {
        result = (37 * result) + _element.get().hashCode();
      }
      result = (37 * result) + _eventType;
      return result;
    }
  }
}
