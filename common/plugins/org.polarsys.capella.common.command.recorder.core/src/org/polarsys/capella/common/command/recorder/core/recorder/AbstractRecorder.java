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
package org.polarsys.capella.common.command.recorder.core.recorder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;

import org.polarsys.capella.common.command.recorder.core.manager.AbstractRecorderManager;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManagerListener2;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Basic implementation for a Recorder based on ResourceSetListener.
 *
 */
public abstract class AbstractRecorder extends ResourceSetListenerImpl implements IRecorder, SessionManagerListener2 {

  /** For storage purpose */
  public class Data {
      /** Constructor */
    public Data(Date d, List<Notification> notifs ) {
      _date = d;
      _notifications = new ArrayList<Notification>(notifs);
    }
    /** Accessors on date*/
    public Date getDate() { return _date;}
    /** Accessors on stored notifications */
    public List<Notification> getNotification() { return _notifications;}
    
    /** Date */
    protected Date _date;
    /** Notifications */
    protected List<Notification> _notifications;
  }
  
  /** Manager */
  protected AbstractRecorderManager _manager;
  
  protected List<Data> _events;
  
  /**
   * Constructor 
   */
  public AbstractRecorder(AbstractRecorderManager manager) {
    _manager = manager;
    _events = new ArrayList<Data>();
  }
  
  /**
   * @see org.eclipse.emf.transaction.ResourceSetListenerImpl#resourceSetChanged(org.eclipse.emf.transaction.ResourceSetChangeEvent)
   */
  @Override
  public void resourceSetChanged(ResourceSetChangeEvent event_p) {
    
    _events.add(new Data(new Date(), event_p.getNotifications()));
    
    if (null != _manager) {
      _manager.recorderChanged(this, event_p);
    }
    
    return;
  }

  /**
   * {@inheritDoc}
   */
  public void clearRecords() {
    for (Data data: _events) { // Clear the notifications
      data.getNotification().clear();
    }
    _events.clear();
    return;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isEmpty() {
    return _events.isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  public void notify(Session updatedSession_p, int notification_p) {
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("deprecation")
  public void viewpointDeselected(Viewpoint deselectedViewpoint_p) {
    // Do nothing.
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("deprecation")
  public void viewpointSelected(Viewpoint selectedViewpoint_p) {
    // Do nothing.
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("deprecation")
  public void notifyAddSession(Session newSession_p) {
    // Do nothing.
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("deprecation")
  public void notifyRemoveSession(Session removedSession_p) {
    // Do nothing.
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("deprecation")
  public void notifyUpdatedSession(Session updated_p) {
    // Do nothing.
  }
}
