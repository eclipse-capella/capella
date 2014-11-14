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
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.NotifyChangedToViewerRefresh;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.ui.progress.UIJob;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.platform.sirius.tig.ef.SiriusSessionListener;

/**
 * A content provider which doens't sent async refresh of ui for each notification.
 * At end of transaction, for all notification received, we trigger only one refresh performing refreshes of all notified objects. 
 */
public class GroupedAdapterFactoryContentProvider extends AdapterFactoryContentProvider {

  protected ResourceSetListener listener = null;

  protected Collection<Notification> notifications = null;

  protected Collection<EObject> toRefresh = new HashSet<EObject>();

  /**
   * @param adapterFactory_p
   */
  public GroupedAdapterFactoryContentProvider(AdapterFactory adapterFactory_p) {
    super(adapterFactory_p);
    registerResourceSetListener();
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
        public void resourceSetChanged(ResourceSetChangeEvent event_p) {
          super.resourceSetChanged(event_p);
          if (refreshRequired(event_p)) {
            runRefresh();
          }
        }

      };
    }
    return listener;
  }

  /**
   * @param event_p
   * @return
   */
  protected boolean refreshRequired(ResourceSetChangeEvent event_p) {
    if (SiriusSessionListener.getInstance().isOpeningSession()) {
      return false;
    }
    if (SiriusSessionListener.getInstance().isClosingSession()) {
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

  protected void runRefresh() {
    if ((viewer != null) && (viewer.getControl() != null) && !viewer.getControl().isDisposed()) {

      UIJob job = new UIJob(viewer.getControl().getDisplay(), Messages.GroupedAdapterFactoryContentProvider_RefreshViewer) {
        @Override
        public IStatus runInUIThread(IProgressMonitor monitor_p) {
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
          ChangeNotification changeNotification =
              new ChangeNotification(notification.getNotifier(), notification.getFeature(), notification.getNewValue(), notification.getEventType());
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

  public synchronized void addNotification(Notification notification_p) {
    if (notifications == null) {
      notifications = new ArrayList<Notification>();
    }
    notifications.add(notification_p);
  }

  /**
   * @param notifications_p
   */
  protected synchronized void addNotifications(List<Notification> notifications_p) {
    if (notifications == null) {
      notifications = new ArrayList<Notification>();
    }
    notifications.addAll(notifications_p);
  }

  public synchronized void addObject(EObject eObject_p) {
    if (toRefresh == null) {
      toRefresh = new HashSet<EObject>();
    }
    toRefresh.add(eObject_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void notifyChanged(Notification notification_p) {
    addNotification(notification_p);
  }

  protected void registerResourceSetListener() {
    MDEAdapterFactory.getEditingDomain().addResourceSetListener(getListener());
  }

  /**
   * Name change notification
   */
  protected class ChangeNotification {
    private WeakReference<Object> _notifierReference;
    private WeakReference<Object> _featureReference;
    private WeakReference<Object> _newValueReference;
    private int _eventType;

    /**
     * Constructor.
     * @param eventType_p
     * @param newValue_p
     * @param notifier_p
     */
    public ChangeNotification(Object notifier_p, Object feature_p, Object newValue_p, int eventType_p) {
      _notifierReference = new WeakReference<Object>(notifier_p);
      _featureReference = new WeakReference<Object>(feature_p);
      _newValueReference = new WeakReference<Object>(newValue_p);
      _eventType = eventType_p;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object_p) {
      boolean cr = super.equals(object_p);

      if (!cr && (object_p instanceof ChangeNotification)) {
        ChangeNotification notification = (ChangeNotification) object_p;
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
        result = 37 * result + _notifierReference.get().hashCode();
      }
      if (_featureReference.get() != null) {
        result = 37 * result + _featureReference.get().hashCode();
      }
      if (_newValueReference.get() != null) {
        result = 37 * result + _newValueReference.get().hashCode();
      }
      result = 37 * result + _eventType;
      return result;
    }
  }
}
