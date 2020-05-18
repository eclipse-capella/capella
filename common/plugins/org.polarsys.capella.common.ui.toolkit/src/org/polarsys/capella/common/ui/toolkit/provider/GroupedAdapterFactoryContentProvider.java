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

package org.polarsys.capella.common.ui.toolkit.provider;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.resources.IFile;
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
 * A content provider which doens't sent async refresh of ui for each notification. At end of transaction, for all
 * notification received, we trigger only one refresh performing refreshes of all notified objects.
 */
public class GroupedAdapterFactoryContentProvider extends AdapterFactoryContentProvider {

  protected ResourceSetListener listener = null;

  protected Collection<Notification> notifications = null;

  protected Collection<EObject> toRefresh = new HashSet<>();

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
      shouldRefresh = refreshRequiredForNotifications(notifications) || refreshRequiredForEobject(toRefresh);
    }

    return shouldRefresh;
  }

  protected boolean refreshRequiredForNotifications(Collection<Notification> notifications) {
    return notifications != null && !notifications.isEmpty();
  }

  protected boolean refreshRequiredForEobject(Collection<EObject> toRefresh) {
    return toRefresh != null && !toRefresh.isEmpty();
  }

  public void notifyChanged2(Notification notification) {
    // If the notification is an IViewerNotification, it specifies how ViewerRefresh should behave. Otherwise fall
    // back to NotifyChangedToViewerRefresh, which determines how to refresh the viewer directly from the model
    // notification.
    if (notification instanceof IViewerNotification) {
      viewerRefresh.addNotification((IViewerNotification) notification);

    } else {
      new NotifyChangedToViewerRefresh().refresh(viewer, notification.getNotifier(), notification.getEventType(),
          notification.getFeature(), notification.getOldValue(), notification.getNewValue(),
          notification.getPosition());
    }
  }

  public void runRefresh() {
    if ((viewer != null) && (viewer.getControl() != null) && !viewer.getControl().isDisposed()) {

      UIJob job = new UIJob(viewer.getControl().getDisplay(),
          Messages.GroupedAdapterFactoryContentProvider_RefreshViewer) {
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
      HashSet<ChangeNotification> duplicateNotifications = new HashSet<>();

      if ((currentNotifications != null) && (!currentNotifications.isEmpty())) {
        viewerRefresh = new ViewerRefresh(viewer);
        for (Notification notification : currentNotifications) {
          Object notifier = notification.getNotifier();
          if (!(notifier instanceof EObject && ((EObject)notifier).eResource() == null)) {
            ChangeNotification changeNotification = new ChangeNotification(notification);
            if (!duplicateNotifications.contains(changeNotification)) {
              duplicateNotifications.add(changeNotification);
              notifyChanged2(notification);
            }
          }
        }

        duplicateNotifications.clear();
        viewerRefresh.run();
        viewerRefresh = null;
        currentNotifications.clear();
      }

      if ((currentRefresh != null) && (!currentRefresh.isEmpty())) {
        ((StructuredViewer) viewer).update(currentRefresh.toArray(), null);
        currentRefresh.clear();
      }
    }
  }

  public synchronized void addNotification(Notification notification) {
    addNotifications(Collections.singletonList(notification));
  }

  /**
   * @param notifications
   */
  protected synchronized void addNotifications(List<Notification> notifications) {
    if (this.notifications == null) {
      this.notifications = new ArrayList<>();
    }
    this.notifications.addAll(notifications);
  }

  public synchronized void addObject(EObject eObject) {
    if (toRefresh == null) {
      toRefresh = new HashSet<>();
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
    private WeakReference<Object> notifierReference;
    private WeakReference<Object> featureReference;
    private WeakReference<Object> newValueReference;
    private WeakReference<Object> element;
    private int eventType;

    /**
     * Constructor.
     * 
     * @param eventType
     * @param newValue
     * @param notifier
     */
    @Deprecated
    public ChangeNotification(Object notifier, Object feature, Object newValue, int eventType) {
      this.notifierReference = new WeakReference<>(notifier);
      this.featureReference = new WeakReference<>(feature);
      this.newValueReference = new WeakReference<>(newValue);
      this.eventType = eventType;
    }

    public ChangeNotification(Notification notification) {
      this(notification.getNotifier(), notification.getFeature(), notification.getNewValue(),
          notification.getEventType());
      if (notification instanceof ViewerNotification) {
        element = new WeakReference<>(((ViewerNotification) notification).getElement());
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

        if (!(((this.notifierReference.get() == null) && (notification.notifierReference.get() == null))
            || ((notifierReference.get() != null)
                && notifierReference.get().equals(notification.notifierReference.get())))) {
          cr = false;

        } else if (!(((featureReference.get() == null) && (notification.featureReference.get() == null))
            || ((featureReference.get() != null)
                && featureReference.get().equals(notification.featureReference.get())))) {
          cr = false;

        } else if (!(((newValueReference.get() == null) && (notification.newValueReference.get() == null))
            || ((newValueReference.get() != null)
                && newValueReference.get().equals(notification.newValueReference.get())))) {
          cr = false;

        } else if (element != null && !(((element.get() == null) && (notification.element.get() == null))
            || ((element.get() != null) && element.get().equals(notification.element.get())))) {
          cr = false;
        }

        cr = (cr) ? eventType == notification.eventType : false;
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
      if (notifierReference.get() != null) {
        result = (37 * result) + notifierReference.get().hashCode();
      }
      if (featureReference.get() != null) {
        result = (37 * result) + featureReference.get().hashCode();
      }
      if (newValueReference.get() != null) {
        result = (37 * result) + newValueReference.get().hashCode();
      }
      if (element != null && element.get() != null) {
        result = (37 * result) + element.get().hashCode();
      }
      result = (37 * result) + eventType;
      return result;
    }
  }
}
